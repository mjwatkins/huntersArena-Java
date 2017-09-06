/**
 * 
 */
package com.watkins.huntersarena.command;

import com.watkins.gaming.GameCommand;
import com.watkins.gaming.GameEngine;
import com.watkins.gaming.States;
import com.watkins.huntersarena.engine.HunterEngine;

/**
 * @author mwatkins
 *
 */
public class StartBattleCommand implements GameCommand {

	private GameEngine engine;
	private String message;
	
	public StartBattleCommand(GameEngine engine) {
		if(engine instanceof HunterEngine){
			this.engine = engine;
			this.message = "";
			
		} else {
			throw new IllegalArgumentException("Not a hunter engine"); 
		}
	}
	
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameCommand#run()
	 */
	@Override
	public void run() {
		com.watkins.gaming.Entity foe = engine.generateEnemy(((HunterEngine)engine).getRound());
		engine.setState(States.BATTLE);
		this.message = "You have entered battle against a " + foe.getName() + "\n"; 
		
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	

}
