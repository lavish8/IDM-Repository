package com.identity.platform.api.error;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 9009167465429395305L;

	/** The error code. */
	private String errorCode;

	/** The error message. */
	private String errorMessage;

	/**
	 * Instantiates a new error model.
	 */
	private ErrorModel() {
		super();
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
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the new error code
	 */
	public void setErrorCode(final String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage
	 *            the new error message
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
