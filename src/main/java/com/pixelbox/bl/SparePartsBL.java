package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.DriverDAO;
import com.pixelbox.dao.SparePartsDAO;
import com.pixelbox.dao.StorageDAO;
import com.pixelbox.dao.TrucksDAO;
import com.pixelbox.dao.TyreDAO;

public class SparePartsBL {
	public static HashMap<String, Object> addSparePartAllotment(int fk_driver_id, int fk_spare_part_id, int fk_truck_id,
			// int count,
			String date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.addSparePartAllotment(fk_driver_id, fk_spare_part_id, fk_truck_id,
					// count,
					date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addSparePartAllotment1(int fk_driver_id, String fk_spare_part_id, int fk_truck_id,
			// int count,
			String date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.addSparePartAllotment1(fk_driver_id, fk_spare_part_id, fk_truck_id,
					// count,
					date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getSparePartAllotment(int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getSparePartAllotment(fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> getSparePartAllotmentByDate(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getSparePartAllotmentByDate(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> updateSparePartAllotment(int spare_part_allotment_id, int fk_driver_id,
			int fk_spare_part_id,
			// String truck_number,
			int count, String date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.updateSparePartAllotment(spare_part_allotment_id, fk_driver_id,
					fk_spare_part_id,
					// truck_number,
					count, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getAllSpareParts() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getAllSpareParts();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> addSparePartInventory(int fk_spare_part_id, int count,
			String invoice_number,double unit_price, double discount,double gst,double total) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.addSparePartInventory(fk_spare_part_id, count, invoice_number,
					unit_price,discount,gst,total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addOilBrandName(String brand_name) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.addOilBrandName(brand_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addBatteryBrandName(String brand_name) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.addBatteryBrandName(brand_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getSparePartInventoryHistory(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getSparePartInventoryHistory(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getSparePartCurrentInventory() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getSparePartCurrentInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> getOilBrandNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getOilBrandNames();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getBatteryBrandNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getBatteryBrandNames();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getInventoryBatteryBrands() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getInventoryBatteryBrands();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getRunningBatteryBrands() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getRunningBatteryBrands();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getInventoryBatteryNumber(int fk_battery_brand_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getInventoryBatteryNumber(fk_battery_brand_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getRunningBatteryNumber(int fk_battery_brand_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getRunningBatteryNumber(fk_battery_brand_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> getOilDistinceBrandNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getOilDistinceBrandNames();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> addOilInventory(String date, int fk_oil_brand_id, String invoice_number,
			Double cost, Double liters, String invoice_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.addOilInventory(date, fk_oil_brand_id, invoice_number, cost,
					liters, invoice_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addIssuedOil(int fk_truck_id,String issued_date, int fk_oil_brand_id,
			Double liters,int fk_driver_id,Double reading) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.addIssuedOil(fk_truck_id,issued_date, fk_oil_brand_id,
					liters,fk_driver_id,reading);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getOilInventoryHistory(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getOilInventoryHistory(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getOilCurrentInventory() {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getOilCurrentInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getIssuedOil(String lower_date, String upper_date, String truck_number ) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getIssuedOil(lower_date, upper_date, truck_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addBatteryInventory(String invoice_number, 
			String invoice_date, String date,int fk_battery_brand_id, String battery_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.addBatteryInventory(invoice_number,  invoice_date,date,
					fk_battery_brand_id, battery_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addIssuedBattery(String issued_battery_number, int fk_issued_battery_id, 
			String issued_and_returned_date,int fk_truck_id,int fk_driver_id, String returned_battery_number,int fk_returned_brand_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.addIssuedBattery(issued_battery_number, fk_issued_battery_id, 
					issued_and_returned_date,fk_truck_id,fk_driver_id, returned_battery_number, fk_returned_brand_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getBatteryInventoryHistory(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getBatteryInventoryHistory(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getIssuedBattery(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getIssuedBattery(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getWasteBattery(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getWasteBattery(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getBatteryCurrentInventory() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getBatteryCurrentInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getRunningBattery() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getRunningBattery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> addTruckMaintenanceAdvanceRequest(String item_cost,String date,String item_name,
			int fk_truck_id, int request_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.addTruckMaintenanceAdvanceRequest(item_cost, date, item_name, fk_truck_id,
					request_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckMaintenanceAdvanceRequestNumber(int fk_truck_id,String date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getTruckMaintenanceAdvanceRequestNumber(fk_truck_id, date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getTruckMaintenanceAdvanceItemDetails(String truck_maintenance_advance_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getTruckMaintenanceAdvanceItemDetails(truck_maintenance_advance_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getTruckMaintenanceAdvanceRequestCount() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.getTruckMaintenanceAdvanceRequestCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	public static HashMap<String, Object> updateTruckMaintenanceAdvanceApprovalStatus(String truck_maintenance_advance_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = SparePartsDAO.updateTruckMaintenanceAdvanceApprovalStatus(truck_maintenance_advance_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getOverallTruckMaintenanceReport(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = SparePartsDAO.getOverallTruckMaintenanceReport(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

}