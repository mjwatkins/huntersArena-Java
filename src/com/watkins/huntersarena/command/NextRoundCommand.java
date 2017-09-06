/**
 * 
 */
package com.watkins.huntersarena.command;

import javax.swing.JOptionPane;

import com.huntersarena.main.HuntersArena;
import com.watkins.gaming.GameCommand;
import com.watkins.gaming.States;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.DisplayPanel;
import com.watkins.huntersarena.gui.action.ExportCharacterAction;
import com.watkins.huntersarena.gui.action.NewGameAction;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class NextRoundCommand implements GameCommand {

	private HunterEngine engine;
	private DisplayPanel panel; 
	private String congratsMessage;
	private final static String YOU_WIN = "You Win !!!"; 
	private final static String NOT_YET = "Not enough victories. Keep fighting";
	
	 public NextRoundCommand(HunterEngine engine, DisplayPanel panel) {
		this.engine = engine; 
		this.panel = panel; 
		this.congratsMessage = "Round " + engine.getRound() + " is complete! \n" +
				" Starting round " + (engine.getRound() + 1); 
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameCommand#run()
	 */
	@Override
	public void run() {
		
		if (engine.getState() == States.BATTLE) {
			JOptionPane.showMessageDialog(HuntersArena.getMainGui(), "You're stil fighting");
			return; 
		}
		if (engine.getVictories() >= 5 && engine.getRound() < 10) {
			JOptionPane.showMessageDialog(HuntersArena.getMainGui(), congratsMessage);
			engine.setVictories(0);
			engine.getMainCharacter().setHealth(engine.getMainCharacter().getMaxHealth());
			engine.setRound(engine.getRound() + 1);
		} else if (engine.getVictories() >= 5 && engine.getRound() >= 10) {
			engine.setGameOver(true);
			engine.setState(States.STARTUP);
			JOptionPane.showMessageDialog(HuntersArena.getMainGui(), YOU_WIN);
			ExportCharacterAction exAction = new ExportCharacterAction();
			exAction.doAction();
			if (exAction.isExportNeeded()){
				ExportCharacterCommand exportCmd = new ExportCharacterCommand((PlayerCharacter) engine.getMainCharacter(),
						engine.getRound(), States.VICTORY, exAction.getSaveFile());
				engine.runCommand(exportCmd);
			}
			engine.setMainChar(null);
			String name = NewGameAction.requestNewCharacterName(null);
			NewGameAction action =  new NewGameAction(HunterEngine.getInstance(), panel ,name);
			action.doAction();
			action.updateInterface();
			HuntersArena.getMainGui().refreshAll();
		} else {
			JOptionPane.showMessageDialog(null, NOT_YET);
		}
	}

}
