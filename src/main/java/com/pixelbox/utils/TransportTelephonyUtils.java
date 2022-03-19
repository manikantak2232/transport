package com.pixelbox.utils;

import java.security.MessageDigest;
import java.util.HashMap;

public class TransportTelephonyUtils {
	public static String getDeformattedPhoneNumber(String phoneNumber) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String sha1HexDigest = null;
				
		boolean result = true;
		int errorCode = 0;
		String errorMessage = "";
		String deformattedPhoneNumber = phoneNumber;
				
		try {
			deformattedPhoneNumber = deformattedPhoneNumber.replaceAll(" ", "");
			deformattedPhoneNumber = deformattedPhoneNumber.replaceAll("-", "");
			deformattedPhoneNumber = deformattedPhoneNumber.replaceAll("[()]", "");
			//deformattedPhoneNumber = deformattedPhoneNumber.replaceAll("/)", "");
			
		} catch (Exception ex) {
			result = false;
			errorCode = TransportGlobalErrorMessageMap.EXCEPTION;
			errorMessage = ex.getMessage();
			responseParameters.put("result", result);
			responseParameters.put("errorCode", errorCode);
			responseParameters.put("errorMessage", errorMessage);
		}
	
		return deformattedPhoneNumber;
	}
}
