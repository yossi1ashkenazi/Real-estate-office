package app.entity.apartment;

import app.gui.GUI_Operator;

/**
 * 
 * This class represent the rental type of Apartment
 *
 */
public class RentApartment extends Apartment {

	private String _rentalStartDate;
	private String _rentalEndDate;
	
	/**
	 * \
	 * @param _address - Apartment address
	 * @param _id - Entity id
	 * @param _squareMeter - The Apartment square in meters
	 * @param _numberOfRooms = The Apartment total room numbers
	 * @param _clientName - Mame of the client
	 * @param _price - The Price per month of the rent 
	 * @param _rentlStartDate - The date we start the rental
	 * @param _rentalEndDate - The date we finish the rental
	 */
	public RentApartment(String _address, long _id, double _squareMeter, int _numberOfRooms, String _clientName, double _price, 
			 String _rentlStartDate, String _rentalEndDate) {
		
		super(_address, _id, _squareMeter, _numberOfRooms, _clientName, _price);
		this._rentalStartDate = _rentlStartDate;
		this._rentalEndDate = _rentalEndDate;
	}

	public String get_rentlStartDate() {
		return _rentalStartDate;
	}

	public void set_rentlStartDate(String _rentlStartDate) {
		this._rentalStartDate = _rentlStartDate;
	}

	public String get_rentalEndDate() {
		return _rentalEndDate;
	}

	public void set_rentalEndDate(String _rentalEndDate) {
		this._rentalEndDate = _rentalEndDate;
	}
	
	
	/**
	 * @return - Object cloning refers to creation of exact copy of an object
	 */
	@Override
	public RentApartment clone() throws CloneNotSupportedException {
		return (RentApartment)super.clone();
		
	}
	
	/**
	 * @return boolean - Return True if the user input is valid
	 * 
	 * @param double squareMeter 
	 * @param int numberOfRooms 
	 * @param double price 
	 * @param String address 
	 * @param String clientName  
	 */
	public static boolean isValidFields( double squareMeter, int numberOfRooms, double price, String address, String clientName, String rentalStartDate, String rentalEndDate) {
		return (Apartment.isValidFields(squareMeter, numberOfRooms, price, address, clientName) &&
				rentalStartDate.matches("\\d{2}-\\d{2}-\\d{4}") && 
				rentalEndDate.matches("\\d{2}-\\d{2}-\\d{4}")
				); 	
	}
	
	/**
	 * @return String - If and when the user insert invalid input this method will show hem what kind of invalid
	 * input he inserted
	 * 
	 * @param squareMeter
	 * @param numberOfRooms
	 * @param price
	 * @param address
	 * @param clientName
	 * @param rentalStartDate
	 * @param rentalEndDate
	 * @return
	 */
	public static String validationError( double squareMeter, int numberOfRooms, double price, String address, String clientName, String rentalStartDate, String rentalEndDate) {
		
		StringBuilder errors = new StringBuilder(Apartment.validationError(squareMeter, numberOfRooms, price, address, clientName));
		
		if (!rentalStartDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
			errors.append("* The Rental Start Date you enterd does not match to date pattern (dd-mm-yyyy) \n" );
		}
		
		if (!rentalEndDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
			errors.append("* The Rental End Date you enterd does not match to date pattern (dd-mm-yyyy) \n" );
		}
		
		return errors.toString();
	}
	
	/**
	 * Method that update the apartment's values
	 */
	@Override
	public void updateApartment(){
		try {
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
			
			// Create and run GUI
			GUI_Operator GUI = new GUI_Operator(LABEL_TEXTS_RENT, "Update apartment values");
			
		    // Take input from the text fields
		     String address = GUI.extractor( "Address" );
		     
			 double squareMeter = ApartmentManager.roundedDouble(
					 Double.parseDouble(
							 GUI.extractor( "Square Meter" )
							 )
					 );
			 
			 int numberOfRooms = Integer.parseInt(
					 GUI.extractor( "Number Of Rooms" )
					 );
			 
			 String clientName = GUI.extractor("Client Name");
			 
			 double price = ApartmentManager.roundedDouble(
					 Double.parseDouble(
							 GUI.extractor("Price")
							 )
					 ); 
			 
			 String rentalStartDate = GUI.extractor("Start Date (dd-mm-yyyy)");
			 
			 String rentalEndDate = GUI.extractor("End Date (dd-mm-yyyy)");
			 
			// Check for invalid input
		    if(
		    	!RentApartment.isValidFields( 
		    			squareMeter, 
		    			numberOfRooms, 
		    			price, address, 
		    			clientName,
		    			rentalStartDate, 
		    			rentalEndDate
		    			)
		    	)
		    	
		    {   	
		    	GUI_Operator.showGUI_Massage(RentApartment.validationError(
		    			squareMeter, 
		    			numberOfRooms, 
		    			price, address, 
		    			clientName, 
		    			rentalStartDate, 
		    			rentalEndDate)
		    			);

			} 
		    
		    // Set new values
		    else 
		    {
				this.set_address(address);
			    this.set_squareMeter(squareMeter);
			    this.set_numberOfRooms(numberOfRooms);
			    this.set_clientName(clientName);
			    this.set_price(price);
			    this.set_rentlStartDate(rentalStartDate);
			    this.set_rentalEndDate(rentalEndDate);
			    
			    GUI_Operator.showGUI_Massage("Successfully updated");
			}		    
		    
		} catch (Exception e) {
			// In case of exception the user will see the next massage
			GUI_Operator.showGUI_Massage("The action failed, please fill in all the fields before sending the update");
		}
		
	}

	/**
	 * @return String - The type of the apartment (Apartment for rent)
	 */
	@Override
	public String getType() {
		return "Apartment for rent";
	}

	/**
	 * @return String - all parameters as one String
	 */
	@Override
	public String getAllParam() {
		//if the place in the table is blank
		String blankSpace = "------";
		return 
				this.getType() + ","+
				this.getID() + "," +
				this.get_address() + "," +
				this.get_squareMeter() + "," +
				this.get_numberOfRooms() + "," +
				this.get_clientName() + "," +
				this.get_price() + "," +
				blankSpace + "," +
				blankSpace + "," +
				this.get_rentlStartDate()+ "," +
				this.get_rentalEndDate();
		
	}
	

}
