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
import com.pixelbox.service.ImageService;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class TrucksDAO {

	final static Logger log = Logger.getLogger(TrucksDAO.class);

	public static HashMap<String, Object> addTrucksAllotment(String truck_allotment_date, int fk_driver_id,
			String allotment_location, int fk_truck_id

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCKS_ALLOTMENT));

			callableStatement.setString("truck_allotment_date", truck_allotment_date);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("allotment_location", allotment_location);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
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
				responseParameters.put("errorMessage", "record successfully inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addTrucksDetails(String truck_number, 
			String chassis_number, 
			String engine_number,
			String registration_date,
			String permit_number,
			String permit_area,
			String permit_validity,
			String insurance_company_name,
			String insurance_policy_number,
			String insurance_date,
			String insurance_expiry_date,
			String fitness_certificate_number,
			String fitness_expire_date,
			String fitness_image_bytes_string,
			String fitness_image_type,
			String rc_image_bytes_string,
			String rc_image_type,
			String insurance_image_bytes_string,
			String insurance_image_type,
			String permit_image_bytes_string,
			String permit_image_type
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCKS_DETAILS));

			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("chassis_number", chassis_number);
			callableStatement.setString("engine_number",engine_number);
			callableStatement.setString("registration_date", registration_date);
			callableStatement.setString("permit_number", permit_number);
			callableStatement.setString("permit_area", permit_area);
			callableStatement.setString("permit_validity",permit_validity);
			callableStatement.setString("insurance_company_name",insurance_company_name);
			callableStatement.setString("insurance_policy_number", insurance_policy_number);
			callableStatement.setString("insurance_date", insurance_date);
			callableStatement.setString("insurance_expiry_date", insurance_expiry_date);
			callableStatement.setString("fitness_certificate_number",fitness_certificate_number);
			callableStatement.setString("fitness_expire_date", fitness_expire_date);
			/*callableStatement.setString("username", username);*/

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);

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
				String primary_id=callableStatement.getString("primary_id");
				String fitness_bucket="truckimages";
				String fitness_folder="fitness";
				String rc_bucket="truckimages";
				String rc_folder="rc";
				String insurance_bucket="truckimages";
				String insurance_folder="insurance";
				String permit_bucket="truckimages";
				String permit_folder="permit";
				
				String rc_uploaded_image_url="";
				String insurance_uploaded_image_url="";
				String fitness_uploaded_image_url="";
				String permit_uploaded_image_url="";
				
				if(!fitness_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(fitness_image_bytes_string,fitness_image_type,fitness_bucket,fitness_folder,primary_id);
					String fitnessFileName = fitness_folder + "/" + primary_id + "." + fitness_image_type;
					fitness_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+fitness_bucket+"/"+fitnessFileName;
				
				}
				if(!rc_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(rc_image_bytes_string,rc_image_type,rc_bucket,rc_folder,primary_id);
					String rcFileName = rc_folder + "/" + primary_id + "." + rc_image_type;
					rc_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+rc_bucket+"/"+rcFileName;
				
				}
				if(!insurance_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(insurance_image_bytes_string,insurance_image_type,insurance_bucket,insurance_folder,primary_id);
					String insuranceFileName = insurance_folder + "/" + primary_id + "." + insurance_image_type;
					insurance_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+insurance_bucket+"/"+insuranceFileName;
				
				}
				if(!permit_image_bytes_string.equals(""))
				{
				ImageService.uploadToAws(permit_image_bytes_string,permit_image_type,permit_bucket,permit_folder,primary_id);
				String permitFileName = permit_folder + "/" + primary_id + "." + permit_image_type;
				permit_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+permit_bucket+"/"+permitFileName;
			
				}
				ImageUploadDAO.addTruckImageUpload(primary_id,rc_uploaded_image_url, insurance_uploaded_image_url,
						fitness_uploaded_image_url, permit_uploaded_image_url);
				responseParameters.put("errorMessage", "record successfully inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addTrucksHealthHistory(String description, int fk_truck_id, String date,
			int fk_spare_parts_id) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCKS_HEALTH_HISTORY));

			callableStatement.setString("description", description);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("date", date);
			callableStatement.setInt("fk_spare_parts_id", fk_spare_parts_id);
			callableStatement.setString("username", username);

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
				responseParameters.put("errorMessage", "record successfully inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addTrucksPartsQuote(String quote_number, String new_quote_date,
			String product_name, String company_name, String agent, int number_of_units, double unit_price,
			double total, int no_of_days_for_delivery, int no_of_hours_for_delivery, String mode_of_payment,
			String type_of_payment, int valid_for_number_of_days, String contact_number)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCKS_PARTS_QUOTE));

			callableStatement.setString("quote_number", quote_number);
			callableStatement.setString("new_quote_date", new_quote_date);
			callableStatement.setString("product_name", product_name);
			callableStatement.setString("company_name", company_name);
			callableStatement.setString("agent", agent);
			callableStatement.setInt("number_of_units", number_of_units);
			callableStatement.setDouble("unit_price", unit_price);
			callableStatement.setDouble("total", total);
			callableStatement.setInt("no_of_days_for_delivery", no_of_days_for_delivery);
			callableStatement.setInt("no_of_hours_for_delivery", no_of_hours_for_delivery);
			callableStatement.setString("mode_of_payment", mode_of_payment);
			callableStatement.setString("type_of_payment", type_of_payment);
			callableStatement.setInt("valid_for_number_of_days", valid_for_number_of_days);
			callableStatement.setString("contact_number", contact_number);
