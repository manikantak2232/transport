package com.pixelbox.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pixelbox.exceptions.TransportException;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;

public class SparePartsDAO {

	final static Logger log = Logger.getLogger(SparePartsDAO.class);

	public static HashMap<String, Object> addSparePartAllotment(
			int fk_driver_id,
			int fk_spare_part_id,
			int fk_truck_id,
	//		int count,
			String date
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_SPARE_PART_ALLOTMENT));

			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setInt("fk_spare_part_id", fk_spare_part_id);			
			callableStatement.setInt("fk_truck_id", fk_truck_id);
	//		callableStatement.setInt("count", count);
			callableStatement.setString("date", date);	
			callableStatement.setString("username", username);

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
			}
			responseParameters.put("Message", "record successfully inserted" );
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addSparePartAllotment1(
			int fk_driver_id,
			String fk_spare_part_id,
			int fk_truck_id,
	//		int count,
			String date
					) throws TransportException, SQLException{

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		connection.setAutoCommit(false);
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.CHECK_SPARE_PARTS_COUNT));

			callableStatement.setString("spare_pard_id", fk_spare_part_id);			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("rows_count", java.sql.Types.INTEGER);
			
			rset = callableStatement.executeQuery();
			
			int rows_count=callableStatement.getInt("rows_count");
			if(rows_count==1){
				ArrayList<HashMap<String, Object>> partNames = new ArrayList<HashMap<String, Object>>();
				
				while(rset.next()){
					HashMap<String, Object> part = new HashMap<String, Object>();

					part.put("type_of_part", rset.getString("type_of_part"));
					part.put("fk_spare_part_id", rset.getString("fk_spare_part_id"));
					part.put("spare_part_name", rset.getString("spare_part_name"));
					partNames.add(part);
				}
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("partNames", partNames);
			}
			else{
	//			String list = fk_spare_part_id;
				String parts[]=fk_spare_part_id.split(",");
	//			ArrayList<Integer> intList = new Gson().fromJson(list, new TypeToken<ArrayList<Integer>>(){}.getType());
				for(int i=0; i<parts.length; i++){
				callableStatement = connection
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_SPARE_PART_ALLOTMENT));

				callableStatement.setInt("fk_driver_id", fk_driver_id);
				callableStatement.setString("fk_spare_part_id",  parts[i]);			
				callableStatement.setInt("fk_truck_id", fk_truck_id);
				callableStatement.setString("date", date);	
				callableStatement.setString("username", username);

				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset = callableStatement.executeQuery();
				responseParameters.put("message", callableStatement.getString("message"));
				}
				
			}
			connection.commit(); 
			return responseParameters;
			
		} catch(Exception e){
			connection.rollback(); 
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
 	public static HashMap<String, Object> getSparePartAllotment(int fk_truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SPARE_PART_ALLOTMENT));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setInt("fk_truck_id",fk_truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
	//		ArrayList<HashMap<String, Object>> sparePartsAllotment = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				spareParts.put("driver_name", fname+" "+mname+" "+lname);
				spareParts.put("spare_part_allotment_id", rset.getInt("pk_spare_part_allotment_id"));
				spareParts.put("fk_driver_id", rset.getInt("fk_driver_id"));
				spareParts.put("first_name", rset.getString("first_name"));
				spareParts.put("middle_name", rset.getString("middle_name"));
				spareParts.put("last_name", rset.getString("last_name"));
				spareParts.put("fk_spare_part_id", rset.getInt("spare_part_id"));				
				spareParts.put("spare_part_name", rset.getString("spare_part_name"));
				spareParts.put("fk_truck_id", rset.getInt("fk_truck_id"));
				spareParts.put("count", rset.getDouble("count"));
				spareParts.put("date", rset.getString("date"));

		//		sparePartsAllotment.add(spareParts);
				responseParameters.put("sparePartsAllotment", spareParts);
			}
			
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	
	/*public static HashMap<String, Object> getSparePartAllotmentByDate(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SPARE_PART_ALLOTMENT_BY_DATE));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> sparePartsAllotment = new ArrayList<HashMap<String, Object>>();
			int previous_truck_id = 0;
			int current_truck_id = 0;
			String current_date=null;
			String previous_date=null;
			int previous_driver_id = 0;
			int current_driver_id = 0;
			String spare_part_names = "";
			HashMap<String, Object> service = null;
			while(rset.next()){
				
				
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				spareParts.put("driver_name", fname+" "+mname+" "+lname);
				spareParts.put("fk_driver_id", rset.getInt("fk_driver_id"));
				spareParts.put("truck_number", rset.getString("truck_number"));
				spareParts.put("first_name", rset.getString("first_name"));
				spareParts.put("middle_name", rset.getString("middle_name"));
				spareParts.put("last_name", rset.getString("last_name"));
				spareParts.put("spare_part_id", rset.getInt("spare_part_id"));			
				spareParts.put("spare_part_name", rset.getString("spare_part_name"));
				spareParts.put("fk_truck_id", rset.getInt("fk_truck_id"));
				spareParts.put("count", rset.getDouble("count"));
				spareParts.put("date", rset.getString("date"));

				sparePartsAllotment.add(spareParts);
			}
			responseParameters.put("sparePartsAllotment", sparePartsAllotment);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}*/
	
	public static HashMap<String, Object> getSparePartAllotmentByDate(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SPARE_PART_ALLOTMENT_BY_DATE));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> sparePartsAllotment = new ArrayList<HashMap<String, Object>>();

			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				spareParts.put("driver_name", fname+" "+mname+" "+lname);
		//		spareParts.put("spare_part_allotment_id", rset.getInt("pk_spare_part_allotment_id"));
				spareParts.put("fk_driver_id", rset.getInt("fk_driver_id"));
				spareParts.put("fk_spare_part_id", rset.getInt("spare_part_id"));				
				spareParts.put("spare_part_name", rset.getString("name"));
				spareParts.put("fk_truck_id", rset.getInt("fk_truck_id"));
				spareParts.put("truck_number", rset.getString("truck_number"));
				spareParts.put("date", rset.getString("date"));

				sparePartsAllotment.add(spareParts);

			}
		responseParameters.put("sparePartsAllotment", sparePartsAllotment);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> updateSparePartAllotment(
			int spare_part_allotment_id,
			int fk_driver_id,
			int fk_spare_part_id,
	//		String truck_number,
			int count,
			String date
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_SPARE_PART_ALLOTMENT));

			callableStatement.setInt("spare_part_allotment_id", spare_part_allotment_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setInt("fk_spare_part_id", fk_spare_part_id);			
//			callableStatement.setString("truck_number", truck_number);
			callableStatement.setInt("count", count);
			callableStatement.setString("date", date);	
			callableStatement.setString("username", username);

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
				responseParameters.put("Message", "record successfully updated" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	
	public static HashMap<String, Object> getAllSpareParts() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_SPARE_PARTS));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> spareParts = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> part = new HashMap<String, Object>();
				
				String name1= rset.getString("name");
				String name2= rset.getString("vendor_name");
				String name= name1+"("+name2+")";
				part.put("pk_spare_parts_id", rset.getInt("pk_spare_parts_id"));
				part.put("name", name);
			//	part.put("fk_vendor_id", rset.getInt("fk_vendor_id"));
			//	part.put("type", rset.getString("type"));
				spareParts.add(part);
			}
			responseParameters.put("SpareParts", spareParts);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addSparePartInventory(
			int fk_spare_part_id,
			int count,
			String invoice_number,double unit_price,double discount,double gst,double total
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_SPARE_PART_INVENTORY));

			callableStatement.setInt("fk_spare_part_id", fk_spare_part_id);			
			callableStatement.setInt("part_count", count);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setDouble("unit_price", unit_price);
			callableStatement.setDouble("discount", discount);
			callableStatement.setDouble("gst", gst);
			callableStatement.setDouble("total", total);
			callableStatement.setString("username", username);

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
				responseParameters.put("Message", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addOilBrandName(
			String brand_name
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OIL_BRAND_NAME));

			callableStatement.setString("brand_name", brand_name);
	//		callableStatement.setString("username", username);

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
				responseParameters.put("Message", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addBatteryBrandName(
			String brand_name
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_BATTERY_BRAND_NAME));

			callableStatement.setString("brand_name", brand_name);
	//		callableStatement.setString("username", username);

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
				responseParameters.put("Message", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getSparePartInventoryHistory(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SPARE_PART_INVENTORY_HISTORY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> sparePartsAllotment = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				spareParts.put("pk_spare_part_inventory_history_id", rset.getInt("pk_spare_part_inventory_history_id"));
				
				spareParts.put("invoice_number", rset.getString("invoice_number"));
				spareParts.put("spare_part_name", rset.getString("spare_part_name"));
				spareParts.put("fk_spare_part_id", rset.getInt("fk_spare_part_id"));				
				spareParts.put("spare_part_name", rset.getString("spare_part_name"));
				spareParts.put("spare_part_count", rset.getDouble("spare_part_count"));
				spareParts.put("date", rset.getString("date"));

				sparePartsAllotment.add(spareParts);
				
			}
			responseParameters.put("inventoryHistory", sparePartsAllotment);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getSparePartCurrentInventory() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SPARE_PART_CURRENT_INVENTORY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> currentInventory = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> part = new HashMap<String, Object>();
				
				part.put("fk_spare_part_id", rset.getInt("fk_spare_part_id"));
				part.put("spare_part_name", rset.getString("spare_part_name"));
				part.put("pk_spare_part_inventory_history_id", rset.getInt("pk_spare_part_inventory_history_id"));
				part.put("spare_part_count", rset.getDouble("spare_part_count"));
				currentInventory.add(part);
			}
			responseParameters.put("currentInventory", currentInventory);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getOilBrandNames() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OIL_BRAND_NAMES));
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> brandNames = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> name = new HashMap<String, Object>();

				name.put("pk_oil_brand_id", rset.getInt("pk_oil_brand_id"));
				name.put("brand_name", rset.getString("brand_name"));
				brandNames.add(name);
			}
			responseParameters.put("brandNames", brandNames);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getBatteryBrandNames() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BATTERY_BRAND_NAMES));
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> brandNames = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> name = new HashMap<String, Object>();

				name.put("pk_battery_brand_id", rset.getInt("pk_battery_brand_id"));
				name.put("brand_name", rset.getString("brand_name"));
				brandNames.add(name);
			}
			responseParameters.put("brandNames", brandNames);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getInventoryBatteryBrands() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_INVENTORY_BATTERY_BRANDS));
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> brandNames = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> name = new HashMap<String, Object>();

				name.put("fk_battery_brand_id", rset.getInt("fk_battery_brand_id"));
				name.put("brand_name", rset.getString("brand_name"));
				brandNames.add(name);
			}
			responseParameters.put("brandNames", brandNames);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getRunningBatteryBrands() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_RUNNING_BATTERY_BRANDS));
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> brandNames = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> name = new HashMap<String, Object>();

				name.put("fk_battery_brand_id", rset.getInt("fk_battery_brand_id"));
				name.put("brand_name", rset.getString("brand_name"));
				brandNames.add(name);
			}
			responseParameters.put("brandNames", brandNames);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getInventoryBatteryNumber(int fk_battery_brand_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_INVENTORY_BATTERY_NUMBERS));
			callableStatement.setInt("fk_battery_brand_id",fk_battery_brand_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<String> batteryNumbers = new ArrayList<String>();
			
			while(rset.next()){
				
				String part= rset.getString("battery_number");

				batteryNumbers.add(part);
				
			}
			responseParameters.put("batteryNumbers", batteryNumbers);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getRunningBatteryNumber(int fk_battery_brand_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_RUNNING_BATTERY_NUMBERS));
			callableStatement.setInt("fk_battery_brand_id",fk_battery_brand_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<String> batteryNumbers = new ArrayList<String>();
			
			while(rset.next()){
				
				String part= rset.getString("battery_number");

				batteryNumbers.add(part);
				
			}
			responseParameters.put("batteryNumber", batteryNumbers);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getOilDistinceBrandNames() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OIL_DISTINCT_BRAND_NAMES));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> brandNames = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> name = new HashMap<String, Object>();

				name.put("fk_oil_brand_id", rset.getInt("fk_oil_brand_id"));
				name.put("brand_name", rset.getString("brand_name"));
				brandNames.add(name);
			}
			responseParameters.put("brandNames", brandNames);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addOilInventory(
			String date, int fk_oil_brand_id, String invoice_number,
			Double cost, Double liters, String invoice_date
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OIL_INVENTORY));

			callableStatement.setString("date", date);			
			callableStatement.setInt("fk_oil_brand_id", fk_oil_brand_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setDouble("cost", cost);
			callableStatement.setDouble("liters", liters);
			callableStatement.setString("invoice_date", invoice_date);

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
				responseParameters.put("Message", "record successfully inserted" );
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addIssuedOil(
			int fk_truck_id,String issued_date, int fk_oil_brand_id,
			Double liters,int fk_driver_id,Double reading
					) throws TransportException, SQLException{

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		CallableStatement callableStatement1 = null;
		CallableStatement callableStatement2 = null;
		CallableStatement callableStatement3 = null;
		ResultSet rset = null;
		ResultSet rset1 = null;
		ResultSet rset2 = null;
		ResultSet rset3 = null;
		responseParameters =TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");
		
		if(!Boolean.parseBoolean(responseParameters.get("result").toString())){
			return responseParameters; 
		}
		
		try {									
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_REQUEST_OIL));

	//		callableStatement.setInt("fk_truck_id", fk_truck_id);			
	//		callableStatement.setString("issued_date", issued_date);
			callableStatement.setInt("fk_oil_brand_id", fk_oil_brand_id);
			callableStatement.setDouble("request_count", liters);
	//		callableStatement.setInt("fk_driver_id", fk_driver_id);
//			callableStatement.setDouble("reading", reading);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
			
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
//			ArrayList<HashMap<String, Object>> inventoryHistory = new ArrayList<HashMap<String, Object>>();
			double remaining=liters;
			while(rset.next() & remaining!=0){
				
		//		HashMap<String, Object> spareParts = new HashMap<String, Object>();			
				double count=rset.getDouble("opening_stock");
				int oil_inventory_history_id=rset.getInt("pk_oil_inventory_history_id");
				double oil_cost=rset.getDouble("cost");
				double oil_liters=rset.getDouble("liters");
				
				if(count>=remaining){
					count=count-remaining;
					double liter_cost=(oil_cost/oil_liters)*remaining;
					remaining=0;
					callableStatement1 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_OIL_INVENTORY_COUNT));
					callableStatement1.setInt("oil_inventory_history_id", oil_inventory_history_id);
					callableStatement1.setDouble("count", count);
		//			callableStatement1.setDouble("oil_liters", oil_liters);
		//			callableStatement1.setDouble("oil_cost", oil_cost);
					callableStatement1.setInt("fk_truck_id", fk_truck_id);
					callableStatement1.setDouble("liter_cost", liter_cost);
					callableStatement1.setString("issued_date", issued_date);
					callableStatement1.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement1.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement1.registerOutParameter("message", java.sql.Types.VARCHAR);
					
					rset1 = callableStatement1.executeQuery();
					responseParameters.put("Message", callableStatement1.getString("message"));
					rset1.close();
					callableStatement1.close();
	//				JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
					
				}
				if(count<remaining){
	//				double liter_count=count;
					double liter_cost=(oil_cost/oil_liters)*count;
					remaining=remaining-count;
					
					callableStatement2 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_OIL_INVENTORY_COUNT));
					callableStatement2.setInt("oil_inventory_history_id", oil_inventory_history_id);
					callableStatement2.setDouble("count", 0);
	//				callableStatement2.setDouble("oil_liters", oil_liters);
	//				callableStatement2.setDouble("oil_cost", oil_cost);					
					callableStatement2.setInt("fk_truck_id", fk_truck_id);
					callableStatement2.setDouble("liter_cost", liter_cost);
					callableStatement2.setString("issued_date", issued_date);
					callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);
					
					rset2 = callableStatement2.executeQuery();
					responseParameters.put("Message", callableStatement2.getString("message"));
					rset2.close();
					callableStatement2.close();
					
		//			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
				}
				
			/*if(!callableStatement.getBoolean("outResult"))
			{
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message")); 
				}
				return responseParameters;
			}else{				
				responseParameters.put("Message", "record successfully inserted" );
			}*/
			}
			
			if(remaining==0){
				callableStatement3 = connection
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_ISSUED_OIL));

				callableStatement3.setInt("fk_truck_id", fk_truck_id);			
				callableStatement3.setString("issued_date", issued_date);
				callableStatement3.setInt("fk_oil_brand_id", fk_oil_brand_id);
				callableStatement3.setDouble("liter", liters);
				callableStatement3.setInt("fk_driver_id", fk_driver_id);
				callableStatement3.setDouble("reading", reading);

				callableStatement3.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement3.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement3.registerOutParameter("message", java.sql.Types.VARCHAR);
				
				rset3 = callableStatement3.executeQuery();
				rset3.close();
				callableStatement3.close();
				
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			responseParameters.put("Message", callableStatement.getString("message"));
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}
		
		return responseParameters;
	}
	
	public static HashMap<String, Object> getOilInventoryHistory(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OIL_INVENTORY_HISTORY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> inventoryHistory = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				spareParts.put("pk_oil_inventory_history_id", rset.getInt("pk_oil_inventory_history_id"));
				
				spareParts.put("date", rset.getString("date"));
				spareParts.put("invoice_number", rset.getString("invoice_number"));
				spareParts.put("fk_oil_brand_id", rset.getInt("fk_oil_brand_id"));				
				spareParts.put("liters", rset.getDouble("liters"));
				spareParts.put("cost", rset.getDouble("cost"));
				spareParts.put("invoice_date", rset.getString("invoice_date"));
				spareParts.put("brand_name", rset.getString("brand_name"));

				inventoryHistory.add(spareParts);
				
			}
			responseParameters.put("inventoryHistory", inventoryHistory);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getOilCurrentInventory() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OIL_CURRENT_INVENTORY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> currentInventory = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> part = new HashMap<String, Object>();
				
				part.put("pk_oil_current_inventory_id", rset.getInt("pk_oil_current_inventory_id"));
				part.put("fk_oil_brand_id", rset.getInt("fk_oil_brand_id"));
				part.put("liters", rset.getDouble("liters"));
				part.put("brand_name", rset.getString("brand_name"));
				currentInventory.add(part);
			}
			responseParameters.put("currentInventory", currentInventory);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getIssuedOil(String lower_date, String upper_date,
				String truck_number)
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
					StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ISSUED_OIL));
			
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("truck_no", truck_number);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> issued_oil = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> tyre = new HashMap<String, Object>();

				tyre.put("pk_issued_oil_id", rset.getInt("pk_issued_oil_id"));
				tyre.put("fk_truck_id", rset.getInt("fk_truck_id"));
				tyre.put("issued_date", rset.getString("issued_date"));
				tyre.put("fk_oil_brand_id", rset.getInt("fk_oil_brand_id"));
				tyre.put("liters", rset.getDouble("liters"));
				tyre.put("reading", rset.getDouble("reading"));				
				tyre.put("fk_driver_id", rset.getInt("fk_driver_id"));
				tyre.put("brand_name", rset.getString("brand_name"));
				tyre.put("truck_number", rset.getString("truck_number"));
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				String name=fname+" "+mname+" "+lname;
				tyre.put("name", name);
				issued_oil.add(tyre);
			}

			responseParameters.put("issued_oil", issued_oil);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addBatteryInventory(String invoice_number, 
			String invoice_date, String date,int fk_battery_brand_id, String battery_number
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_BATTERY_INVENTORY));

			callableStatement.setString("invoice_number", invoice_number);			
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("date", date);
			callableStatement.setInt("fk_battery_brand_id", fk_battery_brand_id);
			callableStatement.setString("battery_number", battery_number);
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
	
	public static HashMap<String, Object> addIssuedBattery(String issued_battery_number, int fk_issued_battery_id, 
			String issued_and_returned_date,int fk_truck_id,int fk_driver_id, String returned_battery_number,int fk_returned_brand_id) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_ISSUED_BATTERY));

			callableStatement.setString("issued_battery_number", issued_battery_number);
			callableStatement.setInt("fk_issued_battery_id", fk_issued_battery_id);
			callableStatement.setString("issued_and_returned_date", issued_and_returned_date);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("returned_battery_number", returned_battery_number);
			callableStatement.setInt("fk_returned_brand_id", fk_returned_brand_id);

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
	
	public static HashMap<String, Object> getBatteryCurrentInventory() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BATTERY_CURRENT_INVENTORY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> currentInventory = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> part = new HashMap<String, Object>();
				
				part.put("pk_battery_current_inventory_id", rset.getInt("pk_battery_current_inventory_id"));
				part.put("fk_battery_brand_id", rset.getInt("fk_battery_brand_id"));
				part.put("brand_name", rset.getString("brand_name"));
				part.put("battery_number", rset.getString("battery_number"));
				currentInventory.add(part);
			}
			responseParameters.put("currentInventory", currentInventory);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getRunningBattery() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_RUNNING_BATTERY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> runningBattery = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> part = new HashMap<String, Object>();
				
				part.put("pk_running_battery_id", rset.getInt("pk_running_battery_id"));
				part.put("battery_number", rset.getString("battery_number"));
				part.put("date", rset.getString("date"));
				part.put("fk_battery_brand_id", rset.getInt("fk_battery_brand_id"));
				part.put("fk_truck_id", rset.getInt("fk_truck_id"));
				part.put("truck_number", rset.getString("truck_number"));
				part.put("brand_name", rset.getString("brand_name"));
				
				runningBattery.add(part);
			}
			responseParameters.put("runningBattery", runningBattery);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getBatteryInventoryHistory(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BATTERY_INVENTORY_HISTORY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> inventoryHistory = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				spareParts.put("pk_battery_inventory_history_id", rset.getInt("pk_battery_inventory_history_id"));
				
				spareParts.put("date", rset.getString("date"));
				spareParts.put("invoice_number", rset.getString("invoice_number"));
				spareParts.put("invoice_date", rset.getString("invoice_date"));				
				spareParts.put("fk_battery_brand_id", rset.getInt("fk_battery_brand_id"));
				spareParts.put("brand_name", rset.getString("brand_name"));
				spareParts.put("battery_number", rset.getString("battery_number"));

				inventoryHistory.add(spareParts);
				
			}
			responseParameters.put("inventoryHistory", inventoryHistory);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getIssuedBattery(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ISSUED_BATTERY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> issuedBattery = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				spareParts.put("pk_issued_battery_id", rset.getInt("pk_issued_battery_id"));				
				spareParts.put("issued_battery_date", rset.getString("issued_battery_date"));
				spareParts.put("battery_number", rset.getString("battery_number"));
				spareParts.put("fk_battery_brand_id", rset.getInt("fk_battery_brand_id"));				
				spareParts.put("fk_truck_id", rset.getInt("fk_truck_id"));
				spareParts.put("fk_driver_id", rset.getInt("fk_driver_id"));
				spareParts.put("returned_battery_date", rset.getString("returned_battery_date"));
				spareParts.put("brand_name", rset.getString("brand_name"));
				spareParts.put("truck_number", rset.getString("truck_number"));
				String fname=rset.getString("first_name");
				String mname=rset.getString("middle_name");
				String lname=rset.getString("last_name");
				String name=fname+" "+mname+" "+lname;
				spareParts.put("driver_name", name);

				issuedBattery.add(spareParts);
				
			}
			responseParameters.put("issuedBattery", issuedBattery);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getWasteBattery(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_WASTE_BATTERY));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> wasteBattery = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				spareParts.put("pk_waste_battery_id", rset.getInt("pk_waste_battery_id"));
				
				spareParts.put("battery_number", rset.getString("battery_number"));
				spareParts.put("fk_battery_brand_id", rset.getInt("fk_battery_brand_id"));
				spareParts.put("brand_name", rset.getString("brand_name"));				
				spareParts.put("date", rset.getString("date"));

				wasteBattery.add(spareParts);
				
			}
			responseParameters.put("wasteBattery", wasteBattery);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addTruckMaintenanceAdvanceRequest(String item_cost, String date, String item_name
		,int fk_truck_id, int request_number) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_TRUCK_MAINTENANCE_ADVANCE_REQUEST));

			callableStatement.setString("item_cost", item_cost);
			callableStatement.setString("date", date);
			callableStatement.setString("item_name", item_name);
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("request_number", request_number);
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
	
	public static HashMap<String, Object> getTruckMaintenanceAdvanceRequestNumber(int fk_truck_id, String date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_NUMBER));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setInt("fk_truck_id",fk_truck_id);
			callableStatement.setString("date",date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("advance_request_count", java.sql.Types.INTEGER);
			callableStatement.registerOutParameter("request_number_value", java.sql.Types.INTEGER);
			
			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
				HashMap<String, Object> numbers = new HashMap<String, Object>();
				numbers.put("advance_request_count", callableStatement.getInt("advance_request_count"));
				numbers.put("request_number_value", callableStatement.getInt("request_number_value"));				
			
			responseParameters.put("numbers", numbers);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckMaintenanceAdvanceItemDetails(String truck_maintenance_advance_id) throws TransportException, SQLException {

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
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_MAINTENANCE_ADVANCE_ITEM_DETAILS));
				
				ArrayList<HashMap<String, Object>> itemDetails = new ArrayList<HashMap<String, Object>>();
				
				String item_id[]=truck_maintenance_advance_id.split(",");
				for(int i=0; i<item_id.length; i++){
				
				callableStatement.setString("truck_maintenance_advance_id", item_id[i]);
				callableStatement.setString("username", username);
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

				rset = callableStatement.executeQuery();

				JDBCConnectionUtils.validateCallableStatement(callableStatement);
				responseParameters.put("result", true);
		
				
				while(rset.next()){
					HashMap<String, Object> item = new HashMap<String, Object>();
					
					item.put("pk_truck_maintenance_advance_id", rset.getInt("pk_truck_maintenance_advance_id"));
					item.put("date", rset.getString("date"));
					item.put("fk_truck_id", rset.getInt("fk_truck_id"));
					item.put("truck_number", rset.getString("truck_number"));
					item.put("item_cost", rset.getDouble("item_cost"));
					item.put("item_name", rset.getString("item_name"));
					itemDetails.add(item);
				}
				}
				responseParameters.put("itemDetails", itemDetails);
				
			} finally {
				JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
			}

			return responseParameters;
		}
	
	public static HashMap<String, Object> getTruckMaintenanceAdvanceRequestCount() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_COUNT));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			

			ArrayList<HashMap<String, Object>> requestCount = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> count = new HashMap<String, Object>();
				count.put("fk_truck_id", rset.getInt("fk_truck_id"));
				count.put("truck_number", rset.getString("truck_number"));
				count.put("truck_maintenance_advance_id", rset.getString("truck_maintenance_advance_id"));
				requestCount.add(count);
			}
			responseParameters.put("requestCount", requestCount);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> updateTruckMaintenanceAdvanceApprovalStatus(String truck_maintenance_advance_id) throws TransportException, SQLException {

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
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_TRUCK_MAINTENANCE_ADVANCE_APPROVAL_STATUS));

				String item_id[]=truck_maintenance_advance_id.split(",");
				for(int i=0; i<item_id.length; i++){
				
					callableStatement.setString("truck_maintenance_advance_id", item_id[i]);
					callableStatement.setString("username", username);
					callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

				rset = callableStatement.executeQuery();
				}

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
	
	public static HashMap<String, Object> getOverallTruckMaintenanceReport(String lower_date, String upper_date) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_OVERALL_MAINTENANCE_REPORT));
		//	HashMap<String, Object> cards = new HashMap<String, Object>();

			callableStatement.setString("lower_date",lower_date);
			callableStatement.setString("upper_date",upper_date);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			ArrayList<HashMap<String, Object>> maintenance = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()){
				HashMap<String, Object> spareParts = new HashMap<String, Object>();
				spareParts.put("id", rset.getInt("id"));				
				spareParts.put("invoice_number", rset.getString("invoice_number"));
				spareParts.put("truck_id", rset.getInt("truck_id"));
				spareParts.put("truck_number", rset.getString("truck_number"));				
				spareParts.put("item_names", rset.getString("item_names"));
				spareParts.put("cost", rset.getDouble("cost"));				
				spareParts.put("remarks", rset.getString("remarks"));
				spareParts.put("date", rset.getString("date"));
				spareParts.put("brand_id", rset.getInt("brand_id"));				
				spareParts.put("brand_name", rset.getString("brand_name"));
				spareParts.put("tyre_number", rset.getString("tyre_number"));
				spareParts.put("tyre_type", rset.getString("tyre_type"));				
				spareParts.put("maintenance_type", rset.getString("maintenance_type"));

				maintenance.add(spareParts);
				
			}
			responseParameters.put("maintenance", maintenance);
			
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
}