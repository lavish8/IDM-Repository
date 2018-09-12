package com.identity.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.identity.platform.auth.constant.Constants;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason=Constants.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
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
