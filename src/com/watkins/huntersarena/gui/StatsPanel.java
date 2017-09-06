/**
 * 
 */
package com.watkins.huntersarena.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.watkins.gaming.EntityTrait;
import com.watkins.gaming.GameEngine;
import com.watkins.huntersarena.gui.action.LevelUpAction;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class StatsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -286112821936749098L;
	private JButton levelUpButton;
	private JTextArea statDisplay; 
	private GameEngine engine;
	private DisplayPanel panel; 
	private final String STAT_TITLE = "Character Statistics:  \n";
	
	public StatsPanel(GameEngine engine) {
		
		this.setLayout(new GridLayout(4, 1));
		
		this.engine = engine; 
		
		this.statDisplay = new JTextArea(STAT_TITLE);
		this.statDisplay.setEnabled(false);
		this.add(statDisplay);
		
		this.levelUpButton = new JButton("Level Up");
		this.levelUpButton.setAction(new LevelUpAction(engine, this));
		this.levelUpButton.setText("Level Up");
		this.levelUpButton.setEnabled(true);
		this.levelUpButton.setOpaque(true);
		this.levelUpButton.setBackground(Color.ORANGE);
		this.add(levelUpButton); 
		
	}
	
	public void refreshPanel() {
		StringBuilder statOutput = new StringBuilder(); 
		PlayerCharacter main = (PlayerCharacter) engine.getMainCharacter(); 
		
		statOutput.append(STAT_TITLE);
		statOutput.append(engine.getMainCharacter().getName() + "\n");
		statOutput.append("Class: " + ((PlayerCharacter)engine.getMainCharacter()).getCurrentClass() + "\n");
		for (EntityTrait trait: EntityTrait.values() ) {
			int value = main.getStats().get(trait);
			statOutput.append(trait.toString() + ":\t " + value + "\n");
		}
		statOutput.append("\n For next level: " +  main.amountToLevel() + "\n"); 
		this.statDisplay.setText(statOutput.toString());
	
	}
}
