package it.univr.MusicValley.authentication.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;

import it.univr.MusicValley.gui.models.Database;
import it.univr.MusicValley.utility.FormLimits;

public class RegistrationModel extends Observable {
	
	private String firstName;
	private String lastName;
	private String username;
	private char[] password1;
	private char[] password2;
	private String fiscalCode;
	private String region;
	private String city;
	private String cap;
	private String address;
	private String civicNumber;
	private String phoneNumber;
	private String cellphoneNumber;
	
	private boolean succeed;
	private String errorMessage;
	
	private static final String a1 = "• contenere solo caratteri alfabetici\n";
	private static final String a2 = "• contenere solo caratteri alfanumerici\n";
	private static final String a3 = "• contenere solo caratteri alfanumerici ed i simboli: . _ -\n";
	private static final String a4 = "• contenere solo caratteri numerici\n";
	private static final String b1 = "• avere una lunghezza minima di ";
	private static final String b2 = "• avere una lunghezza massima di ";
	private static final String b3 = " caratteri\n";
	private static final String b4 = " carattere\n";
	
	private static final String firstNameErrorMessage = "Nome inserito non valido. Il nome deve:\n" 
		+ a1 + b1 + FormLimits.firstNameCharMin + b3 + b2 + FormLimits.firstNameCharLimit + b3 + "\n";
	
	private static final String lastNameErrorMessage = "Cognome inserito non valido. Il cognome deve:\n"
		+ a1 + b1 + FormLimits.lastNameCharMin + b3 + b2 + FormLimits.lastNameCharLimit + b3 + "\n";
	
	private static final String usernameErrorMessage = "Nome utente inserito non valido. Il nome utente deve:\n" 
		+ a3 + b1 + FormLimits.usernameCharMin + b3 + b2 + FormLimits.usernameCharLimit + b3 + "\n";
	
	private static final String passwordErrorMessage = "Password inserita non valida. La password deve:\n" 
		+ b1 + FormLimits.passwordCharMin + b3 + b2 + FormLimits.passwordCharLimit + b3 + "\n";
	
	private static final String differentPasswordsMessage = "Le password non coincidono\n\n";
	
	private static final String fiscalCodeErrorMessage = "Codice fiscale inserito non valido. Il codice fiscale deve:\n"
		+ a2 + b1 + FormLimits.fiscalCodeCharMin + b3 + b2 + FormLimits.fiscalCodeCharLimit + b3 + "\n";
	
	private static final String cityErrorMessage = "Città inserita non valida. La città deve:\n"
		+ a1 + b1 + FormLimits.cityCharMin + b3 + b2 + FormLimits.cityCharLimit + b3 + "\n";
	
	private static final String capErrorMessage = "CAP inserito non valido. Il CAP deve:\n"
		+ a4 + b1 + FormLimits.capCharMin + b3 + b2 + FormLimits.capCharLimit + b3 + "\n";
	
	private static final String addressErrorMessage = "Indirizzo inserito non valido. L'indirizzo deve:\n"
		+ a2 + b1 + FormLimits.addressCharMin + b3 + b2 + FormLimits.addressCharLimit + b3 + "\n";
	
	private static final String civicNumberErrorMessage = "Numero civico inserito non valido. Il numero civico deve:\n"
		+ a2 + b1 + FormLimits.civicNumberCharMin + b4 + b2 + FormLimits.civicNumberCharLimit + b3 + "\n";
	
	private static final String phoneNumberErrorMessage = "Numero di telefono inserito non valido. Il numero di telefono deve:\n"
		+ a4 + b1 + FormLimits.phoneNumberCharMin + b3 + b2 + FormLimits.phoneNumberCharLimit + b3 + "\n";
	
	private static final String cellphoneErrorMessage = "Numero di cellulare inserito non valido. Il numero di cellulare deve:\n"
		+ a4 + b1 + FormLimits.cellphoneNumberCharMin + b3 + b2 + FormLimits.cellphoneNumberCharLimit + b3 + "\n";

	private static final String userExistsMessage = "Esiste già utente \"";
	
	private static final File userDatabase = new File(Database.getInstance().getDatabaseUsersPath());
	
	// --------------------------------------------------------------------------------------------
	
	public RegistrationModel() {
		
	}

	// --------------------------------------------------------------------------------------------
	
