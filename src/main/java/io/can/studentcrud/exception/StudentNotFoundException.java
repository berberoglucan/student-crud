package io.can.studentcrud.exception;

import java.io.Serializable;

public class StudentNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -1235392282897412440L;

	public StudentNotFoundException() {
		super();
	}

	public StudentNotFoundException(String message) {
		super(message);
	}

	public StudentNotFoundException(Throwable cause) {
		super(cause);
	}

}
