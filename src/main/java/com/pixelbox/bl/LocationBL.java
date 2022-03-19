package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.DriverDAO;
import com.pixelbox.dao.LocationDAO;
public class LocationBL {
	public static HashMap<String, Object> addLocationAllotment(int fk_driver_id,
                                                               int fk_truck_id,
                                                               String present_location,
                                                               String new_location,
                                                               String date) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		

		
		try{
			responseParameters = LocationDAO.addLocationAllotment( fk_driver_id,
					                                               fk_truck_id,
					                                               present_location,
					                                               new_location,
					                                              date
					                               	             );
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	public static HashMap<String, Object> addLocationStatus(String status_of_truck,
                                                           int fk_truck_id,
                                                           String present_location,
                                                           String date
                                                          ) {	

HashMap<String, Object> responseParameters = new HashMap<String, Object>();

try{
responseParameters = LocationDAO.addLocationStatus(status_of_truck,
                                                   fk_truck_id,
                                                   present_location,
                                                   date
                                                     );
}catch(Exception e){
e.printStackTrace();
}
return responseParameters;
}
	public static HashMap<String, Object> getLocationAllotment(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = LocationDAO.getLocationAllotment(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
	}
	public static HashMap<String, Object> getLocationStatus(int fk_truck_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = LocationDAO.getLocationStatus(fk_truck_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
	}
	public static HashMap<String, Object>getLocationAllotmentByDate(String lower_date, String upper_date) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = LocationDAO.getLocationAllotmentByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
	}
	public static HashMap<String, Object>getLocationStatusByDate(String lower_date, String upper_date) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = LocationDAO.getLocationStatusByDate(lower_date, upper_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
	}
	//-------------------------------------------------update--------------------------------------------------------//
	public static HashMap<String, Object> updateLocationAllotment(int location_allotment_id,
			                                                      int fk_driver_id,
                                                                  int fk_truck_id,
                                                                  String present_location,
                                                                  String new_location,
                                                                  String date) {	

HashMap<String, Object> responseParameters = new HashMap<String, Object>();



try{
responseParameters = LocationDAO.updateLocationAllotment(location_allotment_id,
		                                            fk_driver_id,
		                                            fk_truck_id,
		                                            present_location,
		                                            new_location,
		                                            date
		                              	             );
}catch(Exception e){
e.printStackTrace();
}
return responseParameters;
}
	public static HashMap<String, Object> updateLocationStatus(int location_status_id,
			                                                   String status_of_truck,
			                                                   int fk_truck_id,
			                                                   String present_location,
			                                                   String date
			                                                   ) {	

HashMap<String, Object> responseParameters = new HashMap<String, Object>();

try{
responseParameters = LocationDAO.updateLocationStatus(location_status_id,
		                                              status_of_truck,
		                                              fk_truck_id,
		                                              present_location,
		                                              date
		                                               );
}catch(Exception e){
e.printStackTrace();
}
return responseParameters;
}
}	