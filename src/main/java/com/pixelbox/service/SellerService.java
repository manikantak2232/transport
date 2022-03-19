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

import com.pixelbox.bl.FactoryBL;
import com.pixelbox.bl.SellerBL;
import com.pixelbox.bl.StorageBL;

@Path("/seller")
public class SellerService {
	final static Logger log = Logger.getLogger(SellerService.class);

	@POST
	@Path("/fuel/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerFuel(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		double fuel_quantity = Double.parseDouble(requestParameters.get("fuel_quantity").toString());
		double fuel_rate = Double.parseDouble(requestParameters.get("fuel_rate").toString());
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String fuel_station_location = requestParameters.get("fuel_station_location").toString();
		String date = requestParameters.get("date").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());

		responseParameters = SellerBL.addSellerFuel(fk_truck_id, fk_driver_id,fuel_quantity, fuel_rate, amount,
				fuel_station_location, date,advance);
		return responseParameters;
	}

	@POST
	@Path("/dispatch/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		String start_location = requestParameters.get("start_location").toString();
		String unload_location = requestParameters.get("unload_location").toString();
		String dispatch_date = requestParameters.get("dispatch_date").toString();
		double estimated_km = Double.parseDouble(requestParameters.get("estimated_km").toString());
		double starting_meter_reading = Double.parseDouble(requestParameters.get("starting_meter_reading").toString());
		double load_quantity = Double.parseDouble(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		int fk_association_id = Integer.parseInt(requestParameters.get("fk_association_id").toString());

		responseParameters = SellerBL.addSellerDispatch(fk_truck_id, fk_driver_id, invoice_number, start_location,
				unload_location, dispatch_date, estimated_km, starting_meter_reading, load_quantity, freight, advance,
				fk_association_id);
		return responseParameters;
	}

	@POST
	@Path("/invoice/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerInvoice(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String customer_first_name = requestParameters.get("customer_first_name").toString();
		String customer_middle_name = requestParameters.get("customer_middle_name").toString();
		String customer_last_name = requestParameters.get("customer_last_name").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();
		String type_of_cement = requestParameters.get("type_of_cement").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String phone_number = requestParameters.get("phone_number").toString();
		String email = requestParameters.get("email").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		String sales_manager_name = requestParameters.get("sales_manager_name").toString();
		double freight_rate = Double.parseDouble(requestParameters.get("freight_rate").toString());

		responseParameters = SellerBL.addSellerInvoice(invoice_number, customer_first_name, customer_middle_name,
				customer_last_name, name_of_brand, from_address, to_address,type_of_cement, invoice_date, phone_number, email,
				number_of_bags, cost_per_bag, total_value, sales_manager_name, freight_rate);
		return responseParameters;
	}

	@POST
	@Path("/purchase/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerPurchase(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		String date = requestParameters.get("date").toString();

		responseParameters = SellerBL.addSellerPurchase(invoice_number, name_of_brand, number_of_bags, cost_per_bag,
				total_value, date);
		return responseParameters;
	}

	@GET
	@Path("/dispatch/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerDispatch(@QueryParam("invoice_number") String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getSellerDispatch(invoice_number);

		return responseParameters;

	}

	@GET
	@Path("/fuel/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerFuel(@QueryParam("fk_truck_id") int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getSellerFuel(fk_truck_id);

		return responseParameters;

	}

	@GET
	@Path("/invoice/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerInvoice(@QueryParam("invoice_number") String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getSellerInvoice(invoice_number);

		return responseParameters;

	}

	@GET
	@Path("/purchase/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerPurchase(@QueryParam("invoice_number") String invoice_number) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getSellerPurchase(invoice_number);

		return responseParameters;

	}

	@POST
	@Path("/dispatch/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerDispatchByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = SellerBL.getSellerDispatchByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/fuel/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerFuelBydate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());

		responseParameters = SellerBL.getSellerFuelBydate(lower_date, upper_date, fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/invoice/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerInvoiceByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = SellerBL.getSellerInvoiceByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/purchase/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerPurchaseByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = SellerBL.getSellerPurchaseByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/fuel/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateSellerFuel(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int seller_fuel_id = Integer.parseInt(requestParameters.get("seller_fuel_id").toString());
		// String truck_number =
		// requestParameters.get("truck_number").toString();
		double fuel_quantity = Double.parseDouble(requestParameters.get("fuel_quantity").toString());
		double fuel_rate = Double.parseDouble(requestParameters.get("fuel_rate").toString());
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String fuel_station_location = requestParameters.get("fuel_station_location").toString();
		String date = requestParameters.get("date").toString();

		responseParameters = SellerBL.updateSellerFuel(seller_fuel_id, fuel_quantity, fuel_rate, amount,
				fuel_station_location, date);
		return responseParameters;
	}

	@POST
	@Path("/dispatch/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateSellerDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int seller_dispatch_id = Integer.parseInt(requestParameters.get("seller_dispatch_id").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		String start_location = requestParameters.get("start_location").toString();
		String time_of_start = requestParameters.get("time_of_start").toString();
		String unload_location = requestParameters.get("unload_location").toString();
		String dispatch_date = requestParameters.get("dispatch_date").toString();
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

		responseParameters = SellerBL.updateSellerDispatch(seller_dispatch_id, fk_truck_id, fk_driver_id,
				invoice_number, start_location, time_of_start, unload_location, dispatch_date, estimated_km,
				starting_meter_reading, closing_meter_reading, checked_kms, load_quantity, freight, advance, balance);
		return responseParameters;
	}

	@POST
	@Path("/invoice/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateSellerInvoice(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int seller_invoice_id = Integer.parseInt(requestParameters.get("seller_invoice_id").toString());
		// String invoice_number =
		// requestParameters.get("invoice_number").toString();
		String customer_first_name = requestParameters.get("customer_first_name").toString();
		String customer_middle_name = requestParameters.get("customer_middle_name").toString();
		String customer_last_name = requestParameters.get("customer_last_name").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		String from_address = requestParameters.get("from_address").toString();
		String to_address = requestParameters.get("to_address").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String phone_number = requestParameters.get("phone_number").toString();
		String email = requestParameters.get("email").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		String sales_manager_name = requestParameters.get("sales_manager_name").toString();
		double freight_rate = Double.parseDouble(requestParameters.get("freight_rate").toString());
		String approve_status = requestParameters.get("approve_status").toString();

		responseParameters = SellerBL.updateSellerInvoice(seller_invoice_id, customer_first_name, customer_middle_name,
				customer_last_name, name_of_brand, from_address, to_address, invoice_date, phone_number, email,
				number_of_bags, cost_per_bag, total_value, sales_manager_name, freight_rate, approve_status);
		return responseParameters;
	}

	@POST
	@Path("/purchase/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateSellerPurchase(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int seller_purchase_id = Integer.parseInt(requestParameters.get("seller_purchase_id").toString());
		// String invoice_number =
		// requestParameters.get("invoice_number").toString();
		String name_of_brand = requestParameters.get("name_of_brand").toString();
		int number_of_bags = Integer.parseInt(requestParameters.get("number_of_bags").toString());
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double total_value = Double.parseDouble(requestParameters.get("total_value").toString());
		String date = requestParameters.get("date").toString();

		responseParameters = SellerBL.updateSellerPurchase(seller_purchase_id, name_of_brand, number_of_bags,
				cost_per_bag, total_value, date);
		return responseParameters;
	}

	@GET
	@Path("/status/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStatusDispatch(@QueryParam("truck_id") int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getStatusDispatch(truck_id);

		return responseParameters;

	}

	@POST
	@Path("/status/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateStatusDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int seller_dispatch_id = Integer.parseInt(requestParameters.get("seller_dispatch_id").toString());
		String dispatch_status = requestParameters.get("dispatch_status").toString();
		String waiting_location = requestParameters.get("waiting_location").toString();
		responseParameters = SellerBL.updateStatusDispatch(seller_dispatch_id, dispatch_status,waiting_location);
		return responseParameters;
	}

	@GET
	@Path("/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCloseDispatch(@QueryParam("truck_id") int truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getCloseDispatch(truck_id);

		return responseParameters;

	}

	@POST
	@Path("/close/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateCloseDispatch(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int seller_dispatch_id = Integer.parseInt(requestParameters.get("seller_dispatch_id").toString());
	//	String dispatch_status = requestParameters.get("dispatch_status").toString();
	//	double balance = Double.parseDouble(requestParameters.get("balance").toString());
	//	double expenditure = Double.parseDouble(requestParameters.get("expenditure").toString());
		double closing_meter_reading = Double.parseDouble(requestParameters.get("closing_meter_reading").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
	/*	int transport = Integer.parseInt(requestParameters.get("transport").toString());
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
		responseParameters = SellerBL.updateCloseDispatch(seller_dispatch_id, 
				closing_meter_reading, checked_kms);
		return responseParameters;
	}

	@GET
	@Path("/trucks/dispatched/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDispatchedTrucks() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getDispatchedTrucks();

		return responseParameters;

	}

	@GET
	@Path("/trucks/unloading/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUnloadingTrucks() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = SellerBL.getUnloadingTrucks();

		return responseParameters;

	}
	
	@POST
	@Path("/driverchange/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerDriverChange(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_seller_dispatch_id = Integer.parseInt(requestParameters.get("fk_seller_dispatch_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String location = requestParameters.get("location").toString();
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		responseParameters = SellerBL.addSellerDriverChange(fk_seller_dispatch_id, fk_driver_id, location, advance);

		return responseParameters;
	}
	
	@GET
	@Path("/change/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerChange(@QueryParam("truck_id") int truck_id) {		
        HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SellerBL.getSellerChange(truck_id);
			
		return responseParameters;
	}
	
	@POST
	@Path("/expenditure/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String date = requestParameters.get("date").toString();

		responseParameters = SellerBL.getSellerDriverExpenditure(fk_driver_id, date);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerDriversCloseExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int seller_dispatch_id = Integer.parseInt(requestParameters.get("seller_dispatch_id").toString());

		responseParameters = SellerBL.getSellerDriversCloseExpenditure(seller_dispatch_id);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int seller_dispatch_id = Integer.parseInt(requestParameters.get("seller_dispatch_id").toString());
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

		responseParameters = SellerBL.addSellerDriverExpenditure(seller_dispatch_id,fk_driver_id, transport, loading, cover_tying, contonment, toll_gate,
				loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry, return_transport,
				return_loading, return_unloading, others, balance);

		return responseParameters;
	}

}
