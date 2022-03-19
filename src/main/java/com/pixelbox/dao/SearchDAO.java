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

public class SearchDAO {
	final static Logger log = Logger.getLogger(SearchDAO.class);

	public static HashMap<String, Object> searchUsers(String criterion,
													String param) throws TransportException, SQLException{

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}
		
		try {									
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.SEARCH_USERS));

			callableStatement.setString("criterion", criterion);
			callableStatement.setString("param", param);						
			callableStatement.setString("username", username);			

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
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
				List<Object> users = new ArrayList<Object>();
				while (rset.next()) {
					HashMap<String, Object> user = new HashMap<String, Object>();

					user.put("user_id", rset.getInt("pk_user_id"));
					user.put("first_name", rset.getString("first_name"));				
					user.put("middle_name", rset.getString("middle_name"));				
					user.put("last_name", rset.getString("last_name"));
					user.put("mobile", rset.getString("mobile"));
					user.put("email", rset.getString("email"));
							 
					users.add(user);
				}

				responseParameters.put("Users", users);
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
