package za.co.ashtech.trek.util;

import org.springframework.http.HttpStatus;
import za.co.ashtech.trek.model.ApiError;

public class TrekException extends Exception {

	private static final long serialVersionUID = 1L;
	String errorCode;
	String description;
	private ApiError error;
	private HttpStatus httpStatus;
	
	
	public TrekException() {
		super();
	}
	
	
	public TrekException(String errorCode, String description,HttpStatus httpStatus) {
		super(description);
		this.errorCode = errorCode;
		this.description = description;
		this.httpStatus = httpStatus;		
		this.error = new ApiError(errorCode, description);
	}


	public String getErrorCode() {
		return errorCode;
	}

	public String getDecription() {
		return description;
	}


	public ApiError getError() {
		return error;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
