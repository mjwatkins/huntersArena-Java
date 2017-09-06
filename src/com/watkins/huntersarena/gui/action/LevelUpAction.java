/**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import com.watkins.gaming.EntityTrait;
import com.watkins.gaming.GameAction;
import com.watkins.gaming.GameEngine;
import com.watkins.gaming.States;
import com.watkins.huntersarena.command.IncreaseLevelCommand;
import com.watkins.huntersarena.engine.HunterEngine;
import com.watkins.huntersarena.gui.StatsPanel;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class LevelUpAction implements GameAction {
	
	private GameEngine engine;
	private StatsPanel panel;
	private EntityTrait	trait; 

	public LevelUpAction(GameEngine engine, StatsPanel panel) {
		this.engine = engine;
		this.panel = panel;
		
	}
	/* (non-Javadoc)
	 * @see javax.swing.Action#getValue(java.lang.String)
	 */
	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#putValue(java.lang.String, java.lang.Object)
	 */
	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.Action#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.doAction();

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		
		if(engine.getState() == States.BATTLE) {
			JOptionPane.showMessageDialog(panel, "You can't focus enough to direct your learning");
			return;
		}
		PlayerCharacter main = (PlayerCharacter)engine.getMainCharacter();
		EntityTrait[] allTraits = {EntityTrait.STRENGTH,EntityTrait.SPEED, 
				EntityTrait.CONSTITUTION, EntityTrait.SKILL}; 
		
		if (main.isReadyToLevel()){
			int result = JOptionPane.showOptionDialog(panel, "Select level up bonus",
					"Level Up", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, allTraits, EntityTrait.STRENGTH); 
			IncreaseLevelCommand cmd = new IncreaseLevelCommand((HunterEngine) engine, allTraits[result]);
			cmd.run();
			
		} else {
			JOptionPane.showMessageDialog(panel, "You don't have enough exp");
		}
		this.updateInterface();
	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		this.panel.refreshPanel();

	}

}
