package it.univr.MusicValley.gui.models;

import java.util.Observable;

import it.univr.MusicValley.data.Product;

public class ProductModel extends Observable {
	
	private Product product;
	
	// --------------------------------------------------------------------------------------------
	
	public ProductModel() {
		
	}

	// --------------------------------------------------------------------------------------------
	
	public void setProduct(Product product) {
		this.product = product;
		setChanged();
		notifyObservers();
	}

	// --------------------------------------------------------------------------------------------
	
	public Product getProduct() {
		return product;
	}
	
}
