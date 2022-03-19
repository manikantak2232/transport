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
import com.pixelbox.bl.SparePartsBL;
import com.pixelbox.bl.StorageBL;
import com.pixelbox.bl.TrucksBL;


@Path("/trucks")
public class TrucksService {
	final static Logger log = Logger.getLogger(TrucksService.class);
	
	@POST
	@Path("/allotment/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTrucksAllotment(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String truck_allotment_date = requestParameters.get("truck_allotment_date").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());		
		String allotment_location = requestParameters.get("allotment_location").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		
		
		responseParameters = TrucksBL.addTrucksAllotment(truck_allotment_date,fk_driver_id,allotment_location,fk_truck_id
				);			
		return responseParameters;	
	}
	
	@POST
	@Path("/details/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTrucksDetails(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String truck_number = requestParameters.get("truck_number").toString();
		//int truck_id=Integer.parseInt(requestParameters.get("truck_id").toString());	
		String chassis_number = requestParameters.get("chassis_number").toString();
		String engine_number = requestParameters.get("engine_number").toString();
		String registration_date = requestParameters.get("registration_date").toString();
		String permit_number = requestParameters.get("permit_number").toString();
		String permit_area =requestParameters.get("permit_area").toString();
		String permit_validity =requestParameters.get("permit_validity").toString();
		String insurance_company_name =requestParameters.get("insurance_company_name").toString();
		String insurance_policy_number =requestParameters.get("insurance_policy_number").toString();
		String insurance_date =requestParameters.get("insurance_date").toString();
		String insurance_expiry_date =requestParameters.get("insurance_expiry_date").toString();
		String fitness_certificate_number=requestParameters.get("fitness_certificate_number").toString();
		String fitness_expire_date =requestParameters.get("fitness_expire_date").toString();
		
		String fitness_image_bytes_string=requestParameters.get("fitness_image_bytes_string").toString();
		String fitness_image_type =requestParameters.get("fitness_image_type").toString();
		
		String rc_image_bytes_string=requestParameters.get("rc_image_bytes_string").toString();
		String rc_image_type =requestParameters.get("rc_image_type").toString();
		String insurance_image_bytes_string=requestParameters.get("insurance_image_bytes_string").toString();
		String insurance_image_type =requestParameters.get("insurance_image_type").toString();
		String permit_image_bytes_string=requestParameters.get("permit_image_bytes_string").toString();
		String permit_image_type =requestParameters.get("permit_image_type").toString();
		
		responseParameters = TrucksBL.addTrucksDetails(truck_number,chassis_number,engine_number,
				registration_date,permit_number,permit_area,permit_validity,insurance_company_name,
				insurance_policy_number,insurance_date,insurance_expiry_date,fitness_certificate_number,
				fitness_expire_date,fitness_image_bytes_string,fitness_image_type,rc_image_bytes_string,
				rc_image_type,insurance_image_bytes_string,insurance_image_type,permit_image_bytes_string,
				permit_image_type);
						
		return responseParameters;	
	}
	
	@POST
	@Path("/health/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTrucksHealthHistory(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String description = requestParameters.get("description").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());		
		String date = requestParameters.get("date").toString();
		int fk_spare_parts_id = Integer.parseInt(requestParameters.get("fk_spare_parts_id").toString());

		responseParameters = TrucksBL.addTrucksHealthHistory(description,fk_truck_id,date,fk_spare_parts_id
				);			
		return responseParameters;	
	}
	
