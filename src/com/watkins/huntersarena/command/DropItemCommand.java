/**
 * 
 */
package com.watkins.huntersarena.command;

import com.watkins.gaming.GameCommand;
import com.watkins.gaming.GameEngine;
import com.watkins.gaming.GameItem;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class DropItemCommand implements GameCommand {

	private GameEngine engine;
	private GameItem item; 
	private final String DROP_MESSAGE = "You have dropped ";
	private final String FAILED_MESSAGE = "You don't have that in inventory"; 
	private String message;
	
	public DropItemCommand(GameEngine engine, GameItem item) {
		this.engine = engine;
		this.item = item;
		this.message = ""; 
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameCommand#run()
	 */
	@Override
	public void run() {
		PlayerCharacter mainChar = (PlayerCharacter) engine.getMainCharacter();
		if (mainChar.getInventory().remove(item)){
			this.message = DROP_MESSAGE + item.getDescription();
		} else {
			this.message = FAILED_MESSAGE; 
		}
		
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
