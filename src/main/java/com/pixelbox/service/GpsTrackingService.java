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
import com.pixelbox.bl.GpsTrackingBL;


@Path("/gps")
public class GpsTrackingService {
	final static Logger log = Logger.getLogger(GpsTrackingService.class);

	@POST
	@Path("/tracking/lat/long")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addLatLong(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lattitude = requestParameters.get("lattitude").toString();
		String longitude = requestParameters.get("longitude").toString();
		String id = requestParameters.get("id").toString();

		responseParameters = GpsTrackingBL.addLatLong(lattitude, longitude,id);

		return responseParameters;
	}
	
/*	@GET
	@Path("/tracking/lat/long/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLatLong(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = GpsTrackingBL.getLatLong();

		return responseParameters;
	}*/
	
	@POST
	@Path("/tracking/lat/long/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLatLong() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			
		responseParameters = GpsTrackingBL.getLatLong();
			
		return responseParameters;
	
	}

}



	


