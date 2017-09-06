/**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import com.watkins.gaming.GameAction;
import com.watkins.huntersarena.command.NextRoundCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.DisplayPanel;

/**
 * @author mwatkins
 *
 */
public class NextRoundAction implements GameAction {
	
	private HunterEngine engine;
	private DisplayPanel outputPanel;
	private String outcome; 
	
	public NextRoundAction(HunterEngine engine, DisplayPanel panel) {
		this.engine = engine; 
		this.outputPanel = panel; 
		
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		
		NextRoundCommand cmd = new NextRoundCommand(this.engine,this.outputPanel);
		cmd.run();
		this.updateInterface();
		

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		this.outputPanel.setPrompt(engine.getMainCharacter().toString());

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
