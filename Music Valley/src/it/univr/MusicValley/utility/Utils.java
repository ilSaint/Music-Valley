package it.univr.MusicValley.utility;

import java.text.DecimalFormat;

public class Utils {

	// --------------------------------------------------------------------------------------------
	
	public static String toUpperCaseFirstChar(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
	
	public static String toUpperCaseFirstCharOfString(String string) {
		
		String[] splittedString = string.split(" ");
		String result = "";
		
		for (String str: splittedString){
			toUpperCaseFirstChar(str);
			result += str + " ";
		}
		
		return result;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public static String toDecimalFormat(float value) {
		return new DecimalFormat("#0.00").format(value);
	}
	
}
