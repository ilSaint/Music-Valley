package it.univr.MusicValley.gui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import it.univr.MusicValley.data.Product;

public class GenreModel extends Observable {
	
	private String genreTitle = new String();
	private List<Product> genreProducts = new ArrayList<Product>();

	// -------------------------------------------------------------------------------------
	
	public GenreModel() {
		
	}

	// -------------------------------------------------------------------------------------
	
	public void setGenreTitle(String genre) {
		
		genreTitle = genre;
		
		setChanged();
		notifyObservers();
	}
	
	// -------------------------------------------------------------------------------------
	
	public void setProducts(String genre) {
		
		genreProducts.clear();
		
		for (Product product : Database.getInstance().getDatabase()) {
			if (product.getGenre().equals(genre))
				genreProducts.add(product);
		}
		
		setChanged();
		notifyObservers();
	}

	// -------------------------------------------------------------------------------------
	
	public String getGenreTitle() 				{ return genreTitle; }
	public List<Product> getGenreProducts() 	{ return genreProducts; }
	
}
