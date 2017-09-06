/**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import com.watkins.gaming.GameAction;
import com.watkins.gaming.States;
import com.watkins.huntersarena.command.StartBattleCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.DisplayPanel;

/**
 * @author mwatkins
 *
 */
public class StartFightAction implements GameAction {

	private HunterEngine engine;
	private DisplayPanel outputPanel;
	private String message; 
	
	
	public StartFightAction(HunterEngine engine, DisplayPanel outputPanel) {
		super();
		this.engine = engine;
		this.outputPanel = outputPanel;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		if (engine.getState() == States.BATTLE){
			JOptionPane.showMessageDialog(outputPanel, "You are already in a fight");
			return; 
		} 
		StartBattleCommand cmd =  new StartBattleCommand(engine);
		cmd.run();
		this.message = cmd.getMessage(); 
		this.updateInterface();

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		
		this.outputPanel.setMessage(this.message);
		this.outputPanel.setPrompt(engine.getMainCharacter().toString() + "\n");
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
