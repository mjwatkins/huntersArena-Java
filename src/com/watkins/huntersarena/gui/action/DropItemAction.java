 /**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import com.watkins.gaming.GameAction;
import com.watkins.gaming.GameEngine;
import com.watkins.gaming.GameItem;
import com.watkins.gaming.States;
import com.watkins.huntersarena.command.DropItemCommand;
import com.watkins.huntersarena.gui.DisplayPanel;
import com.watkins.huntersarena.gui.InventoryPanel;

/**
 * @author mwatkins
 *
 */
public class DropItemAction implements GameAction {

	private GameEngine engine;
	private GameItem item;
	private DisplayPanel outputArea;
	private InventoryPanel iPanel;  
	private String message; 
	
	public DropItemAction(GameEngine engine, DisplayPanel area, InventoryPanel iPanel) {
		this.engine = engine;
		this.iPanel = iPanel; 
		this.outputArea = area;
		
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		
		if(engine.getState() == States.BATTLE) {
			JOptionPane.showMessageDialog(outputArea, "You're too busy to drop something");
			return;
		}
		this.item = iPanel.getSelectedItem(); 
		DropItemCommand cmd = new DropItemCommand(engine, item);
		cmd.run();
		this.message = cmd.getMessage(); 
		
		this.updateInterface();
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		this.outputArea.setMessage(this.message);
		this.outputArea.setPrompt(this.engine.getMainCharacter().toString());
		this.iPanel.refreshInventory();
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
