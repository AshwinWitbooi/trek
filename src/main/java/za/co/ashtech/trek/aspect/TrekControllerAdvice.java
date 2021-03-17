package za.co.ashtech.trek.aspect;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import za.co.ashtech.trek.model.ApiError;
import za.co.ashtech.trek.util.CONSTANTS;
import za.co.ashtech.trek.util.TrekException;


@ControllerAdvice
public class TrekControllerAdvice {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(TrekControllerAdvice.class);
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<ApiError> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
		
		ApiError error = new ApiError(CONSTANTS.ERC002, e.getMessage());
		
		this.logErrorResponse(error);
		
	    return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(TrekException.class)
	public ResponseEntity<ApiError> handleBookLogApiException(TrekException e) {
		
		this.logErrorResponse(e.getError());
		
	    return new ResponseEntity<>(e.getError(),e.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(Exception e) {
		
		ApiError error = new ApiError(CONSTANTS.ERC002, e.getMessage());
		
		this.logErrorResponse(error);
		
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private void logErrorResponse(ApiError error) {
		ObjectMapper mapper  = new ObjectMapper();
		
		try {
			logger.info("RESPONSE: "+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(error));
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
