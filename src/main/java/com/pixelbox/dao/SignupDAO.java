package com.pixelbox.dao;

import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pixelbox.exceptions.TransportException;
import com.pixelbox.utils.TransportCryptoUtils;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class SignupDAO {
	
	final static Logger log = Logger.getLogger(LoginDAO.class);

	public static HashMap<String, Object> registerUser(String username, String digest, String email, String mobile) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		String emailActivationHash = TransportCryptoUtils.getSha1HexDigest(digest);
		int otp = TransportCryptoUtils.getOtp(6);
		
		try {									
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.SIGNUP));

			callableStatement.setString("username", username);
			callableStatement.setString("digest", digest);
			callableStatement.setString("email_id", email);
			callableStatement.setString("mobile", mobile);
			callableStatement.setString("emailActivationHash", emailActivationHash);			
			callableStatement.setInt("otp", otp);			
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
			String message = callableStatement.getString("message");
			boolean outResult = true;
			if(message.equals("user with that email already exists")){
				outResult = false;
			}
			if(!outResult)
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("message", "signup successful" );
				responseParameters.put("emailActivationHash", emailActivationHash);
				responseParameters.put("otp", otp);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> activateUserWithOTP(int otp, String email) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		try {									
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ACTIVATE_USER_WITH_OTP));

			callableStatement.setString("email", email);		
			callableStatement.setInt("otp", otp);			
			
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
				responseParameters.put("message", "User Activated" );
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
