package it.univr.MusicValley.gui.views.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.gui.components.DropShadow;
import it.univr.MusicValley.gui.controllers.ProductController;
import it.univr.MusicValley.utility.Colors;
import it.univr.MusicValley.utility.Utils;

public class ProductPreview extends JPanel {
	
	private Product product;
	private ProductController productController;
	
	private Image coverImage;
	private JLabel coverLabel = new JLabel();
	private JLabel titleLabel = new JLabel();
	private JLabel artistLabel = new JLabel();
	private JButton buyButton;
	
	private JPanel buyButtonPanel = new JPanel(new GridBagLayout());
	private JPanel boxPanel = new JPanel();
	private JPanel shadowPanel = new JPanel(new GridBagLayout());

	private Dimension coverLabelDimension;
	private Dimension titleLabelDimension;
	private Dimension artistLabelDimension;
	private Dimension buyButtonDimension;
	private Dimension buyButtonPanelDimension;
	private Dimension boxPanelDimension;
	private Dimension shadowPanelDimension;
	
	private Timer zoomIn;
	private Timer zoomOut;
	
	private static final int timerSpeed = 5;
	private static final int defaultSize = 100;
	private static final int zoomedSize = 107;
	private double tempSize = 100;
	private static final int betweenBaseAndShadow = 30;
	private static final int betweenShadowAndBox = 35;
	private static final int productInfoWidth = 200;
	private static final int labelHeight = 30;
	private static final int labelTextSize = 17;
	private static final String buyString = "ACQUISTA";
	
	// --------------------------------------------------------------------------------------------
	
