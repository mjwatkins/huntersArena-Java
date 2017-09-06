/**
 * 
 */
package com.watkins.huntersarena.command;

import com.watkins.gaming.EntityTrait;
import com.watkins.gaming.GameCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class IncreaseLevelCommand implements GameCommand{
	
	private HunterEngine engine;
	private EntityTrait trait; 
	
	public IncreaseLevelCommand(HunterEngine engine,EntityTrait trait){
		this.engine = engine; 
		this.trait = trait; 
	}

	@Override
	public void run() {
		PlayerCharacter mainChar = (PlayerCharacter) engine.getMainCharacter();
		switch(trait){
		
		case STRENGTH:
			mainChar.increaseStat(trait);
			break;
		case SPEED:
			mainChar.increaseStat(trait);
			break;
		case CONSTITUTION:
			mainChar.increaseStat(trait);
			mainChar.setMaxhealth(mainChar.getStats().get(trait) * 10);
			break;
		case SKILL:
			mainChar.increaseStat(trait);
			break;
		}
		mainChar.increaseLevel();
			
	}
		
}
	


