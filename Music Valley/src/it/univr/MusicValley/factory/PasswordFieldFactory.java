package it.univr.MusicValley.factory;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import it.univr.MusicValley.utility.CharLimit;
import it.univr.MusicValley.utility.Colors;

public class PasswordFieldFactory extends JPasswordField {
	
	private final String type;
	private final int charLimit;
	
	public PasswordFieldFactory(String type, int charLimit) {
		super();
		this.type = type;
		this.charLimit = charLimit;
		createPasswordField();
	}
	
	private JPasswordField createPasswordField() {
		switch (type) {
			case "Input":	return createInputPasswordField();
			default:		return null;
		}
	}
	
	private JPasswordField createInputPasswordField() {
		setBackground(Colors.WHITE240);
		setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		setDocument(new CharLimit(charLimit));
		setFont(new Font(new JLabel().getFont().toString(), Font.PLAIN, 10));
		setForeground(Colors.GRAY050);
		
		return this;
	}
}
