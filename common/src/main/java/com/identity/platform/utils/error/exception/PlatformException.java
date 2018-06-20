package com.identity.platform.utils.error.exception;

import java.util.ArrayList;
import java.util.List;

import com.identity.platform.utils.error.PlatformError;

public class PlatformException extends Exception {

	private static final long serialVersionUID = -3607672808589370452L;

	/** The errors. */
	private final List<PlatformError> errors = new ArrayList<>(0);

	/** The http status. */
	private final int httpStatus;

	/**
	 * Constructs a new exception with error object.
	 *
	 * @param ex
	 *            the ex
	 * @param httpStatus
	 *            the http status
	 * @param error
	 *            the error
	 */
	public PlatformException(Throwable ex, int httpStatus, PlatformError error) {
		super(ex);
		this.httpStatus = httpStatus;
		this.errors.add(error);
	}

	/**
	 * Constructs a new exception with error object.
	 *
	 * @param httpStatus
	 *            the http status
	 * @param error
	 *            the error
	 */
	public PlatformException(int httpStatus, PlatformError error) {
		this.httpStatus = httpStatus;
		this.errors.add(error);
	}

	/**
	 * Constructs a new exception with list of error.
	 *
	 * @param httpStatus
	 *            the http status
	 * @param errorList
	 *            the error list
	 */
	public PlatformException(int httpStatus, List<PlatformError> errorList) {
		this.httpStatus = httpStatus;
		this.errors.addAll(errorList);
	}

	/**
	 * Constructs a new exception with the specified error code and cause.
	 *
	 * @param httpStatus
	 *            the http status
	 * @param throwable
	 *            the throwable
	 * @param errorCode
	 *            the error code
	 */
	public PlatformException(int httpStatus, Throwable throwable, PlatformError errorCode) {
		super(throwable);
		this.httpStatus = httpStatus;
		this.errors.add(errorCode);
	}

	/**
	 * Gets the errors.
	 *
	 * @return list of PlatformError
	 */
	public List<PlatformError> getErrors() {
		return this.errors;
	}

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public int getHttpStatus() {
		return this.httpStatus;
	}

}
