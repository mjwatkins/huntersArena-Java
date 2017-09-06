/**
 * 
 */
package com.watkins.huntersarena.model;

import com.watkins.gaming.EntityTrait;

/**
 * @author mwatkins
 *
 */
public enum PlayerClass {

	WARRIOR(EntityTrait.STRENGTH),DUELIST(EntityTrait.SPEED),
	KNIGHT(EntityTrait.CONSTITUTION),GENERAL(EntityTrait.SKILL),
	HUNTER(EntityTrait.SKILL);
	
	private EntityTrait linkedTrait;
	
	private PlayerClass(EntityTrait trait){
		this.linkedTrait = trait; 
	}
	
	public static PlayerClass detrmineClass(EntityTrait trait){
		
		switch(trait) {
		
		case STRENGTH:
			return WARRIOR;
		case SPEED:
			return DUELIST;
		case CONSTITUTION:
			return KNIGHT;
		case SKILL:
			return GENERAL;
		default:
			return HUNTER;
		}
	}
	
	public String toString() {
		
		switch(this) {
		
		case WARRIOR:
			return "Warrior";
		case DUELIST:
			return "Duelist";
		case KNIGHT:
			return "Knight";
		case GENERAL:
			return "General";
		default:
			return "Hunter";
		}		
	}
	
}
