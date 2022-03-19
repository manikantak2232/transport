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
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class ImageUploadDAO {

	final static Logger log = Logger.getLogger(ImageUploadDAO.class);

	public static HashMap<String, Object> addDriverImageUpload(String primary_id,String aadhar_uploaded_image_url,
			String license_uploaded_image_url)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DRIVERS_IMAGE_URL));
			callableStatement.setString("primary_id", primary_id);
			callableStatement.setString("aadhar_image_url", aadhar_uploaded_image_url);
			callableStatement.setString("license_image_url", license_uploaded_image_url);

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
	
	public static HashMap<String, Object> updateDriverImageUpload(String primary_id,String aadhar_uploaded_image_url,
			String license_uploaded_image_url)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_DRIVERS_IMAGE_URL));
			callableStatement.setString("primary_id", primary_id);
			callableStatement.setString("aadhar_image_url", aadhar_uploaded_image_url);
			callableStatement.setString("license_image_url", license_uploaded_image_url);

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
	
	public static HashMap<String, Object> addTruckImageUpload(String primary_id,String rc_uploaded_image_url,
			String insurance_uploaded_image_url,String fitness_uploaded_image_url,
			String permit_uploaded_image_url)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCKS_IMAGE_URL));
			callableStatement.setString("primary_id", primary_id);
			callableStatement.setString("rc_image_url", rc_uploaded_image_url);
			callableStatement.setString("insurance_image_url", insurance_uploaded_image_url);
			callableStatement.setString("fitness_image_url", fitness_uploaded_image_url);
			callableStatement.setString("permit_image_url", permit_uploaded_image_url);
			
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
	
	public static HashMap<String, Object> updateTruckImageUpload(String primary_id,String rc_uploaded_image_url,
			String insurance_uploaded_image_url,String fitness_uploaded_image_url,
			String permit_uploaded_image_url)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRUCKS_IMAGE_URL));
			callableStatement.setString("primary_id", primary_id);
			callableStatement.setString("rc_image_url", rc_uploaded_image_url);
			callableStatement.setString("insurance_image_url", insurance_uploaded_image_url);
			callableStatement.setString("fitness_image_url", fitness_uploaded_image_url);
			callableStatement.setString("permit_image_url", permit_uploaded_image_url);

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
	
	public static HashMap<String, Object> addDispatchStartingMeterReadingImageUpload(String primary_id,
			String starting_meter_reading_uploaded_image_url
			)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DISPATCH_STARTING_METER_READING_IMAGE_UPLOAD_URL));
			callableStatement.setString("primary_id", primary_id);
			callableStatement.setString("starting_meter_reading_image_url", starting_meter_reading_uploaded_image_url);
			
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
	
	public static HashMap<String, Object> addDispatchClosingMeterReadingImageUpload(String primary_id,
			String closing_meter_reading_uploaded_image_url
			)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection2 = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement2 = null;
		ResultSet rset2 = null;

		try {
			callableStatement2 = connection2
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DISPATCH_CLOSING_METER_READING_IMAGE_UPLOAD_URL));
			callableStatement2.setString("primary_id", primary_id);
			callableStatement2.setString("closing_meter_reading_image_url", closing_meter_reading_uploaded_image_url);
			
			callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset2 = callableStatement2.executeQuery();

			if (!callableStatement2.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement2.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement2.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset2, callableStatement2, connection2);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addTripStartingMeterReadingImageUpload(Connection con,String primary_id,
			String starting_meter_reading_uploaded_image_url
			)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

	//	Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement2 = null;
		ResultSet rset2 = null;

		try {
			callableStatement2 = con
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRIP_STARTING_METER_READING_IMAGE_UPLOAD_URL));
			callableStatement2.setString("primary_id", primary_id);
			callableStatement2.setString("starting_meter_reading_image_url", starting_meter_reading_uploaded_image_url);
			
			callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset2 = callableStatement2.executeQuery();

			if (!callableStatement2.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement2.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement2.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			rset2.close();
			callableStatement2.close();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addTripClosingMeterReadingImageUpload(Connection con,String primary_id,
			String closing_meter_reading_uploaded_image_url
			)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

//		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement2 = null;
		ResultSet rset2 = null;

		try {
			callableStatement2 = con
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRIP_CLOSING_METER_READING_IMAGE_UPLOAD_URL));
			callableStatement2.setString("primary_id", primary_id);
			callableStatement2.setString("closing_meter_reading_image_url", closing_meter_reading_uploaded_image_url);
			
			callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset2 = callableStatement2.executeQuery();

			if (!callableStatement2.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement2.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement2.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			rset2.close();
			callableStatement2.close();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addPetrolPumpReadingImageUpload(String primary_id,
			String petrol_pump_reading_uploaded_image_url,String starting_meter_reading_uploaded_image_url
			)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement2 = null;
		ResultSet rset2 = null;

		try {
			callableStatement2 = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_PETROL_PUMP_READING_IMAGE_UPLOAD_URL));
			callableStatement2.setString("primary_id", primary_id);
			callableStatement2.setString("petrol_pump_reading_image_url", petrol_pump_reading_uploaded_image_url);
			callableStatement2.setString("starting_meter_reading_uploaded_image_url", starting_meter_reading_uploaded_image_url);
			
			callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset2 = callableStatement2.executeQuery();

			if (!callableStatement2.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement2.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement2.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			rset2.close();
			callableStatement2.close();
		}

		return responseParameters;
	}
	
	/*public static HashMap<String, Object> addFuelStartingMeterReadingImageUpload(String primary_id,
			String starting_meter_reading_uploaded_image_url
			)
			throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DISPATCH_STARTING_METER_READING_IMAGE_UPLOAD_URL));
			callableStatement.setString("primary_id", primary_id);
			callableStatement.setString("starting_meter_reading_image_url", starting_meter_reading_uploaded_image_url);
			
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
	}*/

}
