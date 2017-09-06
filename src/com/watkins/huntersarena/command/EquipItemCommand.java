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
public class EquipItemCommand implements GameCommand {

	private GameEngine engine;
	private GameItem item;
	private String SUCCESS_MESSAGE =  "You have equipped ";
	private String FAIL_MESSAGE = "You're not wearing that...";
	private String message; 
	
	 public EquipItemCommand(GameEngine engine, GameItem item) {
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
		mainChar.equip(item);
		if (mainChar.getEquipment().containsValue(item)) {
			this.message = SUCCESS_MESSAGE + this.item.getDescription();
		} else {
			this.message = FAIL_MESSAGE; 
		}

	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
