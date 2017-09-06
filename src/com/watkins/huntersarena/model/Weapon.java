/**
 * 
 */
package com.watkins.huntersarena.model;

import com.watkins.gaming.Dice;
import com.watkins.gaming.EntityTrait;
import com.watkins.gaming.GameItem;

/**
 * @author mwatkins
 *
 */
public class Weapon implements GameItem{

	private String description;
	private int value; 
	private EntityTrait trait; 
	private WeaponTypes type; 
	private Dice die; 
	
	public Weapon(int power) {
		this.value = power;
		this.trait = EntityTrait.getRandomTrait();
		this.type = WeaponTypes.getType(power);
		this.description = EntityTrait.getTraitDescriptor(trait) + " " +
				WeaponTypes.getDescriptor(type);
		this.die = new Dice(power);
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public int getValue() {
		return this.value;
	}
	
	public EntityTrait getTrait() {
		return trait;
		
	}
	
	public int getDamage() {
		return this.die.roll(); 
	}
	
	public String toString() {
		return this.getDescription(); 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trait == null) ? 0 : trait.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Weapon))
			return false;
		Weapon other = (Weapon) obj;
		if (trait != other.trait)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
