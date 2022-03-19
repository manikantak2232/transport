package com.pixelbox.bl;

import java.util.HashMap;

//import com.pixelbox.dao.ConnectedUsersDAO;
import com.pixelbox.dao.DriverDAO;
import com.pixelbox.dao.FactoryDAO;
import com.pixelbox.dao.TripDAO;
import com.pixelbox.dao.TrucksDAO;

public class TripBL {
	public static HashMap<String, Object> addFactoryTripDetails(int fk_truck_id, int fk_driver_id, String start_location,
			String start_date,  String destination, int estimated_km, int starting_meter_reading,
			 double freight, double advance, String starting_meter_reading_image_bytes_string,
			 String starting_meter_reading_image_type,String load_description,String type_of_load

	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.addFactoryTripDetails(fk_truck_id, fk_driver_id, start_location, start_date,
					 destination, estimated_km, starting_meter_reading, freight,advance,
					 starting_meter_reading_image_bytes_string,starting_meter_reading_image_type,
					 load_description,type_of_load

			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getTripDetails(int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getTripDetails(fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateTripDetails(int trip_id, int fk_truck_id, int fk_driver_id,
			String start_location, String start_date, String time_of_start, String destination, int estimated_km,
			int starting_meter_reading, int load_quantity, double freight, int closing_meter_reading, int checked_kms
	// String trip_status
	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.updateTripDetails(trip_id, fk_truck_id, fk_driver_id, start_location,
					start_date, time_of_start, destination, estimated_km, starting_meter_reading, load_quantity,
					freight, closing_meter_reading, checked_kms

			// trip_status
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getCloseTripDetails(int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getCloseTripDetails(fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseTripDetails(int trip_id,int closing_meter_reading,
			int checked_kms,String closing_meter_reading_image_bytes_string,
			String closing_meter_reading_image_type,double freight) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.updateCloseTripDetails(trip_id,closing_meter_reading, checked_kms,
					closing_meter_reading_image_bytes_string,closing_meter_reading_image_type,freight);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getTripByDate(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getTripByDate(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addStorageTripDetails(int fk_truck_id, int fk_driver_id, String start_location,
			String start_date,  String destination, int estimated_km, int starting_meter_reading,
			int load_quantity, double freight,double advance

	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.addStorageTripDetails(fk_truck_id, fk_driver_id, start_location, start_date,
					 destination, estimated_km, starting_meter_reading, load_quantity, freight, advance

			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getStorageTripDetails(int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getStorageTripDetails(fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateStorageTripDetails(int trip_id, int fk_truck_id, int fk_driver_id,
			String start_location, String start_date, String time_of_start, String destination, int estimated_km,
			int starting_meter_reading, int load_quantity, double freight, int closing_meter_reading, int checked_kms
	// String trip_status
	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.updateStorageTripDetails(trip_id, fk_truck_id, fk_driver_id, start_location,
					start_date, time_of_start, destination, estimated_km, starting_meter_reading, load_quantity,
					freight, closing_meter_reading, checked_kms

			// trip_status
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getCloseStorageTripDetails(int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getCloseStorageTripDetails(fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseStorageTripDetails(int trip_id, int closing_meter_reading,
			int checked_kms) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.updateCloseStorageTripDetails(trip_id, closing_meter_reading, checked_kms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getStorageTripByDate(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getStorageTripByDate(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addSellerTripDetails(int fk_truck_id, int fk_driver_id, String start_location,
			String start_date,  String destination, int estimated_km, int starting_meter_reading,
			int load_quantity, double freight, double advance

	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.addSellerTripDetails(fk_truck_id, fk_driver_id, start_location, start_date,
					 destination, estimated_km, starting_meter_reading, load_quantity, freight, advance

			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getSellerTripDetails(int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getSellerTripDetails(fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateSellerTripDetails(int trip_id, int fk_truck_id, int fk_driver_id,
			String start_location, String start_date, String time_of_start, String destination, int estimated_km,
			int starting_meter_reading, int load_quantity, double freight, int closing_meter_reading, int checked_kms
	// String trip_status
	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.updateSellerTripDetails(trip_id, fk_truck_id, fk_driver_id, start_location,
					start_date, time_of_start, destination, estimated_km, starting_meter_reading, load_quantity,
					freight, closing_meter_reading, checked_kms

			// trip_status
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getCloseSellerTripDetails(int fk_truck_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getCloseSellerTripDetails(fk_truck_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateCloseSellerTripDetails(int trip_id, int closing_meter_reading,
			int checked_kms) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.updateCloseSellerTripDetails(trip_id, closing_meter_reading, checked_kms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getSellerTripByDate(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.getSellerTripByDate(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTripStartedTrucks() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = TripDAO.getTripStartedTrucks();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getTripDriverExpenditure(int fk_driver_id, String date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = TripDAO.getTripDriverExpenditure(fk_driver_id, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTripDriversCloseExpenditure(int trip_id, String dispatch) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = TripDAO.getTripDriversCloseExpenditure(trip_id,dispatch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addTripDriverExpenditure(int trip_id,int fk_driver_id,String dispatch,
		 int transport, int loading,
			int cover_tying, int contonment, int toll_gate, int loading_wage, int unloading_wage, int phone_bills,
			int spares_parts, int puncher, int entry, int return_transport, int return_loading, int return_unloading,
			int others, int balance) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = TripDAO.addTripDriverExpenditure(trip_id, fk_driver_id, dispatch, transport, loading, cover_tying, contonment,
					toll_gate, loading_wage, unloading_wage, phone_bills, spares_parts, puncher, entry,
					return_transport, return_loading, return_unloading, others, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
}
