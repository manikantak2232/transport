package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.AccountsDAO;

public class AccountsBL {
	public static HashMap<String, Object> addCashAndBankAccount(String account_name,String alias_name,String group_name) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addCashAndBankAccount(account_name,alias_name,group_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addAccountName(String account_name,String alias_name,String group_name) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addAccountName(account_name,alias_name,group_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addPayment(String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addPayment(date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
					amount,remarks,truck_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addReceipt(String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addReceipt(date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
					amount,remarks,truck_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addIncome(String date,String sales_and_income_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addIncome(date,sales_and_income_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
					amount,remarks,truck_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addExpenditure(String date,String purchase_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addExpenditure(date,purchase_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
					amount,remarks,truck_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getLedger(String account_name,String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getLedger(account_name,lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTruckProfitAndLoss(String truck_number,String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getTruckProfitAndLoss(truck_number,lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getCashAndBankName() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getCashAndBankName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getPurchaseAccountNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getPurchaseAccountNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getSalesAndIncomeAccontNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getSalesAndIncomeAccontNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getVendorAndCustomerAccountNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getVendorAndCustomerAccountNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getVehicleExpensesAccountNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getVehicleExpensesAccountNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAccountGroupNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getAccountGroupNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAllAccounts() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getAllAccounts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrailBalance() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getTrailBalance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getBalanceSheet() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getBalanceSheet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getBalanceSheet2(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getBalanceSheet2(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTrailBalance2(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getTrailBalance2(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getProfitAndLoss(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getProfitAndLoss(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAccountTreeNames() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getAccountTreeNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getOpeningBalance(String account_name,String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getOpeningBalance(account_name,lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getPayment(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getPayment(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getReceipt(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getReceipt(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getExpenditure(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getExpenditure(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getIncome(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getIncome(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updatePayment(int pk_payment_id,String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.updatePayment(pk_payment_id,date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
					amount,remarks,truck_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateReceipt(int pk_receipt_id,String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.updateReceipt(pk_receipt_id,date,cash_and_bank_ac_name,narration,cheque_no,w_b_no,customer_ac_name,
					amount,remarks,truck_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateIncome(int pk_income_id,String date,String sales_and_income_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.updateIncome(pk_income_id,date,sales_and_income_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
					amount,remarks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateExpenditure(int pk_expenditure_id,String date,String purchase_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.updateExpenditure(pk_expenditure_id,date,purchase_ac_name,narration,invoice_no,w_b_no,customer_ac_name,
					amount,remarks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> deletePayment(int pk_payment_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.deletePayment(pk_payment_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> deleteReceipt(int pk_receipt_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.deleteReceipt(pk_receipt_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> deleteIncome(int pk_income_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.deleteIncome(pk_income_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> deleteExpenditure(int pk_expenditure_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.deleteExpenditure(pk_expenditure_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addOpeningBalance(String date,String account_name,double debit,double credit) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addOpeningBalance(date,account_name,debit,credit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getOpeningBalanceForTb(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getOpeningBalanceForTb(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getTodayPaymentReceiptAmount(String lower_date,String upper_date) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getTodayPaymentReceiptAmount(lower_date,upper_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addJournalEntry(String date,String dr_account,String cr_account,double amount,String narration) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addJournalEntry(date,dr_account,cr_account,amount,narration);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getOpeningBalances() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getOpeningBalances();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateOpeningBalance(int pk_opening_balance_id,String date,String account_name,double debit,double credit) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.updateOpeningBalance(pk_opening_balance_id,date,account_name,debit,credit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> deleteOpeningBalance(int opening_balance_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.deleteOpeningBalance(opening_balance_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> addAccountGroup(String group_name,String group_type) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.addAccountGroup(group_name,group_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> getAccountGroups() {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.getAccountGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> updateAccountGroup(int pk_account_group_id,String group_name,String group_type) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.updateAccountGroup(pk_account_group_id,group_name,group_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> deleteAccountGroup(int pk_account_group_id) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = AccountsDAO.deleteAccountGroup(pk_account_group_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
}