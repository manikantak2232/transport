package com.pixelbox.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.pixelbox.bl.FactoryBL;
import com.pixelbox.bl.SellerBL;
import com.pixelbox.bl.StorageBL;
import com.pixelbox.bl.TrucksBL;

@Path("/storage")
public class StorageService {
	final static Logger log = Logger.getLogger(StorageService.class);

	@POST
	@Path("/fuel/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addStorageFuel(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		double fuel_quantity = Double.parseDouble(requestParameters.get("fuel_quantity").toString());
		double fuel_rate = Double.parseDouble(requestParameters.get("fuel_rate").toString());
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String fuel_station_location = requestParameters.get("fuel_station_location").toString();
		String date = requestParameters.get("date").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());

		
		responseParameters = StorageBL.addStorageFuel(fk_truck_id,fk_driver_id, fuel_quantity, fuel_rate, amount,
				fuel_station_location, date, advance);
		return responseParameters;
	}

	@POST
	@Path("/dispatch/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addStorageDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		String start_location = requestParameters.get("start_location").toString();
		String dispatch_date = requestParameters.get("dispatch_date").toString();
		String unload_location = requestParameters.get("unload_location").toString();
		double estimated_km = Double.parseDouble(requestParameters.get("estimated_km").toString());
		double starting_meter_reading = Double.parseDouble(requestParameters.get("starting_meter_reading").toString());
		double load_quantity = Double.parseDouble(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		int fk_association_id = Integer.parseInt(requestParameters.get("fk_association_id").toString());

		responseParameters = StorageBL.addStorageDispatch(fk_truck_id, fk_driver_id, invoice_number, start_location,
				dispatch_date, unload_location, estimated_km, starting_meter_reading, load_quantity, freight, advance,
				fk_association_id);
		return responseParameters;
	}

	@POST
	@Path("/invoice/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addStorageInvoice(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String customer_first_name = requestParameters.get("customer_first_name").toString();
		String customer_middle_name = requestParameters.get("customer_middle_name").toString();
		String customer_last_name = requestParameters.get("customer_last_name").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		String phone_number = requestParameters.get("phone_number").toString();
		String email = requestParameters.get("email").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		double total_weight = Double.parseDouble(requestParameters.get("total_weight").toString());
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());

		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		String sales_manager_name = requestParameters.get("sales_manager_name").toString();
		double freight_rate = Double.parseDouble(requestParameters.get("freight_rate").toString());
		// String approve_status =
		// requestParameters.get("approve_status").toString();

		responseParameters = StorageBL.addStorageInvoice(invoice_number, customer_first_name, customer_middle_name,
				customer_last_name, name_of_brand, from_address, to_address,type_of_cement, invoice_date, phone_number, email,
				number_of_bags, total_weight, cost_per_bag, total_value, sales_manager_name, freight_rate);
		return responseParameters;
	}

	@POST
	@Path("/incoming/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addStorageIncomingLoad(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String date = requestParameters.get("date").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();
		String brand_name = requestParameters.get("brand_name").toString();
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double number_of_bags = Double.parseDouble(requestParameters.get("number_of_bags").toString());
		double total_weight = Double.parseDouble(requestParameters.get("total_weight").toString());
		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String time_of_arrival = requestParameters.get("time_of_arrival").toString();
		double reading_km = Double.parseDouble(requestParameters.get("reading_km").toString());
		String status = requestParameters.get("status").toString();

		responseParameters = StorageBL.addStorageIncomingLoad(invoice_number, date, from_address, to_address,
				brand_name, cost_per_bag, number_of_bags, total_weight, total_value, fk_truck_id, fk_driver_id,
				time_of_arrival, reading_km, status);
		return responseParameters;
	}

	@GET
	@Path("/dispatch/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageDispatch(@QueryParam("invoice_number") String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getStorageDispatch(invoice_number);

		return responseParameters;

	}

	@GET
	@Path("/fuel/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageFuel(@QueryParam("fk_truck_id") int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getStorageFuel(fk_truck_id);

		return responseParameters;

	}

	@GET
	@Path("/incoming/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageIncomingLoad(@QueryParam("invoice_number") String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getStorageIncomingLoad(invoice_number);

		return responseParameters;

	}

	@GET
	@Path("/invoice/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageInvoice(@QueryParam("invoice_number") String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getStorageInvoice(invoice_number);

		return responseParameters;

	}

	@POST
	@Path("/dispatch/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageDispatchByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = StorageBL.getStorageDispatchByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/fuel/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageFuelByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		responseParameters = StorageBL.getStorageFuelByDate(lower_date, upper_date, fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/incoming/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageIncomingLoadByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = StorageBL.getStorageIncomingLoadByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/invoice/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageInvoiceByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = StorageBL.getStorageInvoiceByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/dispatch/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateStorageDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int storage_dispatch_id = Integer.parseInt(requestParameters.get("storage_dispatch_id").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		// String invoice_number =
		// requestParameters.get("invoice_number").toString();
		String start_location = requestParameters.get("start_location").toString();
		String dispatch_date = requestParameters.get("dispatch_date").toString();
		String time_of_start = requestParameters.get("time_of_start").toString();
		String unload_location = requestParameters.get("unload_location").toString();
		double estimated_km = Double.parseDouble(requestParameters.get("estimated_km").toString());
		double starting_meter_reading = Double.parseDouble(requestParameters.get("starting_meter_reading").toString());
		double closing_meter_reading = Double.parseDouble(requestParameters.get("closing_meter_reading").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
		double load_quantity = Double.parseDouble(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		double balance = Double.parseDouble(requestParameters.get("balance").toString());
		// String dispatch_status =
		// requestParameters.get("dispatch_status").toString();

		responseParameters = StorageBL.updateStorageDispatch(storage_dispatch_id, fk_truck_id, fk_driver_id,
				start_location, dispatch_date, time_of_start, unload_location, estimated_km, starting_meter_reading,
				closing_meter_reading, checked_kms, load_quantity, freight, advance, balance);
		return responseParameters;
	}

	@POST
	@Path("/fuel/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateStorageFuel(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int storage_fuel_id = Integer.parseInt(requestParameters.get("storage_fuel_id").toString());
		// String truck_number =
		// requestParameters.get("truck_number").toString();
		double fuel_quantity = Double.parseDouble(requestParameters.get("fuel_quantity").toString());
		double fuel_rate = Double.parseDouble(requestParameters.get("fuel_rate").toString());
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String fuel_station_location = requestParameters.get("fuel_station_location").toString();
		String date = requestParameters.get("date").toString();
		responseParameters = StorageBL.updateStorageFuel(storage_fuel_id, fuel_quantity, fuel_rate, amount,
				fuel_station_location, date);
		return responseParameters;
	}

	@POST
	@Path("/invoice/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateStorageInvoice(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int storage_invoice_id = Integer.parseInt(requestParameters.get("storage_invoice_id").toString());
		// String invoice_number =
		// requestParameters.get("invoice_number").toString();
		String customer_first_name = requestParameters.get("customer_first_name").toString();
		String customer_middle_name = requestParameters.get("customer_middle_name").toString();
		String customer_last_name = requestParameters.get("customer_last_name").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();

		String phone_number = requestParameters.get("phone_number").toString();
		String email = requestParameters.get("email").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		double total_weight = Double.parseDouble(requestParameters.get("total_weight").toString());
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());

		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		String sales_manager_name = requestParameters.get("sales_manager_name").toString();
		double freight_rate = Double.parseDouble(requestParameters.get("freight_rate").toString());
		String approve_status = requestParameters.get("approve_status").toString();

		responseParameters = StorageBL.updateStorageInvoice(storage_invoice_id, customer_first_name,
				customer_middle_name, customer_last_name, name_of_brand, from_address, to_address, invoice_date,
				phone_number, email, number_of_bags, total_weight, cost_per_bag, total_value, sales_manager_name,
				freight_rate, approve_status);
		return responseParameters;
	}

	@POST
	@Path("/incoming/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateStorageIncomingLoad(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int storage_incoming_load_id = Integer.parseInt(requestParameters.get("storage_incoming_load_id").toString());
		// String invoice_number =
		// requestParameters.get("invoice_number").toString();
		String date = requestParameters.get("date").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();
		String brand_name = requestParameters.get("brand_name").toString();
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double number_of_bags = Double.parseDouble(requestParameters.get("number_of_bags").toString());
		double total_weight = Double.parseDouble(requestParameters.get("total_weight").toString());
		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String time_of_arrival = requestParameters.get("time_of_arrival").toString();
		double reading_km = Double.parseDouble(requestParameters.get("reading_km").toString());
		String status = requestParameters.get("status").toString();

		responseParameters = StorageBL.updateStorageIncomingLoad(storage_incoming_load_id, date, from_address,
				to_address, brand_name, cost_per_bag, number_of_bags, total_weight, total_value, fk_truck_id,
				fk_driver_id, time_of_arrival, reading_km, status);
		return responseParameters;
	}

	@GET
	@Path("/status/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStatusDispatch(@QueryParam("truck_id") int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getStatusDispatch(truck_id);

		return responseParameters;

	}

	@POST
	@Path("/status/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateStatusDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int storage_dispatch_id = Integer.parseInt(requestParameters.get("storage_dispatch_id").toString());
		String dispatch_status = requestParameters.get("dispatch_status").toString();
		String waiting_location = requestParameters.get("waiting_location").toString();
		responseParameters = StorageBL.updateStatusDispatch(storage_dispatch_id, dispatch_status,waiting_location);
		return responseParameters;
	}

	@GET
	@Path("/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCloseDispatch(@QueryParam("truck_id") int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getCloseDispatch(truck_id);

		return responseParameters;

	}

	@POST
	@Path("/close/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateCloseDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int storage_dispatch_id = Integer.parseInt(requestParameters.get("storage_dispatch_id").toString());
	//	String dispatch_status = requestParameters.get("dispatch_status").toString();
	//	double balance = Double.parseDouble(requestParameters.get("balance").toString());
	//	double expenditure = Double.parseDouble(requestParameters.get("expenditure").toString());
		double closing_meter_reading = Double.parseDouble(requestParameters.get("closing_meter_reading").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
		/*int transport = Integer.parseInt(requestParameters.get("transport").toString());
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
*/
		responseParameters = StorageBL.updateCloseDispatch(storage_dispatch_id,
				closing_meter_reading, checked_kms);
		return responseParameters;
	}

	@GET
	@Path("/trucks/dispatched/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDispatchedTrucks() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getDispatchedTrucks();

		return responseParameters;

	}

	@GET
	@Path("/trucks/unloading/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUnloadingTrucks() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = StorageBL.getUnloadingTrucks();

		return responseParameters;

	}
	
	@POST
	@Path("/driverchange/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addStorageDriverChange(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_storage_dispatch_id = Integer.parseInt(requestParameters.get("fk_storage_dispatch_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String location = requestParameters.get("location").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		responseParameters = StorageBL.addStorageDriverChange(fk_storage_dispatch_id, fk_driver_id, location,
				advance);

		return responseParameters;
	}
	
	@GET
	@Path("/change/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageChange(@QueryParam("truck_id") int truck_id) {		
        HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = StorageBL.getStorageChange(truck_id);
			
		return responseParameters;
	}
	
	@POST
	@Path("/expenditure/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String date = requestParameters.get("date").toString();

		responseParameters = StorageBL.getStorageDriverExpenditure(fk_driver_id, date);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageDriversCloseExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int storage_dispatch_id = Integer.parseInt(requestParameters.get("storage_dispatch_id").toString());

		responseParameters = StorageBL.getStorageDriversCloseExpenditure(storage_dispatch_id);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addStorageDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int storage_dispatch_id = Integer.parseInt(requestParameters.get("storage_dispatch_id").toString());
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

		responseParameters = StorageBL.addStorageDriverExpenditure(storage_dispatch_id,fk_driver_id, transport, loading, cover_tying, contonment, toll_gate,
				loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry, return_transport,
				return_loading, return_unloading, others, balance);

		return responseParameters;
	}


}
