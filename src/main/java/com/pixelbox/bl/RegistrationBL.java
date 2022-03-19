package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.RegistrationDAO;

public class RegistrationBL {
	
	public static HashMap<String, Object> addUserRegistration(
			String user_name,
			String email,
			String phone_number,
			String digest,
	//		int lock_user,
			String first_name,
			String middle_name,
			String last_name,
			String gender,
			String manager_type
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = RegistrationDAO.addUserRegistration( 
					 user_name,
					 email,
					 phone_number,
					 digest,
	//				 lock_user,
					 first_name,
						middle_name,
						last_name,
						gender,
						manager_type
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addRegistrationBasic(
			String first_name,
			String middle_name,
			String last_name,
			String gender
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = RegistrationDAO.addRegistrationBasic( 
					
					first_name,
					middle_name,
					last_name,
					gender
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

}