	@POST
	@Path("/quote/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTrucksPartsQuote(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String quote_number = requestParameters.get("quote_number").toString();
		String new_quote_date = requestParameters.get("new_quote_date").toString();
		String product_name = requestParameters.get("product_name").toString();		
		String company_name = requestParameters.get("company_name").toString();
		String agent = requestParameters.get("agent").toString();
		int number_of_units = Integer.parseInt(requestParameters.get("number_of_units").toString());
		double unit_price = Double.parseDouble(requestParameters.get("unit_price").toString());
		double total = Double.parseDouble(requestParameters.get("total").toString());
		int no_of_days_for_delivery = Integer.parseInt(requestParameters.get("no_of_days_for_delivery").toString());
		int no_of_hours_for_delivery = Integer.parseInt(requestParameters.get("no_of_hours_for_delivery").toString());
		String mode_of_payment = requestParameters.get("mode_of_payment").toString();
		String type_of_payment = requestParameters.get("type_of_payment").toString();
		int valid_for_number_of_days = Integer.parseInt(requestParameters.get("valid_for_number_of_days").toString());
		String contact_number = requestParameters.get("contact_number").toString();
	//	String quote_status = requestParameters.get("quote_status").toString();
		
		responseParameters = TrucksBL.addTrucksPartsQuote(quote_number,new_quote_date,product_name,company_name,
				agent,number_of_units,unit_price,total,no_of_days_for_delivery,no_of_hours_for_delivery,mode_of_payment,type_of_payment,
				valid_for_number_of_days,contact_number
				);			
		return responseParameters;	
	}
	
	
	/*@POST
	@Path("/service/add1")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTrucksService1(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String service_center_name = requestParameters.get("service_center_name").toString();
		String service_date = requestParameters.get("service_date").toString();		
		double service_total_cost = Double.parseDouble(requestParameters.get("service_total_cost").toString());
		int fk_spare_parts_id = Integer.parseInt(requestParameters.get("fk_spare_parts_id").toString());
		String description = requestParameters.get("description").toString();
		
		
		responseParameters = TrucksBL.addTrucksService(fk_truck_id,service_center_name,service_date,service_total_cost,
				fk_spare_parts_id,description
				);			
		return responseParameters;	
	}
	
	@POST
	@Path("/service/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTrucksService(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String spare_parts = requestParameters.get("fk_spare_parts_id").toString();
		String spare_array[] =spare_parts.split(",");
		for(int i=0; i<spare_array.length; i++){
				
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String service_center_name = requestParameters.get("service_center_name").toString();
		String service_date = requestParameters.get("service_date").toString();		
		double service_total_cost = Double.parseDouble(requestParameters.get("service_total_cost").toString());
		int fk_spare_parts_id = Integer.parseInt(spare_array[i]);
		String description = requestParameters.get("description").toString();
		
		
		responseParameters = TrucksBL.addTrucksService(fk_truck_id,service_center_name,service_date,service_total_cost,
				fk_spare_parts_id,description
				);	
		}
		return responseParameters;	
		
	}*/

