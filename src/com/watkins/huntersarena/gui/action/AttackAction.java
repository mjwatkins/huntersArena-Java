/**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import com.huntersarena.main.HuntersArena;
import com.watkins.gaming.Entity;
import com.watkins.gaming.GameAction;
import com.watkins.gaming.GameItem;
import com.watkins.gaming.States;
import com.watkins.huntersarena.command.AttackCommand;
import com.watkins.huntersarena.command.ExportCharacterCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.DisplayPanel;
import com.watkins.huntersarena.gui.InventoryPanel;
import com.watkins.huntersarena.model.Enemy;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * GUI action for launching attack between player and enemy
 * @author mwatkins
 *
 */
public class AttackAction implements GameAction {

	private HunterEngine engine;
	private PlayerCharacter main;
	private Enemy foe;
	private String message; 
	private DisplayPanel panel; 
	private InventoryPanel iPanel;
	private final String NEXT_EXCHANGE = "Attack or Flee? \n";
	private final String PLAYER_LOSES = "You have been defeated \n";
	private final String PLAYER_WINS = " is defeated.  You gain experience: ";
	
	
	/**
	 * @param engine
	 * @param attacker
	 * @param defender
	 */
	public AttackAction(HunterEngine engine, DisplayPanel panel, InventoryPanel iPanel) {
		super();
		this.panel = panel; 
		this.engine = engine;
		this.message = ""; 
		this.iPanel = iPanel; 
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		if(engine.getState() != States.BATTLE || engine.getFoe() == null) {
			JOptionPane.showMessageDialog(panel, "You aren't fighting anyone!");
			return; 
		} else if (engine.getState() == States.STARTUP) {
			JOptionPane.showMessageDialog(panel, "Create new Character");
			return;
		}
		this.main = (PlayerCharacter) engine.getMainCharacter();
		this.foe = (Enemy) engine.getFoe(); 
		Entity attacker;
		Entity defender;
		
		if(main.rollInitiative() > foe.rollInitiative()){
			attacker = main;
			defender = foe;
		} else {
			attacker = foe;
			defender = main;
		}
		AttackCommand cmd = new AttackCommand(engine, attacker, defender);
		cmd.run();
		this.message = "\n" + cmd.getMessage();  
		if (defender.getHealth() >=0 ){
			cmd = new AttackCommand(engine,defender, attacker);
			cmd.run();
			this.message += "\n" +cmd.getMessage(); 
			
		}
		//show enemy hp if skill check succeeds
		if (main.skillCheck() > foe.skillCheck()){
			this.message += "\n" + "The " + foe.getName() + " has " + foe.getHealth() 
			+ " health remaining \n"; 
		} else {
			this.message += "\n The " + foe.getName() + " is concealing his injuries \n"; 
		}
		panel.setPrompt(main.toString() + "\n");
		 
		PlayerCharacter main = (PlayerCharacter) engine.getMainCharacter();
		if(main.getHealth() <= 0) { //player is dead end fight and game
			engine.setState(States.STARTUP);
			this.message += "\n" + PLAYER_LOSES; 
			JOptionPane.showMessageDialog(null, PLAYER_LOSES);
			ExportCharacterAction exAction = new ExportCharacterAction(); 
			exAction.doAction();
			if(exAction.isExportNeeded()){
				ExportCharacterCommand exportCmd = new ExportCharacterCommand(main, 
						engine.getRound(), States.DEFEAT,exAction.getSaveFile());
				engine.runCommand(exportCmd);
			}
			engine.setMainChar(null);
			String name = NewGameAction.requestNewCharacterName(panel);
			NewGameAction startOver = new NewGameAction(engine, panel, name);
			startOver.doAction();
			HuntersArena.getMainGui().refreshAll();
			startOver.updateInterface();
			return; 
		} else if (engine.getFoe().getHealth() <=0) { //foe is dead. End fight
			engine.setState(States.MANAGEMENT);
			this.message += "\n" + engine.getFoe().getName() + PLAYER_WINS + engine.getFoe().getExperience();
			main.addExperice(engine.getFoe().getExperience());
			engine.setVictories(engine.getVictories()+1);
			
			if(!engine.getFoe().getInventory().isEmpty()) {
				GameItem item = engine.getFoe().getInventory().remove(0);
				if (!main.getInventory().contains(item)) {
					main.getInventory().add(item); 
				}
				this.message += "\n" + "You found a " + item.getDescription() + "\n"; 
			}
			engine.setFoe(null); //clear enemy
		} else {  //continue battle
			this.message += "\n" + NEXT_EXCHANGE; 
		}
		panel.setPrompt(main.toString() + "\n");
		this.updateInterface();
		
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		panel.setMessage(this.message);
		iPanel.refreshInventory();
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.doAction();
		
	}

}
