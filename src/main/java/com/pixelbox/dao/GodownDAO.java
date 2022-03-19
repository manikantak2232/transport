package com.pixelbox.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mchange.v1.db.sql.ResultSetUtils;
import com.pixelbox.exceptions.TransportException;
import com.pixelbox.service.ImageService;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;
import com.pixelbox.utils.TransportGlobalUtils;

public class GodownDAO {

	public static HashMap<String, Object> addmanager(int age, String firstName, String lastName) throws SQLException{ 
		ResultSet rset=null;
		HashMap<String, Object> connection = new HashMap<String, Object>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/porxy","root","charan5656");  

		//Connection con=null;
		try {
			CallableStatement stmt=con.prepareCall("{call testing(?,?,?,?,?,?)}");  


			stmt.setString("firstname", firstName);
			stmt.setString("lastname", lastName);
			stmt.setInt("age",age);

			rset=stmt.executeQuery(); 

			connection.put("outResult", stmt.getBoolean("outResult"));
			connection.put("message", stmt.getString("message"));
		}
		catch (SQLException e) {

		}
		finally {

		}


		return connection;
	}

	public static HashMap<String, Object> updatemanager( String firstName) throws SQLException{ 
		ResultSet rset=null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/porxy","root","charan5656");  

		//Connection con=null;
		try {
			CallableStatement stmt=con.prepareCall("{call updatecharan(?,?,?,?)}");  


			stmt.setString("first_name", firstName);


			rset=stmt.executeQuery(); 
			while(rset.next()){
				String last_name=rset.getString("lastName");
				int age=rset.getInt("age");

				map.put("last_name", last_name);
				map.put("age", age);

				map.put("outResult", stmt.getBoolean("outResult"));
				map.put("message", stmt.getString("message"));
			}
		}
		catch (SQLException e) {

		}
		finally {

		}


		return map;
	}


	public static HashMap<String, Object> retrivemanager( String firstName,String lastName,int age) throws SQLException{ 
		ResultSet rset=null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/porxy","root","charan5656");  

		//Connection con=null;
		try {
			CallableStatement stmt=con.prepareCall("{call retrive(?,?,?,?,?,?)}");  


			stmt.setString("first_name", firstName);
			stmt.setString("last_name", lastName);
			stmt.setInt("age",age);


			rset=stmt.executeQuery(); 



			map.put("outResult", stmt.getBoolean("outResult"));
			map.put("message", stmt.getString("message"));

		}
		catch (SQLException e) {

		}
		finally {

		}


		return map;
	}

