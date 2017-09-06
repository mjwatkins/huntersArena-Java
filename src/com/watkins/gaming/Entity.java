/**
 * 
 */
package com.watkins.gaming;

import java.util.List;
import java.util.Map;

/**
 * General interface for any type of character.
 * @author mwatkins
 */
public interface Entity {

	/**
	 * Gives short name of entity
	 * @return
	 */
	public String getName(); 
	
	/**
	 * Set name of entity
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Gives long name of entity
	 * @return
	 */
	public String getDescription();
	
	/**
	 * Set description
	 * @param desc
	 */
	public void setDescription(String desc);
	
	/**
	 * Set current health
	 * @param health
	 */
	public void setHealth(int health);
	
	/**
	 * Get the current health points
	 * @return
	 */
	public int getHealth();
	
	/**
	 * Get the maximum health points
	 * @return
	 */
	public int getMaxHealth();
	
	/**
	 * Set the current health
	 * @param health
	 */
	public void setMaxhealth(int health);
	
	/**
	 * Returns the level of the entity
	 * @return
	 */
	public int getLevel(); 
	
	/**
	 * Provides all game items carried by the entity
	 * @return list of game items
	 */
	public List<GameItem> getInventory();
	
	/**
	 * Give a map of the equipment carried. Where
	 * String is the name of the equipment slot.
	 * @return map of equipment
	 */
	public Map<String,GameItem> getEquipment();
	
	/**
	 * Gives map of statistics. Where Integer is the
	 * value of the requested state
	 * @return map of stats
	 */
	public Map<EntityTrait,Integer> getStats(); 
	
	/**
	 * Calculate hit capability for any individual attack.
	 * @return current hti value
	 */
	public int toHit();
	
	/**
	 * Calculate difficulty to hit enity
	 * @return current hit difficulty
	 */
	public int calcDef(); 
	
	/**
	 * Calculate damage caused on successful hit
	 * @return damage caused
	 */
	public int calcDamage(); 
	
}
