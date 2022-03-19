package com.pixelbox.exceptions;

import java.util.UUID;

/**
 * ExceptionInfo call contains exceptionID which provides the unique random id
 * for tracking.
 * 
 * 
 * 
 */
public class ExceptionInfo{

	private String exceptionID;
	private int errorCode;
	private String errorMessage;
	private ExceptionLevelEnum exceptionLevel; 
	private Boolean logged;

	public ExceptionInfo() {
		this.exceptionID = getUniqueID();

	}

	public ExceptionInfo(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exceptionID = getUniqueID();

	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setExceptionID(String exceptionID) {
		this.exceptionID = exceptionID;
	}

	public String getExceptionID() {
		return exceptionID;
	}

	public ExceptionLevelEnum getExceptionLevel() {
		return exceptionLevel;
	}

	public void setExceptionLevel(ExceptionLevelEnum exceptionLevel) {
		this.exceptionLevel = exceptionLevel;
	}

	public Boolean getLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}

	public static String getUniqueID() {
		return "[" + getEpochTime() + "]-" + UUID.randomUUID().toString();
	}

	public static String getEpochTime() {
		long time = System.currentTimeMillis();
		return Long.toString(time);
	}

	
}
