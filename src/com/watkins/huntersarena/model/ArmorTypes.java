/**
 * 
 */
package com.watkins.huntersarena.model;

/**
 * @author mwatkins
 *
 */
public enum ArmorTypes {

	SILK(1),LEATHER(2),STUDDED_LEATHER(3),BONE(4),CHAIN(5),
	EBONY(6),ROYAL(7),PLATE(8),DRAGON_SCALE(9),AEGIS(10); 
	

	private int power;
	
	private ArmorTypes(int pwr) {
		this.power = pwr; 
	}
	
	public int getPower() {
		return this.power; 
	}
	
	public static ArmorTypes getType(int power){
		ArmorTypes result = null; 
		switch(power){
		
		case 1:
			result = SILK;
			break;
		case 2:
			result = LEATHER;
			break;
		case 3:
			result = STUDDED_LEATHER;
			break;
		case 4:
			result = BONE;
			break;
		case 5:
			result = CHAIN;
			break;
		case 6:
			result = EBONY;
			break;
		case 7:
			result = ROYAL;
			break;
		case 8:
			result = PLATE;
			break;
		case 9:
			result = DRAGON_SCALE;
			break;
		case 10:
			result = AEGIS;
			break; 
		}		
		return result; 
	}
	
	public static String getDescriptor(ArmorTypes type) {
		String descriptor = "";
		switch(type){
		
		case SILK:
			descriptor = "Silk Armor";
			break;
		case LEATHER:
			descriptor = "Leather Armor";
			break;
		case STUDDED_LEATHER:
			descriptor = "Studded Leather Armor";
			break;
		case BONE:
			descriptor = "Bone Armor";
			break;
		case CHAIN:
			descriptor = "Chainmail";
			break;
		case EBONY:
			descriptor = "Ebony Armor";
			break;
		case ROYAL:
			descriptor = "Royal Armor";
			break;
		case PLATE:
			descriptor = "Platemail";
			break;
		case DRAGON_SCALE:
			descriptor = "Dragonscale Armor";
			break;
		case AEGIS:
			descriptor = "Aegis Armor";
			break; 
		}
		return descriptor;
	}	
}
