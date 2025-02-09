package com.utils;

public class Utils {
	
	public static String normalizeAndCapitalize(String str) {
	    if (str == null || str.isEmpty()) {
	        return str;}

	    str = str.trim();

	    return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
	}


}
