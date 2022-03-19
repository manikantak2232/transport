package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.DriverDAO;
import com.pixelbox.dao.FactoryDAO;
import com.pixelbox.dao.SparePartsDAO;
import com.pixelbox.dao.TyreDAO;

public class TyreBL {
	public static HashMap<String, Object> addVehicleTyreSummaryReport(String date, String vehicle_number,
			String front_or_back_tyres, String tyre_number, String tyre_issued_reading, String tyre_closing_reading,
			String remarks) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.addVehicleTyreSummaryReport(date, vehicle_number, front_or_back_tyres,
					tyre_number, tyre_issued_reading, tyre_closing_reading, remarks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addFrontTotalSummary(String date, String air_filler, String front,
			String tyre_number, String vehicle_number, int fk_driver_id, String closing_date, String reading,
			String remarks) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.addFrontTotalSummary(date, air_filler, front, tyre_number, vehicle_number,
					fk_driver_id, closing_date, reading, remarks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addHosingTotalSummary(String date, String air_filler, String hosing,
			String tyre_number, String vehicle_number, int fk_driver_id, String closing_date, String reading,
			String remarks) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.addHosingTotalSummary(date, air_filler, hosing, tyre_number, vehicle_number,
					fk_driver_id, closing_date, reading, remarks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getVehicleTyreSummaryReport(String vehicle_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.getVehicleTyreSummaryReport(vehicle_number);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	// ----------------------------------------------get by
	// date----------------------------------------------------------------------//
	public static HashMap<String, Object> getVehicleTyreSummaryReportByDate(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = TyreDAO.getVehicleTyreSummaryReportByDate(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> updateVehicleTyreSummaryReport(int vehicle_tyre_summary_report_id,
			String date, String vehicle_number, String front_or_back_tyres, String tyre_number,
			String tyre_issued_reading, String tyre_closing_reading, String remarks) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.updateVehicleTyreSummaryReport(vehicle_tyre_summary_report_id, date,
					vehicle_number, front_or_back_tyres, tyre_number, tyre_issued_reading, tyre_closing_reading,
					remarks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addTyreInventory(String invoice_number, String tyre_type,
			String invoice_date, String brand_name, String tyre_number, double tyre_unit_price,
			double tyre_discount_percent,double tyre_gst_percent,double tube_unit_price,
			double tube_discount_percent, double tube_gst_percent, double flap_unit_price,
			double flap_discount_percent, double flap_gst_percent) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.addTyreInventory(invoice_number, tyre_type, invoice_date,
					brand_name, tyre_number, tyre_unit_price,tyre_discount_percent,tyre_gst_percent,
					tube_unit_price,tube_discount_percent,tube_gst_percent,flap_unit_price,
					flap_discount_percent,flap_gst_percent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllTyres() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TyreDAO.getAllTyres();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllBrandNames() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TyreDAO.getAllBrandNames();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllRunningBrandNames() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TyreDAO.getAllRunningBrandNames();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllPendingBrandNames() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TyreDAO.getAllPendingBrandNames();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllTyresByCategory(String brand_name, String tyre_type,
			String tyre_category) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.getAllTyresByCategory(brand_name, tyre_type,
					tyre_category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllRunningTyres(String brand_name
			) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.getAllRunningTyres(brand_name
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	

	public static HashMap<String, Object> getAllPendingTyres(String brand_name, String tyre_type
			) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.getAllPendingTyres(brand_name, tyre_type
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateRecoupTyreStatus(String tyre_number, String tyre_status
			) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.updateRecoupTyreStatus(tyre_number, tyre_status
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addIssuedTyres(String issued_tyre_number,String issued_brand_name,String issued_tyre_type,String issued_tyre_category,
			String issued_and_returned_date,int fk_truck_id,int fk_driver_id,double issued_tyre_reading,String returned_tyre_number,
			String returned_brand_name,String returned_tyre_category,double returned_tyre_reading,int recoupTyreCost) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.addIssuedTyres(issued_tyre_number, issued_brand_name, issued_tyre_type, issued_tyre_category,
					issued_and_returned_date,fk_truck_id,fk_driver_id, issued_tyre_reading, returned_tyre_number,
					returned_brand_name,returned_tyre_category,returned_tyre_reading,recoupTyreCost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addRecoupTyres(String tyre_number, String brand_name, String tyre_type, double tyre_taken_reading,
			String tyre_taken_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TyreDAO.addRecoupTyres(tyre_number, brand_name, tyre_type, tyre_taken_reading,
					tyre_taken_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getRunningTyres(String truck_number ) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = TyreDAO.getRunningTyres(truck_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getIssuedTyres(String lower_date, String upper_date,String tyre_category, String truck_number ) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = TyreDAO.getIssuedTyres(lower_date, upper_date,tyre_category, truck_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getWasteTyres(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = TyreDAO.getWasteTyres(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getRecoupTyres() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TyreDAO.getRecoupTyres();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTyresInventoryHistory(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = TyreDAO.getTyresInventoryHistory(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTyresCurrentInventory() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TyreDAO.getTyresCurrentInventory();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
}