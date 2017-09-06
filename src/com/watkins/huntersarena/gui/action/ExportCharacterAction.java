/**
 * 
 */
package com.watkins.huntersarena.gui.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.huntersarena.main.HuntersArena;
import com.watkins.gaming.GameAction;
import com.watkins.gaming.States;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * Allows user to begin exporting a file record of their exploits
 * @author mwatkins
 *
 */
public class ExportCharacterAction implements GameAction {

	private File saveFile = null;
	private PlayerCharacter main;
	private int round;
	private States endState;
	private boolean exportNeeded = false; 
	
	
	
	/**
	 * @return the exportNeeded
	 */
	public boolean isExportNeeded() {
		return exportNeeded;
	}

	/**
	 * @param saveFile
	 * @param main
	 * @param round
	 * @param endState
	 */
	public ExportCharacterAction() {

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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#doAction()
	 */
	@Override
	public void doAction() {
		int outcome = JOptionPane.showConfirmDialog(HuntersArena.getMainGui(), "Do you want to a log of your exploits");
		if(outcome == JOptionPane.NO_OPTION) {
			return; 
		}
		JFileChooser filePick = new JFileChooser();
		filePick.setFileFilter(new FileNameExtensionFilter("Text files","txt"));
		int successValue = filePick.showSaveDialog(HuntersArena.getMainGui()); 
		if (successValue == JFileChooser.APPROVE_OPTION){
			this.exportNeeded = true; 
		}
		this.saveFile = filePick.getSelectedFile(); 

	}

	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameAction#updateInterface()
	 */
	@Override
	public void updateInterface() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the filename
	 */
	public File getSaveFile() {
		return saveFile;
	}

}
