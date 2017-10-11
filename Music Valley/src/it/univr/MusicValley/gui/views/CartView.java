package it.univr.MusicValley.gui.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.gui.components.HeaderPanel;
import it.univr.MusicValley.gui.components.MarginPanel;
import it.univr.MusicValley.gui.components.Scrollable;
import it.univr.MusicValley.gui.controllers.CartController;
import it.univr.MusicValley.gui.models.CartModel;
import it.univr.MusicValley.gui.views.components.CartProductPreview;
import it.univr.MusicValley.utility.Colors;
import it.univr.MusicValley.utility.Utils;

public class CartView extends JPanel implements Observer {
	
	private final Map<String, Object> controllerMap;
	
	private final Map<Product, CartProductPreview> productPanelMap = new HashMap<Product, CartProductPreview>();
	private final JLabel totalPriceLabel = new JLabel();
	private final JButton proceedButton = new ButtonFactory("DoButton", "PROCEDI");
	private final JPanel centerPanel = new JPanel();
	
	// --------------------------------------------------------------------------------------------
	
	public CartView(Map<String, Object> controllerMap){
		this.controllerMap = controllerMap;
		
		setLayout(new BorderLayout());
		add(new HeaderPanel("CARRELLO", false), BorderLayout.NORTH);
		add(createMainPanel(), BorderLayout.CENTER);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createMainPanel() {
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Colors.WHITE);
		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(createProceedPanel(), BorderLayout.SOUTH);
		
		return new MarginPanel(mainPanel);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JScrollPane createCenterPanel() {
		
		centerPanel.setBackground(Colors.WHITE);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		Scrollable centerPanelScrollable = new Scrollable(centerPanel, true, false);
		centerPanelScrollable.setBorder(BorderFactory.createLineBorder(Colors.GRAY200, 1));
		
		return centerPanelScrollable;
	}

	// --------------------------------------------------------------------------------------------
	
	private JPanel createProceedPanel() {
		
		totalPriceLabel.setFont(new Font("Titillium Web", Font.BOLD, 20));
		proceedButton.setPreferredSize(new Dimension((int) proceedButton.getPreferredSize().getWidth(), 40));
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 0));
		titlePanel.setBackground(Colors.WHITE);
		titlePanel.setBorder(new EmptyBorder(10, 0, 5, 0));
		titlePanel.add(new LabelFactory("PageTitle", "TOTALE:", 20));
		titlePanel.add(totalPriceLabel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 0));
		buttonPanel.setBackground(Colors.WHITE);
		buttonPanel.setBorder(new EmptyBorder(5, 0, 15, 0));
		buttonPanel.add(proceedButton);
		
		JPanel proceedPanel = new JPanel(new BorderLayout());
		proceedPanel.add(titlePanel, BorderLayout.NORTH);
		proceedPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		return proceedPanel;
	}
	
	// ============================================================================================
	
	public void addController() {
		proceedButton.addActionListener((CartController) controllerMap.get("CartController"));
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if (obs instanceof CartModel) {
			
			CartModel cartModel = (CartModel) obs;
			Integer operation = (Integer) obj;
			
			if (operation != null) {
				
				if (operation.equals(1)) {
					for (Map.Entry<Product, Integer> entry : cartModel.getCartList().entrySet()) {
						if (!productPanelMap.containsKey(entry.getKey())) {
							CartProductPreview cartProductPreview = new CartProductPreview(
								entry.getKey(), ((CartController) controllerMap.get("CartController"))
							);
							cartModel.addObserver(cartProductPreview);
							centerPanel.add(cartProductPreview);
							productPanelMap.put(entry.getKey(), cartProductPreview); 	
							break;
						}
					}
					
				} else if (operation.equals(0)) {
					for (Map.Entry<Product, CartProductPreview> entry : productPanelMap.entrySet()) {
						if (!cartModel.getCartList().containsKey(entry.getKey())) {
							centerPanel.remove(entry.getValue());
							productPanelMap.remove(entry.getKey());
							break;
						}
					}
				}
				
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			
			totalPriceLabel.setText("" + Utils.toDecimalFormat(((CartModel) obs).getTotalPrice()) + " €");
		}
	}

	// ============================================================================================
	
	public JButton getProceedButton() { return proceedButton; }
	
}
