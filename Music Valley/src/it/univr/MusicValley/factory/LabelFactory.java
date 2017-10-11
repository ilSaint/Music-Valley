package it.univr.MusicValley.factory;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.utility.Colors;

public class LabelFactory extends JLabel {
	
	private final String type;
	private final int fontSize;

	// --------------------------------------------------------------------------------------------
	
	public LabelFactory(String type, String labelText, int fontSize) {
		super(labelText);
		this.type = type;
		this.fontSize = fontSize;
		createLabel();
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createLabel() {
		
		this.setFont(new Font("Titillium Web", Font.PLAIN, this.fontSize));
		
		switch (this.type) {
			case "Main":				return createMainLabel();
			case "Album":				return createAlbumLabel();
			case "Artist":				return createArtistLabel();
			case "Price":				return createPriceLabel();
			case "PageTitle":			return createPageTitleLabel();
			case "Form":				return createFormLabel();
			case "FormError":			return createFormErrorLabel();
			case "ProductViewTitle":	return createProductViewTitleLabel();
			case "ProductViewContent":	return createProductViewContentLabel();
			case "QuantityPrice":		return createQuantityPriceLabel();
			case "NotFound":			return createNotFoundLabel();
			default:					return null;
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JLabel createMainLabel() {
		//setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(Colors.GRAY050);
		setBorder(new EmptyBorder(20, 50, 10, 0));
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createAlbumLabel() {
		//setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(Colors.GRAY050);
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createArtistLabel() {
		//setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(Colors.GRAY100);
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createPriceLabel() {
		//setHorizontalAlignment(SwingConstants.RIGHT);
		setForeground(Colors.RED238);
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createPageTitleLabel() {
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(Colors.RED238);
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createFormLabel() {
		//setAlignmentX(Component.CENTER_ALIGNMENT);
		setForeground(Colors.GRAY050);
		//setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createFormErrorLabel() {
		//setAlignmentX(Component.CENTER_ALIGNMENT);
		setBackground(Colors.WHITE);
		setOpaque(true);
		setForeground(Colors.WHITE);
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createProductViewTitleLabel() {
		//setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(Colors.RED238);
		return this;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JLabel createProductViewContentLabel() {
		//setHorizontalAlignment(SwingConstants.LEFT);
		setBackground(Colors.WHITE);
		setForeground(Colors.GRAY050);
		return this;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JLabel createQuantityPriceLabel(){
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Titillium Web", Font.PLAIN, this.fontSize));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
		return this;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JLabel createNotFoundLabel() {
		setFont(new Font("Titillium Web", Font.PLAIN, this.fontSize));
		setBackground(Color.WHITE);
		setForeground(Colors.GRAY100);
		setHorizontalAlignment(JLabel.CENTER);
		return this;
	}
	
}
