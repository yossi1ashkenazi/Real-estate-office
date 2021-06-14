package app.entity.apartment;

import javax.swing.*;
import app.entity.apartment.sort.ISortable;
import app.entity.apartment.sort.IdComparator;
import app.entity.apartment.sort.NameComparator;
import app.entity.apartment.sort.PriceComparator;
import app.gui.GUI_Operator;
import app.gui.GUI_Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represent the system that manage the apartments
 */
public class ApartmentManager {
		
	/**
	 * @param ArrayList<Apartment> - all the apartments that made by the user s
	 */
	private ArrayList<Apartment> _apartments = new ArrayList<Apartment>();
	
	/**
	 * @param ImageIcon - The icon for the GUI
	 */
	public static ImageIcon _icon = new ImageIcon("src\\app\\icons\\_icon.jpg");
	
	/**
	 * @param boolean - 
	 */
	public boolean _stopRunningMunu = false;
	
	/**
	 * @param long - Counter for Entoty's ID, created to track and control its value
	 */
	public static long _counterID = 0;

	public ApartmentManager() {
		// Create some sample apartments
			_counterID++;
			this._apartments.add(new SellApartment("Yehuda Halevi 22, Tel-Aviv", _counterID, 76, 3, "Yossi Cohen", 2000000, 1500000, "12-2-2021"));
			_counterID++;
			this._apartments.add(new SellApartment("Bugrashov 13, Tel-Aviv", _counterID, 106, 4, "Yossi Cohen", 3000000, 1900000, "22-4-2021"));
			_counterID++;
			this._apartments.add(new SellApartment("Hagadna 6, Rishon Letzion", _counterID, 70, 3, "Dudi Hertz", 1200000, 1000000, "21-1-2021"));
			_counterID++;
			this._apartments.add(new RentApartment("Rotshild 17, Tel-Aviv", _counterID, 50, 2, "Miri Shavtay", 4000, "20-2-2020", "20-2-2021"));
			_counterID++;
			this._apartments.add(new RentApartment("Hayarkon 6, Tel-Aviv", _counterID, 90, 3, "Eli Kopter", 8000, "15-1-2020", "15-1-2021"));
		
	}
	
	/**
	 * Runs the menu
	 */
	public void runManager() {
		
		// String array for the labels
		String[] options = {
				"Add new apartment",
				"Find apartment",
		        "Show all apartments", 
		        "Sort apartments",
		        "Exit"
	        };
		
		try {
			
			while(!_stopRunningMunu) {
				
				String userSelection = GUI_Operator.getUserSelection(
						options, 
						"Please select the action you want to do", 
						"Menu"
					);
				
				// Switch case that represents every option on the menu
				switch ( userSelection ) {
					case "Add new apartment":
						this.addApartment();
						break;
					
					case "Find apartment":
						this.findApartment();
						break;
					case "Show all apartments":
						this.showAllApartments();		
						break;
						
					case "Sort apartments":
						this.sortOptions();		
						break;
						
					case "Exit":
						// If the user choose Exit then we will get out from the loop
						this._stopRunningMunu = true;
						GUI_Operator.showGUI_Massage("Thank you and goodbye");
						break;
	
					default:
						break;
				}
			}
		} 
		catch (Exception e) {
			GUI_Operator.showGUI_Massage("Thank you and goodbye");
		}
	}
	 
