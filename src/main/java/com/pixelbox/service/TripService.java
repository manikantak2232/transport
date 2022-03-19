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
import com.pixelbox.bl.TripBL;
import com.pixelbox.bl.TrucksBL;

@Path("/trip")
public class TripService {
	final static Logger log = Logger.getLogger(TripService.class);

	@POST
	@Path("/factory/details/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addFactoryTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String start_location = requestParameters.get("start_location").toString();
		String start_date = requestParameters.get("start_date").toString();
		String load_description = requestParameters.get("load_description").toString();
		String destination = requestParameters.get("destination").toString();
		int estimated_km = Integer.parseInt(requestParameters.get("estimated_km").toString());
		int starting_meter_reading = Integer.parseInt(requestParameters.get("starting_meter_reading").toString());
		String type_of_load = requestParameters.get("type_of_load").toString();
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		String starting_meter_reading_image_bytes_string=requestParameters.get("starting_meter_reading_image_bytes_string").toString();
		String starting_meter_reading_image_type =requestParameters.get("starting_meter_reading_image_type").toString();


		responseParameters = TripBL.addFactoryTripDetails(fk_truck_id, fk_driver_id, start_location, start_date,
				destination, estimated_km, starting_meter_reading, freight,advance,
				starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,
				load_description,type_of_load);

		return responseParameters;
	}

