package com.pixelbox.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.pixelbox.bl.RegistrationBL;

@Path("/registration")
public class RegistrationService {
	
	@POST
	@Path("/add/users")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addUserRegistration(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String user_name = requestParameters.get("user_name").toString();
		String email = requestParameters.get("email").toString();		
		String phone_number = requestParameters.get("phone_number").toString();
		String digest = requestParameters.get("digest").toString();
//		int lock_user = Integer.parseInt(requestParameters.get("lock_user").toString());
		String first_name = requestParameters.get("first_name").toString();		
		String middle_name = requestParameters.get("middle_name").toString();
		String last_name = requestParameters.get("last_name").toString();
		String gender = requestParameters.get("gender").toString();
		String manager_type = requestParameters.get("manager_type").toString();	
		responseParameters = RegistrationBL.addUserRegistration(user_name,email,phone_number,digest,
				first_name,middle_name,last_name,gender,manager_type
				);			
		return responseParameters;	
	}
	
	@POST
	@Path("/add/basic")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addRegistrationBasic(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String first_name = requestParameters.get("first_name").toString();		
		String middle_name = requestParameters.get("middle_name").toString();
		String last_name = requestParameters.get("last_name").toString();
		String gender = requestParameters.get("gender").toString();
		
		
		responseParameters = RegistrationBL.addRegistrationBasic(first_name,middle_name,last_name,gender
				);			
		return responseParameters;	
	}

}
