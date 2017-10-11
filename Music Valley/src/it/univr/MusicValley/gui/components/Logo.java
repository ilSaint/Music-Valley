package it.univr.MusicValley.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Logo extends JLabel {
	

	private final Color backgroundColor;
	private final int logoImageWidth;
	private final int logoImageHeight;
	
	
	private static final File logoImage = new File("resources/logo/logo_medium.png");
	
	// --------------------------------------------------------------------------------------------
	
	public Logo(Color backgroundColor, int logoImageWidth, int logoImageHeight) {
		this.backgroundColor = backgroundColor;
		this.logoImageWidth = logoImageWidth;
		this.logoImageHeight = logoImageHeight;
		createLogoLabel();
	}

	// --------------------------------------------------------------------------------------------
	
	private JLabel createLogoLabel() {
		
		setBackground(this.backgroundColor);
		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(this.logoImageWidth, this.logoImageHeight));
		
		try {
			this.setIcon(new ImageIcon(ImageIO.read(logoImage).getScaledInstance(this.logoImageWidth, this.logoImageHeight, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
}
