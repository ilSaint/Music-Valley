package it.univr.MusicValley.gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

import it.univr.MusicValley.utility.Colors;

public class MarginPanel extends JPanel {
	
	private JPanel centerPanel;
	private double sidePanelPercent = 12.5;
	
	private JPanel leftSidePanel = new JPanel();
	private JPanel rightSidePanel = new JPanel();
	
	// --------------------------------------------------------------------------------------------
	
	public MarginPanel(JPanel centerPanel) {
		super(new BorderLayout());
		this.centerPanel = centerPanel;
		initialize();
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	public MarginPanel(JPanel centerPanel, double sidePanelPercent) {
		super(new BorderLayout());
		this.centerPanel = centerPanel;
		this.sidePanelPercent = sidePanelPercent;
		initialize();
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void initialize() {
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				double sidePanelSize = sidePanelPercent * (getSize().getWidth()) / 100;
				
				leftSidePanel.setPreferredSize(new Dimension((int) sidePanelSize, 0));
				rightSidePanel.setPreferredSize(new Dimension((int) sidePanelSize, 0));
				centerPanel.revalidate();
			}
		});
		
		leftSidePanel.setBackground(Colors.WHITE);
		rightSidePanel.setBackground(Colors.WHITE);
		
		add(leftSidePanel, BorderLayout.WEST);
		add(this.centerPanel, BorderLayout.CENTER);
		add(rightSidePanel, BorderLayout.EAST);
	}
	
}
