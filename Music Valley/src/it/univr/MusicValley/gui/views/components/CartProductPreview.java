package it.univr.MusicValley.gui.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.gui.controllers.CartController;
import it.univr.MusicValley.gui.models.CartModel;
import it.univr.MusicValley.utility.Colors;
import it.univr.MusicValley.utility.Utils;

public class CartProductPreview extends JPanel implements Observer {
	
	private Product product;
	private CartController cartController;
	
	private JLabel cover = new JLabel();
	private JLabel title = new LabelFactory("Album", null, textSize);
	private JLabel artist = new LabelFactory("Artist", null, textSize);
	private JLabel type = new LabelFactory("ProductViewContent", null, textSize);
	private JLabel price = new LabelFactory("Price", null, textSize);
	private JLabel quantityPrice = new LabelFactory("QuantityPrice", null, textSize);
	private JSpinner quantitySpinner = new JSpinner();
	private JButton removeProductButton = new ButtonFactory("RemoveButton", "");
	
	private static final int paneWidth = 150;
	private static final int textSize = 17;
	private static final int titleSize = 20;
	
	// --------------------------------------------------------------------------------------------
	
	public CartProductPreview(Product product, CartController cartController){
		this.product = product;
		this.cartController = cartController;
		createCartProductViewPanel();
	}

	// --------------------------------------------------------------------------------------------
	
	private void createCartProductViewPanel() {
		
		setLayout(new BorderLayout());
		setBackground(Colors.WHITE);
		add(createInfoPanel(), BorderLayout.WEST);
		add(createQuantityPanel(), BorderLayout.EAST);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) getPreferredSize().getHeight()));
		
		addController();
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createInfoPanel() {
		
		try {
			cover.setIcon(new ImageIcon(ImageIO.read(product.getCover()).getScaledInstance(paneWidth, paneWidth, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cover.setBorder(new EmptyBorder(15, 15, 15, 15));
		title.setText(product.getTitle());
		artist.setText(product.getArtist());
		type.setText(product.getType());
		price.setText(Utils.toDecimalFormat((product.getPrice())) + " €");
		
		JPanel infoPanelEast = new JPanel();
		infoPanelEast.setBackground(Colors.WHITE);
		infoPanelEast.setBorder(new EmptyBorder(15, 0, 0, 0));
		infoPanelEast.setLayout(new BoxLayout(infoPanelEast, BoxLayout.Y_AXIS));
		infoPanelEast.add(title);
		infoPanelEast.add(artist);
		infoPanelEast.add(type);
		infoPanelEast.add(price);
		
		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.setBackground(Colors.WHITE);
		infoPanel.add(cover, BorderLayout.WEST);
		infoPanel.add(infoPanelEast, BorderLayout.EAST);
		
		return infoPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createQuantityPanel() {
		
		quantitySpinner.setModel(new SpinnerNumberModel(1, 1, product.getQuantity(), 1));
		quantitySpinner.setFont(new Font("Titillium Web", Font.PLAIN, 15));
		quantitySpinner.setEditor(new JSpinner.DefaultEditor(quantitySpinner));
		
		quantityPrice.setText(Utils.toDecimalFormat((product.getPrice() * ((SpinnerNumberModel) quantitySpinner.getModel()).getNumber().intValue())) + " €");
		quantityPrice.setPreferredSize(new Dimension(150, 30));
		
		removeProductButton.setPreferredSize(new Dimension(35, 35));
		
		JPanel quantityPanel = new JPanel(new BorderLayout());
		quantityPanel.setBackground(Colors.WHITE);
		quantityPanel.add(new LabelFactory("PageTitle", "QUANTITÀ", titleSize), BorderLayout.NORTH);
		quantityPanel.add(quantitySpinner, BorderLayout.SOUTH);
		
		JPanel pricePanel = new JPanel(new BorderLayout());
		pricePanel.setBackground(Colors.WHITE);
		pricePanel.add(new LabelFactory("PageTitle", "PREZZO", titleSize), BorderLayout.NORTH);
		pricePanel.setPreferredSize(quantityPanel.getPreferredSize());
		pricePanel.add(quantityPrice, BorderLayout.SOUTH);
		
		JPanel priceQuantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		priceQuantityPanel.setBackground(Colors.WHITE);
		priceQuantityPanel.setBorder(new EmptyBorder(0, 0, 0, 5));
		priceQuantityPanel.add(quantityPanel);
		priceQuantityPanel.add(pricePanel);
		
		JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		optionPanel.setBorder(new EmptyBorder(0, 0, 0, 40));
		optionPanel.setBackground(Colors.WHITE);
		optionPanel.add(priceQuantityPanel);
		optionPanel.add(removeProductButton);
		
		JPanel quantityBasePanel = new JPanel(new GridBagLayout());
		quantityBasePanel.setBackground(Colors.WHITE);
		quantityBasePanel.add(optionPanel);
		
		return quantityBasePanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Product getProduct()					{ return product; }
	public JButton getRemoveProductButton() 	{ return removeProductButton; }
	public JLabel getQuantityPriceLabel()		{ return quantityPrice; }
	
	// ============================================================================================
	
	public void addController(){
		removeProductButton.addActionListener(cartController);
		quantitySpinner.addChangeListener(cartController);
	}

	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object arg) {
		for (Map.Entry<Product, Integer> entry : ((CartModel) obs).getCartList().entrySet())
			if (product.equals(entry.getKey()))
				quantitySpinner.setValue(entry.getValue());
	}
}
