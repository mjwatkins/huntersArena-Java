/**
 * 
 */
package com.watkins.huntersarena.command;

import javax.swing.JOptionPane;

import com.watkins.gaming.GameCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.model.Armor;
import com.watkins.huntersarena.model.PlayerCharacter;
import com.watkins.huntersarena.model.Weapon;

/**
 * @author mwatkins
 *
 */
public class NewGameCommand implements GameCommand {

	private HunterEngine engine;
	private String name;
	private final String GAME_INTRO ="Prepare to face 10 rounds of challengers.\n" +
			"You can face as many challengers in a round as you wish. \n" +
			"However, you mus defeat at least 5 before you can move on\n"; 
	private final String WELCOME = "Welcome Hunter!";
	
	
	public NewGameCommand(HunterEngine engine, String name) {
		this.engine = engine; 
		this.name = name; 
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameCommand#run()
	 */
	@Override
	public void run() {
		engine.setRound(1);
		PlayerCharacter newChar = new PlayerCharacter(name);
		newChar.equip(new Weapon(1));
		newChar.equip(new Armor(1));
		engine.setMainChar(newChar);
		JOptionPane.showMessageDialog(null, GAME_INTRO,WELCOME, JOptionPane.PLAIN_MESSAGE);
	}

}