//			callableStatement.setString("quote_status", quote_status);
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
				responseParameters.put("errorMessage", "record successfully inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	/*public static HashMap<String, Object> addTrucksService(int fk_truck_id, String service_center_name,
			String service_date, double service_total_cost, int fk_spare_parts_id, String description

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCKS_SERVICE));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("service_center_name", service_center_name);
			callableStatement.setString("service_date", service_date);
			callableStatement.setDouble("service_total_cost", service_total_cost);
			callableStatement.setInt("fk_spare_parts_id", fk_spare_parts_id);
			callableStatement.setString("description", description);
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
				responseParameters.put("errorMessage", "record successfully inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}*/
	
	public static HashMap<String, Object> addTrucksService(int fk_truck_id, String service_center_name,
			String service_start_date

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCKS_SERVICE));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("service_center_name", service_center_name);
			callableStatement.setString("service_start_date", service_start_date);
			/*callableStatement.setDouble("service_total_cost", service_total_cost);
			callableStatement.setInt("fk_spare_parts_id", fk_spare_parts_id);
			callableStatement.setString("description", description);*/
			//callableStatement.setString("username", username);

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
				responseParameters.put("errorMessage", "record successfully inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> closeTrucksService(int fk_truck_id, String service_closed_date,int fk_spare_parts_id,
			double service_total_cost

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.CLOSE_TRUCKS_SERVICE));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("service_closed_date", service_closed_date);
		    callableStatement.setInt("fk_spare_parts_id", fk_spare_parts_id);
		    callableStatement.setDouble("service_total_cost", service_total_cost);
			//callableStatement.setString("truck_service_status", truck_service_status);
			//callableStatement.setString("username", username);

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
				responseParameters.put("errorMessage", "record successfully inserted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTrucksAllotment(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_ALLOTMENT));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> trucksAllotment = new
			// ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> trucks = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trucks.put("trucks_allotment_id", rset.getInt("pk_trucks_allotment_id"));
				trucks.put("truck_allotment_date", rset.getString("truck_allotment_date"));
				trucks.put("truck_id", rset.getInt("fk_truck_id"));
				trucks.put("driver_name", fname + " " + mname + " " + lname);
				trucks.put("truck_number", rset.getString("truck_number"));
				trucks.put("fk_driver_id", rset.getInt("fk_driver_id"));
				trucks.put("first_name", rset.getString("first_name"));
				trucks.put("middle_name", rset.getString("middle_name"));
				trucks.put("last_name", rset.getString("last_name"));
				trucks.put("allotment_location", rset.getString("allotment_location"));

				// trucksAllotment.add(trucks);
				responseParameters.put("trucksAllotment", trucks);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTrucksDetails(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_DETAILS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> trucksDetails = new
			// ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("chassis_number", rset.getString("chassis_number"));;
				truck.put("engine_number", rset.getString("engine_number"));
				truck.put("registration_date", rset.getString("registration_date"));
				truck.put("permit_number", rset.getString("permit_number"));
				truck.put("permit_area",rset.getString("permit_area"));
				truck.put("permit_validity",rset.getString("permit_validity"));
				truck.put("insurance_company_name", rset.getString("insurance_company_name"));
				truck.put("insurance_policy_number", rset.getString("insurance_policy_number"));
				truck.put("insurance_date", rset.getString("insurance_date"));
				truck.put("insurance_expiry_date", rset.getString("insurance_expiry_date"));
				truck.put("fitness_certificate_number", rset.getString("fitness_certificate_number"));
				truck.put("fitness_expire_date", rset.getString("fitness_expire_date"));
				truck.put("rc_image_url", rset.getString("rc_image_url"));
				truck.put("insurance_image_url", rset.getString("insurance_image_url"));
				truck.put("fitness_image_url", rset.getString("fitness_image_url"));
				truck.put("permit_image_url", rset.getString("permit_image_url"));

				// trucksDetails.add(truck);
				responseParameters.put("trucksDetails", truck);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTrucksPartsQuote(String quote_number)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_PARTS_QUOTE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("quote_number", quote_number);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> trucksPartsQuote = new
			// ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("trucks_parts_quote_id", rset.getInt("pk_trucks_parts_quote_id"));
				truck.put("quote_no", rset.getString("quote_number"));
				truck.put("new_quote_date", rset.getString("new_quote_date"));
				truck.put("product_name", rset.getString("product_name"));
				truck.put("company_name", rset.getString("company_name"));
				truck.put("agent", rset.getString("agent"));
				truck.put("number_of_units", rset.getInt("number_of_units"));
				truck.put("unit_price", rset.getDouble("unit_price"));
				truck.put("total", rset.getDouble("total"));
				truck.put("no_of_days_for_delivery", rset.getInt("no_of_days_for_delivery"));
				truck.put("no_of_hours_for_delivery", rset.getInt("no_of_hours_for_delivery"));
				truck.put("mode_of_payment", rset.getString("mode_of_payment"));
				truck.put("type_of_payment", rset.getString("type_of_payment"));
				truck.put("valid_for_number_of_days", rset.getInt("valid_for_number_of_days"));
				truck.put("contact_number", rset.getString("contact_number"));
				truck.put("quote_status", rset.getString("quote_status"));

				// trucksPartsQuote.add(truck);
				responseParameters.put("trucksPartsQuote", truck);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTrucksHealthHistory(int fk_truck_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_HEALTH_HISTORY));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> healthHistory = new
			// ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> health = new HashMap<String, Object>();
				health.put("trucks_health_history_id", rset.getInt("pk_trucks_health_history_id"));
				health.put("description", rset.getString("description"));
				health.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// health.put("truck_number", rset.getString("truck_number"));
				health.put("date", rset.getString("date"));
				health.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
				health.put("name", rset.getString("name"));
				health.put("type", rset.getString("type"));

				// healthHistory.add(health);
				responseParameters.put("healthHistory", health);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTruckStatusTracking(String selected_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_STATUS_TRACKING));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("selected_date", selected_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			 ArrayList<HashMap<String, Object>> trucksStatus = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> status = new HashMap<String, Object>();
				
				status.put("truck_number", rset.getString("truck_number"));
				status.put("operation", rset.getString("operation"));
				status.put("operation_id", rset.getInt("operation_id"));
				status.put("association_id", rset.getInt("association_id"));
				status.put("association_name", rset.getString("association_name"));
				status.put("operation_date", rset.getString("operation_date"));
				status.put("waiting_location", rset.getString("waiting_location"));

				trucksStatus.add(status);
				
			}
			responseParameters.put("trucksStatus", trucksStatus);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckStatusTrackingList(String selected_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_STATUS_TRACKING_LIST));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("selected_date", selected_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			 ArrayList<HashMap<String, Object>> trucksStatus = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> status = new HashMap<String, Object>();
				
				status.put("truck_number", rset.getString("truck_number"));
				status.put("operation", rset.getString("operation"));
				status.put("operation_id", rset.getInt("operation_id"));
				status.put("association_id", rset.getInt("association_id"));
				status.put("association_name", rset.getString("association_name"));
				status.put("truck_id", rset.getInt("truck_id"));
				status.put("max_id", rset.getInt("max_id"));
				status.put("waiting_location", rset.getString("waiting_location"));
				status.put("operation_date", rset.getString("operation_date"));
				status.put("old_operation_date", rset.getString("old_operation_date"));
				status.put("delay_status", rset.getInt("delay_status"));
				

				trucksStatus.add(status);
				
			}
			responseParameters.put("trucksStatus", trucksStatus);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksService(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_SERVICE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> trucksService = new
			// ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> service = new HashMap<String, Object>();
				service.put("truck_id", rset.getInt("fk_truck_id"));
				service.put("truck_service_id", rset.getInt("pk_truck_service_id"));
				service.put("service_center_name", rset.getString("service_center_name"));
				service.put("service_date", rset.getString("service_date"));
				service.put("service_total_cost", rset.getDouble("service_total_cost"));
				service.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
				service.put("description", rset.getString("description"));
				service.put("name", rset.getString("name"));
				service.put("type", rset.getString("type"));

				// trucksService.add(service);
				responseParameters.put("trucksService", service);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	/*public static HashMap<String, Object> getTrucksServiceByTruck(String lower_date, String upper_date,int fk_truck_id)throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_SERVICE_BY_Truck));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksService = new ArrayList<HashMap<String, Object>>();

			int previous_truck_id = 0;
			int current_truck_id = 0;
			String current_date=null;
			String previous_date=null;
			String current_service_center_name=null;
			String previous_service_center_name=null;
			String spare_part_names = "";
			HashMap<String, Object> service = null;
			while (rset.next()) {
				current_truck_id = rset.getInt("fk_truck_id");
				current_date=rset.getString("service_date");
				current_service_center_name=rset.getString("service_center_name");
				if(current_truck_id != previous_truck_id | !current_date.equals(previous_date)  | !current_service_center_name.equals(previous_service_center_name ))
						{
					
				    service = new HashMap<String, Object>();
					if(current_truck_id != 0 && current_date!=null){
						trucksService.add(service);
					}
					spare_part_names = "";
					
					service.put("fk_truck_id", rset.getInt("fk_truck_id"));
					//service.put("truck_number", rset.getString("truck_number"));
					service.put("service_center_name", rset.getString("service_center_name"));
					service.put("service_date", rset.getString("service_date"));
					service.put("service_total_cost", rset.getDouble("service_total_cost"));				
					service.put("description", rset.getString("description"));
					//service.put("name", rset.getString("name"));
					spare_part_names = rset.getString("name");
					service.put("type", rset.getString("type"));
					//service.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
				} 
					else {
					spare_part_names = spare_part_names +","+ "\n" + rset.getString("name");
					//service.put("name", rset.getString("name"));
				}
				service.put("name", spare_part_names);

				
				
				previous_truck_id = current_truck_id;
				previous_date=current_date;
				previous_service_center_name=current_service_center_name;
				
			}
			responseParameters.put("trucksService", trucksService);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
		}*/

	public static HashMap<String, Object> getTrucksAllotmentByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_ALLOTMENT_BY_DATE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksAllotment = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> trucks = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				trucks.put("driver_name", fname + " " + mname + " " + lname);
				trucks.put("truck_allotment_date", rset.getString("truck_allotment_date"));
				trucks.put("fk_driver_id", rset.getInt("fk_driver_id"));
				trucks.put("truck_number", rset.getString("truck_number"));
				trucks.put("first_name", rset.getString("first_name"));
				trucks.put("middle_name", rset.getString("middle_name"));
				trucks.put("last_name", rset.getString("last_name"));
				trucks.put("allotment_location", rset.getString("allotment_location"));
				trucks.put("fk_truck_id", rset.getInt("fk_truck_id"));

				trucksAllotment.add(trucks);
			}
			responseParameters.put("trucksAllotment", trucksAllotment);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTrucksHealthHistoryByDate(String lower_date, String upper_date,
			int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_HEALTH_HISTORY_BY_DATE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> healthHistory = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> health = new HashMap<String, Object>();
				health.put("description", rset.getString("description"));
				// health.put("fk_truck_id", rset.getInt("fk_truck_id"));
				health.put("date", rset.getString("date"));
				health.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
				health.put("name", rset.getString("name"));
				health.put("type", rset.getString("type"));

				healthHistory.add(health);
			}
			responseParameters.put("healthHistory", healthHistory);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckMaintenanceExpenditure(String lower_date, String upper_date,
			String fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_MAINTENANCE_EXPENDITURE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("fk_truck_id", fk_truck_id);
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> maintenance = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> expenditure = new HashMap<String, Object>();
				expenditure.put("truck_number", rset.getString("truck_number"));
				expenditure.put("item_names", rset.getString("item_names"));
				expenditure.put("cost", rset.getDouble("cost"));
				expenditure.put("remarks", rset.getString("remarks"));
				expenditure.put("date", rset.getString("date"));
				expenditure.put("expenditure_type", rset.getString("expenditure_type"));

				maintenance.add(expenditure);
			}
			responseParameters.put("maintenance", maintenance);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckMaintenanceExpenditureReport(String lower_date, String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
//		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_MAINTENANCE_EXPENDITURE_REPORT));
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> maintenance = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> expenditure = new HashMap<String, Object>();
				expenditure.put("truck_number", rset.getString("truck_number"));
				expenditure.put("item_names", rset.getString("item_names"));
				expenditure.put("cost", rset.getDouble("cost"));
				expenditure.put("remarks", rset.getString("remarks"));
				expenditure.put("date", rset.getString("date"));
				expenditure.put("expenditure_type", rset.getString("expenditure_type"));
				expenditure.put("truck_alias_name", rset.getString("truck_alias_name"));

				maintenance.add(expenditure);
			}
			responseParameters.put("maintenance", maintenance);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTrucksPartsQuoteByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_PARTS_QUOTE_BY_DATE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksPartsQuote = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("quote_number", rset.getString("quote_number"));
				truck.put("new_quote_date", rset.getString("new_quote_date"));
				truck.put("product_name", rset.getString("product_name"));
				truck.put("company_name", rset.getString("company_name"));
				truck.put("agent", rset.getString("agent"));
				truck.put("number_of_units", rset.getInt("number_of_units"));
				truck.put("unit_price", rset.getDouble("unit_price"));
				truck.put("total", rset.getDouble("total"));
				truck.put("no_of_days_for_delivery", rset.getInt("no_of_days_for_delivery"));
				truck.put("no_of_hours_for_delivery", rset.getInt("no_of_hours_for_delivery"));
				truck.put("mode_of_payment", rset.getString("mode_of_payment"));
				truck.put("type_of_payment", rset.getString("type_of_payment"));
				truck.put("valid_for_number_of_days", rset.getInt("valid_for_number_of_days"));
				truck.put("contact_number", rset.getString("contact_number"));
				truck.put("quote_status", rset.getString("quote_status"));

				trucksPartsQuote.add(truck);
			}
			responseParameters.put("trucksPartsQuote", trucksPartsQuote);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksMaintenanceCost(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_MAINTENANCE_COST));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);
			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksCost = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("fk_truck_id", rset.getString("fk_truck_id"));
				truck.put("truck_number", rset.getString("truck_number"));
				truck.put("cost", rset.getDouble("cost"));
				truck.put("truck_alias_name", rset.getString("truck_alias_name"));
				truck.put("maintenance", rset.getString("maintenance"));

				trucksCost.add(truck);
			}
			responseParameters.put("trucksCost", trucksCost);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksTripCount(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

	/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_TRIP_COUNT));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> tripCount = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("fk_truck_id", rset.getInt("fk_truck_id"));
				truck.put("truck_number", rset.getString("truck_number"));
				truck.put("trip_count", rset.getInt("trip_count"));
				truck.put("factory_name", rset.getString("factory_name"));
				truck.put("alias_name", rset.getString("alias_name"));

				tripCount.add(truck);
			}
			responseParameters.put("tripCount", tripCount);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksServiceByTruckNumber(String lower_date, String upper_date,int fk_truck_id)throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_SERVICE_BY_TRUCKNUMBER));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksService = new ArrayList<HashMap<String, Object>>();

			int previous_truck_id = 0;
			int current_truck_id = 0;
			String current_date=null;
			String previous_date=null;
			String current_service_center_name=null;
			String previous_service_center_name=null;
			String spare_part_names = "";
			HashMap<String, Object> service = null;
			while (rset.next()) {
				current_truck_id = rset.getInt("truck_id");
				current_date=rset.getString("service_start_date");
				current_service_center_name=rset.getString("service_center_name");
				if(current_truck_id != previous_truck_id | !current_date.equals(previous_date)  | !current_service_center_name.equals(previous_service_center_name ))
						{
					
				    service = new HashMap<String, Object>();
					if(current_truck_id != 0 && current_date!=null){
						trucksService.add(service);
					}
					spare_part_names = "";
					
					service.put("truck_id", rset.getInt("truck_id"));
					//service.put("truck_number", rset.getString("truck_number"));
					service.put("service_center_name", rset.getString("service_center_name"));
					service.put("service_start_date", rset.getString("service_start_date"));
					service.put("service_closed_date", rset.getString("service_closed_date"));
					service.put("service_total_cost", rset.getDouble("service_total_cost"));				
					service.put("service_status", rset.getString("service_status"));
					//service.put("name", rset.getString("name"));
					spare_part_names = rset.getString("name");
					//service.put("type", rset.getString("type"));
					//service.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
				} 
					else {
					spare_part_names = spare_part_names +","+ "\n" + rset.getString("name");
					//service.put("name", rset.getString("name"));
				}
				service.put("name", spare_part_names);

				
				previous_truck_id = current_truck_id;
				previous_date=current_date;
				previous_service_center_name=current_service_center_name;
				
			}
			responseParameters.put("trucksService", trucksService);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
		}
	
	
	public static HashMap<String, Object> getTrucksServiceByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_SERVICE_BY_DATE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksService = new ArrayList<HashMap<String, Object>>();

			int previous_truck_id = 0;
			int current_truck_id = 0;
			String current_date=null;
			String previous_date=null;
			String current_service_center_name=null;
			String previous_service_center_name = null;
			String spare_part_names = "";
			HashMap<String, Object> service = null;
			while (rset.next()) {
				
				current_truck_id = rset.getInt("truck_id");
				current_date=rset.getString("service_start_date");
				current_service_center_name=rset.getString("service_center_name");
				if(current_truck_id != previous_truck_id | !current_date.equals(previous_date)  | !current_service_center_name.equals(previous_service_center_name ))
						{
					
				    service = new HashMap<String, Object>();
					if(current_truck_id != 0 && current_date!=null){
						trucksService.add(service);
					}
					spare_part_names = "";
					
					service.put("truck_id", rset.getInt("truck_id"));
					service.put("truck_number", rset.getString("truck_number"));
					service.put("service_center_name", rset.getString("service_center_name"));
					service.put("service_start_date", rset.getString("service_start_date"));
					service.put("service_closed_date", rset.getString("service_closed_date"));
					service.put("service_total_cost", rset.getDouble("service_total_cost"));				
					service.put("service_status", rset.getString("service_status"));
					//service.put("name", rset.getString("name"));
					spare_part_names = rset.getString("name");
					//service.put("type", rset.getString("type"));
					//service.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
				} 
					else {
					spare_part_names = spare_part_names +","+ "\n" + rset.getString("name");
					//service.put("name", rset.getString("name"));
				}
				service.put("name", spare_part_names);
		
				
				
				previous_truck_id = current_truck_id;
				previous_date=current_date;
				previous_service_center_name=current_service_center_name;
				
			}
			responseParameters.put("trucksService", trucksService);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

