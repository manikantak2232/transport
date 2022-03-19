package com.pixelbox.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pixelbox.exceptions.TransportException;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;
import com.pixelbox.utils.TransportCryptoUtils;

public class LoginDAO {
	
	final static Logger log = Logger.getLogger(LoginDAO.class);

	public static HashMap<String, Object> getUserFullName(String username) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null; 
		ResultSet rset = null;
		String password;
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LOGGED_IN_USER_NAME));

			callableStatement.setString("username", username);
			//callableStatement.registerOutParameter("pw", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("fname", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("mname", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("lname", java.sql.Types.VARCHAR);
		//	callableStatement.registerOutParameter("manager_type", java.sql.Types.VARCHAR);
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
				//password = callableStatement.getString("pw");
				String user_details_added = "no";
				responseParameters.put("user_details_added", user_details_added);
				
				while(rset.next()){
					responseParameters.put("first_name", rset.getString("first_name"));
					responseParameters.put("middle_name", rset.getString("middle_name"));
					responseParameters.put("last_name", rset.getString("last_name"));
					responseParameters.put("manager_type", rset.getString("manager_type"));
					responseParameters.put("association_count", rset.getInt("@association_count"));
					responseParameters.put("association_id", rset.getInt("@association_id"));
					responseParameters.put("godown_name", rset.getString("godown_name"));
					responseParameters.put("association_name", rset.getString("association_name"));
					
					if(rset.getInt("@count") == 1){
						user_details_added = "yes";
					}				
					responseParameters.put("user_details_added", user_details_added);					
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
	
	// *********** change password : Set new password ************
	
		public static HashMap<String, Object> changePassword(String email,String digest, String old_Digest) throws TransportException, SQLException {
			HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");
			
			if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
				return responseParameters; 
		
			}
			
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			String errorMessage = "";
			responseParameters.put("result", true);
			CallableStatement callableStatement = null;
			ResultSet rset = null;
			
			try {									
				callableStatement = connection
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.CHANGE_PASSWORD));
				callableStatement.setString("email", email);
				callableStatement.setString("old_Digest", old_Digest);
				callableStatement.setString("digest", digest);
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
				String message = callableStatement.getString("message");
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else if(message.equals("Entered password is same.")){
					responseParameters.put("result", false);
					responseParameters.put("errorMessage", message+" Please try to enter another password that you haven't used.");
					return responseParameters; 
				}else if(message.equals("Old password doesnt match")){
					responseParameters.put("result", false);
					responseParameters.put("errorMessage", message);
					return responseParameters; 
				}
				else{
						responseParameters.put("email",email);
						responseParameters.put("message", "You have successfully changed your password." );
					
				}
			}catch(Exception e){
					e.printStackTrace();
			}
				
			finally {
					JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);			
			}
			
			return responseParameters;
		}
		
		public static HashMap<String, Object> linkForNewPassword(String email, String digest) throws TransportException, SQLException {
			HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			
			
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			String errorMessage = "";
			responseParameters.put("result", true);
			CallableStatement callableStatement = null;
			ResultSet rset = null;
			
			try {									
				callableStatement = connection
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.SET_LINK_FOR_NEW_PASSWORD));
				
				callableStatement.setString("email", email);
				callableStatement.setString("digest", digest);
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
				String message = callableStatement.getString("message");
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
					responseParameters.put("message", "record successfully updated" );
				}
			} catch(Exception e){
				e.printStackTrace();
			}

			finally {
				JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
			}

			return responseParameters;
		}
		
		public static HashMap<String, Object> sentEmailForForgotPassword(String email) throws TransportException, SQLException {
			HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			String errorMessage = "";
			responseParameters.put("result", true);
			CallableStatement callableStatement = null;
			ResultSet rset = null;
			int otp = TransportCryptoUtils.getOtp(6);
			
			try {									
				callableStatement = connection
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.SENT_EMAIL_FOR_FORGOT_PASSWORD));
				//callableStatement.setString("country_code", country_code);	
			     callableStatement.setString("email", email);			
			
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
				String message=callableStatement.getString("message");
				if(!callableStatement.getBoolean("outResult"))
				{
					responseParameters.put("result", false);
					responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					responseParameters.put("errorMessage", callableStatement.getString("message"));
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return responseParameters;
				}else if(message.equals("entered UserName doesnt exist")){
					responseParameters.put("message", "entered User Name doesn't Exists." );
					responseParameters.put("result", false);
					return responseParameters;
				}
			else{

						responseParameters.put("email", email);
				
				}
			}catch(Exception e){
					e.printStackTrace();
			}
				
			finally {
					JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);			
			}
			
			return responseParameters;
		}
		
		public static HashMap<String, Object> getLoginUserDetails() throws TransportException, SQLException {
			HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			String errorMessage = "";
			responseParameters.put("result", true);
			CallableStatement callableStatement = null;
			ResultSet rset = null;

			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}

			try {
				callableStatement = connection
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LOGIN_USER_DETAILS));
				// HashMap<String, Object> cards = new HashMap<String, Object>();
				callableStatement.setString("username", username);
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

				rset = callableStatement.executeQuery();

				// checking out param from stored procedure is null
				JDBCConnectionUtils.validateCallableStatement(callableStatement);
				responseParameters.put("result", true);

		//		ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

				while (rset.next()) {
					HashMap<String, Object> details = new HashMap<String, Object>();
					details.put("fk_user_id", rset.getInt("fk_user_id"));
					details.put("manager_type", rset.getString("manager_type"));
					details.put("association_id", rset.getInt("association_id"));
					details.put("association_name", rset.getString("association_name"));

					responseParameters.put("userDetails", details);
				}
				

			} finally {
				JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
			}

			return responseParameters;
		}
		
		
}