	public ProductPreview(Product product, ProductController productController) {
		this.product = product;
		this.productController = productController;
		initialize();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void initialize() {
		
		zoomIn = initializeZoomInTimer();
		zoomOut = initializeZoomOutTimer();
		
		try {
			coverImage = ImageIO.read(product.getCover());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		coverLabel.setIcon(toImageIcon(coverImage, productInfoWidth));
		coverLabel.setBorder(BorderFactory.createLineBorder(Colors.BLACK, 1));
		coverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		coverLabel.setMaximumSize(coverLabel.getPreferredSize());
		
		titleLabel.setText(product.getTitle());
		titleLabel.setFont(new Font("Titillium Web", Font.PLAIN, labelTextSize));
		titleLabel.setForeground(Colors.GRAY050);
		titleLabel.setPreferredSize(new Dimension(productInfoWidth, labelHeight));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setMaximumSize(titleLabel.getPreferredSize());
		titleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		
		artistLabel.setText(product.getArtist());
		artistLabel.setFont(new Font("Titillium Web", Font.PLAIN, labelTextSize));
		artistLabel.setForeground(Colors.GRAY100);
		artistLabel.setPreferredSize(new Dimension(productInfoWidth, labelHeight));
		artistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		artistLabel.setMaximumSize(artistLabel.getPreferredSize());
		artistLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
		
		buyButton = new ButtonFactory("Buy", buyString + " " + Utils.toDecimalFormat(product.getPrice()) + " €");
		
		buyButtonPanel.setBackground(Colors.WHITE);
		buyButtonPanel.setPreferredSize(new Dimension(productInfoWidth, 50));
		buyButtonPanel.add(buyButton);
		buyButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyButtonPanel.setMaximumSize(buyButtonPanel.getPreferredSize());
		
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		boxPanel.setBackground(Colors.WHITE);
		boxPanel.add(coverLabel);
		boxPanel.add(titleLabel);
		boxPanel.add(artistLabel);
		boxPanel.add(buyButtonPanel);
		
		shadowPanel.setBackground(Color.WHITE);
		shadowPanel.add(boxPanel);
		shadowPanel.setPreferredSize(new Dimension(
			(int) shadowPanel.getPreferredSize().getWidth() + betweenShadowAndBox,
			(int) shadowPanel.getPreferredSize().getHeight() + betweenShadowAndBox
		));
		shadowPanel.setBorder(new CompoundBorder(new DropShadow(), BorderFactory.createLineBorder(Colors.GRAY230, 1)));
		
		coverLabelDimension = coverLabel.getPreferredSize();
		titleLabelDimension = titleLabel.getPreferredSize();
		artistLabelDimension = artistLabel.getPreferredSize();
		buyButtonDimension = buyButton.getPreferredSize();
		buyButtonPanelDimension = buyButtonPanel.getPreferredSize();
		boxPanelDimension = boxPanel.getPreferredSize();
		shadowPanelDimension = shadowPanel.getPreferredSize();
		
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		add(shadowPanel);
		setPreferredSize(new Dimension(
			(int) getPreferredSize().getWidth() + betweenBaseAndShadow,
			(int) getPreferredSize().getHeight() + betweenBaseAndShadow
		));
		
		addController();
	}
	
	// --------------------------------------------------------------------------------------------
	
	private ImageIcon toImageIcon(Image image, int size) {
		return new ImageIcon(image.getScaledInstance(size, size, Image.SCALE_SMOOTH));
	}
	
	// --------------------------------------------------------------------------------------------
	
	private Timer initializeZoomInTimer() {
		return new Timer(timerSpeed, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tempSize < zoomedSize) {
					changeSize(++tempSize);
					revalidate();
					repaint();
				} else
					zoomIn.stop();
			}
		});
	}
	
	private Timer initializeZoomOutTimer() {
		return new Timer(timerSpeed, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tempSize > defaultSize) {
					changeSize(--tempSize);
					revalidate();
					repaint();
				} else
					zoomIn.stop();
			}
		});
	}

	// --------------------------------------------------------------------------------------------
	
	public void onMouseEntered() {
		zoomOut.stop();
		zoomIn.start();
	}
	
	public void onMouseExited() {
		zoomIn.stop();
		zoomOut.start();
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void changeSize(double percent) {
		
		double newSize = tempSize / 100;
		
		coverLabel.setMaximumSize(new Dimension(
			(int) (coverLabelDimension.getWidth() * newSize),
			(int) (coverLabelDimension.getHeight() * newSize)
		));
		coverLabel.setIcon(toImageIcon(
			coverImage, 
			(int) coverLabel.getMaximumSize().getWidth()
		));
		
		titleLabel.setPreferredSize(new Dimension(
			(int) (titleLabelDimension.getWidth() * newSize),
			(int) (titleLabelDimension.getHeight() * newSize)
		));
		titleLabel.setMaximumSize(titleLabel.getPreferredSize());
		titleLabel.setFont(new Font(
			titleLabel.getFont().getFontName(),
			titleLabel.getFont().getStyle(), 
			(int) (labelTextSize * newSize)
		));
		titleLabel.setBorder(new EmptyBorder((int) (5 * newSize), 0, 0, 0));
		
		artistLabel.setPreferredSize(new Dimension(
			(int) (artistLabelDimension.getWidth() * newSize),
			(int) (artistLabelDimension.getHeight() * newSize)
		));
		artistLabel.setMaximumSize(artistLabel.getPreferredSize());
		artistLabel.setFont(new Font(
			artistLabel.getFont().getFontName(),
			artistLabel.getFont().getStyle(),
			(int) (labelTextSize * newSize)
		));
		artistLabel.setBorder(new EmptyBorder(0, 0, (int) (5 * newSize), 0));
		
		buyButton.setPreferredSize(new Dimension(
			(int) (buyButtonDimension.getWidth() * newSize),
			(int) (buyButtonDimension.getHeight() * newSize)
		));
		buyButton.setFont(new Font(
			buyButton.getFont().getFontName(),
			buyButton.getFont().getStyle(),
			(int) (16 * newSize)
		));
		
		buyButtonPanel.setPreferredSize(new Dimension(
			(int) (buyButtonPanelDimension.getWidth() * newSize),
			(int) (buyButtonPanelDimension.getHeight() * newSize)
		));
		buyButtonPanel.setMaximumSize(buyButtonPanel.getPreferredSize());
		
		boxPanel.setPreferredSize(new Dimension(
			(int) (boxPanelDimension.getWidth() * newSize),
			(int) (boxPanelDimension.getHeight() * newSize)
		));
		
		shadowPanel.setPreferredSize(new Dimension(
			(int) (shadowPanelDimension.getWidth() * newSize),
			(int) (shadowPanelDimension.getHeight() * newSize)
		));
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void addController() {
		shadowPanel.addMouseListener(productController);
		buyButton.addMouseListener(productController);
		buyButton.addActionListener(productController);
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Product getProductInPreview()	{ return product; }
	public JButton getBuyButton()			{ return buyButton; }
}
