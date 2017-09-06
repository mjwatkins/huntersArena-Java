/**
 * 
 */
package com.watkins.huntersarena.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.watkins.gaming.GameEngine;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.action.NewGameAction;

/**
 * @author mwatkins
 *
 */
public class ArenaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6722085711010981265L;
	private static final String GAME_TITLE = "Hunter's Arena (v1.0)"; 
	private InventoryPanel stuffPanel;
	private StatsPanel statPanel; 
	private DisplayPanel mainPanel;
	private DecisionButtonPanel decisionPanel;
	private JMenuBar topBar;
	private JPopupMenu gameMenu; 
	private JPopupMenu aboutMenu;
	private JMenuItem newGameItem;
	private JMenuItem aboutItem;
	private JMenuItem helpItem;
	
	public ArenaFrame() {
		super(GAME_TITLE);
		System.setProperty("apple.laf.useScreenBarMenu","true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Hunter's Arena");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setLayout(new BorderLayout());
		this.setSize(new Dimension(800, 800));
		
		
		//game panels
		this.mainPanel = new DisplayPanel(); 
		this.stuffPanel = new InventoryPanel((GameEngine)HunterEngine.getInstance(), mainPanel);
		this.statPanel = new StatsPanel(HunterEngine.getInstance()); 
		this.decisionPanel = new DecisionButtonPanel(this.mainPanel, this.stuffPanel);
		this.decisionPanel.setVisible(true);
		//menu par
		topBar = new JMenuBar();
		gameMenu = new JPopupMenu("Game"); 
		gameMenu.setVisible(true);
		aboutMenu = new JPopupMenu("About Hunter's Arena");
		newGameItem = new JMenuItem("New Game");
		newGameItem.setAction(new NewGameAction(HunterEngine.getInstance(), mainPanel, getName()));
		//aboutItem = new JMenuItem("About HA");
		//helpItem = new JMenuItem("Arena Help");
		gameMenu.add(newGameItem);
		//aboutMenu.add(aboutItem);
		//aboutMenu.add(helpItem);
		topBar.setVisible(true);
		
		JPanel barHolder = new JPanel();
		this.add(topBar);
		
		this.add(barHolder,BorderLayout.NORTH); 
		this.add(decisionPanel, BorderLayout.SOUTH); 
		this.add(stuffPanel, BorderLayout.WEST); 
		this.add(statPanel, BorderLayout.EAST); 
		this.add(mainPanel, BorderLayout.CENTER); 
	}
	
	public void refreshAll() {
		this.mainPanel.refreshPanel();
		this.stuffPanel.refreshEquipment();
		this.stuffPanel.refreshInventory();
		this.statPanel.refreshPanel();
	}

	/**
	 * @return the stuffPanel
	 */
	public InventoryPanel getStuffPanel() {
		return stuffPanel;
	}

	/**
	 * @return the statPanel
	 */
	public StatsPanel getStatPanel() {
		return statPanel;
	}

	/**
	 * @return the mainPanel
	 */
	public DisplayPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * @return the decisionPanel
	 */
	public DecisionButtonPanel getDecisionPanel() {
		return decisionPanel;
	}
	
	
}
