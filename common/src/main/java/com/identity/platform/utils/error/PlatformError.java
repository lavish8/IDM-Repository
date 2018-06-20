package com.identity.platform.utils.error;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PlatformError {

    /** Http Status *. */
    private int status;

    /**
     * The code property is an error code specific to your particular REST API.
     */
    private String code;

    /**
     * The message property is a nice human readable error message that can
     * potentially be shown directly to an application end user (not a
     * developer).
     */
    private String message;

    /**
     * The developerMessage property conveys any and all technical information
     * that a developer calling your REST API might find useful. This is where
     * you might include exception messages, stack traces, or anything else that
     * you think will help a developer.
     */

    private String developerMessage;

    /**
     * The moreInfo property specifies a URL that anyone seeing the error
     * message can click (or copy and paste) in a browser. The target web page
     * should describe the error condition fully, as well as potential solutions
     * to help them resolve the error condition.
     */
    private String moreInfo;

    /**
     * Instantiates a new platform error.
     */
    public PlatformError() {
        // Default constructor
    }

    /**
     * Instantiates a new platform error.
     */
    public PlatformError(int status, String code, String message) {
    	this.status = status;
    	this.code = code;
    	this.message = message;
     }

	/**
     * Gets the status.
     *
     * @return the status
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Sets the status.
     *
     * @param status
     *            the new status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the code.
     *
     * @param code
     *            the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message.
     *
     * @param message
     *            the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the developer message.
     *
     * @return the developer message
     */
    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    /**
     * Sets the developer message.
     *
     * @param developerMessage
     *            the new developer message
     */
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    /**
     * Gets the more info.
     *
     * @return the more info
     */
    public String getMoreInfo() {
        return this.moreInfo;
    }

    /**
     * Sets the more info.
     *
     * @param moreInfo
     *            the new more info
     */
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
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
