package it.univr.MusicValley.gui.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import it.univr.MusicValley.data.Product;

public class CartModel extends Observable {
	
	private Map<Product, Integer> cartList = new HashMap<Product, Integer>();
	private float totalPrice = 0;
	
	// --------------------------------------------------------------------------------------------
	
	public CartModel() {
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void initialize() {
		setChanged();
		notifyObservers();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void addProductToCart(Product product) {
		
		if (cartList.containsKey(product))
			cartList.put(product, (cartList.get(product) + 1));
		else
			cartList.put(product, 1);
		
		calculateTotalPrice();
		
		setChanged();
		notifyObservers(1);
	}

	// --------------------------------------------------------------------------------------------
	
	public void removeProductFromCart(Product product) {
		
		if (cartList.containsKey(product)){
			cartList.remove(product);
			
			calculateTotalPrice();
			
			setChanged();
			notifyObservers(0);
		}
	}

	// --------------------------------------------------------------------------------------------

	public void setQuantity(Product product, int value){
		
		cartList.put(product, value);
		
		calculateTotalPrice();
		
		setChanged();
		notifyObservers();
	}

	// --------------------------------------------------------------------------------------------
	
	private void calculateTotalPrice() {
		
		totalPrice = 0;
		
		if (!cartList.isEmpty()){
			for (Map.Entry<Product, Integer> entry : cartList.entrySet()){
				totalPrice += entry.getKey().getPrice() * entry.getValue();
			}
		}
	}

	// --------------------------------------------------------------------------------------------
	
	public float getTotalPrice()					{ return totalPrice; }
	public Map<Product, Integer> getCartList()		{ return cartList;}

}
