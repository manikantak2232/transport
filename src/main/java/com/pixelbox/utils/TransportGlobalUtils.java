package com.pixelbox.utils;

import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class TransportGlobalUtils {
	
	public static HashMap<String, Object> shiroUserDetails() {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
				
		boolean result = true;
		int errorCode = 0;
		String errorMessage = "";
	
		try {
			// get userName from user session
			final Subject currentUser = SecurityUtils.getSubject();
			
			String userName = (String) currentUser.getPrincipal();
			
			
	//		System.out.println(currentUser.hasRole("admin"));
	
			if (userName != null) {
				responseParameters.put("username", userName);
				responseParameters.put("result", result);
		//		responseParameters.put("roleType", currentUser.hasRole("admin"));
			} else {
				result = false;
				errorCode = TransportGlobalErrorMessageMap.NO_LOGGED_IN_USER_FOUND;
				errorMessage = TransportGlobalErrorMessageMap.getMessage(errorCode);				

				responseParameters.put("result", result);
				responseParameters.put("errorCode", errorCode);
				responseParameters.put("errorMessage", errorMessage);
				return responseParameters;
			}
		} catch (Exception ex) {
			result = false;
			errorCode = TransportGlobalErrorMessageMap.EXCEPTION;
			errorMessage = ex.getMessage();
			responseParameters.put("result", result);
			responseParameters.put("errorCode", errorCode);
			responseParameters.put("errorMessage", errorMessage);
	
			return responseParameters;
		}
	
		return responseParameters;
		
	}
}
