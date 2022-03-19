package com.pixelbox.exceptions;

/**
 * HoopException class which wraps the ExceptionInfo and
 * prepare the corresponding error codes and messages further tracking
 * is useful while displaying the error messages each layer of web applications
 */
public class TransportException extends Exception {

	private static final long serialVersionUID = 7765186107407874341L;
	
	/**
	 * ExceptionInfo variable holds exceptionInfo
	 */
	private ExceptionInfo exceptionInfo;

	/**
	 * Constructor for initializing exception info.
	 * @param exceptionInfo
	 */
	public TransportException(ExceptionInfo exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	
	/**
	 * @return
	 */
	public ExceptionInfo getExceptionInfo() {
		return exceptionInfo;
	}


	
}
