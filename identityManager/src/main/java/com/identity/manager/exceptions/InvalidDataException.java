package com.identity.manager.exceptions;

public class InvalidDataException extends BaseException{

	private static final long serialVersionUID = 132686895981785364L;
	
	public InvalidDataException() {
		super();
	}

	public InvalidDataException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(final String message) {
		super(message);
	}

	public InvalidDataException(final Throwable cause) {
		super(cause);
	}
}
