package app.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.entity.apartment.ApartmentManager;

/**
 * class that operate the GUI and return a clean input 
 */
public class GUI_Operator {

	/**
	 * @param Jpanel - The panel of the GUI responsible of its borders and background
	 */
	private JPanel panel = new JPanel(new BorderLayout(5, 5));	  
	
	/**
	 * @param Jpanel - Label the text that will be displayed to the user before the text boxes
	 */
	private JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	
	/**
	 * @param Jpanel - The text fields that will contain the user input
	 */
	private JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	
	/**
	 * @param HashMap - Catalogs and contains the text field variables according to the labels
	 */
	private Map<String, JTextField> textFieldsInputMap = new HashMap<>();
	
	/**
	 * @param HashMap - Catalogs and contains the input by labels
	 */
	private Map<String, String> inputExtractor = new HashMap<>();

	/**\
	 * @param String - LABEL_TEXTS - the labels that will show in the GUI near to the text field 
	 * @param String - GUI_Text - the text that will show on the top of the window of the GUI
	 */
	public GUI_Operator(String[] LABEL_TEXTS, String GUI_Text) {
		
		// Create the GUI fields
		for (int i = 0; i < LABEL_TEXTS.length; i++) { 
			
	    	label.add(new JLabel(LABEL_TEXTS[i], SwingConstants.LEFT));
	    	textFieldsInputMap.put(LABEL_TEXTS[i], new JTextField());
	    	controls.add(this.textFieldsInputMap.get(LABEL_TEXTS[i]));
	    }
		
		 // Add boarder for the labels and 
		panel.add(label, BorderLayout.WEST);
		
		// Add boarder for the text fields
	    panel.add(controls, BorderLayout.CENTER);
	    
	    try {
	    	
	    	JOptionPane.showConfirmDialog(
	    			null, 
	    			panel, 
	    			GUI_Text, 
	    			JOptionPane.OK_CANCEL_OPTION, 
	    			JOptionPane.PLAIN_MESSAGE
	    			);
			
	    	
		    // Extract and catalog the input by labels
		    for (int i = 0; i < LABEL_TEXTS.length; i++) {
		    	
				inputExtractor.put(
						LABEL_TEXTS[i], 
						textFieldsInputMap.get( LABEL_TEXTS[i] ).getText().toString().trim() 
						);
			}
		    
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The action failed, please fill in all the fields before sending the create file");
		}
	    
	}
	
	/**
	 * @param String - LABEL_TEXT - the input that the user want to get from the extractor
	 * @return String - The wanted input from the HashMap on the input extractor
	 */
	public String extractor(String LABEL_TEXT) {
		return this.inputExtractor.get(LABEL_TEXT);
	}
	
	/**
	 * @return String - create a GUI with a selection Box and return the user Selection
	 * 
	 * @param  String[] - the options that will be represent in the GUI
	 * @param  String GUI_Text - the labels that will be represent the choices 
	 * @param  String description - the text that will show on the top of the window of the GUI  
	 */
	public static String getUserSelection( String[] options, String GUI_Text, String description ) {
		
		// Frame for the GUI
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		frame.setSize(420,420);
		
		// This is the menu, here the user will be able to choose which of the main actions of the program he wants to do
		String userSelection = (String)JOptionPane.showInputDialog(
				frame, 
				GUI_Text,
				description,
				0, 
				ApartmentManager._icon,
				options,
				options[0]
			);
		
		return userSelection.trim();	
	}
	
	public static String getUserTextInput(String GUI_Text, String description) {
		// Frame for the GUI
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		frame.setSize(420,420);
				
		// This is the menu, here the user will be able to choose which of the main actions of the program he wants to do
		String userSelection = (String)JOptionPane.showInputDialog(
				frame, 
				GUI_Text,
				description,
				0, 
				ApartmentManager._icon,
				null,
				null
					);
				
				return userSelection.trim();
	}
	
	/**
	 * Show the user a GUI massage with selected string
	 * @param String massage - The massage we want to show the user
	 */
	public static void showGUI_Massage(String massage) {
		JOptionPane.showMessageDialog(null, massage);
	}
}
