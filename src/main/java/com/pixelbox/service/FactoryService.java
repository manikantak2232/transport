package com.pixelbox.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

//import com.pixelbox.bl.ConnectedUsersBL;
import com.pixelbox.bl.DriverBL;
import com.pixelbox.bl.FactoryBL;
import com.pixelbox.bl.TrucksBL;
import com.pixelbox.dao.TrucksDAO;

@Path("/factory")
public class FactoryService {
	final static Logger log = Logger.getLogger(DriverService.class);

	@POST
	@Path("/dispatch/update/intially")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateFactoryDispatchInitially(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		String start_location = requestParameters.get("start_location").toString();
		String unload_location_name = requestParameters.get("unload_location_name").toString();
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		int unload_location_id = Integer.parseInt(requestParameters.get("unload_location_id").toString());
		String dispatch_date = requestParameters.get("dispatch_date").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		double estimated_km = Double.parseDouble(requestParameters.get("estimated_km").toString());
		double starting_meter_reading = Double.parseDouble(requestParameters.get("starting_meter_reading").toString());
		double load_quantity = Double.parseDouble(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		String delivery_number = requestParameters.get("delivery_number").toString();
		String po_number = requestParameters.get("po_number").toString();
		String invoice_number2 = requestParameters.get("invoice_number2").toString();
		String delivery_number2 = requestParameters.get("delivery_number2").toString();
		String po_number2 = requestParameters.get("po_number2").toString();
		double load_quantity2 = Double.parseDouble(requestParameters.get("load_quantity2").toString());
		double freight2 = Double.parseDouble(requestParameters.get("freight2").toString());
		
		String starting_meter_reading_image_bytes_string=requestParameters.get("starting_meter_reading_image_bytes_string").toString();
		String starting_meter_reading_image_type =requestParameters.get("starting_meter_reading_image_type").toString();

		responseParameters = FactoryBL.updateFactoryDispatchInitially(factory_dispatch_id, invoice_number, start_location,
				unload_location_name,unload_location_id,type_of_cement, dispatch_date, advance, estimated_km, starting_meter_reading,
				load_quantity, freight,delivery_number,po_number,invoice_number2,load_quantity2, freight2,delivery_number2,
				po_number2,starting_meter_reading_image_bytes_string,starting_meter_reading_image_type);

		return responseParameters;
	}

	@POST
	@Path("/fuel/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addFactoryFuel(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String truck_no = requestParameters.get("truck_no").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		double fuel_quantity = Double.parseDouble(requestParameters.get("fuel_quantity").toString());
		double fuel_rate = Double.parseDouble(requestParameters.get("fuel_rate").toString());
	//	double amount = Double.parseDouble(requestParameters.get("amount").toString());
		int fk_fuel_station_id = Integer.parseInt(requestParameters.get("fk_fuel_station_id").toString());
		String date = requestParameters.get("date").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		double starting_meter_reading = Double.parseDouble(requestParameters.get("starting_meter_reading").toString());
		double closing_meter_reading = Double.parseDouble(requestParameters.get("closing_meter_reading").toString());
	//	double mileage = Double.parseDouble(requestParameters.get("mileage").toString());
		
		String petrol_pump_reading_image_bytes_string=requestParameters.get("petrol_pump_reading_image_bytes_string").toString();
		String petrol_pump_reading_image_type =requestParameters.get("petrol_pump_reading_image_type").toString();
		String starting_meter_reading_image_bytes_string=requestParameters.get("starting_meter_reading_image_bytes_string").toString();
		String starting_meter_reading_image_type =requestParameters.get("starting_meter_reading_image_type").toString();
		String dispatch_ids =requestParameters.get("dispatch_ids").toString();


		responseParameters = FactoryBL.addFactoryFuel(truck_no,fk_driver_id, fuel_quantity, fuel_rate,
				fk_fuel_station_id, date,advance,starting_meter_reading,closing_meter_reading,
				petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,starting_meter_reading_image_bytes_string,
				starting_meter_reading_image_type,dispatch_ids);

		return responseParameters;
	}
	
	@POST
	@Path("/invoice/photo/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addInvoicePhoto(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String petrol_pump_reading_image_bytes_string=requestParameters.get("petrol_pump_reading_image_bytes_string").toString();
		String petrol_pump_reading_image_type =requestParameters.get("petrol_pump_reading_image_type").toString();
		int dispatch_id =Integer.parseInt(requestParameters.get("dispatch_ids").toString());


		responseParameters = FactoryBL.addInvoicePhoto(petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,dispatch_id);

		return responseParameters;
	}
	
	@POST
	@Path("/fuel/error/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addFuel(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String truck_no = requestParameters.get("truck_no").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		double fuel_quantity = Double.parseDouble(requestParameters.get("fuel_quantity").toString());
		double fuel_rate = Double.parseDouble(requestParameters.get("fuel_rate").toString());
	//	double amount = Double.parseDouble(requestParameters.get("amount").toString());
		int fk_fuel_station_id = Integer.parseInt(requestParameters.get("fk_fuel_station_id").toString());
		String date = requestParameters.get("date").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		double starting_meter_reading = Double.parseDouble(requestParameters.get("starting_meter_reading").toString());
		double closing_meter_reading = Double.parseDouble(requestParameters.get("closing_meter_reading").toString());
	//	double mileage = Double.parseDouble(requestParameters.get("mileage").toString());
		
		String petrol_pump_reading_image_bytes_string=requestParameters.get("petrol_pump_reading_image_bytes_string").toString();
		String petrol_pump_reading_image_type =requestParameters.get("petrol_pump_reading_image_type").toString();
		String starting_meter_reading_image_bytes_string=requestParameters.get("starting_meter_reading_image_bytes_string").toString();
		String starting_meter_reading_image_type =requestParameters.get("starting_meter_reading_image_type").toString();
		String fuel_type =requestParameters.get("fuel_type").toString();


		responseParameters = FactoryBL.addFuel(truck_no,fk_driver_id, fuel_quantity, fuel_rate,
				fk_fuel_station_id, date,advance,starting_meter_reading,closing_meter_reading,
				petrol_pump_reading_image_bytes_string,petrol_pump_reading_image_type,starting_meter_reading_image_bytes_string,
				starting_meter_reading_image_type,fuel_type);

		return responseParameters;
	}

	@POST
	@Path("/invoice/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addFactoryInvoice(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String customer_first_name = requestParameters.get("customer_first_name").toString();
		String customer_middle_name = requestParameters.get("customer_middle_name").toString();
		String customer_last_name = requestParameters.get("customer_last_name").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		String sales_manager_first_name = requestParameters.get("sales_manager_first_name").toString();
		String sales_manager_middle_name = requestParameters.get("sales_manager_middle_name").toString();
		String sales_manager_last_name = requestParameters.get("sales_manager_last_name").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String phone_number = requestParameters.get("phone_number").toString();
		String email = requestParameters.get("email").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		String total_weight = requestParameters.get("total_weight").toString();
		Double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		Double freight_rate = Double.parseDouble(requestParameters.get("freight_rate").toString());
		Double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		// String approve_status =
		// requestParameters.get("approve_status").toString();

		responseParameters = FactoryBL.addFactoryInvoice(invoice_number, customer_first_name, customer_middle_name,
				customer_last_name, name_of_brand, sales_manager_first_name, sales_manager_middle_name,
				sales_manager_last_name, invoice_date, phone_number, email, from_address, to_address, type_of_cement,
				number_of_bags, total_weight, cost_per_bag, freight_rate, total_value);

		return responseParameters;
	}

	// -----------------------------------get-------------------------------------------------------------------------//
	@GET
	@Path("/dispatch/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatch(@QueryParam("invoice_number") String invoice_number) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getFactoryDispatch(invoice_number);

		return responseParameters;
	}

	@GET
	@Path("/fuel/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryFuel(@QueryParam("factory_fuel_id") int factory_fuel_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getFactoryFuel(factory_fuel_id);

		return responseParameters;
	}

	@GET
	@Path("/invoice/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryInvoice(@QueryParam("invoice_number") String invoice_number) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getFactoryInvoice(invoice_number);

		return responseParameters;
	}

	@POST
	@Path("/dispatch/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String fk_truck_id = requestParameters.get("fk_truck_id").toString();

		responseParameters = FactoryBL.getFactoryDispatchByDate(lower_date, upper_date,fk_truck_id);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/fuel/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchFuelByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String fk_truck_id = requestParameters.get("fk_truck_id").toString();

		responseParameters = FactoryBL.getFactoryDispatchFuelByDate(lower_date, upper_date,fk_truck_id);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/by/id")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchById(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		String company_name = requestParameters.get("company_name").toString();

		responseParameters = FactoryBL.getFactoryDispatchById(factory_dispatch_id, company_name);

		return responseParameters;
	}
	
	@POST
	@Path("/unload/report/by/id")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUnloadReportById(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());

		responseParameters = FactoryBL.getUnloadReportById(factory_dispatch_id);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/report/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> totalDispatchReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = FactoryBL.totalDispatchReport(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/report/daily/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchDailyReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = FactoryBL.getFactoryDispatchDailyReport(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/report/loading/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchLoadingReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = FactoryBL.getFactoryDispatchLoadingReport(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/cumulative/report/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoriesCumulativeReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String association_id =requestParameters.get("association_id").toString();

		responseParameters = FactoryBL.getFactoriesCumulativeReport(lower_date, upper_date,association_id);

		return responseParameters;
	}
	
	@POST
	@Path("/dipatch/report/bill/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDispatchReportForBill(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String association_id =requestParameters.get("association_id").toString();

		responseParameters = FactoryBL.getDispatchReportForBill(lower_date, upper_date,association_id);

		return responseParameters;
	}
	
	@GET
	@Path("/dipatch/fuel/link/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDispatchFuelLinking() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getDispatchFuelLinking();

		return responseParameters;
	}
	
	@POST
	@Path("/dipatch/bill/ids/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateSvtcBill(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String dispatch_ids =requestParameters.get("dispatch_ids").toString();

		responseParameters = FactoryBL.updateSvtcBill(dispatch_ids);

		return responseParameters;
	}
	
	@POST
	@Path("/generated/bill/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getGeneratedBills(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int association_id =Integer.parseInt(requestParameters.get("association_id").toString());

		responseParameters = FactoryBL.getGeneratedBills(association_id);

		return responseParameters;
	}
	
	@POST
	@Path("/generated/bill/group/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getGeneratedBillsGroup(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int association_id =Integer.parseInt(requestParameters.get("association_id").toString());

		responseParameters = FactoryBL.getGeneratedBillsGroup(association_id);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/bill/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchBill(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int association_id = Integer.parseInt(requestParameters.get("association_id").toString());

		responseParameters = FactoryBL.getFactoryDispatchBill(lower_date, upper_date,association_id);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/billcount/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchBillCount(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int association_id = Integer.parseInt(requestParameters.get("association_id").toString());

		responseParameters = FactoryBL.getFactoryDispatchBillCount(lower_date, upper_date,association_id);

		return responseParameters;
	}
	
	@POST
	@Path("/quantity/freight/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getQuantityAndFreigt(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String company_name = requestParameters.get("company_name").toString();

		responseParameters = FactoryBL.getQuantityAndFreigt(lower_date, upper_date,company_name);

		return responseParameters;
	}

	@POST
	@Path("/fuel/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryFuelByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String truck_no = requestParameters.get("truck_no").toString();
		responseParameters = FactoryBL.getFactoryFuelByDate(lower_date, upper_date, truck_no);

		return responseParameters;
	}

	@POST
	@Path("/invoice/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryInvoiceByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = FactoryBL.getFactoryInvoiceByDate(lower_date, upper_date);

		return responseParameters;
	}

	// -----------------------------------------------------update-----------------------------------------------------//
	@POST
	@Path("/dispatch/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateFactoryDispatch(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		String invoice_number2 = requestParameters.get("invoice_number2").toString();
		String loading_date = requestParameters.get("loading_date").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String unload_location_name = requestParameters.get("unload_location_name").toString();
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		double load_quantity = Double.parseDouble(requestParameters.get("load_quantity").toString());
		double load_quantity2 = Double.parseDouble(requestParameters.get("load_quantity2").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		double freight2 = Double.parseDouble(requestParameters.get("freight2").toString());
		int unload_location_id = Integer.parseInt(requestParameters.get("unload_location_id").toString());

		responseParameters = FactoryBL.updateFactoryDispatch(factory_dispatch_id, invoice_number, loading_date,
				invoice_date, unload_location_name, type_of_cement, load_quantity, freight,unload_location_id,
				invoice_number2,freight2,load_quantity2);

		return responseParameters;
	}
	
	@POST
	@Path("/unload/report/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateUnloadReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		double starting_meter_reading = Double.parseDouble(requestParameters.get("starting_meter_reading").toString());
		double closing_meter_reading = Double.parseDouble(requestParameters.get("closing_meter_reading").toString());
		String unload_report_locations = requestParameters.get("unload_report_locations").toString();
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
		
		responseParameters = FactoryBL.updateUnloadReport(factory_dispatch_id, starting_meter_reading, closing_meter_reading,
				unload_report_locations, checked_kms);

		return responseParameters;
	}
	
	@POST
	@Path("/outside/dispatch/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateOutsideFactoryDispatch(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		String truck_number = requestParameters.get("truck_number").toString();
		String driver_name = requestParameters.get("driver_name").toString();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String unload_location_name = requestParameters.get("unload_location_name").toString();
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		double load_quantity = Double.parseDouble(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		int unload_location_id = Integer.parseInt(requestParameters.get("unload_location_id").toString());
		int outside_company_id = Integer.parseInt(requestParameters.get("outside_company_id").toString());

		responseParameters = FactoryBL.updateOutsideFactoryDispatch(factory_dispatch_id, invoice_number, truck_number,
				driver_name,invoice_date, unload_location_name, type_of_cement, load_quantity, freight,unload_location_id,
				outside_company_id);

		return responseParameters;
	}

	@POST
	@Path("/fuel/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateFactoryFuel(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int factory_fuel_id = Integer.parseInt(requestParameters.get("factory_fuel_id").toString());
		String truck_no =requestParameters.get("truck_no").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		double fuel_quantity = Double.parseDouble(requestParameters.get("fuel_quantity").toString());
		double fuel_rate = Double.parseDouble(requestParameters.get("fuel_rate").toString());
		int fk_fuel_station_id = Integer.parseInt(requestParameters.get("fk_fuel_station_id").toString());
		String starting_meter_reading = requestParameters.get("starting_meter_reading").toString();
		String closing_meter_reading = requestParameters.get("closing_meter_reading").toString();
		String date = requestParameters.get("date").toString();

		responseParameters = FactoryBL.updateFactoryFuel(factory_fuel_id, truck_no,fk_driver_id, fuel_quantity, fuel_rate,
				date,starting_meter_reading,closing_meter_reading,fk_fuel_station_id);

		return responseParameters;
	}

	@POST
	@Path("/invoice/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateFactoryInvoice(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int factory_invoice_id = Integer.parseInt(requestParameters.get("factory_invoice_id").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		String customer_first_name = requestParameters.get("customer_first_name").toString();
		String customer_middle_name = requestParameters.get("customer_middle_name").toString();
		String customer_last_name = requestParameters.get("customer_last_name").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		String sales_manager_first_name = requestParameters.get("sales_manager_first_name").toString();
		String sales_manager_middle_name = requestParameters.get("sales_manager_middle_name").toString();
		String sales_manager_last_name = requestParameters.get("sales_manager_last_name").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String phone_number = requestParameters.get("phone_number").toString();
		String email = requestParameters.get("email").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		String total_weight = requestParameters.get("total_weight").toString();
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double freight_rate = Double.parseDouble(requestParameters.get("freight_rate").toString());
		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		String approve_status = requestParameters.get("approve_status").toString();

		responseParameters = FactoryBL.updateFactoryInvoice(factory_invoice_id, invoice_number, customer_first_name,
				customer_middle_name, customer_last_name, name_of_brand, sales_manager_first_name,
				sales_manager_middle_name, sales_manager_last_name, invoice_date, phone_number, email, from_address,
				to_address, type_of_cement, number_of_bags, total_weight, cost_per_bag, freight_rate, total_value,
				approve_status);

		return responseParameters;
	}

	// -------------------------------------------------------status---------------------------------------------------------//
	@GET
	@Path("/dispatch/status/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchStatus(@QueryParam("truck_id") int truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getFactoryDispatchStatus(truck_id);

		return responseParameters;
	}

	@POST
	@Path("/status/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateFactoryDispatchStatus(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		String dispatch_status = requestParameters.get("dispatch_status").toString();
		String waiting_location = requestParameters.get("waiting_location").toString();
		String unload_report_locations = requestParameters.get("unload_report_locations").toString();
		responseParameters = FactoryBL.updateFactoryDispatchStatus(factory_dispatch_id, dispatch_status,
				waiting_location,unload_report_locations);

		return responseParameters;
	}

	@GET
	@Path("/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCloseDispatch(@QueryParam("truck_id") int truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getCloseDispatch(truck_id);

		return responseParameters;
	}

	@POST
	@Path("/close/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateCloseDispatch(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());	
		double closing_meter_reading = Double.parseDouble(requestParameters.get("closing_meter_reading").toString());
		double checked_kms = Double.parseDouble(requestParameters.get("checked_kms").toString());
		
		String closing_meter_reading_image_bytes_string=requestParameters.get("closing_meter_reading_image_bytes_string").toString();
		String closing_meter_reading_image_type =requestParameters.get("closing_meter_reading_image_type").toString();
	
		responseParameters = FactoryBL.updateCloseDispatch(factory_dispatch_id,
				closing_meter_reading, checked_kms,closing_meter_reading_image_bytes_string,
				closing_meter_reading_image_type);

		return responseParameters;
	}

	@POST
	@Path("/driverchange/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addFactoryDriverChange(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_factory_dispatch_id = Integer.parseInt(requestParameters.get("fk_factory_dispatch_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String location = requestParameters.get("location").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		responseParameters = FactoryBL.addFactoryDriverChange(fk_factory_dispatch_id, fk_driver_id, location, advance);

		return responseParameters;
	}

	@GET
	@Path("/change/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryChange(@QueryParam("truck_id") int truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getFactoryChange(truck_id);

		return responseParameters;
	}
	
	@GET
	@Path("/association/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAssociation() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = FactoryBL.getAssociation();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/factoriesAssociation/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoriesAssociation() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = FactoryBL.getFactoriesAssociation();
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/expenditure/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
	//	String date = requestParameters.get("date").toString();

		responseParameters = FactoryBL.getFactoryDriverExpenditure(fk_driver_id);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDriversCloseExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());

		responseParameters = FactoryBL.getFactoryDriversCloseExpenditure(factory_dispatch_id);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		
		int transport = Integer.parseInt(requestParameters.get("transport").toString());
		int loading = Integer.parseInt(requestParameters.get("loading").toString());
		int cover_tying = Integer.parseInt(requestParameters.get("cover_tying").toString());
		int contonment = Integer.parseInt(requestParameters.get("contonment").toString());
		int toll_gate = Integer.parseInt(requestParameters.get("toll_gate").toString());
		int loading_wage = Integer.parseInt(requestParameters.get("loading_wage").toString());
		int unloading_wage = Integer.parseInt(requestParameters.get("unloading_wage").toString());
		int phone_bills = Integer.parseInt(requestParameters.get("phone_bills").toString());
		int spares_parts = Integer.parseInt(requestParameters.get("spares_parts").toString());
		int puncher = Integer.parseInt(requestParameters.get("puncher").toString());
		int entry = Integer.parseInt(requestParameters.get("entry").toString());
		int return_transport = Integer.parseInt(requestParameters.get("return_transport").toString());
		int return_loading = Integer.parseInt(requestParameters.get("return_loading").toString());
		int return_unloading = Integer.parseInt(requestParameters.get("return_unloading").toString());
		int others = Integer.parseInt(requestParameters.get("others").toString());
		int balance = Integer.parseInt(requestParameters.get("balance").toString());

		responseParameters = FactoryBL.addDriverExpenditure(factory_dispatch_id,fk_driver_id, transport, loading, cover_tying, contonment, toll_gate,
				loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry, return_transport,
				return_loading, return_unloading, others, balance);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/approval/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateDriverExpenditureApproval(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int factory_dispatch_id = Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		
		int transport = Integer.parseInt(requestParameters.get("transport").toString());
		int loading = Integer.parseInt(requestParameters.get("loading").toString());
		int cover_tying = Integer.parseInt(requestParameters.get("cover_tying").toString());
		int contonment = Integer.parseInt(requestParameters.get("contonment").toString());
		int toll_gate = Integer.parseInt(requestParameters.get("toll_gate").toString());
		int loading_wage = Integer.parseInt(requestParameters.get("loading_wage").toString());
		int unloading_wage = Integer.parseInt(requestParameters.get("unloading_wage").toString());
		int phone_bills = Integer.parseInt(requestParameters.get("phone_bills").toString());
		int spares_parts = Integer.parseInt(requestParameters.get("spares_parts").toString());
		int puncher = Integer.parseInt(requestParameters.get("puncher").toString());
		int entry = Integer.parseInt(requestParameters.get("entry").toString());
		int return_transport = Integer.parseInt(requestParameters.get("return_transport").toString());
		int return_loading = Integer.parseInt(requestParameters.get("return_loading").toString());
		int return_unloading = Integer.parseInt(requestParameters.get("return_unloading").toString());
		int others = Integer.parseInt(requestParameters.get("others").toString());

		responseParameters = FactoryBL.updateDriverExpenditureApproval(factory_dispatch_id,fk_driver_id, transport, loading, cover_tying, contonment, toll_gate,
				loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry, return_transport,
				return_loading, return_unloading, others);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/advance/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addDriverAdvance(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		String date = requestParameters.get("date").toString();

		responseParameters = FactoryBL.addDriverAdvance(fk_truck_id,fk_driver_id, advance, date);

		return responseParameters;
	}
	
	@POST
	@Path("/loading/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addFactoryLoading(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		int fk_association_id = Integer.parseInt(requestParameters.get("fk_association_id").toString());
		String dispatch_status = requestParameters.get("dispatch_status").toString();

		responseParameters = FactoryBL.addFactoryLoading(fk_truck_id, fk_driver_id, fk_association_id,
				dispatch_status);

		return responseParameters;
	}
	
	@GET
	@Path("/fuel/station/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFuelStationName() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = FactoryBL.getFuelStationName();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/dispatch/unload/report/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUnloadReport() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = FactoryBL.getUnloadReport();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/unload/locations/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUnloadLocationNames(@QueryParam("association_id") int association_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getUnloadLocationNames(association_id);

		return responseParameters;
	}
	
	@GET
	@Path("/outside/company/names/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOutsideCompanyNames(@QueryParam("association_id") int association_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getOutsideCompanyNames(association_id);

		return responseParameters;
	}
	
	@GET
	@Path("/dispatch/invoice/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchForInvoicePhoto() {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getFactoryDispatchForInvoicePhoto();

		return responseParameters;
	}
	
	@GET
	@Path("/driver/expenditure/approval/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverExpenditureForApproval() {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = FactoryBL.getDriverExpenditureForApproval();

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/outside/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addOutsideFactoryDispatch(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String truck_number = requestParameters.get("truck_number").toString();
		String driver_name = requestParameters.get("driver_name").toString();
		String driver_phone_number = requestParameters.get("driver_phone_number").toString();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String start_location = requestParameters.get("start_location").toString();
		String unload_location_name = requestParameters.get("unload_location_name").toString();
		int unload_location_id = Integer.parseInt(requestParameters.get("unload_location_id").toString());
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		int fk_association_id = Integer.parseInt(requestParameters.get("fk_association_id").toString());
		String dispatch_date = requestParameters.get("dispatch_date").toString();
		double load_quantity = Double.parseDouble(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		int outside_company_id = Integer.parseInt(requestParameters.get("outside_company_id").toString());
		String delivery_number = requestParameters.get("delivery_number").toString();
		String po_number = requestParameters.get("po_number").toString();
		String invoice_number2 = requestParameters.get("invoice_number2").toString();
		String delivery_number2 = requestParameters.get("delivery_number2").toString();
		String po_number2 = requestParameters.get("po_number2").toString();
		double load_quantity2 = Double.parseDouble(requestParameters.get("load_quantity2").toString());
		double freight2 = Double.parseDouble(requestParameters.get("freight2").toString());

		responseParameters = FactoryBL.addOutsideFactoryDispatch(truck_number,driver_name,driver_phone_number, invoice_number, start_location,
				unload_location_name,unload_location_id,type_of_cement, dispatch_date, load_quantity, freight,fk_association_id,
				outside_company_id,delivery_number,po_number,invoice_number2,load_quantity2, freight2,delivery_number2,
				po_number2);

		return responseParameters;
	}
	
	@POST
	@Path("/fuel/fuel_station/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryFuelByFuelStation(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String fuel_station_id =requestParameters.get("fuel_station_id").toString();
		responseParameters = FactoryBL.getFactoryFuelByFuelStation(lower_date, upper_date, fuel_station_id);

		return responseParameters;
	}
	
	@POST
	@Path("/dispatch/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteFactoryDispatch(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		int factory_dispatch_id =Integer.parseInt(requestParameters.get("factory_dispatch_id").toString());
		responseParameters = FactoryBL.deleteFactoryDispatch(factory_dispatch_id);

		return responseParameters;
	}
	
	@POST
	@Path("/freight/quantity/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFreightAndQuantity(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		responseParameters = FactoryBL.getFreightAndQuantity(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/fuel/cost/report/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFuelCostReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		responseParameters = FactoryBL.getFuelCostReport(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/fuelstation/cost/report/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFuelStationCostReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		responseParameters = FactoryBL.getFuelStationCostReport(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/freight/quantity/month/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFreightAndQuantityMonth(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		responseParameters = FactoryBL.getFreightAndQuantityMonth(lower_date, upper_date);

		return responseParameters;
	}

}
