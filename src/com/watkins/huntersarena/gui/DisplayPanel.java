/**
 * 
 */
package com.watkins.huntersarena.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.watkins.huntersarena.engine.HunterEngine;

/**
 * @author mwatkins
 *
 */
public class DisplayPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2595777085528046262L;
	private JTextArea outputArea;
	private JScrollPane pane; 
	private Canvas drawArea; 
	private JLabel promptLabel;
	
	public DisplayPanel() {
		this.outputArea = new JTextArea();
		this.setLayout(new BorderLayout());
		this.add(this.outputArea, BorderLayout.CENTER);
		outputArea.setText("Welcome to Hunter's arena");
		this.promptLabel = new JLabel(); 
		this.add(promptLabel,BorderLayout.SOUTH);
	}
	
	public void setMessage(String content){
		this.outputArea.setText(content);
	}
	
	public void appendMessage(String content) {
		this.outputArea.append(content);
	}
	
	public void setPrompt(String content) {
		this.promptLabel.setText(content);
	}
	
	public void refreshPanel() {
		this.setPrompt(HunterEngine.getInstance().getMainCharacter().toString());
	}
}
