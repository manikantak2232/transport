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
import com.pixelbox.bl.LocationBL;

@Path("/location")
public class LocationService {
	final static Logger log = Logger.getLogger(LocationService.class);
	
	@POST
	@Path("/allotment/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addLocationAllotment(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		int fk_driver_id= Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		int fk_truck_id =Integer.parseInt(requestParameters.get("fk_truck_id").toString());		
		String present_location = requestParameters.get("present_location").toString();
		String new_location= requestParameters.get("new_location").toString();
		String date= requestParameters.get("date").toString();
		
	    responseParameters = LocationBL.addLocationAllotment(fk_driver_id,fk_truck_id,present_location,new_location,date);
			
		return responseParameters;
	
	}
	@POST
	@Path("/status/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addLocationStatus(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String status_of_truck = requestParameters.get("status_of_truck").toString();	
		int fk_truck_id= Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String present_location = requestParameters.get("present_location").toString();
		String date = requestParameters.get("date").toString();
		
		responseParameters = LocationBL.addLocationStatus(status_of_truck,fk_truck_id,present_location,date);
			
		return responseParameters;
	
	}//-------------------------------------------------get------------------------------------------------//
	@GET
	@Path("/allotment/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLocationAllotment(@QueryParam("fk_truck_id") int fk_truck_id) {		
        HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = LocationBL.getLocationAllotment(fk_truck_id);
			
		return responseParameters;
	
	}
	@GET
	@Path("/status/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLocationStatus(@QueryParam("fk_truck_id") int fk_truck_id) {		
        HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = LocationBL.getLocationStatus(fk_truck_id);
			
		return responseParameters;
	
	}
	//------------------------------------------------get-date-----------------------------------//
	@POST
	@Path("/allotment/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLocationAllotmentByDate(HashMap<String, Object> requestParameters) {		

HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = LocationBL.getLocationAllotmentByDate(lower_date,upper_date);
			
		return responseParameters;	
	}
	@POST
	@Path("/status/date/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLocationStatusByDate(HashMap<String, Object> requestParameters) {		

HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = LocationBL.getLocationStatusByDate(lower_date,upper_date);
			
		return responseParameters;	
	}
//-------------------------------------------------update-----------------------------------//
	@POST
	@Path("/allotment/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateLocationAllotment(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int location_allotment_id= Integer.parseInt(requestParameters.get("location_allotment_id").toString());	
		int fk_driver_id= Integer.parseInt(requestParameters.get("fk_driver_id").toString());
		int fk_truck_id = Integer.parseInt(requestParameters.get("fk_truck_id").toString());		
		String present_location = requestParameters.get("present_location").toString();
		String new_location= requestParameters.get("new_location").toString();
		String date= requestParameters.get("date").toString();
		
	    responseParameters = LocationBL.updateLocationAllotment(location_allotment_id,fk_driver_id,fk_truck_id,present_location,new_location,date);
			
		return responseParameters;
	
	}
	@POST
	@Path("/status/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object>updateLocationStatus(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int location_status_id= Integer.parseInt(requestParameters.get("location_status_id").toString());	
		String status_of_truck = requestParameters.get("status_of_truck").toString();	
		int fk_truck_id= Integer.parseInt(requestParameters.get("fk_truck_id").toString());
		String present_location = requestParameters.get("present_location").toString();
		String date = requestParameters.get("date").toString();
		
		responseParameters = LocationBL.updateLocationStatus(location_status_id,status_of_truck,fk_truck_id,present_location,date);
			
		return responseParameters;
	
	}
}


	
	