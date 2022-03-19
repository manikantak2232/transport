package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.FactoryDAO;
/*import com.pixelbox.dao.CardsDAO;
import com.pixelbox.dao.ConnectedUsersDAO;*/
import com.pixelbox.dao.SellerDAO;
import com.pixelbox.dao.StorageDAO;
import com.pixelbox.dao.TrucksDAO;


public class StorageBL {
	
	public static HashMap<String, Object> addStorageFuel(
			int fk_truck_id,
			int fk_driver_id,
			double fuel_quantity,
			double fuel_rate,
			double amount,
			String fuel_station_location,
			String date,
			double advance
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.addStorageFuel( 
					fk_truck_id,
					fk_driver_id,
					fuel_quantity, 
					fuel_rate, 
					amount,
					fuel_station_location,
					date,
					advance
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addStorageDispatch(
			int fk_truck_id,
			int fk_driver_id,
			String invoice_number,
			String start_location,
			String dispatch_date,
			String unload_location,			
			double estimated_km,
			double starting_meter_reading,
			double load_quantity,
			double freight,
			double advance,
			int fk_association_id
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.addStorageDispatch( 
					fk_truck_id,
					fk_driver_id, 
					invoice_number, 
					start_location,
					dispatch_date,
					unload_location,	
					estimated_km,
					starting_meter_reading,
					load_quantity,
					freight,
					advance,
					fk_association_id
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> addStorageInvoice(
			String invoice_number,
			String customer_first_name,
			String customer_middle_name,
			String customer_last_name,
			String name_of_brand,
			String from_address,
			String to_address,
			String type_of_cement,
			String invoice_date,
			String phone_number,
			String email,
			int number_of_bags,
			double total_weight,
			double cost_per_bag,
			double total_value,
			String sales_manager_name,
			double freight_rate	
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.addStorageInvoice( 
					invoice_number,
					customer_first_name, 
					customer_middle_name, 
					customer_last_name,
					name_of_brand,
					from_address,
					to_address,
					type_of_cement,
					invoice_date,
					phone_number,
					email,
					number_of_bags,
					total_weight,
					cost_per_bag,
					total_value,
					sales_manager_name,
					freight_rate
					
							);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addStorageIncomingLoad(
			String invoice_number ,
			String date,
			String from_address,
			String to_address,
			String brand_name,
			double cost_per_bag,
			double number_of_bags ,
			double total_weight,
			double total_value,
			int truck_number,
			int fk_driver_id,
			String time_of_arrival,
			double reading_km,
			String status
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.addStorageIncomingLoad( 
					invoice_number ,
					date,
					from_address,
					to_address,
					brand_name,
					cost_per_bag,
					number_of_bags ,
					total_weight,
					total_value,
					truck_number,
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
	
	

	public static HashMap<String, Object> getStorageDispatch(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getStorageDispatch(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getStorageFuel(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getStorageFuel(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStorageIncomingLoad(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getStorageIncomingLoad(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getStorageInvoice(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getStorageInvoice(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getStorageDispatchByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.getStorageDispatchByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStorageFuelByDate(String lower_date, String upper_date, int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.getStorageFuelByDate(lower_date, upper_date, fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStorageIncomingLoadByDate(String lower_date, String upper_date) {		

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
			double estimated_km,
			double starting_meter_reading,
			double closing_meter_reading,
			int checked_kms,
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
					estimated_km,
					starting_meter_reading,
					closing_meter_reading,
					checked_kms,
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
	
	public static HashMap<String, Object> getStatusDispatch(int truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getStatusDispatch(truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> updateStatusDispatch(
			int storage_dispatch_id,
			String dispatch_status,
			String waiting_location
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateStatusDispatch( 
					storage_dispatch_id,
					dispatch_status	,
					waiting_location
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getCloseDispatch(int truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getCloseDispatch(truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> updateCloseDispatch(
			int storage_dispatch_id,double closing_meter_reading,
			int checked_kms
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = StorageDAO.updateCloseDispatch( 
					storage_dispatch_id,closing_meter_reading,
					checked_kms);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getDispatchedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getDispatchedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getUnloadingTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = StorageDAO.getUnloadingTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getStorageChange(int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = StorageDAO.getStorageChange(truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addStorageDriverChange(int fk_storage_dispatch_id, int fk_driver_id,
			String location, double advance) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = StorageDAO.addStorageDriverChange(fk_storage_dispatch_id, fk_driver_id, location,
					advance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStorageDriverExpenditure(int fk_driver_id, String date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = StorageDAO.getStorageDriverExpenditure(fk_driver_id, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStorageDriversCloseExpenditure(int storage_dispatch_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = StorageDAO.getStorageDriversCloseExpenditure(storage_dispatch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addStorageDriverExpenditure(int storage_dispatch_id,int fk_driver_id,
		 int transport, int loading,
			int cover_tying, int contonment, int toll_gate, int loading_wage, int unloading_wage, int phone_bills,
			int spares_parts, int puncher, int entry, int return_transport, int return_loading, int return_unloading,
			int others, int balance) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = StorageDAO.addStorageDriverExpenditure(storage_dispatch_id, fk_driver_id, transport, loading, cover_tying, contonment,
					toll_gate, loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry,
					return_transport, return_loading, return_unloading, others, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
}
