package it.univr.MusicValley.gui.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import it.univr.MusicValley.data.Product;

public class MainModel extends Observable {
	
	private List<Product> lastReleasesList = new ArrayList<Product>();
	private List<Product> bestAlbumsList = new ArrayList<Product>();
	private List<Product> userPersonalizedList = new ArrayList<Product>();
	private String lastReleasesListTitle = new String("ULTIME USCITE");
	private String bestAlbumsListTitle = new String("ALBUM MIGLIORI");
	private String userPersonalizedListTitle = new String("");
	
	private static final int defaultMaxElementsPerList = 11;
	private static final String suggestedFor = "CONSIGLIATO PER ";
	private static final String stringFormat = "<html><nobr><span>%s<font color='rgb(238,0,0)'><b>%s</b></font></span></nobr></html>";
	
	// --------------------------------------------------------------------------------------------
	
	public MainModel() {
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void initialize() {
		setLastReleasesList();
		setBestAlbumsList();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void setLastReleasesList() {
		
		List<Product> orderedDatabase= new ArrayList<Product>();
		
		for(Product product: Database.getInstance().getDatabase())
			orderedDatabase.add(product);
		
		Comparator<Product> yearOrder = new Comparator<Product>() {
			@Override
			public int compare(Product product1, Product product2) {
				return product2.getDate() - product1.getDate();
			}
	    };
	    
	    Collections.sort(orderedDatabase, yearOrder);
		
		int maxElementsPerList = Integer.min(defaultMaxElementsPerList, orderedDatabase.size());
		int count = 0;
		
		for(Product product : orderedDatabase)
			if(count++ < maxElementsPerList)
				lastReleasesList.add(product);
		
		setChanged();
		notifyObservers("LastReleasesList");
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void setBestAlbumsList() {
		
		List<Product> orderedDatabase = new ArrayList<Product>();
		
		for(Product product: Database.getInstance().getDatabase())
			orderedDatabase.add(product);
		
		Comparator<Product> salesOrder =  new Comparator<Product>() {
			@Override
			public int compare(Product product1, Product product2) {
				return product2.getSales() - product1.getSales();
			}
	    };
	    
	    Collections.sort(orderedDatabase, salesOrder);
		
	    int maxElementsPerList = Integer.min(defaultMaxElementsPerList, orderedDatabase.size());
		int count = 0;
		
		for(Product product: orderedDatabase)
			if(count++ < maxElementsPerList)
				bestAlbumsList.add(product);
		
		setChanged();
		notifyObservers("BestAlbumList");
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void setUserPersonalizedList(String username) {
		
		Set<String> genreSet = new HashSet<String>();
		Set<Product> personalSet = new HashSet<Product>();
		List<Product> genreList = new ArrayList<Product>();
		
		for (Product product: LoggedUser.getInstance().getBoughtProduct())
			genreSet.add(product.getGenre());
	
		for (String str: genreSet)
			for (Product product:Database.getInstance().getDatabase())
				if (str.equals(product.getGenre()))
					genreList.add(product);
				
		for (Iterator<Product> iter = genreList.listIterator(); iter.hasNext(); ) {
		    Product a = iter.next();
		    for (Product prod: LoggedUser.getInstance().getBoughtProduct())
		    	if (a.getCode() == prod.getCode()) {
		    		iter.remove();
		    }
		}
		
		int maxElementsPerList = Integer.min(defaultMaxElementsPerList, genreList.size());
		Random randomizer = new Random();
		
		for(int count = 0 ; count < maxElementsPerList || personalSet.size() < maxElementsPerList; count++)
			personalSet.add(genreList.get(randomizer.nextInt(genreList.size())));
		
		userPersonalizedList.clear();
		userPersonalizedList.addAll(personalSet);
		
		//setChanged();
		//notifyObservers("UserPersonalizedList");
	
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void setUserPersonalizedListTitle(String username) {
		
		if (!username.equals("") && !username.equals(null)) {
			userPersonalizedListTitle = String.format(stringFormat, suggestedFor, username.toUpperCase());
			setUserPersonalizedList(username);
		} else {
			userPersonalizedListTitle = username;
		}
		
		setChanged();
		notifyObservers("userStateChanged");
	}
	
	// --------------------------------------------------------------------------------------------
	
	public List<Product> getLastReleasesList() 			{ return lastReleasesList; }
	public List<Product> getBestAlbumsList() 			{ return bestAlbumsList; }
	public List<Product> getUserPersonalizedList() 		{ return userPersonalizedList; }
	public String getLastReleasesListTitle()			{ return lastReleasesListTitle; }
	public String getBestAlbumsListTitle()				{ return bestAlbumsListTitle; }
	public String getUsernameListTitle() 				{ return userPersonalizedListTitle; }
	
}
