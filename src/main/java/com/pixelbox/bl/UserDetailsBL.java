package com.pixelbox.bl;

import java.util.ArrayList;
import java.util.HashMap;

import com.pixelbox.dao.UserDetailsDAO;
import com.pixelbox.utils.TransportGlobalUtils;

public class UserDetailsBL {
	
	/*public static HashMap<String, Object> saveUserDetails(
										HashMap<String, Object> generalDetails, 
										HashMap<String, Object> basicDetails,
										HashMap<String, Object> personalContactDetails,
										HashMap<String, Object> personalSocialMediaDetails,
										ArrayList<HashMap<String, Object>> educationDetailsList,
										ArrayList<HashMap<String, Object>> employmentDetailsList,
										ArrayList<HashMap<String, Object>> businessDetailsList) {		
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = UserDetailsDAO.saveUserDetails(generalDetails, 
																basicDetails,
																personalContactDetails,
																personalSocialMediaDetails,
																educationDetailsList,
																employmentDetailsList,
																businessDetailsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	*/
	
	public static HashMap<String, Object> getUserDetails() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = UserDetailsDAO.getUserDetails();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateUserDetails(
			HashMap<String, Object> generalDetails, 
			HashMap<String, Object> basicDetails,
			HashMap<String, Object> personalContactDetails,
			HashMap<String, Object> personalSocialMediaDetails,
			ArrayList<HashMap<String, Object>> educationDetailsList,
			ArrayList<HashMap<String, Object>> employmentDetailsList,
			ArrayList<HashMap<String, Object>> businessDetailsList) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = UserDetailsDAO.updateUserDetails(generalDetails, 
									basicDetails,
									personalContactDetails,
									personalSocialMediaDetails,
									educationDetailsList,
									employmentDetailsList,
									businessDetailsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getUserDetailMissing(int pk_user_detail_missing_notification_id,
																int user_detail_owner_id,
																String user_detail_type,
																String user_detail_name,
																String missing_element	) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = UserDetailsDAO.getUserDetailMissing(pk_user_detail_missing_notification_id,
																user_detail_owner_id,
																user_detail_type,
																user_detail_name,
																missing_element);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
}
