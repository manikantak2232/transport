package com.pixelbox.bl;

import java.util.HashMap;

/*import com.pixelbox.dao.CardsDAO;
import com.pixelbox.dao.ConnectedUsersDAO;*/
import com.pixelbox.dao.SellerDAO;
import com.pixelbox.dao.LogDAO;


public class LogBL {
	
	public static HashMap<String, Object> getActionLog(String action_item, String invoice_number, int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = LogDAO.getActionLog(action_item, invoice_number, fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	/*public static HashMap<String, Object> getStorageIncomingLoadByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.getStorageIncomingLoadByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStorageInvoiceByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.getStorageInvoiceByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> updateStorageDispatch(
			int storage_dispatch_id,
			int fk_truck_id,
			int fk_driver_id,
	//		String invoice_number,
			String start_location,
			String dispatch_date,
			String time_of_start,
			String unload_location,			
			int estimated_dispatch_days,
			int estimated_dispatch_hours,
			double estimated_km,
			double starting_meter_reading,
			double closing_meter_reading,
			double load_quantity,
			double freight,
			double advance,
			double balance
//			String dispatch_status	
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateStorageDispatch( 
					storage_dispatch_id,
					fk_truck_id,
					fk_driver_id, 
		//			invoice_number, 
					start_location,
					dispatch_date,
					time_of_start,
					unload_location,	
					estimated_dispatch_days,
					estimated_dispatch_hours,
					estimated_km,
					starting_meter_reading,
					closing_meter_reading,
					load_quantity,
					freight,
					advance,
					balance
	//				dispatch_status	
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateStorageFuel(
			int storage_fuel_id,
		//	int fk_truck_id,
			double fuel_quantity,
			double fuel_rate,
			double amount,
			String fuel_station_location,
			String date
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateStorageFuel( 
					storage_fuel_id,
		//			fk_truck_id,
					fuel_quantity, 
					fuel_rate, 
					amount,
					fuel_station_location,
					date
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	
	public static HashMap<String, Object> updateStorageInvoice(
			int storage_invoice_id,
	//		String invoice_number,
			String customer_first_name,
			String customer_middle_name,
			String customer_last_name,
			String name_of_brand,
			String from_address,
			String to_address,
			String invoice_date,
			String phone_number,
			String email,
			int number_of_bags,
			double total_weight,
			double cost_per_bag,
			double total_value,
			String sales_manager_name,
			double freight_rate,
			String approve_status	
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateStorageInvoice( 
					storage_invoice_id,
		//			invoice_number,
					customer_first_name, 
					customer_middle_name, 
					customer_last_name,
					name_of_brand,
					from_address,
					to_address,
					invoice_date,
					phone_number,
					email,
					number_of_bags,
					total_weight,
					cost_per_bag,
					total_value,
					sales_manager_name,
					freight_rate,
					approve_status
							);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateStorageIncomingLoad(
			int storage_incoming_load_id,
	//		String invoice_number ,
			String date,
			String from_address,
			String to_address,
			String brand_name,
			double cost_per_bag,
			double number_of_bags ,
			double total_weight,
			double total_value,
			int fk_truck_id,
			int fk_driver_id,
			String time_of_arrival,
			double reading_km,
			String status
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateStorageIncomingLoad( 
					storage_incoming_load_id,
		//			invoice_number ,
					date,
					from_address,
					to_address,
					brand_name,
					cost_per_bag,
					number_of_bags ,
					total_weight,
					total_value,
					fk_truck_id,
					fk_driver_id,
					time_of_arrival,
					reading_km,
					status												
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStatusDispatch(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getStatusDispatch(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> updateStatusDispatch(
			int storage_dispatch_id,
			String dispatch_status	
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateStatusDispatch( 
					storage_dispatch_id,
					dispatch_status	
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getCloseDispatch(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getCloseDispatch(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> updateCloseDispatch(
			int storage_dispatch_id,
			String dispatch_status,
			double balance,
			double expenditure,
			double closing_meter_reading
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateCloseDispatch( 
					storage_dispatch_id,
					dispatch_status,
					balance,
					expenditure,
					closing_meter_reading
					
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}*/
	
}
