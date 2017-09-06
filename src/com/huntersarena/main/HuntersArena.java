/**
 * 
 */
package com.huntersarena.main;

import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.ArenaFrame;
import com.watkins.huntersarena.gui.action.NewGameAction;


/**
 * Main class. Provides access to main GUI from anywhere
 * @author mwatkins
 *
 */
public class HuntersArena {
	
	private static ArenaFrame hunterGui;
	
	/**
	 * Sets up new character action before activating main GUI 
	 * @param args
	 */
	public static void main(String[] args) {
		
		

		hunterGui = new ArenaFrame(); 
		//initialize inital game
		String name = NewGameAction.requestNewCharacterName(hunterGui.getMainPanel());
		NewGameAction action = new NewGameAction(HunterEngine.getInstance(),
				hunterGui.getMainPanel(),name); 
		action.doAction();
		action.updateInterface();
		hunterGui.refreshAll();
		hunterGui.setVisible(true);

	}
	/**
	 * Provides access to main GUI.  Should function as near singleton
	 * @return
	 */
	public static ArenaFrame getMainGui() {
		return HuntersArena.hunterGui; 
	}

}
