package com.pixelbox.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pixelbox.bl.SparePartsBL;
import com.pixelbox.dto.TestDto;


@Path("/spareparts")
public class SparePartsService {
	final static Logger log = Logger.getLogger(SparePartsService.class);
	
	@POST
	@Path("/allotment/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSparePartAllotment(HashMap<String, Object> requestParameters) {
		
	//	HashMap<String, Object> requestParameters = null;
	//	List<Integer> subList  = dto.getFk_spare_part_id();
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String list = requestParameters.get("fk_spare_part_id").toString();
		ArrayList<Integer> intList = new Gson().fromJson(list, new TypeToken<ArrayList<Integer>>(){}.getType());
		for(int i=0; i<intList.size(); i++){
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		int fk_spare_part_id =  intList.get(i);		
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
	//	int count = Integer.parseInt(requestParameters.get("count").toString());
		String date = requestParameters.get("date").toString();
			
		responseParameters = SparePartsBL.addSparePartAllotment(fk_driver_id,fk_spare_part_id,fk_truck_id,
				date);
		}
		return responseParameters;
	
	}
	
	@POST
	@Path("/allotment/add1")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSparePartAllotment1(HashMap<String, Object> requestParameters) {
		
	//	HashMap<String, Object> requestParameters = null;
	//	List<Integer> subList  = dto.getFk_spare_part_id();
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String fk_spare_part_id =  requestParameters.get("fk_spare_part_id").toString();		
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String date = requestParameters.get("date").toString();
			
		responseParameters = SparePartsBL.addSparePartAllotment1(fk_driver_id,fk_spare_part_id,fk_truck_id,
				date);
		
		return responseParameters;
	
	}
	
	//	String spare_parts = (list).replace("[", "").replace("]", "");
	//	String spare_arr[] =spare_parts.split(",");
	//	int spare_array[] = new int[spare_arr.length];
	@GET
	@Path("/allotment/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSparePartAllotment(@QueryParam ("fk_truck_id") int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getSparePartAllotment(fk_truck_id);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/allotment/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSparePartAllotmentByDate(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = SparePartsBL.getSparePartAllotmentByDate(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/allotment/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateSparePartAllotment(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int spare_part_allotment_id = Integer.parseInt(requestParameters.get("spare_part_allotment_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		int fk_spare_part_id = Integer.parseInt(requestParameters.get("fk_spare_part_id").toString());		
	//	String truck_number = requestParameters.get("truck_number").toString();
		int count = Integer.parseInt(requestParameters.get("count").toString());
		String date = requestParameters.get("date").toString();
			
		responseParameters = SparePartsBL.updateSparePartAllotment(spare_part_allotment_id,fk_driver_id,fk_spare_part_id,
				count,date);
			
		return responseParameters;
	
	}
	
	
	@GET
	@Path("/all/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllSpareParts() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getAllSpareParts();
			
		return responseParameters;
	}
	
