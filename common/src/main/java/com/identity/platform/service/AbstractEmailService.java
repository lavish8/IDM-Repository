package com.identity.platform.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractEmailService.
 *
 * @param <T> the generic type
 */
public abstract class AbstractEmailService<T> implements EmailService<T> {

	/** The default to address. */
	@Value("${default.to.address}")
	protected String defaultToAddress;
	
	/** The default from address. */
	@Value("${default.from.address}")
	private String defaultFromAddress;
	
	/** The default reply to address. */
	@Value("${default.reply.to.address}")
	private String defaultReplyToAddress;

	/* (non-Javadoc)
	 * @see com.identity.platform.service.EmailService#sendGenericEmailMessage(org.springframework.mail.SimpleMailMessage, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendGenericEmailMessage(SimpleMailMessage message, String subject, String body) {
		message.setTo(defaultToAddress);
		message.setFrom(defaultFromAddress);
		message.setReplyTo(defaultReplyToAddress);
		message.setSubject(subject);
		message.setText(body);
	}
}
