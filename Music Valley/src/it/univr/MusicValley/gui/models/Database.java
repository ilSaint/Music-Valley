package it.univr.MusicValley.gui.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;

import it.univr.MusicValley.data.Member;
import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.utility.Utils;

public final class Database extends Observable {
	
	private static Database instance = null;
	
	private List<Product> products = new ArrayList<Product>();
	private String[] genreList;
	
	private static final String databasePath = new String("C:/database");
	private static final String databaseUsersPath = new String("C:/database/users.txt");
	private static final String databaseOrdersPath = new String("C:/database/orders.txt");
	private static final String databaseProductsPath = new String("C:/database/products.txt");
	
	// --------------------------------------------------------------------------------------------
	
	private Database() {
		readDataBase();
	}

	// --------------------------------------------------------------------------------------------
	
    public static Database getInstance() {
        if (instance != null)
			return instance;
		else
			return instance = new Database();
    }
    
	// --------------------------------------------------------------------------------------------
	
	private void readDataBase() {
		
		SortedSet<String> genreSortedSet = new TreeSet<String>();
		String line = null;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(databasePath + "/products.txt"));
			
			while ((line = br.readLine()) != null) {
				
				String[] product = line.split(";");
				
				File cover = new File(databasePath + "/covers/" + product[0].trim() + ".jpg");
				int code = Integer.parseInt(product[0].trim());;
				String title = product[1].trim();
				String[] traces = product[2].trim().split("%");
				float price = Float.parseFloat(product[3].trim());
				int date = Integer.parseInt(product[4].trim());
				String artist = product[5].trim();
				String description = product[6].trim();
				String genre = Utils.toUpperCaseFirstChar(product[7].trim());
				String[] participants = product[8].trim().split("%");
				String[] instruments = product[9].trim().split("%");
				for (String instrument : instruments)
					instrument = instrument.toLowerCase();
				String type = product[10].trim();
				int sales = Integer.parseInt(product[11].trim());
				int quantity = Integer.parseInt(product[12].trim());
				
				ArrayList<Member> members = new ArrayList<Member>();
				for (int i = 13, size = product.length; i < size; i++)
					members.add(new Member(product[i].split("%")));
				
				genreSortedSet.add(genre);
				products.add(new Product(code, title, traces, price, date, artist, description, genre, participants, instruments, members, cover, type, sales, quantity));
				
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		genreList = genreSortedSet.toArray(new String[genreSortedSet.size()]);
	}
	
	// --------------------------------------------------------------------------------------------
	
	public List<Product> getDatabase() 			{ return products; }
	public String[] getGenreList() 				{ return genreList; }
	public String getDatabasePath() 			{ return databasePath; }
	public String getDatabaseUsersPath() 		{ return databaseUsersPath; }
	public String getDatabaseOrdersPath() 		{ return databaseOrdersPath; }
	public String getDatabaseProductsPath() 	{ return databaseProductsPath; }
	
	// --------------------------------------------------------------------------------------------
	
}
