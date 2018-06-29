package com.identity.platform.utils.error;

import org.springframework.http.HttpStatus;

// TODO: Auto-generated Javadoc
/**
 * The Enum PlatformErrorCodes.
 */
public enum PlatformErrorCodes {

	/** The not found. */
	NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found", "ERROR404"),

	/** The resource already exist. */
	RESOURCE_ALREADY_EXIST(HttpStatus.CONFLICT.value(), "Resource Already Exist", "ERROR409"),

	/** The internal server error. */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "ERROR500"),

	/** The failed dependency. */
	FAILED_DEPENDENCY(HttpStatus.FAILED_DEPENDENCY.value(), "Failed Dependency", "ERROR424"),

	/** The not implemented. */
	NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED.value(), "Not Implemented", "ERROR501"),

	/** The bad request. */
	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad Request", "ERROR400"),

	/** The unauthorized. */
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", "ERROR401"),

	/**  The method is not implemented. */
	METHOD_NOT_IMPLEMENTED(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Implemented", "ERROR405");

	/** The value. */
	private final int value;

	/** The reason phrase. */
	private final String reasonPhrase;

	/** The error code. */
	private final String errorCode;

	/**
	 * Instantiates a new platform error codes.
	 *
	 * @param value
	 *            the value
	 * @param reasonPhrase
	 *            the reason phrase
	 * @param errorCode
	 *            the error code
	 */
	PlatformErrorCodes(int value, String reasonPhrase, String errorCode) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Return the integer value of this status code.
	 *
	 * @return the int
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 *
	 * @return the reason phrase
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	/**
	 * Method to get appropriate platform error codes.
	 *
	 * @param httpStatus the http status
	 * @return the platform error codes
	 */
	public static PlatformErrorCodes findAppropriateCode(int httpStatus) {
		for (PlatformErrorCodes errorCode : PlatformErrorCodes.values()) {
			if (errorCode.value() == httpStatus) {
				return errorCode;
			}
		}
		return PlatformErrorCodes.INTERNAL_SERVER_ERROR;
	}
}
