package com.pixelbox.bl;

import java.util.HashMap;

//import com.pixelbox.dao.ConnectedUsersDAO;
import com.pixelbox.dao.DriverDAO;
import com.pixelbox.dao.FactoryDAO;
import com.pixelbox.dao.TrucksDAO;

public class FactoryBL {
	public static HashMap<String, Object> updateFactoryDispatchInitially(int factory_dispatch_id, String invoice_number,
			String start_location, String unload_location_name,int unload_location_id,String type_of_cement, String dispatch_date, Double advance, double estimated_km,
			double starting_meter_reading, double load_quantity, double freight, 
			String delivery_number,String po_number,String invoice_number2,double load_quantity2, double freight2,String delivery_number2,
			String po_number2,String starting_meter_reading_image_bytes_string,String starting_meter_reading_image_type) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.updateFactoryDispatchInitially(factory_dispatch_id, invoice_number,
					start_location,unload_location_name,unload_location_id,type_of_cement, dispatch_date, advance, estimated_km, starting_meter_reading,
					load_quantity, freight,delivery_number,po_number,invoice_number2,load_quantity2, freight2,delivery_number2,
					po_number2,starting_meter_reading_image_bytes_string,starting_meter_reading_image_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addFactoryFuel(String truck_no,int fk_driver_id, double fuel_quantity, double fuel_rate,
			 int fk_fuel_station_id, String date, double advance,double starting_meter_reading,
			 double closing_meter_reading,String petrol_pump_reading_image_bytes_string,
			 String petrol_pump_reading_image_type,String starting_meter_reading_image_bytes_string,
				String starting_meter_reading_image_type,String dispatch_ids) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.addFactoryFuel(truck_no, fk_driver_id, fuel_quantity, fuel_rate,
					fk_fuel_station_id, date, advance, starting_meter_reading,closing_meter_reading,
					petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,
					starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,dispatch_ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addInvoicePhoto(String petrol_pump_reading_image_bytes_string,
			 String petrol_pump_reading_image_type,int dispatch_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.addInvoicePhoto(
					petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,dispatch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addFuel(String truck_no,int fk_driver_id, double fuel_quantity, double fuel_rate,
			 int fk_fuel_station_id, String date, double advance,double starting_meter_reading,
			 double closing_meter_reading,String petrol_pump_reading_image_bytes_string,
			 String petrol_pump_reading_image_type,String starting_meter_reading_image_bytes_string,
				String starting_meter_reading_image_type,String fuel_type) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.addFuel(truck_no, fk_driver_id, fuel_quantity, fuel_rate,
					fk_fuel_station_id, date, advance, starting_meter_reading,closing_meter_reading,
					petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,
					starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,fuel_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addFactoryInvoice(String invoice_number, String customer_first_name,
			String customer_middle_name, String customer_last_name, String name_of_brand,
			String sales_manager_first_name, String sales_manager_middle_name, String sales_manager_last_name,
			String invoice_date, String phone_number, String email, String from_address, String to_address,
			String type_of_cement, int number_of_bags, String total_weight, double cost_per_bag, double freight_rate,
			double total_value) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.addFactoryInvoice(invoice_number, customer_first_name, customer_middle_name,
					customer_last_name, name_of_brand, sales_manager_first_name, sales_manager_middle_name,
					sales_manager_last_name, invoice_date, phone_number, email, from_address, to_address,
					type_of_cement, number_of_bags, total_weight, cost_per_bag, freight_rate, total_value

			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	// ------------------------------------get---------------------------------------------------------------------------------//
	public static HashMap<String, Object> getFactoryDispatch(String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getFactoryDispatch(invoice_number);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryFuel(int factory_fuel_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getFactoryFuel(factory_fuel_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryChange(int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getFactoryChange(truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryInvoice(String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getFactoryInvoice(invoice_number);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryDispatchByDate(String lower_date, String upper_date, String fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDispatchByDate(lower_date, upper_date, fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchFuelByDate(String lower_date, String upper_date, String fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDispatchFuelByDate(lower_date, upper_date, fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchById(int factory_dispatch_id, String company_name) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDispatchById(factory_dispatch_id, company_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getUnloadReportById(int factory_dispatch_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getUnloadReportById(factory_dispatch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> totalDispatchReport(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.totalDispatchReport(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchDailyReport(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDispatchDailyReport(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchLoadingReport(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDispatchLoadingReport(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoriesCumulativeReport(String lower_date, String upper_date, String association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoriesCumulativeReport(lower_date, upper_date, association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getDispatchReportForBill(String lower_date, String upper_date, String association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getDispatchReportForBill(lower_date, upper_date, association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getDispatchFuelLinking() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getDispatchFuelLinking();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateSvtcBill(String dispatch_ids) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.updateSvtcBill(dispatch_ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getGeneratedBills(int association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getGeneratedBills(association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getGeneratedBillsGroup(int association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getGeneratedBillsGroup(association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchBill(String lower_date, String upper_date, int association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDispatchBill(lower_date, upper_date, association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchBillCount(String lower_date, String upper_date, int association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDispatchBillCount(lower_date, upper_date, association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getQuantityAndFreigt(String lower_date, String upper_date, String company_name) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getQuantityAndFreigt(lower_date, upper_date, company_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryFuelByDate(String lower_date, String upper_date, String truck_no) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryFuelByDate(lower_date, upper_date, truck_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryInvoiceByDate(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryInvoiceByDate(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	// ---------------------------------------------------update--------------------------------------------------------//
	public static HashMap<String, Object> updateFactoryDispatch(int factory_dispatch_id,String invoice_number,
			String loading_date,String invoice_date,String unload_location_name,String type_of_cement,double load_quantity,
			double freight,int unload_location_id,String invoice_number2,double freight2,double load_quantity2) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.updateFactoryDispatch(factory_dispatch_id, invoice_number, loading_date,
					invoice_date, unload_location_name, type_of_cement, load_quantity, freight,unload_location_id,
					invoice_number2,freight2,load_quantity2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateUnloadReport(int factory_dispatch_id, double starting_meter_reading, 
			double closing_meter_reading,String	unload_report_locations, int checked_kms) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.updateUnloadReport(factory_dispatch_id, starting_meter_reading, closing_meter_reading,
					unload_report_locations, checked_kms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateOutsideFactoryDispatch(int factory_dispatch_id,String invoice_number,String truck_number,
			String driver_name,String invoice_date, String unload_location_name,String type_of_cement,double load_quantity,double freight,
			int unload_location_id,int outside_company_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.updateOutsideFactoryDispatch(factory_dispatch_id, invoice_number, truck_number,
					driver_name,invoice_date, unload_location_name, type_of_cement, load_quantity, freight,unload_location_id,
					outside_company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> updateFactoryFuel(int factory_fuel_id, String truck_no,int fk_driver_id,double fuel_quantity,double fuel_rate,
			String date,String starting_meter_reading,String closing_meter_reading,int fk_fuel_station_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.updateFactoryFuel(factory_fuel_id, truck_no,fk_driver_id, fuel_quantity, fuel_rate,
					date,starting_meter_reading,closing_meter_reading,fk_fuel_station_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> updateFactoryInvoice(int factory_invoice_id, String invoice_number,
			String customer_first_name, String customer_middle_name, String customer_last_name, String name_of_brand,
			String sales_manager_first_name, String sales_manager_middle_name, String sales_manager_last_name,
			String invoice_date, String phone_number, String email, String from_address, String to_address,
			String type_of_cement, int number_of_bags, String total_weight, double cost_per_bag, double freight_rate,
			double total_value, String approve_status) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.updateFactoryInvoice(factory_invoice_id, invoice_number,
					customer_first_name, customer_middle_name, customer_last_name, name_of_brand,
					sales_manager_first_name, sales_manager_middle_name, sales_manager_last_name, invoice_date,
					phone_number, email, from_address, to_address, type_of_cement, number_of_bags, total_weight,
					cost_per_bag, freight_rate, total_value, approve_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getFactoryDispatchStatus(int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getFactoryDispatchStatus(truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateFactoryDispatchStatus(int factory_dispatch_id, String dispatch_status,
			String waiting_location,String unload_report_locations) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.updateFactoryDispatchStatus(factory_dispatch_id, dispatch_status,
					waiting_location,unload_report_locations);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getCloseDispatch(int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getCloseDispatch(truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseDispatch(int factory_dispatch_id,
			  double closing_meter_reading, double checked_kms,String closing_meter_reading_image_bytes_string,
			  String closing_meter_reading_image_type) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.updateCloseDispatch(factory_dispatch_id, 
					 closing_meter_reading, checked_kms,closing_meter_reading_image_bytes_string,closing_meter_reading_image_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addFactoryDriverChange(int fk_factory_dispatch_id, int fk_driver_id,
			String location, double advance) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.addFactoryDriverChange(fk_factory_dispatch_id, fk_driver_id, location, advance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAssociation() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = FactoryDAO.getAssociation();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getFactoriesAssociation() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = FactoryDAO.getFactoriesAssociation();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getFactoryDriverExpenditure(int fk_driver_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDriverExpenditure(fk_driver_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDriversCloseExpenditure(int factory_dispatch_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryDriversCloseExpenditure(factory_dispatch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addDriverExpenditure(int factory_dispatch_id,int fk_driver_id,
		 int transport, int loading,int cover_tying, int contonment, int toll_gate, int loading_wage, int unloading_wage, 
		 int phone_bills,int spares_parts, int puncher, int entry, int return_transport, int return_loading, int return_unloading,
			int others, int balance) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.addDriverExpenditure(factory_dispatch_id, fk_driver_id, transport, loading, cover_tying, contonment,
					toll_gate, loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry,
					return_transport, return_loading, return_unloading, others, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateDriverExpenditureApproval(int factory_dispatch_id,int fk_driver_id,
			 int transport, int loading,int cover_tying, int contonment, int toll_gate, int loading_wage, int unloading_wage, 
			 int phone_bills,int spares_parts, int puncher, int entry, int return_transport, int return_loading, int return_unloading,
				int others) {

			HashMap<String, Object> responseParameters = new HashMap<String, Object>();

			try {
				responseParameters = FactoryDAO.updateDriverExpenditureApproval(factory_dispatch_id, fk_driver_id, transport, loading, cover_tying, contonment,
						toll_gate, loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry,
						return_transport, return_loading, return_unloading, others);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseParameters;
		}
	
	public static HashMap<String, Object> addDriverAdvance(int fk_truck_id,int fk_driver_id,double advance,String date) {

			HashMap<String, Object> responseParameters = new HashMap<String, Object>();

			try {
				responseParameters = FactoryDAO.addDriverAdvance(fk_truck_id,fk_driver_id,advance,date);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseParameters;
		}
	
	public static HashMap<String, Object> addFactoryLoading(int fk_truck_id, int fk_driver_id, int fk_association_id,
			String dispatch_status) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.addFactoryLoading(fk_truck_id, fk_driver_id, fk_association_id,
					dispatch_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFuelStationName() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = FactoryDAO.getFuelStationName();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getUnloadReport() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = FactoryDAO.getUnloadReport();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getUnloadLocationNames(int association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getUnloadLocationNames(association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getOutsideCompanyNames(int association_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getOutsideCompanyNames(association_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryDispatchForInvoicePhoto() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getFactoryDispatchForInvoicePhoto();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getDriverExpenditureForApproval() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.getDriverExpenditureForApproval();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addOutsideFactoryDispatch(String truck_number,String driver_name,String driver_phone_number, 
			String invoice_number, String start_location,String unload_location_name, int unload_location_id,String type_of_cement, String dispatch_date,
			double load_quantity, double freight, int fk_association_id, int outside_company_id,
			String delivery_number,String po_number,String invoice_number2,double load_quantity2,double freight2,
			String delivery_number2, String po_number2) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = FactoryDAO.addOutsideFactoryDispatch(truck_number,driver_name,driver_phone_number, invoice_number, start_location,
					unload_location_name,unload_location_id,type_of_cement, dispatch_date, load_quantity, freight,fk_association_id,
					outside_company_id,delivery_number,po_number,invoice_number2,load_quantity2, freight2,delivery_number2,
					po_number2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFactoryFuelByFuelStation(String lower_date, String upper_date, String fuel_station_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFactoryFuelByFuelStation(lower_date, upper_date, fuel_station_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> deleteFactoryDispatch(int factory_dispatch_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.deleteFactoryDispatch(factory_dispatch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFreightAndQuantity(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFreightAndQuantity(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFuelCostReport(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFuelCostReport(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFuelStationCostReport(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFuelStationCostReport(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getFreightAndQuantityMonth(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = FactoryDAO.getFreightAndQuantityMonth(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
}