	@GET
	@Path("/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTripDetails(@QueryParam("fk_truck_id") int fk_truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TripBL.getTripDetails(fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/details/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		// String truck_number =
		// requestParameters.get("truck_number").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String start_location = requestParameters.get("start_location").toString();
		String start_date = requestParameters.get("start_date").toString();
		String time_of_start = requestParameters.get("time_of_start").toString();
		String destination = requestParameters.get("destination").toString();
		int estimated_km = Integer.parseInt(requestParameters.get("estimated_km").toString());
		int starting_meter_reading = Integer.parseInt(requestParameters.get("starting_meter_reading").toString());
		int closing_meter_reading = Integer.parseInt(requestParameters.get("closing_meter_reading").toString());
		int load_quantity = Integer.parseInt(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
		// String trip_status = requestParameters.get("trip_status").toString();

		responseParameters = TripBL.updateTripDetails(trip_id, fk_truck_id, fk_driver_id, start_location, start_date,
				time_of_start, destination, estimated_km, starting_meter_reading, load_quantity, freight,
				closing_meter_reading, checked_kms);

		return responseParameters;
	}

	@GET
	@Path("/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCloseTripDetails(@QueryParam("fk_truck_id") int fk_truck_id ) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TripBL.getCloseTripDetails(fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/close/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateCloseTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		int closing_meter_reading = Integer.parseInt(requestParameters.get("closing_meter_reading").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
//		String trip_status = requestParameters.get("trip_status").toString();
		String closing_meter_reading_image_bytes_string=requestParameters.get("closing_meter_reading_image_bytes_string").toString();
		String closing_meter_reading_image_type =requestParameters.get("closing_meter_reading_image_type").toString();
		double freight = Double.parseDouble(requestParameters.get("freight").toString());

		responseParameters = TripBL.updateCloseTripDetails(trip_id, closing_meter_reading, checked_kms,
				closing_meter_reading_image_bytes_string,closing_meter_reading_image_type,freight);

		return responseParameters;
	}

	@POST
	@Path("/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTripByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = TripBL.getTripByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/storage/details/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addStorageTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String start_location = requestParameters.get("start_location").toString();
		String start_date = requestParameters.get("start_date").toString();
	//	String time_of_start = requestParameters.get("time_of_start").toString();
		String destination = requestParameters.get("destination").toString();
		int estimated_km = Integer.parseInt(requestParameters.get("estimated_km").toString());
		int starting_meter_reading = Integer.parseInt(requestParameters.get("starting_meter_reading").toString());
		int load_quantity = Integer.parseInt(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		double advance = Double.parseDouble(requestParameters.get("advance").toString());

		responseParameters = TripBL.addStorageTripDetails(fk_truck_id, fk_driver_id, start_location, start_date,
				 destination, estimated_km, starting_meter_reading, load_quantity, freight, advance);

		return responseParameters;
	}

	@GET
	@Path("/storage/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageTripDetails(@QueryParam("fk_truck_id") int fk_truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TripBL.getStorageTripDetails(fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/storage/details/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateStorageTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		// String truck_number =
		// requestParameters.get("truck_number").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String start_location = requestParameters.get("start_location").toString();
		String start_date = requestParameters.get("start_date").toString();
		String time_of_start = requestParameters.get("time_of_start").toString();
		String destination = requestParameters.get("destination").toString();
		int estimated_km = Integer.parseInt(requestParameters.get("estimated_km").toString());
		int starting_meter_reading = Integer.parseInt(requestParameters.get("starting_meter_reading").toString());
		int closing_meter_reading = Integer.parseInt(requestParameters.get("closing_meter_reading").toString());
		int load_quantity = Integer.parseInt(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
		// String trip_status = requestParameters.get("trip_status").toString();

		responseParameters = TripBL.updateStorageTripDetails(trip_id, fk_truck_id, fk_driver_id, start_location,
				start_date, time_of_start, destination, estimated_km, starting_meter_reading, load_quantity, freight,
				closing_meter_reading, checked_kms);

		return responseParameters;
	}

	@GET
	@Path("/storage/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCloseStorageTripDetails(@QueryParam("fk_truck_id") int fk_truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TripBL.getCloseStorageTripDetails(fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/storage/close/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateCloseStorageTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		int closing_meter_reading = Integer.parseInt(requestParameters.get("closing_meter_reading").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
	//	String trip_status = requestParameters.get("trip_status").toString();
		responseParameters = TripBL.updateCloseStorageTripDetails(trip_id, closing_meter_reading, checked_kms);

		return responseParameters;
	}

	@POST
	@Path("/storage/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageTripByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = TripBL.getStorageTripByDate(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/seller/details/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String start_location = requestParameters.get("start_location").toString();
		String start_date = requestParameters.get("start_date").toString();
	//	String time_of_start = requestParameters.get("time_of_start").toString();
		String destination = requestParameters.get("destination").toString();
		int estimated_km = Integer.parseInt(requestParameters.get("estimated_km").toString());
		int starting_meter_reading = Integer.parseInt(requestParameters.get("starting_meter_reading").toString());
		int load_quantity = Integer.parseInt(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		double advance = Double.parseDouble(requestParameters.get("advance").toString());
		
		responseParameters = TripBL.addSellerTripDetails(fk_truck_id, fk_driver_id, start_location, start_date,
				 destination, estimated_km, starting_meter_reading, load_quantity, freight,
				 advance);

		return responseParameters;
	}

	@GET
	@Path("/seller/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerTripDetails(@QueryParam("fk_truck_id") int fk_truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TripBL.getSellerTripDetails(fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/seller/details/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateSellerTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		// String truck_number =
		// requestParameters.get("truck_number").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String start_location = requestParameters.get("start_location").toString();
		String start_date = requestParameters.get("start_date").toString();
		String time_of_start = requestParameters.get("time_of_start").toString();
		String destination = requestParameters.get("destination").toString();
		int estimated_km = Integer.parseInt(requestParameters.get("estimated_km").toString());
		int starting_meter_reading = Integer.parseInt(requestParameters.get("starting_meter_reading").toString());
		int closing_meter_reading = Integer.parseInt(requestParameters.get("closing_meter_reading").toString());
		int load_quantity = Integer.parseInt(requestParameters.get("load_quantity").toString());
		double freight = Double.parseDouble(requestParameters.get("freight").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
		// String trip_status = requestParameters.get("trip_status").toString();

		responseParameters = TripBL.updateSellerTripDetails(trip_id, fk_truck_id, fk_driver_id, start_location,
				start_date, time_of_start, destination, estimated_km, starting_meter_reading, load_quantity, freight,
				closing_meter_reading, checked_kms);

		return responseParameters;
	}

	@GET
	@Path("/seller/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCloseSellerTripDetails(@QueryParam("fk_truck_id") int fk_truck_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TripBL.getCloseSellerTripDetails(fk_truck_id);

		return responseParameters;
	}

	@POST
	@Path("/seller/close/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateCloseSellerTripDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		int closing_meter_reading = Integer.parseInt(requestParameters.get("closing_meter_reading").toString());
		int checked_kms = Integer.parseInt(requestParameters.get("checked_kms").toString());
	//	String trip_status = requestParameters.get("trip_status").toString();
		responseParameters = TripBL.updateCloseSellerTripDetails(trip_id, closing_meter_reading, checked_kms);

		return responseParameters;
	}

	@POST
	@Path("/seller/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerTripByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = TripBL.getSellerTripByDate(lower_date, upper_date);

		return responseParameters;
	}
	
	@GET
	@Path("/started/trucks/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTripStartedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TripBL.getTripStartedTrucks();
			
		return responseParameters;
	
	}

	@POST
	@Path("/expenditure/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTripDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String date = requestParameters.get("date").toString();

		responseParameters = TripBL.getTripDriverExpenditure(fk_driver_id, date);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/close/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTripDriversCloseExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		String dispatch = requestParameters.get("dispatch").toString();

		responseParameters = TripBL.getTripDriversCloseExpenditure(trip_id,dispatch);

		return responseParameters;
	}
	
	@POST
	@Path("/driver/expenditure/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTripDriverExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int trip_id = Integer.parseInt(requestParameters.get("trip_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String dispatch = requestParameters.get("dispatch").toString();
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

		responseParameters = TripBL.addTripDriverExpenditure(trip_id,fk_driver_id, dispatch,transport, loading, cover_tying, contonment, toll_gate,
				loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry, return_transport,
				return_loading, return_unloading, others, balance);

		return responseParameters;
	}

}

	


