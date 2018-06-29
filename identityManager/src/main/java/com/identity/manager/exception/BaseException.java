package com.identity.manager.exception;

import java.util.List;

import com.identity.manager.utils.GeneralUtility;

public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 5965347760196965114L;

	public enum Severity {
		HIGH, LOW, MEDIUS
	}

	private List<Message> messages;
	private String uniqueID;
	private String logMessage;
	private Severity severity;
	
	private String exceptionCode;
	private Exception originalException;

	
	public BaseException() {
		this.uniqueID = GeneralUtility.getUniqueId();
		this.setSeverity(Severity.LOW);
	}
	
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}


	public BaseException(String message) {
		super(message);
	}


	public BaseException(Throwable cause) {
		super(cause);
	}


	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> theMessages){
		this.messages=theMessages;
	}
	
	public Exception getOriginalException() {
		return originalException;
	}

	public void setOriginalException(Exception originalException) {
		this.originalException = originalException;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}


	public String getUniqueID() {
		return uniqueID;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
}
