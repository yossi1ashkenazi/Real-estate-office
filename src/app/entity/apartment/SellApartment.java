package app.entity.apartment;
import app.gui.GUI_Operator;

/**
 * This class represent the sale type of Apartment
 */
public class SellApartment extends Apartment {

	private double _offeredPrice;
	private String _entryDate;
	
	/**
	 * 
	 * @param _address - Apartment address
	 * @param _id - Entity id
	 * @param _squareMeter - The Apartment square in meters
	 * @param _numberOfRooms = The Apartment total room numbers
	 * @param _clientName - Mame of the client
	 * @param _price - The Price per month of the rent 
	 * @param _offeredPrice - The price the client offered
	 * @param _entryDate - The date that the client will enter the apartment
	 */
	public SellApartment(String _address, long _id, double _squareMeter, int _numberOfRooms, String _clientName, double _price,
			double _offeredPrice, String _entryDate) {
		super(_address, _id, _squareMeter, _numberOfRooms, _clientName, _price);
		this._offeredPrice = _offeredPrice;
		this._entryDate = _entryDate;
	}
	
	public double get_offeredPrice() {
		return _offeredPrice;
	}

	public void set_offeredPrice(double _offeredPrice) {
		this._offeredPrice = _offeredPrice;
	}

	public String get_entryDate() {
		return _entryDate;
	}

	
	public void set_entryDate(String _entryDate) {
		this._entryDate = _entryDate;
	}
	
	/**
	 * @return - Object cloning refers to creation of exact copy of an object
	 */
	@Override
	public SellApartment clone() throws CloneNotSupportedException {
		return (SellApartment)super.clone();
		
	}
	
	/**
	 * @return boolean - return True if the user input is valid
	 * 
	 * @param double squareMeter 
	 * @param int numberOfRooms 
	 * @param double price 
	 * @param String address 
	 * @param String clientName  
	 */	
	public static boolean isValidFields( double squareMeter, int numberOfRooms, double price, String address, String clientName, String entryDate, double offeredPrice) {
		return Apartment.isValidFields(squareMeter, numberOfRooms, price, address, clientName) &&
				entryDate.matches("\\d{2}-\\d{2}-\\d{4}") && 
				offeredPrice >=1 &&
				offeredPrice < Double.MAX_VALUE
				;
	}
	
	/**
	 * * @return String - If and when the user insert invalid input this method will show hem what kind of invalid
	 * input he inserted
	 * 
	 * @param squareMeter
	 * @param numberOfRooms
	 * @param price
	 * @param address
	 * @param clientName
	 * @param entryDate
	 * @param offeredPrice
	 * @return
	 */
	public static String validationError( double squareMeter, int numberOfRooms, double price, String address, String clientName, String entryDate, double offeredPrice) {	
		
		StringBuilder errors = new StringBuilder(
				Apartment.validationError(
						squareMeter, 
						numberOfRooms, 
						price, address, 
						clientName)
				);
		
		if (offeredPrice < 1) {
			errors.append("* Offerd Price parameter cannot be a negativ number !\n" );
		}
		
		if (squareMeter >= Double.MAX_VALUE) {
			errors.append("* Offerd Price parameter cannot be more then " + Double.MAX_VALUE +  "!\n" );
		}
		
		if (!entryDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
			errors.append("* The Entry Date you enterd is does not match to date pattern (dd-mm-yyyy) \n" );
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
			String[] LABEL_TEXTS_SALL = {
					"Address",
			         "Square Meter", 
			         "Number Of Rooms", 
			         "Client Name", 
			         "Price",
			         "Offered Price", 
			         "Entry Date (dd-mm-yyyy)" 
		         };
			
			// Create and run GUI
			GUI_Operator GUI = new GUI_Operator(LABEL_TEXTS_SALL, "Update apartment values");
			
		    // Take input from the text fields 
		     String address = GUI.extractor("Address");
		     
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
			 
			 double offeredPrice = ApartmentManager.roundedDouble(
					 Double.parseDouble(
							 GUI.extractor("Offered Price")
							 )
					 ); 
			 
			 String entryDate = GUI.extractor("Entry Date (dd-mm-yyyy)");
		    
			 // Check for invalid input
			 if( !SellApartment.isValidFields( 
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
			 
			// Set new values
			 else 
			 { 
				this.set_address(address);
			    this.set_squareMeter(squareMeter);
			    this.set_numberOfRooms(numberOfRooms);
			    this.set_clientName(clientName);
			    this.set_price(price);
			    this.set_offeredPrice(offeredPrice);
			    this.set_entryDate(entryDate);
			    
			    GUI_Operator.showGUI_Massage("Successfully updated");
			}
		  	    
		} catch (Exception e) {
			// In case of exception the user will see the next massage
			GUI_Operator.showGUI_Massage("The action failed, please fill in all the fields before sending the update");
		} 
		
	}


	/**
	 * @return String - The type of the Apartment (Apartment for sell)
	 */
	@Override
	public String getType() {
		return "Apartment for sell";
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
				this.get_offeredPrice()+ "," +
				this.get_entryDate() + "," +
				blankSpace + "," +
				blankSpace;		
	}
	

}
