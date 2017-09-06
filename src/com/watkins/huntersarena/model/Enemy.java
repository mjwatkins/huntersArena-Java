/**
 * 
 */
package com.watkins.huntersarena.model;

import com.watkins.gaming.Dice;
import com.watkins.gaming.EntityTrait;

/**
 * @author mwatkins
 *
 */
public class Enemy extends PlayerCharacter {

	private static final String[] types = {"Spider","Snake","Wolf","Bear", "Rhino", "Panther", "Lion",
                 "Griffin","Dragon","Legendary Hydra"};
	public Enemy(int power) {
		super(types[power-1]);
		Dice factor = new Dice(power);
		int base = power; 
		for (EntityTrait trait: this.getStats().keySet()) {
			this.getStats().put(trait, base + factor.roll() > 4 ? 
					base + factor.roll() : base ); 
			if(trait == EntityTrait.CONSTITUTION) {
				this.setMaxhealth(this.getStats().get(trait) * 10);
				this.setHealth(this.getStats().get(trait) * 10);
			}
		}
		//add equipment
		Dice die = new Dice(10);
		if(die.roll() >= 8) {  //30% chance of drop
			if (die.roll() >= 5) {  //50% chance of weapon
				this.getInventory().add(new Weapon(power));
			} else {
				this.getInventory().add(new Armor(power)); 
			}
			
		}
		super.addExperice(power);
	}

}
