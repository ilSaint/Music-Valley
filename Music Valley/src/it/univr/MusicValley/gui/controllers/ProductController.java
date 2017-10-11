package it.univr.MusicValley.gui.controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import it.univr.MusicValley.gui.Gui;
import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.models.CartModel;
import it.univr.MusicValley.gui.models.ProductModel;
import it.univr.MusicValley.gui.views.ProductView;
import it.univr.MusicValley.gui.views.components.ProductPreview;

public class ProductController implements ActionListener, MouseListener {
	
	private CardManager cardManager;
	private Map<String, Object> modelMap;
	private ProductView productView;
	
	public ProductController(CardManager cardManager, Map<String, Object> modelMap, ProductView productView) {
		this.cardManager = cardManager;
		this.modelMap = modelMap;
		this.productView = productView;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		ProductPreview productPreview = (ProductPreview) SwingUtilities.getAncestorOfClass(ProductPreview.class, (Component) ae.getSource());
		
		if (ae.getSource() == productView.getBuyButton() ) {
			((CartModel) modelMap.get("CartModel")).addProductToCart(productView.getVisualizedProduct());
		} else if ((productPreview) != null && ae.getSource() == productPreview.getBuyButton()) {
			((CartModel) modelMap.get("CartModel")).addProductToCart(productPreview.getProductInPreview());
		} else if (ae.getSource() == productView.getGoBackButton()) {
			cardManager.goToPreviousCard();
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if (! (me.getSource() instanceof JButton)) {
			ProductPreview productPreview = (ProductPreview) SwingUtilities.getAncestorOfClass(ProductPreview.class, (Component) me.getSource());
			((ProductModel) modelMap.get("ProductModel")).setProduct(productPreview.getProductInPreview());
			cardManager.goToCard(Gui.Views.PRODUCT, false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		ProductPreview productPreview = (ProductPreview) SwingUtilities.getAncestorOfClass(
			ProductPreview.class, (Component) me.getSource()
		);
		productPreview.onMouseEntered();
	}

	@Override
	public void mouseExited(MouseEvent me) {
		ProductPreview productPreview = (ProductPreview) SwingUtilities.getAncestorOfClass(
			ProductPreview.class, (Component) me.getSource()
		);
		productPreview.onMouseExited();
	}

	@Override
	public void mousePressed(MouseEvent me) {
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		
	}
	
}
