package it.univr.MusicValley.gui.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.gui.models.Database;
import it.univr.MusicValley.gui.models.LoggedUser;
import java.io.FileNotFoundException;
import java.util.Observable;

public final class LoggedUser extends Observable {
	
	private static LoggedUser instance = null;
	
	private String username;			// 0
	private String firstName;			// 2
	private String lastName;			// 3
	private String region;				// 5
	private String city;				// 6
	private String cap;					// 7
	private String address;				// 8
	private String civicNumber;			// 9
	private String phoneNumber;			// 10
	private String cellphoneNumber;		// 11
	private int cartCounter;
	private boolean isNewUser;
	
	private boolean isDiscounted;
	private Set<Product> boughtProduct;
	
	private static final int minimalPrice = 250;
	
	// --------------------------------------------------------------------------------------------
	
	public LoggedUser() {
		
	}
	
	// --------------------------------------------------------------------------------------------
	
    public static LoggedUser getInstance() {
        if (instance != null)
			return instance;
		else
			return instance = new LoggedUser();
    }
	
	// --------------------------------------------------------------------------------------------
	
	public void setLoggedUser(String username, boolean isNewUser) {
		
		String line = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(Database.getInstance().getDatabasePath() + "/users.txt"));
			
			while ((line = br.readLine()) != null) {
				String[] userData = line.split(";");
				if (userData[0].equals(username)) {
					this.username = userData[0];
					this.firstName = userData[2];
					this.lastName = userData[3];
					this.region = userData[5];
					this.city = userData[6];
					this.cap = userData[7];
					this.address = userData[8];
					this.civicNumber = userData[9];
					this.phoneNumber = userData[10];
					if (userData.length > 11)
						this.cellphoneNumber = userData[11];
					else
						this.cellphoneNumber = "";
					this.cartCounter = 0;
					this.isNewUser = isNewUser;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		userPersonalization();
		
		this.setChanged();
		this.notifyObservers();
	}

	// --------------------------------------------------------------------------------------------
	
	public void userLogout() {
		
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.region = "";
		this.city = "";
		this.cap = "";
		this.address = "";
		this.civicNumber = "";
		this.phoneNumber = "";
		this.cellphoneNumber = "";
		this.cartCounter = 0;
		this.isNewUser = false;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void incrementCart() {
		this.cartCounter++;
		//
		//
	}
	
	public void resetCart() {
		this.cartCounter = 0;
		//
		//
	}

	// --------------------------------------------------------------------------------------------
	
	public void userPersonalization() {
		
		String line = null;
		int ordersCount = 0;
		int discountCount = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String username;
		String[] codes;
		float totalPrice;
		String firstBoughtStringData = null;
		String boughtStringData = null;
		Date boughtDate = null;
		Date firstBoughtDate = null;
	
		boughtProduct = new HashSet<Product>();
		isDiscounted = false;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(Database.getInstance().getDatabasePath() + "/orders.txt"));
			
			while ((line = br.readLine()) != null) {
				
				String[] order = line.split(";");
				
				username = order[0];
				
				if (username.equals(LoggedUser.getInstance().getUsername())) {
					codes = order[1].trim().split("%");
					
					for(String code:codes) {
						for(Product product: Database.getInstance().getDatabase())
							if(Integer.parseInt(code) == product.getCode())
								boughtProduct.add(product);
					}
					
					totalPrice = Float.parseFloat(order[2]);
					
					if (ordersCount == 0){					
						firstBoughtStringData = order[3];
						boughtStringData = order[3];
					} else {
						boughtStringData = order[3];
					}
					
					try {
						boughtDate = formatter.parse(boughtStringData);
						firstBoughtDate = formatter.parse(firstBoughtStringData);
					} catch (ParseException e) {
						
					} 
						
					if (totalPrice > minimalPrice) {
						if(checkDate(firstBoughtDate, boughtDate)){
							if(++discountCount > 3)
								isDiscounted = true;
						} else
							discountCount = 0;
					}
					
					ordersCount++;
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	private boolean checkDate(Date firstBoughtDate, Date boughtDate) {
		return ChronoUnit.DAYS.between(boughtDate.toInstant(), firstBoughtDate.toInstant()) <= 365;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public String getUsername() 			{ return username; }
	public String getFirstName() 			{ return firstName; }
	public String getLastName() 			{ return lastName; }
	public String getRegion() 				{ return region; }
	public String getCity() 				{ return city; }
	public String getCap() 					{ return cap; }
	public String getAddress() 				{ return address; }
	public String getCivicNumber() 			{ return civicNumber; }
	public String getPhoneNumber() 			{ return phoneNumber; }
	public String getCellphoneNumber() 		{ return cellphoneNumber; }
	public int getCartCounter()				{ return cartCounter; }
	public boolean getNewUser()				{ return isNewUser; }
	public boolean getIsDiscounted() 		{ return isDiscounted; }
	public Set<Product> getBoughtProduct()	{ return boughtProduct; }
	
}
