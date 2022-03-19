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

import com.pixelbox.bl.DriverBL;
import com.pixelbox.bl.FactoryBL;
import com.pixelbox.bl.SparePartsBL;
import com.pixelbox.bl.TrucksBL;
import com.pixelbox.bl.TyreBL;

@Path("/tyre")
public class TyreService {
	final static Logger log = Logger.getLogger(TyreService.class);

	@POST
	@Path("/vehicletyresummaryreport/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addVehicleTyreSummaryReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String vehicle_number = requestParameters.get("vehicle_number").toString();
		String front_or_back_tyres = requestParameters.get("front_or_back_tyres").toString();
		String tyre_number = requestParameters.get("tyre_number").toString();
		String tyre_issued_reading = requestParameters.get("tyre_issued_reading").toString();
		String tyre_closing_reading = requestParameters.get("tyre_closing_reading").toString();
		String remarks = requestParameters.get("remarks").toString();

		responseParameters = TyreBL.addVehicleTyreSummaryReport(date, vehicle_number, front_or_back_tyres, tyre_number,
				tyre_issued_reading, tyre_closing_reading, remarks);

		return responseParameters;

	}

	@POST
	@Path("/addFrontTotalSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addFrontTotalSummary(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String air_filler = requestParameters.get("air_filler").toString();
		String front = requestParameters.get("front").toString();
		String tyre_number = requestParameters.get("tyre_number").toString();
		String vehicle_number = requestParameters.get("vehicle_number").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String closing_date = requestParameters.get("closing_date").toString();
		String reading = requestParameters.get("reading").toString();
		String remarks = requestParameters.get("remarks").toString();

		responseParameters = TyreBL.addFrontTotalSummary(date, air_filler, front, tyre_number, vehicle_number,
				fk_driver_id, closing_date, reading, remarks);

		return responseParameters;

	}

	@POST
	@Path("/addHosingTotalSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addHosingTotalSummary(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String air_filler = requestParameters.get("air_filler").toString();
		String hosing = requestParameters.get("hosing").toString();
		String tyre_number = requestParameters.get("tyre_number").toString();
		String vehicle_number = requestParameters.get("vehicle_number").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String closing_date = requestParameters.get("closing_date").toString();
		String reading = requestParameters.get("reading").toString();
		String remarks = requestParameters.get("remarks").toString();

		responseParameters = TyreBL.addHosingTotalSummary(date, air_filler, hosing, tyre_number, vehicle_number,
				fk_driver_id, closing_date, reading, remarks);

		return responseParameters;

	}

	@GET
	@Path("/vehicletyresummaryreport/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getVehicleTyreSummaryReport(@QueryParam("vehicle_number") String vehicle_number) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = TyreBL.getVehicleTyreSummaryReport(vehicle_number);

		return responseParameters;

	}

	// ----------------------------------------grt by
	// date----------------------------------------//
	@POST
	@Path("/summaryreport/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getVehicleTyreSummaryReportByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = TyreBL.getVehicleTyreSummaryReportByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/summaryreport/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateVehicleTyreSummaryReportByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int vehicle_tyre_summary_report_id = Integer
				.parseInt(requestParameters.get("vehicle_tyre_summary_report_id").toString());
		String date = requestParameters.get("date").toString();
		String vehicle_number = requestParameters.get("vehicle_number").toString();
		String front_or_back_tyres = requestParameters.get("front_or_back_tyres").toString();
		String tyre_number = requestParameters.get("tyre_number").toString();
		String tyre_issued_reading = requestParameters.get("tyre_issued_reading").toString();
		String tyre_closing_reading = requestParameters.get("tyre_closing_reading").toString();
		String remarks = requestParameters.get("remarks").toString();

		responseParameters = TyreBL.updateVehicleTyreSummaryReport(vehicle_tyre_summary_report_id, date, vehicle_number,
				front_or_back_tyres, tyre_number, tyre_issued_reading, tyre_closing_reading, remarks);

		return responseParameters;
	}
	
	@POST
	@Path("/inventory/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTyreInventory(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		
		String number = requestParameters.get("tyre_number").toString();
		double tyre_unit_price = Double.parseDouble(requestParameters.get("tyre_unit_price").toString());
		double tyre_discount_percent = Double.parseDouble(requestParameters.get("tyre_discount_percent").toString());
		double tyre_gst_percent = Double.parseDouble(requestParameters.get("tyre_gst_percent").toString());
		double tube_unit_price = Double.parseDouble(requestParameters.get("tube_unit_price").toString());
		double tube_discount_percent = Double.parseDouble(requestParameters.get("tube_discount_percent").toString());
		double tube_gst_percent = Double.parseDouble(requestParameters.get("tube_gst_percent").toString());
		double flap_unit_price = Double.parseDouble(requestParameters.get("flap_unit_price").toString());
		double flap_discount_percent = Double.parseDouble(requestParameters.get("flap_discount_percent").toString());
		double flap_gst_percent = Double.parseDouble(requestParameters.get("flap_gst_percent").toString());
		String tyre[] =number.split("\n");
	//	double tyre_cost=(cost/tyre.length);
		String tyre_type = requestParameters.get("tyre_type").toString();
	//	String tyre_type1[] =type.split("\n");
		for(int i=0; i<tyre.length; i++){
			String invoice_number = requestParameters.get("invoice_number").toString();
		//	String tyre_type = tyre_type1[i];
			String invoice_date = requestParameters.get("invoice_date").toString();
			String brand_name = requestParameters.get("brand_name").toString();
			String tyre_number = tyre[i];

		responseParameters = TyreBL.addTyreInventory(invoice_number, tyre_type, invoice_date,
				brand_name, tyre_number,tyre_unit_price,tyre_discount_percent,tyre_gst_percent,
				tube_unit_price,tube_discount_percent,tube_gst_percent,flap_unit_price,
				flap_discount_percent,flap_gst_percent);	
		}
		return responseParameters;

	}
	