	/**
	 * Add new apartment to the list
	 */
	public void addApartment(){
		try {

			 String address;
			 double squareMeter;
			 int numberOfRooms;
			 String clientName; 
			 double price; 
			 double offeredPrice; 
			 String entryDate; 
			 String rentalStartDate;
			 String rentalEndDate;

			// String array for the labels
			String[] options = {"Apartment For Sall", 
								"Apartment For Rent"};
			
			String userSelection = GUI_Operator.getUserSelection(
					options, 
					"Please select the type of apartment you would like to add to the apartment database", 
					"Add new apartment"
				);
			
			//
			GUI_Operator GUI;
			
			// Switch case for the input that returned from the user selection
			switch ( userSelection) {
				case "Apartment For Rent":
					
					// String array for the labels
					String[] LABEL_TEXTS_RENT = {
							 "Address",
					         "Square Meter", 
					         "Number Of Rooms", 
					         "Client Name", 
					         "Price",
					         "Start Date (dd-mm-yyyy)", 
					         "End Date (dd-mm-yyyy)" 
				         };
					
					// Create a new GUI with the text labels we created
					GUI = new GUI_Operator(LABEL_TEXTS_RENT, "Create a new rent apartment");
					
				    // Take input from the GUI
				    address = GUI.extractor("Address");
				    
				    squareMeter = roundedDouble(
				    			Double.parseDouble(
				    					GUI.extractor("Square Meter")
				    					)
				    			);
				    
				    numberOfRooms = Integer.parseInt(
				    		GUI.extractor("Number Of Rooms")
				    		);
				    
				    clientName = GUI.extractor("Client Name");
				    
				    price = roundedDouble(
				    			Double.parseDouble(
				    					GUI.extractor("Price")
				    					)
				    			);
				    
				    rentalStartDate = GUI.extractor("Start Date (dd-mm-yyyy)");
				    
				    rentalEndDate = GUI.extractor("End Date (dd-mm-yyyy)");
				    				    
				    // Check if the id already exist in the list and for invalid input
				    if ( this.isExist(_counterID + 1) ) {
				    	GUI_Operator.showGUI_Massage("The action failed, the database is allready contain an apartment with similar ID, please try again");
				    	_counterID++;
					} 
				    
				    // Check for invalid input
				    else if( !RentApartment.isValidFields( 
				    		squareMeter, 
				    		numberOfRooms, 
				    		price, 
				    		address, 
				    		clientName,
				    		rentalStartDate, 
				    		rentalEndDate
				    		
				    		) 				    		
				    	)
				    {
				    	GUI_Operator.showGUI_Massage(
				    			RentApartment.validationError(
				    					squareMeter, 
				    					numberOfRooms, 
				    					price, address, 
				    					clientName, 
				    					rentalStartDate, 
				    					rentalEndDate
				    				)
				    			);
				    }
		
				    else 
				    { 
						// Advanced the counter and create a new apartment in the list
						_counterID++;
						this._apartments.add( 
								new RentApartment(
									address, 
									_counterID, 
									squareMeter, 
									numberOfRooms, 
									clientName, 
									price, 
									rentalStartDate, 
									rentalEndDate
								)
							);
						
						GUI_Operator.showGUI_Massage("Apartment added successfully");
					}			
			    break;
				case "Apartment For Sall":
					
					// String array for the labels
					String[] LABEL_TEXTS_SALL = {
							"Address",
					         "Square Meter", 
					         "Number Of Rooms", 
					         "Client Name", 
					         "Price",
					         "Offered Price",
					         "Entry Date dd-mm-yyyy"
				         };
					
					// Create a new GUI with the text labels we created
					GUI = new GUI_Operator(LABEL_TEXTS_SALL, "Create a new sale Apartment");
					    
				    // Take input from the text fields
					address = GUI.extractor("Address");
					
				    squareMeter = roundedDouble(
				    		Double.parseDouble(
				    				GUI.extractor("Square Meter"))
				    		);
				    
				    numberOfRooms = Integer.parseInt(
				    		GUI.extractor("Number Of Rooms")
				    		);
				    
				    clientName = GUI.extractor("Client Name");
				    
				    price = roundedDouble(
				    		Double.parseDouble(
				    				GUI.extractor("Price")));
				    
				    offeredPrice = roundedDouble(
				    		Double.parseDouble(GUI.extractor(
				    				"Offered Price")));
				    
				    entryDate = GUI.extractor("Entry Date dd-mm-yyyy");
	
				    
				    // Check if the id already exist in the list 
				    if ( this.isExist(_counterID + 1) ) {
				    	GUI_Operator.showGUI_Massage("The action failed, the database is allready contain an apartment with similar ID, please try again");
				    	_counterID++;
					} 
				    
				    // Check for invalid input
				    else if( !SellApartment.isValidFields( 
				    		squareMeter, 
				    		numberOfRooms, 
				    		price, address, 
				    		clientName, 
				    		entryDate, 
				    		offeredPrice
				    		)
				    	)
				    {  
				    	
				    	GUI_Operator.showGUI_Massage(
				    			SellApartment.validationError(
				    					squareMeter, 
				    					numberOfRooms, 
				    					price, address, 
				    					clientName, 
				    					entryDate, 
				    					offeredPrice
				    					)
				    			);
				    }
		
				    else { 
						// Advanced the counter and create a new apartment in the list
						_counterID++;
						this._apartments.add( 
								new SellApartment(
									address, 
									_counterID, 
									squareMeter, 
									numberOfRooms, 
									clientName, 
									price, 
									offeredPrice, 
									entryDate
								)
							);
						
						GUI_Operator.showGUI_Massage("Apartment added successfully");
					}			
				    				    		    
				break;
			}
		} catch (Exception e) {
			GUI_Operator.showGUI_Massage("The action failed, please fill in all the fields before sending the create file");
		}					    
	}

