package it.univr.MusicValley.utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CharLimit extends PlainDocument {
	
	private int limit;
	
	// --------------------------------------------------------------------------------------------
	
	public CharLimit(int limit) {
		super();
		this.limit = limit;
	}

	// --------------------------------------------------------------------------------------------
	
	@Override
	public void insertString(int offset, String str, AttributeSet attr) {
		if (str == null)
			return;
		
		if ((getLength() + str.length()) <= limit)
			try {
				super.insertString(offset, str, attr);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
	}
	
}
