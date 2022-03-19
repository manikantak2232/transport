package com.pixelbox.dto;

/**
 * @author Cheman Shaik
 *
 * HoopResponse is the response envelope class for all responses from services
 */
public class BaseResponse{

	public boolean result = true;
	public Integer errorCode;
	public String errorMessage;
	
	public Object response;

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean success) {
		this.result = success;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	
	
}
