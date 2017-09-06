/**
 * 
 */
package com.watkins.huntersarena.command;

import javax.swing.JOptionPane;

import com.huntersarena.main.HuntersArena;
import com.watkins.gaming.GameCommand;
import com.watkins.gaming.GameEngine;
import com.watkins.gaming.States;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.action.ExportCharacterAction;
import com.watkins.huntersarena.gui.action.NewGameAction;
import com.watkins.huntersarena.model.Enemy;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class FleeCommand implements GameCommand {

	private GameEngine engine;
	private boolean success;
	private String message;
	private final String SUCCESS_MESSAGE = "\n You fled \n";
	private final String FAIL_MESSAGE = "\n You couldn't outrun it!! \n";
	private final String CRIT_FAIL_MESSAGE = "\n Your opponent has you cornered \n";
	
			
	
	public FleeCommand(GameEngine engine) {
		if (engine instanceof HunterEngine) {
			this.engine = engine; 
			this.success = false; 
			this.message = ""; 
		} else {
			throw new IllegalArgumentException("Not  a Hunter Engine");
		}
		
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameCommand#run()
	 */
	@Override
	public void run() {
		PlayerCharacter mainChar = (PlayerCharacter) engine.getMainCharacter();
		Enemy foe = ((HunterEngine)engine).getFoe(); 
		if(mainChar.rollInitiative() > foe.rollInitiative()){
			success = true; 
			engine.setState(States.MANAGEMENT);
			((HunterEngine)engine).setFoe(null);
			message = SUCCESS_MESSAGE; 
		} else if(mainChar.skillCheck() >= foe.skillCheck()) {
			success = false;
			message = FAIL_MESSAGE;
		} else {
			success = false; 
			AttackCommand attackCmd = new AttackCommand(engine, foe, mainChar);
			attackCmd.run();
			message = CRIT_FAIL_MESSAGE + "\n" + attackCmd.getMessage(); 
			if(engine.getMainCharacter().getHealth() <= 0) {
				((HunterEngine)engine).setFoe(null);
				engine.setState(States.STARTUP);
				JOptionPane.showMessageDialog(null, "You are defeat even as you attept to flee");
				ExportCharacterAction exAction = new ExportCharacterAction();
				exAction.doAction();
				if(exAction.isExportNeeded()){
					ExportCharacterCommand exportCmd = new ExportCharacterCommand(mainChar,
							((HunterEngine)engine).getRound(), States.DEFEAT, exAction.getSaveFile());
					engine.runCommand(exportCmd);
				}
				
				((HunterEngine)engine).setMainChar(null);
				String name = NewGameAction.requestNewCharacterName(HuntersArena.getMainGui().getMainPanel());
				NewGameAction startOver = new NewGameAction((HunterEngine)engine, HuntersArena.getMainGui().getMainPanel(), name);
				startOver.doAction();
				startOver.updateInterface();
				HuntersArena.getMainGui().refreshAll();
			}
		}
		

	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
