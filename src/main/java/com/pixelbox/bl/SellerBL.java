package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.FactoryDAO;
import com.pixelbox.dao.SellerDAO;
import com.pixelbox.dao.StorageDAO;


public class SellerBL {
	
	public static HashMap<String, Object> addSellerFuel(
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
			responseParameters = SellerDAO.addSellerFuel( 
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
	
	public static HashMap<String, Object> addSellerDispatch(
			int fk_truck_id,
			int fk_driver_id,
			String invoice_number,
			String start_location,
			String unload_location,
			String dispatch_date,		
			double estimated_km,
			double starting_meter_reading,
			double load_quantity,
			double freight,
			double advance,
			int fk_association_id

			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.addSellerDispatch( 
					fk_truck_id,
					fk_driver_id, 
					invoice_number, 
					start_location,
					unload_location,
					dispatch_date,
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
	
	
	public static HashMap<String, Object> addSellerInvoice(
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
			double cost_per_bag,
			double total_value,
			String sales_manager_name,
			double freight_rate
				
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.addSellerInvoice( 
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
	
	public static HashMap<String, Object> addSellerPurchase(
			String invoice_number,
			String name_of_brand,
			int number_of_bags,
			double cost_per_bag,
			double total_value,
			String date
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.addSellerPurchase( 
					invoice_number,
					name_of_brand, 
					number_of_bags, 
					cost_per_bag,
					total_value,
					date
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> getSellerDispatch(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = SellerDAO.getSellerDispatch(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getSellerFuel(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = SellerDAO.getSellerFuel(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> getSellerInvoice(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = SellerDAO.getSellerInvoice(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getSellerPurchase(String invoice_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = SellerDAO.getSellerPurchase(invoice_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getSellerDispatchByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.getSellerDispatchByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getSellerFuelBydate(String lower_date, String upper_date, int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.getSellerFuelBydate(lower_date, upper_date , fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getSellerInvoiceByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.getSellerInvoiceByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getSellerPurchaseByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.getSellerPurchaseByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateSellerFuel(
			int seller_fuel_id,
	//		String truck_number,
			double fuel_quantity,
			double fuel_rate,
			double amount,
			String fuel_station_location,
			String date
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.updateSellerFuel( 
					seller_fuel_id,
		//			truck_number,
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

	public static HashMap<String, Object> updateSellerDispatch(
			int seller_dispatch_id,
			int fk_truck_id,
			int fk_driver_id,
			String invoice_number,
			String start_location,
			String time_of_start,
			String unload_location,
			String dispatch_date,
			double estimated_km,
			double starting_meter_reading,
			double closing_meter_reading,
			int checked_kms,
			double load_quantity,
			double freight,
			double advance,
			double balance
		//	String dispatch_status	
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.updateSellerDispatch( 
					seller_dispatch_id,
					fk_truck_id,
					fk_driver_id, 
					invoice_number, 
					start_location,
					time_of_start,
					unload_location,
					dispatch_date,
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
	
	
	public static HashMap<String, Object> updateSellerInvoice(
			int seller_invoice_id,
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
			double cost_per_bag,
			double total_value,
			String sales_manager_name,
			double freight_rate,
			String approve_status
				
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.updateSellerInvoice( 
					seller_invoice_id,
	//				invoice_number,
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
	
	public static HashMap<String, Object> updateSellerPurchase(
			int seller_purchase_id,
	//		String invoice_number,
			String name_of_brand,
			int number_of_bags,
			double cost_per_bag,
			double total_value,
			String date
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.updateSellerPurchase( 
					seller_purchase_id,
	//				invoice_number,
					name_of_brand, 
					number_of_bags, 
					cost_per_bag,
					total_value,
					date
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getStatusDispatch(int truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = SellerDAO.getStatusDispatch(truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> updateStatusDispatch(
			int seller_dispatch_id,
			String dispatch_status	,
			String waiting_location
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.updateStatusDispatch( 
					seller_dispatch_id,
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
			responseParameters = SellerDAO.getCloseDispatch(truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> updateCloseDispatch(
			int seller_dispatch_id,
			double closing_meter_reading,int checked_kms
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = SellerDAO.updateCloseDispatch( 
					seller_dispatch_id,closing_meter_reading,
					checked_kms
					
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getDispatchedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = SellerDAO.getDispatchedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getUnloadingTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = SellerDAO.getUnloadingTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getSellerChange(int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SellerDAO.getSellerChange(truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addSellerDriverChange(int fk_seller_dispatch_id, int fk_driver_id,
			String location, double advance) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SellerDAO.addSellerDriverChange(fk_seller_dispatch_id, fk_driver_id, location,
					advance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getSellerDriverExpenditure(int fk_driver_id, String date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SellerDAO.getSellerDriverExpenditure(fk_driver_id, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getSellerDriversCloseExpenditure(int seller_dispatch_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SellerDAO.getSellerDriversCloseExpenditure(seller_dispatch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addSellerDriverExpenditure(int seller_dispatch_id,int fk_driver_id,
		 int transport, int loading,
			int cover_tying, int contonment, int toll_gate, int loading_wage, int unloading_wage, int phone_bills,
			int spares_parts, int puncher, int entry, int return_transport, int return_loading, int return_unloading,
			int others, int balance) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SellerDAO.addSellerDriverExpenditure(seller_dispatch_id, fk_driver_id, transport, loading, cover_tying, contonment,
					toll_gate, loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry,
					return_transport, return_loading, return_unloading, others, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	

}
