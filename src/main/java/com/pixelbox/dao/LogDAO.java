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

public class LogDAO {

	final static Logger log = Logger.getLogger(StorageDAO.class);

	public static HashMap<String, Object> getActionLog(String action_item, String invoice_number, int fk_truck_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ACTIONS_LOG));
			// HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("action_item", action_item);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> actionLog = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> action = new HashMap<String, Object>();
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				String uname = fname+" "+mname+" "+lname;
				action.put("action_activity", rset.getString("action_activity"));
				action.put("uname", uname);
				action.put("date_time", rset.getString("date_time"));

				actionLog.add(action);
			}
			responseParameters.put("StorageFuel", actionLog);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	/*
	 * public static HashMap<String, Object> getStorageIncomingLoadByDate(String
	 * lower_date, String upper_date) throws TransportException, SQLException {
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>(); Connection connection =
	 * JDBCConnectionUtils.getJDBCConnection(); String errorMessage = "";
	 * responseParameters.put("result", true); CallableStatement
	 * callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * GET_STORAGE_INCOMING_LOAD_BY_DATE)); // HashMap<String, Object> cards =
	 * new HashMap<String, Object>();
	 * 
	 * callableStatement.setString("lower_date",lower_date);
	 * callableStatement.setString("upper_date",upper_date);
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * // checking out param from stored procedure is null
	 * JDBCConnectionUtils.validateCallableStatement(callableStatement);
	 * responseParameters.put("result", true);
	 * 
	 * ArrayList<HashMap<String, Object>> storageIncomingLoad = new
	 * ArrayList<HashMap<String, Object>>();
	 * 
	 * while(rset.next()){ HashMap<String, Object> load = new HashMap<String,
	 * Object>(); String fname=rset.getString("first_name"); String
	 * mname=rset.getString("middle_name"); String
	 * lname=rset.getString("last_name"); load.put("invoice_number",
	 * rset.getString("invoice_number")); load.put("date",
	 * rset.getString("date")); load.put("from_address",
	 * rset.getString("from_address")); load.put("to_address",
	 * rset.getString("to_address")); load.put("brand_name",
	 * rset.getString("brand_name")); load.put("cost_per_bag",
	 * rset.getDouble("cost_per_bag")); load.put("number_of_bags",
	 * rset.getDouble("number_of_bags")); load.put("total_weight",
	 * rset.getDouble("total_weight")); load.put("total_value",
	 * rset.getDouble("total_value")); load.put("fk_truck_id",
	 * rset.getInt("fk_truck_id")); load.put("fk_driver_id",
	 * rset.getInt("fk_driver_id")); load.put("driver_name",
	 * fname+" "+mname+" "+lname); load.put("first_name",
	 * rset.getString("first_name")); load.put("middle_name",
	 * rset.getString("middle_name")); load.put("last_name",
	 * rset.getString("last_name")); load.put("time_of_arrival",
	 * rset.getString("time_of_arrival")); load.put("reading_km",
	 * rset.getDouble("reading_km")); load.put("status",
	 * rset.getString("status"));
	 * 
	 * 
	 * storageIncomingLoad.add(load); }
	 * responseParameters.put("StorageIncomingLoad", storageIncomingLoad);
	 * 
	 * } finally { JDBCConnectionUtils.closeResultSetAndStatement(rset,
	 * callableStatement); }
	 * 
	 * return responseParameters; }
	 * 
	 * public static HashMap<String, Object> getStorageInvoiceByDate(String
	 * lower_date, String upper_date) throws TransportException, SQLException {
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>(); Connection connection =
	 * JDBCConnectionUtils.getJDBCConnection(); String errorMessage = "";
	 * responseParameters.put("result", true); CallableStatement
	 * callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * GET_STORAGE_INVOICE_BY_DATE)); // HashMap<String, Object> cards = new
	 * HashMap<String, Object>();
	 * 
	 * callableStatement.setString("lower_date",lower_date);
	 * callableStatement.setString("upper_date",upper_date);
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * // checking out param from stored procedure is null
	 * JDBCConnectionUtils.validateCallableStatement(callableStatement);
	 * responseParameters.put("result", true);
	 * 
	 * 
	 * ArrayList<HashMap<String, Object>> storageInvoice = new
	 * ArrayList<HashMap<String, Object>>();
	 * 
	 * while(rset.next()){ HashMap<String, Object> invoice = new HashMap<String,
	 * Object>(); String fname=rset.getString("customer_first_name"); String
	 * mname=rset.getString("customer_middle_name"); String
	 * lname=rset.getString("customer_last_name"); invoice.put("customer_name",
	 * fname+" "+mname+" "+lname); invoice.put("customer_first_name",
	 * rset.getString("customer_first_name"));
	 * invoice.put("customer_middle_name",
	 * rset.getString("customer_middle_name"));
	 * invoice.put("customer_last_name", rset.getString("customer_last_name"));
	 * invoice.put("invoice_number", rset.getString("invoice_number"));
	 * invoice.put("invoice_date", rset.getString("invoice_date"));
	 * invoice.put("name_of_brand", rset.getString("name_of_brand"));
	 * invoice.put("from_address", rset.getString("from_address"));
	 * invoice.put("to_address", rset.getString("to_address"));
	 * invoice.put("phone_number", rset.getString("phone_number"));
	 * invoice.put("email", rset.getString("email"));
	 * invoice.put("number_of_bags", rset.getInt("number_of_bags"));
	 * invoice.put("total_weight", rset.getDouble("total_weight"));
	 * invoice.put("cost_per_bag", rset.getDouble("cost_per_bag"));
	 * invoice.put("total_value", rset.getDouble("total_value"));
	 * invoice.put("sales_manager_name", rset.getString("sales_manager_name"));
	 * invoice.put("freight_rate", rset.getDouble("freight_rate"));
	 * invoice.put("approve_status", rset.getString("approve_status"));
	 * 
	 * 
	 * storageInvoice.add(invoice); } responseParameters.put("StorageInvoice",
	 * storageInvoice);
	 * 
	 * } finally { JDBCConnectionUtils.closeResultSetAndStatement(rset,
	 * callableStatement); }
	 * 
	 * return responseParameters; }
	 * 
	 * 
	 * public static HashMap<String, Object> updateStorageDispatch( int
	 * storage_dispatch_id, int fk_truck_id, int fk_driver_id, // String
	 * invoice_number, String start_location, String dispatch_date, String
	 * time_of_start, String unload_location, int estimated_dispatch_days, int
	 * estimated_dispatch_hours, double estimated_km, double
	 * starting_meter_reading, double closing_meter_reading, double
	 * load_quantity, double freight, double advance, double balance // String
	 * dispatch_status ) throws TransportException, SQLException{
	 * 
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>();
	 * 
	 * Connection connection = JDBCConnectionUtils.getJDBCConnection(); String
	 * errorMessage = ""; responseParameters.put("result", true);
	 * CallableStatement callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * UPDATE_STORAGE_DISPATCH));
	 * 
	 * callableStatement.setInt("storage_dispatch_id", storage_dispatch_id);
	 * callableStatement.setInt("fk_truck_id", fk_truck_id);
	 * callableStatement.setInt("fk_driver_id", fk_driver_id); //
	 * callableStatement.setString("invoice_number", invoice_number);
	 * callableStatement.setString("start_location", start_location);
	 * callableStatement.setString("dispatch_date", dispatch_date);
	 * callableStatement.setString("time_of_start", time_of_start);
	 * callableStatement.setString("unload_location", unload_location);
	 * callableStatement.setInt("estimated_dispatch_days",
	 * estimated_dispatch_days);
	 * callableStatement.setInt("estimated_dispatch_hours",
	 * estimated_dispatch_hours); callableStatement.setDouble("estimated_km",
	 * estimated_km); callableStatement.setDouble("starting_meter_reading",
	 * starting_meter_reading);
	 * callableStatement.setDouble("closing_meter_reading",
	 * closing_meter_reading); callableStatement.setDouble("load_quantity",
	 * load_quantity); callableStatement.setDouble("freight", freight);
	 * callableStatement.setDouble("advance", advance);
	 * callableStatement.setDouble("balance", balance);
	 * callableStatement.setString("username", username);
	 * 
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * if(!callableStatement.getBoolean("outResult")) {
	 * responseParameters.put("result", false);
	 * responseParameters.put("errorCode",
	 * TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
	 * responseParameters.put("errorMessage",
	 * callableStatement.getString("message")); if
	 * (log.isEnabledFor(Level.ERROR)) {
	 * log.error(callableStatement.getString("message")); } return
	 * responseParameters; }else{ responseParameters.put("Message",
	 * "record successfully updated" ); } } catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * finally { JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement,
	 * connection); }
	 * 
	 * return responseParameters; }
	 * 
	 * 
	 * public static HashMap<String, Object> updateStorageFuel( int
	 * storage_fuel_id, // int fk_truck_id, double fuel_quantity, double
	 * fuel_rate, double amount, String fuel_station_location, String date )
	 * throws TransportException, SQLException{
	 * 
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>();
	 * 
	 * Connection connection = JDBCConnectionUtils.getJDBCConnection(); String
	 * errorMessage = ""; responseParameters.put("result", true);
	 * CallableStatement callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * UPDATE_STORAGE_FUEL)); callableStatement.setInt("storage_fuel_id",
	 * storage_fuel_id); // callableStatement.setInt("truck_number",
	 * fk_truck_id); callableStatement.setDouble("fuel_quantity",
	 * fuel_quantity); callableStatement.setDouble("fuel_rate", fuel_rate);
	 * callableStatement.setDouble("amount", amount);
	 * callableStatement.setString("fuel_station_location",
	 * fuel_station_location); callableStatement.setString("date", date);
	 * callableStatement.setString("username", username);
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * if(!callableStatement.getBoolean("outResult")) {
	 * responseParameters.put("result", false);
	 * responseParameters.put("errorCode",
	 * TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
	 * responseParameters.put("errorMessage",
	 * callableStatement.getString("message")); if
	 * (log.isEnabledFor(Level.ERROR)) {
	 * log.error(callableStatement.getString("message")); } return
	 * responseParameters; }else{ responseParameters.put("Message",
	 * "record successfully updated" ); } } catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * finally { JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement,
	 * connection); }
	 * 
	 * return responseParameters; }
	 * 
	 * 
	 * 
	 * 
	 * public static HashMap<String, Object> updateStorageInvoice( int
	 * storage_invoice_id, // String invoice_number, String customer_first_name,
	 * String customer_middle_name, String customer_last_name, String
	 * name_of_brand, String from_address, String to_address, String
	 * invoice_date, String phone_number, String email, int number_of_bags,
	 * double total_weight, double cost_per_bag, double total_value, String
	 * sales_manager_name, double freight_rate, String approve_status ) throws
	 * TransportException, SQLException{
	 * 
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>();
	 * 
	 * Connection connection = JDBCConnectionUtils.getJDBCConnection(); String
	 * errorMessage = ""; responseParameters.put("result", true);
	 * CallableStatement callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * UPDATE_STORAGE_INVOICE)); callableStatement.setInt("storage_invoice_id",
	 * storage_invoice_id); // callableStatement.setString("invoice_number",
	 * invoice_number); callableStatement.setString("customer_first_name",
	 * customer_first_name); callableStatement.setString("customer_middle_name",
	 * customer_middle_name); callableStatement.setString("customer_last_name",
	 * customer_last_name); callableStatement.setString("invoice_date",
	 * invoice_date); callableStatement.setString("name_of_brand",
	 * name_of_brand); callableStatement.setString("from_address",
	 * from_address); callableStatement.setString("to_address", to_address);
	 * 
	 * callableStatement.setString("phone_number", phone_number);
	 * callableStatement.setString("email", email);
	 * callableStatement.setInt("number_of_bags", number_of_bags);
	 * callableStatement.setDouble("total_weight", total_weight);
	 * callableStatement.setDouble("cost_per_bag", cost_per_bag);
	 * callableStatement.setDouble("total_value", total_value);
	 * callableStatement.setString("sales_manager_name", sales_manager_name);
	 * callableStatement.setDouble("freight_rate", freight_rate);
	 * callableStatement.setString("approve_status", approve_status);
	 * callableStatement.setString("username", username);
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * if(!callableStatement.getBoolean("outResult")) {
	 * responseParameters.put("result", false);
	 * responseParameters.put("errorCode",
	 * TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
	 * responseParameters.put("errorMessage",
	 * callableStatement.getString("message")); if
	 * (log.isEnabledFor(Level.ERROR)) {
	 * log.error(callableStatement.getString("message")); } return
	 * responseParameters; }else{ responseParameters.put("Message",
	 * "record successfully updated" ); } } catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * finally { JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement,
	 * connection); }
	 * 
	 * return responseParameters; }
	 * 
	 * public static HashMap<String, Object> updateStorageIncomingLoad( int
	 * storage_incoming_load_id, // String invoice_number , String date, String
	 * from_address, String to_address, String brand_name, double cost_per_bag,
	 * double number_of_bags , double total_weight, double total_value, int
	 * fk_truck_id, int fk_driver_id, String time_of_arrival, double reading_km,
	 * String status ) throws TransportException, SQLException{
	 * 
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>();
	 * 
	 * Connection connection = JDBCConnectionUtils.getJDBCConnection(); String
	 * errorMessage = ""; responseParameters.put("result", true);
	 * CallableStatement callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * UPDATE_STORAGE_INCOMING_LOAD));
	 * 
	 * callableStatement.setInt("storage_incoming_load_id",
	 * storage_incoming_load_id); //
	 * callableStatement.setString("invoice_number", invoice_number);
	 * callableStatement.setString("date", date);
	 * callableStatement.setString("from_address", from_address);
	 * callableStatement.setString("to_address", to_address);
	 * callableStatement.setString("brand_name", brand_name);
	 * callableStatement.setDouble("cost_per_bag", cost_per_bag);
	 * callableStatement.setDouble("number_of_bags", number_of_bags);
	 * callableStatement.setDouble("total_weight", total_weight);
	 * callableStatement.setDouble("total_value", total_value);
	 * callableStatement.setInt("fk_truck_id", fk_truck_id);
	 * callableStatement.setInt("fk_driver_id", fk_driver_id);
	 * callableStatement.setString("time_of_arrival", time_of_arrival);
	 * callableStatement.setDouble("reading_km", reading_km);
	 * callableStatement.setString("status", status);
	 * callableStatement.setString("username", username);
	 * 
	 * 
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * if(!callableStatement.getBoolean("outResult")) {
	 * responseParameters.put("result", false);
	 * responseParameters.put("errorCode",
	 * TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
	 * responseParameters.put("errorMessage",
	 * callableStatement.getString("message")); if
	 * (log.isEnabledFor(Level.ERROR)) {
	 * log.error(callableStatement.getString("message")); } return
	 * responseParameters; }else{ responseParameters.put("errorMessage",
	 * "record successfully inserted" ); } } catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * finally { JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement,
	 * connection); }
	 * 
	 * return responseParameters; }
	 * 
	 * public static HashMap<String, Object> getStatusDispatch(String
	 * invoice_number) throws TransportException, SQLException { HashMap<String,
	 * Object> responseParameters = new HashMap<String, Object>(); Connection
	 * connection = JDBCConnectionUtils.getJDBCConnection(); String errorMessage
	 * = ""; responseParameters.put("result", true); CallableStatement
	 * callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * GET_STATUS_STORAGE_DISPATCH)); // HashMap<String, Object> cards = new
	 * HashMap<String, Object>();
	 * 
	 * callableStatement.setString("invoice_number",invoice_number);
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * // checking out param from stored procedure is null
	 * JDBCConnectionUtils.validateCallableStatement(callableStatement);
	 * responseParameters.put("result", true);
	 * 
	 * // ArrayList<HashMap<String, Object>> sellerDispatch = new
	 * ArrayList<HashMap<String, Object>>();
	 * 
	 * while(rset.next()){ HashMap<String, Object> status = new HashMap<String,
	 * Object>();
	 * 
	 * status.put("storage_dispatch_id", rset.getInt("pk_storage_dispatch_id"));
	 * status.put("fk_truck_id", rset.getInt("fk_truck_id"));
	 * status.put("truck_number", rset.getString("truck_number")); //
	 * dispatch.put("invoice_no", rset.getString("invoice_number"));
	 * status.put("unload_location", rset.getString("unload_location"));
	 * 
	 * // sellerDispatch.add(dispatch); responseParameters.put("StatusDispatch",
	 * status); }
	 * 
	 * 
	 * } finally { JDBCConnectionUtils.closeResultSetAndStatement(rset,
	 * callableStatement); }
	 * 
	 * return responseParameters; }
	 * 
	 * public static HashMap<String, Object> updateStatusDispatch( int
	 * storage_dispatch_id, String dispatch_status ) throws TransportException,
	 * SQLException{
	 * 
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>();
	 * 
	 * Connection connection = JDBCConnectionUtils.getJDBCConnection(); String
	 * errorMessage = ""; responseParameters.put("result", true);
	 * CallableStatement callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * UPDATE_STATUS_STORAGE_DISPATCH));
	 * 
	 * callableStatement.setInt("storage_dispatch_id", storage_dispatch_id);
	 * callableStatement.setString("dispatch_status", dispatch_status);
	 * callableStatement.setString("username", username);
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * if(!callableStatement.getBoolean("outResult")) {
	 * responseParameters.put("result", false);
	 * responseParameters.put("errorCode",
	 * TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
	 * responseParameters.put("errorMessage",
	 * callableStatement.getString("message")); if
	 * (log.isEnabledFor(Level.ERROR)) {
	 * log.error(callableStatement.getString("message")); } return
	 * responseParameters; }else{ responseParameters.put("Message",
	 * "record successfully updated" ); } } catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * finally { JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement,
	 * connection); }
	 * 
	 * return responseParameters; }
	 * 
	 * public static HashMap<String, Object> getCloseDispatch(String
	 * invoice_number) throws TransportException, SQLException { HashMap<String,
	 * Object> responseParameters = new HashMap<String, Object>(); Connection
	 * connection = JDBCConnectionUtils.getJDBCConnection(); String errorMessage
	 * = ""; responseParameters.put("result", true); CallableStatement
	 * callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * GET_CLOSE_STORAGE_DISPATCH)); // HashMap<String, Object> cards = new
	 * HashMap<String, Object>();
	 * 
	 * callableStatement.setString("invoice_number",invoice_number);
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * // checking out param from stored procedure is null
	 * JDBCConnectionUtils.validateCallableStatement(callableStatement);
	 * responseParameters.put("result", true);
	 * 
	 * // ArrayList<HashMap<String, Object>> sellerDispatch = new
	 * ArrayList<HashMap<String, Object>>();
	 * 
	 * while(rset.next()){ HashMap<String, Object> dispatch = new
	 * HashMap<String, Object>(); String fname=rset.getString("first_name");
	 * String mname=rset.getString("middle_name"); String
	 * lname=rset.getString("last_name"); dispatch.put("storage_dispatch_id",
	 * rset.getInt("pk_storage_dispatch_id")); dispatch.put("fk_truck_id",
	 * rset.getInt("fk_truck_id")); dispatch.put("fk_driver_id",
	 * rset.getInt("fk_driver_id")); dispatch.put("truck_number",
	 * rset.getString("truck_number")); dispatch.put("first_name",
	 * rset.getString("first_name")); dispatch.put("middle_name",
	 * rset.getString("middle_name")); dispatch.put("last_name",
	 * rset.getString("last_name")); dispatch.put("driver_name",
	 * fname+" "+mname+" "+lname); dispatch.put("invoice_no",
	 * rset.getString("invoice_number")); dispatch.put("start_location",
	 * rset.getString("start_location")); dispatch.put("time_of_start",
	 * rset.getString("time_of_start")); dispatch.put("unload_location",
	 * rset.getString("unload_location")); dispatch.put("dispatch_date",
	 * rset.getString("dispatch_date")); dispatch.put("estimated_dispatch_days",
	 * rset.getInt("estimated_dispatch_days"));
	 * dispatch.put("estimated_dispatch_hours",
	 * rset.getInt("estimated_dispatch_hours")); dispatch.put("estimated_km",
	 * rset.getInt("estimated_km")); dispatch.put("starting_meter_reading",
	 * rset.getDouble("starting_meter_reading"));
	 * dispatch.put("closing_meter_reading",
	 * rset.getDouble("closing_meter_reading")); dispatch.put("load_quantity",
	 * rset.getDouble("load_quantity")); dispatch.put("freight",
	 * rset.getDouble("freight")); dispatch.put("advance",
	 * rset.getDouble("advance")); dispatch.put("balance",
	 * rset.getDouble("balance")); dispatch.put("dispatch_status",
	 * rset.getString("dispatch_status")); dispatch.put("expenditure",
	 * rset.getDouble("expenditure"));
	 * 
	 * // sellerDispatch.add(dispatch);
	 * responseParameters.put("StorageDispatch", dispatch); }
	 * 
	 * 
	 * } finally { JDBCConnectionUtils.closeResultSetAndStatement(rset,
	 * callableStatement); }
	 * 
	 * return responseParameters; }
	 * 
	 * public static HashMap<String, Object> updateCloseDispatch( int
	 * storage_dispatch_id, String dispatch_status, double balance, double
	 * expenditure, double closing_meter_reading
	 * 
	 * ) throws TransportException, SQLException{
	 * 
	 * HashMap<String, Object> responseParameters = new HashMap<String,
	 * Object>();
	 * 
	 * Connection connection = JDBCConnectionUtils.getJDBCConnection(); String
	 * errorMessage = ""; responseParameters.put("result", true);
	 * CallableStatement callableStatement = null; ResultSet rset = null;
	 * 
	 * responseParameters =TransportGlobalUtils.shiroUserDetails(); String
	 * username = (String) responseParameters.get("username");
	 * 
	 * if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
	 * return responseParameters; }
	 * 
	 * try { callableStatement = connection
	 * .prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.
	 * UPDATE_CLOSE_STORAGE_DISPATCH));
	 * 
	 * callableStatement.setInt("storage_dispatch_id", storage_dispatch_id);
	 * callableStatement.setString("dispatch_status", dispatch_status);
	 * callableStatement.setDouble("balance", balance);
	 * callableStatement.setDouble("expenditure", expenditure);
	 * callableStatement.setDouble("closing_meter_reading",
	 * closing_meter_reading); callableStatement.setString("username",
	 * username);
	 * 
	 * 
	 * callableStatement.registerOutParameter("outResult",
	 * java.sql.Types.BOOLEAN);
	 * callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
	 * callableStatement.registerOutParameter("message",
	 * java.sql.Types.VARCHAR);
	 * 
	 * rset = callableStatement.executeQuery();
	 * 
	 * if(!callableStatement.getBoolean("outResult")) {
	 * responseParameters.put("result", false);
	 * responseParameters.put("errorCode",
	 * TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
	 * responseParameters.put("errorMessage",
	 * callableStatement.getString("message")); if
	 * (log.isEnabledFor(Level.ERROR)) {
	 * log.error(callableStatement.getString("message")); } return
	 * responseParameters; }else{ responseParameters.put("Message",
	 * "record successfully updated" ); } } catch(Exception e){
	 * e.printStackTrace(); }
	 * 
	 * finally { JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement,
	 * connection); }
	 * 
	 * return responseParameters; }
	 */
}
