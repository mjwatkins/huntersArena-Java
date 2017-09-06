/**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import com.watkins.gaming.GameAction;
import com.watkins.gaming.States;
import com.watkins.huntersarena.command.FleeCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.DisplayPanel;
import com.watkins.huntersarena.model.Enemy;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class FleeAction implements GameAction {

	
	private DisplayPanel panel;
	private HunterEngine engine;
	private PlayerCharacter main;
	private Enemy foe;
	private String message;
	
	
	/**
	 * @param panel
	 * @param engine
	 */
	public FleeAction(DisplayPanel panel, HunterEngine engine) {
		super();
		this.panel = panel;
		this.engine = engine;
		//this.main = (PlayerCharacter)engine.getMainCharacter();
		//this.foe = (Enemy)engine.getFoe(); 
		this.message = ""; 
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		if(engine.getFoe() == null) {
			JOptionPane.showMessageDialog(panel, "You aren't fighting anyone!");
			return; 
		} else if (engine.getState() == States.STARTUP) {
			JOptionPane.showMessageDialog(panel, "Create new Character");
			return;
		}
		FleeCommand cmd = new FleeCommand(engine);
		cmd.run();
		this.message = cmd.getMessage();
		this.updateInterface();
		
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		this.panel.setMessage(this.message);
		this.panel.setPrompt(engine.getMainCharacter().toString());
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
