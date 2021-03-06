package com.overseer.util;

public class StringUtil {

	public static boolean isNullOrEmpty(String value) {
		return ((value == null) || value.isEmpty());
	}

	public static boolean isNullOrWhitespace(String value) {
		if (value == null)
			return true;
		char[] characters = value.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			if (!Character.isWhitespace(characters[i]))
				return false;
		}
		return true;
	}
}
