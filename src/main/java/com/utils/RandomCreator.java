package com.utils;

import java.security.SecureRandom;

public class RandomCreator {

	
	private static final SecureRandom random = new SecureRandom();

	
	public static String generateUID(String forWhat,Integer digits) {
		return randomGenerate(forWhat, digits).toString();		
	}
	
	public static StringBuilder randomGenerate(String forWhat,Integer digits) {
		StringBuilder uid=new StringBuilder(forWhat);
		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		for(int i=1;i<=digits;i++) {
			uid.append(characters.charAt(random.nextInt(characters.length())));
		}
		return uid;
	}
}
