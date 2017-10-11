package it.univr.MusicValley.gui.components;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Scrollable extends JScrollPane {
	
	public Scrollable(JPanel panel, boolean verticalScroll, boolean horizontalScroll) {
		super(panel);
		
		if (verticalScroll) 	setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		else 					setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		if (horizontalScroll) 	setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		else 					setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		setBorder(null);
		getVerticalScrollBar().setUnitIncrement(16);
		
	}
}