	public static HashMap<String, Object> loged( String UserName,String Password) throws SQLException{ 
		ResultSet rset=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 

		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/porxy","root","charan5656");  

		//Connection con=null;
		try {
			CallableStatement stmt=con.prepareCall("{call Login(?,?,?,?,?)}");  


			stmt.setString("Username",UserName );
			stmt.setString("Password",Password);


			rset=stmt.executeQuery();
			boolean result = stmt.getBoolean("outResult");
			String message = stmt.getString("message");

			responseParameters.put("outResult", result);
			if(!result){
				String code=  stmt.getString("errCode");
				if(code.equals("404")){
					responseParameters.put("code", 200);
					responseParameters.put("message", "password or username is wrong");
				}else{
					responseParameters.put("code", 500);
					responseParameters.put("message", "something went wrong in the proc");
				}
			}else{
				while(rset.next()){
					String first_name=rset.getString("firstName");	
					String last_name=rset.getString("lastName");
					int age=rset.getInt("age");

					responseParameters.put("message", message);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			responseParameters.put("outResult", false);
			responseParameters.put("code", 500);
			responseParameters.put("message", e.getMessage());
		}
		finally {

		}


		return responseParameters;
	}

	public static HashMap<String, Object> addInward(String invoice_number,String invoice_date,String inward_date,
			Double quantity,String fk_type_of_cement_name,String truck_number,String transporter,Double hamali_per_bag,
			String fk_association_name,String fk_unload_location_name,String fk_action_name,String truckNumberImageBytes,
			String truckNumberImageType,String workDoneImageBytes,
			String workDoneImageType,double crossing_quantity,double direct_quantity) throws SQLException{ 

		CallableStatement callableStatement=null;
		ResultSet rset=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);
			
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}
			connection.setAutoCommit(false);
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_INWARD));


			callableStatement.setString("invoice_number",invoice_number );
			callableStatement.setString("invoice_date",invoice_date);
			callableStatement.setString("inward_date",inward_date );
			callableStatement.setDouble("quantity",quantity);
			callableStatement.setString("fk_type_of_cement_name",fk_type_of_cement_name );
			callableStatement.setString("truck_number",truck_number);
			callableStatement.setString("transporter",transporter );
			callableStatement.setDouble("hamali_per_bag",hamali_per_bag);
			callableStatement.setString("fk_association_name",fk_association_name );
			callableStatement.setString("fk_unload_location_name",fk_unload_location_name);
			callableStatement.setString("fk_action_name",fk_action_name );
			callableStatement.setDouble("crossing_quantity",crossing_quantity );
			callableStatement.setDouble("direct_quantity",direct_quantity );

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery(); 
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("message", callableStatement.getString("message"));
				connection.rollback();
				return responseParameters;
			} else {
				String primary_id=callableStatement.getString("primary_id");
				String truck_number_bucket="nikithaimages";
				String truck_number_folder="inward truck number images";
				// work done images
				String truck_number_uploaded_image_url="";

				if(!truckNumberImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(truckNumberImageBytes,truckNumberImageType,truck_number_bucket,truck_number_folder,primary_id);
					String truckNumberFileName = truck_number_folder + "/" + primary_id + "." + truckNumberImageType;
					truck_number_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+truck_number_bucket+"/"+truckNumberFileName;

				}


				//	String primary_id =callableStatement.getString("primary_id");
				String work_done_bucket="nikithaimages";
				String work_done_folder=" inward work done images";

				String word_done_uploaded_image_url="";

				if(!workDoneImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(workDoneImageBytes,workDoneImageType,work_done_bucket,work_done_folder,primary_id);
					String workDoneFileName = work_done_folder + "/" + primary_id + "." + workDoneImageType;
					word_done_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+work_done_bucket+"/"+workDoneFileName;

				}
				/*ImageUploadDAO.addPetrolPumpReadingImageUpload(primary_id,petrol_pump_reading_uploaded_image_url,
						starting_meter_reading_uploaded_image_url);		*/

				CallableStatement callableStatement2 = null;
				ResultSet rset2 = null;

				try {
					callableStatement2 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_INWARD_TRUCK_OR_WORK_IMAGE_URLS));
					callableStatement2.setString("primary_id", primary_id);
					callableStatement2.setString("truck_number_uploaded_image_url", truck_number_uploaded_image_url);
					callableStatement2.setString("word_done_uploaded_image_url", word_done_uploaded_image_url);

					callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

					rset2 = callableStatement2.executeQuery();
					System.out.println(callableStatement2);


					if (!callableStatement2.getBoolean("outResult")) {

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
				responseParameters.put("message", callableStatement.getString("message"));
				connection.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();

		}
		finally {

		}
		return responseParameters;
	}

	public static HashMap<String, Object> getActions() throws Exception{ 

		ArrayList<HashMap<String, Object>> actionDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null; 

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ACTIONS));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_action_id", rset.getInt("pk_action_id"));
				details.put("name", rset.getString("name"));

				actionDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("actionDetails",actionDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> getTypeCement() throws Exception{ 

		ArrayList<HashMap<String, Object>> typeDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null; 

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TYPECEMENT));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_type_id", rset.getInt("pk_type_id"));
				details.put("name", rset.getString("name"));

				typeDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("typeDetails",typeDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> getAssociation() throws Exception{ 
		ArrayList<HashMap<String, Object>> associationDetails = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		CallableStatement callableStatement = null;
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ASSOCIATION_GODOWN));


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_association_id", rset.getInt("pk_association_id"));
				details.put("association_name", rset.getString("association_name"));

				associationDetails.add(details);
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("associationDetails",associationDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> getInward(String upperDate,String lowerDate) throws Exception{ 
		ArrayList<HashMap<String, Object>> getInwardDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}

			ResultSet rset = null; 

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_INWARD));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("username",username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("id", rset.getInt("id"));
				details.put("invoice_number", rset.getString("invoice_number"));
				details.put("invoice_date", rset.getString("invoice_date"));
				details.put("inward_date", rset.getString("inward_date"));
				details.put("quantity", rset.getDouble("quantity"));
				details.put("fk_type_of_cement_name", rset.getString("cement_name"));
				details.put("truck_number", rset.getString("truck_number"));
				details.put("transporter", rset.getString("transporter"));
				details.put("hamali_per_bag", rset.getDouble("hamali_per_bag"));
				details.put("crossing_quantity", rset.getDouble("crossing_quantity"));
				details.put("direct_quantity", rset.getDouble("direct_quantity"));
				details.put("fk_association_name", rset.getString("association_name"));
				details.put("fk_unload_location_name", rset.getString("unload_location_name"));
				details.put("fk_action_name", rset.getString("action_name"));
				details.put("truck_number_image_url", rset.getString("truck_number_image_url"));
				details.put("work_done_image_url", rset.getString("work_done_image_url"));
				getInwardDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("inwardDetails",getInwardDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> updateInward( int id,String invoice_number,String invoice_date,
			String inward_date,Double quantity,String fk_type_of_cement_name,String truck_number,String transporter,
			Double hamali_per_bag,String fk_association_name,String fk_unload_location_name,
			String fk_action_name,double crossing_quantity,double direct_quantity) throws Exception{ 

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_INWARD));

			callableStatement.setInt("id", id);
			callableStatement.setString("invoice_number",invoice_number );
			callableStatement.setString("invoice_date",invoice_date);
			callableStatement.setString("inward_date",inward_date );
			callableStatement.setDouble("quantity",quantity);
			callableStatement.setString("fk_type_of_cement_name",fk_type_of_cement_name );
			callableStatement.setString("truck_number",truck_number);
			callableStatement.setString("transporter",transporter );
			callableStatement.setDouble("hamali_per_bag",hamali_per_bag);
			callableStatement.setString("fk_association_name",fk_association_name );
			callableStatement.setString("fk_unload_location_name",fk_unload_location_name);
			callableStatement.setString("fk_action_name",fk_action_name );
			callableStatement.setDouble("crossing_quantity",crossing_quantity );
			callableStatement.setDouble("direct_quantity",direct_quantity );


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);




			rset=callableStatement.executeQuery(); 
			//	System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getBoolean("outResult"));

			}
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteInward( int id){ 

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null; 

			//Connection con=null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_INWARD));

			callableStatement.setInt("Pk_id", id);
			System.out.println(+id);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}
		}catch (Exception e) {

		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> unloadInward(){ 
		ArrayList<HashMap<String, Object>> unloadDetails = new ArrayList<HashMap<String, Object>>();	
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_UNLOAD));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 


			/*if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}*/
			//		ArrayList<HashMap<String,Object>> unloadDetails= new ArrayList<HashMap<String,Object>>();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				System.out.println(+rset.getInt("pk_unload_location_id"));
				details.put("pk_unload_location_id", rset.getInt("pk_unload_location_id"));
				details.put("locations", rset.getString("locations"));
				details.put("fk_association_id", rset.getInt("fk_association_id"));
				details.put("association_name", rset.getString("association_name"));

				unloadDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}

		responseParameters.put("unloadDetails",unloadDetails);
		//	responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		//	responseParameters.put("message", callableStatement.getString("message"));


		return responseParameters;
	}
	public static HashMap<String, Object> getBill(String upperDate,String lowerDate,String associationName,String unloadLocationName) throws Exception{ 
		ArrayList<HashMap<String, Object>> getBill = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null; 

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BILL));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("associationName",associationName);
			callableStatement.setString("unloadLocationName",unloadLocationName);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();

				details.put("totalquantity", rset.getDouble("totalquantity"));
				details.put("totalCrossing", rset.getDouble("totalCrossing"));
				details.put("actionlist", rset.getString("actionlist"));
				details.put("typeCement", rset.getString("typeCement"));
				details.put("totalDirect", rset.getString("totalDirect"));

				getBill.add(details);
			}

		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}
		responseParameters.put("bills",getBill);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> addoutward(String invoice_number,
			String customer_name, String invoice_date, String date_of_outward,
			double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String hamali_per_bags, String  fk_action_id,
			String outwardTruckNumberImageBytes,String outwardTtruckNumberImageType,
			String outwardWorkDoneImageBytes,String outwardWorkDoneImageType,
			double freight_per_bag, double distances,double received_payment)throws SQLException{ 

		ArrayList<HashMap<String, Object>> getBill = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}
			connection.setAutoCommit(false);
			ResultSet rset = null;  

			//Connection con=null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OUTWARD));

			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("customer_name", customer_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("date_of_outward", date_of_outward);
			callableStatement.setDouble("quantity", quantity);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("transporter", transporter);
			callableStatement.setString("fk_association_id", fk_association_id);
			callableStatement.setString("fk_type_of_cement_id", fk_type_of_cement_id);
			callableStatement.setString("fk_unload_location_id", fk_unload_location_id);
			callableStatement.setString("hamali_per_bags", hamali_per_bags);
			callableStatement.setString("fk_action_id", fk_action_id);
			callableStatement.setDouble("freight_per_bag", freight_per_bag);
			callableStatement.setDouble("distance", distances);
			callableStatement.setDouble("received_payment", received_payment);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();  
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("message", callableStatement.getString("message"));
				connection.rollback();
				return responseParameters;
			} else {
				String primary_id=callableStatement.getString("primary_id");
				String outward_truck_number_bucket="nikithaimages";
				String outward_truck_number_folder="outward truck number images";
				// work done images
				String outward_truck_number_uploaded_image_url="";

				if(!outwardTruckNumberImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(outwardTruckNumberImageBytes,outwardTtruckNumberImageType,outward_truck_number_bucket,outward_truck_number_folder,primary_id);
					String outwardTruckNumberFileName = outward_truck_number_folder + "/" + primary_id + "." + outwardTtruckNumberImageType;
					outward_truck_number_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+outward_truck_number_bucket+"/"+outwardTruckNumberFileName;

				}


				//	String primary_id =callableStatement.getString("primary_id");
				String outward_work_done_bucket="nikithaimages";
				String outward_work_done_folder=" outward work done images";

				String outward_word_done_uploaded_image_url="";

				if(!outwardWorkDoneImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(outwardWorkDoneImageBytes,outwardWorkDoneImageType,outward_work_done_bucket,outward_work_done_folder,primary_id);
					String outwardWorkDoneFileName = outward_work_done_folder + "/" + primary_id + "." + outwardWorkDoneImageType;
					outward_word_done_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+outward_work_done_bucket+"/"+outwardWorkDoneFileName;

				}
				/*ImageUploadDAO.addPetrolPumpReadingImageUpload(primary_id,petrol_pump_reading_uploaded_image_url,
						starting_meter_reading_uploaded_image_url);		*/

				CallableStatement callableStatement2 = null;
				ResultSet rset2 = null;

				try {
					callableStatement2 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OUTWARD_TRUCK_OR_WORK_IMAGE_URLS));
					callableStatement2.setString("primary_id", primary_id);
					callableStatement2.setString("outward_truck_number_uploaded_image_url", outward_truck_number_uploaded_image_url);
					callableStatement2.setString("outward_word_done_uploaded_image_url", outward_word_done_uploaded_image_url);

					callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

					rset2 = callableStatement2.executeQuery();
					System.out.println(callableStatement2);


					if (!callableStatement2.getBoolean("outResult")) {

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

				responseParameters.put("message", callableStatement.getString("message"));
				connection.commit();
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		return responseParameters;
	}

	public static HashMap<String, Object> addOutwardHamali(	String customer_name, String invoice_date,
			double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String hamali_per_bags, String  fk_action_id,
			String outwardTruckNumberImageBytes,String outwardTtruckNumberImageType,
			String outwardWorkDoneImageBytes,String outwardWorkDoneImageType)throws SQLException{ 

		ArrayList<HashMap<String, Object>> getBill = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}
			connection.setAutoCommit(false);
			ResultSet rset = null;  

			//Connection con=null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OUTWARD_HAMALI));

			callableStatement.setString("customer_name", customer_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setDouble("quantity", quantity);
			callableStatement.setString("fk_type_of_cement_id", fk_type_of_cement_id);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("transporter", transporter);
			callableStatement.setString("fk_association_id", fk_association_id);
			callableStatement.setString("fk_unload_location_id", fk_unload_location_id);
			callableStatement.setString("hamali_per_bags", hamali_per_bags);
			callableStatement.setString("fk_action_id", fk_action_id);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();  
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("message", callableStatement.getString("message"));
				connection.rollback();
				return responseParameters;
			} else {
				String primary_id=callableStatement.getString("primary_id");
				String outward_truck_number_bucket="nikithaimages";
				String outward_truck_number_folder="outward truck number images";
				// work done images
				String outward_truck_number_uploaded_image_url="";

				if(!outwardTruckNumberImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(outwardTruckNumberImageBytes,outwardTtruckNumberImageType,outward_truck_number_bucket,outward_truck_number_folder,primary_id);
					String outwardTruckNumberFileName = outward_truck_number_folder + "/" + primary_id + "." + outwardTtruckNumberImageType;
					outward_truck_number_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+outward_truck_number_bucket+"/"+outwardTruckNumberFileName;

				}


				//	String primary_id =callableStatement.getString("primary_id");
				String outward_work_done_bucket="nikithaimages";
				String outward_work_done_folder=" outward work done images";

				String outward_word_done_uploaded_image_url="";

				if(!outwardWorkDoneImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(outwardWorkDoneImageBytes,outwardWorkDoneImageType,outward_work_done_bucket,outward_work_done_folder,primary_id);
					String outwardWorkDoneFileName = outward_work_done_folder + "/" + primary_id + "." + outwardWorkDoneImageType;
					outward_word_done_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+outward_work_done_bucket+"/"+outwardWorkDoneFileName;

				}
				/*ImageUploadDAO.addPetrolPumpReadingImageUpload(primary_id,petrol_pump_reading_uploaded_image_url,
						starting_meter_reading_uploaded_image_url);		*/

				CallableStatement callableStatement2 = null;
				ResultSet rset2 = null;

				try {
					callableStatement2 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OUTWARD_TRUCK_OR_WORK_IMAGE_URLS));
					callableStatement2.setString("primary_id", primary_id);
					callableStatement2.setString("outward_truck_number_uploaded_image_url", outward_truck_number_uploaded_image_url);
					callableStatement2.setString("outward_word_done_uploaded_image_url", outward_word_done_uploaded_image_url);

					callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

					rset2 = callableStatement2.executeQuery();
					System.out.println(callableStatement2);


					if (!callableStatement2.getBoolean("outResult")) {

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

				responseParameters.put("message", callableStatement.getString("message"));
				connection.commit();
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		return responseParameters;
	}




	public static HashMap<String, Object> getOutward(String upperDate,String lowerDate) throws Exception{ 

		ArrayList<HashMap<String, Object>> getOutwardDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null; 

			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OUTWARD));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("username",username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_outward_id", rset.getInt("pk_outward_id"));
				details.put("invoice_number", rset.getString("invoice_number"));
				details.put("customer_name", rset.getString("customer_name"));
				details.put("invoice_date", rset.getString("invoice_date"));
				details.put("date_of_outward", rset.getString("date_of_outward"));
				details.put("quantity", rset.getDouble("quantity"));
				details.put("truck_number", rset.getString("truck_number"));
				details.put("transporter", rset.getString("transporter"));
				details.put("association_name", rset.getString("association_name"));
				details.put("typeofcement", rset.getString("typeofcement"));
				details.put("unload_location", rset.getString("unload_location"));
				details.put("hamali_per_bags", rset.getString("hamali_per_bags"));
				details.put("action_name", rset.getString("action_name"));
				details.put("truck_number_image_url", rset.getString("truck_number_image_url"));
				details.put("work_done_image_url", rset.getString("work_done_image_url"));
				details.put("freight_per_bag", rset.getDouble("freight_per_bag"));
				details.put("distances", rset.getDouble("distances"));
				details.put("received_payment", rset.getDouble("received_payment"));

				getOutwardDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("outwardDetails",getOutwardDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}


	public static HashMap<String, Object> updateOutward( int pk_outward_id,String invoice_number,
			String customer_name, String invoice_date, String date_of_outward,
			double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String hamali_per_bags, String  fk_action_id,
			double freight_per_bag,double distances,double received_payment)throws Exception{ 

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}

			ResultSet rset = null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_OUTWARD));

			callableStatement.setInt("pk_outward_id", pk_outward_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("customer_name", customer_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("date_of_outward", date_of_outward);
			callableStatement.setDouble("quantity", quantity);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("transporter", transporter);
			callableStatement.setString("fk_association_id", fk_association_id);
			callableStatement.setString("fk_type_of_cement_id", fk_type_of_cement_id);
			callableStatement.setString("fk_unload_location_id", fk_unload_location_id);
			callableStatement.setString("hamali_per_bags", hamali_per_bags);
			callableStatement.setString("fk_action_id", fk_action_id);
			callableStatement.setDouble("freight_per_bag", freight_per_bag);
			callableStatement.setDouble("distance", distances);
			callableStatement.setDouble("received_payment", received_payment);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);



			rset=callableStatement.executeQuery(); 
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getBoolean("outResult"));

			}
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteOutward( int pk_outward_id){ 

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			ResultSet rset = null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_OUTWARD));

			callableStatement.setInt("outward_id", pk_outward_id);
			System.out.println(+pk_outward_id);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}
		}catch (Exception e) {

		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> getHamali(String upperDate,String lowerDate, String associationName) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getHamaliDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);  

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_HAMALI));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("association_name",associationName);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_id", rset.getInt("pk_id"));
				details.put("start_date", rset.getString("start_date"));
				details.put("end_date", rset.getString("end_date"));
				details.put("hamali", rset.getString("hamali"));
				details.put("service_charge", rset.getString("service_charge"));
				details.put("fixed_expenses", rset.getDouble("fixed_expenses"));
				details.put("hamali_psc", rset.getString("hamali_psc"));
				details.put("hamali_con", rset.getString("hamali_con"));
				details.put("association_name", rset.getString("association_name"));
				details.put("hamali_trade", rset.getString("hamali_trade"));

				getHamaliDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("HamaliDetails",getHamaliDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> addBill(String association_name, 
			String invoiceNumber,double grandTotal)throws SQLException{ 

		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getAddBill = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_BILL));

			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			System.out.println(sqlDate);

			callableStatement.setString("association_name", association_name);
		//	callableStatement.setString("godown", godown);			
			callableStatement.setString("invoiceNumber", invoiceNumber);
			callableStatement.setDouble("grandTotal", grandTotal);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			System.out.println(callableStatement);
			rset = callableStatement.executeQuery();  
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("message", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		return responseParameters;
	}

	public static HashMap<String, Object> getHamaliAccount(String upperDate,String lowerDate,String associationName,String unloadLocationName,String hamaliType) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> gethamali = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_HAMALI_ACCOUNT));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("associationName",associationName);
			callableStatement.setString("unloadLocationName",unloadLocationName);
			callableStatement.setString("hamaliType",hamaliType);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();

				details.put("invoice_number", rset.getString("invoice_number"));
				details.put("truck_number", rset.getString("truck_number"));
				details.put("quantity", rset.getDouble("quantity1"));
				details.put("actionlist", rset.getString("actionlist"));
				details.put("hamali_per_bag", rset.getString("hamali_per_bag"));
				details.put("crossing1", rset.getString("crossing1"));
				details.put("total", rset.getString("total"));

				gethamali.add(details);
			}

		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}
		responseParameters.put("Hamali",gethamali);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> getOutwardHamali(String upperDate,String lowerDate,String associationName,
			String unloadLocationName,String hamaliType) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> gethamali = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OUTWARD_HAMALI));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("associationName",associationName);
			callableStatement.setString("unloadLocationName",unloadLocationName);
			callableStatement.setString("hamaliType",hamaliType);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();

				details.put("invoice_date", rset.getString("invoice_date"));
				details.put("truck_number", rset.getString("truck_number"));
				details.put("quantity", rset.getDouble("quantity1"));
				details.put("actionlist", rset.getString("actionlist"));
				details.put("crossing1", rset.getString("crossing1"));
				details.put("hamali_per_bag", rset.getString("hamali_per_bag"));
				details.put("total", rset.getString("total"));

				gethamali.add(details);
			}

		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}
		responseParameters.put("Hamali",gethamali);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}
	public static HashMap<String, Object> getHamaliType() throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> hamaliTypeDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true); 

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_HAMALI_TYPE));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_id", rset.getInt("pk_id"));
				details.put("type_list", rset.getString("type_list"));

				hamaliTypeDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("hamaliTypeDetails",hamaliTypeDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> getBillDetails( String associationName) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getBillDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);  

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BILL_DETAILS));


			callableStatement.setString("assosciationType",associationName);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_id", rset.getInt("pk_id"));
				details.put("association_name", rset.getString("association_name"));
				details.put("bill_date", rset.getString("bill_date"));
				details.put("invoiceNumber", rset.getString("invoice_number"));
				details.put("grand_total", rset.getDouble("grand_total"));
				details.put("tds_amount", rset.getDouble("tds_amount"));
				details.put("received_amount", rset.getDouble("received_amount"));
				details.put("received_date", rset.getString("received_date"));


				getBillDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("getBillDetails",getBillDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}
	public static HashMap<String, Object> updateBill(int pk_id,String invoiceNumber,String association_name,
			String godown,String date,double grand_total,double received_amount,double tds_amount,String received_date) throws Exception{ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_BILL));

			callableStatement.setInt("pk_id", pk_id);
			//System.out.println(+id);
			callableStatement.setString("invoiceNumber",invoiceNumber );
			//	System.out.println(invoice_number);
			callableStatement.setString("association_name",association_name);
			//	System.out.println(invoice_date);
			callableStatement.setString("godown",godown );
			callableStatement.setString("date_bill",date );
			//	System.out.println(inward_date);
			callableStatement.setDouble("grand_total",grand_total);
			//	System.out.println(quantity);
			callableStatement.setDouble("received_amount",received_amount);
			callableStatement.setDouble("tds_amount",tds_amount);
			//	System.out.println(fk_type_of_cement_id);
			callableStatement.setString("received_date",received_date);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);




			rset=callableStatement.executeQuery(); 
			//	System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getBoolean("outResult"));

			}
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteBill( int pk_id){ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_BILL));

			callableStatement.setInt("pk_bill_id", pk_id);
			System.out.println(+pk_id);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}
		}catch (Exception e) {

		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> getUserLoginDetails()
			throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_USER_LOGIN_DETAILS));

			callableStatement.setString("username", username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			while (rset.next()) {
				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("godown_name", rset.getString("godown_name"));
				details.put("association_name", rset.getString("association_name"));
				details.put("godown_status", rset.getInt("godown_status"));

				responseParameters.put("loginDetails", details);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addHamaliDetails(String associationName,String startDate,String endDate,double hamali,
			double fixedExpenses,double hamaliPsc,double hamaliCon,double serviceCharge,double hamaliTrade)throws SQLException{ 

		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getHamaliDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_HAMALI_DETAILS));

			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			System.out.println(sqlDate);



			callableStatement.setString("association_name", associationName);
			callableStatement.setString("start_date", startDate);			
			callableStatement.setString("end_date", endDate);
			callableStatement.setDouble("hamali", hamali);
			callableStatement.setDouble("fixed_expenses", fixedExpenses);
			callableStatement.setDouble("hamali_psc", hamaliPsc);
			callableStatement.setDouble("hamali_con", hamaliCon);
			callableStatement.setDouble("service_charge", serviceCharge);
			callableStatement.setDouble("hamali_trade", hamaliTrade);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			System.out.println(callableStatement);
			rset = callableStatement.executeQuery();  
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("message", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		return responseParameters;
	}

	public static HashMap<String, Object> getHamaliDetails( String associationName) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getHamaliDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_HAMALI_DETAILS));


			callableStatement.setString("assosciationType",associationName);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_id", rset.getInt("pk_id"));
				details.put("start_date", rset.getString("start_date"));
				details.put("end_date", rset.getString("end_date"));
				details.put("hamali", rset.getDouble("hamali"));
				details.put("service_charge", rset.getDouble("service_charge"));
				details.put("fixed_expenses", rset.getDouble("fixed_expenses"));
				details.put("hamali_psc", rset.getDouble("hamali_psc"));
				details.put("hamali_con", rset.getDouble("hamali_con"));
				details.put("hamali_trade", rset.getDouble("hamali_trade"));
				details.put("association_name", rset.getString("association_name"));

				getHamaliDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("getHamaliDetails",getHamaliDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> updateHamaliDetails(int pk_id,String associationName,String startDate,String endDate,double hamali,
			double fixedExpenses,double hamaliPsc,double hamaliCon,double serviceCharge,double hamaliTrade) throws Exception{ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap. UPDATE_HAMALI_DETAILS));

			callableStatement.setInt("pk_id", pk_id);

			callableStatement.setString("start_date", startDate);			
			callableStatement.setString("end_date", endDate);
			callableStatement.setDouble("hamali", hamali);
			callableStatement.setDouble("service_charge", serviceCharge);
			callableStatement.setDouble("fixed_expenses", fixedExpenses);
			callableStatement.setDouble("hamali_psc", hamaliPsc);
			callableStatement.setDouble("hamali_con", hamaliCon);
			callableStatement.setString("association_name", associationName);
			callableStatement.setDouble("hamali_trade", hamaliTrade);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);




			rset=callableStatement.executeQuery(); 
			//	System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getBoolean("outResult"));

			}
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteHamaliDetails( int pk_id){ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_HAMALI_DETAILS));

			callableStatement.setInt("pk_hamali_id", pk_id);
			System.out.println(+pk_id);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}
		}catch (Exception e) {

		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> addAdvanceBooking(String invoice_number, String date_of_outward,
			double quantity,  String truck_number,String transporter,double hamali_per_bags, String  fk_action_id,
			String outwardTruckNumberImageBytes, String outwardTtruckNumberImageType,
			String outwardWorkDoneImageBytes,String outwardWorkDoneImageType)throws SQLException{ 

		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getAdvance = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}
			connection.setAutoCommit(false);
			//Connection con=null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_ADVANCE_BOOKING));

			callableStatement.setString("invoice_number", invoice_number);

			callableStatement.setString("date_of_outward", date_of_outward);
			callableStatement.setDouble("quantity", quantity);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("transporter", transporter);
			callableStatement.setDouble("hamali_per_bags", hamali_per_bags);
			callableStatement.setString("fk_action_id", fk_action_id);



			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();  
			System.out.println(callableStatement);


			if (!callableStatement.getBoolean("outResult")) {
				connection.rollback(); 
				responseParameters.put("result", false);
				responseParameters.put("errorCode", " Some error");
				responseParameters.put("message", callableStatement.getString("message"));

				return responseParameters;
			} else {

				String primary_id=callableStatement.getString("primary_id");
				String outward_truck_number_bucket="nikithaimages";
				String outward_truck_number_folder="advance booking truck number images";
				// work done images
				String outward_truck_number_uploaded_image_url="";

				if(!outwardTruckNumberImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(outwardTruckNumberImageBytes,outwardTtruckNumberImageType,outward_truck_number_bucket,outward_truck_number_folder,primary_id);
					String outwardTruckNumberFileName = outward_truck_number_folder + "/" + primary_id + "." + outwardTtruckNumberImageType;
					outward_truck_number_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+outward_truck_number_bucket+"/"+outwardTruckNumberFileName;

				}


				//	String primary_id =callableStatement.getString("primary_id");
				String outward_work_done_bucket="nikithaimages";
				String outward_work_done_folder="advance booking work done images";

				String outward_word_done_uploaded_image_url="";

				if(!outwardWorkDoneImageBytes.equals(""))
				{
					ImageService.uploadToAwsWorkImage(outwardWorkDoneImageBytes,outwardWorkDoneImageType,outward_work_done_bucket,outward_work_done_folder,primary_id);
					String outwardWorkDoneFileName = outward_work_done_folder + "/" + primary_id + "." + outwardWorkDoneImageType;
					outward_word_done_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+outward_work_done_bucket+"/"+outwardWorkDoneFileName;

				}
				/*ImageUploadDAO.addPetrolPumpReadingImageUpload(primary_id,petrol_pump_reading_uploaded_image_url,
						starting_meter_reading_uploaded_image_url);		*/

				CallableStatement callableStatement2 = null;
				ResultSet rset2 = null;

				try {
					callableStatement2 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OUTWARD_TRUCK_OR_WORK_IMAGE_URLS));
					callableStatement2.setString("primary_id", primary_id);
					callableStatement2.setString("outward_truck_number_uploaded_image_url", outward_truck_number_uploaded_image_url);
					callableStatement2.setString("outward_word_done_uploaded_image_url", outward_word_done_uploaded_image_url);

					callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

					rset2 = callableStatement2.executeQuery();
					System.out.println(callableStatement2);


					if (!callableStatement2.getBoolean("outResult")) {

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

				responseParameters.put("message", callableStatement.getString("message"));
				connection.commit();
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		return responseParameters;
	}
	public static HashMap<String, Object> getAdvanceBooking(String upperDate,String lowerDate) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getAdvanceBookingDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);
			
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ADVANCE_BOOKING));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("username",username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_advance_outward_id", rset.getInt("pk_advance_outward_id"));
				details.put("invoice_number", rset.getString("invoice_number"));
				details.put("date_of_outward", rset.getString("date_of_outward"));
				details.put("quantity", rset.getDouble("quantity"));
				details.put("truck_number", rset.getString("truck_number"));
				details.put("transporter", rset.getString("transporter"));
				details.put("hamali_per_bags", rset.getDouble("hamali_per_bags"));
				details.put("action_name", rset.getString("action_name"));
				getAdvanceBookingDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("getAdvanceBookingDetails",getAdvanceBookingDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> updateAdvanceBooking( int pk_advance_outward_id,String invoice_number,String date_of_outward,
			double quantity,  String truck_number,String transporter, double hamali_per_bags, String  fk_action_id)throws Exception{ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			//Connection con=null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_ADVANCE_BOOKING));

			callableStatement.setInt("pk_advance_outward_id", pk_advance_outward_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("date_of_outward", date_of_outward);
			callableStatement.setDouble("quantity", quantity);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("transporter", transporter);
			callableStatement.setDouble("hamali_per_bags", hamali_per_bags);
			callableStatement.setString("fk_action_id", fk_action_id);



			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);



			rset=callableStatement.executeQuery(); 
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getBoolean("outResult"));

			}
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}

		return responseParameters;
	}
	public static HashMap<String, Object> deleteAdvanceBooking( int pk_advance_outward_id){ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true); 

			//Connection con=null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_ADVANCE_BOOKING ));

			callableStatement.setInt("advance_outward_id", pk_advance_outward_id);
			//System.out.println(+pk_outward_id);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}
		}catch (Exception e) {

		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> addCustomer(String customer_name)throws SQLException{ 

		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getHamaliDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_CUSTOMER));

			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			System.out.println(sqlDate);



			callableStatement.setString("customer_name", customer_name);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			System.out.println(callableStatement);
			rset = callableStatement.executeQuery();  
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("message", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		return responseParameters;
	}


	public static HashMap<String, Object> getCustomers() throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> customerNames = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_CUSTOMER));


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_customer_name_id", rset.getInt("pk_customer_name_id"));
				details.put("customer_name", rset.getString("customer_name"));

				customerNames.add(details);
			}
		}
		catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("customerNames",customerNames);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> getOutwardCustomer(String upperDate,String lowerDate,
			String customerName) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getOutwardDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OUTWARD_CUSTOMER ));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("customerName",customerName);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_outward_id", rset.getInt("pk_outward_id"));
				details.put("invoice_number", rset.getString("invoice_number"));
				details.put("customer_name", rset.getString("customer_name"));
				details.put("invoice_date", rset.getString("invoice_date"));
				details.put("quantity", rset.getDouble("quantity"));
				details.put("truck_number", rset.getString("truck_number"));
				details.put("transporter", rset.getString("transporter"));
				details.put("association_name", rset.getString("association_name"));
				details.put("typeofcement", rset.getString("typeofcement"));
				details.put("unload_location", rset.getString("unload_location"));
				details.put("action_name", rset.getString("action_name"));
				details.put("freight_per_bag", rset.getDouble("freight_per_bag"));

				details.put("received_payment", rset.getDouble("received_payment"));
				details.put("balance_amount", rset.getDouble("balance_amount"));
				getOutwardDetails.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("outwardDetails",getOutwardDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}


	public static HashMap<String, Object> updateOutwardCustomer( int pk_outward_id,String invoice_number,
			String customer_name, String invoice_date,double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String  fk_action_id,
			double freight_per_bag,double received_payment)throws Exception{ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_OUTWARD_CUSTOMER));

			callableStatement.setInt("pk_outward_id", pk_outward_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("customer_name", customer_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setDouble("quantity", quantity);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("transporter", transporter);
			callableStatement.setString("fk_association_id", fk_association_id);
			callableStatement.setString("fk_type_of_cement_id", fk_type_of_cement_id);
			callableStatement.setString("fk_unload_location_id", fk_unload_location_id);
			callableStatement.setString("fk_action_id", fk_action_id);
			callableStatement.setDouble("freight_per_bag", freight_per_bag);
			callableStatement.setDouble("received_payment", received_payment);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getBoolean("outResult"));

			}
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteOutwardCustomer( int pk_outward_id){ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_OUTWARD_CUSTOMER));

			callableStatement.setInt("outward_id", pk_outward_id);



			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}
		}catch (Exception e) {

		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> getDcPending(String upperDate,String lowerDate) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getDcPending = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);
			
			responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

			if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
				return responseParameters;
			}
			
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DC_PENDING));

			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("username",username);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_outward_hamali_id", rset.getInt("pk_outward_hamali_id"));

				details.put("customer_name", rset.getString("customer_name"));
				details.put("invoice_date", rset.getString("invoice_date"));

				details.put("quantity", rset.getDouble("quantity"));
				details.put("truck_number", rset.getString("truck_number"));
				details.put("transporter", rset.getString("transporter"));
				details.put("association_name", rset.getString("association_name"));
				details.put("typeofcement", rset.getString("typeofcement"));
				details.put("unload_location", rset.getString("unload_location"));
				details.put("hamali_per_bags", rset.getString("hamali_per_bags"));
				details.put("action_name", rset.getString("action_name"));

				getDcPending.add(details);
			}

		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("getDcPending",getDcPending);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}

	public static HashMap<String, Object> updateDcPending( int pk_outward_hamali_id,String customer_name,
			String invoice_date,double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String hamali_per_bags, String  fk_action_id)throws Exception{ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true); 

			//Connection con=null;

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_DC_PENDING));

			callableStatement.setInt("pk_outward_hamali_id", pk_outward_hamali_id);
			callableStatement.setString("customer_name", customer_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setDouble("quantity", quantity);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("transporter", transporter);
			callableStatement.setString("fk_association_id", fk_association_id);
			callableStatement.setString("fk_type_of_cement_id", fk_type_of_cement_id);
			callableStatement.setString("fk_unload_location_id", fk_unload_location_id);
			callableStatement.setString("hamali_per_bags", hamali_per_bags);
			callableStatement.setString("fk_action_id", fk_action_id);


			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);



			rset=callableStatement.executeQuery(); 
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getBoolean("outResult"));

			}
		}catch (Exception e) {
			System.out.println(e);
		}
		finally {

		}

		return responseParameters;
	}
	public static HashMap<String, Object> deleteDcPending( int pk_outward_hamali_id){ 
		ResultSet rset=null;

		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);

			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_DC_PENDING));

			callableStatement.setInt("outward_hamali_id", pk_outward_hamali_id);



			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery(); 
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("errCode", callableStatement.getString("errCode"));
				responseParameters.put("result", callableStatement.getString("outResult"));

			}
		}catch (Exception e) {

		}
		finally {

		}

		return responseParameters;
	}

	public static HashMap<String, Object> getInwardActions() throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> actionInwardDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true); 
		
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_INWARD_ACTIONS));
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_action_id", rset.getInt("pk_action_id"));
				details.put("name", rset.getString("name"));

				actionInwardDetails.add(details);
			}
		
		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("actionInwardDetails",actionInwardDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}
	public static HashMap<String, Object> getOutwardActions() throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> actionOutwardDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);  
		
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OUTWARD_ACTIONS));
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_action_id", rset.getInt("pk_action_id"));
				details.put("name", rset.getString("name"));

				actionOutwardDetails.add(details);
			}
		
		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("actionOutwardDetails",actionOutwardDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}
	
	public static HashMap<String, Object> getStock(String associationName,String unloadLocationName,String stockType, String typeOfCement) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getStockDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true); 
		
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_STOCK));
			
			
			callableStatement.setString("associationName",associationName);
			callableStatement.setString("unloadLocationName",unloadLocationName);
			callableStatement.setString("stockType",stockType);
			callableStatement.setString("typeOfCement",typeOfCement);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				
				
				details.put("actionlist", rset.getString("actionlist"));
				details.put("quantity", rset.getDouble("totalquantity"));
				details.put("typeCement", rset.getString("typeCement"));
				
				
				getStockDetails.add(details);
			}
		
		}catch (Exception e) {
          System.out.println(e);
		}
		finally {

		}
		responseParameters.put("stock",getStockDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}
	
	public static HashMap<String, Object> getStockType() throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> stockTypeDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true); 
		
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_STOCK_TYPE));
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("stock_id", rset.getInt("stock_id"));
				details.put("stock_type", rset.getString("stock_type"));

				stockTypeDetails.add(details);
			}
		
		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("stockTypeDetails",stockTypeDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}
	
	public static HashMap<String, Object> getActionsBill() throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> actionBillDetails = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			Connection connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);  
		
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ACTIONS_BILL_TYPE));
			
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				details.put("pk_action_bill_id", rset.getInt("pk_action_bill_id"));
				details.put("action_bill_names", rset.getString("action_bill_names"));

				actionBillDetails.add(details);
			}
		
		}catch (Exception e) {

		}
		finally {

		}
		responseParameters.put("actionBillDetails",actionBillDetails);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}
	
	public static HashMap<String, Object> getExcel(String actionList,String associationType,String truckNumber,String material, String quantity,String receiptDate,String storageLocation,String dateOfEGP) throws SQLException{ 
		ResultSet rset=null;
		Connection connection = null;
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);  
		
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_EXCEL_BILL));
			
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
		    System.out.println(  "create connection called from " + st[2] );
			
			callableStatement.setString("truckNumber",truckNumber );
			callableStatement.setString("material",material );
			callableStatement.setString("quantity",quantity);
			callableStatement.setString("receiptDate",receiptDate );
		
			callableStatement.setString("storageLocation",storageLocation);
			callableStatement.setString("dateOfEGP",dateOfEGP );
			callableStatement.setString("actionList",actionList);
			callableStatement.setString("associationType",associationType );
		    
		

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery(); 
			System.out.println(callableStatement);
			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("message", callableStatement.getString("message"));
				
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("result", true);
				
			}
			

			
			
			/*if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorMessage", callableStatement.getString("message"));

				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}*/
		}catch (Exception e) {
			e.printStackTrace();

		}
		finally {
			connection.close();	
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getBillExcel(String upperDate,String lowerDate,String associationName) throws Exception{ 
		ResultSet rset=null;
		ArrayList<HashMap<String, Object>> getBillExcel = new ArrayList<HashMap<String, Object>>();
		CallableStatement callableStatement=null;
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			
			Connection 	connection = JDBCConnectionUtils.getJDBCConnection();
			responseParameters.put("result", true);  
		
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BILL_EXCEL));
			
			callableStatement.setString("lowerDate",lowerDate);
			callableStatement.setString("upperDate",upperDate );
			callableStatement.setString("associationName",associationName);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset=callableStatement.executeQuery();
			System.out.println(callableStatement);
			while(rset.next()){

				HashMap<String, Object> details = new HashMap<String, Object>();
				
				details.put("totalquantity", rset.getDouble("totalquantity"));
				details.put("actionList", rset.getString("actionList"));
				details.put("typeCement", rset.getString("typeCement"));
				/*details.put("invoiceNumber", rset.getString("invoiceNumber"));
				details.put("invoiceNumberBharathi", rset.getString("invoiceNumberBharathi"));
				details.put("invoiceNumberDeccan", rset.getString("invoiceNumberDeccan"));*/
				
				
				getBillExcel.add(details);
			}
		
		}catch (Exception e) {
          System.out.println(e);
		}
		finally {

		}
		responseParameters.put("bills",getBillExcel);
		responseParameters.put("outResult", callableStatement.getBoolean("outResult"));
		responseParameters.put("message", callableStatement.getString("message"));

		return responseParameters;
	}


}
