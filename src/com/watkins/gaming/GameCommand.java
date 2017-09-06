/**
 * 
 */
package com.watkins.gaming;

/**
 * Allows all commands to be run on a separate thread.
 * @author mwatkins
 *
 */
public interface GameCommand  extends Runnable{

	/**
	 * Executes game command code
	 */
	public void run(); 
	
	
}
