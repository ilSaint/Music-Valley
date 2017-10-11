package it.univr.MusicValley.authentication.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;

import it.univr.MusicValley.gui.models.Database;

public class LoginModel extends Observable {
	
	private String username;
	private char[] password;
	private boolean succeed;
	
	private static final File userDatabase = new File(Database.getInstance().getDatabasePath() + "/users.txt");
	
	// --------------------------------------------------------------------------------------------
	
	public LoginModel() {
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void setUsername(String username)	{ this.username = username; }
	public void setPassword(char[] password)	{ this.password = password; }
	
	// --------------------------------------------------------------------------------------------
	
	private void setSucceed(boolean succeed) {
		this.succeed = succeed;
		setChanged();
		notifyObservers();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public boolean authenticationVerification() {
		
		String[] userData;
		String line = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(userDatabase));
			
			while ((line = br.readLine()) != null) {
				userData = line.split(";");
				
				if (userData[0].equals(username) && Arrays.equals(userData[1].toCharArray(), password)) {
					br.close();
					setSucceed(true);
					return succeed;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setSucceed(false);
		return succeed;
	}

	// --------------------------------------------------------------------------------------------
	
	public String getUsername()		{ return username; }
	public boolean getSucceed()		{ return succeed; }
	
}