	public void setFirstName(String firstName) 				{ this.firstName = firstName; }
	public void setLastName(String lastName) 				{ this.lastName = lastName; }
	public void setUsername(String username) 				{ this.username = username; }
	public void setPassword1(char[] password1) 				{ this.password1 = password1; }
	public void setPassword2(char[] password2) 				{ this.password2 = password2; }
	public void setFiscalCode(String fiscalCode) 			{ this.fiscalCode = fiscalCode; }
	public void setRegion(String region) 					{ this.region = region; }
	public void setCity(String city) 						{ this.city = city; }
	public void setCap(String cap) 							{ this.cap = cap; }
	public void setAddress(String address) 					{ this.address = address; }
	public void setCivicNumber(String civicNumber) 			{ this.civicNumber = civicNumber; }
	public void setPhoneNumber(String phoneNumber) 			{ this.phoneNumber = phoneNumber; }
	public void setCellphoneNumber(String cellphoneNumber) 	{ this.cellphoneNumber = cellphoneNumber; }
	
	// --------------------------------------------------------------------------------------------
	
	public void verifyFields() {
		
		if (stringIsValid(firstName, 1, FormLimits.firstNameCharMin)) {
			succeed = true;
		} else {
			errorMessage = firstNameErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(lastName, 1, FormLimits.lastNameCharMin)) {
			succeed = true;
		} else {
			errorMessage = lastNameErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(username, 2, FormLimits.usernameCharMin)) {
			if (userExists()) {
				errorMessage = userExistsMessage + username + "\"";
				succeed = false;
				return;
			} else
				succeed = true;
		} else {
			errorMessage = usernameErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(String.valueOf(password1), 5, FormLimits.passwordCharMin)) {
			succeed = true;
		} else {
			errorMessage = passwordErrorMessage;
			succeed = false;
			return;
		}
		
		if (Arrays.equals(password2, password1)) {
			succeed = true;
		} else {
			errorMessage = differentPasswordsMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(fiscalCode, 3, FormLimits.fiscalCodeCharMin)) {
			succeed = true;
		} else {
			errorMessage = fiscalCodeErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(city, 1, FormLimits.cityCharMin)) {
			succeed = true;
		} else {
			errorMessage = cityErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(cap, 4, FormLimits.capCharMin)) {
			succeed = true;
		} else {
			errorMessage = capErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(address, 5, FormLimits.addressCharMin)) {
			succeed = true;
		} else {
			errorMessage = addressErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(civicNumber, 6, FormLimits.civicNumberCharMin)) {
			succeed = true;
		} else {
			errorMessage = civicNumberErrorMessage;
			succeed = false;
			return;
		}
		
		if (stringIsValid(phoneNumber, 4, FormLimits.phoneNumberCharMin)) {
			succeed = true;
		} else {
			errorMessage = phoneNumberErrorMessage;
			succeed = false;
			return;
		}
		
		if (!cellphoneNumber.equals(null) && !cellphoneNumber.equals("")) {
			if (stringIsValid(cellphoneNumber, 4, FormLimits.cellphoneNumberCharMin)) {
				succeed = true;
			} else {
				errorMessage = cellphoneErrorMessage;
				succeed = false;
				return;
			}
		} else {
			succeed = true;
		}
		
	}

	// --------------------------------------------------------------------------------------------
	
	private boolean stringIsValid(String fieldString, int type, int min) {
		
		if (fieldString.equals(null) || fieldString.equals("") || fieldString.length() < min) {
			return false;
			
		} else {
			switch (type) {
				case 1:		return fieldString.matches("[A-Za-zÀàÈèÉéÌìÒòÙù ]+");				// nome, congome, città
				case 2:		return fieldString.matches("(?=.*[a-zA-Z])*[a-zA-Z0-9-_.]+");		// nome utente
				case 3:		return fieldString.matches("[A-Za-z0-9]+");							// codice fiscale
				case 4:		return fieldString.matches("[0-9]+");								// cap, num tel, num cell
				case 5: 	return fieldString.matches(".*[a-zA-Z]+.*");						// indirizzo, password
				case 6:		return fieldString.matches(".*[a-zA-Z0-9]+.*");						// num civico
				default:	return false;
			}
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	private boolean userExists() {
		
		String[] userData;
		String line = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(userDatabase));
			
			while ((line = br.readLine()) != null) {
				userData = line.split(";");
				if (userData[0].equals(username)) {
					br.close();
					return true;
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void registerUser() {
		
		try {
			
			BufferedWriter out = new BufferedWriter(new FileWriter(userDatabase, true));
			
			out.write(username + ";");
			out.write(String.valueOf(password1) + ";");
			out.write(firstName + ";");
			out.write(lastName + ";");
			out.write(fiscalCode + ";");
			out.write(region + ";");
			out.write(city + ";");
			out.write(cap + ";");
			out.write(address + ";");
			out.write(civicNumber + ";");
			out.write(phoneNumber + ";");
			out.write(cellphoneNumber + ";");
			out.write("\n");
			
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// ============================================================================================
	
	public String getUsername()			{ return username; }
	public boolean getSucceed() 		{ return succeed; }
	public String getErrorMessage() 	{ return errorMessage; }
	
}
