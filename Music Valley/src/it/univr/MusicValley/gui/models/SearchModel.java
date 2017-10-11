package it.univr.MusicValley.gui.models;

import java.util.Observable;

public class SearchModel extends Observable {
	
	private String searchedText;
	private Integer minPrice;
	private Integer maxPrice;
	private String selectedRadioButton;
	
	// --------------------------------------------------------------------------------------------
	
	public SearchModel(){
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void toDefaultModel() {
		searchedText = "";
		minPrice = 0;
		maxPrice = 50;
		selectedRadioButton = "Artista";
		
		setChanged();
		notifyObservers();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public String getToSearch()				{ return searchedText; }
	public Integer getMinPrice()			{ return minPrice; }
	public Integer getMaxPrice()			{ return maxPrice; }
	public String getSelectedRadioButton()	{ return selectedRadioButton; }
	
}
