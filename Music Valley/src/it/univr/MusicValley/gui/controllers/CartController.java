package it.univr.MusicValley.gui.controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.models.CartModel;
import it.univr.MusicValley.gui.views.CartView;
import it.univr.MusicValley.gui.views.components.CartProductPreview;
import it.univr.MusicValley.utility.Utils;

public class CartController implements ActionListener, ChangeListener {

	//private CardManager cardManager;
	private Map<String, Object> modelMap;
	private CartView cartView;
	
	// --------------------------------------------------------------------------------------------
	
	public CartController(CardManager cardManager, Map<String, Object> modelMap, CartView cartView) {
		//this.cardManager = cardManager;
		this.modelMap = modelMap;
		this.cartView = cartView;
	}

	// --------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		CartProductPreview cartProductPreview = (CartProductPreview) SwingUtilities.getAncestorOfClass(
			CartProductPreview.class, (Component) ae.getSource()
		);
		
		if (cartProductPreview != null) {
			((CartModel) modelMap.get("CartModel")).removeProductFromCart(cartProductPreview.getProduct());
		} else if (ae.getSource() == cartView.getProceedButton()) {
			System.out.println("Procedi");
		}
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		
		CartProductPreview cartProductPreview = (CartProductPreview) SwingUtilities.getAncestorOfClass(
			CartProductPreview.class, (Component) ce.getSource()
		);
		
		if (cartProductPreview != null) {
			JSpinner spinner = (JSpinner) ce.getSource();
			float price = (Integer) spinner.getValue() * cartProductPreview.getProduct().getPrice();
			
			cartProductPreview.getQuantityPriceLabel().setText("" + Utils.toDecimalFormat(price) + " €");
			((CartModel) modelMap.get("CartModel")).setQuantity(cartProductPreview.getProduct(), (Integer) spinner.getValue());
		}
		
	}
	
	// --------------------------------------------------------------------------------------------
	
}
