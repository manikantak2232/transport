package com.pixelbox.exceptions;

import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.pixelbox.dto.BaseResponse;
import com.pixelbox.utils.TransportGlobalErrorMessageMap;;

public class ExceptionHelper {

	/**
	 * @param exceptionLevel
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static ExceptionInfo prepareExceptionInfo(ExceptionLevelEnum exceptionLevel, int errorCode) {
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setExceptionLevel(exceptionLevel);
		exceptionInfo.setErrorCode(errorCode);
		exceptionInfo.setErrorMessage(TransportGlobalErrorMessageMap.getMessage(errorCode));
		exceptionInfo.setLogged(Boolean.TRUE);
		
		return exceptionInfo;
	}
	
	/**
	 * @param exceptionLevel
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static ExceptionInfo prepareExceptionInfo(ExceptionLevelEnum exceptionLevel, int errorCode, String errorMessage) {
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setExceptionLevel(exceptionLevel);
		exceptionInfo.setErrorCode(errorCode);
		exceptionInfo.setErrorMessage(errorMessage);
		exceptionInfo.setLogged(Boolean.TRUE);
		
		return exceptionInfo;
	}
	
	
	public static ExceptionInfo prepareExceptionInfo(ExceptionLevelEnum exceptionLevel, int errorCode,Exception e) {
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setExceptionLevel(exceptionLevel);
		exceptionInfo.setErrorCode(errorCode);
		
		if(null != e){
			exceptionInfo.setErrorMessage(e.getMessage());
		}
		exceptionInfo.setLogged(Boolean.TRUE);
		
		return exceptionInfo;
	}

	public static HashMap<String, Object> prepareExceptionInfo(Exception e, Logger l){
	
		l.error(e.getMessage());
		return prepareExceptionInfo(e);
	}

	public static HashMap<String, Object> prepareExceptionInfo(Exception e){
		HashMap<String,Object> responseMap = new HashMap<String,Object>(); 
		
		if(e instanceof TransportException){
			ExceptionInfo exceptionInfo =  ((TransportException) e).getExceptionInfo();
			
			responseMap.put("errorCode", exceptionInfo.getErrorCode());
			responseMap.put("result", false);
			responseMap.put("errorMessage", exceptionInfo.getErrorMessage());
		}else if (e instanceof SQLException)
		{
			responseMap.put("errorCode", TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
			responseMap.put("result", false);
			responseMap.put("errorMessage", TransportGlobalErrorMessageMap.getMessage(TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR));
		}else{
			// Exception
			responseMap.put("errorCode", TransportGlobalErrorMessageMap.EXCEPTION);
			responseMap.put("result", false);
			responseMap.put("errorMessage", TransportGlobalErrorMessageMap.getMessage(TransportGlobalErrorMessageMap.EXCEPTION));
		}
		return responseMap;
	}
	
	public static void prepareSQLException(BaseResponse baseResponse){
		baseResponse.setResult(false);
		baseResponse.setErrorMessage(TransportGlobalErrorMessageMap.getMessage(TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR));
		baseResponse.setErrorCode(TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR);
	}
	
	
	/**
	 * prepareResponse set Base Response object with result, Error code and error message
	 * @param responseToSend
	 * @param Exception
	 */
	public static void prepareResponse(BaseResponse responseToSend, Exception e){
		if(null == responseToSend){
			responseToSend = new BaseResponse();
		}
		responseToSend.setResult(false);
		
		if (e instanceof SQLException)
		{
			responseToSend.errorCode = TransportGlobalErrorMessageMap.DB_EXECUTION_ERROR;
			//responseToSend.errorMessage = DHGlobalErrorMessageMap.getMessage(DHGlobalErrorMessageMap.DB_EXECUTION_ERROR);
			responseToSend.errorMessage = e.getMessage();
		}else{
			responseToSend.errorCode = TransportGlobalErrorMessageMap.EXCEPTION;
			//responseToSend.errorMessage = DHGlobalErrorMessageMap.getMessage(DHGlobalErrorMessageMap.EXCEPTION);
			responseToSend.errorMessage = e.getMessage();
		}
	}
}
