package it.univr.MusicValley.gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.utility.Colors;

public class HeaderPanel extends JPanel {
	
	private String titleString;
	private boolean addBackButton;
	
	private JLabel titleLabel = new LabelFactory("PageTitle", null, titleFontSize);
	private JButton backButton;
	
	private static final int sidePanelWidth = 100;
	private static final int headerHeight = 80;
	private static final int backButtonSize = 50;
	private static final int titleFontSize = 38;
	
	// --------------------------------------------------------------------------------------------
	
	public HeaderPanel(String titleString, boolean addBackButton) {
		this.titleString = titleString;
		this.addBackButton = addBackButton;
		initialize();
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void initialize() {
		
		titleLabel.setText(titleString);
		
		JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, (headerHeight - backButtonSize) / 2));
		westPanel.setBackground(Colors.WHITE);
		westPanel.setPreferredSize(new Dimension(sidePanelWidth, headerHeight));
		
		if (addBackButton) {
			backButton = new ButtonFactory("Back", "");
			backButton.setPreferredSize(new Dimension(backButtonSize, backButtonSize));
			westPanel.add(backButton);
		}
		
		JPanel labelPanel = new JPanel(new GridBagLayout());
		labelPanel.setBackground(Colors.WHITE);
		labelPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, headerHeight));
		labelPanel.add(titleLabel);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Colors.WHITE);
		eastPanel.setPreferredSize(new Dimension(sidePanelWidth, headerHeight));
		
		setLayout(new BorderLayout());
		add(labelPanel);
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	public JLabel getTitleLabel()	{ return titleLabel; }
	public JButton getBackButton() 	{ return backButton; }
	
}
