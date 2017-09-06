/**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.watkins.gaming.GameAction;
import com.watkins.huntersarena.command.NewGameCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.DisplayPanel;

/**
 * @author mwatkins
 *
 */
public class NewGameAction implements GameAction {

	private HunterEngine engine;
	private DisplayPanel panel;
	private String message;
	private String name;
	
	
	/**
	 * @param engine
	 * @param panel
	 */
	public NewGameAction(HunterEngine engine, DisplayPanel panel, String name) {
		super();
		this.engine = engine;
		this.panel = panel;
		this.name = name; 
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		
		NewGameCommand cmd = new NewGameCommand(engine, name);
		cmd.run();
		this.updateInterface();

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		panel.setPrompt(engine.getMainCharacter().toString() + "\n");
		panel.setMessage("Welcome to Hunter's Arena");
		
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
	
	public static String requestNewCharacterName(JPanel parent) {
		String name = null;
		name = JOptionPane.showInputDialog(parent, "What is your name?"); 
		return name; 
	}

}
