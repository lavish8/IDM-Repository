package com.identity.manager.exception;

public class Message {

	private String i18nCode;
	private String[] messageArguments;
	private MessageType type;
	private Long dataRowId;

	public enum MessageType {
		INFO, ERROR;
	}

	/**
	 * Default constructor
	 */
	public Message() { // NOSONAR

	}

	/**
	 * 
	 * @param i18Code
	 * @param messageType
	 * @param messageArguments
	 */
	public Message(String i18nCode, MessageType messageType, String... messageArguments) {
		setI18nCode(i18nCode);
		setType(messageType);
		setMessageArguments(messageArguments);
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getI18nCode() {
		return i18nCode;
	}

	public void setI18nCode(String i18nCode) {
		this.i18nCode = i18nCode;
	}

	public String[] getMessageArguments() {
		return messageArguments;
	}

	public void setMessageArguments(String... messageArguments) {
		this.messageArguments = messageArguments;
	}

	public Long getDataRowId() {
		return dataRowId;
	}

	public void setDataRowId(Long dataRowId) {
		this.dataRowId = dataRowId;
	}

}
