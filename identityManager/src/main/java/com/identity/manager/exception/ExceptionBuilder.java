/*
 * Author: maheshs1
 * Description: This is exception builder class that will instantiate the custom exception class and return the same.
 *
 */
package com.identity.manager.exception;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.identity.manager.exception.BaseException.Severity;

/**
 * @author iss
 * @generated 
 *            "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ExceptionBuilder {

	private Class<? extends BaseException> exceptionClass;
	private BaseException exception;
	private List<Message> messages;
	private String i18nCode;
	private Exception originaleException;
	private String logMessage;
	private String exceptionCode;
	private String[] messageArguments;
	private Message message;
	private Severity severity;

	private ExceptionBuilder() {
	}

	public static ExceptionBuilder getInstance(
			Class<? extends BaseException> exceptionClass) {
		ExceptionBuilder exceptionBuilder = new ExceptionBuilder();
		exceptionBuilder.setExceptionClass(exceptionClass);
		return exceptionBuilder;

	}

	public static ExceptionBuilder getInstance(
			Class<? extends BaseException> exceptionClass,
			String exceptionCode, String logMessage) {

		ExceptionBuilder exceptionBuilder = new ExceptionBuilder();
		exceptionBuilder.setExceptionClass(exceptionClass);
		exceptionBuilder.setExceptionCode(exceptionCode);
		exceptionBuilder.setLogMessage(logMessage);
		return exceptionBuilder;
	}

	public Boolean initializeException(
			Class<? extends BaseException> exceptionClass) {
		try {

			if (originaleException != null && logMessage != null) {
				Constructor constructor = Class.forName(
						exceptionClass.getName()).getConstructor(String.class,
						Throwable.class);
				exception = (BaseException) constructor.newInstance(logMessage,
						originaleException);
			} else if (originaleException == null && logMessage != null) {
				Constructor constructor = Class.forName(
						exceptionClass.getName()).getConstructor(String.class);
				exception = (BaseException) constructor.newInstance(logMessage);
			}

			else if (originaleException != null) {
				Constructor constructor = Class.forName(
						exceptionClass.getName()).getConstructor(
						Throwable.class);
				exception = (BaseException) constructor
						.newInstance(originaleException);
			} else {
				exception = exceptionClass.newInstance();
			}
		} catch (InstantiationException instantiationException) {

			return false;
		} catch (IllegalAccessException illegalAccessException) {

			return false;
		} catch (IllegalArgumentException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

	public BaseException build() {
		initializeException(exceptionClass);
		exception.setMessages(messages);
		exception.setExceptionCode(exceptionCode);
		exception.setSeverity(severity);
		return exception;
	}

	public ExceptionBuilder setOriginalException(Exception originalException) {
		this.originaleException = originalException;
		return this;
	}

	public ExceptionBuilder setLogMessage(String logMessage) {
		this.logMessage = logMessage;
		return this;
	}

	public ExceptionBuilder setMessage(Message message) {
		this.addMessage(message);
		return this;
	}

	public ExceptionBuilder setSeverity(Severity severity) {
		this.severity = severity;
		return this;
	}


	public ExceptionBuilder setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
		return this;
	}

	public ExceptionBuilder setMessages(List<Message> messages) {
		this.addMessages(messages);
		return this;
	}

	public ExceptionBuilder setMessage(String i18nCode) {
		this.addMessage(i18nCode);
		return this;
	}

	public ExceptionBuilder setMessage(String i18nCode,
			String[] messageArguments) {
		this.addMessage(i18nCode, messageArguments);
		return this;
	}

	public ExceptionBuilder setExceptionClass(
			Class<? extends BaseException> exceptionClass) {
		this.exceptionClass = exceptionClass;
		return this;
	}

	public void addMessages(List<Message> theMessages) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		if (theMessages != null)
			this.messages.addAll(theMessages);
	}

	public void addMessage(Message theMessage) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		if (theMessage != null)
			this.messages.add(theMessage);
	}

	public void addMessage(String i18nCode) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		Message messageTemp = new Message();
		messageTemp.setI18nCode(i18nCode);
		this.messages.add(messageTemp);
	}

	public void addMessage(String i18nCode, String[] messageArguments) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		Message messageTemp = new Message();
		messageTemp.setI18nCode(i18nCode);
		messageTemp.setMessageArguments(messageArguments);
		this.messages.add(messageTemp);
	}

}
