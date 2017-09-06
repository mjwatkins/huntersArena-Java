/**
 * 
 */
package com.watkins.huntersarena.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import com.watkins.gaming.GameEngine;
import com.watkins.gaming.GameItem;
import com.watkins.huntersarena.gui.action.DropItemAction;
import com.watkins.huntersarena.gui.action.EquipItemAction;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class InventoryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7814347252032718379L;
	private JLabel[] equipmentDisplay;
	private JList<GameItem> inventoryDisplay;
	private JViewport port;
	private JButton dropButton;
	private JButton equipButton; 
	private GameEngine engine; 
	private final String TITLE = "Worn Equipment\n";
	public final String WEAPON_SLOT = "Weapon: ";
	public final String ARMOR_SLOT = "Armor:"; 
	
	public InventoryPanel(GameEngine engine, DisplayPanel panel) {
		this.engine = engine; 
		this.setLayout(new GridLayout(3,1));
		this.equipmentDisplay = new JLabel[3]; 
		this.equipmentDisplay[0] = new JLabel(TITLE);
		this.equipmentDisplay[0].setOpaque(true);
		this.equipmentDisplay[0].setBackground(Color.LIGHT_GRAY);
		this.equipmentDisplay[1] =  new JLabel(WEAPON_SLOT);
		this.equipmentDisplay[2] =  new JLabel(ARMOR_SLOT); 
				//new JTextArea(TITLE + WEAPON_SLOT + "\t\n" + ARMOR_SLOT + "\t\n");
		//this.equipmentDisplay.setEnabled(false);
		JPanel displayHolderPanel = new JPanel();
		displayHolderPanel.setLayout(new GridLayout(3,1));
		displayHolderPanel.add(equipmentDisplay[0]); 
		displayHolderPanel.add(equipmentDisplay[1]); 
		displayHolderPanel.add(equipmentDisplay[2]); 
		this.add(displayHolderPanel);
		
		
		this.port = new JViewport();
		this.port.setAutoscrolls(true);
		
		this.inventoryDisplay = new JList<GameItem>(); 
		this.inventoryDisplay.setName("Inventory");
		this.inventoryDisplay.setToolTipText("Inventory Items");
		this.port.setPreferredSize(new Dimension(250, 500));
		this.setPreferredSize(new Dimension(300,600));
		
		JScrollPane pane = new JScrollPane(port);
		port.add(this.inventoryDisplay);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(pane);

		JPanel buttonHolderPanel = new JPanel();
		buttonHolderPanel.setLayout(new GridLayout(4,1));
		this.equipButton = new JButton("Equip Item");
		this.equipButton.setAction(new EquipItemAction(engine,panel , this));
		this.equipButton.setText("Equip Item");
		this.equipButton.setEnabled(true);
		this.equipButton.setOpaque(true);
		this.equipButton.setBackground(Color.GREEN);
		buttonHolderPanel.add(this.equipButton);
		this.dropButton = new JButton("Drop Item");
		this.dropButton.setAction(new DropItemAction(engine, panel, this));	
		this.dropButton.setText("Drop Item");	
		this.dropButton.setEnabled(true);
		this.dropButton.setOpaque(true);
		this.dropButton.setBackground(Color.YELLOW);
		buttonHolderPanel.add(this.dropButton); 
		this.add(buttonHolderPanel);

	}
	
	public GameItem getSelectedItem() {
		return this.inventoryDisplay.getSelectedValue();
	}
	
	public String refreshEquipment() {
		StringBuilder currentEquipment = new StringBuilder(WEAPON_SLOT);
		currentEquipment.append(engine.getMainCharacter().getEquipment().get(PlayerCharacter.WEAPON_SLOT).getDescription() );
		this.equipmentDisplay[1].setText(currentEquipment.toString());
		currentEquipment = new StringBuilder(); 
		currentEquipment.append(ARMOR_SLOT);
		currentEquipment.append(engine.getMainCharacter().getEquipment().get(PlayerCharacter.CHEST_SLOT).getDescription() );		
		this.equipmentDisplay[2].setText(currentEquipment.toString());
		
		return currentEquipment.toString(); 
	}
	
	public void refreshInventory() {
		
		this.inventoryDisplay.removeAll();
		Object[] inventory = this.engine.getMainCharacter().getInventory().toArray(); 
		GameItem[] allItems = new GameItem[inventory.length];
		for (int i=0; i < inventory.length; i++){
			allItems[i]= (GameItem) inventory[i];
		}
		this.inventoryDisplay.setListData(allItems);
		
	}
	
}
