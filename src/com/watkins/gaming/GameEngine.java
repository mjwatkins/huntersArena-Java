/**
 * 
 */
package com.watkins.gaming;

/**
 * Interface for any game engine responsible for executing
 * commands and maintaining game state
 * @author mwatkins
 *
 */
public interface GameEngine {
	
	/**
	 * Run command on a separate thread.
	 * @param command
	 */
	public void runCommand(GameCommand command);
	
	/**
	 * Get main character for this game engine
	 * @return
	 */
	public Entity getMainCharacter();
	
	/**
	 * Create new enemy and store in game engine
	 * @param strength power of enemy (1 being lowest)
	 * @return enemy created
	 */
	public Entity generateEnemy(int strength);
	
	/**
	 * Current game state
	 * @param state state engine is being set to
	 */
	public void setState(States state);
	
	/**
	 * Get current state of game
	 * @return  curent game state.
	 */
	public States getState();
	
	
	
	

}