	/**
	 *  Check if the list contain specific apartment
	 * @param long ID - The ID of the Entity represent the primary key of the Entity value
	 * @return boolean - Return True if the apartment with the ID exist in the apartments list
	 */
	private boolean isExist(long ID) {
		for (Apartment a : _apartments) {
			if (a.getID() == ID) 
				return true;
		}				
		return false;		
	}
	
	
	/**
	 * Shows all the details of the apartments in the list
	 */
	public void showAllApartments() {
		
		try {
			// create and show table wit all the apartments details
			GUI_Table gt = new GUI_Table(_apartments);
		
		} 
		catch (Exception e) {
			GUI_Operator.showGUI_Massage("Somthing went wrong, please try again");
		}
	}
	
	/**
	 * Method that takes the user's preference according to which sorting he is interested 
	 * in doing and sends it to the relevant sorting method
	 */
	public void sortOptions() {
		// String array for the labels
				String[] options = {
						"Sort by Price",
						"Sort by Client Names",
						"Sort by ID's"
			        };
				
				try {
					//takes the user's preference according to which sorting he is interested in doing
					String userSelection = GUI_Operator.getUserSelection(
							options, 
							"Please select how do you like to sort the aprtment list", 
							"Sort"
						);
					
					// Switch case that represents user's choice of sorting the list 
					switch ( userSelection ) {
					
						case "Sort by Price":
							this.sorter( new PriceComparator() );
							break;
						
						case "Sort by Client Names":
							this.sorter( new NameComparator() );
							break;
							
						case "Sort by ID's":
							this.sorter( new IdComparator() );
							break;
					
						default:
							break;
					}
					
				} 
				catch (Exception e) {
					GUI_Operator.showGUI_Massage( "Somthing went wrong, please try again" );
				}
	}
		
	/**
	 * Sort all the apartments value in the list, the method uses Comparator
	 * @param comparator - the method we will sort the apartment by
	 */
	public void sorter(Comparator<ISortable> comparator) {
		// Sort with the comparator
		Collections.sort(_apartments, comparator);
		
		// Show massage to the user
		GUI_Operator.showGUI_Massage("The apartments were successfully sorted");
	}
	
	/**
	 * Method that takes the user's preference according to which way he is interested 
	 * to find the apartment
	 */
	public void findApartment() {
		
		// String array for the labels
		String[] options = {
				"Find by ID",
				"Find by Client Name",
				"Find from a list of IDs"
	        };
		
		try {
			//takes the user's preference according to which search he is interested in doing
			String userSelection = GUI_Operator.getUserSelection(
					options, 
					"Please select how do you like to find the aprtment", 
					"Find"
				);
			
			// Switch case that represents user's choice of search the list 
			switch ( userSelection ) {
			
				case "Find by ID":
					this.findByID();
					break;
				
				case "Find by Client Name":
					this.associateClientApartments();
					break;
					
				case "Find from a list of IDs":
					this.selectIdFromList();
					break;
			
				default:
					break;
			}
			
		} 
		catch (Exception e) {
			GUI_Operator.showGUI_Massage("Somthing went wrong, please try again");
		}		
					 
	}
	
