package com.pixelbox.service;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.pixelbox.bl.LoginBL;
import com.pixelbox.bl.SignupBL;

@Path("/signup")
public class SignupService {
	final static Logger log = Logger.getLogger(SignupService.class);
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> registerUser(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		responseParameters = SignupBL.registerUser(requestParameters);
		
		return responseParameters; 
	}
	
	@POST
	@Path("/activateWithOTP")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> activateUserWithOTP(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int otp = Integer.parseInt(requestParameters.get("otp").toString());
		String email = requestParameters.get("email").toString();
		responseParameters = SignupBL.activateUserWithOTP(otp, email);
		
		return responseParameters; 
	}
	
	
}

