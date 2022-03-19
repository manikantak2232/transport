package com.pixelbox.utils;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class TransportGlobalErrorMessageMap {

	final static Logger log = Logger.getLogger(TransportGlobalErrorMessageMap.class);

	public final static String ERROR_RETRIEVING_ERROR_MESSAGE = "Error getting error message";

	public static void initErrorIDNamesMapping() 
	{
		ErrorIDsVsNames = new HashMap<Integer, String>();

		ErrorIDsVsNames.put(CONNECTION_OBJECT_NULL, 
				CONNECTION_OBJECT_NULL_MESSAGE);
		ErrorIDsVsNames.put(EMPTY_REQUEST_PARAMETERS, 
				EMPTY_REQUEST_PARAMETERS_MESSAGE);
		ErrorIDsVsNames.put(EXCEPTION, 
				EXCEPTION_MESSAGE);
		ErrorIDsVsNames.put(INVALID_CREDENTIALS, 
				INVALID_CREDENTIALS_MESSAGE);
		ErrorIDsVsNames.put(INVALID_EMAIL, 
				INVALID_EMAIL_MESSAGE);
		ErrorIDsVsNames.put(DB_EXECUTION_ERROR, 
				DB_EXECUTION_ERROR_MESSAGE);
		ErrorIDsVsNames.put(INVALID_FORGOT_PASSWORD_RESET_TOKEN, 
				INVALID_FORGOT_PASSWORD_RESET_TOKEN_MESSAGE);
		ErrorIDsVsNames.put(PASSWORD_CONFIRM_PASSWORD_NO_MATCH, 
				PASSWORD_CONFIRM_PASSWORD_NO_MATCH_MESSAGE);
		ErrorIDsVsNames.put(INVALID_SESSION_TOKEN, 
				INVALID_SESSION_TOKEN_MESSAGE);

		ErrorIDsVsNames.put( CONCURRENT_LOGIN_NOT_ALLOWED, 
				CONCURRENT_LOGIN_NOT_ALLOWED_MESSAGE);
		ErrorIDsVsNames.put( NO_USER_DETAILS_FOUND, 
				NO_USER_DETAILS_FOUND_MESSAGE);
		ErrorIDsVsNames.put( NO_LOGGED_IN_USER_FOUND, 
				NO_LOGGED_IN_USER_FOUND_MESSAGE);
		ErrorIDsVsNames.put( YOU_ARE_NOT_AUTHORIZED, 
				YOU_ARE_NOT_AUTHORIZED_MESSAGE);
		ErrorIDsVsNames.put( ERROR_PARSE_EXCEPTION, 
				ERROR_PARSE_EXCEPTION_MESSAGE);

		ErrorIDsVsNames.put( EMPTY_ARRAYLIST,
				EMPTY_ARRAYLIST_MESSAGE);

	}
	//define the common error id here
	public final static int DB_EXECUTION_ERROR = 1030;
	public final static int CONNECTION_OBJECT_NULL = 1031;
	public final static int EMPTY_REQUEST_PARAMETERS = 1017;
	public final static int EXCEPTION = 1005;

	//define the error id  for login here
	public final static int INVALID_CREDENTIALS = 1001;
	public final static int INVALID_EMAIL = 1002;
	public final static int INVALID_FORGOT_PASSWORD_RESET_TOKEN = 1019;
	public final static int PASSWORD_CONFIRM_PASSWORD_NO_MATCH = 1020;
	public final static int INVALID_SESSION_TOKEN = 1016;
	public final static int CONCURRENT_LOGIN_NOT_ALLOWED = 1000;
	public final static int NO_USER_DETAILS_FOUND = 1100;
	public final static int NO_LOGGED_IN_USER_FOUND = 1101;	
	public final static int YOU_ARE_NOT_AUTHORIZED = 401;

	// ERROR CODES FOR KNOWN EXCEPTIONS
	public final static int ERROR_PARSE_EXCEPTION = 4001;
	public final static int SQL_EXCEPTION = 4002;
	
	// ACL(shiro) exceptions
	public final static int UNAUTHORIZED_EXCEPTION = 2001;
	
	// logic errors
	public final static int EMPTY_ARRAYLIST = 6001;

	//define the COMMON error message here
	public final static String DB_EXECUTION_ERROR_MESSAGE = "error in executing your request";
	public final static String CONNECTION_OBJECT_NULL_MESSAGE = "problem acquiring a connection. connection variable null";
	public final static String EMPTY_REQUEST_PARAMETERS_MESSAGE = "the request parameters are empty";
	public final static String EXCEPTION_MESSAGE = "exception occured";

	//define the error message  for login here
	public final static String INVALID_CREDENTIALS_MESSAGE = "Invalid Credentials";
	public final static String INVALID_EMAIL_MESSAGE = "Invalid Email";
	public final static String INVALID_FORGOT_PASSWORD_RESET_TOKEN_MESSAGE = "Invalid fogot password reset token";
	public final static String PASSWORD_CONFIRM_PASSWORD_NO_MATCH_MESSAGE = "Password and Confirm Password does not match";
	public final static String CONCURRENT_LOGIN_NOT_ALLOWED_MESSAGE = "Concurrent Login not allowed";
	public final static String NO_USER_DETAILS_FOUND_MESSAGE = "User details not found";
	public final static String NO_LOGGED_IN_USER_FOUND_MESSAGE = "No Logged-in User Details Found!";
	public final static String YOU_ARE_NOT_AUTHORIZED_MESSAGE = "You are not authorized to do this..!";
	

	public final static String INVALID_SESSION_TOKEN_MESSAGE = "invalid session token";

	//ERROR MESSAGES FOR KNOWN EXCEPTIONS 
	public final static String ERROR_PARSE_EXCEPTION_MESSAGE = "error while parsing a date";
	public final static String SQL_EXCEPTION_MESSAGE = "sql exception occured while processing your request";
	
	// ACL(shiro) exceptions messages
	public final static String UNAUTHORIZED_EXCEPTION_MESSAGE = "Invalid Permissions";

	// logic errors
	public final static String EMPTY_ARRAYLIST_MESSAGE = "Unable to fetch the list for :";
	
	private static HashMap<Integer, String> ErrorIDsVsNames = null; 



	public static String getMessage(int errorID)
	{
		String retMes = "";
		try 
		{
			if(ErrorIDsVsNames == null)
			{
				initErrorIDNamesMapping();
			}
			retMes = ErrorIDsVsNames.get(errorID);
		} 
		catch (Exception e) 
		{
			retMes = null;
			log.error(e.getMessage());
		}
		return retMes;
	}

	
	//non-message mapped global constants
	public final static int USER_LOGIN_SESSION_TIME_OUT_MILLIS = 18000000;

}
