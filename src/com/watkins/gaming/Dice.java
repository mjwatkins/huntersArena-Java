/**
 * 
 */
package com.watkins.gaming;

/**
 * Random nuber generator that simulates the behavior of an
 * X side die or set of dice
 * @author mwatkins
 *
 */
public class Dice {

	private int sides;
	private int result; 
	private int number;
	
	/**
	 * Constructs single X sidded die
	 * @param num number of sides
	 */
	public Dice(int num) {
		this.sides = num;
		this.number = 1; 
	}
	
	/**
	 * Rolls one X sided die and returns the result
	 * @return result random integer setul
	 */
	public int roll() {
		result = (int) Math.round(((sides-1) * Math.random() )+ 1);
		return result; 
	}
	
	/**
	 * Rolls a number of X sided dice requestd
	 * @param num  number of dice selected
	 * @return  total of all dice rolled
	 */
	public int rollDice(int num) {
		int sum = 0;
		this.number = num; 
		for (int i=0; i < this.number; i++) {
			sum += this.roll(); 
		}
		return sum; 
	}
}
