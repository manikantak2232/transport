package com.pixelbox.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.pixelbox.exceptions.TransportException;
import com.pixelbox.utils.TransportCryptoUtils;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class UserDetailsDAO {
	
	final static Logger log = Logger.getLogger(UserDetailsDAO.class);
	static String username;
	/*public static HashMap<String, Object> saveUserDetails(
											HashMap<String, Object> generalDetails, 
											HashMap<String, Object> basicDetails,
											HashMap<String, Object> personalContactDetails,
											HashMap<String, Object> personalSocialMediaDetails,
											ArrayList<HashMap<String, Object>> educationDetailsList,
											ArrayList<HashMap<String, Object>> employmentDetailsList,
											ArrayList<HashMap<String, Object>> businessDetailsList) throws HoopException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}
		
		responseParameters = insertBasicDetails(basicDetails);
		responseParameters = insertGeneralDetails(generalDetails); 
		responseParameters = insertPersonalContactDetails(personalContactDetails);
		responseParameters = insertPersonalSocialMediaDetails(personalSocialMediaDetails);
		
		if(educationDetailsList != null){
			responseParameters = insertEducationDetails(educationDetailsList);
		}
		if(employmentDetailsList != null){
			responseParameters = insertEmploymentDetails(employmentDetailsList);
		}
		if(businessDetailsList != null){
			responseParameters = insertBusinessDetails(businessDetailsList);
		}
		
		//*********** adding default cards start ***************************

		HashMap<String, Object> defaultPersonalCardDetails = new HashMap<String, Object>();
		defaultPersonalCardDetails.put("contact_no", "true");
		defaultPersonalCardDetails.put("email_id", "true");
		defaultPersonalCardDetails.put("address_line1", "true");
		defaultPersonalCardDetails.put("address_line2", "true");
		defaultPersonalCardDetails.put("city", "true");
		defaultPersonalCardDetails.put("state", "true");
		defaultPersonalCardDetails.put("country", "true");
		defaultPersonalCardDetails.put("zipcode", "true");
		
		responseParameters = OwnCardsDAO.addCard("Personal Card",
													"Personal",
													"-15256321",
													null,
													defaultPersonalCardDetails,
													null,
													null,
													null);
		
		if(employmentDetailsList != null){
			HashMap<String, Object> defaultEmploymentCardDetails = new HashMap<String, Object>();
			defaultEmploymentCardDetails.put("company_name", employmentDetailsList.get(0).get("company_name"));
			defaultEmploymentCardDetails.put("contact_no", "true");		
			defaultEmploymentCardDetails.put("email_id", "true");
			defaultEmploymentCardDetails.put("website", "true");
			defaultEmploymentCardDetails.put("address_line1", "true");
			defaultEmploymentCardDetails.put("address_line2", "true");
			defaultEmploymentCardDetails.put("city", "true");
			defaultEmploymentCardDetails.put("state", "true");
			defaultEmploymentCardDetails.put("country", "true");
			defaultEmploymentCardDetails.put("zipcode", "true");
			
			ArrayList<HashMap<String, Object>> defaultEmploymentDetailsList =
										new ArrayList<HashMap<String, Object>>();
			defaultEmploymentDetailsList.add(defaultEmploymentCardDetails);
			
			responseParameters = OwnCardsDAO.addCard("Work Card",
														"Work",
														" -1959937",
														basicDetails.get("profession").toString(),
														defaultPersonalCardDetails,
														defaultEmploymentDetailsList,
														null,
														null);
		}else{
			if(businessDetailsList != null){
				HashMap<String, Object> defaultBusinessCardDetails = new HashMap<String, Object>();
				defaultBusinessCardDetails.put("business_name", businessDetailsList.get(0).get("business_name"));
				defaultBusinessCardDetails.put("contact_no", "true");		
				defaultBusinessCardDetails.put("email_id", "true");
				defaultBusinessCardDetails.put("website", "true");
				defaultBusinessCardDetails.put("address_line1", "true");
				defaultBusinessCardDetails.put("address_line2", "true");
				defaultBusinessCardDetails.put("city", "true");
				defaultBusinessCardDetails.put("state", "true");
				defaultBusinessCardDetails.put("country", "true");
				defaultBusinessCardDetails.put("zipcode", "true");
				
				ArrayList<HashMap<String, Object>> defaultBusinessDetailsList =
											new ArrayList<HashMap<String, Object>>();
				defaultBusinessDetailsList.add(defaultBusinessCardDetails);
				
				responseParameters = OwnCardsDAO.addCard("Business Card",
															"Business",
															"-65472",
															basicDetails.get("profession").toString(),
															defaultPersonalCardDetails,
															null,
															defaultBusinessDetailsList,
															null);
			}
		}
		//************ adding default cards end  ***************************
		return responseParameters;
	}*/
	
	public static HashMap<String, Object> insertGeneralDetails(HashMap<String, Object> generalDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String student = generalDetails.get("student").toString();
			String doing_multiple_courses = generalDetails.get("doing_multiple_courses").toString();
			String employee = generalDetails.get("employee").toString();
			String working_for_multiple_companies = generalDetails.get("working_for_multiple_companies").toString();
			String business = generalDetails.get("business").toString();
			String holding_multiple_businesses = generalDetails.get("holding_multiple_businesses").toString();
			String unemployed = generalDetails.get("holding_multiple_businesses").toString();
			String part_time_freelancer = generalDetails.get("part_time_freelancer").toString();
			String full_time_freelancer = generalDetails.get("full_time_freelancer").toString();
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.USER_DETAILS_GENERAL));
	
			callableStatement.setBoolean("student", Boolean.parseBoolean(student));
			callableStatement.setBoolean("doing_multiple_courses", Boolean.parseBoolean(doing_multiple_courses));
			callableStatement.setBoolean("employee",  Boolean.parseBoolean(employee));
			callableStatement.setBoolean("working_for_multiple_companies",  Boolean.parseBoolean(working_for_multiple_companies));
			callableStatement.setBoolean("business",  Boolean.parseBoolean(business));
			callableStatement.setBoolean("holding_multiple_businesses",  Boolean.parseBoolean(holding_multiple_businesses));
			callableStatement.setBoolean("unemployed",  Boolean.parseBoolean(unemployed));
			callableStatement.setBoolean("part_time_freelancer",  Boolean.parseBoolean(part_time_freelancer));
			callableStatement.setBoolean("full_time_freelancer",  Boolean.parseBoolean(full_time_freelancer));
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("errorMessage", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> insertBasicDetails(HashMap<String, Object> basicDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String prefix = basicDetails.get("prefix").toString();
			String first_name = basicDetails.get("first_name").toString();
			String middle_name = basicDetails.get("middle_name").toString();
			String last_name = basicDetails.get("last_name").toString();
			String gender = basicDetails.get("gender").toString();
			String dob = basicDetails.get("dob").toString();
			String profession = basicDetails.get("profession").toString();			
			String image_url = basicDetails.get("image_url").toString();
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.USER_DETAILS_BASIC));
	
			callableStatement.setString("prefix", prefix);
			callableStatement.setString("first_name", first_name);
			callableStatement.setString("middle_name", middle_name);
			callableStatement.setString("last_name", last_name);
			callableStatement.setString("gender", gender);
			callableStatement.setString("dob", dob);
			callableStatement.setString("profession", profession);			
			callableStatement.setString("image_url", image_url);
			
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("errorMessage", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> insertPersonalContactDetails(HashMap<String, Object> personalContactDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String mobile_phone = personalContactDetails.get("mobile_phone").toString();
			String home_telephone = personalContactDetails.get("home_telephone").toString();
			String personal_email_id = personalContactDetails.get("personal_email_id").toString();
 			String address_line1 = personalContactDetails.get("address_line1").toString();
			String address_line2 = personalContactDetails.get("address_line2").toString();
			String city = personalContactDetails.get("city").toString();
			String state = personalContactDetails.get("state").toString();
			String country = personalContactDetails.get("country").toString();
			String zipcode = personalContactDetails.get("zipcode").toString();
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.USER_DETAILS_PERSONAL_CONTACT));
	
			callableStatement.setString("mobile_phone", mobile_phone);
			callableStatement.setString("home_telephone", home_telephone);
			callableStatement.setString("personal_email_id",  personal_email_id);
			callableStatement.setString("address_line1",  address_line1);
			callableStatement.setString("address_line2",  address_line2);
			callableStatement.setString("city",  city);
			callableStatement.setString("state",  state);
			callableStatement.setString("country",  country);
			callableStatement.setString("zipcode",  zipcode);
			
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("errorMessage", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> insertPersonalSocialMediaDetails(HashMap<String, Object> personalSocialMediaDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
            String personal_social_media_account_id;
            			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.USER_DETAILS_PERSONAL_SOCIAL_MEDIA));
					
			for(String personal_social_media_name :personalSocialMediaDetails.keySet()){
				personal_social_media_account_id = (personalSocialMediaDetails.get(personal_social_media_name)).toString();
								
				callableStatement.setString("personal_social_media_name_param", personal_social_media_name);
				callableStatement.setString("personal_social_media_account_id_param", personal_social_media_account_id);
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}

	
	public static HashMap<String, Object> insertEducationDetails(ArrayList<HashMap<String, Object>> educationDetailsList) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String education_level;
			String institute;
			String course;
			String year;
			String address_line1;
			String address_line2;
			String city;
			String state;
			String country;
			String zipcode;
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.USER_DETAILS_EDUCATION));
	
			for(HashMap<String, Object> educationDetails: educationDetailsList){
				
				education_level = educationDetails.get("education_level").toString();
				institute = educationDetails.get("institute").toString();
				course = educationDetails.get("course").toString();
				year = educationDetails.get("year").toString();
				address_line1 = educationDetails.get("address_line1").toString();
				address_line2 = educationDetails.get("address_line2").toString();
				city = educationDetails.get("city").toString();
				state = educationDetails.get("state").toString();
				country = educationDetails.get("country").toString();
				zipcode = educationDetails.get("zipcode").toString();
				
				callableStatement.setString("education_level", education_level);
				callableStatement.setString("institute", institute);
				callableStatement.setString("course",  course);
				callableStatement.setString("year", year);
				callableStatement.setString("address_line1", address_line1);
				callableStatement.setString("address_line2", address_line2);
				callableStatement.setString("city", city);
				callableStatement.setString("state", state);
				callableStatement.setString("country", country);
				callableStatement.setString("zipcode", zipcode);
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> insertEmploymentDetails(ArrayList<HashMap<String, Object>> employmentDetailsList) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
            String company_name;
            String company_type;
            //String profession;
            String designation;
            String phone;
            String email_id;
            String website;
			String address_line1;
			String address_line2;
			String city;
			String state;
			String country;
			String zipcode;
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.USER_DETAILS_EMPLOYMENT));
	
			for(HashMap<String, Object> employmentDetails: employmentDetailsList){
				
				company_name = employmentDetails.get("company_name").toString();
				company_type = employmentDetails.get("company_type").toString();
				//profession = employmentDetails.get("profession").toString();
				designation = employmentDetails.get("designation").toString();				
				phone = employmentDetails.get("phone").toString();
				email_id = employmentDetails.get("email_id").toString();
				website = employmentDetails.get("website").toString();
				address_line1 = employmentDetails.get("address_line1").toString();
				address_line2 = employmentDetails.get("address_line2").toString();
				city = employmentDetails.get("city").toString();
				state = employmentDetails.get("state").toString();
				country = employmentDetails.get("country").toString();
				zipcode = employmentDetails.get("zipcode").toString();
				
				callableStatement.setString("company_name", company_name);
				callableStatement.setString("company_type", company_type);
				//callableStatement.setString("profession",  profession);
				callableStatement.setString("designation",  designation);
				callableStatement.setString("phone", phone);
				callableStatement.setString("email_id", email_id);
				callableStatement.setString("website", website);
				callableStatement.setString("address_line1", address_line1);
				callableStatement.setString("address_line2", address_line2);
				callableStatement.setString("city", city);
				callableStatement.setString("state", state);
				callableStatement.setString("country", country);
				callableStatement.setString("zipcode", zipcode);
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> insertBusinessDetails(ArrayList<HashMap<String, Object>> businessDetailsList) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
            String business_name;
            String business_nature;
            String phone;
            String email_id;
            String website;
            String address_line1;
            String address_line2;
            String city;
            String state;
            String country;
            String zipcode;
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.USER_DETAILS_BUSINESS));
	
			for(HashMap<String, Object> businessDetails: businessDetailsList){
				
				business_name = businessDetails.get("business_name").toString();
				business_nature = businessDetails.get("business_nature").toString();
				phone = businessDetails.get("phone").toString();
				email_id = businessDetails.get("email_id").toString();
				website = businessDetails.get("website").toString();
				address_line1 = businessDetails.get("address_line1").toString();
				address_line2 = businessDetails.get("address_line2").toString();
				city = businessDetails.get("city").toString();
				state = businessDetails.get("state").toString();
				country = businessDetails.get("country").toString();
				zipcode = businessDetails.get("zipcode").toString();
				
				callableStatement.setString("business_name", business_name);
				callableStatement.setString("business_nature", business_nature);
				callableStatement.setString("phone", phone);
				callableStatement.setString("email_id", email_id);
				callableStatement.setString("website", website);
				callableStatement.setString("address_line1", address_line1);
				callableStatement.setString("address_line2", address_line2);
				callableStatement.setString("city", city);
				callableStatement.setString("state", state);
				callableStatement.setString("country", country);
				callableStatement.setString("zipcode", zipcode);				
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	
	//*******************view methods *************************************************
	
	public static HashMap<String, Object> getUserDetails() throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		HashMap<String, Object> basicDetails = new HashMap<String, Object>();
		HashMap<String, Object> generalDetails = new HashMap<String, Object>();
		HashMap<String, Object> personalContactDetails = new HashMap<String, Object>();
		HashMap<String, Object> personalSocialMediaDetails = new HashMap<String, Object>();
		
		ArrayList<HashMap<String, Object>> educationDetailsList = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> employmentDetailsList = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> businessDetailsList = new ArrayList<HashMap<String, Object>>();
		
		responseParameters = TransportGlobalUtils.shiroUserDetails();
		username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}
		
		basicDetails = getBasicDetails();
		generalDetails = getGeneralDetails();
		personalContactDetails = getPersonalContactDetails();
		personalSocialMediaDetails = getPersonalSocialMediaDetails();
		educationDetailsList = getEducationDetailsList();
		employmentDetailsList = getEmploymentDetailsList();
		businessDetailsList = getBusinessDetailsList();
		
		responseParameters.put("Basic Details", basicDetails);
		responseParameters.put("General Details", generalDetails);
		responseParameters.put("Personal Contact Details", personalContactDetails);
		responseParameters.put("Personal Social Media Details", personalSocialMediaDetails);
		responseParameters.put("Education Details List", educationDetailsList);
		responseParameters.put("Employment Details List", employmentDetailsList);
		responseParameters.put("Business Details List", businessDetailsList);
			
		return responseParameters;
	}

	public static HashMap<String, Object> getBasicDetails() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		HashMap<String, Object> basicDetails = new HashMap<String, Object>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_BASIC));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{			
				while(rset.next()){
					basicDetails.put("prefix", rset.getString("prefix"));
					basicDetails.put("first_name", rset.getString("first_name"));
					basicDetails.put("middle_name", rset.getString("middle_name"));
					basicDetails.put("last_name", rset.getString("last_name"));
					basicDetails.put("gender", rset.getString("@gender"));
					basicDetails.put("dob", rset.getString("dob"));	
					basicDetails.put("profession", rset.getString("profession"));						
					basicDetails.put("image_url", rset.getString("image_url"));						
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return basicDetails;
	}
	
	public static HashMap<String, Object> getGeneralDetails() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		HashMap<String, Object> generalDetails = new HashMap<String, Object>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_GENERAL));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{			
				while(rset.next()){
					generalDetails.put("student", rset.getBoolean("student"));
					generalDetails.put("doing_multiple_courses", rset.getBoolean("doing_multiple_courses"));
					generalDetails.put("employee", rset.getBoolean("employee"));
					generalDetails.put("working_for_multiple_companies", rset.getBoolean("working_for_multiple_companies"));
					generalDetails.put("business", rset.getBoolean("business"));
					generalDetails.put("holding_multiple_businesses", rset.getBoolean("holding_multiple_businesses"));		
					generalDetails.put("unemployed", rset.getBoolean("unemployed"));		
					generalDetails.put("holding_multiple_businesses", rset.getBoolean("holding_multiple_businesses"));		
					generalDetails.put("part_time_freelancer", rset.getBoolean("part_time_freelancer"));		
					generalDetails.put("full_time_freelancer", rset.getBoolean("full_time_freelancer"));		
					
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return generalDetails;
	}
	
	public static HashMap<String, Object> getPersonalContactDetails() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		HashMap<String, Object> personalContactDetails = new HashMap<String, Object>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_PERSONAL_CONTACT));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{			
				while(rset.next()){
					personalContactDetails.put("mobile_no", rset.getString("mobile_no"));
					personalContactDetails.put("home_telephone_no", rset.getString("home_telephone_no"));
					personalContactDetails.put("personal_email_id", rset.getString("personal_email_id"));
					personalContactDetails.put("address_line1", rset.getString("address_line1"));
					personalContactDetails.put("address_line2", rset.getString("address_line2"));
					personalContactDetails.put("city", rset.getString("city"));
					personalContactDetails.put("state", rset.getString("state"));
					personalContactDetails.put("country", rset.getString("country"));
					personalContactDetails.put("zipcode", rset.getString("zipcode"));					
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return personalContactDetails;
	}
	
	public static HashMap<String, Object> getPersonalSocialMediaDetails() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		HashMap<String, Object> personalSocialMediaDetails = new HashMap<String, Object>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_PERSONAL_SOCIAL_MEDIA));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{			
				while(rset.next()){
					//personalSocialMediaDetails.put("social_media_name", rset.getString("social_media_name"));
					personalSocialMediaDetails.put(rset.getString("social_media_name"), rset.getString("social_media_account_id"));	
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return personalSocialMediaDetails;
	}
	
	
	public static ArrayList<HashMap<String, Object>> getEducationDetailsList() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		ArrayList<HashMap<String, Object>> educationDetailsList = new ArrayList<HashMap<String, Object>>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_EDUCATION));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				//return responseParameters;
			}else{			
				while(rset.next()){
					HashMap<String, Object> educationDetails = new HashMap<String, Object>();

					educationDetails.put("education_level", rset.getString("education_level"));
					educationDetails.put("institute_name", rset.getString("institute_name"));
					educationDetails.put("course_name", rset.getString("course_name"));
					educationDetails.put("year", rset.getString("year"));
					educationDetails.put("address_line1", rset.getString("address_line1"));					
					educationDetails.put("address_line2", rset.getString("address_line2"));					
					educationDetails.put("city", rset.getString("city"));					
					educationDetails.put("state", rset.getString("state"));					
					educationDetails.put("country", rset.getString("country"));					
					educationDetails.put("zipcode", rset.getString("zipcode"));
					
					educationDetailsList.add(educationDetails);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return educationDetailsList;
	}
	
	public static ArrayList<HashMap<String, Object>> getEmploymentDetailsList() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		ArrayList<HashMap<String, Object>> employmentDetailsList = new ArrayList<HashMap<String, Object>>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_EMPLOYMENT));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				//return responseParameters;
			}else{			
				while(rset.next()){
					HashMap<String, Object> employmentDetails = new HashMap<String, Object>();

					employmentDetails.put("company_name", rset.getString("company_name"));
					employmentDetails.put("company_type", rset.getString("company_type"));
					//employmentDetails.put("profession", rset.getString("profession"));
					employmentDetails.put("designation", rset.getString("designation"));					
					employmentDetails.put("work_phone_no", rset.getString("work_phone_no"));					
					employmentDetails.put("work_email_id", rset.getString("work_email_id"));				
					employmentDetails.put("company_website", rset.getString("company_website"));				
					employmentDetails.put("address_line1", rset.getString("address_line1"));					
					employmentDetails.put("address_line2", rset.getString("address_line2"));					
					employmentDetails.put("city", rset.getString("city"));					
					employmentDetails.put("state", rset.getString("state"));					
					employmentDetails.put("country", rset.getString("country"));					
					employmentDetails.put("zipcode", rset.getString("zipcode"));					
																				
					employmentDetailsList.add(employmentDetails);

				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return employmentDetailsList;
	}
	
	public static ArrayList<HashMap<String, Object>> getBusinessDetailsList() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		ArrayList<HashMap<String, Object>> businessDetailsList = new ArrayList<HashMap<String, Object>>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_BUSINESS));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				//return responseParameters;
			}else{			
				while(rset.next()){
					HashMap<String, Object> businessDetails = new HashMap<String, Object>();

					businessDetails.put("business_name", rset.getString("business_name"));
					businessDetails.put("business_nature", rset.getString("business_nature"));
					businessDetails.put("business_phone_no", rset.getString("business_phone_no"));
					businessDetails.put("business_email_id", rset.getString("business_email_id"));					
					businessDetails.put("business_website", rset.getString("business_website"));				
					businessDetails.put("address_line1", rset.getString("address_line1"));					
					businessDetails.put("address_line2", rset.getString("address_line2"));					
					businessDetails.put("city", rset.getString("city"));					
					businessDetails.put("state", rset.getString("state"));					
					businessDetails.put("country", rset.getString("country"));					
					businessDetails.put("zipcode", rset.getString("zipcode"));					
																				
					businessDetailsList.add(businessDetails);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return businessDetailsList;
	}
	
	//******************************** update methods ************************************************
	
	public static HashMap<String, Object> updateUserDetails(
			HashMap<String, Object> generalDetails, 
			HashMap<String, Object> basicDetails,
			HashMap<String, Object> personalContactDetails,
			HashMap<String, Object> personalSocialMediaDetails,			
			ArrayList<HashMap<String, Object>> educationDetailsList,
			ArrayList<HashMap<String, Object>> employmentDetailsList,
			ArrayList<HashMap<String, Object>> businessDetailsList) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		responseParameters = TransportGlobalUtils.shiroUserDetails();
		username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}
		
		String updateItems = "";
		
		if(basicDetails != null){
			responseParameters = updateBasicDetails(basicDetails);
			updateItems = updateItems + "Basic Details, ";
		}
		if(generalDetails != null){
			responseParameters = updateGeneralDetails(generalDetails); 
			updateItems = updateItems + "General Details, ";
		}
		if(personalContactDetails != null){
			responseParameters = updatePersonalContactDetails(personalContactDetails);
			updateItems = updateItems + "Personal Contact Details, ";
		}
		if(personalSocialMediaDetails != null){
			responseParameters = updatePersonalSocialMediaDetails(personalSocialMediaDetails);	
			updateItems = updateItems + "Personal Social Media Details, ";
		}
		
		if(educationDetailsList != null){
			responseParameters = updateEducationDetails(educationDetailsList);
			updateItems = updateItems + "Education Details, ";
		}
		
		if(employmentDetailsList != null){
			responseParameters = updateEmploymentDetails(employmentDetailsList);
			updateItems = updateItems + "Employment Details, ";
		}
		
		if(businessDetailsList != null){
			responseParameters = updateBusinessDetails(businessDetailsList);
			updateItems = updateItems + "Business Details, ";
		}
		
		responseParameters = getConnectedUsers();
		
		responseParameters = addUpdateNotifications((ArrayList<Integer>)responseParameters.get("Connected Users"), updateItems);
				
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> updateGeneralDetails(HashMap<String, Object> generalDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String student = generalDetails.get("student").toString();
			String doing_multiple_courses = generalDetails.get("doing_multiple_courses").toString();
			String employee = generalDetails.get("employee").toString();
			String working_for_multiple_companies = generalDetails.get("working_for_multiple_companies").toString();
			String business = generalDetails.get("business").toString();
			String holding_multiple_businesses = generalDetails.get("holding_multiple_businesses").toString();
			String unemployed = generalDetails.get("holding_multiple_businesses").toString();
			String part_time_freelancer = generalDetails.get("part_time_freelancer").toString();
			String full_time_freelancer = generalDetails.get("full_time_freelancer").toString();
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_USER_DETAILS_GENERAL));
	
			callableStatement.setBoolean("student", Boolean.parseBoolean(student));
			callableStatement.setBoolean("doing_multiple_courses", Boolean.parseBoolean(doing_multiple_courses));
			callableStatement.setBoolean("employee",  Boolean.parseBoolean(employee));
			callableStatement.setBoolean("working_for_multiple_companies",  Boolean.parseBoolean(working_for_multiple_companies));
			callableStatement.setBoolean("business",  Boolean.parseBoolean(business));
			callableStatement.setBoolean("holding_multiple_businesses",  Boolean.parseBoolean(holding_multiple_businesses));
			callableStatement.setBoolean("unemployed",  Boolean.parseBoolean(unemployed));
			callableStatement.setBoolean("part_time_freelancer",  Boolean.parseBoolean(part_time_freelancer));
			callableStatement.setBoolean("full_time_freelancer",  Boolean.parseBoolean(full_time_freelancer));
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("errorMessage", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> updateBasicDetails(HashMap<String, Object> basicDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String prefix = basicDetails.get("prefix").toString();
			String first_name = basicDetails.get("first_name").toString();
			String middle_name = basicDetails.get("middle_name").toString();
			String last_name = basicDetails.get("last_name").toString();
			String gender = basicDetails.get("gender").toString();
			String dob = basicDetails.get("dob").toString();
			String profession = basicDetails.get("profession").toString();
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_USER_DETAILS_BASIC));
	
			callableStatement.setString("prefix", prefix);
			callableStatement.setString("first_name", first_name);
			callableStatement.setString("middle_name", middle_name);
			callableStatement.setString("last_name", last_name);
			callableStatement.setString("gender", gender);
			callableStatement.setString("dob", dob);
			callableStatement.setString("profession", profession);			
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("errorMessage", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> updatePersonalContactDetails(HashMap<String, Object> personalContactDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String mobile_phone = personalContactDetails.get("mobile_phone").toString();
			String home_telephone = personalContactDetails.get("home_telephone").toString();
			String personal_email_id = personalContactDetails.get("personal_email_id").toString();
			String address_line1 = personalContactDetails.get("address_line1").toString();
			String address_line2 = personalContactDetails.get("address_line2").toString();
			String city = personalContactDetails.get("city").toString();
			String state = personalContactDetails.get("state").toString();
			String country = personalContactDetails.get("country").toString();
			String zipcode = personalContactDetails.get("zipcode").toString();
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_USER_DETAILS_PERSONAL_CONTACT));
	
			callableStatement.setString("mobile_phone", mobile_phone);
			callableStatement.setString("home_telephone", home_telephone);
			callableStatement.setString("personal_email_id",  personal_email_id);
			callableStatement.setString("address_line1",  address_line1);
			callableStatement.setString("address_line2",  address_line2);
			callableStatement.setString("city",  city);
			callableStatement.setString("state",  state);
			callableStatement.setString("country",  country);
			callableStatement.setString("zipcode",  zipcode);
						
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("errorMessage", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> updatePersonalSocialMediaDetails(HashMap<String, Object> personalSocialMediaDetails) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
            String personal_social_media_account_id;
            			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_USER_DETAILS_PERSONAL_SOCIAL_MEDIA));
					
			for(String personal_social_media_name :personalSocialMediaDetails.keySet()){
				personal_social_media_account_id = (personalSocialMediaDetails.get(personal_social_media_name)).toString();
								
				callableStatement.setString("personal_social_media_name_param", personal_social_media_name);
				callableStatement.setString("personal_social_media_account_id_param", personal_social_media_account_id);
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}

	public static HashMap<String, Object> updateEducationDetails(ArrayList<HashMap<String, Object>> educationDetailsList) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
			String education_level;
			String institute;
			String course;
			String year;
            String address_line1;
            String address_line2;
            String city;
            String state;
            String country;
            String zipcode;
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_USER_DETAILS_EDUCATION));
	
			for(HashMap<String, Object> educationDetails: educationDetailsList){
				
				education_level = educationDetails.get("education_level").toString();
				institute = educationDetails.get("institute").toString();
				course = educationDetails.get("course").toString();
				year = educationDetails.get("year").toString();
				address_line1 = educationDetails.get("address_line1").toString();
				address_line2 = educationDetails.get("address_line2").toString();
				city = educationDetails.get("city").toString();
				state = educationDetails.get("state").toString();
				country = educationDetails.get("country").toString();
				zipcode = educationDetails.get("zipcode").toString();
				
				callableStatement.setString("education_level", education_level);
				callableStatement.setString("institute", institute);
				callableStatement.setString("course",  course);
				callableStatement.setString("year", year);
				callableStatement.setString("address_line1", address_line1);
				callableStatement.setString("address_line2", address_line2);
				callableStatement.setString("city", city);
				callableStatement.setString("state", state);
				callableStatement.setString("country", country);
				callableStatement.setString("zipcode", zipcode);
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateEmploymentDetails(ArrayList<HashMap<String, Object>> employmentDetailsList) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
            String company_name;
            String company_type;
            //String profession;
            String designation;            
            String phone;
            String email_id;
            String website;
            String address_line1;
            String address_line2;
            String city;
            String state;
            String country;
            String zipcode;
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_USER_DETAILS_EMPLOYMENT));
	
			for(HashMap<String, Object> employmentDetails: employmentDetailsList){
				
				company_name = employmentDetails.get("company_name").toString();
				company_type = employmentDetails.get("company_type").toString();
				//profession = employmentDetails.get("profession").toString();
				designation = employmentDetails.get("designation").toString();				
				phone = employmentDetails.get("phone").toString();
				email_id = employmentDetails.get("email_id").toString();
				website = employmentDetails.get("website").toString();
				address_line1 = employmentDetails.get("address_line1").toString();
				address_line2 = employmentDetails.get("address_line2").toString();
				city = employmentDetails.get("city").toString();
				state = employmentDetails.get("state").toString();
				country = employmentDetails.get("country").toString();
				zipcode = employmentDetails.get("zipcode").toString();
				
				callableStatement.setString("company_name_param", company_name);
				callableStatement.setString("company_type", company_type);
				//callableStatement.setString("profession",  profession);
				callableStatement.setString("designation",  designation);				
				callableStatement.setString("phone", phone);
				callableStatement.setString("email_id", email_id);
				callableStatement.setString("website", website);
				callableStatement.setString("address_line1", address_line1);
				callableStatement.setString("address_line2", address_line2);
				callableStatement.setString("city", city);
				callableStatement.setString("state", state);
				callableStatement.setString("country", country);
				callableStatement.setString("zipcode", zipcode);
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateBusinessDetails(ArrayList<HashMap<String, Object>> businessDetailsList) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
            String business_name;
            String business_nature;
            String phone;
            String email_id;
            String website;
            String address_line1;
            String address_line2;
            String city;
            String state;
            String country;
            String zipcode;
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_USER_DETAILS_BUSINESS));
	
			for(HashMap<String, Object> businessDetails: businessDetailsList){
				
				business_name = businessDetails.get("business_name").toString();
				business_nature = businessDetails.get("business_nature").toString();
				phone = businessDetails.get("phone").toString();
				email_id = businessDetails.get("email_id").toString();
				website = businessDetails.get("website").toString();
				address_line1 = businessDetails.get("address_line1").toString();
				address_line2 = businessDetails.get("address_line2").toString();
				city = businessDetails.get("city").toString();
				state = businessDetails.get("state").toString();
				country = businessDetails.get("country").toString();
				zipcode = businessDetails.get("zipcode").toString();
				
				callableStatement.setString("business_name_param", business_name);
				callableStatement.setString("business_nature", business_nature);
				callableStatement.setString("phone", phone);
				callableStatement.setString("email_id", email_id);
				callableStatement.setString("website", website);
				callableStatement.setString("address_line1", address_line1);
				callableStatement.setString("address_line2", address_line2);
				callableStatement.setString("city", city);
				callableStatement.setString("state", state);
				callableStatement.setString("country", country);
				callableStatement.setString("zipcode", zipcode);
				
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
	
		return responseParameters;
	}
	
	public static HashMap<String, Object> getConnectedUsers() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		ArrayList<Integer> connectedUsers = new ArrayList<Integer>();

		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_CONNECTED_USERS));
	
			callableStatement.setString("username", username);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{			
				while(rset.next()){
					connectedUsers.add(rset.getInt("user_id"));					
				}	
				
				responseParameters.put("Connected Users", connectedUsers);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}		
				
		return responseParameters;
	}
	
	public static HashMap<String, Object> addUpdateNotifications(
														ArrayList<Integer> connectedUsers, 
														String updateItems) throws TransportException, SQLException {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		try {									
			
            int connected_user_id;
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_USER_DETAILS_UPDATE_NOTIFICATIONS));
	
			updateItems = updateItems.substring(0, updateItems.length()-2);

			for(int connectedUser: connectedUsers){
				
				connected_user_id = connectedUser;
				
				callableStatement.setInt("connected_user_id", connected_user_id);
				
				
				callableStatement.setString("update_items", updateItems);
				callableStatement.setString("username", username);
				
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
		
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else{				
					responseParameters.put("errorMessage", "record successfully inserted" );
				}
			
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
		
		return responseParameters;
	}
		
	public static HashMap<String, Object> getUserDetailMissing(int pk_user_detail_missing_notification_id,
																int user_detail_owner_id,
																String user_detail_type,
																String user_detail_name,
																String missing_element) throws TransportException, SQLException {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		HashMap<String, Object> userDetailMising = new HashMap<String, Object>();
		String missingElementValue = "";
		
		try {									
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_DETAILS_MISSING));
	
			callableStatement.setInt("pk_user_detail_missing_notification_id_param", pk_user_detail_missing_notification_id);
			callableStatement.setInt("user_detail_owner_id", user_detail_owner_id);
			callableStatement.setString("user_detail_type", user_detail_type);
			callableStatement.setString("user_detail_name", user_detail_name);
			callableStatement.setString("missing_element", missing_element);
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
	
			if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{			
				while(rset.next()){
					missingElementValue = rset.getString("@missing_element");				
				}	
				
				responseParameters.put("User Detail Missing", missingElementValue);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}		
				
		return responseParameters;
	}
	
}
