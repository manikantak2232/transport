package com.pixelbox.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

//import com.pixelbox.bl.HomeBL;
import com.pixelbox.bl.UserDetailsBL;
import com.pixelbox.utils.TransportGlobalUtils;

@Path("/userDetails")
public class UserDetailsService {
	final static Logger log = Logger.getLogger(UserDetailsService.class);
	
	/*@POST
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> saveUserDetails(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String rps = requestParameters.toString();
				
		HashMap<String, Object> generalDetails = (HashMap<String, Object>) requestParameters.get("General Details");	
		HashMap<String, Object> basicDetails = (HashMap<String, Object>) requestParameters.get("Basic Details");	
		HashMap<String, Object> personalContactDetails = (HashMap<String, Object>) requestParameters.get("Personal Contact Details");	
		HashMap<String, Object> personalSocialMediaDetails = (HashMap<String, Object>) requestParameters.get("Personal Social Media Details");	
		
		ArrayList<HashMap<String, Object>> educationDetailsList = (ArrayList<HashMap<String, Object>>) requestParameters.get("Education Details");	
		ArrayList<HashMap<String, Object>> employmentDetailsList = (ArrayList<HashMap<String, Object>>) requestParameters.get("Employment Details");	
		ArrayList<HashMap<String, Object>> businessDetailsList = (ArrayList<HashMap<String, Object>>) requestParameters.get("Business Details");	
		
		if(generalDetails == null){
			responseParameters.put("result", false);
			responseParameters.put("errorMessage", "General Details Missing");
			return responseParameters;
			
		}
		if(basicDetails == null){
			responseParameters.put("result", false);
			responseParameters.put("errorMessage", "Basic Details Missing");	
			return responseParameters;
		}
		if(personalContactDetails == null){
			responseParameters.put("result", false);
			responseParameters.put("errorMessage", "Personal Contact Details Missing");		
			return responseParameters;
		}
		if(personalSocialMediaDetails == null){
			responseParameters.put("result", false);
			responseParameters.put("errorMessage", "Personal Social Media Details Missing");		
			return responseParameters;
		}
		
		responseParameters = UserDetailsBL.saveUserDetails(
				generalDetails,
				basicDetails,
				personalContactDetails,
				personalSocialMediaDetails,
				educationDetailsList,
				employmentDetailsList,
				businessDetailsList);
			
		return responseParameters;
	
	}*/
	
	@POST
	@Path("/all/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateUserDetails(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String rps = requestParameters.toString();
				
		HashMap<String, Object> generalDetails = (HashMap<String, Object>) requestParameters.get("General Details");	
		HashMap<String, Object> basicDetails = (HashMap<String, Object>) requestParameters.get("Basic Details");	
		HashMap<String, Object> personalContactDetails = (HashMap<String, Object>) requestParameters.get("Personal Contact Details");	
		HashMap<String, Object> personalSocialMediaDetails = (HashMap<String, Object>) requestParameters.get("Personal Social Media Details");	

		ArrayList<HashMap<String, Object>> educationDetailsList = (ArrayList<HashMap<String, Object>>) requestParameters.get("Education Details");	
		ArrayList<HashMap<String, Object>> employmentDetailsList = (ArrayList<HashMap<String, Object>>) requestParameters.get("Employment Details");	
		ArrayList<HashMap<String, Object>> businessDetailsList = (ArrayList<HashMap<String, Object>>) requestParameters.get("Business Details");	
		
		responseParameters = UserDetailsBL.updateUserDetails(
				generalDetails,
				basicDetails,
				personalContactDetails,
				personalSocialMediaDetails,
				educationDetailsList,
				employmentDetailsList,
				businessDetailsList);
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/all/view")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUserDetails() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
						
		HashMap<String, Object> generalDetails = null;	
		HashMap<String, Object> basicDetails = null;	
		HashMap<String, Object> personalContactDetails = null;
		HashMap<String, Object> personalSocialMediaDetails = null;
		
		ArrayList<HashMap<String, Object>> educationDetailsList = null;
		ArrayList<HashMap<String, Object>> employmentDetailsList = null;
		ArrayList<HashMap<String, Object>> businessDetailsList = null;
			
		responseParameters = UserDetailsBL.getUserDetails();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/showMissing")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUserDetailMissing(
									@QueryParam("pk_user_detail_missing_notification_id") int pk_user_detail_missing_notification_id,
									@QueryParam("user_detail_owner_id") int user_detail_owner_id,
									@QueryParam("user_detail_type") String user_detail_type,
									@QueryParam("user_detail_name") String user_detail_name,
									@QueryParam("missing_element") String missing_element			
									) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = UserDetailsBL.getUserDetailMissing(pk_user_detail_missing_notification_id,
																 user_detail_owner_id,
																 user_detail_type,
																 user_detail_name,
																 missing_element	
																);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/showMultipleMissing")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getMultipleUserDetailsMissing(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		 
		ArrayList<HashMap<String, Object>> userDetailsMissingList = (ArrayList<HashMap<String, Object>>) requestParameters.get("Education Details");	

			
		//responseParameters = UserDetailsBL.getMultipleUserDetailsMissing();
			
		return responseParameters;
	
	}
	
	
}
