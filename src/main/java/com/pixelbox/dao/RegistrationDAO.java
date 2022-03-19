package com.pixelbox.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pixelbox.exceptions.TransportException;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;

public class RegistrationDAO {
	
	final static Logger log = Logger.getLogger(StorageDAO.class);

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
			) throws TransportException, SQLException{

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		/*responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}*/
		try {									
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_REGISTRATION_USERS));

			callableStatement.setString("user_name", user_name);
			callableStatement.setString("email", email);			
			callableStatement.setString("phone_number", phone_number);
			callableStatement.setString("digest", digest);
//			callableStatement.setInt("lock_user", lock_user);
			callableStatement.setString("first_name", first_name);
			callableStatement.setString("middle_name", middle_name);			
			callableStatement.setString("last_name", last_name);
			callableStatement.setString("gender", gender);
			callableStatement.setString("manager_type", manager_type);
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
				responseParameters.put("errorMessage", callableStatement.getString("message") );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	
	public static HashMap<String, Object> addRegistrationBasic(
			String first_name,
			String middle_name,
			String last_name,
			String gender
			) throws TransportException, SQLException{

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		/*responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}*/
		
		try {									
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_REGISTRATION_BASIC));
								
			callableStatement.setString("first_name", first_name);
			callableStatement.setString("middle_name", middle_name);
			callableStatement.setString("last_name", last_name);
			callableStatement.setString("gender", gender);
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

}
