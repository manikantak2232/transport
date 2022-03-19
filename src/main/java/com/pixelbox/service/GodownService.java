package com.pixelbox.service;


import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.pixelbox.bl.FactoryBL;
import com.pixelbox.bl.GodownBL;

@Path("/godown")
public class GodownService {

	@POST
	@Path("/getDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getselecteddriver(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int age = Integer.parseInt(requestParameters.get("age").toString());
		String firstName = requestParameters.get("firstName").toString();
		String lastName = requestParameters.get("lastName").toString();
		responseParameters = GodownBL.addmanager(age, firstName, lastName);

		return responseParameters;
	}

	@POST
	@Path("/retrive")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateselecteddriver(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String firstName = requestParameters.get("firstName").toString();



		responseParameters = GodownBL.updatemanager( firstName);


		return responseParameters;
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> retriveselecteddriver(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String firstName = requestParameters.get("firstName").toString();
		String lastName = requestParameters.get("lastName").toString();
		int age = Integer.parseInt(requestParameters.get("age").toString());


		responseParameters = GodownBL.retrivemanager( firstName,lastName,age);

		return responseParameters;
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> login(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String firstName = requestParameters.get("Username").toString();
		String lastName = requestParameters.get("Password").toString();
		// age = Integer.parseInt(requestParameters.get("age").toString());


		responseParameters = GodownBL.loged( firstName,lastName);

		return responseParameters;
	}


	@POST
	@Path("/inward/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addInward(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String invoice_number =  requestParameters.get("invoiceNumber").toString();
		String invoice_date = requestParameters.get("invoiceDate").toString();
		String inward_date = requestParameters.get("inwordDate").toString();
		Double quantity = Double.parseDouble( requestParameters.get("quantity").toString());
		String fk_type_of_cement_name =  requestParameters.get("typeOfCement").toString();
		String truck_number =  requestParameters.get("truckNumber").toString();
		String transporter =  requestParameters.get("transporter").toString();
		Double hamali_per_bag = Double.parseDouble( requestParameters.get("hamaliBag").toString());
		Double crossing_quantity = Double.parseDouble( requestParameters.get("crossing_quantity").toString());
		Double direct_quantity = Double.parseDouble( requestParameters.get("direct_quantity").toString());
		String fk_association_name =  requestParameters.get("associationName").toString();
		String fk_unload_location_name =  requestParameters.get("unloadLocationName").toString();
		String fk_action_name = requestParameters.get("actionName").toString();
		String truckNumberImageBytes =  requestParameters.get("truckNumberImageBytes").toString();
		String truckNumberImageType =  requestParameters.get("truckNumberImageType").toString();
		String workDoneImageBytes =  requestParameters.get("workDoneImageBytes").toString();
		String workDoneImageType =  requestParameters.get("workDoneImageType").toString();

		// age = Integer.parseInt(requestParameters.get("age").toString());


		responseParameters =  GodownBL.addInward(invoice_number, invoice_date, inward_date, quantity, fk_type_of_cement_name, 
				truck_number, transporter, hamali_per_bag, fk_association_name, fk_unload_location_name, fk_action_name, 
				truckNumberImageBytes, truckNumberImageType, workDoneImageBytes,workDoneImageType,crossing_quantity,direct_quantity);

		return responseParameters;
	}

	@GET
	@Path("/actions/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getActions() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();



		responseParameters = GodownBL.getActions();

		return responseParameters;

	}

	@GET
	@Path("/typecement/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTypeCement() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();



		responseParameters = GodownBL.getTypeCement();

		return responseParameters;

	}

	@GET
	@Path("/association/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAssociation() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = GodownBL.getAssociation();

		return responseParameters;

	}

	@POST
	@Path("/inward/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getInward(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();

		responseParameters = GodownBL.getInward(upperDate, lowerDate);

		return responseParameters;

	}


	@POST
	@Path ("/inward/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateInward(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  id = Integer.parseInt(requestParameters.get("id").toString());
			String invoice_number =  requestParameters.get("invoice_number").toString();
			String invoice_date = requestParameters.get("invoice_date").toString();
			String inward_date = requestParameters.get("inward_date").toString();
			Double quantity = Double.parseDouble( requestParameters.get("quantity").toString());
			String fk_type_of_cement_name =  requestParameters.get("fk_type_of_cement_name").toString();
			String truck_number =  requestParameters.get("truck_number").toString();
			String transporter =  requestParameters.get("transporter").toString();
			Double hamali_per_bag = Double.parseDouble( requestParameters.get("hamali_per_bag").toString());
			String fk_association_name =  requestParameters.get("fk_association_name").toString();
			String fk_unload_location_name =  requestParameters.get("fk_unload_location_name").toString();
			String fk_action_name =  requestParameters.get("fk_action_name").toString();
			Double crossing_quantity = Double.parseDouble( requestParameters.get("crossing_quantity").toString());
			Double direct_quantity = Double.parseDouble( requestParameters.get("direct_quantity").toString());

			responseParameters =  GodownBL.updateInward(id,invoice_number, invoice_date, inward_date, quantity, 
					fk_type_of_cement_name, truck_number, transporter, hamali_per_bag, fk_association_name, 
					fk_unload_location_name, fk_action_name,crossing_quantity,direct_quantity);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path ("/inward/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteInward(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  id = Integer.parseInt(requestParameters.get("id").toString());

			System.out.println("hi");
			responseParameters =  GodownBL.deleteInward(id);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}
	@GET
	@Path ("/inward/unload")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> unloadInward() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{


			System.out.println("hi");
			responseParameters =  GodownBL.unloadInward();

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path("/inward/get/bill")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBill(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();
		String associationName = requestParameters.get("associationName").toString();
		String unloadLocationName = requestParameters.get("unloadLocationName").toString();

		responseParameters = GodownBL.getBill(upperDate, lowerDate,associationName,unloadLocationName);

		return responseParameters;

	}


	@POST
	@Path("/outwardadd")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addoutward(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String invoice_number = requestParameters.get("invoice_number").toString();
		String customer_name = requestParameters.get("customer_name").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		String  date_of_outward = requestParameters.get("date_of_outward").toString();
		double  quantity = Double.parseDouble(requestParameters.get("quantity").toString());
		String truck_number = requestParameters.get("truck_number").toString();
		String  transporter = requestParameters.get("transporter").toString();
		String  fk_association_id = requestParameters.get("association_name").toString();
		String fk_type_of_cement_id = requestParameters.get("typeofcement").toString();
		String fk_unload_location_id = requestParameters.get("unload_location").toString();
		String  hamali_per_bags = requestParameters.get("hamali_per_bags").toString();
		String   fk_action_id = requestParameters.get("action_name").toString();
		String outwardTruckNumberImageBytes =  requestParameters.get("outwardTruckNumberImageBytes").toString();
		String outwardTtruckNumberImageType =  requestParameters.get("outwardTtruckNumberImageType").toString();
		String outwardWorkDoneImageBytes =  requestParameters.get("outwardWorkDoneImageBytes").toString();
		String outwardWorkDoneImageType =  requestParameters.get("outwardWorkDoneImageType").toString();
		double freight_per_bag = Double.parseDouble( requestParameters.get("freight_per_bag").toString());
		double  distances = Double.parseDouble(requestParameters.get("distances").toString());
		double   received_payment = Double.parseDouble(requestParameters.get("received_payment").toString());


		responseParameters = GodownBL.addoutward(invoice_number, customer_name, invoice_date, date_of_outward,
				quantity,truck_number,transporter,fk_association_id,fk_type_of_cement_id,fk_unload_location_id,hamali_per_bags,
				fk_action_id,outwardTruckNumberImageBytes,outwardTtruckNumberImageType,outwardWorkDoneImageBytes,outwardWorkDoneImageType,
				freight_per_bag,distances,received_payment);

		return responseParameters;
	}

	@POST
	@Path("/outward/hamali/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addoutwardHamali(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String customer_name = requestParameters.get("customer_name").toString();
		String invoice_date = requestParameters.get("invoice_date").toString();
		double  quantity = Double.parseDouble(requestParameters.get("quantity").toString());
		String truck_number = requestParameters.get("truck_number").toString();
		String  transporter = requestParameters.get("transporter").toString();
		String  fk_association_id = requestParameters.get("association_name").toString();
		String fk_type_of_cement_id = requestParameters.get("typeofcement").toString();
		String fk_unload_location_id = requestParameters.get("unload_location").toString();
		String  hamali_per_bags = requestParameters.get("hamali_per_bags").toString();
		String   fk_action_id = requestParameters.get("action_name").toString();
		String outwardTruckNumberImageBytes =  requestParameters.get("outwardTruckNumberImageBytes").toString();
		String outwardTtruckNumberImageType =  requestParameters.get("outwardTtruckNumberImageType").toString();
		String outwardWorkDoneImageBytes =  requestParameters.get("outwardWorkDoneImageBytes").toString();
		String outwardWorkDoneImageType =  requestParameters.get("outwardWorkDoneImageType").toString();

		responseParameters = GodownBL.addOutwardHamali(customer_name, invoice_date,
				quantity,truck_number,transporter,fk_association_id,fk_type_of_cement_id,fk_unload_location_id,hamali_per_bags,
				fk_action_id,outwardTruckNumberImageBytes,outwardTtruckNumberImageType,outwardWorkDoneImageBytes,outwardWorkDoneImageType);

		return responseParameters;
	}

	@POST
	@Path("/outward/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOutward(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();

		responseParameters = GodownBL.getOutward(upperDate, lowerDate);

		return responseParameters;

	}

	@POST
	@Path ("/outward/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateOutward(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_outward_id = Integer.parseInt(requestParameters.get("pk_outward_id").toString());
			String invoice_number = requestParameters.get("invoice_number").toString();
			String customer_name = requestParameters.get("customer_name").toString();
			String invoice_date = requestParameters.get("invoice_date").toString();
			String  date_of_outward = requestParameters.get("date_of_outward").toString();
			double  quantity = Double.parseDouble(requestParameters.get("quantity").toString());
			String truck_number = requestParameters.get("truck_number").toString();
			String  transporter = requestParameters.get("transporter").toString();
			String  fk_association_id = requestParameters.get("association_name").toString();
			String fk_type_of_cement_id = requestParameters.get("typeofcement").toString();
			String fk_unload_location_id = requestParameters.get("unload_location").toString();
			String  hamali_per_bags = requestParameters.get("hamali_per_bags").toString();
			String   fk_action_id = requestParameters.get("action_name").toString();
			double freight_per_bag = Double.parseDouble( requestParameters.get("freight_per_bag").toString());
			double  distances = Double.parseDouble(requestParameters.get("distances").toString());
			double   received_payment = Double.parseDouble(requestParameters.get("received_payment").toString());
			responseParameters = GodownBL.updateOutward(pk_outward_id,invoice_number, customer_name, invoice_date, date_of_outward,
					quantity,truck_number,transporter,fk_association_id,fk_type_of_cement_id,fk_unload_location_id,hamali_per_bags,
					fk_action_id,freight_per_bag,distances,received_payment);
		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path ("/outward/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteOutward(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_outward_id = Integer.parseInt(requestParameters.get("pk_outward_id").toString());

			System.out.println("hi");
			responseParameters =  GodownBL.deleteOutward(pk_outward_id);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@GET
	@Path("/user/login_details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getUserLoginDetails() {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = GodownBL.getUserLoginDetails();

		return responseParameters;
	}

	@POST
	@Path("/hamali/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getHamali(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();
		String associationName =  requestParameters.get("associationName").toString();


		responseParameters = GodownBL.getHamali(upperDate, lowerDate,associationName);

		return responseParameters;

	}
	@POST
	@Path("/bill/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addBill(HashMap<String, Object> requestParameters) {


		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String association_name = requestParameters.get("association_name").toString();
	//	String godown = requestParameters.get("godown").toString();


		String invoiceNumber = requestParameters.get("invoiceNumber").toString();
		double  grandTotal = Double.parseDouble(requestParameters.get("grandTotal").toString());



		responseParameters = GodownBL.addBill(association_name,invoiceNumber,grandTotal);

		return responseParameters;
	}

	@POST
	@Path("/hamali/account/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getHamaliAccount(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();
		String associationName = requestParameters.get("associationName").toString();
		String unloadLocationName = requestParameters.get("unloadLocationName").toString();
		String hamaliType = requestParameters.get("hamaliType").toString();

		responseParameters = GodownBL.getHamaliAccount(upperDate, lowerDate,associationName,unloadLocationName,hamaliType);

		return responseParameters;

	}

	@POST
	@Path("/outward/hamali/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOutwardHamali(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();
		String associationName = requestParameters.get("associationName").toString();
		String unloadLocationName = requestParameters.get("unloadLocationName").toString();
		String hamaliType = requestParameters.get("hamaliType").toString();

		responseParameters = GodownBL.getOutwardHamali(upperDate, lowerDate,associationName,unloadLocationName,hamaliType);

		return responseParameters;

	}

	@GET
	@Path("/hamalitype/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getHamaliType() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();



		responseParameters = GodownBL.getHamaliType();

		return responseParameters;

	}

	@POST
	@Path("/bill/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBillDetails(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();


		String associationName =  requestParameters.get("associationName").toString();


		responseParameters = GodownBL.getBillDetails(associationName);

		return responseParameters;

	}

	@POST
	@Path ("/bill/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateBill(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_id = Integer.parseInt(requestParameters.get("pk_id").toString());
			String invoiceNumber =  requestParameters.get("invoiceNumber").toString();
			String association_name = requestParameters.get("association_name").toString();
			String godown = requestParameters.get("godown").toString();
			String date =  requestParameters.get("date").toString();
			double grand_total = Double.parseDouble( requestParameters.get("grand_total").toString());
			double received_amount = Double.parseDouble( requestParameters.get("received_amount").toString());
			double tds_amount = Double.parseDouble( requestParameters.get("tds_amount").toString());
			String received_date =  requestParameters.get("received_date").toString();


			System.out.println("hi");
			responseParameters =  GodownBL.updateBill(pk_id,invoiceNumber,association_name,godown,date,grand_total,received_amount,tds_amount,received_date);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path ("/bill/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteBill(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_id = Integer.parseInt(requestParameters.get("pk_id").toString());

			System.out.println("hi");
			responseParameters =  GodownBL.deleteBill(pk_id);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path("/hamali/details/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addHamaliDetails(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String  associationName = requestParameters.get("associationName").toString();
		String startDate = requestParameters.get("startDate").toString();
		String endDate = requestParameters.get("endDate").toString();
		double hamali = Double.parseDouble(requestParameters.get("hamali").toString());
		double  fixedExpenses = Double.parseDouble(requestParameters.get("fixedExpenses").toString());
		double  hamaliPsc = Double.parseDouble(requestParameters.get("hamaliPsc").toString());
		double hamaliCon = Double.parseDouble(requestParameters.get("hamaliCon").toString());
		double  serviceCharge = Double.parseDouble(requestParameters.get("serviceCharge").toString());
		double  hamaliTrade = Double.parseDouble(requestParameters.get("hamaliTrade").toString());


		/*String  truck_number_image_url = requestParameters.get("truck_number_image_url").toString();
		String   work_done_image_url = requestParameters.get("work_done_image_url").toString();*/

		/*String outwardTruckNumberImageBytes =  requestParameters.get("outwardTruckNumberImageBytes").toString();
		String outwardTtruckNumberImageType =  requestParameters.get("outwardTtruckNumberImageType").toString();
		String outwardWorkDoneImageBytes =  requestParameters.get("outwardWorkDoneImageBytes").toString();
		String outwardWorkDoneImageType =  requestParameters.get("outwardWorkDoneImageType").toString();
		 */

		responseParameters = GodownBL.addHamaliDetails(associationName, startDate, endDate, hamali,
				fixedExpenses,hamaliPsc,hamaliCon,serviceCharge,hamaliTrade);

		return responseParameters;
	}

	@POST
	@Path("/hamali/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getHamaliDetails(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();


		String associationName =  requestParameters.get("associationName").toString();


		responseParameters = GodownBL.getHamaliDetails(associationName);

		return responseParameters;

	}
	@POST
	@Path ("/hamali/details/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateHamaliDetails(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_id = Integer.parseInt(requestParameters.get("pk_id").toString());
			String  associationName = requestParameters.get("association_name").toString();
			String startDate = requestParameters.get("start_date").toString();
			String endDate = requestParameters.get("end_date").toString();
			double hamali = Double.parseDouble(requestParameters.get("hamali").toString());
			double  fixedExpenses = Double.parseDouble(requestParameters.get("fixed_expenses").toString());
			double  hamaliPsc = Double.parseDouble(requestParameters.get("hamali_psc").toString());
			double hamaliCon = Double.parseDouble(requestParameters.get("hamali_con").toString());
			double  serviceCharge = Double.parseDouble(requestParameters.get("service_charge").toString());
			double  hamaliTrade = Double.parseDouble(requestParameters.get("hamali_trade").toString());


			System.out.println("hi");
			responseParameters =  GodownBL.updateHamaliDetails(pk_id,associationName, startDate, endDate, hamali,
					fixedExpenses,hamaliPsc,hamaliCon,serviceCharge,hamaliTrade);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}


	@POST
	@Path ("/hamali/details/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteHamaliDetails(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_id = Integer.parseInt(requestParameters.get("pk_id").toString());

			System.out.println("hi");
			responseParameters =  GodownBL.deleteHamaliDetails(pk_id);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path("/advancebooking/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addAdvanceBooking(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String invoice_number = requestParameters.get("invoice_number").toString();

		String  date_of_outward = requestParameters.get("date_of_outward").toString();
		double  quantity = Double.parseDouble(requestParameters.get("quantity").toString());
		String truck_number = requestParameters.get("truck_number").toString();
		String  transporter = requestParameters.get("transporter").toString();

		double  hamali_per_bags = Double.parseDouble(requestParameters.get("hamali_per_bags").toString());
		String   fk_action_id = requestParameters.get("action_name").toString();
		/*String  truck_number_image_url = requestParameters.get("truck_number_image_url").toString();
		String   work_done_image_url = requestParameters.get("work_done_image_url").toString();*/

		String outwardTruckNumberImageBytes =  requestParameters.get("outwardTruckNumberImageBytes").toString();
		String outwardTtruckNumberImageType =  requestParameters.get("outwardTtruckNumberImageType").toString();
		String outwardWorkDoneImageBytes =  requestParameters.get("outwardWorkDoneImageBytes").toString();
		String outwardWorkDoneImageType =  requestParameters.get("outwardWorkDoneImageType").toString();


		responseParameters = GodownBL.addAdvanceBooking(invoice_number,  date_of_outward,quantity,truck_number,
				transporter,hamali_per_bags,fk_action_id,outwardTruckNumberImageBytes,outwardTtruckNumberImageType,
				outwardWorkDoneImageBytes,outwardWorkDoneImageType);

		return responseParameters;
	}

	@POST
	@Path("/advanvebooking/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAdvanceBooking(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();

		responseParameters = GodownBL.getAdvanceBooking(upperDate, lowerDate);

		return responseParameters;

	}


	@POST
	@Path ("/advanvebooking/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateAdvanceBooking(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_advance_outward_id = Integer.parseInt(requestParameters.get("pk_advance_outward_id").toString());
			String invoice_number = requestParameters.get("invoice_number").toString();
			String  date_of_outward = requestParameters.get("date_of_outward").toString();
			double  quantity = Double.parseDouble(requestParameters.get("quantity").toString());
			String truck_number = requestParameters.get("truck_number").toString();
			String  transporter = requestParameters.get("transporter").toString();
			double  hamali_per_bags = Double.parseDouble(requestParameters.get("hamali_per_bags").toString());
			String   fk_action_id = requestParameters.get("action_name").toString();


			// age = Integer.parseInt(requestParameters.get("age").toString());

			System.out.println("hi");
			responseParameters = GodownBL.updateAdvanceBooking(pk_advance_outward_id,invoice_number,  date_of_outward,
					quantity,truck_number,transporter,hamali_per_bags,fk_action_id);
		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}
	@POST
	@Path ("/advanvebooking/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteAdvanceBooking(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_advance_outward_id = Integer.parseInt(requestParameters.get("pk_advance_outward_id").toString());

			responseParameters =  GodownBL.deleteAdvanceBooking(pk_advance_outward_id);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@GET
	@Path("/customers/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCustomers() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = GodownBL.getCustomers();

		return responseParameters;

	}

	@POST
	@Path("/outward/customer/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOutwardCustomer(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();
		String customerName = requestParameters.get("customerName").toString();

		responseParameters = GodownBL.getOutwardCustomer(upperDate, lowerDate,customerName);

		return responseParameters;

	}


	@POST
	@Path ("/outward/customer/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateOutwardCustomer(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_outward_id = Integer.parseInt(requestParameters.get("pk_outward_id").toString());
			String invoice_number = requestParameters.get("invoice_number").toString();
			String customer_name = requestParameters.get("customer_name").toString();
			String invoice_date = requestParameters.get("invoice_date").toString();
			double  quantity = Double.parseDouble(requestParameters.get("quantity").toString());
			String truck_number = requestParameters.get("truck_number").toString();
			String  transporter = requestParameters.get("transporter").toString();
			String  fk_association_id = requestParameters.get("association_name").toString();
			String fk_type_of_cement_id = requestParameters.get("typeofcement").toString();
			String fk_unload_location_id = requestParameters.get("unload_location").toString();
			String   fk_action_id = requestParameters.get("action_name").toString();
			double freight_per_bag = Double.parseDouble( requestParameters.get("freight_per_bag").toString());
			double   received_payment = Double.parseDouble(requestParameters.get("received_payment").toString());
			// age = Integer.parseInt(requestParameters.get("age").toString());

			System.out.println("hi");
			responseParameters = GodownBL.updateOutwardCustomer(pk_outward_id,invoice_number, customer_name, invoice_date, 
					quantity,truck_number,transporter,fk_association_id,fk_type_of_cement_id,fk_unload_location_id,
					fk_action_id,freight_per_bag,received_payment);
		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}


	@POST
	@Path ("/outward/customer/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteOutwardCustomer(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_outward_id = Integer.parseInt(requestParameters.get("pk_outward_id").toString());

			System.out.println("hi");
			responseParameters =  GodownBL.deleteOutwardCustomer(pk_outward_id);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path("/customer/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addCustomer(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String  customer_name = requestParameters.get("customer_name").toString();


		responseParameters = GodownBL.addCustomer(customer_name);

		return responseParameters;
	}
	@POST
	@Path("/dcpending/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDcPending(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String upperDate =  requestParameters.get("upperDate").toString();
		String lowerDate = requestParameters.get("lowerDate").toString();

		responseParameters = GodownBL.getDcPending(upperDate, lowerDate);

		return responseParameters;

	}
	@POST
	@Path ("/dcpending/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateDcPending(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_outward_hamali_id = Integer.parseInt(requestParameters.get("pk_outward_hamali_id").toString());

			String customer_name = requestParameters.get("customer_name").toString();
			String invoice_date = requestParameters.get("invoice_date").toString();

			double  quantity = Double.parseDouble(requestParameters.get("quantity").toString());
			String truck_number = requestParameters.get("truck_number").toString();
			String  transporter = requestParameters.get("transporter").toString();
			String  fk_association_id = requestParameters.get("association_name").toString();
			String fk_type_of_cement_id = requestParameters.get("typeofcement").toString();
			String fk_unload_location_id = requestParameters.get("unload_location").toString();
			String  hamali_per_bags = requestParameters.get("hamali_per_bags").toString();
			String   fk_action_id = requestParameters.get("action_name").toString();

			System.out.println("hi");
			responseParameters = GodownBL.updateDcPending(pk_outward_hamali_id, customer_name, invoice_date,
					quantity,truck_number,transporter,fk_association_id,fk_type_of_cement_id,fk_unload_location_id,hamali_per_bags,
					fk_action_id);
		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}

	@POST
	@Path ("/dcpending/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteDcPending(HashMap<String, Object> requestParameters) {
		System.out.println(requestParameters);
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		try{
			int  pk_outward_hamali_id = Integer.parseInt(requestParameters.get("pk_outward_hamali_id").toString());

			System.out.println("hi");
			responseParameters =  GodownBL.deleteDcPending(pk_outward_hamali_id);

		}catch(Exception e){
			System.out.println(e);
		}

		return responseParameters;
	}
	
	@GET
	@Path("/actions/getInward")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getInwardActions() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();



		responseParameters = GodownBL.getInwardActions();

		return responseParameters;

	}
	
	@GET
	@Path("/actions/getOutward")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOutwardActions() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();



		responseParameters = GodownBL.getOutwardActions();

		return responseParameters;

	}
	
	@POST
	@Path("/stock/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStock(HashMap<String, Object> requestParameters) {		
	
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String associationName = requestParameters.get("associationName").toString();
		String unloadLocationName = requestParameters.get("unloadLocationName").toString();
		String stockType = requestParameters.get("stockType").toString();
		String typeOfCement = requestParameters.get("typeOfCement").toString();

		responseParameters = GodownBL.getStock(associationName,unloadLocationName,stockType,typeOfCement);

		return responseParameters;

	}
	@GET
	@Path("/actions/bill/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getActionsBill() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();



		responseParameters = GodownBL.getActionsBill();

		return responseParameters;

	}
	
	
	@GET
	@Path("/stockType/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getStockType() {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();



		responseParameters = GodownBL.getStockType();

		return responseParameters;

	}
	
	
	@POST
	@Path("/excel/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getExcel(HashMap<String, Object> requestParameters) {	
		
		 String actionList = null;
         String associationType = null;
         String truckNumber = null;
         String material = null ;
         String quantity = null ;
         String dateExcel = null;
         String receiptDate = null;
         String storageLocation = null;
         String dateOfEGP = null;
         String dataExcelFormat =null;
         DateFormat formatt = null;
         Date date  = null ;
         SimpleDateFormat newFormat  = null;
         String dateFormat2 = null;
         DateFormat formatt1 = null;
         Date date1 = null;
         SimpleDateFormat newFormat1 = null;
	
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String association = requestParameters.get("association").toString();
		String billActionType = requestParameters.get("billActionType").toString();
		String excelPath = requestParameters.get("excelPath").toString();
	//	File srcFile = new File(excelPath);
		Workbook wb;
		 try{
			// File srcFile = new File(excelPath);
			 FileInputStream fis = new FileInputStream(excelPath);
			 System.out.println(fis);
			  
			    // Load existing
			    wb = WorkbookFactory.create(fis);
			    
			   /* String start_dt = "MED1560-01/07/2018";
			    System.out.println(start_dt.substring(start_dt.lastIndexOf("-") + 1));
			     formatt = new SimpleDateFormat("dd/MM/yy"); 
			     date = (Date)formatt.parse(start_dt);
			    System.out.println(date);
			     newFormat = new SimpleDateFormat("yyyy-MM-dd");
			    String finalString = newFormat.format(date);
			    System.out.println(finalString);*/
			    
			 
	// FileInputStream input = new FileInputStream(srcFile);
          //  POIFSFileSystem fs = new POIFSFileSystem( input );
           // HSSFWorkbook wb = new HSSFWorkbook(fs);
      //      Workbook wb = WorkbookFactory.create(srcFile);
            Sheet sheet = wb.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            Row row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                
               /* Date now = new Date(formatter.formatCellValue( row.getCell(13)););
                String month = (now.getMonth() + 1);               
                int day = now.getDate();
                if(month < 10) 
                    month = "0" + month;
                if(day < 10) 
                    day = "0" + day;
                 today = now.getFullYear() + '-' + month + '-' + day;
                 
                 System.out.println(today);*/
                if(association.equals("zuari" ) && billActionType.equals("Inward")){
                    actionList = billActionType;
                    associationType = association;
                   truckNumber = formatter.formatCellValue( row.getCell(4));
                    material =  formatter.formatCellValue( row.getCell(9));
                    quantity =  formatter.formatCellValue( row.getCell(11));
                   dateExcel =  formatter.formatCellValue( row.getCell(13));
                   formatt = new SimpleDateFormat("MM/dd/yy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                   storageLocation =  formatter.formatCellValue( row.getCell(15));
                   dateFormat2 =  formatter.formatCellValue( row.getCell(16));
                   formatt1 = new SimpleDateFormat("MM/dd/yy"); 
   			    date1 = (Date)formatt1.parse(dateFormat2);
   			    newFormat1 = new SimpleDateFormat("yyyy-MM-dd");
   			    dateOfEGP = newFormat.format(date1);
   			    
   			   
                   }
               
               if(association.equals("zuari" ) && billActionType.equals("Outward")){
                    actionList = billActionType;
                    associationType = association;
                   truckNumber = formatter.formatCellValue( row.getCell(32));
                    material =  formatter.formatCellValue( row.getCell(24));
                    quantity =  formatter.formatCellValue( row.getCell(26));
                   dateExcel =  formatter.formatCellValue( row.getCell(5));
                   formatt = new SimpleDateFormat("dd.MM.yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                   storageLocation =  formatter.formatCellValue( row.getCell(9));
                   dateFormat2 =  formatter.formatCellValue( row.getCell(3));
                   formatt1 = new SimpleDateFormat("dd.MM.yyyy"); 
   			    date1 = (Date)formatt1.parse(dateFormat2);
   			    newFormat1 = new SimpleDateFormat("yyyy-MM-dd");
   			    dateOfEGP = newFormat.format(date1);
   			   }
               
               if(association.equals("bharathi" ) && billActionType.equals("Outward")){
                    actionList = billActionType;
                    associationType = association;
                   truckNumber = formatter.formatCellValue( row.getCell(16));
                    material =  formatter.formatCellValue( row.getCell(10));
                    quantity =  formatter.formatCellValue( row.getCell(17));
                   dateExcel =  formatter.formatCellValue( row.getCell(5));
                   formatt = new SimpleDateFormat("dd.MM.yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                   }
               
               if(association.equals("chettinad" ) && billActionType.equals("Outward")){
                    actionList = billActionType;
                    associationType = association;
                   truckNumber = formatter.formatCellValue( row.getCell(4));
                    material =  formatter.formatCellValue( row.getCell(2));
                    quantity =  formatter.formatCellValue( row.getCell(5));
                   dateExcel =  formatter.formatCellValue( row.getCell(0));
                   dataExcelFormat = dateExcel.substring(dateExcel.lastIndexOf("-") + 1);
                   formatt = new SimpleDateFormat("dd/MM/yyyy"); 
   			    date = (Date)formatt.parse(dataExcelFormat);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                   
   			   }
               
               if(association.equals("anjani" ) && billActionType.equals("Inward")){
                    actionList = billActionType;
                    associationType = association;
                   truckNumber = formatter.formatCellValue( row.getCell(15));
                    material =  formatter.formatCellValue( row.getCell(5));
                    quantity =  formatter.formatCellValue( row.getCell(4));
                   dateExcel =  formatter.formatCellValue( row.getCell(0));
                   formatt = new SimpleDateFormat("dd/MM/yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                   
   			   }
               
               if(association.equals("anjani" ) && billActionType.equals("Outward")){
                    actionList = billActionType;
                    associationType = association;
                   truckNumber = formatter.formatCellValue( row.getCell(14));
                    material =  formatter.formatCellValue( row.getCell(5));
                    quantity =  formatter.formatCellValue( row.getCell(4));
                   dateExcel =  formatter.formatCellValue( row.getCell(0));
                   formatt = new SimpleDateFormat("dd/MM/yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                   
   			   }
               if(association.equals("jsw" ) && billActionType.equals("Inward")){
                   actionList = billActionType;
                   associationType = association;
                  truckNumber = formatter.formatCellValue( row.getCell(24));
                   material =  formatter.formatCellValue( row.getCell(17));
                   quantity =  formatter.formatCellValue( row.getCell(12));
                  dateExcel =  formatter.formatCellValue( row.getCell(10));
                  formatt = new SimpleDateFormat("dd-MM-yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                  
   			   }
               
               if(association.equals("jsw" ) && billActionType.equals("Outward")){
                   actionList = billActionType;
                   associationType = association;
                  truckNumber = formatter.formatCellValue( row.getCell(25));
                   material =  formatter.formatCellValue( row.getCell(21));
                   quantity =  formatter.formatCellValue( row.getCell(33));
                  dateExcel =  formatter.formatCellValue( row.getCell(31));
                  formatt = new SimpleDateFormat("dd-MM-yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                  
   			   }
               if(association.equals("deccan" ) && billActionType.equals("Outward")){
                   actionList = billActionType;
                   associationType = association;
                  truckNumber = formatter.formatCellValue( row.getCell(7));
                   material =  formatter.formatCellValue( row.getCell(6));
                   quantity =  formatter.formatCellValue( row.getCell(4));
                  dateExcel =  formatter.formatCellValue( row.getCell(0));
                  formatt = new SimpleDateFormat("dd.MM.yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                  
   			   }
               if(association.equals("ramco" ) && billActionType.equals("Inward")){
                   actionList = billActionType;
                   associationType = association;
                  truckNumber = formatter.formatCellValue( row.getCell(0));
                   material =  formatter.formatCellValue( row.getCell(1));
                   quantity =  formatter.formatCellValue( row.getCell(2));
                  dateExcel =  formatter.formatCellValue( row.getCell(3));
                  formatt = new SimpleDateFormat("dd.MM.yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                  
   			   }
               
               if(association.equals("ramco" ) && billActionType.equals("Outward")){
                   actionList = billActionType;
                   associationType = association;
                  truckNumber = formatter.formatCellValue( row.getCell(0));
                   material =  formatter.formatCellValue( row.getCell(1));
                   quantity =  formatter.formatCellValue( row.getCell(2));
                  dateExcel =  formatter.formatCellValue( row.getCell(3));
                  formatt = new SimpleDateFormat("dd.MM.yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                  
   			   }
               
               if(association.equals("ramco" ) && billActionType.equals("Crossing")){
                   actionList = billActionType;
                   associationType = association;
                  truckNumber = formatter.formatCellValue( row.getCell(0));
                   material =  formatter.formatCellValue( row.getCell(1));
                   quantity =  formatter.formatCellValue( row.getCell(2));
                  dateExcel =  formatter.formatCellValue( row.getCell(3));
                  formatt = new SimpleDateFormat("dd.MM.yyyy"); 
   			    date = (Date)formatt.parse(dateExcel);
   			    newFormat = new SimpleDateFormat("yyyy-MM-dd");
   			    receiptDate = newFormat.format(date);
                  
   			   }
               
            
                
            responseParameters = GodownBL.getExcel(actionList,associationType, truckNumber,material,quantity,receiptDate,storageLocation,dateOfEGP);
    			

            
            }
		  }catch(Exception e){
	            System.out.println(e);
		  }

		return responseParameters;

	}
	
	@POST
	@Path("/inward/get/bill/excel")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBillExcel(HashMap<String, Object> requestParameters) {		

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
        
		String lowerDate = requestParameters.get("lowerDate").toString();
		String upperDate =  requestParameters.get("upperDate").toString();
		String associationName = requestParameters.get("associationName").toString();

		responseParameters = GodownBL.getBillExcel(upperDate,lowerDate,associationName);

		return responseParameters;

	}
	


}
