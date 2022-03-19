package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.DriverDAO;
import com.pixelbox.dao.SignupDAO;
import com.pixelbox.dao.StorageDAO;

public class DriverBL {
	
	public static HashMap<String, Object> addDriverDetails(
			String first_name,
			String middle_name,
			String last_name,
			String date_of_birth,
			String aadhar_card,
			String driving_license,
			String non_transport_license_expiry_date,
			String transport_license_expiry_date,
			String phone_number,
			String address,
			String aadhar_image_bytes_string,
			String aadhar_image_type,
			String license_image_bytes_string,
			String license_image_type
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();		
		try{
			responseParameters = DriverDAO.addDriverDetails( 
					first_name,
					middle_name, 
					last_name, 
					date_of_birth,
					aadhar_card,
					driving_license,
					non_transport_license_expiry_date,
					transport_license_expiry_date,
					phone_number,
					address,
					aadhar_image_bytes_string,
					aadhar_image_type,
					license_image_bytes_string,
					license_image_type
															
												);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getDriverDetails(int fk_driver_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = DriverDAO.getDriverDetails(fk_driver_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getDriverFullDetails() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = DriverDAO.getDriverFullDetails();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}

	public static HashMap<String, Object> getAllDriverDetails() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = DriverDAO.getAllDriverDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}
	
	public static HashMap<String, Object> getAvailableDrivers() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = DriverDAO.getAvailableDrivers();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> getDrivers() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = DriverDAO.getDrivers();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> updateDriverDetails(
			int fk_driver_id,
			String first_name,
			String middle_name,
			String last_name,
			String date_of_birth,
			String aadhar_card,
			String driving_license,
			String non_transport_license_expiry_date,
			String transport_license_expiry_date,
			String phone_number,
			String address,
			String aadhar_image_bytes_string,
			String aadhar_image_type,
			String license_image_bytes_string,
			String license_image_type
			
			) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();		
		try{
			responseParameters = DriverDAO.updateDriverDetails( 
					fk_driver_id,
					first_name,
					middle_name, 
					last_name, 
					date_of_birth,
					aadhar_card,
					driving_license,
					non_transport_license_expiry_date,
				    transport_license_expiry_date,
					phone_number,
					address,
					aadhar_image_bytes_string,
					aadhar_image_type,
					license_image_bytes_string,
					license_image_type
															
												);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getDriverSalarySlip(String lower_date, String upper_date, int fk_driver_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.getDriverSalarySlip(lower_date, upper_date, fk_driver_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getDriverSalary(int fk_driver_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.getDriverSalary(fk_driver_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addDriverSalaryHistory(int fk_driver_id,double no_of_days,double salary_per_day,
			double commission,double short_bags,double cost_per_bag,double short_fuel,
			double cost_per_liter,double total_salary) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.addDriverSalaryHistory(fk_driver_id,no_of_days,salary_per_day,commission,
					short_bags,cost_per_bag,short_fuel,cost_per_liter,total_salary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	
	public static HashMap<String, Object> driverSalaryPay(int dispatch_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.driverSalaryPay(dispatch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getavailableAndEngagedDrivers() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = DriverDAO.getavailableAndEngagedDrivers();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;

	}

	public static HashMap<String, Object> addDriverAllotmentToTruck(

			int fk_truck_id, int fk_driver_id, String driver_allotment_date, int fk_association_id

	) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.addDriverAllotmentToTruck(fk_truck_id, fk_driver_id, driver_allotment_date,
					fk_association_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getDriverAllotmentToTruckByDate(String lower_date, String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.getDriverAllotmentToTruckByDate(lower_date, upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getDriverAllotmentToTruck(int pk_driver_allotment_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.getDriverAllotmentToTruck(pk_driver_allotment_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateDriverAllotmentToTruck(int pk_driver_allotment_id, String driver_allotment_date, 
			int fk_driver_id, int fk_association_id, int driver_status_id) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try {
			responseParameters = DriverDAO.updateDriverAllotmentToTruck(pk_driver_allotment_id, driver_allotment_date,
					fk_driver_id, fk_association_id, driver_status_id
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getDriverBalance(int fk_driver_id) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = DriverDAO.getDriverBalance(fk_driver_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
	
	public static HashMap<String, Object> getAllDriverBalance() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = DriverDAO.getAllDriverBalance();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}

}
