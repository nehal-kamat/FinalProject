package ml;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextField;

/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class colorFeatureExtractor extends JPanel implements ActionListener {
	static private final String newline = "\n";
	JButton openTrainingFolderButton, openTestFolderButton, extractTrainingFeatures;
	JTextArea log;
	JFileChooser fc;
	File trainData = new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/input/trainingImageResized"),testData =null,inputFileList=null,extractedFeatures=null;
	String[] comboValues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };	
	JComboBox comboBox = new JComboBox(comboValues);
	JComboBox comboBox_1 = new JComboBox(comboValues);
	JComboBox comboBox_2 = new JComboBox(comboValues);
	JComboBox comboBox_3 = new JComboBox(comboValues);
	private JTextField textField;

	public colorFeatureExtractor() {

		
		
		
		// Create a file chooser
		//user.dir is to get defualt directory
		fc =  new JFileChooser(new File(System.getProperty("user.dir")));
		
		// Uncomment one of the following lines to try a different
		// file selection mode. The first allows just directories
		// to be selected (and, at least in the Java look and feel,
		// shown). The second allows both files and directories
		// to be selected. If you leave these lines commented out,
		// then the default mode (FILES_ONLY) will be used.
		//
		 fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		 openTestFolderButton = new JButton("Open test folder...");
		 openTestFolderButton.setBounds(308, 12, 201, 51);
		 openTestFolderButton.addActionListener(this);
		  
		  extractTrainingFeatures = new JButton("Run on Training Data");
		  extractTrainingFeatures.setBounds(28, 108, 238, 47);
		  extractTrainingFeatures.addActionListener(this);
		setLayout(null);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 12, 801, 232);
		buttonPanel.setLayout(null);
		buttonPanel.add(openTestFolderButton);
		buttonPanel.add(extractTrainingFeatures);


		// Add the buttons and the log to this panel.
		add(buttonPanel);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Create the open button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		openTrainingFolderButton = new JButton("Open training folder...");
		openTrainingFolderButton.setBounds(28, 12, 238, 51);
		buttonPanel.add(openTrainingFolderButton);
		
		
		comboBox.setBounds(611, 35, 78, 30);
		comboBox.setSelectedIndex(9);
		buttonPanel.add(comboBox);
		
		JLabel lblNnewLabel = new JLabel("N");
		lblNnewLabel.setBounds(568, 38, 41, 25);
		buttonPanel.add(lblNnewLabel);
		
		JLabel lblCh = new JLabel("C_h");
		lblCh.setBounds(568, 87, 48, 25);
		buttonPanel.add(lblCh);
		

		comboBox_1.setBounds(628, 84, 78, 30);
		comboBox_1.setSelectedIndex(7);
		buttonPanel.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Run on Test Data");
		btnNewButton.setBounds(308, 108, 201, 47);
		buttonPanel.add(btnNewButton);
		
		comboBox_2.setBounds(628, 135, 78, 30);
		comboBox_2.setSelectedIndex(5);
		buttonPanel.add(comboBox_2);
		
		JLabel lblCs = new JLabel("C_s");
		lblCs.setBounds(568, 143, 48, 25);
		buttonPanel.add(lblCs);
		
		JLabel lblCv = new JLabel("C_v");
		lblCv.setBounds(568, 185, 48, 25);
		buttonPanel.add(lblCv);
		
		comboBox_3.setBounds(628, 177, 78, 30);
		comboBox_3.setSelectedIndex(5);
		buttonPanel.add(comboBox_3);
		
		JLabel lblNewLabel = new JLabel("Test Number :");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(28, 185, 178, 35);
		buttonPanel.add(lblNewLabel);
		
		textField = new JTextField("Default");
		textField.setBounds(204, 190, 114, 29);
		buttonPanel.add(textField);
		textField.setColumns(10);
		openTrainingFolderButton.addActionListener(this);
		
				// Create the log first, because the action listeners
				// need to refer to it.
				log = new JTextArea(5, 20);
				log.setBounds(12, 244, 801, 200);
				add(log);
				log.setMargin(new Insets(5, 5, 5, 5));
				log.setEditable(false);				
	}

	public void actionPerformed(ActionEvent e) 
	{
		// Handle open button action.
		if (e.getSource() == openTrainingFolderButton) {
			int returnVal = fc.showOpenDialog(colorFeatureExtractor.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				trainData = fc.getSelectedFile();
				// This is where a real application would open the file.
				log.append("Folder selected for training : " + trainData.toString() + "." + newline);				
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
		                log.append("Folder seelcted for testing : " + testData.getName() + "." + newline);
		          
		            } else {
		                log.append("Open command cancelled by user." + newline);
		            }
		            log.setCaretPosition(log.getDocument().getLength());
		}
		}
	
		else if (e.getSource() == extractTrainingFeatures) 
		{
			try
			{
				//int N=10,C_h=8,C_s=6,C_v=6;
				int N = Integer.parseInt(comboBox.getSelectedItem().toString());
				int C_h = Integer.parseInt(comboBox_1.getSelectedItem().toString());
				int C_s = Integer.parseInt(comboBox_2.getSelectedItem().toString());
				int C_v = Integer.parseInt(comboBox_3.getSelectedItem().toString());
				
				String testNumber = textField.getText();
				String testDirectory = "results/";
				inputFileList = new File(testDirectory+"colorFeatureExtractor"+testNumber+".list");
				extractedFeatures = new File(testDirectory+"colorFeatures"+testNumber+".train");
				
				//generate log file
				FileWriter F = new FileWriter(new File(testDirectory+"colorFeatureExtractor"+testNumber+".log"));
				StringBuffer logData = new StringBuffer();
				
				logData.append("Training Data : "+trainData.getName()+"\n");
				logData.append("N : "+Integer.toString(N)+"\n");
				logData.append("C_h : "+Integer.toString(C_h)+"\n");
				logData.append("C_s : "+Integer.toString(C_s)+"\n");
				logData.append("C_v : "+Integer.toString(C_v)+"\n");
				
				Date start =new Date();
				
				logData.append("Start time : "+start.toString()+"\n");
						
				ML.batchColorFeatureBuilder(trainData.toString(), N, C_h, C_s, C_v,extractedFeatures,inputFileList);
				
				Date end = new Date();
				logData.append("End time : "+end.toString()+"\n");
				
				
				long executionTimeMS = end.getTime()-start.getTime(); //in milliseconds
				String executionTime = Float.toString((float)executionTimeMS/(60*1000))+" minutes";
				
				logData.append("Execution Time : "+executionTime+"\n");
				
				log.append(logData.toString());
				F.write(logData.toString());
				F.flush();
				F.close();
			}
			catch(IOException k)
			{
				
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

		frame.setMinimumSize(new Dimension(800,600));
		
		// Add content to the window.
		frame.getContentPane().add(new colorFeatureExtractor());

		// Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null);     
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
