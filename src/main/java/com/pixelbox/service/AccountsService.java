package com.pixelbox.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.pixelbox.bl.AccountsBL;

@Path("/accounts")
public class AccountsService {
	final static Logger log = Logger.getLogger(DriverService.class);

	@POST
	@Path("/cashAndBank/add")
	@Produces(MediaType.APPLICATION_JSON)
	
	
	public HashMap<String, Object> addCashAndBankAccount(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String account_name = requestParameters.get("account_name").toString();
		String alias_name = requestParameters.get("alias_name").toString();
		String group_name = requestParameters.get("group_name").toString();
		
		responseParameters = AccountsBL.addCashAndBankAccount(account_name,alias_name,group_name);

		return responseParameters;
	}
	
	@POST
	@Path("/account_name/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addAccountName(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String account_name = requestParameters.get("account_name").toString();
		String alias_name = requestParameters.get("alias_name").toString();
		String group_name = requestParameters.get("group_name").toString();
		
		responseParameters = AccountsBL.addAccountName(account_name,alias_name,group_name);

		return responseParameters;
	}
	
	@POST
	@Path("/payment/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addPayment(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String cash_and_bank_ac_name = requestParameters.get("cash_and_bank_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String cheque_no = requestParameters.get("cheque_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
//		String reference = requestParameters.get("reference").toString();
		String remarks = requestParameters.get("remarks").toString();
		String truck_no =requestParameters.get("truck_no").toString();
		
		responseParameters = AccountsBL.addPayment(date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
				amount,remarks,truck_no);

		return responseParameters;
	}
	
	@POST
	@Path("/receipt/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addReceipt(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String cash_and_bank_ac_name = requestParameters.get("cash_and_bank_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String cheque_no = requestParameters.get("cheque_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
//		String reference = requestParameters.get("reference").toString();
		String remarks = requestParameters.get("remarks").toString();
		String truck_no = requestParameters.get("truck_no").toString();
		
		responseParameters = AccountsBL.addReceipt(date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
				amount,remarks,truck_no);

		return responseParameters;
	}
	
	@POST
	@Path("/income/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addIncome(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String sales_and_income_ac_name = requestParameters.get("sales_and_income_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String invoice_no = requestParameters.get("invoice_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
//		String reference = requestParameters.get("reference").toString();
		String remarks = requestParameters.get("remarks").toString();
		String truck_no =requestParameters.get("truck_no").toString();
		
		responseParameters = AccountsBL.addIncome(date,sales_and_income_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
				amount,remarks,truck_no);

		return responseParameters;
	}
	
	@POST
	@Path("/expenditure/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String purchase_ac_name = requestParameters.get("purchase_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String invoice_no = requestParameters.get("invoice_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
//		String reference = requestParameters.get("reference").toString();
		String remarks = requestParameters.get("remarks").toString();
		String truck_no =requestParameters.get("truck_no").toString();
		
		responseParameters = AccountsBL.addExpenditure(date,purchase_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
				amount,remarks,truck_no);

		return responseParameters;
	}
	
	@POST
	@Path("/ledger/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLedger(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String account_name = requestParameters.get("account_name").toString();
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getLedger(account_name,lower_date,upper_date);

		return responseParameters;
	}
	
	@GET
	@Path("/cash_and_bank_name/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getCashAndBankName() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getCashAndBankName();

		return responseParameters;
	}
	
	@GET
	@Path("/purchase_account_names/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getPurchaseAccountNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getPurchaseAccountNames();

		return responseParameters;
	}
	
	@GET
	@Path("/sales_and_income_account_names/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSalesAndIncomeAccontNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getSalesAndIncomeAccontNames();

		return responseParameters;
	}
	
	@GET
	@Path("/vendor_and_customer_account_names/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getVendorAndCustomerAccountNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getVendorAndCustomerAccountNames();

		return responseParameters;
	}
	
	@GET
	@Path("/vehicle_expenses_account_names/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getVehicleExpensesAccountNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getVehicleExpensesAccountNames();

		return responseParameters;
	}
	
	@GET
	@Path("/account_group_names/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAccountGroupNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getAccountGroupNames();

		return responseParameters;
	}
	
	@GET
	@Path("/all_accounts/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAllAccounts() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getAllAccounts();

		return responseParameters;
	}
	
	@GET
	@Path("/trail_balance/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrailBalance() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getTrailBalance();

		return responseParameters;
	}
	
	@GET
	@Path("/balance_sheet/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBalanceSheet() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getBalanceSheet();

		return responseParameters;
	}
	
	@POST
	@Path("/balance_sheet2/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getBalanceSheet2(HashMap<String,Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = AccountsBL.getBalanceSheet2(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/trail_balance2/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTrailBalance2(HashMap<String,Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = AccountsBL.getTrailBalance2(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/profit_and_loss/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getProfitAndLoss(HashMap<String,Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();

		responseParameters = AccountsBL.getProfitAndLoss(lower_date,upper_date);

		return responseParameters;
	}
	
	@GET
	@Path("/account_tree_names/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAccountTreeNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getAccountTreeNames();

		return responseParameters;
	}
	
	@POST
	@Path("/opening_balance/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOpeningBalance(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String account_name = requestParameters.get("account_name").toString();
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getOpeningBalance(account_name,lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/payment/get")
	@Produces(MediaType.APPLICATION_JSON)
	@RequiresRoles("admin")
	
	public HashMap<String, Object> getPayment(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getPayment(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/receipt/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getReceipt(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getReceipt(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/expenditure/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getExpenditure(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/income/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getIncome(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getIncome(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/payment/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updatePayment(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_payment_id = Integer.parseInt(requestParameters.get("pk_payment_id").toString());
		String date = requestParameters.get("date").toString();
		String cash_and_bank_ac_name = requestParameters.get("cash_and_bank_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String cheque_no = requestParameters.get("cheque_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String remarks = requestParameters.get("remarks").toString();
		String truck_no = requestParameters.get("truck_no").toString();
		
		responseParameters = AccountsBL.updatePayment(pk_payment_id,date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
				amount,remarks,truck_no);

		return responseParameters;
	}
	
	@POST
	@Path("/receipt/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateReceipt(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_receipt_id = Integer.parseInt(requestParameters.get("pk_receipt_id").toString());
		String date = requestParameters.get("date").toString();
		String cash_and_bank_ac_name = requestParameters.get("cash_and_bank_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String cheque_no = requestParameters.get("cheque_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String remarks = requestParameters.get("remarks").toString();
		String truck_no = requestParameters.get("truck_no").toString();
		
		responseParameters = AccountsBL.updateReceipt(pk_receipt_id,date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
				amount,remarks,truck_no);

		return responseParameters;
	}
	
	@POST
	@Path("/income/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateIncome(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_income_id = Integer.parseInt(requestParameters.get("pk_income_id").toString());
		String date = requestParameters.get("date").toString();
		String sales_and_income_ac_name = requestParameters.get("sales_and_income_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String invoice_no = requestParameters.get("invoice_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String remarks = requestParameters.get("remarks").toString();
		
		responseParameters = AccountsBL.updateIncome(pk_income_id,date,sales_and_income_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
				amount,remarks);

		return responseParameters;
	}
	
	@POST
	@Path("/expenditure/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_expenditure_id = Integer.parseInt(requestParameters.get("pk_expenditure_id").toString());
		String date = requestParameters.get("date").toString();
		String purchase_ac_name = requestParameters.get("purchase_ac_name").toString();
		String narration = requestParameters.get("narration").toString();
		String invoice_no = requestParameters.get("invoice_no").toString();
		String w_b_no = requestParameters.get("w_b_no").toString();
		String customer_ac_name =requestParameters.get("customer_ac_name").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String remarks = requestParameters.get("remarks").toString();
		
		responseParameters = AccountsBL.updateExpenditure(pk_expenditure_id,date,purchase_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
				amount,remarks);

		return responseParameters;
	}
	
	@POST
	@Path("/payment/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deletePayment(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_payment_id = Integer.parseInt(requestParameters.get("pk_payment_id").toString());
		
		responseParameters = AccountsBL.deletePayment(pk_payment_id);

		return responseParameters;
	}
	
	@POST
	@Path("/receipt/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteReceipt(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_receipt_id = Integer.parseInt(requestParameters.get("pk_receipt_id").toString());
		
		responseParameters = AccountsBL.deleteReceipt(pk_receipt_id);

		return responseParameters;
	}
	
	@POST
	@Path("/income/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteIncome(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_income_id = Integer.parseInt(requestParameters.get("pk_income_id").toString());
		
		responseParameters = AccountsBL.deleteIncome(pk_income_id);

		return responseParameters;
	}
	
	@POST
	@Path("/expenditure/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteExpenditure(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_expenditure_id = Integer.parseInt(requestParameters.get("pk_expenditure_id").toString());
		
		responseParameters = AccountsBL.deleteExpenditure(pk_expenditure_id);

		return responseParameters;
	}
	
	@POST
	@Path("/opening_balance/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addOpeningBalance(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String account_name = requestParameters.get("account_name").toString();

		double debit = Double.parseDouble(requestParameters.get("debit").toString());
		double credit = Double.parseDouble(requestParameters.get("credit").toString());

		
		responseParameters = AccountsBL.addOpeningBalance(date,account_name,debit,credit);

		return responseParameters;
	}
	
	@POST
	@Path("/opening_balance/tb/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOpeningBalanceForTb(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getOpeningBalanceForTb(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/payment/receipt/amount/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTodayPaymentReceiptAmount(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getTodayPaymentReceiptAmount(lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/journal/entry/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addJournalEntry(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String dr_account = requestParameters.get("dr_account").toString();
		String cr_account = requestParameters.get("cr_account").toString();
		double amount = Double.parseDouble(requestParameters.get("amount").toString());
		String narration = requestParameters.get("narration").toString();
		
		responseParameters = AccountsBL.addJournalEntry(date,dr_account,cr_account,amount,narration);

		return responseParameters;
	}
	
	@GET
	@Path("/opening_balances/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getOpeningBalances() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getOpeningBalances();

		return responseParameters;
	}
	
	@POST
	@Path("/opening_balance/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateOpeningBalance(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String date = requestParameters.get("date").toString();
		String account_name = requestParameters.get("account_name").toString();
		double debit = Double.parseDouble(requestParameters.get("debit").toString());
		int pk_opening_balance_id = Integer.parseInt(requestParameters.get("pk_opening_balance_id").toString());
		double credit = Double.parseDouble(requestParameters.get("credit").toString());

		
		responseParameters = AccountsBL.updateOpeningBalance(pk_opening_balance_id,date,account_name,debit,credit);

		return responseParameters;
	}
	
	@POST
	@Path("/opening_balance/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteOpeningBalance(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int opening_balance_id = Integer.parseInt(requestParameters.get("opening_balance_id").toString());
		
		responseParameters = AccountsBL.deleteOpeningBalance(opening_balance_id);

		return responseParameters;
	}
	
	@POST
	@Path("/truck/pl/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getTruckProfitAndLoss(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String truck_number = requestParameters.get("truck_number").toString();
		String lower_date = requestParameters.get("lower_date").toString();
		String upper_date = requestParameters.get("upper_date").toString();
		
		responseParameters = AccountsBL.getTruckProfitAndLoss(truck_number,lower_date,upper_date);

		return responseParameters;
	}
	
	@POST
	@Path("/account_group/add")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addAccountGroup(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String group_name = requestParameters.get("group_name").toString();
		String group_type = requestParameters.get("group_type").toString();
		
		responseParameters = AccountsBL.addAccountGroup(group_name,group_type);

		return responseParameters;
	}
	
	@GET
	@Path("/account_group/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getAccountGroups() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		responseParameters = AccountsBL.getAccountGroups();

		return responseParameters;
	}
	
	@POST
	@Path("/account_group/update")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> updateAccountGroup(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		String group_name = requestParameters.get("group_name").toString();
		String group_type = requestParameters.get("group_type").toString();
		int pk_account_group_id = Integer.parseInt(requestParameters.get("pk_account_group_id").toString());

		
		responseParameters = AccountsBL.updateAccountGroup(pk_account_group_id,group_name,group_type);

		return responseParameters;
	}
	
	@POST
	@Path("/account_group/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> deleteAccountGrouo(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		int pk_account_group_id = Integer.parseInt(requestParameters.get("pk_account_group_id").toString());
		
		responseParameters = AccountsBL.deleteAccountGroup(pk_account_group_id);

		return responseParameters;
	}
}
