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

public class TyreDAO {

	final static Logger log = Logger.getLogger(LoginDAO.class);

	public static HashMap<String, Object> addVehicleTyreSummaryReport(String date, String vehicle_number,
			String front_or_back_tyres, String tyre_number, String tyre_issued_reading, String tyre_closing_reading,
			String remarks) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_VEHICLE_TYRE_SUMMARY_REPORT));

			callableStatement.setString("date", date);
			callableStatement.setString("vehicle_number", vehicle_number);
			callableStatement.setString("front_or_back_tyres", front_or_back_tyres);
			callableStatement.setString("tyre_number", tyre_number);
			callableStatement.setString("tyre_issued_reading", tyre_issued_reading);
			callableStatement.setString("tyre_closing_reading", tyre_closing_reading);
			callableStatement.setString("remarks", remarks);

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

	public static HashMap<String, Object> addFrontTotalSummary(String date, String air_filler, String front,
			String tyre_number, String vehicle_number, int fk_driver_id, String closing_date, String reading,
			String remarks) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FRONT_TOTAL_SUMMARY));

			callableStatement.setString("date", date);
			callableStatement.setString("air_filler", air_filler);
			callableStatement.setString("front", front);
			callableStatement.setString("tyre_number", tyre_number);
			callableStatement.setString("vehicle_number", vehicle_number);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("closing_date", closing_date);
			callableStatement.setString("reading", reading);
			callableStatement.setString("remarks", remarks);

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

	public static HashMap<String, Object> addHosingTotalSummary(String date, String air_filler, String hosing,
			String tyre_number, String vehicle_number, int fk_driver_id, String closing_date, String reading,
			String remarks) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_HOSING_TOTAL_SUMMARY));

			callableStatement.setString("date", date);
			callableStatement.setString("air_filler", air_filler);
			callableStatement.setString("hosing", hosing);
			callableStatement.setString("tyre_number", tyre_number);
			callableStatement.setString("vehicle_number", vehicle_number);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("closing_date", closing_date);
			callableStatement.setString("reading", reading);
			callableStatement.setString("remarks", remarks);
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

	public static HashMap<String, Object> getVehicleTyreSummaryReport(String vehicle_number)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_VEHICLE_TYRE_SUMMARY_REPORT));

			callableStatement.setString("vehicle_number", vehicle_number);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> vehicles = new
			// ArrayList<HashMap<String, Object>>();//

			while (rset.next()) {
				HashMap<String, Object> vehicle = new HashMap<String, Object>();
				vehicle.put("vehicle_tyre_summary_report_id", rset.getString("pk_vehicle_tyre_summary_report_id"));
				vehicle.put("date", rset.getString("date"));
				vehicle.put("vehicle_no", rset.getString("vehicle_number"));
				vehicle.put("front_or_back_tyres", rset.getString("front_or_back_tyres"));
				vehicle.put("tyre_number", rset.getString("tyre_number"));
				vehicle.put("tyre_issued_reading", rset.getString("tyre_issued_reading"));
				vehicle.put("tyre_closing_reading", rset.getString("tyre_closing_reading"));
				vehicle.put("remarks", rset.getString("remarks"));

				// vehicles.add(vehicle);//
				responseParameters.put("Vehicles", vehicle);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	// ---------------------------------------------------get by
	// date----------------------------------------------//
	public static HashMap<String, Object> getVehicleTyreSummaryReportByDate(String lower_date, String upper_date)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_VEHICLE_TYRE_SUMMARY_REPORT_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> vehicles = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> vehicle = new HashMap<String, Object>();

				vehicle.put("date", rset.getString("date"));
				vehicle.put("vehicle_number", rset.getString("vehicle_number"));
				vehicle.put("front_or_back_tyres", rset.getString("front_or_back_tyres"));
				vehicle.put("tyre_number", rset.getString("tyre_number"));
				vehicle.put("tyre_issued_reading", rset.getString("tyre_issued_reading"));
				vehicle.put("tyre_closing_reading", rset.getString("tyre_closing_reading"));
				vehicle.put("remarks", rset.getString("remarks"));
				vehicles.add(vehicle);
			}

			responseParameters.put("Vehicles", vehicles);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	// -----------------------------------------------------------update----------------------------------------------//
	public static HashMap<String, Object> updateVehicleTyreSummaryReport(int vehicle_tyre_summary_report_id,
			String date, String vehicle_number, String front_or_back_tyres, String tyre_number,
			String tyre_issued_reading, String tyre_closing_reading, String remarks)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_VEHICLE_TYRE_SUMMARY_REPORT));
			callableStatement.setInt("vehicle_tyre_summary_report_id", vehicle_tyre_summary_report_id);
			callableStatement.setString("date", date);
			callableStatement.setString("vehicle_number", vehicle_number);
			callableStatement.setString("front_or_back_tyres", front_or_back_tyres);
			callableStatement.setString("tyre_number", tyre_number);
			callableStatement.setString("tyre_issued_reading", tyre_issued_reading);
			callableStatement.setString("tyre_closing_reading", tyre_closing_reading);
			callableStatement.setString("remarks", remarks);

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

	public static HashMap<String, Object> addTyreInventory(String invoice_number, String tyre_type,
			String invoice_date, String brand_name, String tyre_number, double tyre_unit_price,
			double tyre_discount_percent,double tyre_gst_percent,double tube_unit_price,
			double tube_discount_percent, double tube_gst_percent, double flap_unit_price,
			double flap_discount_percent, double flap_gst_percent
			) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TYRE_INVENTORY));

			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("tyre_type", tyre_type);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("brand_name", brand_name);
			callableStatement.setString("tyre_number", tyre_number);
			callableStatement.setDouble("tyre_unit_price", tyre_unit_price);
			callableStatement.setDouble("tyre_discount_percent", tyre_discount_percent);
			callableStatement.setDouble("tyre_gst_percent", tyre_gst_percent);
			callableStatement.setDouble("tube_unit_price", tube_unit_price);
			callableStatement.setDouble("tube_discount_percent", tube_discount_percent);
			callableStatement.setDouble("tube_gst_percent", tube_gst_percent);
			callableStatement.setDouble("flap_unit_price", flap_unit_price);
			callableStatement.setDouble("flap_discount_percent", flap_discount_percent);
			callableStatement.setDouble("flap_gst_percent", flap_gst_percent);
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
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllTyres() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_TYRES));
			//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);


			ArrayList<String> spareParts = new ArrayList<String>();

			while(rset.next()){

				String name1= rset.getString("tyre_number");
				String name2= rset.getString("brand_name");
				String name3= rset.getString("tyre_type");
				String name= name1+"("+name2+")"+"("+name3+")";
				//	part.put("pk_tyres_current_inventory_id", rset.getInt("pk_tyres_current_inventory_id"));
				String part = name;
				//	part.put("fk_vendor_id", rset.getInt("fk_vendor_id"));
				//	part.put("type", rset.getString("type"));
				spareParts.add(part);

			}
			responseParameters.put("Tyres", spareParts);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllBrandNames() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_BRAND_NAMES));
			//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);


			ArrayList<String> spareParts = new ArrayList<String>();

			while(rset.next()){

				String part= rset.getString("brand_name");
				spareParts.add(part);

			}
			responseParameters.put("BrandNames", spareParts);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllRunningBrandNames() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_RUNNING_BRAND_NAMES));
			//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);


			ArrayList<String> spareParts = new ArrayList<String>();

			while(rset.next()){

				String part= rset.getString("brand_name");
				spareParts.add(part);

			}
			responseParameters.put("BrandNames", spareParts);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllPendingBrandNames() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_PENDING_BRAND_NAMES));
			//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);


			ArrayList<String> spareParts = new ArrayList<String>();

			while(rset.next()){

				String part= rset.getString("brand_name");
				spareParts.add(part);

			}
			responseParameters.put("BrandNames", spareParts);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}


	public static HashMap<String, Object> getAllTyresByCategory(String brand_name, String tyre_type,
			String tyre_category)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_TYRES_BY_CATEGORY));

			callableStatement.setString("brand_name", brand_name);
			callableStatement.setString("tyre_type", tyre_type);
			callableStatement.setString("tyre_category", tyre_category);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> parts = new ArrayList<HashMap<String,Object>>();

			while(rset.next()){
				HashMap<String,Object> spareParts=new HashMap<String,Object>();
				if(tyre_category.equals("recoup")){
					spareParts.put("tyre_number",rset.getString("tyre_number"));
					spareParts.put("recoup_tyre_cost",rset.getDouble("recoup_tyre_cost"));
				}else{
					spareParts.put("tyre_number",rset.getString("tyre_number"));
				}
				
				parts.add(spareParts);
			}
			responseParameters.put("IssuedTyres", parts);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllRunningTyres(String brand_name)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_RUNNING_TYRES));

			callableStatement.setString("brand_name", brand_name);
			//		callableStatement.setString("tyre_type", tyre_type);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<String> spareParts = new ArrayList<String>();

			while(rset.next()){

				String part= rset.getString("tyre_number");

				spareParts.add(part);

			}
			responseParameters.put("ReturnedTyres", spareParts);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllPendingTyres(String brand_name, String tyre_type
			)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_PENDING_TYRES));

			callableStatement.setString("brand_name", brand_name);
			callableStatement.setString("tyre_type", tyre_type);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<String> spareParts = new ArrayList<String>();

			while(rset.next()){

				String part= rset.getString("tyre_number");

				spareParts.add(part);

			}
			responseParameters.put("Tyres", spareParts);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateRecoupTyreStatus( String tyre_number, String tyre_status)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_RECOUP_TYRE_STATUS));

			callableStatement.setString("tyre_number", tyre_number);
			callableStatement.setString("tyre_status", tyre_status);

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

	public static HashMap<String, Object> addIssuedTyres(String issued_tyre_number,String issued_brand_name,
			String issued_tyre_type,String issued_tyre_category,String issued_and_returned_date,
			int fk_truck_id,int fk_driver_id,double issued_tyre_reading,String returned_tyre_number,
			String returned_brand_name,String returned_tyre_category,
			double returned_tyre_reading,int recoupTyreCost) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		connection.setAutoCommit(false);
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_ISSUED_TYRES));

			callableStatement.setString("issued_tyre_number", issued_tyre_number);
			callableStatement.setString("issued_brand_name", issued_brand_name);
			callableStatement.setString("issued_tyre_type", issued_tyre_type);
			callableStatement.setString("issued_tyre_category", issued_tyre_category);
			callableStatement.setString("issued_and_returned_date", issued_and_returned_date);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setDouble("issued_tyre_reading", issued_tyre_reading);
			callableStatement.setString("returned_tyre_number", returned_tyre_number);
			callableStatement.setString("returned_brand_name", returned_brand_name);
			//		callableStatement.setString("returned_tyre_type", returned_tyre_type);
			callableStatement.setString("returned_tyre_category", returned_tyre_category);
			callableStatement.setDouble("returned_tyre_reading", returned_tyre_reading);
			callableStatement.setInt("recoupTyreCost", recoupTyreCost);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

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
				connection.commit(); 
				responseParameters.put("message", callableStatement.getString("message"));
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

	public static HashMap<String, Object> addRecoupTyres(String tyre_number, String brand_name, String tyre_type, double tyre_taken_reading,
			String tyre_taken_date) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_RECOUP_TYRES));

			callableStatement.setString("tyre_number", tyre_number);
			callableStatement.setString("brand_name", brand_name);
			callableStatement.setString("tyre_type", tyre_type);
			callableStatement.setDouble("tyre_taken_reading", tyre_taken_reading);
			callableStatement.setString("tyre_taken_date", tyre_taken_date);


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

	public static HashMap<String, Object> getRunningTyres(String truck_number)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_RUNNING_TYRES));

			callableStatement.setString("truck_no", truck_number);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> running_tyres = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> tyre = new HashMap<String, Object>();

				tyre.put("fk_truck_id", rset.getInt("fk_truck_id"));
				tyre.put("tyre_number", rset.getString("tyre_number"));
				tyre.put("date", rset.getString("date"));
				tyre.put("brand_name", rset.getString("brand_name"));
				//		tyre.put("tyre_type", rset.getString("tyre_type"));
				tyre.put("truck_number", rset.getString("truck_number"));

				running_tyres.add(tyre);
			}

			responseParameters.put("running_tyres", running_tyres);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getIssuedTyres(String lower_date, String upper_date,
			String tyre_category, String truck_number)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ISSUED_TYRES));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("tyre_category", tyre_category);
			callableStatement.setString("truck_no", truck_number);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> issued_tyres = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> tyre = new HashMap<String, Object>();

				tyre.put("pk_issued_tyre_id", rset.getInt("pk_issued_tyre_id"));
				tyre.put("issued_tyre_date", rset.getString("issued_tyre_date"));
				tyre.put("tyre_number", rset.getString("tyre_number"));
				tyre.put("brand_name", rset.getString("brand_name"));
				tyre.put("tyre_type", rset.getString("tyre_type"));
				tyre.put("fk_truck_id", rset.getInt("fk_truck_id"));

				tyre.put("fk_driver_id", rset.getInt("fk_driver_id"));
				tyre.put("issued_tyre_reading", rset.getDouble("issued_tyre_reading"));
				tyre.put("returned_tyre_reading", rset.getDouble("returned_tyre_reading"));
				tyre.put("returned_tyre_date", rset.getString("returned_tyre_date"));
				tyre.put("truck_number", rset.getString("truck_number"));
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				String name=fname+" "+mname+" "+lname;
				tyre.put("name", name);
				issued_tyres.add(tyre);
			}

			responseParameters.put("issued_tyres", issued_tyres);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getWasteTyres(String lower_date, String upper_date)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_WASTE_TYRES));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> waste_tyres = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> tyre = new HashMap<String, Object>();

				tyre.put("pk_waste_tyre_id", rset.getInt("pk_waste_tyre_id"));
				tyre.put("tyre_number", rset.getString("tyre_number"));
				tyre.put("date", rset.getString("date"));
				tyre.put("brand_name", rset.getString("brand_name"));
				//		tyre.put("tyre_type", rset.getString("tyre_type"));


				waste_tyres.add(tyre);
			}

			responseParameters.put("waste_tyres", waste_tyres);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getRecoupTyres()
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_RECOUP_TYRES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> recoupTyres = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> tyre = new HashMap<String, Object>();

				tyre.put("pk_recoup_tyre_id", rset.getInt("pk_recoup_tyre_id"));
				tyre.put("tyre_number", rset.getString("tyre_number"));
				tyre.put("date", rset.getString("date"));
				tyre.put("brand_name", rset.getString("brand_name"));
				tyre.put("tyre_type", rset.getString("tyre_type"));
				tyre.put("tyre_status", rset.getString("tyre_status"));
				recoupTyres.add(tyre);
			}

			responseParameters.put("recoupTyres", recoupTyres);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTyresInventoryHistory(String lower_date, String upper_date)
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TYRES_INVENTORY_HISTORY));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();


			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> inventory_history = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> tyre = new HashMap<String, Object>();

				tyre.put("pk_tyres_inventory_history_id", rset.getInt("pk_tyres_inventory_history_id"));
				tyre.put("invoice_number", rset.getString("invoice_number"));
				tyre.put("tyre_number", rset.getString("tyre_number"));
				tyre.put("invoice_date", rset.getString("invoice_date"));
				tyre.put("brand_name", rset.getString("brand_name"));
				tyre.put("tyre_type", rset.getString("tyre_type"));

				inventory_history.add(tyre);

			}
			responseParameters.put("inventoryHistory", inventory_history);


		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTyresCurrentInventory()
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
			callableStatement = connection.prepareCall(
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TYRES_CURRENT_INVENTORY));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> currentInventory = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> tyre = new HashMap<String, Object>();

				tyre.put("pk_tyres_current_inventory_id", rset.getInt("pk_tyres_current_inventory_id"));
				tyre.put("tyre_number", rset.getString("tyre_number"));
				tyre.put("brand_name", rset.getString("brand_name"));
				tyre.put("tyre_type", rset.getString("tyre_type"));
				currentInventory.add(tyre);
			}

			responseParameters.put("currentInventory", currentInventory);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}


}
