/**
 * 
 */
package com.watkins.gaming;

/**
 * Specific capability types for game entities
 * @author mwatkins
 *
 */
public enum EntityTrait {

	STRENGTH,SPEED,CONSTITUTION,SKILL;
	
	private static final int NUM_TRAITS = 4; 
	
	public String toString() {
		String output = ""; 
		switch(this) {
		case STRENGTH:
			output = "Strength";
			break;
		case SPEED:
			output ="Speed";
			break;
		case SKILL:
			output = "Skill";
			break; 
		case CONSTITUTION:
			output = "Constitution";
			break;
		}
		return output;
	}
	
	/**
	 * Outputs matching descriptor for a item that utilizes the matching 
	 * trait.  (e.g. Sleek weapons may make use of speed in damage calc)
	 * @param trait  related trauit
	 * @return  Descriptive term
	 */
	public static String getTraitDescriptor(EntityTrait trait) {
		String descriptor = "";
		switch (trait) {
		
		case STRENGTH:
			descriptor = "Brutal";
			break;
		case SPEED:
			descriptor = "Sleek";
			break;
		case CONSTITUTION:
			descriptor = "Heavy";
			break;
		case SKILL:
			descriptor = "Tactical";
			break;
		}
		return descriptor; 
	}
	
	/**
	 * Selects a trait from existing list at random.  ideal for 
	 * selecting game item traits.
	 * @return Selected trait.
	 */
	public static EntityTrait getRandomTrait() {
		EntityTrait trait = null;
		Dice die = new Dice(NUM_TRAITS); 
		int result = die.roll();
		if (result == 1) {
			trait = EntityTrait.STRENGTH;
		} else if (result == 2) {
			trait = EntityTrait.SPEED;
		} else if (result == 3) {
			trait = CONSTITUTION;
		} else {
			trait = SKILL; 
		}
		
		return trait; 
	}
	

}
