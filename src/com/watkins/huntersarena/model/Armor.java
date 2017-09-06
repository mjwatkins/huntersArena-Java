/**
 * 
 */
package com.watkins.huntersarena.model;

import com.watkins.gaming.GameItem;

/**
 * @author mwatkins
 *
 */
public class Armor implements GameItem {
	
	private ArmorTypes type; 
	private int value;
	
	public Armor(int value) {
		this.value = value;
		this.type = ArmorTypes.getType(value);
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameItem#getDescription()
	 */
	@Override
	public String getDescription() {
		return ArmorTypes.getDescriptor(type);
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameItem#getValue()
	 */
	@Override
	public int getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + value;
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
		if (!(obj instanceof Armor))
			return false;
		Armor other = (Armor) obj;
		if (type != other.type)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getDescription();
	}
	
	

}
