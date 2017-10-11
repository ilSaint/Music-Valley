package it.univr.MusicValley.factory;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import it.univr.MusicValley.utility.CharLimit;
import it.univr.MusicValley.utility.Colors;

public class TextFieldFactory extends JTextField {
	
	private final String type;
	private final int fontSize;
	private final int textCharLimit;

	// --------------------------------------------------------------------------------------------
	
	public TextFieldFactory(String type, String labelText, int fontSize, int textCharLimit) {
		super(labelText);
		this.type = type;
		this.fontSize = fontSize;
		this.textCharLimit = textCharLimit;
		createTextField();
	}

	// --------------------------------------------------------------------------------------------
	
	private JTextField createTextField() {
		switch (type) {
			case "Input": 	return createInputTextField();
			default:		return null;
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JTextField createInputTextField() {
		setBackground(Colors.WHITE240);
		setFont(new Font("Titillium Web", Font.PLAIN, this.fontSize));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		setDocument(new CharLimit(textCharLimit));
		
		return this;
	}
	
}
