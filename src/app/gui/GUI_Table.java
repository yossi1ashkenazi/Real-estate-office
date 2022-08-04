package app.gui;
import java.awt.Dialog;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;

import app.entity.apartment.Apartment;
import app.entity.apartment.RentApartment;
import app.entity.apartment.SellApartment;

/**
 * class that represent a table that contain all of the apartments data 
 */
public class GUI_Table {
		/**
		 * @param JFrame - GUI frame
		 */
	 	private JFrame frame = new JFrame(); ;
	 	
	 	/**
	 	 * @param JTable - Data table designed for use with GUI 
	 	 */
	 	private JTable table;
	 	/**
	 	 * @param Dialog - Top level window that can contain a JTable
	 	 */
	 	private Dialog dialog;
	 	
	 	/**
	 	 * @param ArrayList<Apartment> - list of apartment from the manager class 
	 	 */
	    public GUI_Table(ArrayList<Apartment> _apartments){ 
	    	
	    	// Set the columns of the table
			String[] columns = {
					"Type",
					"ID",
					"Address",
					"Square Meter",
					"Number Of Rooms",
					"Client Name",
					"Price",
					"Offered Price",
					"Entry Date",
					"Rental Start Date",
					"Rental End Date",
				};
			
			// Set the table's values
			String[][] data = new String[_apartments.size()+1][columns.length];
			
			// Set columns inside the table
			data[0] = columns;
			
			// Set apartments data in the table
			for (int i = 0, j = 1; i < _apartments.size(); i++,j++) {
				
				data[j] = _apartments.get(i).getAllParam().split(",");
			}			
		
			// Set the table and adjust cells 	
			table=new JTable(data,columns); 
			table.setRowHeight(30);
			setJTableColumnsWidth(table,1200,40,10,95,30,40,30,30,30,30,35,35);
			
			// Scroll pane is a scroll bar to contain the table and used inside the frame
			JScrollPane sp=new JScrollPane(table);;
			frame.add(sp);    
	    
			// Create a new dialog GUI with the frame and the table we just created.
	    	MyDialog md = new MyDialog(frame,table);
	    }
		
	    /**
	     * Method that adjust the column of the table
	     * 
	     * @param JTable table - The table we want to adjust 
	     * @param int tablePreferredWidth - The size of the Preferred Width
	     * @param double percentages - percentages of the Preferred Width  
	     */
	    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
	            double... percentages) {
	        double total = 0;
	        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	            total += percentages[i];
	        }
	     
	        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	            TableColumn column = table.getColumnModel().getColumn(i);
	            column.setPreferredWidth((int)
	                    (tablePreferredWidth * (percentages[i] / total)));
	        }
	    }
	    
	    
}
