package it.univr.MusicValley.gui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


import it.univr.MusicValley.data.Product;

public class ResultModel extends Observable {
	
	private List<Product> resultList = new ArrayList<Product>();
	private String searchedText;
	
	// --------------------------------------------------------------------------------------------
	
	public ResultModel() {

	}
	
	// --------------------------------------------------------------------------------------------
	
	public void setResultList(String searchedText, Integer minPrice, Integer maxPrice, String radioButtonText) {
		
		this.searchedText = searchedText;
		this.resultList.clear();
		
		if (radioButtonText.equals("Artista")) {
			for (Product product: Database.getInstance().getDatabase()) {
				if (product.getArtist().equalsIgnoreCase(searchedText) && checkPrice(product.getPrice(), minPrice, maxPrice))
					resultList.add(product);
			}
			
		} else if (radioButtonText.equals("Album")) {
			for (Product product: Database.getInstance().getDatabase()) {
				if (product.getTitle().equalsIgnoreCase(searchedText) && checkPrice(product.getPrice(), minPrice, maxPrice))
					resultList.add(product);
			}
			
		} else if (radioButtonText.equals("Genere")) {
			for (Product product: Database.getInstance().getDatabase()) {
				if (product.getGenre().equalsIgnoreCase(searchedText) && checkPrice(product.getPrice(), minPrice, maxPrice))
					resultList.add(product);
			}
			
		} else if (radioButtonText.equals("Partecipante")) {
			for (Product product: Database.getInstance().getDatabase()) {
				for (String participant: product.getParticipants())
					if (participant.equalsIgnoreCase(searchedText) && checkPrice(product.getPrice(), minPrice, maxPrice))
						resultList.add(product);
			}
		}
		
		setChanged();
		notifyObservers();
	}

	// --------------------------------------------------------------------------------------------
	
	private boolean checkPrice(float price, int minPrice, int maxPrice){
		return price >= minPrice && price <= maxPrice;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public String getTitle()				{ return searchedText; }
	public List<Product> getResearchList() 	{ return resultList; }
	
}
