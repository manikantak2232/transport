package com.pixelbox.exceptions;

public enum ExceptionLevelEnum {
	DATA_LEVEL, BUSINESS_LEVEL, SERVICE_LEVEL;

	public String value(){
		return name();
	}
	

}
