package com.pixelbox.bl;
import java.util.HashMap;

import com.pixelbox.dao.FactoryDAO;
import com.pixelbox.dao.GodownDAO;

public class GodownBL {

	public static HashMap<String, Object> addmanager(int age, String firstName, String lastName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addmanager( 
					age,
					firstName, 
					lastName

					);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> updatemanager( String firstName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updatemanager( 

					firstName


					);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> retrivemanager( String firstName, String lastName,int age){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.retrivemanager( 
					firstName, 
					lastName,
					age


					);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> loged( String firstName,String lastName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.loged( 

					firstName,
					lastName


					);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> addInward(String invoice_number,String invoice_date,String inward_date,
			Double quantity,String fk_type_of_cement_name,String truck_number,String transporter,Double hamali_per_bag,
			String fk_association_name,String fk_unload_location_name,String fk_action_name,String truckNumberImageBytes, 
			String truckNumberImageType, String workDoneImageBytes,String workDoneImageType,double crossing_quantity,double direct_quantity){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addInward( invoice_number,
					invoice_date,
					inward_date,
					quantity,
					fk_type_of_cement_name,
					truck_number,
					transporter,
					hamali_per_bag,
					fk_association_name,
					fk_unload_location_name,
					fk_action_name,
					truckNumberImageBytes, 
					truckNumberImageType, 
					workDoneImageBytes,
					workDoneImageType,
					crossing_quantity,
					direct_quantity


					);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getActions(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getActions();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getTypeCement(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getTypeCement();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}


	public static HashMap<String, Object> getAssociation(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getAssociation();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getInward(String upperDate,String lowerDate){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getInward(upperDate,lowerDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> updateInward( int id,String invoice_number,String invoice_date,String inward_date,
			Double quantity,String fk_type_of_cement_name,String truck_number,String transporter,Double hamali_per_bag,
			String fk_association_name,String fk_unload_location_name,String fk_action_name,double crossing_quantity,double direct_quantity){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updateInward( id,
					invoice_number,
					invoice_date,
					inward_date,
					quantity,
					fk_type_of_cement_name,
					truck_number,
					transporter,
					hamali_per_bag,
					fk_association_name,
					fk_unload_location_name,
					fk_action_name,
					crossing_quantity,
					direct_quantity


					);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return responseParameters;

	}

	public static HashMap<String, Object> deleteInward( int id){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.deleteInward( id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> unloadInward(){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			System.out.println("BL");
			responseParameters = GodownDAO.unloadInward();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	public static HashMap<String, Object> getBill(String upperDate,String lowerDate,String associationName,String unloadLocationName ){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getBill(upperDate,lowerDate,associationName,unloadLocationName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> addoutward(String invoice_number,
			String customer_name, String invoice_date, String date_of_outward,
			double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String hamali_per_bags,String  fk_action_id,
			String outwardTruckNumberImageBytes,String outwardTtruckNumberImageType,
			String outwardWorkDoneImageBytes,String outwardWorkDoneImageType,
			double freight_per_bag,double distances,double received_payment) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addoutward (invoice_number, customer_name, invoice_date, date_of_outward,
					quantity,truck_number,transporter,fk_association_id, fk_type_of_cement_id,
					fk_unload_location_id,hamali_per_bags, fk_action_id,outwardTruckNumberImageBytes,outwardTtruckNumberImageType,
					outwardWorkDoneImageBytes,outwardWorkDoneImageType,freight_per_bag,distances,received_payment);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> addOutwardHamali(	String customer_name, String invoice_date,
			double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String hamali_per_bags,String  fk_action_id,
			String outwardTruckNumberImageBytes,String outwardTtruckNumberImageType,
			String outwardWorkDoneImageBytes,String outwardWorkDoneImageType) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addOutwardHamali(customer_name, invoice_date,quantity,truck_number,transporter,
					fk_association_id, fk_type_of_cement_id,
					fk_unload_location_id,hamali_per_bags, fk_action_id,outwardTruckNumberImageBytes,outwardTtruckNumberImageType,
					outwardWorkDoneImageBytes,outwardWorkDoneImageType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getOutward(String upperDate,String lowerDate){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getOutward(upperDate,lowerDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> updateOutward( int pk_outward_id,String invoice_number,
			String customer_name, String invoice_date, String date_of_outward,
			double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String hamali_per_bags,String  fk_action_id ,
			double freight_per_bag,double distances,double received_payment){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updateOutward( pk_outward_id,
					invoice_number, customer_name, invoice_date, date_of_outward,
					quantity,truck_number,transporter,fk_association_id, fk_type_of_cement_id,
					fk_unload_location_id,hamali_per_bags, fk_action_id,
					freight_per_bag,distances,received_payment);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return responseParameters;

	}

	public static HashMap<String, Object> deleteOutward( int pk_outward_id){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.deleteOutward( pk_outward_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getHamali(String upperDate,String lowerDate,String associationName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getHamali(upperDate,lowerDate,associationName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}



	public static HashMap<String, Object> addBill(String association_name,
			String invoiceNumber,double grandTotal
			) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addBill(association_name,invoiceNumber,grandTotal);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getHamaliAccount(String upperDate,String lowerDate,String associationName,String unloadLocationName,String hamaliType ){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getHamaliAccount(upperDate,lowerDate,associationName,unloadLocationName,hamaliType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getOutwardHamali(String upperDate,String lowerDate,String associationName,String unloadLocationName,String hamaliType ){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getOutwardHamali(upperDate,lowerDate,associationName,unloadLocationName,hamaliType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getHamaliType(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getHamaliType();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getBillDetails(String associationName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getBillDetails(associationName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> updateBill(int pk_id,String invoiceNumber,String association_name,
			String godown,String date,double grand_total,double received_amount,double tds_amount,String received_date ){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updateBill( pk_id,
					invoiceNumber,
					association_name,
					godown,
					date,
					grand_total,
					received_amount,
					tds_amount,
					received_date

					);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return responseParameters;

	}

	public static HashMap<String, Object> deleteBill(int pk_id){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.deleteBill( pk_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getUserLoginDetails() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = GodownDAO.getUserLoginDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addHamaliDetails(String associationName,String startDate,String endDate,double hamali,
			double fixedExpenses,double hamaliPsc,double hamaliCon,double serviceCharge,double hamaliTrade ) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addHamaliDetails (associationName, startDate, endDate, hamali,
					fixedExpenses,hamaliPsc,hamaliCon,serviceCharge,hamaliTrade);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getHamaliDetails(String associationName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getHamaliDetails(associationName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	public static HashMap<String, Object> updateHamaliDetails(int pk_id,String associationName,String startDate,String endDate,double hamali,
			double fixedExpenses,double hamaliPsc,double hamaliCon,double serviceCharge,double hamaliTrade  ){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updateHamaliDetails( pk_id,
					associationName, startDate, endDate, hamali,
					fixedExpenses,hamaliPsc,hamaliCon,serviceCharge,hamaliTrade

					);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return responseParameters;

	}


	public static HashMap<String, Object> deleteHamaliDetails(int pk_id){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.deleteHamaliDetails( pk_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> addAdvanceBooking(String invoice_number, String date_of_outward,
			double quantity,  String truck_number,String transporter,double hamali_per_bags,String  fk_action_id,
			String outwardTruckNumberImageBytes, String outwardTtruckNumberImageType,String outwardWorkDoneImageBytes,
			String outwardWorkDoneImageType ) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addAdvanceBooking (invoice_number,  date_of_outward,
					quantity,truck_number,transporter,hamali_per_bags, fk_action_id,outwardTruckNumberImageBytes,
					outwardTtruckNumberImageType,outwardWorkDoneImageBytes,outwardWorkDoneImageType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}

	public static HashMap<String, Object> getAdvanceBooking(String upperDate,String lowerDate){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getAdvanceBooking(upperDate,lowerDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> updateAdvanceBooking( int pk_outward_id,String invoice_number,String date_of_outward,double quantity,
			String truck_number,String transporter, double hamali_per_bags,String  fk_action_id){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updateAdvanceBooking( pk_outward_id,invoice_number,  date_of_outward,quantity,truck_number,transporter,
					hamali_per_bags, fk_action_id);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return responseParameters;

	}
	public static HashMap<String, Object> deleteAdvanceBooking( int pk_advance_outward_id){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.deleteAdvanceBooking(pk_advance_outward_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}


	public static HashMap<String, Object> getCustomers(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getCustomers();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getOutwardCustomer(String upperDate,String lowerDate,String customerName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getOutwardCustomer(upperDate,lowerDate,customerName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> updateOutwardCustomer( int pk_outward_id,String invoice_number,
			String customer_name, String invoice_date, double quantity,  String truck_number,
			String transporter, String fk_association_id,String fk_type_of_cement_id,
			String fk_unload_location_id, String  fk_action_id,
			double freight_per_bag,double received_payment ){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updateOutwardCustomer( pk_outward_id,
					invoice_number, customer_name, invoice_date, 
					quantity,truck_number,transporter,fk_association_id, fk_type_of_cement_id,
					fk_unload_location_id, fk_action_id,freight_per_bag,received_payment);

		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return responseParameters;

	}

	public static HashMap<String, Object> deleteOutwardCustomer( int pk_outward_id){

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.deleteOutwardCustomer( pk_outward_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> addCustomer(String customer_name ) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.addCustomer(customer_name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getDcPending(String upperDate,String lowerDate){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getDcPending(upperDate,lowerDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	public static HashMap<String, Object> updateDcPending( int pk_outward_hamali_id,String customer_name,
			String invoice_date,double quantity,  String truck_number,String transporter,String fk_association_id,
			String fk_type_of_cement_id,String fk_unload_location_id, String hamali_per_bags,String  fk_action_id){
			
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.updateDcPending( pk_outward_hamali_id, customer_name, invoice_date, 
					quantity,truck_number,transporter,fk_association_id, fk_type_of_cement_id,
					fk_unload_location_id,hamali_per_bags, fk_action_id);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return responseParameters;

	}
	public static HashMap<String, Object> deleteDcPending( int pk_outward_hamali_id){
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.deleteDcPending( pk_outward_hamali_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	
	public static HashMap<String, Object> getInwardActions(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getInwardActions();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	public static HashMap<String, Object> getOutwardActions(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getOutwardActions();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	
	public static HashMap<String, Object> getStock(String associationName,String unloadLocationName,String stockType,String typeOfCement ){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getStock(associationName,unloadLocationName,stockType,typeOfCement);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	public static HashMap<String, Object> getStockType(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getStockType();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
	public static HashMap<String, Object> getActionsBill(){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getActionsBill();
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}

	public static HashMap<String, Object> getExcel(String actionList,String associationType,String truckNumber,String material, String quantity,String receiptDate,String storageLocation,String dateOfEGP){
			
			HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			try{
				responseParameters = GodownDAO.getExcel(actionList,associationType, truckNumber,material,quantity,receiptDate,storageLocation,dateOfEGP);
			}catch(Exception e){
				e.printStackTrace();
			}
			return responseParameters;

		}
	     public static HashMap<String, Object> getBillExcel(String upperDate,String lowerDate,String associationName){ 
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			responseParameters = GodownDAO.getBillExcel(upperDate,lowerDate,associationName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;

	}
}
