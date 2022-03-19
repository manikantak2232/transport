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
import com.pixelbox.dao.ImageUploadDAO;

public class DriverDAO {

	final static Logger log = Logger.getLogger(DriverDAO.class);

	public static HashMap<String, Object> addDriverDetails(String first_name, String middle_name, String last_name,
			String date_of_birth, String aadhar_card, String driving_license, String non_transport_license_expiry_date,
			String transport_license_expiry_date, String phone_number, String address,String aadhar_image_bytes_string,
			String aadhar_image_type,String license_image_bytes_string,String license_image_type)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DRIVER_DETAILS));

			callableStatement.setString("first_name", first_name);
			callableStatement.setString("middle_name", middle_name);
			callableStatement.setString("last_name", last_name);
			callableStatement.setString("date_of_birth", date_of_birth);
			callableStatement.setString("aadhar_card", aadhar_card);
			callableStatement.setString("driving_license", driving_license);
			callableStatement.setString("non_transport_license_expiry_date", non_transport_license_expiry_date);
			callableStatement.setString("transport_license_expiry_date", transport_license_expiry_date);
			callableStatement.setString("phone_number", phone_number);
			callableStatement.setString("address", address);
			// callableStatement.setString("website", website);
			// callableStatement.setString("address", address);
			// callableStatement.setString("username", username);

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
				String aadhar_bucket="driversimages";
				String aadhar_folder="aadhar-cards";
				String license_bucket="driversimages";
				String license_folder="driving-licenses";
				
				String aadhar_uploaded_image_url="";
				String license_uploaded_image_url="";
				
				if(!aadhar_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(aadhar_image_bytes_string,aadhar_image_type,aadhar_bucket,aadhar_folder,primary_id);
					String aadharFileName = aadhar_folder + "/" + primary_id + "." + aadhar_image_type;
					aadhar_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+aadhar_bucket+"/"+aadharFileName;
				}
				if(!license_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(license_image_bytes_string,license_image_type,license_bucket,license_folder,primary_id);
					String licenseFileName = license_folder + "/" + primary_id + "." + license_image_type;
					license_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+license_bucket+"/"+licenseFileName;
				}
				
				ImageUploadDAO.addDriverImageUpload(primary_id,aadhar_uploaded_image_url, license_uploaded_image_url);
			      
				
				responseParameters.put("Message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getDriverDetails(int fk_driver_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_DETAILS));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

//			ArrayList<HashMap<String, Object>> driverDetails1 = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> driver = new HashMap<String, Object>();
//				String fname=rset.getString("first_name");
//				String mname=rset.getString("middle_name");
//				String lname=rset.getString("last_name");
//				driver.put("driver_name", fname+" "+mname+" "+lname);
				driver.put("first_name", rset.getString("first_name"));
				driver.put("middle_name", rset.getString("middle_name"));
				driver.put("last_name", rset.getString("last_name"));
             	driver.put("date_of_birth", rset.getString("date_of_birth"));
				driver.put("aadhar_card", rset.getString("aadhar_card"));
				driver.put("driving_license", rset.getString("driving_license"));
				driver.put("non_transport_license_expiry_date", rset.getString("non_transport_license_expiry_date"));
				driver.put("transport_license_expiry_date", rset.getString("transport_license_expiry_date"));
				driver.put("phone_number", rset.getString("phone_number"));
				driver.put("address", rset.getString("address"));
				driver.put("aadhar_image_url", rset.getString("aadhar_image_url"));
				driver.put("license_image_url", rset.getString("license_image_url"));
				
			//	driverDetails1.add(driver);
				responseParameters.put("DriverDetails", driver);
			}
	//		responseParameters.put("DriverDetails1", driverDetails1);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getDriverFullDetails() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_FULL_DETAILS));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
		
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> driverDetails = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> driver = new HashMap<String, Object>();
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				driver.put("driver_name", fname+" "+mname+" "+lname);
             	driver.put("date_of_birth", rset.getString("date_of_birth"));
				driver.put("aadhar_card", rset.getString("aadhar_card"));
				driver.put("driving_license", rset.getString("driving_license"));
				driver.put("non_transport_license_expiry_date", rset.getString("non_transport_license_expiry_date"));
				driver.put("transport_license_expiry_date", rset.getString("transport_license_expiry_date"));
				driver.put("phone_number", rset.getString("phone_number"));
				driver.put("address", rset.getString("address"));
				driver.put("aadhar_image_url", rset.getString("aadhar_image_url"));
				driver.put("license_image_url", rset.getString("license_image_url"));
				driver.put("svtc_id", rset.getString("svtc_id"));
				
				driverDetails.add(driver);
		//		responseParameters.put("DriverDetails", driver);
			}
			responseParameters.put("DriverDetails", driverDetails);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllDriverDetails() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_DRIVER_DETAILS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> driverDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> driver = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				
				/*if(mname.equals("")||mname.equals("null")){
					mname="";
				}
				if(lname.equals("")||lname.equals("null")){
					lname="";
				}*/
				driver.put("driver_name", fname + " " + mname + " " + lname);

				driver.put("driver_id", rset.getInt("pk_driver_details_id"));

				driverDetails.add(driver);
			}
			responseParameters.put("DriverDetails", driverDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getAvailableDrivers() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_AVAILABLE_DRIVERS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> driverDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> driver = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				driver.put("driver_name", fname + " " + mname + " " + lname);

				driver.put("driver_id", rset.getInt("pk_driver_details_id"));

				driverDetails.add(driver);
			}
			responseParameters.put("DriverDetails", driverDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getDrivers() throws TransportException, SQLException {
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
			callableStatement = connection.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVERS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> driverDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> driver = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				driver.put("driver_name", (fname + " " + mname + " " + lname).replaceAll("\\s+", " "));
				driver.put("driver_id", rset.getInt("pk_driver_details_id"));

				driverDetails.add(driver);
			}
			responseParameters.put("DriverDetails", driverDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateDriverDetails(
			int fk_driver_id,
			String first_name,
			String middle_name, 
			String last_name, 
			String date_of_birth,
			String aadhar_card,
			String driving_license,	
			String non_transport_license_expiry_date,
			String transport_license_expiry_date,
			String phone_number,
			String address,
			String aadhar_image_bytes_string,
			String aadhar_image_type,
			String license_image_bytes_string,
			String license_image_type
				) throws TransportException, SQLException{

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
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_DRIVER_DETAILS));
				callableStatement.setInt("fk_driver_id", fk_driver_id);
				callableStatement.setString("first_name", first_name);
				callableStatement.setString("middle_name", middle_name);			
				callableStatement.setString("last_name", last_name);
				callableStatement.setString("date_of_birth",date_of_birth);
				callableStatement.setString("aadhar_card", aadhar_card);
				callableStatement.setString("driving_license", driving_license);	
				callableStatement.setString("non_transport_license_expiry_date",non_transport_license_expiry_date);
				callableStatement.setString("transport_license_expiry_date",transport_license_expiry_date);
				callableStatement.setString("phone_number", phone_number);
				callableStatement.setString("address", address);				

				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);

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
					
					String primary_id=callableStatement.getString("primary_id");
					String aadhar_bucket="driversimages";
					String aadhar_folder="aadhar-cards";
					String license_bucket="driversimages";
					String license_folder="driving-licenses";
					
					String aadhar_uploaded_image_url="";
					String license_uploaded_image_url="";
					
					if(!aadhar_image_bytes_string.equals(""))
					{
						ImageService.uploadToAws(aadhar_image_bytes_string,aadhar_image_type,aadhar_bucket,aadhar_folder,primary_id);
						String aadharFileName = aadhar_folder + "/" + primary_id + "." + aadhar_image_type;
						aadhar_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+aadhar_bucket+"/"+aadharFileName;
					}
					if(!license_image_bytes_string.equals(""))
					{
						ImageService.uploadToAws(license_image_bytes_string,license_image_type,license_bucket,license_folder,primary_id);
						String licenseFileName = license_folder + "/" + primary_id + "." + license_image_type;
						license_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+license_bucket+"/"+licenseFileName;
					}
					
					ImageUploadDAO.updateDriverImageUpload(primary_id,aadhar_uploaded_image_url, license_uploaded_image_url);
				      
					
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

	public static HashMap<String, Object> getDriverSalarySlip(String lower_date, String upper_date, int fk_driver_id)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		/*
		 * responseParameters = TransportGlobalUtils.shiroUserDetails(); String
		 * username = (String) responseParameters.get("username");
		 * 
		 * if
		 * (!Boolean.parseBoolean(responseParameters.get("result").toString()))
		 * { return responseParameters; }
		 */
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_SALARY_SLIP));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setInt("fk_driver_id", fk_driver_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> salaries = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> slip = new HashMap<String, Object>();
				slip.put("dispatch_date", rset.getString("dispatch_date"));
				slip.put("fk_truck_id", rset.getInt("fk_truck_id"));
				slip.put("truck_number", rset.getString("truck_number"));
				slip.put("start_location", rset.getString("start_location"));
				slip.put("unload_location", rset.getString("unload_location"));
				slip.put("estimated_km", rset.getInt("estimated_km"));
				slip.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				slip.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				slip.put("load_quantity", rset.getInt("load_quantity"));
				slip.put("freight", rset.getDouble("freight"));
				slip.put("expenditure", rset.getDouble("expenditure"));
				slip.put("checked_kms", rset.getInt("checked_kms"));
				slip.put("fuel_quantity", rset.getDouble("fuel_quantity"));
				slip.put("fuel_rate", rset.getDouble("fuel_rate"));
				slip.put("amount", rset.getDouble("amount"));

				salaries.add(slip);

			}
			responseParameters.put("DriversSalary", salaries);

		} finally {

			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addDriverAllotmentToTruck(int fk_truck_id, int fk_driver_id,
			String driver_allotment_date, int fk_association_id

	) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DRIVER_ALLOTMENT_TO_TRUCK));

			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("driver_allotment_date", driver_allotment_date);
			callableStatement.setInt("fk_association_id", fk_association_id);

			callableStatement.setString("username", username);

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
	
	public static HashMap<String, Object> getDriverSalary(int fk_driver_id)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		
		  responseParameters = TransportGlobalUtils.shiroUserDetails(); String
		  username = (String) responseParameters.get("username");
		  
		  if(!Boolean.parseBoolean(responseParameters.get("result").toString()))
		  	{ 
			  return responseParameters; 
			}
		 
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_SALARY));

			callableStatement.setInt("fk_driver_id", fk_driver_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> salaries = new ArrayList<HashMap<String, Object>>();
	
			while (rset.next()) {
				HashMap<String, Object> salary = new HashMap<String, Object>();
				salary.put("pk_factory_dispatch_id", rset.getInt("pk_factory_dispatch_id"));
				salary.put("fk_truck_id", rset.getInt("fk_truck_id"));
				salary.put("truck_number", rset.getString("truck_number"));
				salary.put("start_location", rset.getString("start_location"));
				salary.put("unload_location", rset.getString("unload_location"));
				salary.put("invoice_number", rset.getString("invoice_number"));
				salary.put("freight", rset.getDouble("freight"));
				salary.put("loading_date", rset.getString("loading_date"));
				salary.put("closed_date", rset.getString("closed_date"));
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				salary.put("driver_name", fname+" "+mname+" "+lname);

				salaries.add(salary);

			}
			responseParameters.put("DriversSalary", salaries);

		} finally {

			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getavailableAndEngagedDrivers() throws TransportException, SQLException {
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
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_AVAILABLE_AND_ENGAGED_DRIVER_DETAILS));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> driverDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> driver = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				driver.put("driver_name", fname + " " + mname + " " + lname);
				driver.put("driver_id", rset.getInt("pk_driver_details_id"));
				driver.put("phone_number", rset.getString("phone_number"));
				driver.put("driver_status", rset.getString("driver_status"));

				driverDetails.add(driver);
			}
			responseParameters.put("DriverDetails", driverDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> driverSalaryPay( int dispatch_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_DRIVER_SALARY_PAYMENT));

			callableStatement.setInt("dispatch_id", dispatch_id);
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
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addDriverSalaryHistory( int fk_driver_id,double no_of_days,double salary_per_day,
			double commission,double short_bags,double cost_per_bag,double short_fuel,
			double cost_per_liter,double total_salary)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DRIVER_SALARY_PAYMENT_HISTORY));

			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setDouble("no_of_days", no_of_days);
			callableStatement.setDouble("salary_per_day", salary_per_day);
			callableStatement.setDouble("commission", commission);
			callableStatement.setDouble("short_bags", short_bags);
			callableStatement.setDouble("cost_per_bag", cost_per_bag);
			callableStatement.setDouble("short_fuel", short_fuel);
			callableStatement.setDouble("cost_per_liter", cost_per_liter);
			callableStatement.setDouble("total_salary", total_salary);
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
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getDriverAllotmentToTruckByDate(String lower_date, String upper_date)
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
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_ALLOTMENT_TO_TRUCK_BY_DATE));
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

			ArrayList<HashMap<String, Object>> driverAllotment = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> driver = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				driver.put("driver_name", fname + " " + mname + " " + lname);
				driver.put("driver_allotment_date", rset.getString("driver_allotment_date"));
				driver.put("fk_driver_id", rset.getInt("fk_driver_id"));
				driver.put("truck_number", rset.getString("truck_number"));
				driver.put("first_name", rset.getString("first_name"));
				driver.put("middle_name", rset.getString("middle_name"));
				driver.put("last_name", rset.getString("last_name"));
				driver.put("fk_truck_id", rset.getInt("fk_truck_id"));

				driverAllotment.add(driver);
			}
			responseParameters.put("DriverAllotment", driverAllotment);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getDriverAllotmentToTruck(int pk_driver_allotment_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_ALLOTMENT_TO_TRUCK));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setInt("pk_driver_allotment_id", pk_driver_allotment_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> driverAllotment = new
			// ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> driver = new HashMap<String, Object>();
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				driver.put("driver_name", fname + " " + mname + " " + lname);
				driver.put("driver_allotment_date", rset.getString("driver_allotment_date"));
				driver.put("pk_driver_allotment_id", rset.getInt("pk_driver_allotment_id"));
				driver.put("fk_association_id", rset.getInt("fk_association_id"));
				driver.put("association_name", rset.getString("association_name"));
				driver.put("driver_id", rset.getInt("fk_driver_id"));

				responseParameters.put("DriverAllotment", driver);
				// driverAllotment.add(driver);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateDriverAllotmentToTruck(int pk_driver_allotment_id,
			String driver_allotment_date, int fk_driver_id, int fk_association_id, int driver_status_id)
			throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_DRIVER_ALLOTMENT_TO_TRUCK));
			callableStatement.setInt("pk_driver_allotment_id", pk_driver_allotment_id);
			callableStatement.setString("driver_allotment_date", driver_allotment_date);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setInt("fk_association_id", fk_association_id);
			callableStatement.setInt("driver_status_id", driver_status_id);
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
	
	public static HashMap<String, Object> getDriverBalance(int fk_driver_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_BALANCE));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
	
			while(rset.next()){
				HashMap<String, Object> driver = new HashMap<String, Object>();

				driver.put("balance", rset.getDouble("balance"));

				responseParameters.put("DriverDetails", driver);
			}
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllDriverBalance() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_DRIVER_BALANCE));
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> driver=new ArrayList<HashMap<String,Object>>();
			while(rset.next()){
				HashMap<String, Object> details = new HashMap<String, Object>();

				details.put("pk_current_driver_balance_id", rset.getInt("pk_current_driver_balance_id"));
				details.put("fk_driver_id", rset.getInt("fk_driver_id"));
				details.put("balance", rset.getDouble("balance"));
				
				driver.add(details);
				
			}
			
			responseParameters.put("DriverDetails", driver);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

}
