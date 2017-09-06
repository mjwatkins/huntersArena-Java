/**
 * 
 */
package com.watkins.huntersarena.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.watkins.gaming.GameCommand;
import com.watkins.gaming.States;
import com.watkins.huntersarena.model.PlayerCharacter;

/**
 * @author mwatkins
 *
 */
public class ExportCharacterCommand implements GameCommand {

	private PlayerCharacter main;
	private int round;
	private States endState; 
	private File saveFile; 
	private final String VICTORY_DESCRIPTION = "This is record to the victorious hunter! \n";
	private final String DEFEAT_DESCRIPTION = "Here lies the defeated hunter. \n"; 
	
	public ExportCharacterCommand(PlayerCharacter main, int round, States endState,File saveFile) {
		this.main = main;
		this.round = round; 
		this.endState = endState;
		this.saveFile = saveFile; 
		
	}
	
	/* (non-Javadoc)
	 * @see com.watkins.gaming.GameCommand#run()
	 */
	@Override
	public void run() {

			
			BufferedWriter writer=null;
			try {
				writer = new BufferedWriter(new FileWriter(saveFile));
				if (endState == States.VICTORY) {
					writer.append(VICTORY_DESCRIPTION);
				} else {
					writer.append(DEFEAT_DESCRIPTION);
				}
				writer.append("Round : " + round + "\n"); 
				writer.append(main.exportInfo());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
			}
		}
	}


