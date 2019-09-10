package io.can.studentcrud.exception;

import java.time.ZonedDateTime;

public class ErrorResponse {

	private final String message;

	private final String httpStatus;

	private final ZonedDateTime timeStamp;

	public ErrorResponse(String message, String httpStatus, ZonedDateTime timeStamp) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public ZonedDateTime getTimeStamp() {
		return timeStamp;
	}

}
