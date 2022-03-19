package com.pixelbox.dao;

import java.awt.Font;
import java.awt.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextArea;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


import com.pixelbox.exceptions.TransportException;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.JDBCConnectionUtils;
import com.pixelbox.utils.StoredProcsMap;

public class AccountsDAO {

	final static Logger log = Logger.getLogger(LoginDAO.class);

	public static HashMap<String, Object> addCashAndBankAccount(String account_name,String alias_name,String group_name
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_CASH_AND_BANK_ACCOUNT));
			callableStatement.setString("account_name", account_name);
			callableStatement.setString("alias_name", alias_name);
			callableStatement.setString("group_name", group_name);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addAccountName(String account_name,String alias_name,String group_name
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		//	String username = (String) responseParameters.get("username");

		/*if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_ACCOUNT_NAME));
			callableStatement.setString("account_name", account_name);
			callableStatement.setString("alias_name", alias_name);
			callableStatement.setString("group_name", group_name);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addPayment(String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_PAYMENT));
			callableStatement.setString("date", date);
			callableStatement.setString("cash_and_bank_ac_name", cash_and_bank_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("cheque_no", cheque_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("voucher_id", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("voucher_id", callableStatement.getString("voucher_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addReceipt(String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_RECEIPT));
			callableStatement.setString("date", date);
			callableStatement.setString("cash_and_bank_ac_name", cash_and_bank_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("cheque_no", cheque_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("voucher_id", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("voucher_id", callableStatement.getString("voucher_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addIncome(String date,String sales_and_income_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
			String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_INCOME));
			callableStatement.setString("date", date);
			callableStatement.setString("sales_and_income_ac_name", sales_and_income_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("invoice_no", invoice_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("voucher_id", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("voucher_id", callableStatement.getString("voucher_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addExpenditure(String date,String purchase_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_EXPENDITURE));
			callableStatement.setString("date", date);
			callableStatement.setString("purchase_ac_name", purchase_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("invoice_no", invoice_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("voucher_id", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("voucher_id", callableStatement.getString("voucher_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}



	public static HashMap<String, Object> getCashAndBankName() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_CASH_AND_BANK_NAME));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountNames=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> acName = new HashMap<String, Object>();
				acName.put("account_name", rset.getString("account_name"));
				accountNames.add(acName);
			}
			responseParameters.put("accountNames", accountNames);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getPurchaseAccountNames() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_PURCHASE_ACCOUNT_NAMES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountNames=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> acName = new HashMap<String, Object>();
				acName.put("account_name", rset.getString("account_name"));
				accountNames.add(acName);
			}
			responseParameters.put("accountNames", accountNames);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getSalesAndIncomeAccontNames() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_SALES_AND_INCOME_ACCOUNT_NAMES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountNames=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> acName = new HashMap<String, Object>();
				acName.put("account_name", rset.getString("account_name"));
				accountNames.add(acName);
			}
			responseParameters.put("accountNames", accountNames);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getVendorAndCustomerAccountNames() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_VENDOR_AND_CUSTOMER_ACCOUNT_NAMES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountNames=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> acName = new HashMap<String, Object>();
				acName.put("account_name", rset.getString("account_name"));
				accountNames.add(acName);
			}
			responseParameters.put("accountNames", accountNames);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getVehicleExpensesAccountNames() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_VEHICLE_EXPENSES_ACCOUNT_NAMES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountNames=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> acName = new HashMap<String, Object>();
				acName.put("account_name", rset.getString("account_name"));
				accountNames.add(acName);
			}
			responseParameters.put("accountNames", accountNames);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAccountGroupNames() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ACCOUNT_GROUP_NAMES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountNames=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> acName = new HashMap<String, Object>();
				acName.put("account_name", rset.getString("account_name"));
				accountNames.add(acName);
			}
			responseParameters.put("accountNames", accountNames);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAllAccounts() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ALL_ACCOUNTS));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountNames=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> acName = new HashMap<String, Object>();
				acName.put("account_name", rset.getString("account_name"));
				accountNames.add(acName);
			}
			responseParameters.put("accountNames", accountNames);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTrailBalance() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRIAL_BALANCE));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> trialBalance=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> balance = new HashMap<String, Object>();
				balance.put("name", rset.getString("name"));
				balance.put("debit", rset.getDouble("debit"));
				balance.put("credit", rset.getDouble("credit"));
				balance.put("group_name", rset.getString("group_name"));
				trialBalance.add(balance);
			}
			/*	trialBalance.sort(Comparator.comparing(
                    m -> m.get("yourKey"), 
                    Comparator.nullsLast(Comparator.naturalOrder()))
               );*/
			responseParameters.put("trialBalance", trialBalance);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getBalanceSheet() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BALANCE_SHEET));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			double pl_debit=0;
			double pl_credit=0;
			double creditors=0;
			double debtors=0;

			ArrayList<HashMap<String,Object>> cashAndBankDebtor=new ArrayList<HashMap<String , Object>>();
			ArrayList<HashMap<String,Object>> cashAndBankCreditor=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {

				if(rset.getString("group_type").equals("income")| rset.getString("group_type").equals("expenditure")){
					pl_debit=pl_debit+rset.getDouble("debit");
					pl_credit=pl_credit+rset.getDouble("credit");
				}else{ 
					if(rset.getString("group_name").equals("Vendor/Customer") ){
						debtors=debtors+rset.getDouble("debit");
						creditors=creditors+rset.getDouble("credit");
					}else{

						if(rset.getString("group_name").equals("Cash & Bank")){
							if(rset.getDouble("debit")!=0){
								HashMap<String, Object> balance = new HashMap<String, Object>();
								balance.put("group_name", rset.getString("group_name"));
								balance.put("name", rset.getString("name"));
								balance.put("debit", rset.getDouble("debit"));
								balance.put("credit", rset.getDouble("credit"));

								cashAndBankDebtor.add(balance);
							}else{
								HashMap<String, Object> balance = new HashMap<String, Object>();
								balance.put("group_name", rset.getString("group_name"));
								balance.put("name", rset.getString("name"));
								balance.put("debit", rset.getDouble("debit"));
								balance.put("credit", rset.getDouble("credit"));

								cashAndBankCreditor.add(balance);
							}

						}

					}
				}

			}
			responseParameters.put("cashAndBankDebtor", cashAndBankDebtor);
			responseParameters.put("cashAndBankCreditor", cashAndBankCreditor);
			responseParameters.put("creditors", creditors);
			responseParameters.put("debtors", debtors);
			responseParameters.put("pl_debit", pl_debit);
			responseParameters.put("pl_credit", pl_credit);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getBalanceSheet2(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_BALANCE_SHEET));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			HashMap<String,ArrayList<HashMap<String,Object>>> sub=new HashMap<String,ArrayList<HashMap<String,Object>>>();

			while (rset.next()) {

				ArrayList<HashMap<String,Object>> small=new ArrayList<HashMap<String , Object>>();
				if(sub.get(rset.getString("group_name"))==null){
					HashMap<String,Object> balanceSheet=new HashMap<String,Object>();

					balanceSheet.put("group_name", rset.getString("group_name"));
					balanceSheet.put("name", rset.getString("name"));
					balanceSheet.put("debit", rset.getDouble("debit"));
					balanceSheet.put("credit", rset.getDouble("credit"));
					balanceSheet.put("group_type", rset.getString("group_type"));

					small.add(balanceSheet);
					sub.put(rset.getString("group_name"), small);

				}else{

					HashMap<String,Object> balanceSheet=new HashMap<String,Object>();

					balanceSheet.put("group_name", rset.getString("group_name"));
					balanceSheet.put("name", rset.getString("name"));
					balanceSheet.put("debit", rset.getDouble("debit"));
					balanceSheet.put("credit", rset.getDouble("credit"));
					balanceSheet.put("group_type", rset.getString("group_type"));

					sub.get(rset.getString("group_name")).add(balanceSheet);
				}
			}

			responseParameters.put("data", sub);

			/*responseParameters.put("creditors", creditors);
			responseParameters.put("debtors", debtors);
			responseParameters.put("pl_debit", pl_debit);
			responseParameters.put("pl_credit", pl_credit);
			responseParameters.put("incomeAndExpAccounts", incomeAndExpAccounts);
			responseParameters.put("drAndCrAccounts", drAndCrAccounts);*/

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	@SuppressWarnings("unused")
	public static HashMap<String, Object> getTrailBalance2(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;
		Graphics g=null;
		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRIAL_BALANCE));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			HashMap<String,ArrayList<HashMap<String,Object>>> sub=new HashMap<String,ArrayList<HashMap<String,Object>>>();

			while (rset.next()) {
				ArrayList<HashMap<String,Object>> small=new ArrayList<HashMap<String , Object>>();
				if(sub.get(rset.getString("group_name"))==null){
					HashMap<String,Object> balanceSheet=new HashMap<String,Object>();

					balanceSheet.put("group_name", rset.getString("group_name"));
					balanceSheet.put("name", rset.getString("name"));
					balanceSheet.put("debit", rset.getDouble("debit"));
					balanceSheet.put("credit", rset.getDouble("credit"));

					small.add(balanceSheet);
					sub.put(rset.getString("group_name"), small);

					/*if ((Double)rset.getDouble("op_debit")!=null) {
						balanceSheet.put("debit", rset.getDouble("debit")+rset.getDouble("op_debit"));
					}else{
						balanceSheet.put("debit", rset.getDouble("debit"));
					}*/

				}else{

					HashMap<String,Object> balanceSheet=new HashMap<String,Object>();

					balanceSheet.put("group_name", rset.getString("group_name"));
					balanceSheet.put("name", rset.getString("name"));
					balanceSheet.put("debit", rset.getDouble("debit"));
					balanceSheet.put("credit", rset.getDouble("credit"));
					sub.get(rset.getString("group_name")).add(balanceSheet);
				}

			}
			//		Map<String, ArrayList<HashMap<String, Object>>> map = new TreeMap<>(sub);
			responseParameters.put("data", sub);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getProfitAndLoss(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		String errorMessage = "";
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_PROFIT_AND_LOSS));

			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> income=new ArrayList<HashMap<String , Object>>();
			ArrayList<HashMap<String,Object>> expenditure=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {

				if(rset.getDouble("credit")!=0){
					HashMap<String, Object> balance = new HashMap<String, Object>();
					balance.put("group_name", rset.getString("group_name"));
					balance.put("name", rset.getString("name"));
					balance.put("debit", rset.getDouble("debit"));
					balance.put("credit", rset.getDouble("credit"));
					income.add(balance);
				}else{
					HashMap<String, Object> balance = new HashMap<String, Object>();
					balance.put("group_name", rset.getString("group_name"));
					balance.put("name", rset.getString("name"));
					balance.put("debit", rset.getDouble("debit"));
					balance.put("credit", rset.getDouble("credit"));
					expenditure.add(balance);
				}

			}
			responseParameters.put("income", income);
			responseParameters.put("expenditure", expenditure);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAccountTreeNames() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ACCOUNT_TREE_NAMES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> groups=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {

				HashMap<String, Object> balance = new HashMap<String, Object>();
				balance.put("group_name", rset.getString("group_name"));
				balance.put("account_names", rset.getString("account_names"));

				groups.add(balance);

			}
			responseParameters.put("groups", groups);

		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();

		}
		finally {

			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getOpeningBalance(String account_name,String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OPENING_BALANCE));
			callableStatement.setString("account_name", account_name);
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("crb", java.sql.Types.DOUBLE);
			callableStatement.registerOutParameter("drb", java.sql.Types.DOUBLE);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> ledgerDetails=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> ledger = new HashMap<String, Object>();
				ledger.put("credit", rset.getDouble("cr"));
				ledger.put("debit", rset.getDouble("dr"));

				ledgerDetails.add(ledger);
			}
			responseParameters.put("ledgerDetails", ledgerDetails);
			responseParameters.put("crb", callableStatement.getDouble("crb"));
			responseParameters.put("drb", callableStatement.getDouble("drb"));
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getLedger(String account_name,String lower_date,String upper_date) throws TransportException, SQLException, ParseException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_LEDGER));
			callableStatement.setString("account_name", account_name);
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("account_credit_balance", java.sql.Types.DOUBLE);
			callableStatement.registerOutParameter("account_debit_balance", java.sql.Types.DOUBLE);
			callableStatement.registerOutParameter("opening_bal_debit", java.sql.Types.DOUBLE);
			callableStatement.registerOutParameter("opening_bal_credit", java.sql.Types.DOUBLE);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> ledgerDetails=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> ledger = new HashMap<String, Object>();
				ledger.put("date", rset.getString("date"));
				ledger.put("narration", rset.getString("narration"));
				ledger.put("credit", rset.getDouble("credit"));
				ledger.put("debit", rset.getDouble("debit"));
				ledger.put("account_id", rset.getInt("account_id"));
				ledger.put("account", rset.getString("account"));
				ledger.put("voucher", rset.getString("voucher"));
				ledger.put("truck_no", rset.getString("truck_no"));

				ledgerDetails.add(ledger);
			}
			responseParameters.put("ledgerDetails", ledgerDetails);
			responseParameters.put("account_credit_balance", callableStatement.getDouble("account_credit_balance"));
			responseParameters.put("account_debit_balance", callableStatement.getDouble("account_debit_balance"));
			responseParameters.put("opening_bal_debit", callableStatement.getDouble("opening_bal_debit"));
			responseParameters.put("opening_bal_credit", callableStatement.getDouble("opening_bal_credit"));
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getPayment(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}

		final Subject currentUser = SecurityUtils.getSubject();

		/*		List<String> lt=new ArrayList<String>();
		lt.add("admin");
		lt.add("maintenance_manager");*/

		//	currentUser.hasRoles(new ArrayList<String>(Arrays.asList("admin","maintenance manager")));

		if(!currentUser.hasRole("admin") && !currentUser.hasRole("maintenance manager")){
			boolean result = false;
			int errorCode = TransportGlobalErrorMessageMap.YOU_ARE_NOT_AUTHORIZED;
			String message = TransportGlobalErrorMessageMap.getMessage(errorCode);				

			responseParameters.put("result", result);
			responseParameters.put("errorCode", errorCode);
			responseParameters.put("message", message);

			return responseParameters;
		};

		/*		if (!Boolean.parseBoolean(responseParameters.get("roleType").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_PAYMENT));
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();

			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> paymentDetails=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> payment = new HashMap<String, Object>();
				payment.put("date", rset.getString("date"));
				payment.put("pk_payment_id", rset.getInt("pk_payment_id"));
				payment.put("narration", rset.getString("narration"));
				payment.put("voucher", rset.getString("voucher"));
				payment.put("w_b_no", rset.getString("w_b_no"));
				payment.put("cash_and_bank_ac_name", rset.getString("cash_and_bank_ac_name"));
				payment.put("cheque_no", rset.getString("cheque_no"));
				payment.put("customer_ac_name", rset.getString("customer_ac_name"));
				payment.put("amount", rset.getDouble("amount"));
				payment.put("remarks", rset.getString("remarks"));
				payment.put("truck_no", rset.getString("truck_no"));

				paymentDetails.add(payment);
			}
			responseParameters.put("paymentDetails", paymentDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getReceipt(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_RECEIPT));
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();

			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> receiptDetails=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> receipt = new HashMap<String, Object>();
				receipt.put("date", rset.getString("date"));
				receipt.put("pk_receipt_id", rset.getInt("pk_receipt_id"));
				receipt.put("narration", rset.getString("narration"));
				receipt.put("w_b_no", rset.getString("w_b_no"));
				receipt.put("voucher", rset.getString("voucher"));
				receipt.put("cash_and_bank_ac_name", rset.getString("cash_and_bank_ac_name"));
				receipt.put("cheque_no", rset.getString("cheque_no"));
				receipt.put("customer_ac_name", rset.getString("customer_ac_name"));
				receipt.put("amount", rset.getDouble("amount"));
				receipt.put("remarks", rset.getString("remarks"));
				receipt.put("truck_no", rset.getString("truck_no"));

				receiptDetails.add(receipt);
			}
			responseParameters.put("receiptDetails", receiptDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getExpenditure(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_EXPENDITURE));
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();

			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> expenditureDetails=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> expenditure = new HashMap<String, Object>();
				expenditure.put("date", rset.getString("date"));
				expenditure.put("pk_expenditure_id", rset.getInt("pk_expenditure_id"));
				expenditure.put("narration", rset.getString("narration"));
				expenditure.put("voucher", rset.getString("voucher"));
				expenditure.put("w_b_no", rset.getString("w_b_no"));
				expenditure.put("purchase_ac_name", rset.getString("purchase_ac_name"));
				expenditure.put("invoice_no", rset.getString("invoice_no"));
				expenditure.put("customer_ac_name", rset.getString("customer_ac_name"));
				expenditure.put("amount", rset.getDouble("amount"));
				expenditure.put("remarks", rset.getString("remarks"));

				expenditureDetails.add(expenditure);
			}
			responseParameters.put("expenditureDetails", expenditureDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getIncome(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_INCOME));
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();

			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> incomeDetails=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> income = new HashMap<String, Object>();
				income.put("date", rset.getString("date"));
				income.put("pk_income_id", rset.getInt("pk_income_id"));
				income.put("narration", rset.getString("narration"));
				income.put("voucher", rset.getString("voucher"));
				income.put("sales_and_income_ac_name", rset.getString("sales_and_income_ac_name"));
				income.put("invoice_no", rset.getString("invoice_no"));
				income.put("w_b_no", rset.getString("w_b_no"));
				income.put("customer_ac_name", rset.getString("customer_ac_name"));
				income.put("amount", rset.getDouble("amount"));
				income.put("remarks", rset.getString("remarks"));

				incomeDetails.add(income);
			}
			responseParameters.put("incomeDetails", incomeDetails);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updatePayment(int pk_payment_id,String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}
		
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_PAYMENT));
			callableStatement.setInt("pk_payment_id", pk_payment_id);
			callableStatement.setString("date", date);
			callableStatement.setString("cash_and_bank_ac_name", cash_and_bank_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("cheque_no", cheque_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateReceipt(int pk_receipt_id,String date,String cash_and_bank_ac_name,String narration,
			String cheque_no,String w_b_no, String customer_ac_name,double amount,String remarks,String truck_no
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}
		
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_RECEIPT));
			callableStatement.setInt("pk_receipt_id", pk_receipt_id);
			callableStatement.setString("date", date);
			callableStatement.setString("cash_and_bank_ac_name", cash_and_bank_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("cheque_no", cheque_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("truck_no", truck_no);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateIncome(int pk_income_id,String date,String sales_and_income_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}
		
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_INCOME));
			callableStatement.setInt("pk_income_id", pk_income_id);
			callableStatement.setString("date", date);
			callableStatement.setString("sales_and_income_ac_name", sales_and_income_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("invoice_no", invoice_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateExpenditure(int pk_expenditure_id,String date,String purchase_ac_name,String narration,
			String invoice_no,String w_b_no, String customer_ac_name,double amount,String remarks
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		
		String username = (String) responseParameters.get("username");

		if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}
		
		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_EXPENDITURE));
			callableStatement.setInt("pk_expenditure_id", pk_expenditure_id);
			callableStatement.setString("date", date);
			callableStatement.setString("purchase_ac_name", purchase_ac_name);
			callableStatement.setString("narration", narration);
			callableStatement.setString("invoice_no", invoice_no);
			callableStatement.setString("w_b_no", w_b_no);
			callableStatement.setString("customer_ac_name", customer_ac_name);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("remarks", remarks);
			callableStatement.setString("username", username);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> deletePayment(int pk_payment_id) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_PAYMENT));
			callableStatement.setInt("payment_id", pk_payment_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteReceipt(int pk_receipt_id) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_RECEIPT));
			callableStatement.setInt("receipt_id", pk_receipt_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteIncome(int pk_income_id) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_INCOME));
			callableStatement.setInt("income_id", pk_income_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteExpenditure(int pk_expenditure_id) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_EXPENDITURE));
			callableStatement.setInt("expenditure_id", pk_expenditure_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addOpeningBalance(String date,String account_name,double debit,double credit
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_OPENING_BALANCE));
			callableStatement.setString("date", date);
			callableStatement.setString("account_name", account_name);
			callableStatement.setDouble("debit", debit);
			callableStatement.setDouble("credit", credit);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getOpeningBalanceForTb(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OPENING_BALANCE_FOR_TB));
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			rset = callableStatement.executeQuery();

			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> openingBlance=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> balance = new HashMap<String, Object>();
				balance.put("date", rset.getString("date"));
				balance.put("pk_opening_balance_id", rset.getInt("pk_opening_balance_id"));
				balance.put("account_name", rset.getString("account_name"));
				balance.put("debit", rset.getDouble("debit"));
				balance.put("credit", rset.getDouble("credit"));
				balance.put("account_status", rset.getInt("account_status"));
				balance.put("group_name", rset.getString("group_name"));

				openingBlance.add(balance);
			}
			responseParameters.put("openingBlance", openingBlance);

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> getTodayPaymentReceiptAmount(String lower_date,String upper_date) throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		/*	if (!Boolean.parseBoolean(responseParameters.get("result").toString())) {
			return responseParameters;
		}*/

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TODAY_PAYMENT_RECEIPT_AMOUNT));
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("payment_amount", java.sql.Types.DOUBLE);
			callableStatement.registerOutParameter("receipt_amount", java.sql.Types.DOUBLE);
			rset = callableStatement.executeQuery();

			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);
			
			responseParameters.put("payment_amount", callableStatement.getDouble("payment_amount"));
			responseParameters.put("receipt_amount", callableStatement.getDouble("receipt_amount"));

		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> addJournalEntry(String date,String dr_account,String cr_account,double amount,String narration) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_JOURNAL_ENTRY));
			callableStatement.setString("date", date);
			callableStatement.setString("dr_account", dr_account);
			callableStatement.setString("cr_account", cr_account);
			callableStatement.setDouble("amount", amount);
			callableStatement.setString("narration", narration);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter("voucher_id", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("voucher_id", callableStatement.getString("voucher_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getOpeningBalances() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_OPENING_BALANCES));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> openingBalanceDetails=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {

				HashMap<String, Object> balance = new HashMap<String, Object>();

				balance.put("date", rset.getString("date"));
				balance.put("account_name", rset.getString("account_name"));
				balance.put("pk_opening_balance_id", rset.getInt("pk_opening_balance_id"));
				balance.put("debit", rset.getDouble("debit"));
				balance.put("credit", rset.getDouble("credit"));

				openingBalanceDetails.add(balance);

			}
			responseParameters.put("openingBalanceDetails", openingBalanceDetails);

		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();

		}
		finally {

			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateOpeningBalance(int pk_opening_balance_id,String date,String account_name,double debit,double credit
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_OPENING_BALANCES));
			callableStatement.setInt("pk_opening_balance_id", pk_opening_balance_id);
			callableStatement.setString("date", date);
			callableStatement.setString("account_name", account_name);
			callableStatement.setDouble("debit", debit);
			callableStatement.setDouble("credit", credit);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteOpeningBalance(int opening_balance_id) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_OPENING_BALANCE));
			callableStatement.setInt("opening_balance_id", opening_balance_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("errorMessage", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getTruckProfitAndLoss(String truck_number,String lower_date,String upper_date) throws TransportException, SQLException, ParseException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_TRUCK_PROFIT_AND_LOSS));
			callableStatement.setString("truck_no", truck_number);
			callableStatement.setString("lower_date", lower_date);
			callableStatement.setString("upper_date", upper_date);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			System.out.println(callableStatement);
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> truckProfitAndLoss=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {
				HashMap<String, Object> truckPL = new HashMap<String, Object>();
				truckPL.put("account_name", rset.getString("account_name"));
				truckPL.put("amount_type", rset.getString("amount_type"));
				truckPL.put("amount", rset.getDouble("amount"));

				truckProfitAndLoss.add(truckPL);
			}
			responseParameters.put("truckProfitAndLoss", truckProfitAndLoss);
		} finally {
			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}
	
	public static HashMap<String, Object> addAccountGroup(String group_name,String group_type) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.ADD_ACCOUNT_GROUP));
			callableStatement.setString("group_name", group_name);
			callableStatement.setString("group_type", group_type);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
				responseParameters.put("voucher_id", callableStatement.getString("voucher_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> getAccountGroups() throws TransportException, SQLException {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();
		String username = (String) responseParameters.get("username");

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.GET_ACCOUNT_GROUPS));

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();
			JDBCConnectionUtils.validateCallableStatement(callableStatement);
			responseParameters.put("result", true);

			ArrayList<HashMap<String,Object>> accountGroups=new ArrayList<HashMap<String , Object>>();
			while (rset.next()) {

				HashMap<String, Object> balance = new HashMap<String, Object>();

				balance.put("account_name", rset.getString("account_name"));
				balance.put("group_type", rset.getString("group_type"));
				balance.put("pk_account_group_id", rset.getInt("pk_account_group_id"));

				accountGroups.add(balance);

			}
			responseParameters.put("accountGroups", accountGroups);

		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();

		}
		finally {

			JDBCConnectionUtils.closeResultSetAndStatement(rset, callableStatement);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> updateAccountGroup(int pk_account_group_id,String group_name,String group_type
			) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.UPDATE_ACCOUNT_GROUP));
			callableStatement.setInt("pk_account_group_id", pk_account_group_id);
			callableStatement.setString("group_name", group_name);
			callableStatement.setString("group_type", group_type);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

	public static HashMap<String, Object> deleteAccountGroup(int pk_account_group_id) throws TransportException, SQLException {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		Connection connection = JDBCConnectionUtils.getJDBCConnection();
		responseParameters.put("result", true);
		CallableStatement callableStatement = null;
		ResultSet rset = null;

		responseParameters = TransportGlobalUtils.shiroUserDetails();

		try {
			callableStatement = connection
					.prepareCall(StoredProcsMap.ProcIDsVsNames.get(StoredProcsMap.DELETE_ACCOUNT_GROUP));
			callableStatement.setInt("account_group_id", pk_account_group_id);

			callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
			callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

			rset = callableStatement.executeQuery();

			if (!callableStatement.getBoolean("outResult")) {
				responseParameters.put("result", false);
				responseParameters.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
				responseParameters.put("message", callableStatement.getString("message"));
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(callableStatement.getString("message"));
				}
				return responseParameters;
			} else {
				responseParameters.put("message", callableStatement.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCConnectionUtils.closeAllDBObjects(rset, callableStatement, connection);
		}

		return responseParameters;
	}

}