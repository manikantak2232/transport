package com.pixelbox.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pixelbox.exceptions.TransportException;
import com.pixelbox.service.ImageService;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class GpsTrackingDAO {

	final static Logger log = Logger.getLogger(LoginDAO.class);


	public static HashMap<String, Object> addLatLong(String lattitude, String longitude,String id
	) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

/*		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_LAT_LONG));
			callableStatement.setString("lattitude", lattitude);
			callableStatement.setString("longitude", longitude);
			callableStatement.setString("id", id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
/*			callableStatement.registerOutParameter("lat", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("lon", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("return_id", java.sql.Types.VARCHAR);*/

			rset = callableStatement.executeQuery();
			
			ArrayList<HashMap<String, Object>> locations = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				
				factory.put("lattitude", rset.getString("lattitude"));
				factory.put("longitude", rset.getString("longitude"));
				factory.put("id", rset.getString("id"));

				locations.add(factory);
			}
			responseParameters.put("locations", locations);

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", "record successfully inserted");
/*				responseParameters.put("lattitude", callableStatement.getString("lat"));
				responseParameters.put("longitude", callableStatement.getString("lon"));*/
				
		/*		HashMap<String, Object> location = new HashMap<String, Object>();
				location.put("lattitude", callableStatement.getString("lat"));
				location.put("longitude", callableStatement.getString("lon"));
				location.put("id", callableStatement.getString("return_id"));
			
				responseParameters.put("location", location);*/
				
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
/*	
	public static HashMap<String, Object> getLatLong()
			throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LAT_LONG));
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> gpsArray = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> location = new HashMap<String, Object>();

				location.put("lattitude", rset.getString("lattitude"));
				location.put("longitude", rset.getString("longitude"));
			
				gpsArray.add(location);
			}

			responseParameters.put("gpsArray", gpsArray);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}*/
	
	public static HashMap<String, Object> getLatLong() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LAT_LONG));
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);


			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

	//		ArrayList<HashMap<String, Object>> gpsArray = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> location = new HashMap<String, Object>();

				location.put("lattitude", rset.getString("lattitude"));
				location.put("longitude", rset.getString("longitude"));
			
	//			gpsArray.add(location);
				responseParameters.put("location", location);
			}

		//	responseParameters.put("gpsArray", gpsArray);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	

}



