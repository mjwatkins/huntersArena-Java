/**
 * 
 */
package com.watkins.huntersarena.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ComponentInputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.action.AttackAction;
import com.watkins.huntersarena.gui.action.FleeAction;
import com.watkins.huntersarena.gui.action.NextRoundAction;
import com.watkins.huntersarena.gui.action.StartFightAction;

/**
 * @author mwatkins
 *
 */
public class DecisionButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7719500218093700516L;
	private JButton nextFightButton;
	private JButton nextRoundButton;
	private JButton fleeButton;
	private JButton attackButton;
	private DisplayPanel panel; 
	
	public DecisionButtonPanel(DisplayPanel panel, InventoryPanel iPanel){
		ComponentInputMap iMap = new ComponentInputMap(this); 
		iMap.put(KeyStroke.getKeyStroke("f"), 
				new StartFightAction(HunterEngine.getInstance(),panel));
		this.setLayout(new GridLayout(1,4));
		this.panel = panel;
		HunterEngine engine = HunterEngine.getInstance();

		this.nextFightButton = new JButton("Fight New Enemy");
		this.nextFightButton.setAction(new StartFightAction(engine,
				panel));
		this.add(nextFightButton);
		this.nextFightButton.setEnabled(true);
		this.nextFightButton.setText("Fight New Enemy");
		this.nextFightButton.setOpaque(true);
		this.nextFightButton.setBackground(Color.GREEN);
		this.nextFightButton.setVisible(true);
		this.setInputMap(WHEN_IN_FOCUSED_WINDOW, iMap);
		
		this.nextRoundButton = new JButton("Next Round");
		this.nextRoundButton.setAction(new NextRoundAction(engine, panel));
		this.nextRoundButton.setEnabled(true);
		this.nextRoundButton.setText("Next Round");
		this.nextRoundButton.setOpaque(true);
		this.nextRoundButton.setBackground(Color.ORANGE);		
		this.add(nextRoundButton);
		
		this.attackButton = new JButton("Attack");
		this.attackButton.setAction(new AttackAction(engine, panel, iPanel));
		this.attackButton.setEnabled(true);
		this.attackButton.setText("Attack");
		this.attackButton.setOpaque(true);
		this.attackButton.setBackground(Color.GREEN);		
		this.add(attackButton);
		
		this.fleeButton = new JButton("Flee");
		this.fleeButton.setAction(new FleeAction(panel, engine));
		this.fleeButton.setEnabled(true);
		this.fleeButton.setText("Flee");
		this.fleeButton.setOpaque(true);
		this.fleeButton.setBackground(Color.YELLOW);
		this.add(fleeButton); 
		
	}
}
