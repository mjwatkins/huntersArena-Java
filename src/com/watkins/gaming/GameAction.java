/**
 * 
 */
package com.watkins.gaming;

import javax.swing.Action;

/**
 * Customized action allowing alternate uses of 
 * action classes outside of GUI interface.
 * @author mwatkins
 *
 */
public interface GameAction  extends Action {

	/**
	 * Commence Action's core activity based on existing data
	 */
	public void doAction(); 
	
	/**
	 * Update the interface basked on outcomes
	 */
	public void updateInterface(); 
}
