package io.can.studentcrud.exception;

public class InvalidPageRequestParameterException extends RuntimeException {

	private static final long serialVersionUID = -519502760508015545L;

	public InvalidPageRequestParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPageRequestParameterException(String message) {
		super(message);
	}

	public InvalidPageRequestParameterException(Throwable cause) {
		super(cause);
	}

}