	@POST
	@Path("/service/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTrucksService(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		/*String spare_parts = requestParameters.get("fk_spare_parts_id").toString();
		String spare_array[] =spare_parts.split(",");
		for(int i=0; i<spare_array.length; i++){*/
				
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String service_center_name = requestParameters.get("service_center_name").toString();
		String service_start_date = requestParameters.get("service_start_date").toString();		
		/*double service_total_cost = Double.parseDouble(requestParameters.get("service_total_cost").toString());
		int fk_spare_parts_id = Integer.parseInt(spare_array[i]);
		String description = requestParameters.get("description").toString();*/
		
		
		responseParameters = TrucksBL.addTrucksService(fk_truck_id,service_center_name,service_start_date);	
		
		return responseParameters;	
		
}
	
	@POST
	@Path("/service/close")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> closeTrucksService(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String spare_parts = requestParameters.get("fk_spare_parts_id").toString();
		String spare_array[] =spare_parts.split(",");
		for(int i=0; i<spare_array.length; i++){
				
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String service_closed_date = requestParameters.get("service_closed_date").toString();
		int fk_spare_parts_id = Integer.parseInt(spare_array[i]);	
		double service_total_cost = Double.parseDouble(requestParameters.get("service_total_cost").toString());
		//String truck_service_status = requestParameters.get("service_status").toString();
		
		responseParameters = TrucksBL.closeTrucksService(fk_truck_id,service_closed_date,fk_spare_parts_id,service_total_cost
				);	
		}
				return responseParameters;	
		
}

	
	@GET
	@Path("/allotment/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksAllotment(@QueryParam ("fk_truck_id") int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksAllotment(fk_truck_id);
			
		return responseParameters;
	
	}
	
	
	@GET
	@Path("/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksDetails(@QueryParam ("fk_truck_id") int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksDetails(fk_truck_id);
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/quote/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksPartsQuote(@QueryParam ("quote_number") String quote_number) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksPartsQuote(quote_number);
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/health/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksHealthHistory(@QueryParam ("fk_truck_id") int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksHealthHistory(fk_truck_id);
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/service/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksService(@QueryParam ("fk_truck_id") int fk_truck_id) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksService(fk_truck_id);
			
		return responseParameters;
	
	}
	
	
	@POST
	@Path("/allotment/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksAllotmentByDate(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = TrucksBL.getTrucksAllotmentByDate(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/health/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksHealthHistoryByDate(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		
		responseParameters = TrucksBL.getTrucksHealthHistoryByDate(lower_date, upper_date,fk_truck_id);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/maintenance_expenditure/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckMaintenanceExpenditure(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		String fk_truck_id =requestParameters.get("fk_truck_id").toString();
		
		responseParameters = TrucksBL.getTruckMaintenanceExpenditure(lower_date, upper_date,fk_truck_id);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/maintenance_expenditure/get/date/report")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckMaintenanceExpenditureReport(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = TrucksBL.getTruckMaintenanceExpenditureReport(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/quote/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksPartsQuoteByDate(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = TrucksBL.getTrucksPartsQuoteByDate(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/maintenance/cost/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksMaintenanceCost(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = TrucksBL.getTrucksMaintenanceCost(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/trip/count/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksTripCount(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = TrucksBL.getTrucksTripCount(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/service/get/truck/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksServiceByTruckNumber(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		responseParameters = TrucksBL.getTrucksServiceByTruckNumber(lower_date, upper_date,fk_truck_id);
			
		return responseParameters;	
	}
	


@POST
	@Path("/service/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksServiceByDate(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = TrucksBL.getTrucksServiceByDate(lower_date, upper_date);
			
		return responseParameters;	
	}
	/*@POST
	@Path("/service/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksServiceByDate(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = TrucksBL.getTrucksServiceByDate(lower_date, upper_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/service/get/truck/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksServiceByTruck(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		responseParameters = TrucksBL.getTrucksServiceByTruck(lower_date, upper_date,fk_truck_id);
			
		return responseParameters;	
	}
	*/
	
	
	@POST
	@Path("/allotment/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateTrucksAllotment(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int trucks_allotment_id = Integer.parseInt(requestParameters.get("trucks_allotment_id").toString());
		String truck_allotment_date = requestParameters.get("truck_allotment_date").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());		
		String allotment_location = requestParameters.get("allotment_location").toString();
//		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		
		
		responseParameters = TrucksBL.updateTrucksAllotment(trucks_allotment_id,truck_allotment_date,fk_driver_id,allotment_location
				);			
		return responseParameters;	
	}
	
	@POST
	@Path("/details/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateTrucksDetails(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
	    int truck_id = Integer.parseInt(requestParameters.get("truck_id").toString());
		String chassis_number = requestParameters.get("chassis_number").toString();		
		String  engine_number = requestParameters.get("engine_number").toString();
		String  registration_date= requestParameters.get("registration_date").toString();
		String  permit_number= requestParameters.get("permit_number").toString();
		String  permit_area= requestParameters.get("permit_area").toString();
		String  permit_validity= requestParameters.get("permit_validity").toString();
		String  insurance_company_name= requestParameters.get("insurance_company_name").toString();
		String  insurance_policy_number= requestParameters.get("insurance_policy_number").toString();
		String  insurance_date = requestParameters.get("insurance_date").toString();
		String  insurance_expiry_date = requestParameters.get("insurance_expiry_date").toString();
		String  fitness_certificate_number = requestParameters.get("fitness_certificate_number").toString();
		String  fitness_expire_date = requestParameters.get("fitness_expire_date").toString();
		
		String rc_image_bytes_string=requestParameters.get("rc_image_bytes_string").toString();
		String rc_image_type =requestParameters.get("rc_image_type").toString();
		String insurance_image_bytes_string=requestParameters.get("insurance_image_bytes_string").toString();
		String insurance_image_type =requestParameters.get("insurance_image_type").toString();
		String fitness_image_bytes_string=requestParameters.get("fitness_image_bytes_string").toString();
		String fitness_image_type =requestParameters.get("fitness_image_type").toString();
		String permit_image_bytes_string=requestParameters.get("permit_image_bytes_string").toString();
		String permit_image_type =requestParameters.get("permit_image_type").toString();
		
		responseParameters = TrucksBL.updateTrucksDetails(truck_id,chassis_number,engine_number,registration_date,permit_number,permit_area,permit_validity,insurance_company_name,
						insurance_policy_number,insurance_date,insurance_expiry_date,fitness_certificate_number,fitness_expire_date,
						rc_image_bytes_string,rc_image_type,insurance_image_bytes_string,insurance_image_type,
						fitness_image_bytes_string,fitness_image_type,permit_image_bytes_string,permit_image_type);
		return responseParameters;	
	}

	
	@POST
	@Path("/health/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateTrucksHealthHistory(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int trucks_health_history_id = Integer.parseInt(requestParameters.get("trucks_health_history_id").toString());
		String description = requestParameters.get("description").toString();
	//	int fk_truck_id = requestParameters.get("fk_truck_id").toString();		
		String date = requestParameters.get("date").toString();
		int fk_spare_parts_id = Integer.parseInt(requestParameters.get("fk_spare_parts_id").toString());

		responseParameters = TrucksBL.updateTrucksHealthHistory(trucks_health_history_id,description,date,fk_spare_parts_id
				);			
		return responseParameters;	
	}
	
	@POST
	@Path("/quote/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateTrucksPartsQuote(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int trucks_parts_quote_id = Integer.parseInt(requestParameters.get("trucks_parts_quote_id").toString());
	//	String quote_number = requestParameters.get("quote_number").toString();
		String new_quote_date = requestParameters.get("new_quote_date").toString();
		String product_name = requestParameters.get("product_name").toString();		
		String company_name = requestParameters.get("company_name").toString();
		String agent = requestParameters.get("agent").toString();
		int number_of_units = Integer.parseInt(requestParameters.get("number_of_units").toString());
		double unit_price = Double.parseDouble(requestParameters.get("unit_price").toString());
		double total = Double.parseDouble(requestParameters.get("total").toString());
		int no_of_days_for_delivery = Integer.parseInt(requestParameters.get("no_of_days_for_delivery").toString());
		int no_of_hours_for_delivery = Integer.parseInt(requestParameters.get("no_of_hours_for_delivery").toString());
		String mode_of_payment = requestParameters.get("mode_of_payment").toString();
		String type_of_payment = requestParameters.get("type_of_payment").toString();
		int valid_for_number_of_days = Integer.parseInt(requestParameters.get("valid_for_number_of_days").toString());
		String contact_number = requestParameters.get("contact_number").toString();
		String quote_status = requestParameters.get("quote_status").toString();
		
		responseParameters = TrucksBL.updateTrucksPartsQuote(trucks_parts_quote_id,new_quote_date,product_name,company_name,
				agent,number_of_units,unit_price,total,no_of_days_for_delivery,no_of_hours_for_delivery,mode_of_payment,type_of_payment,
				valid_for_number_of_days,contact_number,quote_status
				);			
		return responseParameters;	
	}
	
	
	@POST
	@Path("/service/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateTrucksService(HashMap<String, Object> requestParameters) {		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int trucks_service_id = Integer.parseInt(requestParameters.get("trucks_service_id").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String service_center_name = requestParameters.get("service_center_name").toString();
		String service_date = requestParameters.get("service_date").toString();		
		double service_total_cost = Double.parseDouble(requestParameters.get("service_total_cost").toString());
		int fk_spare_parts_id = Integer.parseInt(requestParameters.get("fk_spare_parts_id").toString());
		String description = requestParameters.get("description").toString();
		
		
		responseParameters = TrucksBL.updateTrucksService(trucks_service_id,fk_truck_id,service_center_name,service_date,service_total_cost,
				fk_spare_parts_id,description
				);			
		return responseParameters;	
	}
	
	
	@GET
	@Path("/all/bystatus/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllTrucksDetails() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getAllTrucksDetails();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/assigned/available/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAssignedAvailableTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getAssignedAvailableTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/all/grid/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksGrid() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksGrid();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/all/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucks();
			
		return responseParameters;
	
	}
	
	
	@GET
	@Path("/all/other/trucks/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksAndOtherTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksAndOtherTrucks();
			
		return responseParameters;
	
	}

	@GET
	@Path("/alltrucks/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getAllTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/engaged/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getEngagedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getEngagedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/factory/engaged/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryEngagedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getFactoryEngagedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/storage/engaged/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStorageEngagedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getStorageEngagedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/seller/engaged/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSellerEngagedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getSellerEngagedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/changedriver/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksDriverChange() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getTrucksDriverChange();
			
		return responseParameters;
	
	}
	

	@GET
	@Path("/available/engaged/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllAvailableAndEngagedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getAllAvailableAndEngagedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/alloted/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllotedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getAllotedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/assigned/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAssignedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getAssignedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/factory/dispatched/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryDispatchedTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getFactoryDispatchedTrucks();
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/factory/unloading/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getFactoryUnloadingTrucks() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = TrucksBL.getFactoryUnloadingTrucks();
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/status/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckStatus(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String truck_number = requestParameters.get("truck_number").toString();
		
		responseParameters = TrucksBL.getTruckStatus(truck_number);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/idle/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTruckIdleStatus(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String idle_reason=requestParameters.get("idle_reason").toString();
		
		responseParameters = TrucksBL.addTruckIdleStatus(fk_truck_id,idle_reason);
			
		return responseParameters;	
	}
	
	@GET
	@Path("/status/tracking/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckStatusTracking(@QueryParam ("selected_date") String selected_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			String date_select=selected_date.concat(" 23:59:59");
		responseParameters = TrucksBL.getTruckStatusTracking(date_select);
			
		return responseParameters;
	
	}
	
	@GET
	@Path("/status/tracking/list/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckStatusTrackingList(@QueryParam ("selected_date") String selected_date) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			String date_select=selected_date.concat(" 23:59:59");
		responseParameters = TrucksBL.getTruckStatusTrackingList(date_select);
			
		return responseParameters;
	
	}
	
	@POST
	@Path("/grid/calendar/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrucksGridCalendar(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String truck_number = requestParameters.get("truck_number").toString();
		String month_start_date=requestParameters.get("month_start_date").toString();
		
		responseParameters = TrucksBL.getTrucksGridCalendar(truck_number,month_start_date);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/expiry/permit/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getExpiryTrucksPermit(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String date_type = requestParameters.get("date_type").toString();
		
		responseParameters = TrucksBL.getExpiryTrucksPermit(date_type);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/expiry/insurance/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getExpiryTrucksInsurance(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String date_type = requestParameters.get("date_type").toString();
		
		responseParameters = TrucksBL.getExpiryTrucksInsurance(date_type);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/expiry/fitness/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getExpiryTrucksFitness(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String date_type = requestParameters.get("date_type").toString();
		
		responseParameters = TrucksBL.getExpiryTrucksFitness(date_type);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/currentday/status/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckCurrentDayStatusDetails(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String operation = requestParameters.get("operation").toString();
		int operation_id = Integer.parseInt(requestParameters.get("operation_id").toString());
		
		responseParameters = TrucksBL.getTruckCurrentDayStatusDetails(operation,operation_id);
			
		return responseParameters;	
	}
	
	@POST
	@Path("/maintenance/expenditure/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addTruckMaintenanceExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		
		String itemName = requestParameters.get("item_name").toString();
		String items[] =itemName.split("\n");
		String cost = requestParameters.get("item_cost").toString();
		String itemCost[] =cost.split("\n");
		for(int i=0; i<items.length; i++){
			String item_cost = itemCost[i];
			String date = requestParameters.get("date").toString();
			String expenditure_type = requestParameters.get("expenditure_type").toString();
			String remarks = requestParameters.get("remarks").toString();
			String item_name = items[i];
			int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());

		responseParameters = TrucksBL.addTruckMaintenanceExpenditure( item_cost, date,remarks, item_name, fk_truck_id,expenditure_type);	
		}
		return responseParameters;
	}
/*	public HashMap<String, Object> addTruckMaintenanceExpenditure(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String item_names = requestParameters.get("item_names").toString();
		String date = requestParameters.get("date").toString();
		String remarks = requestParameters.get("remarks").toString();
		double cost = Double.parseDouble(requestParameters.get("cost").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		
		responseParameters = TrucksBL.addTruckMaintenanceExpenditure(item_names,date,remarks,cost,fk_truck_id);
			
		return responseParameters;	
	}*/
	
}
