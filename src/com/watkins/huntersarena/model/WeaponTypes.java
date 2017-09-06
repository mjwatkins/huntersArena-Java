/**
 * 
 */
package com.watkins.huntersarena.model;

/**
 * @author mwatkins
 *
 */
public enum WeaponTypes {
	DAGGER(1), SHORTSWORD(2),LONGSWORD(3),BROADSWORD(4),AXE(5),
	MACE(6),KATANA(7),GREAT_AXE(8),WARHAMMER(9), CLAYMORE(10);
	
	private int power;
	
	private WeaponTypes(int pwr) {
		this.power = pwr; 
	}
	
	public int getPower() {
		return this.power; 
	}

	public static WeaponTypes getType(int power){
		WeaponTypes result = null; 
		switch(power){
		
		case 1:
			result = DAGGER;
			break;
		case 2:
			result = SHORTSWORD;
			break;
		case 3:
			result = LONGSWORD;
			break;
		case 4:
			result = BROADSWORD;
			break;
		case 5:
			result = AXE;
			break;
		case 6:
			result = MACE;
			break;
		case 7:
			result = KATANA;
			break;
		case 8:
			result = GREAT_AXE;
			break;
		case 9:
			result = GREAT_AXE;
			break;
		case 10:
			result = CLAYMORE;
			break; 
		}		
		return result; 
	}	
	public static String getDescriptor(WeaponTypes type) {
		String descriptor = "";
		switch(type){
		
		case DAGGER:
			descriptor = "Dagger";
			break;
		case SHORTSWORD:
			descriptor = "Shortsword";
			break;
		case LONGSWORD:
			descriptor = "Longsword";
			break;
		case BROADSWORD:
			descriptor = "Broadsword";
			break;
		case AXE:
			descriptor = "Axe";
			break;
		case MACE:
			descriptor = "Mace";
			break;
		case KATANA:
			descriptor = "Katana";
			break;
		case GREAT_AXE:
			descriptor = "Great Axe";
			break;
		case WARHAMMER:
			descriptor = "War Hammer";
			break;
		case CLAYMORE:
			descriptor = "Claymore";
			break; 
		}
		return descriptor;
	}
}
