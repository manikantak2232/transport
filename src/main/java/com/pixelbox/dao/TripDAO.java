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

public class TripDAO {

	final static Logger log = Logger.getLogger(LoginDAO.class);

	public static HashMap<String, Object> addFactoryTripDetails(int fk_truck_id, int fk_driver_id, String start_location,
			String start_date, String destination, int estimated_km, int starting_meter_reading,
		 double freight, double advance, String starting_meter_reading_image_bytes_string,
		 String starting_meter_reading_image_type,String load_description,String type_of_load

	) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		connection.setAutoCommit(false);
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FACTORY_TRIP_DETAILS));
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("start_date", start_date);
			callableStatement.setString("type_of_load", type_of_load);
			callableStatement.setString("destination", destination);
			callableStatement.setInt("estimated_km", estimated_km);
			callableStatement.setInt("starting_meter_reading", starting_meter_reading);
			callableStatement.setString("load_description", load_description);
			callableStatement.setDouble("freight", freight);
			callableStatement.setDouble("advance", advance);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				connection.rollback();
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				String primary_id=callableStatement.getString("primary_id");
				String starting_meter_reading_bucket="truckimages";
				String starting_meter_reading_folder="trip starting meter reading";
				
				String starting_meter_reading_uploaded_image_url="";
				
				if(!starting_meter_reading_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,starting_meter_reading_bucket,starting_meter_reading_folder,primary_id);
					String startingMeterReadingFileName = starting_meter_reading_folder + "/" + primary_id + "." + starting_meter_reading_image_type;
					starting_meter_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+starting_meter_reading_bucket+"/"+startingMeterReadingFileName;
				
				}
				ImageUploadDAO.addTripStartingMeterReadingImageUpload(connection,primary_id,starting_meter_reading_uploaded_image_url
						);
				responseParameters.put("message", callableStatement.getString("message"));
				connection.commit();
			}
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTripDetails(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRIP_DETAILS));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> factories = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				trip.put("trip_id", rset.getInt("pk_trip_id"));
				trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				// factories.add(factory);//
				responseParameters.put("Trips", trip);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateTripDetails(int trip_id, int fk_truck_id, int fk_driver_id,
			String start_location, String start_date, String time_of_start, String destination, int estimated_km,
			int starting_meter_reading, int load_quantity, double freight, int closing_meter_reading, int checked_kms

	// String trip_status
	) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRIP_DETAILS));
			callableStatement.setInt("trip_id", trip_id);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("start_date", start_date);
			callableStatement.setString("time_of_start", time_of_start);
			callableStatement.setString("destination", destination);
			callableStatement.setInt("estimated_km", estimated_km);
			callableStatement.setInt("starting_meter_reading", starting_meter_reading);
			callableStatement.setInt("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setInt("closing_meter_reading", closing_meter_reading);
			callableStatement.setInt("checked_kms", checked_kms);
			callableStatement.setString("username", username);
			// callableStatement.setString("trip_status",trip_status);

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

	public static HashMap<String, Object> getCloseTripDetails(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_CLOSE_TRIP_DETAILS));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> factories = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				trip.put("trip_id", rset.getInt("pk_trip_id"));
				trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// String truck_number=rset.getString("truck_number");
				// trip.put("truck_number", rset.getString("truck_number"));
				trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
			//	trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				// factories.add(factory);//
				responseParameters.put("Trips", trip);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseTripDetails(int trip_id,int closing_meter_reading,
			int checked_kms, String closing_meter_reading_image_bytes_string,
			String closing_meter_reading_image_type, double freight) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		connection.setAutoCommit(false);
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_CLOSE_TRIP_DETAILS));
			callableStatement.setInt("trip_id", trip_id);
			callableStatement.setInt("closing_meter_reading", closing_meter_reading);
			callableStatement.setInt("checked_kms", checked_kms);
			callableStatement.setDouble("freight", freight);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				connection.rollback();
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				String primary_id=callableStatement.getString("primary_id");
				String closing_meter_reading_bucket="truckimages";
				String closing_meter_reading_folder="trip closing meter reading";
				
				String closing_meter_reading_uploaded_image_url="";
				
				if(!closing_meter_reading_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(closing_meter_reading_image_bytes_string,closing_meter_reading_image_type,closing_meter_reading_bucket,closing_meter_reading_folder,primary_id);
					String closingMeterReadingFileName = closing_meter_reading_folder + "/" + primary_id + "." + closing_meter_reading_image_type;
					closing_meter_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+closing_meter_reading_bucket+"/"+closingMeterReadingFileName;
				
				}
				ImageUploadDAO.addTripClosingMeterReadingImageUpload(connection,primary_id,closing_meter_reading_uploaded_image_url
						);
				responseParameters.put("message", callableStatement.getString("message"));
				connection.commit();
			}
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTripByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRIP_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> trips = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				 //trip.put("trip_id", rset.getInt("pk_trip_id"));
				 //trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				 trip.put("truck_number",rset.getString("truck_number"));
				 //trip.put("truck_number", rset.getString("truck_number"));
				// trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				 trip.put("driver_name", fname + " " + mname + " " + lname);
				 trip.put("start_location", rset.getString("start_location"));
				 trip.put("start_date", rset.getString("start_date"));
				 trip.put("time_of_start", rset.getString("time_of_start"));
				 trip.put("destination", rset.getString("destination"));
				 trip.put("estimated_km", rset.getInt("estimated_km"));
				 trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				 trip.put("load_quantity", rset.getInt("load_quantity"));
				 trip.put("freight",rset.getDouble("freight"));
				 trip.put("closing_meter_reading",rset.getInt("closing_meter_reading"));
				 trip.put("checked_kms",rset.getInt("checked_kms"));
				 trip.put("trip_status", rset.getString("trip_status"));
				
                 trips.add(trip);//
				
			}
			responseParameters.put("Trips", trips);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}


	public static HashMap<String, Object> addStorageTripDetails(int fk_truck_id, int fk_driver_id, String start_location,
			String start_date, String destination, int estimated_km, int starting_meter_reading,
			int load_quantity, double freight, double advance

	) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_STORAGE_TRIP_DETAILS));
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("start_date", start_date);
		//	callableStatement.setString("time_of_start", time_of_start);
			callableStatement.setString("destination", destination);
			callableStatement.setInt("estimated_km", estimated_km);
			callableStatement.setInt("starting_meter_reading", starting_meter_reading);
			callableStatement.setInt("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setDouble("advance", advance);
			callableStatement.setString("username", username);

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

	public static HashMap<String, Object> getStorageTripDetails(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_STORAGE_TRIP_DETAILS));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> factories = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				trip.put("trip_id", rset.getInt("pk_storage_trip_id"));
				trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				// factories.add(factory);//
				responseParameters.put("Trips", trip);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateStorageTripDetails(int trip_id, int fk_truck_id, int fk_driver_id,
			String start_location, String start_date, String time_of_start, String destination, int estimated_km,
			int starting_meter_reading, int load_quantity, double freight, int closing_meter_reading, int checked_kms

	// String trip_status
	) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_STORAGE_TRIP_DETAILS));
			callableStatement.setInt("storage_trip_id", trip_id);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("start_date", start_date);
			callableStatement.setString("time_of_start", time_of_start);
			callableStatement.setString("destination", destination);
			callableStatement.setInt("estimated_km", estimated_km);
			callableStatement.setInt("starting_meter_reading", starting_meter_reading);
			callableStatement.setInt("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setInt("closing_meter_reading", closing_meter_reading);
			callableStatement.setInt("checked_kms", checked_kms);
			callableStatement.setString("username", username);
			// callableStatement.setString("trip_status",trip_status);

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

	public static HashMap<String, Object> getCloseStorageTripDetails(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_CLOSE_STORAGE_TRIP_DETAILS));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> factories = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				trip.put("trip_id", rset.getInt("pk_storage_trip_id"));
				trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// String truck_number=rset.getString("truck_number");
				// trip.put("truck_number", rset.getString("truck_number"));
				trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				// factories.add(factory);//
				responseParameters.put("Trips", trip);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseStorageTripDetails(int trip_id, int closing_meter_reading,
			int checked_kms) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_CLOSE_STORAGE_TRIP_DETAILS));
			callableStatement.setInt("storage_trip_id", trip_id);
			callableStatement.setInt("closing_meter_reading", closing_meter_reading);
			callableStatement.setInt("checked_kms", checked_kms);
	//		callableStatement.setString("trip_status", trip_status);
			callableStatement.setString("username", username);

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

	public static HashMap<String, Object> getStorageTripByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_STORAGE_TRIP_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> trips = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				// trip.put("trip_id", rset.getInt("pk_trip_id"));
				//trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// String truck_number=rset.getString("truck_number");
				 trip.put("truck_number", rset.getString("truck_number"));
				//trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				trips.add(trip);//

			}
			responseParameters.put("Trips", trips);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addSellerTripDetails(int fk_truck_id, int fk_driver_id, String start_location,
			String start_date, String destination, int estimated_km, int starting_meter_reading,
			int load_quantity, double freight, double advance

	) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_SELLER_TRIP_DETAILS));
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("start_date", start_date);
		//	callableStatement.setString("time_of_start", time_of_start);
			callableStatement.setString("destination", destination);
			callableStatement.setInt("estimated_km", estimated_km);
			callableStatement.setInt("starting_meter_reading", starting_meter_reading);
			callableStatement.setInt("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setDouble("advance", advance);
			callableStatement.setString("username", username);

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

	public static HashMap<String, Object> getSellerTripDetails(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SELLER_TRIP_DETAILS));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> factories = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				trip.put("trip_id", rset.getInt("pk_seller_trip_id"));
				trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				// factories.add(factory);//
				responseParameters.put("Trips", trip);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateSellerTripDetails(int trip_id, int fk_truck_id, int fk_driver_id,
			String start_location, String start_date, String time_of_start, String destination, int estimated_km,
			int starting_meter_reading, int load_quantity, double freight, int closing_meter_reading, int checked_kms

	// String trip_status
	) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_SELLER_TRIP_DETAILS));
			callableStatement.setInt("seller_trip_id", trip_id);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("start_date", start_date);
			callableStatement.setString("time_of_start", time_of_start);
			callableStatement.setString("destination", destination);
			callableStatement.setInt("estimated_km", estimated_km);
			callableStatement.setInt("starting_meter_reading", starting_meter_reading);
			callableStatement.setInt("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setInt("closing_meter_reading", closing_meter_reading);
			callableStatement.setInt("checked_kms", checked_kms);
			callableStatement.setString("username", username);
			// callableStatement.setString("trip_status",trip_status);

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

	public static HashMap<String, Object> getCloseSellerTripDetails(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_CLOSE_SELLER_TRIP_DETAILS));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> factories = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				trip.put("trip_id", rset.getInt("pk_seller_trip_id"));
				trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// String truck_number=rset.getString("truck_number");
				// trip.put("truck_number", rset.getString("truck_number"));
				trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				// factories.add(factory);//
				responseParameters.put("Trips", trip);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseSellerTripDetails(int trip_id, int closing_meter_reading,
			int checked_kms) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_CLOSE_SELLER_TRIP_DETAILS));
			callableStatement.setInt("seller_trip_id", trip_id);
			callableStatement.setInt("closing_meter_reading", closing_meter_reading);
			callableStatement.setInt("checked_kms", checked_kms);
	//		callableStatement.setString("trip_status", trip_status);
			callableStatement.setString("username", username);

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

	public static HashMap<String, Object> getSellerTripByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SELLER_TRIP_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> trips = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> trip = new HashMap<String, Object>();
				// trip.put("trip_id", rset.getInt("pk_trip_id"));
		//		trip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// String truck_number=rset.getString("truck_number");
				 trip.put("truck_number", rset.getString("truck_number"));
			//	trip.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trip.put("driver_name", fname + " " + mname + " " + lname);
				trip.put("start_location", rset.getString("start_location"));
				trip.put("start_date", rset.getString("start_date"));
				trip.put("time_of_start", rset.getString("time_of_start"));
				trip.put("destination", rset.getString("destination"));
				trip.put("estimated_km", rset.getInt("estimated_km"));
				trip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				trip.put("load_quantity", rset.getInt("load_quantity"));
				trip.put("freight", rset.getDouble("freight"));
				trip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				trip.put("checked_kms", rset.getInt("checked_kms"));
				trip.put("trip_status", rset.getString("trip_status"));

				trips.add(trip);

			}
			responseParameters.put("Trips", trips);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTripStartedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRIP_STARTED_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();
			ArrayList<HashMap<String, Object>> truckNumber = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truckNo = new HashMap<String, Object>();
				truckNo.put("fk_truck_id", rset.getInt("fk_truck_id"));
				truckNo.put("truck_number", rset.getString("truck_number"));
				truckNumber.add(truckNo);
				
				HashMap<String, Object> trucks = new HashMap<String, Object>();
				trucks.put("fk_truck_id", rset.getInt("fk_truck_id"));
				trucks.put("truck_number", rset.getString("truck_number"));
				trucks.put("dispatch", rset.getString("dispatch"));
				truckDetails.add(trucks);
			}
			responseParameters.put("truckNumber", truckNumber);
			responseParameters.put("truckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTripDriverExpenditure(int fk_driver_id, String date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRIP_DRIVER_EXPENDITURE));

			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("date", date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> Factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("pk_trip_id", rset.getInt("pk_trip_id"));
				factory.put("dispatch", rset.getString("dispatch"));
				factory.put("destination", rset.getString("destination"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("time_of_start", rset.getString("time_of_start"));

				Factories.add(factory);

			}

			responseParameters.put("Factories", Factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTripDriversCloseExpenditure(int trip_id, String dispatch)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRIP_DRIVERS_CLOSE_EXPENDITURE));

			callableStatement.setInt("trip_id", trip_id);
			callableStatement.setString("dispatch", dispatch);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> Drivers = new ArrayList<HashMap<String, Object>>();
			ArrayList<HashMap<String, Object>> driverAdvance = new ArrayList<HashMap<String, Object>>();
			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				HashMap<String, Object> driver_advance = new HashMap<String, Object>();
				
				factory.put("driver_id", rset.getInt("fk_driver_id"));
				String fname= rset.getString("first_name");
				String mname= rset.getString("middle_name");
				String lname= rset.getString("last_name");
				String name=fname+" "+mname+" "+lname;
				factory.put("driver_name", name);
				Drivers.add(factory);
				
				driver_advance.put("balance", rset.getDouble("balance"));
				driver_advance.put("driver_name", name);
				driver_advance.put("fk_driver_id", rset.getInt("fk_driver_id"));
				driverAdvance.add(driver_advance);

			}
			responseParameters.put("driverAdvance", driverAdvance);
			responseParameters.put("Drivers", Drivers);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addTripDriverExpenditure(int trip_id,int fk_driver_id,
            String dispatch,int transport,int loading,int cover_tying,int contonment,
			  int toll_gate,int loading_wage,int unloading_wage,
			  int phone_bills,int spares_parts,int puncher,int entry,
			  int return_transport,int return_loading,int return_unloading,int others, int balance) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRIP_DRIVER_EXPENDITURE));
			callableStatement.setInt("trip_id", trip_id);
			callableStatement.setInt("driver_id", fk_driver_id);
			callableStatement.setString("dispatch", dispatch);
			callableStatement.setString("username", username);
			callableStatement.setInt("transport", transport);
			callableStatement.setInt("loading", loading);
			callableStatement.setInt("cover_tying", cover_tying);
			callableStatement.setInt("contonment", contonment);
			callableStatement.setInt("toll_gate", toll_gate);
			callableStatement.setInt("loading_wage", loading_wage);
			callableStatement.setInt("unloading_wage", unloading_wage);
			callableStatement.setInt("phone_bills", phone_bills);
			callableStatement.setInt("spares_parts", spares_parts);
			callableStatement.setInt("puncher", puncher);
			callableStatement.setInt("entry", entry);
			callableStatement.setInt("return_transport", return_transport);
			callableStatement.setInt("return_loading", return_loading);
			callableStatement.setInt("return_unloading", return_unloading);
			callableStatement.setInt("others", others);
			callableStatement.setInt("balance", balance);
			

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
	

}