	@POST
	@Path("/inventory")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSparePartInventory(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_spare_part_id = Integer.parseInt(requestParameters.get("fk_spare_part_id").toString());		
		int count = Integer.parseInt(requestParameters.get("count").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		double unit_price = Double.parseDouble(requestParameters.get("unit_price").toString());
		double discount = Double.parseDouble(requestParameters.get("discount").toString());
		double gst = Double.parseDouble(requestParameters.get("gst").toString());
		double total = Double.parseDouble(requestParameters.get("total").toString());
		
		responseParameters = SparePartsBL.addSparePartInventory(fk_spare_part_id,count, invoice_number,
				unit_price,discount,gst,total);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/inventory/history/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSparePartInventoryHistory(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = SparePartsBL.getSparePartInventoryHistory(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@GET
	@Path("/current/inventory/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSparePartCurrentInventory() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getSparePartCurrentInventory();
			
		return responseParameters;
	}
	
	@GET
	@Path("/oil/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOilBrandNames() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getOilBrandNames();
			
		return responseParameters;
	}
	
	@GET
	@Path("/battery/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBatteryBrandNames() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getBatteryBrandNames();
			
		return responseParameters;
	}
	
	@GET
	@Path("/battery/inventory/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getInventoryBatteryBrands() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getInventoryBatteryBrands();
			
		return responseParameters;
	}
	
	@GET
	@Path("/battery/running/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getRunningBatteryBrands() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getRunningBatteryBrands();
			
		return responseParameters;
	}
	
	@POST
	@Path("/battery/inventory/number/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getInventoryBatteryNumber(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_battery_brand_id = Integer.parseInt(requestParameters.get("fk_battery_brand_id").toString());	
		responseParameters = SparePartsBL.getInventoryBatteryNumber(fk_battery_brand_id);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/battery/running/number/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getRunningBatteryNumber(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_battery_brand_id = Integer.parseInt(requestParameters.get("fk_battery_brand_id").toString());	
		responseParameters = SparePartsBL.getRunningBatteryNumber(fk_battery_brand_id);
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/oil/distinct/brand/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOilDistinceBrandNames() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getOilDistinceBrandNames();
			
		return responseParameters;
	}
	
	@POST
	@Path("/oil/inventory/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addOilInventory(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String date = requestParameters.get("date").toString();		
		int fk_oil_brand_id = Integer.parseInt(requestParameters.get("fk_oil_brand_id").toString());
		String invoice_number = requestParameters.get("invoice_number").toString();
		Double liters = Double.parseDouble(requestParameters.get("liters").toString());		
		Double cost = Double.parseDouble(requestParameters.get("cost").toString());
		String invoice_date = requestParameters.get("invoice_date").toString();
		responseParameters = SparePartsBL.addOilInventory(date,fk_oil_brand_id, invoice_number,
				cost,liters,invoice_date);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/oil/new/brandname")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addOilBrandName(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String brand_name = requestParameters.get("brand_name").toString();		
		responseParameters = SparePartsBL.addOilBrandName(brand_name);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/battery/new/brandname")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addBatteryBrandName(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String brand_name = requestParameters.get("brand_name").toString();		
		responseParameters = SparePartsBL.addBatteryBrandName(brand_name);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/oil/issue/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addIssuedOil(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());		
		String issued_date = requestParameters.get("issued_date").toString();
		int fk_oil_brand_id = Integer.parseInt(requestParameters.get("fk_oil_brand_id").toString());
		Double liters = Double.parseDouble(requestParameters.get("liters").toString());		
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		Double reading = Double.parseDouble(requestParameters.get("reading").toString());
		
		responseParameters = SparePartsBL.addIssuedOil(fk_truck_id,issued_date, fk_oil_brand_id,
				liters,fk_driver_id,reading);
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/oil/current/inventory/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOilCurrentInventory() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getOilCurrentInventory();
			
		return responseParameters;
	}
	
	@POST
	@Path("/oil/inventory/history/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOilInventoryHistory(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = SparePartsBL.getOilInventoryHistory(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/oil/issued/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getIssuedOil(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String truck_number = requestParameters.get("truck_number").toString();

		responseParameters = SparePartsBL.getIssuedOil(lower_date, upper_date, truck_number);

		return responseParameters;
	}
	
	@POST
	@Path("/battery/inventory/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addBatteryInventory(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		
		String number = requestParameters.get("battery_number").toString();
		String tyre[] =number.split("\n");
	//	String type = requestParameters.get("tyre_type").toString();
	//	String tyre_type1[] =type.split("\n");
		for(int i=0; i<tyre.length; i++){
			String invoice_number = requestParameters.get("invoice_number").toString();
	//		String tyre_type = tyre_type1[i];
			String invoice_date = requestParameters.get("invoice_date").toString();
			String date = requestParameters.get("date").toString();
			int fk_battery_brand_id = Integer.parseInt(requestParameters.get("battery_brand_id").toString());
			String battery_number = tyre[i];

		responseParameters = SparePartsBL.addBatteryInventory(invoice_number, invoice_date,date,
				fk_battery_brand_id, battery_number);	
		}
		return responseParameters;

	}
	
	@POST
	@Path("/battery/issued/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addIssuedBattery(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String issued_battery_number = requestParameters.get("issued_battery_number").toString();
		int fk_issued_battery_id = Integer.parseInt(requestParameters.get("fk_issued_battery_id").toString());
	
		String issued_and_returned_date = requestParameters.get("issued_and_returned_date").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String returned_battery_number = requestParameters.get("returned_battery_number").toString();
		int fk_returned_brand_id = Integer.parseInt(requestParameters.get("fk_returned_brand_id").toString());

		responseParameters = SparePartsBL.addIssuedBattery(issued_battery_number, fk_issued_battery_id, 
				issued_and_returned_date,fk_truck_id,fk_driver_id, returned_battery_number, fk_returned_brand_id);

		return responseParameters;

	}
	
	@GET
	@Path("/battery/inventory/current/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBatteryCurrentInventory() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getBatteryCurrentInventory();
			
		return responseParameters;
	}
	
	@GET
	@Path("/battery/running/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getRunningBattery() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getRunningBattery();
			
		return responseParameters;
	}
	
	@POST
	@Path("/battery/inventory/history/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBatteryInventoryHistory(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = SparePartsBL.getBatteryInventoryHistory(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/battery/issued/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getIssuedBattery(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = SparePartsBL.getIssuedBattery(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/battery/waste/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getWasteBattery(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = SparePartsBL.getWasteBattery(lower_date, upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/advance/request/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTruckMaintenanceAdvanceRequest(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		
		String itemName = requestParameters.get("item_name").toString();
		String items[] =itemName.split("\n");
		String cost = requestParameters.get("item_cost").toString();
		String itemCost[] =cost.split("\n");
		for(int i=0; i<items.length; i++){
			String item_cost = itemCost[i];
			String date = requestParameters.get("date").toString();
			String item_name = items[i];
			int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
			int request_number = Integer.parseInt(requestParameters.get("request_number_value").toString());

		responseParameters = SparePartsBL.addTruckMaintenanceAdvanceRequest( item_cost, date, item_name, fk_truck_id,
				request_number);	
		}
		return responseParameters;

	}
	
	@POST
	@Path("/advance/request/number/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckMaintenanceAdvanceRequestNumber(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String date = requestParameters.get("date").toString();

		responseParameters = SparePartsBL.getTruckMaintenanceAdvanceRequestNumber(fk_truck_id, date);

		return responseParameters;
	}
	
	@POST
	@Path("/advance/item/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckMaintenanceAdvanceItemDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String truck_maintenance_advance_id = requestParameters.get("truck_maintenance_advance_id").toString();

		responseParameters = SparePartsBL.getTruckMaintenanceAdvanceItemDetails(truck_maintenance_advance_id);

		return responseParameters;
	}
	
	@GET
	@Path("/advance/request/count/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckMaintenanceAdvanceRequestCount() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = SparePartsBL.getTruckMaintenanceAdvanceRequestCount();
			
		return responseParameters;
	}
	
	@POST
	@Path("/approval/status/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateTruckMaintenanceAdvanceApprovalStatus(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String truck_maintenance_advance_id = requestParameters.get("truck_maintenance_advance_id").toString();

		responseParameters = SparePartsBL.updateTruckMaintenanceAdvanceApprovalStatus(truck_maintenance_advance_id);

		return responseParameters;
	}
	
	@POST
	@Path("/truck/overall/maintenance/report/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOverallTruckMaintenanceReport(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = SparePartsBL.getOverallTruckMaintenanceReport(lower_date, upper_date);

		return responseParameters;
	}

}