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
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class FactoryDAO {

	final static Logger log = Logger.getLogger(LoginDAO.class);

	public static HashMap<String, Object> updateFactoryDispatchInitially(int factory_dispatch_id, String invoice_number,
			String start_location, String unload_location_name,int unload_location_id,String type_of_cement, String dispatch_date, Double advance,
			double estimated_km, double starting_meter_reading,double load_quantity, double freight,
			String delivery_number,String po_number,String invoice_number2,double load_quantity2, double freight2,String delivery_number2,
			String po_number2,
			String starting_meter_reading_image_bytes_string, String starting_meter_reading_image_type
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_FACTORY_DISPATCH_INITIALLY));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("unload_location_name", unload_location_name);
			callableStatement.setString("type_of_cement", type_of_cement);
			callableStatement.setInt("unload_location_id", unload_location_id);
			callableStatement.setString("dispatch_date", dispatch_date);
			callableStatement.setDouble("advance", advance);
			callableStatement.setDouble("estimated_km", estimated_km);
			callableStatement.setDouble("starting_meter_reading", starting_meter_reading);
			callableStatement.setDouble("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setString("delivery_number", delivery_number);
			callableStatement.setString("po_number", po_number);
			callableStatement.setString("invoice_number2", invoice_number2);
			callableStatement.setDouble("load_quantity2", load_quantity2);
			callableStatement.setDouble("freight2", freight2);
			callableStatement.setString("delivery_number2", delivery_number2);
			callableStatement.setString("po_number2", po_number2);
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
				String starting_meter_reading_folder="dispatch starting meter reading";

				String starting_meter_reading_uploaded_image_url="";

				if(!starting_meter_reading_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,starting_meter_reading_bucket,starting_meter_reading_folder,primary_id);
					String startingMeterReadingFileName = starting_meter_reading_folder + "/" + primary_id + "." + starting_meter_reading_image_type;
					starting_meter_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+starting_meter_reading_bucket+"/"+startingMeterReadingFileName;

				}
				/*ImageUploadDAO.addDispatchStartingMeterReadingImageUpload(primary_id,starting_meter_reading_uploaded_image_url
						);*/

				CallableStatement callableStatement2 = null;
				ResultSet rset2 = null;

				try {
					callableStatement2 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DISPATCH_STARTING_METER_READING_IMAGE_UPLOAD_URL));
					callableStatement2.setString("primary_id", primary_id);
					callableStatement2.setString("starting_meter_reading_image_url", starting_meter_reading_uploaded_image_url);

					callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

					rset2 = callableStatement2.executeQuery();

					if (!callableStatement2.getBoolean("outResult")) {
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

	public static HashMap<String, Object> addFactoryFuel(String truck_no, int fk_driver_id ,double fuel_quantity, double fuel_rate,
			int fk_fuel_station_id, String date, double advance,
			double starting_meter_reading,double closing_meter_reading,
			String petrol_pump_reading_image_bytes_string,String petrol_pump_reading_image_type,
			String starting_meter_reading_image_bytes_string,String starting_meter_reading_image_type,String dispatch_ids) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FACTORY_FUEL));
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setDouble("fuel_quantity", fuel_quantity);
			callableStatement.setDouble("fuel_rate", fuel_rate);
			//		callableStatement.setDouble("amount", amount);
			callableStatement.setInt("fk_fuel_station_id", fk_fuel_station_id);
			callableStatement.setString("date", date);
			callableStatement.setDouble("advance", advance);
			callableStatement.setDouble("starting_meter_reading", starting_meter_reading);
			callableStatement.setDouble("closing_meter_reading", closing_meter_reading);
			//		callableStatement.setDouble("mileage", mileage);
			callableStatement.setString("username", username);
			callableStatement.setString("dispatch_ids", dispatch_ids);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

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
				System.out.println(callableStatement.getString("message"));
				if(callableStatement.getString("message").equals("inserted successfully")){
					String primary_id=callableStatement.getString("primary_id");
					String petrol_pump_reading_bucket="truckimages";
					String petrol_pump_reading_folder="petrol pump reading";

					String petrol_pump_reading_uploaded_image_url="";

					if(!petrol_pump_reading_image_bytes_string.equals(""))
					{
						ImageService.uploadToAws(petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,petrol_pump_reading_bucket,petrol_pump_reading_folder,primary_id);
						String petrolPumpReadingFileName = petrol_pump_reading_folder + "/" + primary_id + "." + petrol_pump_reading_image_type;
						petrol_pump_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+petrol_pump_reading_bucket+"/"+petrolPumpReadingFileName;

					}
					String starting_meter_reading_bucket="truckimages";
					String starting_meter_reading_folder="fuel starting meter reading";

					String starting_meter_reading_uploaded_image_url="";

					if(!starting_meter_reading_image_bytes_string.equals(""))
					{
						ImageService.uploadToAws(starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,starting_meter_reading_bucket,starting_meter_reading_folder,primary_id);
						String startingMeterReadingFileName = starting_meter_reading_folder + "/" + primary_id + "." + starting_meter_reading_image_type;
						starting_meter_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+starting_meter_reading_bucket+"/"+startingMeterReadingFileName;

					}
					/*ImageUploadDAO.addPetrolPumpReadingImageUpload(primary_id,petrol_pump_reading_uploaded_image_url,
							starting_meter_reading_uploaded_image_url);		*/

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
					
					responseParameters.put("message", callableStatement.getString("message"));
					responseParameters.put("primary_id", primary_id);
				} else{
					responseParameters.put("message", callableStatement.getString("message"));
				}
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
	
	public static HashMap<String, Object> addInvoicePhoto(String petrol_pump_reading_image_bytes_string,
			String petrol_pump_reading_image_type,int dispatch_id) throws TransportException, SQLException {

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
		/*	callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FACTORY_FUEL));*/
			
			
			String petrol_pump_reading_bucket="invoiceimages";
			String petrol_pump_reading_folder="factory-invoice";

			String petrol_pump_reading_uploaded_image_url="";

			if(!petrol_pump_reading_image_bytes_string.equals(""))
			{
				ImageService.uploadToAwsInvoice(petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,petrol_pump_reading_bucket,petrol_pump_reading_folder,dispatch_id);
				String petrolPumpReadingFileName = petrol_pump_reading_folder + "/" + dispatch_id + "." + petrol_pump_reading_image_type;
				petrol_pump_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+petrol_pump_reading_bucket+"/"+petrolPumpReadingFileName;

			}

				callableStatement = connection
						.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_INVOICE_PHOTO));
				callableStatement.setInt("dispatch_id", dispatch_id);
				callableStatement.setString("invoice_image_url", petrol_pump_reading_uploaded_image_url);

				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

				rset = callableStatement.executeQuery();

				if (!callableStatement.getBoolean("outResult")) {
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
				rset.close();
				callableStatement.close();
			}


		return responseParameters;
	}
	
	public static HashMap<String, Object> addFuel(String truck_no, int fk_driver_id ,double fuel_quantity, double fuel_rate,
			int fk_fuel_station_id, String date, double advance,
			double starting_meter_reading,double closing_meter_reading,
			String petrol_pump_reading_image_bytes_string,String petrol_pump_reading_image_type,
			String starting_meter_reading_image_bytes_string,String starting_meter_reading_image_type,String fuel_type) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_ERROR_FUEL));
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setDouble("fuel_quantity", fuel_quantity);
			callableStatement.setDouble("fuel_rate", fuel_rate);
			//		callableStatement.setDouble("amount", amount);
			callableStatement.setInt("fk_fuel_station_id", fk_fuel_station_id);
			callableStatement.setString("date", date);
			callableStatement.setDouble("advance", advance);
			callableStatement.setDouble("starting_meter_reading", starting_meter_reading);
			callableStatement.setDouble("closing_meter_reading", closing_meter_reading);
			//		callableStatement.setDouble("mileage", mileage);
			callableStatement.setString("username", username);
			callableStatement.setString("fuel_type", fuel_type);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("primary_id", java.sql.Types.VARCHAR);
			
			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

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
				String petrol_pump_reading_bucket="truckimages";
				String petrol_pump_reading_folder="petrol pump reading";

				String petrol_pump_reading_uploaded_image_url="";

				if(!petrol_pump_reading_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,petrol_pump_reading_bucket,petrol_pump_reading_folder,primary_id);
					String petrolPumpReadingFileName = petrol_pump_reading_folder + "/" + primary_id + "." + petrol_pump_reading_image_type;
					petrol_pump_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+petrol_pump_reading_bucket+"/"+petrolPumpReadingFileName;

				}


				//			String primary_id=callableStatement.getString("primary_id");
				String starting_meter_reading_bucket="truckimages";
				String starting_meter_reading_folder="fuel starting meter reading";

				String starting_meter_reading_uploaded_image_url="";

				if(!starting_meter_reading_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,starting_meter_reading_bucket,starting_meter_reading_folder,primary_id);
					String startingMeterReadingFileName = starting_meter_reading_folder + "/" + primary_id + "." + starting_meter_reading_image_type;
					starting_meter_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+starting_meter_reading_bucket+"/"+startingMeterReadingFileName;

				}
				/*ImageUploadDAO.addPetrolPumpReadingImageUpload(primary_id,petrol_pump_reading_uploaded_image_url,
						starting_meter_reading_uploaded_image_url);		*/

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

				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("primary_id", primary_id);
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

	public static HashMap<String, Object> addFactoryInvoice(String invoice_number, String customer_first_name,
			String customer_middle_name, String customer_last_name, String name_of_brand,
			String sales_manager_first_name, String sales_manager_middle_name, String sales_manager_last_name,
			String invoice_date, String phone_number, String email, String from_address, String to_address,String type_of_cement,
			int number_of_bags, String total_weight, double cost_per_bag, double freight_rate, double total_value
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FACTORY_INVOICE));
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("customer_first_name", customer_first_name);
			callableStatement.setString("customer_middle_name", customer_middle_name);
			callableStatement.setString("customer_last_name", customer_last_name);
			callableStatement.setString("name_of_brand", name_of_brand);
			callableStatement.setString("sales_manager_first_name", sales_manager_first_name);
			callableStatement.setString("sales_manager_middle_name", sales_manager_middle_name);
			callableStatement.setString("sales_manager_last_name", sales_manager_last_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("phone_number", phone_number);
			callableStatement.setString("email", email);
			callableStatement.setString("from_address", from_address);
			callableStatement.setString("to_address", to_address);
			callableStatement.setString("type_of_cement", type_of_cement);
			callableStatement.setInt("number_of_bags", number_of_bags);
			callableStatement.setString("total_weight", total_weight);
			callableStatement.setDouble("cost_per_bag", cost_per_bag);
			callableStatement.setDouble("freight_rate", freight_rate);
			callableStatement.setDouble("total_value", total_value);
			//		callableStatement.setString("approve_status", approve_status);
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

	// -----------------------get
	// factory------------------------------------------//
	public static HashMap<String, Object> getFactoryDispatch(String invoice_number)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH));

			callableStatement.setString("invoice_number", invoice_number);
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
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("factory_dispatch_id", rset.getString("pk_factory_dispatch_id"));
				factory.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// String tnumber=rset.getString("truck_number");
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_no", rset.getString("invoice_number"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("time_of_start", rset.getString("time_of_start"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("dispatch_date", rset.getString("dispatch_date"));
				factory.put("advance", rset.getDouble("advance"));
				factory.put("balance", rset.getDouble("balance"));
				factory.put("estimated_km", rset.getInt("estimated_km"));
				factory.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				factory.put("checked_kms", rset.getInt("checked_kms"));
				factory.put("load_quantity", rset.getInt("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("dispatch_status", rset.getString("dispatch_status"));
				factory.put("expenditure", rset.getDouble("expenditure"));

				// factories.add(factory);//
				responseParameters.put("Factories", factory);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryFuel(int factory_fuel_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_FUEL));

			callableStatement.setInt("factory_fuel_id", factory_fuel_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("pk_factory_fuel_id", rset.getInt("pk_factory_fuel_id"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				//		factory.put("driver_name", rset.getString("first_name")+" "+rset.getString("middle_name")+" "+rset.getString("last_name"));
				//		factory.put("driver_name", rset.getString("driver_name"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", (fname + " " + mname + " " + lname).replaceAll("\\s+", " "));
				System.out.println((fname + " " + mname + " " + lname).replaceAll("\\s+", " "));
				factory.put("fuel_quantity", rset.getDouble("fuel_quantity"));
				factory.put("fuel_rate", rset.getDouble("fuel_rate"));
				factory.put("fk_fuel_station_id", rset.getInt("fk_fuel_station_id"));
				factory.put("fuel_station_name", rset.getString("fuel_station_name"));
				factory.put("date", rset.getString("date"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("mileage", rset.getDouble("mileage"));
				factory.put("petrol_pump_reading_image_url", rset.getString("petrol_pump_reading_image_url"));
				factory.put("starting_meter_reading_uploaded_image_url", rset.getString("starting_meter_reading_uploaded_image_url"));

				responseParameters.put("FuelReport", factory);
			}
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryChange(int truck_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_CHANGE));

			callableStatement.setInt("truck_id", truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			//		ArrayList<HashMap<String, Object>> trucksDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> number = new HashMap<String, Object>();
				number.put("fk_factory_dispatch_id", rset.getInt("fk_factory_dispatch_id"));
				number.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname= rset.getString("first_name");
				String mname= rset.getString("middle_name");
				String lname= rset.getString("last_name");
				String driver_name=fname+" "+mname+" "+lname;
				number.put("driver_name", driver_name);

				responseParameters.put("TrucksDetails", number);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryInvoice(String invoice_number)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_INVOICE));

			callableStatement.setString("invoice_number", invoice_number);
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
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("invoice_no", rset.getString("invoice_number"));
				factory.put("factory_invoice_id", rset.getString("pk_factory_invoice_id"));
				String fname = rset.getString("customer_first_name");
				String mname = rset.getString("customer_middle_name");
				String lname = rset.getString("customer_last_name");
				factory.put("customer_first_name", rset.getString("customer_first_name"));
				factory.put("customer_middle_name", rset.getString("customer_middle_name"));
				factory.put("customer_last_name", rset.getString("customer_last_name"));
				factory.put("customer_name", fname + " " + mname + " " + lname);
				factory.put("name_of_brand", rset.getString("name_of_brand"));
				String sfname = rset.getString("sales_manager_first_name");
				String smname = rset.getString("sales_manager_middle_name");
				String slname = rset.getString("sales_manager_last_name");
				factory.put("sales_manager_name", sfname + " " + smname + " " + slname);
				factory.put("sales_manager_first_name", rset.getString("sales_manager_first_name"));
				factory.put("sales_manager_middle_name", rset.getString("sales_manager_middle_name"));
				factory.put("sales_manager_last_name", rset.getString("sales_manager_last_name"));
				factory.put("invoice_date", rset.getString("invoice_date"));
				factory.put("phone_number", rset.getString("phone_number"));
				factory.put("email", rset.getString("email"));
				factory.put("from_address", rset.getString("from_address"));
				factory.put("to_address", rset.getString("to_address"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("number_of_bags", rset.getInt("number_of_bags"));
				factory.put("total_weight", rset.getInt("total_weight"));
				factory.put("cost_per_bag", rset.getDouble("cost_per_bag"));
				factory.put("freight_rate", rset.getDouble("freight_rate"));
				factory.put("total_value", rset.getDouble("total_value"));
				factory.put("approve_status", rset.getString("approve_status"));
				// factories.add(factory);//
				responseParameters.put("Factories", factory);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	// ---------------------------------------------get By
	// Date-------------------------------------------------------------------------//
	public static HashMap<String, Object> getFactoryDispatchByDate(String lower_date, String upper_date, String fk_truck_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("fk_truck_id", fk_truck_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("pk_factory_dispatch_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("dispatch_status", rset.getString("dispatch_status"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryDispatchFuelByDate(String lower_date, String upper_date, String fk_truck_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_FUEL_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("fk_truck_id", fk_truck_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", (fname + " " + mname + " " + lname).replaceAll("\\s+", " "));
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("loading_date", rset.getString("loading_dat"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("liters", rset.getDouble("liters"));
				factory.put("rate", rset.getDouble("rate"));
				factory.put("mileage", rset.getDouble("mileage"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("dispatch_status", rset.getString("dispatch_status"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryDispatchById(int factory_dispatch_id, String company_name)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_BY_ID));

			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setString("company_name", company_name);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			//		ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("invoice_number2", rset.getString("invoice_number2"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("dispatch_id"));
				factory.put("unloadLocation", rset.getString("unload_location"));
				factory.put("unload_location_id", rset.getInt("fk_unload_location_id"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("companyName", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date").replace(" ", "T"));
				factory.put("invoice_date", rset.getString("invoice_date").replace(" ", "T"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("load_quantity2", rset.getDouble("load_quantity2"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("freight2", rset.getDouble("freight2"));
				factory.put("fk_association_id", rset.getInt("fk_association_id"));
				factory.put("unload_location_name", rset.getString("unload_location_name"));
				factory.put("outside_company_id", rset.getInt("fk_outside_company_id"));

				responseParameters.put("Factories", factory);

			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getUnloadReportById(int factory_dispatch_id)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_UNLOAD_REPORT_BY_ID));

			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			while (rset.next()) {
				HashMap<String, Object> report = new HashMap<String, Object>();
				report.put("id", rset.getInt("pk_factory_dispatch_id"));
				report.put("truck_number", rset.getString("truck_number"));
				report.put("invoice_number", rset.getString("invoice_number"));
				report.put("start_location", rset.getString("start_location"));
				report.put("unload_location", rset.getString("unload_location"));
				report.put("starting_meter_reading", rset.getString("starting_meter_reading"));
				report.put("closing_meter_reading", rset.getString("closing_meter_reading"));
				report.put("date", rset.getString("date"));

				responseParameters.put("unloadReport", report);

			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> totalDispatchReport(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.TOTAL_DISPATCH_REPORT));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("pk_factory_dispatch_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				factory.put("load_quantity", rset.getInt("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("dispatch_status", rset.getString("dispatch_status"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryDispatchDailyReport(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_DAILY_REPORT));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("invoice1", rset.getString("invoice1"));
				factory.put("invoice2", rset.getString("invoice2"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("dispatch_id", rset.getInt("dispatch_id"));
				factory.put("fk_association_id", rset.getInt("fk_association_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("company_name", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date"));
				/*				factory.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getInt("closing_meter_reading"));*/
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("load1", rset.getDouble("load1"));
				factory.put("load2", rset.getDouble("load2"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("unload_location_name", rset.getString("unload_location_name"));
				factory.put("unload_report_locations", rset.getString("unload_report_locations"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("checked_kms", rset.getDouble("checked_kms"));
				
				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchLoadingReport(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_LOADING_REPORT));

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
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("invoice1", rset.getString("invoice1"));
				factory.put("invoice2", rset.getString("invoice2"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("dispatch_id", rset.getInt("dispatch_id"));
				factory.put("fk_association_id", rset.getInt("fk_association_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("company_name", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date"));
				/*				factory.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getInt("closing_meter_reading"));*/
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("load1", rset.getDouble("load1"));
				factory.put("load2", rset.getDouble("load2"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("unload_location_name", rset.getString("unload_location_name"));
				factory.put("unload_report_locations", rset.getString("unload_report_locations"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getQuantityAndFreigt(String lower_date, String upper_date,String company_name)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_QUANTITY_AND_FREIGHT));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("company_name", company_name);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));

				factories.add(factory);
			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}


	public static HashMap<String, Object> getFactoriesCumulativeReport(String lower_date, String upper_date, String association_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORIES_CUMULATIVE_REPORT));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("association_id", association_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("truck_type", rset.getString("truck_type"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("dispatch_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("company_name", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("dispatch_status", rset.getString("dispatch_status"));
				factory.put("fk_association_id", rset.getInt("fk_association_id"));
				factory.put("unload_location_name", rset.getString("unload_location_name"));
				factory.put("unload_report_locations", rset.getString("unload_report_locations"));
				factory.put("association_name", rset.getString("association_name"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getDispatchReportForBill(String lower_date, String upper_date, String association_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DISPATCH_REPORT_FOR_BILL));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("association_id", association_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("bill_id", rset.getString("bill_id"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("truck_type", rset.getString("truck_type"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("dispatch_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("company_name", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("dispatch_status", rset.getString("dispatch_status"));
				factory.put("fk_association_id", rset.getInt("fk_association_id"));
				factory.put("unload_location_name", rset.getString("unload_location_name"));
				factory.put("unload_report_locations", rset.getString("unload_report_locations"));
				factory.put("association_name", rset.getString("association_name"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getDispatchFuelLinking()
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DISPATCH_FUEL_LINKING));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("pk_factory_dispatch_id", rset.getInt("dispatch_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("association_name", rset.getString("association_name"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getGeneratedBills(int association_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_GENERATED_BILLS));

			callableStatement.setInt("association_id", association_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();
			HashMap<String,ArrayList<HashMap<String,Object>>> sub=new HashMap<String,ArrayList<HashMap<String,Object>>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("bill_id", rset.getString("bill_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("truck_type", rset.getString("truck_type"));
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("dispatch_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("company_name", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("dispatch_status", rset.getString("dispatch_status"));
				factory.put("fk_association_id", rset.getInt("fk_association_id"));
				factory.put("unload_location_name", rset.getString("unload_location_name"));
				factory.put("unload_report_locations", rset.getString("unload_report_locations"));
				factory.put("association_name", rset.getString("association_name"));
				factory.put("bill_date_time", rset.getString("bill_date_time"));

				factories.add(factory);

			}
			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getGeneratedBillsGroup(int association_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_GENERATED_BILLS_GROUP));

			callableStatement.setInt("association_id", association_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);
			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> billDetails = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("association_name", rset.getString("association_name"));
				factory.put("bill_date_time", rset.getString("bill_date_time"));
				
				billDetails.add(factory);

			}
			responseParameters.put("billDetails", billDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> updateSvtcBill(String dispatch_ids)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_DISPATCH_BILL_IDS));
			
			callableStatement.setString("dispatch_ids", dispatch_ids);

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

	public static HashMap<String, Object> getFactoryDispatchBill(String lower_date, String upper_date, int association_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_BILL));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setInt("association_id", association_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("start_location", rset.getString("start_location"));
				factory.put("pk_factory_dispatch_id", rset.getInt("dispatch_id"));
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("company_name", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));

				factories.add(factory);
			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryDispatchBillCount(String lower_date, String upper_date, int association_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_BILL_COUNT));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setInt("association_id", association_id);
			//	callableStatement.

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("unload_location", rset.getString("unload_location"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));

				factories.add(factory);
			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryFuelByDate(String lower_date, String upper_date, String truck_no)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_FUEL_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("pk_factory_fuel_id", rset.getInt("pk_factory_fuel_id"));
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", (fname + " " + mname + " " + lname).replaceAll("\\s+", " "));
				factory.put("fuel_quantity", rset.getDouble("fuel_quantity"));
				factory.put("fuel_rate", rset.getDouble("fuel_rate"));
				factory.put("fk_fuel_station_id", rset.getInt("fk_fuel_station_id"));
				factory.put("fuel_station_name", rset.getString("fuel_station_name"));
				factory.put("date", rset.getString("date"));
				factory.put("starting_meter_reading", rset.getDouble("starting_meter_reading"));
				factory.put("closing_meter_reading", rset.getDouble("closing_meter_reading"));
				factory.put("mileage", rset.getDouble("mileage"));
				factory.put("petrol_pump_reading_image_url", rset.getString("petrol_pump_reading_image_url"));
				factory.put("starting_meter_reading_uploaded_image_url", rset.getString("starting_meter_reading_uploaded_image_url"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryInvoiceByDate(String lower_date, String upper_date)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_INVOICE_BY_DATE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("invoice_number", rset.getString("invoice_number"));
				String fname = rset.getString("customer_first_name");
				String mname = rset.getString("customer_middle_name");
				String lname = rset.getString("customer_last_name");
				factory.put("customer_name", fname + " " + mname + " " + lname);
				factory.put("name_of_brand", rset.getString("name_of_brand"));
				String sfname = rset.getString("sales_manager_first_name");
				String smname = rset.getString("sales_manager_middle_name");
				String slname = rset.getString("sales_manager_last_name");
				factory.put("sales_manager_name", sfname + " " + smname + " " + slname);
				factory.put("invoice_date", rset.getString("invoice_date"));
				factory.put("phone_number", rset.getString("phone_number"));
				factory.put("email", rset.getString("email"));
				factory.put("from_address", rset.getString("from_address"));
				factory.put("to_address", rset.getString("to_address"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("number_of_bags", rset.getInt("number_of_bags"));
				factory.put("total_weight", rset.getInt("total_weight"));
				factory.put("cost_per_bag", rset.getDouble("cost_per_bag"));
				factory.put("freight_rate", rset.getDouble("freight_rate"));
				factory.put("total_value", rset.getDouble("total_value"));
				factory.put("approve_status", rset.getString("approve_status"));
				factories.add(factory);

			}
			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	// --------------------------------------update-------------------------------------------------------------------//

	public static HashMap<String, Object> updateFactoryDispatch(int factory_dispatch_id,String invoice_number,
			String loading_date,String invoice_date,String unload_location_name,String type_of_cement,double load_quantity,
			double freight,int unload_location_id,String invoice_number2,double freight2,double load_quantity2)
					throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_FACTORY_DISPATCH));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("loading_date", loading_date);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("unload_location_name", unload_location_name);
			callableStatement.setString("type_of_cement", type_of_cement);
			callableStatement.setDouble("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setDouble("unload_location_id", unload_location_id);
			callableStatement.setString("username", username);
			callableStatement.setString("invoice_number2", invoice_number2);
			callableStatement.setDouble("load_quantity2", load_quantity2);
			callableStatement.setDouble("freight2", freight2);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
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
	
	public static HashMap<String, Object> updateUnloadReport(int factory_dispatch_id, double starting_meter_reading, 
			double closing_meter_reading,String	unload_report_locations, int checked_kms)
					throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_UNLOAD_REPORT));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setDouble("starting_meter_reading", starting_meter_reading);
			callableStatement.setDouble("closing_meter_reading", closing_meter_reading);
			callableStatement.setString("unload_report_locations", unload_report_locations);
			callableStatement.setInt("checked_kms", checked_kms);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
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

	public static HashMap<String, Object> updateOutsideFactoryDispatch(int factory_dispatch_id,String invoice_number,String truck_number,
			String driver_name,String invoice_date, String unload_location_name,String type_of_cement,double load_quantity,double freight,
			int unload_location_id,int outside_company_id)
					throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_OUTSIDE_FACTORY_DISPATCH));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("driver_name", driver_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("unload_location_name", unload_location_name);
			callableStatement.setString("type_of_cement", type_of_cement);
			callableStatement.setDouble("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setDouble("unload_location_id", unload_location_id);
			callableStatement.setDouble("outside_company_id", outside_company_id);
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

	public static HashMap<String, Object> updateFactoryFuel(int factory_fuel_id, String truck_no,int fk_driver_id,double fuel_quantity,double fuel_rate,
			String date,String starting_meter_reading,String closing_meter_reading,int fk_fuel_station_id)
					throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_FACTORY_FUEL));
			callableStatement.setInt("factory_fuel_id", factory_fuel_id);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setDouble("fuel_quantity", fuel_quantity);
			callableStatement.setDouble("fuel_rate", fuel_rate);
			callableStatement.setString("date", date);
			callableStatement.setInt("fk_fuel_station_id", fk_fuel_station_id);
			callableStatement.setString("username", username);
			callableStatement.setString("starting_meter_reading", starting_meter_reading);
			callableStatement.setString("closing_meter_reading", closing_meter_reading);

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

	public static HashMap<String, Object> updateFactoryInvoice(int factory_invoice_id, String invoice_number,
			String customer_first_name, String customer_middle_name, String customer_last_name, String name_of_brand,
			String sales_manager_first_name, String sales_manager_middle_name, String sales_manager_last_name,
			String invoice_date, String phone_number, String email, String from_address, String to_address,String type_of_cement,
			int number_of_bags, String total_weight, double cost_per_bag, double freight_rate, double total_value,
			String approve_status) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_FACTORY_INVOICE));
			callableStatement.setInt("factory_invoice_id", factory_invoice_id);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("customer_first_name", customer_first_name);
			callableStatement.setString("customer_middle_name", customer_middle_name);
			callableStatement.setString("customer_last_name", customer_last_name);
			callableStatement.setString("name_of_brand", name_of_brand);
			callableStatement.setString("sales_manager_first_name", sales_manager_first_name);
			callableStatement.setString("sales_manager_middle_name", sales_manager_middle_name);
			callableStatement.setString("sales_manager_last_name", sales_manager_last_name);
			callableStatement.setString("invoice_date", invoice_date);
			callableStatement.setString("phone_number", phone_number);
			callableStatement.setString("email", email);
			callableStatement.setString("from_address", from_address);
			callableStatement.setString("to_address", to_address);
			callableStatement.setString("type_of_cement", type_of_cement);
			callableStatement.setInt("number_of_bags", number_of_bags);
			callableStatement.setString("total_weight", total_weight);
			callableStatement.setDouble("cost_per_bag", cost_per_bag);
			callableStatement.setDouble("freight_rate", freight_rate);
			callableStatement.setDouble("total_value", total_value);
			callableStatement.setString("approve_status", approve_status);
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

	// ------------------------------------------------------status--------------------------------------------------------------//
	public static HashMap<String, Object> getFactoryDispatchStatus(int truck_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_STATUS));

			callableStatement.setInt("fk_truck_id", truck_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			// ArrayList<HashMap<String, Object>> FactoryDispatch = new
			// ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> status = new HashMap<String, Object>();
				status.put("factory_dispatch_id", rset.getInt("pk_factory_dispatch_id"));
				//	status.put("fk_truck_id", rset.getInt("fk_truck_id"));
				///	status.put("truck_number", rset.getString("truck_number"));
				status.put("invoice_number", rset.getString("invoice_number"));
				status.put("unload_location", rset.getString("unload_location"));
				responseParameters.put("StatusDispatch", status);

			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateFactoryDispatchStatus(int factory_dispatch_id, String dispatch_status,
			String waiting_location,String unload_report_locations)
					throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_FACTORY_DISPATCH_STATUS));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setString("dispatch_status", dispatch_status);
			callableStatement.setString("waiting_location", waiting_location);
			callableStatement.setString("unload_report_locations", unload_report_locations);
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

	public static HashMap<String, Object> getCloseDispatch(int truck_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_CLOSE_FACTORY_DISPATCH));

			callableStatement.setInt("fk_truck_id", truck_id);
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
				HashMap<String, Object> dispatch = new HashMap<String, Object>();
				dispatch.put("factory_dispatch_id", rset.getString("pk_factory_dispatch_id"));
				//	dispatch.put("fk_truck_id", rset.getInt("fk_truck_id"));
				// String tnumber=rset.getString("truck_number");
				//	dispatch.put("truck_number", rset.getString("truck_number"));
				dispatch.put("fk_driver_id", rset.getInt("fk_driver_id"));
				dispatch.put("invoice_no", rset.getString("invoice_number"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				dispatch.put("driver_name", fname + " " + mname + " " + lname);
				dispatch.put("start_location", rset.getString("start_location"));
				dispatch.put("time_of_start", rset.getString("time_of_start"));
				dispatch.put("unload_location", rset.getString("unload_location"));
				dispatch.put("dispatch_date", rset.getString("dispatch_date"));
				//		dispatch.put("advance", rset.getDouble("advance"));
				//	dispatch.put("balance", rset.getDouble("balance"));
				dispatch.put("estimated_km", rset.getInt("estimated_km"));
				dispatch.put("starting_meter_reading", rset.getInt("starting_meter_reading"));
				dispatch.put("closing_meter_reading", rset.getInt("closing_meter_reading"));
				dispatch.put("checked_kms", rset.getInt("checked_kms"));
				dispatch.put("load_quantity", rset.getInt("load_quantity"));
				dispatch.put("freight", rset.getDouble("freight"));
				dispatch.put("dispatch_status", rset.getString("dispatch_status"));
				//		dispatch.put("expenditure", rset.getDouble("expenditure"));

				// factories.add(factory);//
				responseParameters.put("FactoryDispatch", dispatch);
			}

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseDispatch(int factory_dispatch_id,
			double closing_meter_reading,double checked_kms,
			String closing_meter_reading_image_bytes_string,String closing_meter_reading_image_type) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_CLOSE_FACTORY_DISPATCH));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setDouble("closing_meter_reading", closing_meter_reading);
			callableStatement.setDouble("checked_kms", checked_kms);

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
				String closing_meter_reading_bucket="truckimages";
				String closing_meter_reading_folder="dispatch closing meter reading";

				String closing_meter_reading_uploaded_image_url="";

				if(!closing_meter_reading_image_bytes_string.equals(""))
				{
					ImageService.uploadToAws(closing_meter_reading_image_bytes_string,closing_meter_reading_image_type,closing_meter_reading_bucket,closing_meter_reading_folder,primary_id);
					String closingMeterReadingFileName = closing_meter_reading_folder + "/" + primary_id + "." + closing_meter_reading_image_type;
					closing_meter_reading_uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+closing_meter_reading_bucket+"/"+closingMeterReadingFileName;

				}
				/*ImageUploadDAO.addDispatchClosingMeterReadingImageUpload(primary_id,closing_meter_reading_uploaded_image_url
						);*/

				CallableStatement callableStatement2 = null;
				ResultSet rset2 = null;

				try {
					callableStatement2 = connection
							.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DISPATCH_CLOSING_METER_READING_IMAGE_UPLOAD_URL));
					callableStatement2.setString("primary_id", primary_id);
					callableStatement2.setString("closing_meter_reading_image_url", closing_meter_reading_uploaded_image_url);

					callableStatement2.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
					callableStatement2.registerOutParameter("errCode", java.sql.Types.CHAR);
					callableStatement2.registerOutParameter("message", java.sql.Types.VARCHAR);

					rset2 = callableStatement2.executeQuery();

					if (!callableStatement2.getBoolean("outResult")) {
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

	public static HashMap<String, Object> addFactoryDriverChange(int fk_factory_dispatch_id, int fk_driver_id,
			String location, double advance) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FACTORY_DRIVER_CHANGE));
			callableStatement.setInt("fk_factory_dispatch_id", fk_factory_dispatch_id);
			callableStatement.setInt("driver_id", fk_driver_id);
			callableStatement.setString("location", location);
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

	public static HashMap<String, Object> getAssociation() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ASSOCIATION));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> association = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_association_id", rset.getInt("pk_association_id"));
				truck.put("association_name", rset.getString("association_name"));

				association.add(truck);
			}
			responseParameters.put("association", association);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoriesAssociation() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORIES_ASSOCIATION));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> association = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> truck = new HashMap<String, Object>();
				truck.put("pk_association_id", rset.getInt("pk_association_id"));
				truck.put("association_name", rset.getString("association_name"));
				truck.put("association_alias_name", rset.getString("association_alias_name"));

				association.add(truck);
			}
			responseParameters.put("association", association);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryDriverExpenditure(int fk_driver_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DRIVER_EXPENDITURE));

			callableStatement.setInt("fk_driver_id", fk_driver_id);
			//		callableStatement.setString("date", date);

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
				factory.put("pk_factory_dispatch_id", rset.getInt("pk_factory_dispatch_id"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				factory.put("unload_location", rset.getString("unload_location"));
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

	public static HashMap<String, Object> getFactoryDriversCloseExpenditure(int factory_dispatch_id)
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DRIVERS_CLOSE_EXPENDITURE));

			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);

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

	public static HashMap<String, Object> addDriverExpenditure(int factory_dispatch_id,int fk_driver_id,
			int transport,int loading,int cover_tying,int contonment,
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FACTORY_DRIVER_EXPENDITURE));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setInt("driver_id", fk_driver_id);
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
	
	public static HashMap<String, Object> updateDriverExpenditureApproval(int factory_dispatch_id,int fk_driver_id,
			int transport,int loading,int cover_tying,int contonment,
			int toll_gate,int loading_wage,int unloading_wage,
			int phone_bills,int spares_parts,int puncher,int entry,
			int return_transport,int return_loading,int return_unloading,int others) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_DRIVER_EXPENDITURE_APPROVAL));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);
			callableStatement.setInt("driver_id", fk_driver_id);
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
	
	public static HashMap<String, Object> addDriverAdvance(int fk_truck_id,int fk_driver_id, double advance,String date) throws TransportException, SQLException {

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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_DRIVER_ADVANCE));
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setString("username", username);
			callableStatement.setDouble("advance", advance);
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

	public static HashMap<String, Object> addFactoryLoading(int fk_truck_id, int fk_driver_id, 
			int fk_association_id, String dispatch_status
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_FACTORY_LOADING));
			callableStatement.setInt("fk_truck_id", fk_truck_id);
			callableStatement.setInt("fk_driver_id", fk_driver_id);
			callableStatement.setInt("fk_association_id", fk_association_id);
			callableStatement.setString("dispatch_status", dispatch_status);
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

	public static HashMap<String, Object> getFuelStationName() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FUEL_STATION_NAME));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> fuelStation = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> station = new HashMap<String, Object>();
				station.put("fuel_station_id", rset.getInt("pk_fuel_station_id"));
				station.put("fuel_station_name", rset.getString("fuel_station_name"));

				fuelStation.add(station);
			}
			responseParameters.put("fuelStation", fuelStation);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getUnloadReport() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_UNLOAD_REPORT));
			// HashMap<String, Object> cards = new HashMap<String, Object>();
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> unloadReport = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> report = new HashMap<String, Object>();
				report.put("id", rset.getInt("pk_factory_dispatch_id"));
				report.put("truck_number", rset.getString("truck_number"));
				report.put("invoice_number", rset.getString("invoice_number"));
				report.put("start_location", rset.getString("start_location"));
				report.put("unload_location", rset.getString("unload_location"));
				report.put("starting_meter_reading", rset.getString("starting_meter_reading"));
				report.put("closing_meter_reading", rset.getString("closing_meter_reading"));
				report.put("date", rset.getString("date"));

				unloadReport.add(report);
			}
			responseParameters.put("unloadReport", unloadReport);

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {

			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getUnloadLocationNames(int association_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_UNLOAD_LOCATION_NAMES));

			callableStatement.setInt("association_id", association_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> unloadLocation = new ArrayList<HashMap<String, Object>>();


			while (rset.next()) {
				HashMap<String, Object> location = new HashMap<String, Object>();
				location.put("pk_unload_location_id", rset.getInt("pk_unload_location_id"));
				location.put("name", rset.getString("name"));
				unloadLocation.add(location);
			}
			responseParameters.put("unloadLocations", unloadLocation);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getOutsideCompanyNames(int association_id) throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OUTSIDE_COMPANY_NAMES));

			callableStatement.setInt("association_id", association_id);
			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> companyNames = new ArrayList<HashMap<String, Object>>();
			ArrayList<String> names_list = new ArrayList<String>();

			while (rset.next()) {
				HashMap<String, Object> name = new HashMap<String, Object>();
				name.put("pk_outside_company_id", rset.getInt("pk_outside_company_id"));
				name.put("outside_company_name", rset.getString("outside_company_name"));
				companyNames.add(name);
				String cname= rset.getString("outside_company_name");
				names_list.add(cname);
			}
			responseParameters.put("companyNames", companyNames);
			responseParameters.put("names_list", names_list);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchForInvoicePhoto() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_DISPATCH_FOR_INVOICE_PHOTO));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> details = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> name = new HashMap<String, Object>();
				name.put("dispatch_id", rset.getInt("dispatch_id"));
				name.put("truck_number", rset.getString("truck_number"));
				name.put("invoice_number", rset.getString("invoice_number"));
				name.put("factory_name", rset.getString("factory_name"));
				name.put("driver_name", rset.getString("driver_name"));
				details.add(name);
			}
			responseParameters.put("details", details);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getDriverExpenditureForApproval() throws TransportException, SQLException {
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_DRIVER_EXPENDITURE_FOR_APPROVAL));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("invoice_number", rset.getString("invoice_number"));
				String fname = rset.getString("first_name");
				String mname = rset.getString("middle_name");
				String lname = rset.getString("last_name");
				factory.put("driver_name", fname + " " + mname + " " + lname);
				factory.put("start_location", rset.getString("start_location"));
				factory.put("dispatch_id", rset.getInt("pk_factory_dispatch_id"));
		//		factory.put("fk_association_id", rset.getInt("fk_association_id"));
				factory.put("unload_location", rset.getString("unload_location"));
		//		factory.put("company_name", rset.getString("company_name"));
				factory.put("loading_date", rset.getString("loading_date"));
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("type_of_cement", rset.getString("type_of_cement"));
				factory.put("fk_driver_id", rset.getInt("fk_driver_id"));
				
				factory.put("transport", rset.getInt("transport"));
				factory.put("load", rset.getDouble("loading"));
				factory.put("balance", rset.getInt("balance"));
				factory.put("cover_tying", rset.getInt("cover_tying"));
				factory.put("contonment", rset.getInt("contonment"));
				factory.put("toll_gate", rset.getInt("toll_gate"));
				factory.put("loading_wage", rset.getInt("loading_wage"));
				factory.put("unloading_wage", rset.getInt("unloading_wage"));
				factory.put("phone_bills", rset.getInt("phone_bills"));
				factory.put("spares_parts", rset.getInt("spares_parts"));
				factory.put("puncher", rset.getInt("puncher"));
				factory.put("entry", rset.getInt("entry"));
				factory.put("return_transport", rset.getInt("return_transport"));
				factory.put("return_loading", rset.getInt("return_loading"));
				factory.put("return_unloading", rset.getInt("return_unloading"));
				factory.put("others", rset.getInt("others"));

				factories.add(factory);

			}

			responseParameters.put("Factories", factories);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addOutsideFactoryDispatch(String truck_number,String driver_name,String driver_phone_number, 
			String invoice_number, String start_location,String unload_location_name,int unload_location_id,String type_of_cement, String dispatch_date,
			double load_quantity, double freight,int fk_association_id, int outside_company_id,
			String delivery_number,String po_number,String invoice_number2,double load_quantity2,double freight2,
			String delivery_number2,String po_number2
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
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OUTSIDE_FACTORY_DISPATCH));
			callableStatement.setString("truck_number", truck_number);
			callableStatement.setString("driver_name", driver_name);
			callableStatement.setString("driver_phone_number", driver_phone_number);
			callableStatement.setString("invoice_number", invoice_number);
			callableStatement.setString("start_location", start_location);
			callableStatement.setString("unload_location_name", unload_location_name);
			callableStatement.setInt("unload_location_id", unload_location_id);
			callableStatement.setString("type_of_cement", type_of_cement);
			callableStatement.setString("dispatch_date", dispatch_date);
			callableStatement.setDouble("load_quantity", load_quantity);
			callableStatement.setDouble("freight", freight);
			callableStatement.setInt("fk_association_id", fk_association_id);
			callableStatement.setInt("outside_company_id", outside_company_id);
			callableStatement.setString("delivery_number", delivery_number);
			callableStatement.setString("po_number", po_number);
			callableStatement.setString("invoice_number2", invoice_number2);
			callableStatement.setDouble("freight2", freight2);
			callableStatement.setDouble("load_quantity2", load_quantity2);
			callableStatement.setString("delivery_number2", delivery_number2);
			callableStatement.setString("po_number2", po_number2);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				connection.rollback();
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("essage", callableStatement.getString("message"));
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
	
	public static HashMap<String, Object> getFactoryFuelByFuelStation(String lower_date, String upper_date, String fuel_station_id)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
	//	String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FACTORY_FUEL_BY_FUEL_STATION));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);
			callableStatement.setString("fuel_station_id", fuel_station_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("pk_factory_fuel_id", rset.getInt("pk_factory_fuel_id"));
				factory.put("fk_truck_id", rset.getInt("truck_id"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fuel_quantity", rset.getDouble("fuel_quantity"));
				factory.put("fuel_rate", rset.getDouble("fuel_rate"));
				factory.put("fk_fuel_station_id", rset.getInt("fk_fuel_station_id"));
				factory.put("fuel_station_name", rset.getString("fuel_station_name"));
				factory.put("date", rset.getString("date"));
				factory.put("voucher", rset.getString("voucher"));
				
				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> deleteFactoryDispatch(int factory_dispatch_id) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_FACTORY_DISPATCH));
			callableStatement.setInt("factory_dispatch_id", factory_dispatch_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);

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
	
	public static HashMap<String, Object> getFreightAndQuantity(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
	//	String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FREIGHT_AND_QUANTITY));

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
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("load_quantity", rset.getDouble("load_quantity"));
				factory.put("freight", rset.getDouble("freight"));
				factory.put("association_name", rset.getString("association_name"));
				
				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFuelCostReport(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
	//	String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FUEL_COST_REPORT));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("cost", rset.getDouble("cost"));
				factory.put("truck_number", rset.getString("truck_number"));
				factory.put("fuel_station_name", rset.getString("fuel_station_name"));
				
				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFuelStationCostReport(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
	//	String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FUEL_STATION_COST_REPORT));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> factories = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("cost", rset.getDouble("cost"));
				factory.put("fuel_station_name", rset.getString("fuel_station_name"));
				
				factories.add(factory);

			}

			responseParameters.put("Factories", factories);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFreightAndQuantityMonth(String lower_date, String upper_date)
			throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
	//	String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_FREIGHT_AND_QUANTITY_MONTH));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			// checking out param from stored procedure is null
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			ArrayList<HashMap<String, Object>> details = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				HashMap<String, Object> factory = new HashMap<String, Object>();
				factory.put("date", rset.getString("date"));
				factory.put("quantity", rset.getString("quantity"));
				factory.put("freight", rset.getString("freight"));
				
				details.add(factory);

			}

			responseParameters.put("details", details);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	

}
