package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.FactoryDAO;
import com.pixelbox.dao.GpsTrackingDAO;

public class GpsTrackingBL {
	public static HashMap<String, Object> addLatLong(String lattitude, String longitude,String id	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = GpsTrackingDAO.addLatLong(lattitude, longitude, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
/*	public static HashMap<String, Object> getLatLong() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = GpsTrackingDAO.getLatLong();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}*/
	
	public static HashMap<String, Object> getLatLong() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = GpsTrackingDAO.getLatLong();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
}
