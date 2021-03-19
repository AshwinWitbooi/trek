package za.co.ashtech.trek.util;

import org.springframework.http.HttpStatus;
import za.co.ashtech.trek.model.ApiError;

public class TrekException extends Exception {

	private static final long serialVersionUID = 1L;
	final String errorCode;
	final String description;
	private final  ApiError error;
	private final HttpStatus httpStatus;
	
	
	public TrekException() {
		super();
		this.description="Error occurred contact administrator";
		this.errorCode="ER01";
		this.error=new ApiError(null,null);
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
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
