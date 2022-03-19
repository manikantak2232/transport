package com.pixelbox.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class TransportCryptoUtils {

	public static String getSha1HexDigest(String plainString) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String sha1HexDigest = null;
				
		boolean result = true;
		int errorCode = 0;
		String errorMessage = "";
	
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
	        byte[] hashedBytes = md.digest(plainString.getBytes("UTF-8"));
	 	        
	        StringBuffer stringBuffer = new StringBuffer();
	        for (int i = 0; i < hashedBytes.length; i++) {
	            stringBuffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16)
	                    .substring(1));
	        }	        
	        sha1HexDigest = stringBuffer.toString();	       
		} catch (Exception ex) {
			result = false;
			errorCode = TransportGlobalErrorMessageMap.EXCEPTION;
			errorMessage = ex.getMessage();
			responseParameters.put("result", result);
			responseParameters.put("errorCode", errorCode);
			responseParameters.put("errorMessage", errorMessage);
		}
	
		return sha1HexDigest;
		
	}
	
	
	public static int getOtp(int size) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int otp = 0;
				
		boolean result = true;
		int errorCode = 0;
		String errorMessage = "";
	
		try {
			StringBuilder generatedToken = new StringBuilder();
	        try {
	            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
	            // Generate 20 integers 0..20
	            for (int i = 0; i < size; i++) {
	                generatedToken.append(number.nextInt(9));
	            }
	            if(generatedToken.length()==5){
	            	generatedToken.append("9");
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	        otp = Integer.parseInt(generatedToken.toString());	       
		} catch (Exception ex) {
			result = false;
			errorCode = TransportGlobalErrorMessageMap.EXCEPTION;
			errorMessage = ex.getMessage();
			responseParameters.put("result", result);
			responseParameters.put("errorCode", errorCode);
			responseParameters.put("errorMessage", errorMessage);
		}
	
		return otp;
		
	}
}
