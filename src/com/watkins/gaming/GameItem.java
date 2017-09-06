/**
 * 
 */
package com.watkins.gaming;

/**
 * Game item used by game entities
 * @author mwatkins
 *
 */
public interface GameItem {

	/**
	 * Text descriptor of item
	 * @return current description
	 */
	public String getDescription();
	
	/**
	 * Operational value of item (e.g. weapon effectiveness)
	 * @return
	 */
	public int getValue(); 
}
