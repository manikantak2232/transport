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
import com.pixelbox.bl.StorageBL;
import com.pixelbox.bl.UserDetailsBL;

@Path("/driver")
public class DriverService {
	final static Logger log = Logger.getLogger(DriverService.class);

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addDriverDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String first_name = requestParameters.get("first_name").toString();
		String middle_name = requestParameters.get("middle_name").toString();
		String last_name = requestParameters.get("last_name").toString();
		String date_of_birth = requestParameters.get("date_of_birth").toString();
		String aadhar_card = requestParameters.get("aadhar_card").toString();
		String driving_license = requestParameters.get("driving_license").toString();
		String non_transport_license_expiry_date = requestParameters.get("non_transport_license_expiry_date")
				.toString();
		String transport_license_expiry_date = requestParameters.get("transport_license_expiry_date").toString();
		String phone_number = requestParameters.get("phone_number").toString();
		String address = requestParameters.get("address").toString();

		String aadhar_image_bytes_string = requestParameters.get("aadhar_image_bytes_string").toString();
		String aadhar_image_type = requestParameters.get("aadhar_image_type").toString();
		String license_image_bytes_string = requestParameters.get("license_image_bytes_string").toString();
		String license_image_type = requestParameters.get("license_image_type").toString();

		responseParameters = DriverBL.addDriverDetails(first_name, middle_name, last_name, date_of_birth, aadhar_card,
				driving_license, non_transport_license_expiry_date, transport_license_expiry_date, phone_number,
				address, aadhar_image_bytes_string, aadhar_image_type, license_image_bytes_string, license_image_type);

		return responseParameters;

	}

	@GET
	@Path("/getDriverDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverDetails(@QueryParam("fk_driver_id") int fk_driver_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getDriverDetails(fk_driver_id);

		return responseParameters;

	}
	
	@GET
	@Path("/full/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverFullDetails() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getDriverFullDetails();

		return responseParameters;

	}

	@GET
	@Path("/getAllDriverDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllDriverDetails() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getAllDriverDetails();

		return responseParameters;

	}

	@GET
	@Path("/available/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAvailableDrivers() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getAvailableDrivers();

		return responseParameters;

	}

	@GET
	@Path("/all/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDrivers() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getDrivers();

		return responseParameters;

	}

	@POST
	@Path("/updateDriverDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateDriverDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String first_name = requestParameters.get("first_name").toString();
		String middle_name = requestParameters.get("middle_name").toString();
		String last_name = requestParameters.get("last_name").toString();
		String date_of_birth = requestParameters.get("date_of_birth").toString();
		String aadhar_card = requestParameters.get("aadhar_card").toString();
		String driving_license = requestParameters.get("driving_license").toString();
		String non_transport_license_expiry_date = requestParameters.get("non_transport_license_expiry_date")
				.toString();
		String transport_license_expiry_date = requestParameters.get("transport_license_expiry_date").toString();
		String phone_number = requestParameters.get("phone_number").toString();
		String address = requestParameters.get("address").toString();

		String aadhar_image_bytes_string = requestParameters.get("aadhar_image_bytes_string").toString();
		String aadhar_image_type = requestParameters.get("aadhar_image_type").toString();
		String license_image_bytes_string = requestParameters.get("license_image_bytes_string").toString();
		String license_image_type = requestParameters.get("license_image_type").toString();

		responseParameters = DriverBL.updateDriverDetails(fk_driver_id, first_name, middle_name, last_name,
				date_of_birth, aadhar_card, driving_license, non_transport_license_expiry_date,
				transport_license_expiry_date, phone_number, address, aadhar_image_bytes_string, aadhar_image_type,
				license_image_bytes_string, license_image_type);

		return responseParameters;

	}

	@POST
	@Path("/slip/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverSalarySlip(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		responseParameters = DriverBL.getDriverSalarySlip(lower_date, upper_date, fk_driver_id);

		return responseParameters;
	}


	@POST
	@Path("/salary/pay")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> driverSalaryPay(HashMap<String, Object> requestParameters) {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String dispatchIds = requestParameters.get("dispatchIds").toString();
		String ids[] =dispatchIds.split(",");
		for(int i=0; i<ids.length; i++){
			int dispatch_id = Integer.parseInt(ids[i]);
		responseParameters = DriverBL.driverSalaryPay(dispatch_id);
		}
		return responseParameters;
	}
	
	@POST
	@Path("/salary/history/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addDriverSalaryHistory(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		double no_of_days = Double.parseDouble(requestParameters.get("no_of_days").toString());
		double salary_per_day = Double.parseDouble(requestParameters.get("salary_per_day").toString());
		double commission = Double.parseDouble(requestParameters.get("commission").toString());
		double short_bags = Double.parseDouble(requestParameters.get("short_bags").toString());
		double cost_per_bag = Double.parseDouble(requestParameters.get("cost_per_bag").toString());
		double short_fuel = Double.parseDouble(requestParameters.get("short_fuel").toString());
		double cost_per_liter = Double.parseDouble(requestParameters.get("cost_per_liter").toString());
		double total_salary = Double.parseDouble(requestParameters.get("total_salary").toString());
		
		responseParameters = DriverBL.addDriverSalaryHistory(fk_driver_id,no_of_days,salary_per_day,commission,
				short_bags,cost_per_bag,short_fuel,cost_per_liter,total_salary);

		return responseParameters;
	}

	@POST
	@Path("/salary/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverSalary(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		responseParameters = DriverBL.getDriverSalary(fk_driver_id);

		return responseParameters;
	}

	@GET
	@Path("/available/engaged/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getavailableAndEngagedDrivers() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getavailableAndEngagedDrivers();

		return responseParameters;

	}

	@POST
	@Path("/allotment/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addDriverAllotmentToTruck(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		String driver_allotment_date = requestParameters.get("driver_allotment_date").toString();
		int fk_association_id = Integer.parseInt(requestParameters.get("fk_association_id").toString());

		responseParameters = DriverBL.addDriverAllotmentToTruck(fk_truck_id, fk_driver_id, driver_allotment_date,
				fk_association_id);
		return responseParameters;
	}

	@POST
	@Path("/allotment/get/date")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverAllotmentToTruckByDate(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = DriverBL.getDriverAllotmentToTruckByDate(lower_date, upper_date);

		return responseParameters;
	}

	@POST
	@Path("/allotment/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverAllotmentToTruck(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		int pk_driver_allotment_id = Integer.parseInt(requestParameters.get("pk_driver_allotment_id").toString());
		// String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = DriverBL.getDriverAllotmentToTruck(pk_driver_allotment_id);

		return responseParameters;
	}

	@POST
	@Path("/allotment/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateDriverAllotmentToTruck(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_driver_allotment_id = Integer.parseInt(requestParameters.get("pk_driver_allotment_id").toString());
		String driver_allotment_date = requestParameters.get("driver_allotment_date").toString();
		int fk_driver_id = Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		int fk_association_id = Integer.parseInt(requestParameters.get("fk_association_id").toString());
		int driver_status_id = Integer.parseInt(requestParameters.get("driver_status_id").toString());

		responseParameters = DriverBL.updateDriverAllotmentToTruck(pk_driver_allotment_id, driver_allotment_date,
				fk_driver_id, fk_association_id, driver_status_id);

		return responseParameters;

	}

	@GET
	@Path("/balance/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDriverBalance(@QueryParam("fk_driver_id") int fk_driver_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getDriverBalance(fk_driver_id);

		return responseParameters;

	}
	
	@GET
	@Path("/balance/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllDriverBalance() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = DriverBL.getAllDriverBalance();

		return responseParameters;

	}

}
