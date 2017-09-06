/**
 * 
 */
package com.watkins.huntersarena.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.watkins.gaming.Dice;
import com.watkins.gaming.Entity;
import com.watkins.gaming.EntityTrait;
import com.watkins.gaming.GameItem;
import com.watkins.huntersarena.engine.HunterEngine;

/**
 * @author mwatkins
 *
 */
public class PlayerCharacter implements Entity {

	public static final String WEAPON_SLOT = "Weapon";
	public static final String CHEST_SLOT = "Armor";
	public static final int MAX_DEFAULT_STAT = 4; 
	private String name;
	private String desc; 
	private int level;
	private PlayerClass currentClass; 
	private int hp;
	private int maxHealth; 
	private long experience; 
	private ArrayList<GameItem> inventory;
	private HashMap<String,GameItem> equipment;
	private HashMap<EntityTrait,Integer> stats;
	
	
	private boolean rolled = false; 
	
	
	public PlayerCharacter(String name){
		this.name = name;
		this.level = 1;
		this.experience = 0;
		
		Dice die = new Dice(MAX_DEFAULT_STAT); 
		this.stats = new HashMap<EntityTrait,Integer>();
		this.stats.put(EntityTrait.STRENGTH,die.roll());
		this.stats.put(EntityTrait.SPEED, die.roll());
		this.stats.put(EntityTrait.CONSTITUTION, die.roll());
		this.stats.put(EntityTrait.SKILL, die.roll());
		this.hp = this.stats.get(EntityTrait.CONSTITUTION) * 10; 
		this.rolled = true; 
		this.maxHealth = this.hp; 
		this.inventory = new ArrayList<GameItem>();
		this.equipment = new HashMap<String,GameItem>();
		this.updateCurrentClass();
	}
	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getName()
	 */
	@Override
	public String getName() {
		return this.name; 
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String desc) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#setHealth(int)
	 */
	@Override
	public void setHealth(int health) {
		this.hp = health; 

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getHealth()
	 */
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return this.hp;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getMaxHealth()
	 */
	@Override
	public int getMaxHealth() {
		return this.maxHealth;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#setMaxhealth(int)
	 */
	@Override
	public void setMaxhealth(int health) {
		this.maxHealth = health; 

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.level;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getInventory()
	 */
	@Override
	public List<GameItem> getInventory() {
		return this.inventory;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getEquipment()
	 */
	@Override
	public Map<String, GameItem> getEquipment() {
		return this.equipment;
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.Entity#getStats()
	 */
	@Override
	public HashMap<EntityTrait, Integer> getStats() {
		return this.stats;
	}

	@Override
	public int toHit() {
		Dice die = new Dice(HunterEngine.DEFAULT_DIE);
		return this.stats.get(EntityTrait.SKILL) +  die.rollDice(this.stats.get(EntityTrait.SPEED)) ;
	}
	@Override
	public int calcDef() {
		if (this.equipment.get(CHEST_SLOT) != null) {
			return  this.stats.get(EntityTrait.SPEED) * HunterEngine.DEF_MOD +
				this.stats.get(EntityTrait.SKILL) + this.equipment.get(CHEST_SLOT).getValue();	
		} else {
			return this.stats.get(EntityTrait.SPEED) * HunterEngine.DEF_MOD; 
		}
		
	}
	@Override
	public int calcDamage() {
		int damage = 0; 
		if (this.equipment.get(WEAPON_SLOT) != null && this.equipment.get(WEAPON_SLOT) instanceof  Weapon) {
			Weapon weapon = (Weapon)this.equipment.get(WEAPON_SLOT);
			damage = weapon.getDamage() + this.stats.get(weapon.getTrait()) +
					this.stats.get(EntityTrait.STRENGTH);
		} else {
			damage = this.stats.get(EntityTrait.STRENGTH);
		}
		return damage;
	}

	
	public void increaseStat(EntityTrait trait){
		if(this.stats.containsKey(trait)){
			int newValue = this.stats.get(trait) + 1;
			this.stats.put(trait, newValue); 
		}
		this.updateCurrentClass();
	}
	
	public void increaseLevel() {
		this.level++; 
	}
	
	public boolean isReadyToLevel(){
		boolean ready = false; 
		int next = (this.level + 1) * (this.level + 1);
		if (this.experience >= next) {
			return true;
		} else {
			return false; 
		}
		
	}
	
	public long amountToLevel() {
		return (this.level + 1) * (this.level + 1); 
	}
	
	public int rollInitiative() {
		Dice die = new Dice(this.stats.get(EntityTrait.SPEED));
		return die.roll(); 
	}

	
	public int skillCheck() {
		Dice die = new Dice(this.stats.get(EntityTrait.SKILL));
		return die.roll(); 
	}
	
	public void equip(GameItem item) {
		if (item instanceof Armor) {
			Armor oldArmor =  (Armor) this.equipment.get(CHEST_SLOT);
			this.equipment.put(CHEST_SLOT, item);
			if (this.inventory.contains(item)) {
				this.inventory.remove(item);
			}
			if (oldArmor != null) {
				this.inventory.add(oldArmor);
				
			}
		} else if (item instanceof Weapon) {
			Weapon oldWeapon =  (Weapon) this.equipment.get(WEAPON_SLOT);
			this.equipment.put(WEAPON_SLOT, item);
			if (this.inventory.contains(item)) {
				this.inventory.remove(item);
			}
			if (oldWeapon != null) {
				this.inventory.add(oldWeapon);
				
			}			
		}
	}
	
	@Override
	public String toString(){
		return this.name + " : Level " + this.level + " HP " + this.hp + " EXP " + this.experience; 
		
	}
	/**
	 * @return the experience
	 */
	public long getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(long experience) {
		this.experience = experience;
	}
	
	
	public void addExperice(long exp) {
		this.experience += exp; 
	}
	/**
	 * @return the currentClass
	 */
	public PlayerClass getCurrentClass() {
		return currentClass;
	}
	/**
	 * @param currentClass the currentClass to set
	 */
	public void setCurrentClass(PlayerClass currentClass) {
		this.currentClass = currentClass;
	}
	
	public void updateCurrentClass() {
		int highestValue= 1; 
		EntityTrait highest;
		this.currentClass = PlayerClass.HUNTER; 
		for(EntityTrait trait:this.getStats().keySet()){
			if (this.getStats().get(trait) > highestValue) {
				highestValue = this.getStats().get(trait);
				highest = trait; 
				this.currentClass = PlayerClass.detrmineClass(trait);
			} else if (this.getStats().get(trait) == highestValue) {
				highestValue = this.getStats().get(trait);
				highest = trait;
				this.currentClass = PlayerClass.HUNTER;
			}
		}
		
	}
	
	
	public String exportInfo() {
		StringBuilder exportCharacter = new StringBuilder();
		exportCharacter.append("Name: " + this.getName() + "\n");
		exportCharacter.append("Class: " + this.getCurrentClass() + "\n");
		exportCharacter.append("Level: " + this.getLevel()+ "\n");
		exportCharacter.append("Experience: " + this.getExperience() + "\n");
		exportCharacter.append("Armor: " + this.equipment.get(CHEST_SLOT)+ "\n");
		exportCharacter.append("Weapon: " + this.equipment.get(WEAPON_SLOT)+ "\n");
		for (EntityTrait trait: this.stats.keySet()){
			exportCharacter.append(trait + ": " + this.stats.get(trait)+ "\n");
		}
		return exportCharacter.toString(); 
	}
}
