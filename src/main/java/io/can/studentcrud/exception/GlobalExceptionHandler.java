package io.can.studentcrud.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(StudentNotFoundException exc) {
		ErrorResponse errorResponse = new ErrorResponse(exc.getMessage(), HttpStatus.NOT_FOUND.toString(), ZonedDateTime.now(ZoneId.of("UTC")));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(InvalidPageRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(InvalidPageRequestParameterException exc) {
		ErrorResponse errorResponse = new ErrorResponse(exc.getMessage(), HttpStatus.BAD_REQUEST.toString(), ZonedDateTime.now(ZoneId.of("UTC")));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(IllegalArgumentException exc) {
		ErrorResponse errorResponse = new ErrorResponse(exc.getMessage(), HttpStatus.BAD_REQUEST.toString(), ZonedDateTime.now(ZoneId.of("UTC")));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleCustomException(Exception exc) {
		ErrorResponse errorResponse = new ErrorResponse(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ZonedDateTime.now(ZoneId.of("UTC")));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
}
