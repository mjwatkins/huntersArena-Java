/**
 * 
 */
package com.watkins.huntersarena.command;

import com.watkins.gaming.Entity;
import com.watkins.gaming.GameCommand;
import com.watkins.gaming.GameEngine;
import com.watkins.huntersarena.engine.HunterEngine;

/**
 * @author mwatkins
 *
 */
public class AttackCommand implements GameCommand {

	private GameEngine engine;
	private String HIT_MESSAGE = " hits for ";
	private String MISS_MESASGE = " misses...";
	private String message; 
	private Entity attacker;
	private Entity defender; 
	
	public AttackCommand (GameEngine engine,Entity attacker, Entity defender) {
		if (engine instanceof HunterEngine) {
			this.engine = engine;
			this.attacker = attacker;
			this.defender = defender;
		} else {
			throw new IllegalArgumentException("Not a Hunter Engine!!");
		}
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameCommand#run()
	 */
	@Override
	public void run() {
		int damage = ((HunterEngine) engine).attackAttempt(attacker, defender);
		if (damage > 0) {
			this.message = attacker.getName() + HIT_MESSAGE + damage +" damage";
		} else {
			this.message = attacker.getName() + MISS_MESASGE; 
		}

	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
