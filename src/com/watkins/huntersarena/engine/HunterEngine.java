/**
 * 
 */
package com.watkins.huntersarena.engine;


import java.util.ArrayList;

import com.watkins.gaming.Dice;
import com.watkins.gaming.Entity;
import com.watkins.gaming.GameCommand;
import com.watkins.gaming.GameEngine;
import com.watkins.gaming.States;
import com.watkins.huntersarena.model.Enemy;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * Executes main game functions and maintains game status for hunter's
 * arena. implemented as a singleton
 * @author mwatkins
 *
 */
public class HunterEngine implements GameEngine {

	public static final int DEFAULT_DIE = 6; 
	public static final int DEF_MOD = 2; 
	public static final int MIN_VICTORIES = 5; 
	private PlayerCharacter mainchar;
	private Enemy foe;
	private int round;
	private boolean gameOver;
	private int victories;
	private ArrayList<GameCommand> validCommands;
	private States state; 
	private static HunterEngine engine;

	/**
	 * Allows access to singleton engine
	 * @return
	 */
	public static HunterEngine getInstance() {
		if (engine == null) {
			engine = new HunterEngine(); 
		}
		return engine; 
	}
	
	private HunterEngine() {
		this.state = States.STARTUP;
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameEngine#runCommand(com.watkins.gaming.GameCommand)
	 */
	@Override
	public synchronized void runCommand(GameCommand command) {
		Thread workerThread = new Thread(command); 
		workerThread.start();

	}
	
	
	/**
	 * Can be used to calcuate attack attempt
	 * @param attacker
	 * @param defender
	 * @return
	 */
	public int attackAttempt(Entity attacker, Entity defender) {
		int armorValue = (defender.getEquipment().get(PlayerCharacter.CHEST_SLOT) != null)?
			defender.getEquipment().get(PlayerCharacter.CHEST_SLOT).getValue():0; 
		if (attacker.toHit() >= defender.calcDef()) {
			int damage = attacker.calcDamage() - armorValue;
			if (damage > 0) {
				defender.setHealth(defender.getHealth()-damage);
				return damage; 
			} else {
				return 0; 
			}
		} else {
			return 0; 
		}
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameEngine#getMainCharacter()
	 */
	@Override
	public Entity getMainCharacter() {
		return this.mainchar;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameEngine#generateEnemy(int)
	 */
	@Override
	public Entity generateEnemy(int strength) {
		//change strength to random value... 
		Dice die = new Dice(strength); 
		int currentStrength = die.roll(); 
		if (currentStrength < (strength -2)) {
			currentStrength = strength - 2; 
		}
		Entity newEnemy = new Enemy(currentStrength);
		this.foe = (Enemy) newEnemy; 
		return newEnemy;
	}

	public Enemy getFoe() {
		return foe;
	}

	public void setFoe(Enemy foe) {
		this.foe = foe;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public void setMainChar(PlayerCharacter player) {
		this.mainchar = player; 
	}
}
