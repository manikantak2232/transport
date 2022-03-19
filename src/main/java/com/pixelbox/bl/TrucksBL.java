package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.DriverDAO;
import com.pixelbox.dao.SparePartsDAO;
import com.pixelbox.dao.StorageDAO;
import com.pixelbox.dao.TrucksDAO;


public class TrucksBL {
	
	public static HashMap<String, Object> addTrucksAllotment(
			String truck_allotment_date,
			int fk_driver_id,
			String allotment_location,
			int fk_truck_id
					
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.addTrucksAllotment( 
					truck_allotment_date,
					fk_driver_id, 
					allotment_location, 
					fk_truck_id
																		
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> addTrucksDetails(
			String truck_number,
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
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.addTrucksDetails( 
					truck_number,
					chassis_number,
					engine_number,
					registration_date,
					permit_number,
					permit_area,
					permit_validity,
					insurance_company_name,
					insurance_policy_number,
					insurance_date,
					insurance_expiry_date,
					fitness_certificate_number,
					fitness_expire_date,
					fitness_image_bytes_string,
					fitness_image_type,
					rc_image_bytes_string,
					rc_image_type,insurance_image_bytes_string,insurance_image_type,permit_image_bytes_string,
					permit_image_type
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> addTrucksHealthHistory(
			String description,
			int fk_truck_id,
			String date,
			int fk_spare_parts_id	
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.addTrucksHealthHistory( 
					 description,
					 fk_truck_id,
					 date,
					 fk_spare_parts_id
							);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> addTrucksPartsQuote(
			String quote_number ,
			String new_quote_date,
			String product_name,
			String company_name,
			String agent,
			int number_of_units,
			double unit_price ,
			double total,
			int no_of_days_for_delivery,
			int no_of_hours_for_delivery,
			String mode_of_payment,
			String type_of_payment,
			int valid_for_number_of_days,
			String contact_number
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.addTrucksPartsQuote( 
					quote_number ,
					new_quote_date,
					product_name,
					company_name,
					agent,
					number_of_units,
					unit_price ,
					total,
					no_of_days_for_delivery,
					no_of_hours_for_delivery,
					mode_of_payment,
					type_of_payment,
					valid_for_number_of_days,
					contact_number
					
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	/*public static HashMap<String, Object> addTrucksService(
			int fk_truck_id ,
			String service_center_name,
			String service_date,
			double service_total_cost,
			int fk_spare_parts_id,
			String description
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.addTrucksService( 
					fk_truck_id ,
					service_center_name,
					service_date,
					service_total_cost,
					fk_spare_parts_id,
					description
					
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}*/
	
	public static HashMap<String, Object> addTrucksService(
			int fk_truck_id ,
			String service_center_name,
			String service_start_date
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.addTrucksService( 
					fk_truck_id ,
					service_center_name,
					service_start_date
					
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object>  closeTrucksService(
			int fk_truck_id ,
			String service_closed_date,
			int fk_spare_parts_id,
			double service_total_cost
			
			//String truck_service_status
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.closeTrucksService( 
					fk_truck_id ,
					service_closed_date,
					fk_spare_parts_id,
					service_total_cost
				    
				   // truck_service_status
					
					
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}


	
	public static HashMap<String, Object> getTrucksAllotment(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksAllotment(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTrucksDetails(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksDetails(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksPartsQuote(String quote_number) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksPartsQuote(quote_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTrucksHealthHistory(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksHealthHistory(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTrucksService(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksService(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTruckStatusTracking(String selected_date) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTruckStatusTracking(selected_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTruckStatusTrackingList(String selected_date) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTruckStatusTrackingList(selected_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
/*	public static HashMap<String, Object> getTrucksServiceByTruck(String lower_date, String upper_date,int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksServiceByTruck(lower_date, upper_date,fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}*/
	
	public static HashMap<String, Object> getTrucksAllotmentByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksAllotmentByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksHealthHistoryByDate(String lower_date, String upper_date, int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksHealthHistoryByDate(lower_date, upper_date, fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckMaintenanceExpenditure(String lower_date, String upper_date, String fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTruckMaintenanceExpenditure(lower_date, upper_date, fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckMaintenanceExpenditureReport(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTruckMaintenanceExpenditureReport(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksPartsQuoteByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksPartsQuoteByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksMaintenanceCost(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksMaintenanceCost(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksTripCount(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksTripCount(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getTrucksServiceByTruckNumber(String lower_date, String upper_date,int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksServiceByTruckNumber(lower_date, upper_date,fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

public static HashMap<String, Object> getTrucksServiceByDate(String lower_date, String upper_date) {		

	HashMap<String, Object> responseParameters = new HashMap<String, Object>();
	try{
		responseParameters = TrucksDAO.getTrucksServiceByDate(lower_date, upper_date);
	}catch(Exception e){
		e.printStackTrace();
	}
	return responseParameters;
}

	
	/*public static HashMap<String, Object> getTrucksServiceByDate(String lower_date, String upper_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksServiceByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}*/
	
	
	public static HashMap<String, Object> updateTrucksAllotment(
			int trucks_allotment_id,
			String truck_allotment_date,
			int fk_driver_id,
			String allotment_location
	//		int fk_truck_id
					
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.updateTrucksAllotment( 
					trucks_allotment_id,
					truck_allotment_date,
					fk_driver_id, 
					allotment_location 
	//				fk_truck_id
																		
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	
public static HashMap<String, Object> updateTrucksDetails(
			
	        int truck_id,
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
			String rc_image_bytes_string,
			String rc_image_type,
			String insurance_image_bytes_string,
			String insurance_image_type,
			String fitness_image_bytes_string,
			String fitness_image_type,
			String permit_image_bytes_string,
			String permit_image_type
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.updateTrucksDetails(
					truck_id,chassis_number,engine_number,registration_date,permit_number,permit_area,permit_validity,insurance_company_name,
					insurance_policy_number,insurance_date,insurance_expiry_date,fitness_certificate_number,fitness_expire_date,
					rc_image_bytes_string,rc_image_type,insurance_image_bytes_string,insurance_image_type,
					fitness_image_bytes_string,fitness_image_type,permit_image_bytes_string,permit_image_type);
					
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> updateTrucksHealthHistory(
			int trucks_health_history_id,
			String description,
	//		int fk_truck_id,
			String date,
			int fk_spare_parts_id	
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.updateTrucksHealthHistory( 
					trucks_health_history_id,
					 description,
		//			 fk_truck_id,
					 date,
					 fk_spare_parts_id
							);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	
	public static HashMap<String, Object> updateTrucksPartsQuote(
			int trucks_parts_quote_id,
	//		String quote_number ,
			String new_quote_date,
			String product_name,
			String company_name,
			String agent,
			int number_of_units,
			double unit_price ,
			double total,
			int no_of_days_for_delivery,
			int no_of_hours_for_delivery,
			String mode_of_payment,
			String type_of_payment,
			int valid_for_number_of_days,
			String contact_number,
			String quote_status
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.updateTrucksPartsQuote( 
					trucks_parts_quote_id,
		//			quote_number ,
					new_quote_date,
					product_name,
					company_name,
					agent,
					number_of_units,
					unit_price ,
					total,
					no_of_days_for_delivery,
					no_of_hours_for_delivery,
					mode_of_payment,
					type_of_payment,
					valid_for_number_of_days,
					contact_number,
					quote_status
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateTrucksService(
			int trucks_service_id,
			int fk_truck_id ,
			String service_center_name,
			String service_date,
			double service_total_cost,
			int fk_spare_parts_id,
			String description
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.updateTrucksService( 
					trucks_service_id,
					fk_truck_id ,
					service_center_name,
					service_date,
					service_total_cost,
					fk_spare_parts_id,
					description
					
											);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllTrucksDetails() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getAllTrucksDetails();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTrucksDriverChange() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksDriverChange();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTrucksAndOtherTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksAndOtherTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAssignedAvailableTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getAssignedAvailableTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTrucksGrid() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getTrucksGrid();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getAllTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}

	
	public static HashMap<String, Object> getEngagedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getEngagedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getFactoryEngagedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getFactoryEngagedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getStorageEngagedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getStorageEngagedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getSellerEngagedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getSellerEngagedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllAvailableAndEngagedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getAllAvailableAndEngagedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllotedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getAllotedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAssignedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getAssignedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getFactoryDispatchedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getFactoryDispatchedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getFactoryUnloadingTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TrucksDAO.getFactoryUnloadingTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTruckStatus(String truck_number) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTruckStatus(truck_number);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addTruckIdleStatus(int fk_truck_id,String idle_reason) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.addTruckIdleStatus(fk_truck_id,idle_reason);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrucksGridCalendar(String truck_number,
			String month_start_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTrucksGridCalendar(truck_number,month_start_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getExpiryTrucksPermit(String date_type) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getExpiryTrucksPermit(date_type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getExpiryTrucksInsurance(String date_type) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getExpiryTrucksInsurance(date_type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getExpiryTrucksFitness(String date_type) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getExpiryTrucksFitness(date_type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckCurrentDayStatusDetails(String operation,
			int operation_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = TrucksDAO.getTruckCurrentDayStatusDetails(operation,operation_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addTruckMaintenanceExpenditure(String item_cost,String date,String remarks,String  item_name,
			int fk_truck_id,String expenditure_type) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TrucksDAO.addTruckMaintenanceExpenditure(item_cost, date,remarks, item_name, fk_truck_id,expenditure_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
}
