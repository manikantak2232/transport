package com.pixelbox.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pixelbox.exceptions.TransportException;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class LocationDAO {

	final static Logger log = Logger.getLogger(LoginDAO.class);

	public static HashMap<String, Object> addLocationAllotment(int fk_driver_id, int fk_truck_id,
			String present_location, String new_location, String date) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_LOCATION_ALLOTMENT));

			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("present_location", present_location);
			callableStatement.setString("new_location", new_location);
			callableStatement.setString("date", date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addLocationStatus(String status_of_truck, int fk_truck_id,
			String present_location, String date) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_LOCATION_STATUS));

			callableStatement.setString("status_of_truck", status_of_truck);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("present_location", present_location);
			callableStatement.setString("date", date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	// -----------------------------------------------------get--------------------------------------------------//
	public static HashMap<String, Object> getLocationAllotment(int fk_truck_id)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LOCATION_ALLOTMENT));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> locations = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> location = new HashMap<String, Object>();
				location.put("location_allotment_id", rset.getInt("pk_location_allotment_id"));
				location.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				location.put("driver_name", fname + " " + mname + " " + lname);
				location.put("fk_truck_id", rset.getString("fk_truck_id"));
				location.put("present_location", rset.getString("present_location"));
				location.put("new_location", rset.getString("new_location"));
				location.put("date", rset.getString("date"));
				responseParameters.put("Locations", location);

			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getLocationStatus(int fk_truck_id) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LOCATION_STATUS));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> locations = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> location = new HashMap<String, Object>();
				location.put("location_status_id", rset.getString("pk_location_status_id"));
				location.put("status_of_truck", rset.getString("status_of_truck"));
				location.put("fk_truck_id", rset.getString("fk_truck_id"));
				location.put("present_location", rset.getString("present_location"));
				location.put("date", rset.getString("date"));
				// locations.add(location);//
				responseParameters.put("Locations", location);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getLocationAllotmentByDate(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}
		
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LOCATION_ALLOTMENT_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> states = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> state = new HashMap<String, Object>();
				state.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				state.put("driver_name", fname + " " + mname + " " + lname);
				state.put("fk_truck_id", rset.getInt("fk_truck_id"));
				state.put("truck_number", rset.getString("truck_number"));
				state.put("present_location", rset.getString("present_location"));
				state.put("new_location", rset.getString("new_location"));
				state.put("date", rset.getString("date"));
				states.add(state);

			}

			responseParameters.put("States", states);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getLocationStatusByDate(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		
		responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}
		
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LOCATION_STATUS_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> states = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> state = new HashMap<String, Object>();
				state.put("status_of_truck", rset.getString("status_of_truck"));
				state.put("fk_truck_id", rset.getInt("fk_truck_id"));
				state.put("truck_number", rset.getString("truck_number"));
				state.put("present_location", rset.getString("present_location"));
				state.put("date", rset.getString("date"));
				states.add(state);

			}

			responseParameters.put("States", states);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	// -------------------------------------------updates-----------------------------------------------------//
	public static HashMap<String, Object> updateLocationAllotment(int location_allotment_id, int fk_driver_id,
			int fk_truck_id, String present_location, String new_location, String date)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_LOCATION_ALLOTMENT));

			callableStatement.setInt("location_allotment_id", location_allotment_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("present_location", present_location);
			callableStatement.setString("new_location", new_location);
			callableStatement.setString("date", date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateLocationStatus(int location_status_id, String status_of_truck,
			int fk_truck_id, String present_location, String date) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_LOCATION_STATUS));
			callableStatement.setInt("location_status_id", location_status_id);
			callableStatement.setString("status_of_truck", status_of_truck);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("present_location", present_location);
			callableStatement.setString("date", date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
}