//	public static HashMap<String, Object> getTrucksServiceByDate(String lower_date, String upper_date)
//			throws TransportException, SQLException {
//		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
//		Connection connection = JDBCConnectionUtils.getJDBCConnection();
//		String errorMessage = "";
//		responseParameters.put("result", true);
//		CallableStatement callableStatement = null;
//		ResultSet rset = null;
//
//		responseParameters = TransportGlobalUtils.shiroUserDetails();
//		String username = (String) responseParameters.get("username");
//
//		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
//			return responseParameters;
//		}
//
//		try {
//			callableStatement = connection
//					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_SERVICE_BY_DATE));
//			// HashMap<String, Object> cards = new HashMap<String, Object>();
//
//			callableStatement.setString("lower_date", lower_date);
//			callableStatement.setString("upper_date", upper_date);
//			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
//			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
//			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
//
//			rset = callableStatement.executeQuery();
//
//			// checking out param from stored procedure is null
//			JDBCConnectionUtils.validateCallableStatement(callableStatement);
//			responseParameters.put("result", true);
//
//			ArrayList<HashMap<String, Object>> trucksService = new ArrayList<HashMap<String, Object>>();
//
//			while (rset.next()) {
//				HashMap<String, Object> service = new HashMap<String, Object>();
//				service.put("fk_truck_id", rset.getInt("fk_truck_id"));
//				service.put("truck_number", rset.getString("truck_number"));
//				service.put("service_center_name", rset.getString("service_center_name"));
//				service.put("service_date", rset.getString("service_date"));
//				service.put("service_total_cost", rset.getDouble("service_total_cost"));
//				service.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
//				service.put("description", rset.getString("description"));
//				service.put("name", rset.getString("name"));
//				service.put("type", rset.getString("type"));
//
//				trucksService.add(service);
//			}
//			responseParameters.put("trucksService", trucksService);
//
//		} finally {
//			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
//		}
//
//		return responseParameters;
//	}
	
	/*public static HashMap<String, Object> getTrucksServiceByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_SERVICE_BY_DATE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksService = new ArrayList<HashMap<String, Object>>();

			int previous_truck_id = 0;
			int current_truck_id = 0;
			String current_date=null;
			String previous_date=null;
			String current_service_center_name=null;
			String previous_service_center_name = null;
			String spare_part_names = "";
			HashMap<String, Object> service = null;
			while (rset.next()) {
				
				current_truck_id = rset.getInt("fk_truck_id");
				current_date=rset.getString("service_date");
				current_service_center_name=rset.getString("service_center_name");
				if(current_truck_id != previous_truck_id | !current_date.equals(previous_date)  | !current_service_center_name.equals(previous_service_center_name ))
						{
					
				    service = new HashMap<String, Object>();
					if(current_truck_id != 0 && current_date!=null){
						trucksService.add(service);
					}
					spare_part_names = "";
					
					service.put("fk_truck_id", rset.getInt("fk_truck_id"));
					service.put("truck_number", rset.getString("truck_number"));
					service.put("service_center_name", rset.getString("service_center_name"));
					service.put("service_date", rset.getString("service_date"));
					service.put("service_total_cost", rset.getDouble("service_total_cost"));				
					service.put("description", rset.getString("description"));
					//service.put("name", rset.getString("name"));
					spare_part_names = rset.getString("name");
					service.put("type", rset.getString("type"));
					//service.put("fk_spare_parts_id", rset.getInt("fk_spare_parts_id"));
				} 
					else {
					spare_part_names = spare_part_names +","+ "\n" + rset.getString("name");
					//service.put("name", rset.getString("name"));
				}
				service.put("name", spare_part_names);
				previous_truck_id = current_truck_id;
				previous_date=current_date;
				previous_service_center_name=current_service_center_name;
				
			}
			responseParameters.put("trucksService", trucksService);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}*/

	public static HashMap<String, Object> updateTrucksAllotment(int trucks_allotment_id, String truck_allotment_date,
			int fk_driver_id, String allotment_location
	// String truck_number

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRUCKS_ALLOTMENT));

			callableStatement.setInt("trucks_allotment_id", trucks_allotment_id);
			callableStatement.setString("truck_allotment_date", truck_allotment_date);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("allotment_location", allotment_location);
			callableStatement.setString("username", username);
			// callableStatement.setString("truck_number", truck_number);

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
				responseParameters.put("Message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateTrucksDetails(int truck_id,String chassis_number,
			String engine_number,String registration_date,String permit_number,String permit_area,String permit_validity,String insurance_company_name,
			String insurance_policy_number,String insurance_date,String insurance_expiry_date,
			String fitness_certificate_number,String fitness_expire_date,String rc_image_bytes_string,
			String rc_image_type,String insurance_image_bytes_string,String insurance_image_type,
			String fitness_image_bytes_string,String fitness_image_type,String permit_image_bytes_string,
			String permit_image_type) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRUCKS_DETAILS));

		
			callableStatement.setInt("truck_id", truck_id);
			callableStatement.setString("chassis_number", chassis_number);
			callableStatement.setString("engine_number", engine_number);
			callableStatement.setString("registration_date", registration_date);
			callableStatement.setString("permit_number", permit_number);
			callableStatement.setString("permit_area", permit_area);
			callableStatement.setString("permit_validity", permit_validity);
			callableStatement.setString("insurance_company_name",insurance_company_name);
			callableStatement.setString("insurance_policy_number", insurance_policy_number);
			callableStatement.setString("insurance_date", insurance_date);
			callableStatement.setString("insurance_expiry_date", insurance_expiry_date);
			callableStatement.setString("fitness_certificate_number",fitness_certificate_number);
			callableStatement.setString("fitness_expire_date",fitness_expire_date);
			 
			//callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);

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
				String primary_id=callableStatement.getString("primary_id");
				String fitness_bucket="truckimages";
				String fitness_folder="fitness";
				String rc_bucket="truckimages";
				String rc_folder="rc";
				String insurance_bucket="truckimages";
				String insurance_folder="insurance";
				String permit_bucket="truckimages";
				String permit_folder="permit";
				
				String rc_uploaded_image_url="";
				String insurance_uploaded_image_url="";
				String fitness_uploaded_image_url="";
				String permit_uploaded_image_url="";
				
				if(!fitness_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(fitness_image_bytes_string,fitness_image_type,fitness_bucket,fitness_folder,primary_id);
					String fitnessFileName = fitness_folder + "/" + primary_id + "." + fitness_image_type;
					fitness_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+fitness_bucket+"/"+fitnessFileName;
				
				}
				if(!rc_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(rc_image_bytes_string,rc_image_type,rc_bucket,rc_folder,primary_id);
					String rcFileName = rc_folder + "/" + primary_id + "." + rc_image_type;
					rc_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+rc_bucket+"/"+rcFileName;
				
				}
				if(!insurance_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(insurance_image_bytes_string,insurance_image_type,insurance_bucket,insurance_folder,primary_id);
					String insuranceFileName = insurance_folder + "/" + primary_id + "." + insurance_image_type;
					insurance_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+insurance_bucket+"/"+insuranceFileName;
				
				}
				if(!permit_image_bytes_string.equals(""))
				{
				ImageService.uploadToAws(permit_image_bytes_string,permit_image_type,permit_bucket,permit_folder,primary_id);
				String permitFileName = permit_folder + "/" + primary_id + "." + permit_image_type;
				permit_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+permit_bucket+"/"+permitFileName;
			
				}
				ImageUploadDAO.updateTruckImageUpload(primary_id,rc_uploaded_image_url, insurance_uploaded_image_url,
						fitness_uploaded_image_url, permit_uploaded_image_url);
				
				responseParameters.put("Message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}


	public static HashMap<String, Object> updateTrucksHealthHistory(int trucks_health_history_id, String description,
			// String truck_number,
			String date, int fk_spare_parts_id) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRUCKS_HEALTH_HISTORY));

			callableStatement.setInt("trucks_health_history_id", trucks_health_history_id);
			callableStatement.setString("description", description);
			// callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("date", date);
			callableStatement.setInt("fk_spare_parts_id", fk_spare_parts_id);
			callableStatement.setString("username", username);

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
				responseParameters.put("Message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateTrucksPartsQuote(int trucks_parts_quote_id,
			// String quote_number ,
			String new_quote_date, String product_name, String company_name, String agent, int number_of_units,
			double unit_price, double total, int no_of_days_for_delivery, int no_of_hours_for_delivery,
			String mode_of_payment, String type_of_payment, int valid_for_number_of_days, String contact_number,
			String quote_status) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRUCKS_PARTS_QUOTE));

			callableStatement.setInt("trucks_parts_quote_id", trucks_parts_quote_id);
			// callableStatement.setString("quote_number", quote_number);
			callableStatement.setString("new_quote_date", new_quote_date);
			callableStatement.setString("product_name", product_name);
			callableStatement.setString("company_name", company_name);
			callableStatement.setString("agent", agent);
			callableStatement.setInt("number_of_units", number_of_units);
			callableStatement.setDouble("unit_price", unit_price);
			callableStatement.setDouble("total", total);
			callableStatement.setInt("no_of_days_for_delivery", no_of_days_for_delivery);
			callableStatement.setInt("no_of_hours_for_delivery", no_of_hours_for_delivery);
			callableStatement.setString("mode_of_payment", mode_of_payment);
			callableStatement.setString("type_of_payment", type_of_payment);
			callableStatement.setInt("valid_for_number_of_days", valid_for_number_of_days);
			callableStatement.setString("contact_number", contact_number);
			callableStatement.setString("quote_status", quote_status);
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
				responseParameters.put("Message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateTrucksService(int trucks_service_id, int fk_truck_id,
			String service_center_name, String service_date, double service_total_cost, int fk_spare_parts_id,
			String description

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRUCKS_SERVICE));

			callableStatement.setInt("trucks_service_id", trucks_service_id);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("service_center_name", service_center_name);
			callableStatement.setString("service_date", service_date);
			callableStatement.setDouble("service_total_cost", service_total_cost);
			callableStatement.setInt("fk_spare_parts_id", fk_spare_parts_id);
			callableStatement.setString("description", description);
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
				responseParameters.put("Message", "record successfully updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllTrucksDetails() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_TRUCKS_DETAILS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_trucks_id", rset.getInt("pk_trucks_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksDriverChange() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_DRIVER_CHANGE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("fk_truck_id", rset.getInt("fk_truck_id"));
				truck.put("truck_number", rset.getString("truck_number"));
				truck.put("pk_factory_dispatch_id", rset.getInt("pk_factory_dispatch_id"));
				truck.put("fk_driver_id", rset.getInt("fk_driver"));
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				String driver_name=fname+" "+mname+" "+lname;
				truck.put("driver_name", driver_name);
				
				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucks() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_trucks_id", rset.getInt("pk_trucks_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksAndOtherTrucks() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_AND_OTHER_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_trucks_id", rset.getInt("pk_trucks_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getAssignedAvailableTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ASSIGNED_AVAILABLE_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_trucks_id", rset.getInt("pk_trucks_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksGrid() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();
			ArrayList<String> truck_number = new ArrayList<String>();
			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_trucks_id", rset.getInt("pk_trucks_id"));
				truck.put("truck_number", rset.getString("truck_number"));
				truckDetails.add(truck);
				
				String trucks= rset.getString("truck_number");
				truck_number.add(trucks);				
				
			}
			responseParameters.put("TruckDetails", truckDetails);
			responseParameters.put("truck_number", truck_number);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_trucks_id", rset.getInt("pk_trucks_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getEngagedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ENGAGED_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_trucks_id", rset.getInt("pk_trucks_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryEngagedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_ENGAGED_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("fk_truck_id", rset.getInt("fk_truck_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getStorageEngagedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_STORAGE_ENGAGED_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("fk_truck_id", rset.getInt("fk_truck_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getSellerEngagedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SELLER_ENGAGED_TRUCKS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truckDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("fk_truck_id", rset.getInt("fk_truck_id"));
				truck.put("truck_number", rset.getString("truck_number"));

				truckDetails.add(truck);
			}
			responseParameters.put("TruckDetails", truckDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllAvailableAndEngagedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_AVAILABLE_AND_ENGAGED_TRUCKS_DETAILS));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> trucksDetails = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("truck_id", rset.getInt("trucks_id"));	
				truck.put("truck_number", rset.getString("truck_number"));
				truck.put("allotment_date", rset.getString("allotment_date"));
			    String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				truck.put("driver_name", fname+" "+mname+" "+lname);
				truck.put("driver_id", rset.getInt("driver_id"));					
				truck.put("trucks_status", rset.getString("trucks_status"));
				
				
				trucksDetails.add(truck);
			}
			responseParameters.put("TrucksDetails", trucksDetails);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllotedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALLOTED_TRUCKS));
			callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<String> truck_number = new ArrayList<String>();
	//		ArrayList<String> trucks = new ArrayList<String>();
			ArrayList<HashMap<String, Object>> trucksDetails = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				String truck= rset.getString("truck_number");
				truck_number.add(truck);
				
				HashMap<String, Object> trucks = new HashMap<String, Object>();
				trucks.put("truck_id", rset.getInt("fk_truck_id"));
				trucks.put("driver_id", rset.getInt("driver_id"));
	//			trucks.put("balance", rset.getDouble("balance"));
				String fname= rset.getString("first_name");
				String mname= rset.getString("middle_name");
				String lname= rset.getString("last_name");
				String name= fname+" "+mname+" "+lname;
				trucks.put("truck_number", rset.getString("truck_number"));
				trucks.put("driver_name", name);
				trucks.put("fk_association_id", rset.getInt("fk_association_id"));
				trucks.put("association_name", rset.getString("association_name"));

				trucksDetails.add(trucks);
			}
			responseParameters.put("truck_number", truck_number);
			responseParameters.put("trucksDetails", trucksDetails);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getAssignedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ASSIGNED_TRUCKS));
		//	callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> trucksDetails = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){

				HashMap<String, Object> trucks = new HashMap<String, Object>();
				trucks.put("fk_truck_id", rset.getInt("fk_truck_id"));
				trucks.put("pk_driver_allotment_id", rset.getInt("pk_driver_allotment_id"));
				trucks.put("truck_number", rset.getString("truck_number"));

				trucksDetails.add(trucks);
			}
			responseParameters.put("trucksDetails", trucksDetails);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryUnloadingTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_UNLOADING_TRUCKS));
			callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<String> truck_number = new ArrayList<String>();
			ArrayList<HashMap<String, Object>> trucksDetails = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				String truck= rset.getString("truck_number");
				truck_number.add(truck);
				
				HashMap<String, Object> trucks = new HashMap<String, Object>();
				trucks.put("truck_id", rset.getInt("truck_id"));
				trucks.put("truck_number", rset.getString("truck_number"));

				trucksDetails.add(trucks);
			}
			responseParameters.put("truck_number", truck_number);
			responseParameters.put("trucksDetails", trucksDetails);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchedTrucks() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCHED_TRUCKS));
			callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<String> truck_number = new ArrayList<String>();
			ArrayList<HashMap<String, Object>> trucksDetails = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				String truck= rset.getString("truck_number");
				truck_number.add(truck);
				
				HashMap<String, Object> trucks = new HashMap<String, Object>();
				trucks.put("truck_id", rset.getInt("truck_id"));
				trucks.put("truck_number", rset.getString("truck_number"));

				trucksDetails.add(trucks);
			}
			responseParameters.put("truck_number", truck_number);
			responseParameters.put("trucksDetails", trucksDetails);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckStatus(String truck_number) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			responseParameters.put("message", "No Login User Found");
			return responseParameters;
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_STATUS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> truck_allotment_status = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> status = new HashMap<String, Object>();
				HashMap<String, Object> allotment_status = new HashMap<String, Object>();
				String message=callableStatement.getString("message");
				/*if(message==null){
					responseParameters.put("message", "Not Found");
				}*/
				
				if(message.equals("success")){
					status.put("dispatch_status", rset.getString("dispatch_status"));
					status.put("dispatch_id", rset.getInt("dispatch_id"));
					status.put("dispatch_type", rset.getString("dispatch_type"));
					status.put("loading_date", rset.getString("loading_date"));
					status.put("truck_id", rset.getInt("truck_id"));
	
					responseParameters.put("truckStatus", status);
				}
				if(message.equals("driver alloted")){
					String fname=rset.getString("first_name");
					String mname=rset.getString("middle_name");
					String lname=rset.getString("last_name");
					String driver_name=fname+""+mname+""+lname;
					String association_name=rset.getString("association_name");
					status.put("driver_name", driver_name);
					status.put("driver_id", rset.getInt("driver_id"));
					status.put("truck_id", rset.getInt("truck_id"));
					status.put("association_id", rset.getInt("association_id"));
					status.put("association_name", association_name);
					String truck_status="In Transit to "+association_name;
					status.put("truck_status", truck_status);
	
					responseParameters.put("truckStatus", status);
				}
				if(message.equals("loading")){
					status.put("dispatch_status", rset.getString("dispatch_status"));
					status.put("dispatch_id", rset.getInt("dispatch_id"));
					status.put("dispatch_type", rset.getString("dispatch_type"));
					status.put("loading_date", rset.getString("loading_date"));
					status.put("truck_id", rset.getInt("truck_id"));
					status.put("driver_id", rset.getInt("driver_id"));
	
					responseParameters.put("truckStatus", status);
				}
				
				if(message.equals("unloading")){
					status.put("dispatch_status", rset.getString("dispatch_status"));
					status.put("dispatch_id", rset.getInt("dispatch_id"));
					status.put("loading_date", rset.getString("loading_date"));
					status.put("truck_id", rset.getInt("truck_id"));
	
					responseParameters.put("truckStatus", status);
				}
				
				if(message.equals("idle")){
					status.put("truck_status", rset.getString("truck_status"));
					status.put("truck_id", rset.getInt("truck_id"));
					status.put("truck_number", rset.getString("truck_number"));
	
					responseParameters.put("truckStatus", status);
				}
				if(message.equals("truck in both allotment and idle")){
					String fname=rset.getString("first_name");
					String mname=rset.getString("middle_name");
					String lname=rset.getString("last_name");
					String driver_name=fname+" "+mname+" "+lname;
					allotment_status.put("driver_name", driver_name);
					allotment_status.put("driver_id", rset.getInt("driver_id"));
					allotment_status.put("truck_id", rset.getInt("truck_id"));
					allotment_status.put("association_id", rset.getInt("association_id"));
					allotment_status.put("truck_number", rset.getString("truck_number"));					 
					allotment_status.put("allotment_type", rset.getString("allotment_type"));
					allotment_status.put("truck_status",rset.getString("truck_status"));
					String association_name=rset.getString("association_name");
					String association="In Transit to "+association_name;
					allotment_status.put("association", association);
					allotment_status.put("association_name",association_name);

					truck_allotment_status.add(allotment_status);
				}
				
				if(message.equals("truck in both allotment and trip")){
					status.put("dispatch_status", rset.getString("dispatch_status"));
					status.put("dispatch_id", rset.getInt("dispatch_id"));
					status.put("dispatch_type", rset.getString("dispatch_type"));
					status.put("truck_id", rset.getInt("truck_id"));
	
					responseParameters.put("truckStatus", status);
				}
				
				responseParameters.put("message", message);
				
				if(message.equals("truck in both allotment and idle")){
					responseParameters.put("truck_allotment_status", truck_allotment_status);
				}
			}
			

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addTruckIdleStatus(int fk_truck_id,String idle_reason) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCK_IDLE_STATUS));
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("idle_reason", idle_reason);
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
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	
	public static HashMap<String, Object> getTrucksGridCalendar(String truck_number,String month_start_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCKS_GRID_CALENDAR));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("month_start_date", month_start_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> gridDetails = new ArrayList<HashMap<String, Object>>();
		//	ArrayList<String> truck_number = new ArrayList<String>();
			while (rset.next()) {
				
				String calendar=rset.getString("str");
				String grid[] =calendar.split(",");
			//	String grid2[];
				for(int i=0; i<grid.length; i++){
					HashMap<String, Object> status = new HashMap<String, Object>();
					
					String grid2[] =grid[i].split("\\*");
					String grid3[]=grid2[0].split(" ");
					status.put("month_date", grid3[0]);	
					status.put("truck_status", grid2[1]);
					
					gridDetails.add(status);
				}	
				
			}
			responseParameters.put("gridDetails", gridDetails);
		}
		finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getExpiryTrucksPermit(String date_type)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_EXPIRY_TRUCKS_PERMIT));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("date_type", date_type);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksPermit = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> permit = new HashMap<String, Object>();
				permit.put("permit_number", rset.getString("permit_number"));
				permit.put("permit_validity", rset.getString("permit_validity"));
				permit.put("truck_number", rset.getString("truck_number"));
				permit.put("fk_truck_id", rset.getInt("fk_truck_id"));

				trucksPermit.add(permit);
			}
			responseParameters.put("trucksPermit", trucksPermit);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getExpiryTrucksInsurance(String date_type)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_EXPIRY_TRUCKS_INSURANCE));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("date_type", date_type);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksInsurance = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> insurance = new HashMap<String, Object>();
				insurance.put("insurance_company_name", rset.getString("insurance_company_name"));
				insurance.put("insurance_policy_number", rset.getString("insurance_policy_number"));
				insurance.put("insurance_expiry_date", rset.getString("insurance_expiry_date"));
				insurance.put("truck_number", rset.getString("truck_number"));
				insurance.put("fk_truck_id", rset.getInt("fk_truck_id"));

				trucksInsurance.add(insurance);
			}
			responseParameters.put("trucksInsurance", trucksInsurance);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getExpiryTrucksFitness(String date_type)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_EXPIRY_TRUCKS_FITNESS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("date_type", date_type);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> trucksFitness = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> fitness = new HashMap<String, Object>();
				fitness.put("fitness_certificate_number", rset.getString("fitness_certificate_number"));
				fitness.put("fitness_expiry_date", rset.getString("fitness_expire_date"));
				fitness.put("truck_number", rset.getString("truck_number"));
				fitness.put("fk_truck_id", rset.getInt("fk_truck_id"));

				trucksFitness.add(fitness);
			}
			responseParameters.put("trucksFitness", trucksFitness);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckCurrentDayStatusDetails(String operation,
			int operation_id)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_CURRENT_DAY_STATUS_DETAILS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("operation", operation);
			callableStatement.setInt("operation_id", operation_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

	//		ArrayList<HashMap<String, Object>> trucksFitness = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> currentStatus = new HashMap<String, Object>();
				
				String message=callableStatement.getString("message");
				if(message.equals("in transit to factory")){
					currentStatus.put("trip_fk_driver_id", rset.getInt("fk_driver_id"));
					String fname=rset.getString("first_name");
					String mname=rset.getString("middle_name");
					String lname=rset.getString("last_name");
					String driver_name=fname+" "+mname+" "+lname;
					currentStatus.put("trip_driver_name", driver_name);
					currentStatus.put("trip_start_location", rset.getString("start_location"));
					currentStatus.put("trip_time_of_start", rset.getString("time_of_start"));
					currentStatus.put("trip_destination", rset.getString("destination"));
					currentStatus.put("trip_estimated_km", rset.getDouble("estimated_km"));
					currentStatus.put("trip_starting_meter_reading", rset.getDouble("starting_meter_reading"));
					currentStatus.put("trip_freight", rset.getDouble("freight"));
					currentStatus.put("trip_status", rset.getString("trip_status"));
					
					responseParameters.put("currentStatus", currentStatus);
				}
				
				if(message.equals("unloading")){
					currentStatus.put("unloading_fk_driver_id", rset.getInt("fk_driver_id"));
					String fname=rset.getString("first_name");
					String mname=rset.getString("middle_name");
					String lname=rset.getString("last_name");
					String driver_name=fname+" "+mname+" "+lname;
					currentStatus.put("unloading_driver_name", driver_name);
					currentStatus.put("unloading_invoice_number", rset.getString("invoice_number"));
					currentStatus.put("unloading_start_location", rset.getString("start_location"));
					currentStatus.put("unloading_status_date", rset.getString("unloading_status_date"));
					currentStatus.put("unloading_unload_location", rset.getString("unload_location"));
					currentStatus.put("unloading_estimated_km", rset.getDouble("estimated_km"));
					currentStatus.put("unloading_load_quantity", rset.getDouble("load_quantity"));
					currentStatus.put("unloading_starting_meter_reading", rset.getDouble("starting_meter_reading"));
					currentStatus.put("unloading_freight", rset.getDouble("freight"));
					currentStatus.put("unloading_dispatch_status", rset.getString("dispatch_status"));
	
					responseParameters.put("currentStatus", currentStatus);
				}
				
				if(message.equals("loading")){
					currentStatus.put("loading_fk_driver_id", rset.getInt("fk_driver_id"));
					String fname=rset.getString("first_name");
					String mname=rset.getString("middle_name");
					String lname=rset.getString("last_name");
					String driver_name=fname+" "+mname+" "+lname;
					currentStatus.put("loading_driver_name", driver_name);
					currentStatus.put("loading_date", rset.getString("loading_date"));
					currentStatus.put("loading_dispatch_status", rset.getString("dispatch_status"));
					currentStatus.put("association_name", rset.getString("association_name"));
					currentStatus.put("fk_association_id", rset.getInt("fk_association_id"));
	
					responseParameters.put("currentStatus", currentStatus);
				}
				
				if(message.equals("waiting")){
					currentStatus.put("waiting_fk_driver_id", rset.getInt("fk_driver_id"));
					String fname=rset.getString("first_name");
					String mname=rset.getString("middle_name");
					String lname=rset.getString("last_name");
					String driver_name=fname+" "+mname+" "+lname;
					currentStatus.put("waiting_driver_name", driver_name);
					currentStatus.put("waiting_invoice_number", rset.getString("invoice_number"));
					currentStatus.put("waiting_start_location", rset.getString("start_location"));
					currentStatus.put("waiting_status_date", rset.getString("waiting_status_date"));
					currentStatus.put("waiting_unload_location", rset.getString("unload_location"));
					currentStatus.put("waiting_estimated_km", rset.getDouble("estimated_km"));
					currentStatus.put("waiting_load_quantity", rset.getDouble("load_quantity"));
					currentStatus.put("waiting_starting_meter_reading", rset.getDouble("starting_meter_reading"));
					currentStatus.put("waiting_freight", rset.getDouble("freight"));
					currentStatus.put("waiting_dispatch_status", rset.getString("dispatch_status"));
	
					responseParameters.put("currentStatus", currentStatus);
				}
				
				if(message.equals("in transit from factory")){
					currentStatus.put("from_factory_fk_driver_id", rset.getInt("fk_driver_id"));
					String fname=rset.getString("first_name");
					String mname=rset.getString("middle_name");
					String lname=rset.getString("last_name");
					String driver_name=fname+" "+mname+" "+lname;
					currentStatus.put("from_factory_driver_name", driver_name);
					currentStatus.put("from_factory_invoice_number", rset.getString("invoice_number"));
					currentStatus.put("from_factory_start_location", rset.getString("start_location"));
					currentStatus.put("in_transit_from_factory_date", rset.getString("in_transit_from_factory_date"));
					currentStatus.put("from_factory_unload_location", rset.getString("unload_location"));
					currentStatus.put("from_factory_estimated_km", rset.getDouble("estimated_km"));
					currentStatus.put("from_factory_load_quantity", rset.getDouble("load_quantity"));
					currentStatus.put("from_factory_starting_meter_reading", rset.getDouble("starting_meter_reading"));
					currentStatus.put("from_factory_freight", rset.getDouble("freight"));
					currentStatus.put("from_factory_dispatch_status", rset.getString("dispatch_status"));
	
					responseParameters.put("currentStatus", currentStatus);
				}
					responseParameters.put("message", message);
			}
			

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addTruckMaintenanceExpenditure(String item_cost,String date,String remarks,String  item_name,
			int fk_truck_id,String expenditure_type) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCK_MAINTENANCE_EXPENDITURE));

			callableStatement.setString("cost", item_cost);
			callableStatement.setString("date", date);
			callableStatement.setString("item_names", item_name);
			callableStatement.setString("remarks", remarks);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("expenditure_type", expenditure_type);
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

}