	/**
	 * Find specific apartment by ID
	 */
	public void findByID() {
		// Ask the user for input, the user will give us ID		
				String findID = GUI_Operator.getUserTextInput(
						"Please enter the ID of the requested apartment", 
						"Enter the ID of the requested apartment");
				
				// We will look for an apartment with the ID , if we will found it then the user will   
				// be able to choose to perform actions on the apartments that match the search
				if ( isExist( Long.parseLong(findID) ) ) 
				{
					this.actionsOnApartment( getApartmentByID(findID) );
				}
				
				else 
				{
					// if the ID not found
					GUI_Operator.showGUI_Massage("The Apartment is not in our database :(");			
				}
	}
	
	/**
	 * Method that get a name of a client from the user and show all of his apartments
	 */
	public void associateClientApartments() {
			
		try {
			// Take input from the user (Client Name)
			String findName = GUI_Operator.getUserTextInput(
					"Please enter the ID of the requested apartment", 
					"Enter the ID of the requested apartment");
			
			// Collect all the apartment connected to the client name from the input
			ArrayList<Apartment> nameApartments = new ArrayList<>();
			
			for (Apartment apartment : _apartments) {
				if ( findName.toLowerCase().equals( 
						apartment.get_clientName().toLowerCase() ) ) 
				{
					nameApartments.add(apartment);
				}
			}
			
			// show the user all of the client apartment
			GUI_Table gt = new GUI_Table(nameApartments);
			
		} 
		catch (Exception e) {
			GUI_Operator.showGUI_Massage( "Somthing went wrong, please try again" );
		}
			
	}
	
	/**
	 * Preset the user a list of ID's that he can selects and perform actions
	 */
	public void selectIdFromList() {
		try {
			
			// Collect all the ID's from the apartments list
			String[] ID_Options = new String[_apartments.size()];
			
			for (int i = 0; i < _apartments.size(); i++) {
				ID_Options[i] = String.valueOf( _apartments.get(i).getID() );
			}
			
			// Use GUI to show the list of ID's to the user and he return the chosen ID
			String userSelection = GUI_Operator.getUserSelection( ID_Options, "Select the wanted ID", "Select the wanted ID 2" );
			
			// Give the user the option to perform actions on the selected apartment 
			this.actionsOnApartment( getApartmentByID( userSelection ) );
			
		} catch (Exception e) {
			GUI_Operator.showGUI_Massage( "Somthing went wrong, please try again" );
		}
	}

	/**
	 * Method that get ID and return the apartment with the ID
	 * 
	 * @param long ID - The input is the ID of the apartment we are looking for
	 * @return Apartment -The apartment with the ID 
	 */
	public Apartment getApartmentByID(String ID) {
		
		for (Apartment apartment : _apartments) {
			
			if (apartment.getID() == Long.parseLong(ID)) {
				return apartment;
			}
		}
		return null;
	}
	
	/** 
	 * Method that give the user option to take 3 action on apartment: Edit, Remove and Show details
	 * @param Apartment apartment - The apartment we want to take actions on
	 */
	public void actionsOnApartment(Apartment apartment) {
		
		// String array for the labels
		String[] options = {
				"Edit Apartment", 
				"Remove Apartment", 
				"Show Details"};
		
		// Now the user will be able to choose actions he can do on the apartment we found
		String userSelection = GUI_Operator.getUserSelection(
				options, 
				"Please select the action you want to take  on selected apartment", 
				"Chooses an cation"						
				);
		

		// Switch case that represents every action the user can do on the chosen apartment 
		switch (userSelection) {
			case "Edit Apartment":
				apartment.updateApartment();					
				break;
				
			case "Remove Apartment":
				try {
					// remove the chosen apartment from the list
					_apartments.remove(apartment);
					GUI_Operator.showGUI_Massage("Successfully removed from the list");
					
				} catch (Exception e) {
					
					// if something went wrong while trying to remove the object from the list
					GUI_Operator.showGUI_Massage("The action failed, please try again");
				}
				
				break;
				
			case "Show Details":
				 
				// create a table with the chosen apartment and show it to the user
				GUI_Table gt = new GUI_Table( new ArrayList<>( Arrays.asList(apartment) ) );
				break;

			default:
				break;
		}
	}
	
	/**
	 * 
	 * @param unroundedDouble
	 * @return the input after rounded to 2 down (xxxx.xx) 
	 */
	public static double roundedDouble(double unroundedDouble) {
		return (Math.round(unroundedDouble * 100.0) / 100.0);
	}
	
}
