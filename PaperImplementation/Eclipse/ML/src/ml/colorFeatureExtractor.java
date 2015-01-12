package ml;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//package components;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class colorFeatureExtractor extends JPanel implements ActionListener {
	static private final String newline = "\n";
	JButton openTrainingFolderButton, saveInputListButton, openTestFolderButton, saveButton;
	JTextArea log;
	JFileChooser fc;

	public colorFeatureExtractor() {
		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create a file chooser
		fc = new JFileChooser();

		// Uncomment one of the following lines to try a different
		// file selection mode. The first allows just directories
		// to be selected (and, at least in the Java look and feel,
		// shown). The second allows both files and directories
		// to be selected. If you leave these lines commented out,
		// then the default mode (FILES_ONLY) will be used.
		//
		 fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Create the open button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		openTrainingFolderButton = new JButton("Open a File...",
				createImageIcon("images/Open16.gif"));
		openTrainingFolderButton.addActionListener(this);
		
		 openTestFolderButton = new JButton("Open a File...",
                 createImageIcon("images/Open16.gif"));
		 openTestFolderButton.addActionListener(this);

		// Create the save button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		saveInputListButton = new JButton("Save a File...",
				createImageIcon("images/Save16.gif"));
		saveInputListButton.addActionListener(this);
		
		  saveButton = new JButton("Save a File...",
                  createImageIcon("images/Save16.gif"));
		  saveButton.addActionListener(this);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(openTrainingFolderButton);
		buttonPanel.add(openTestFolderButton);
		buttonPanel.add(saveInputListButton);
		buttonPanel.add(saveButton);

		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) 
	{

		File trainData,testData,inputFileList,extractedFeatures;
		// Handle open button action.
		if (e.getSource() == openTrainingFolderButton) {
			int returnVal = fc.showOpenDialog(colorFeatureExtractor.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				trainData = fc.getSelectedFile();
				// This is where a real application would open the file.
				log.append("Opening: " + trainData.getName() + "." + newline);
				
				/*try
				{
					ML.batchColorFeatureBuilder(file.toString(), 10, 8, 6, 6,new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/input/continuousTest2.train"),new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/output/filesInputTest2.txt"));
				}
				catch(IOException k)
				{
					
				}
				log.append("fdd\n");*/
			} else {
				log.append("Open command cancelled by user." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());

			
			
		} 
		
		else if(e.getSource()==openTestFolderButton)
		{
			// Handle open button action.
			 if (e.getSource() == openTestFolderButton) {
		            int returnVal = fc.showOpenDialog(colorFeatureExtractor.this);

		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		                testData = fc.getSelectedFile();
		                //This is where a real application would open the file.
		                log.append("Opening: " + testData.getName() + "." + newline);
		          
		            } else {
		                log.append("Open command cancelled by user." + newline);
		            }
		            log.setCaretPosition(log.getDocument().getLength());
		}
		}
		
		// Handle save button action.
		
		else if (e.getSource() == saveInputListButton) {
			int returnVal = fc.showSaveDialog(colorFeatureExtractor.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				inputFileList = fc.getSelectedFile();
				// This is where a real application would save the file.
				log.append("File selected for storing input file list : " + inputFileList.getName() + "." + newline);
			} else {
				log.append("Save command cancelled by user." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
		
		else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(colorFeatureExtractor.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                extractedFeatures = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("File selected for storing extracted features : " + extractedFeatures.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = colorFeatureExtractor.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("ColorFeaturesExtractor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new colorFeatureExtractor());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