	@GET
	@Path("/all/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllTyres() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TyreBL.getAllTyres();
			
		return responseParameters;
	}
	
	@GET
	@Path("/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllBrandNames() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TyreBL.getAllBrandNames();
			
		return responseParameters;
	}
	
	@GET
	@Path("/running/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllRunningBrandNames() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TyreBL.getAllRunningBrandNames();
			
		return responseParameters;
	}
	
	@GET
	@Path("/pending/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllPendingBrandNames() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TyreBL.getAllPendingBrandNames();
			
		return responseParameters;
	}
	
	@POST
	@Path("/all/bycategory/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllTyresByCategory(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String brand_name = requestParameters.get("brand_name").toString();
		String tyre_type = requestParameters.get("tyre_type").toString();
		String tyre_category = requestParameters.get("tyre_category").toString();

		responseParameters = TyreBL.getAllTyresByCategory(brand_name, tyre_type, tyre_category);

		return responseParameters;
	}
	
	@POST
	@Path("/all/runningtyres/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllRunningTyres(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String brand_name = requestParameters.get("brand_name").toString();
	//	String tyre_type = requestParameters.get("tyre_type").toString();

		responseParameters = TyreBL.getAllRunningTyres(brand_name);

		return responseParameters;
	}
	
	@POST
	@Path("/all/pendingtyres/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllPendingTyres(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String brand_name = requestParameters.get("brand_name").toString();
		String tyre_type = requestParameters.get("tyre_type").toString();

		responseParameters = TyreBL.getAllPendingTyres(brand_name, tyre_type);

		return responseParameters;
	}
	
	@POST
	@Path("/recoup/status/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateRecoupTyreStatus(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String tyre_number = requestParameters.get("tyre_number").toString();
		String tyre_status = requestParameters.get("tyre_status").toString();

		responseParameters = TyreBL.updateRecoupTyreStatus(tyre_number, tyre_status);

		return responseParameters;
	}
	
	@POST
	@Path("/issued/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addIssuedTyres(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String issued_tyre_number = requestParameters.get("issued_tyre_number").toString();
		String issued_brand_name = requestParameters.get("issued_brand_name").toString();
		String issued_tyre_type = requestParameters.get("issued_tyre_type").toString();
		String issued_tyre_category = requestParameters.get("issued_tyre_category").toString();
		String issued_and_returned_date = requestParameters.get("issued_and_returned_date").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		int recoupTyreCost = Integer.parseInt(requestParameters.get("recoupTyreCost").toString());
		Double issued_tyre_reading = Double.parseDouble(requestParameters.get("issued_tyre_reading").toString());
		String returned_tyre_number = requestParameters.get("returned_tyre_number").toString();
		String returned_brand_name = requestParameters.get("returned_brand_name").toString();
	//	String returned_tyre_type = requestParameters.get("returned_tyre_type").toString();
		String returned_tyre_category = requestParameters.get("returned_tyre_category").toString();
		Double returned_tyre_reading = Double.parseDouble(requestParameters.get("returned_tyre_reading").toString());

		responseParameters = TyreBL.addIssuedTyres(issued_tyre_number, issued_brand_name, issued_tyre_type, issued_tyre_category,
				issued_and_returned_date,fk_truck_id,fk_driver_id, issued_tyre_reading, returned_tyre_number,
				returned_brand_name,returned_tyre_category,returned_tyre_reading,recoupTyreCost);

		return responseParameters;

	}
	
	@POST
	@Path("/recoup/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addRecoupTyres(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String tyre_number = requestParameters.get("tyre_number").toString();
		String brand_name = requestParameters.get("brand_name").toString();
		String tyre_type = requestParameters.get("tyre_type").toString();
		Double tyre_taken_reading = Double.parseDouble(requestParameters.get("tyre_taken_reading").toString());
		String tyre_taken_date = requestParameters.get("tyre_taken_date").toString();

		responseParameters = TyreBL.addRecoupTyres(tyre_number, brand_name, tyre_type, tyre_taken_reading,
				tyre_taken_date);

		return responseParameters;

	}
	
	@POST
	@Path("/running/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getRunningTyres(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String truck_number = requestParameters.get("truck_number").toString();

		responseParameters = TyreBL.getRunningTyres(truck_number);

		return responseParameters;
	}
	
	@POST
	@Path("/issued/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getIssuedTyres(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String tyre_category = requestParameters.get("tyre_category").toString();
		String truck_number = requestParameters.get("truck_number").toString();

		responseParameters = TyreBL.getIssuedTyres(lower_date, upper_date,tyre_category, truck_number);

		return responseParameters;
	}
	
	@POST
	@Path("/waste/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getWasteTyres(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = TyreBL.getWasteTyres(lower_date, upper_date);

		return responseParameters;
	}
	
	@GET
	@Path("/recoup/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getRecoupTyres() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TyreBL.getRecoupTyres();
			
		return responseParameters;
	}
	
	@GET
	@Path("/inventory/current/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTyresCurrentInventory() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TyreBL.getTyresCurrentInventory();
			
		return responseParameters;
	}
	
	@POST
	@Path("/inventory/history/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTyresInventoryHistory(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = TyreBL.getTyresInventoryHistory(lower_date, upper_date);

		return responseParameters;
	}

}
