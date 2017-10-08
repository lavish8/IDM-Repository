package com.identity.manager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.identity.manager.service.AbstractEmailService;

public class SmtpEmailServiceImpl extends AbstractEmailService {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailServiceImpl.class);

	@Autowired
	private MailSender emailSender;

	@Override
	public void sendGenericEmailMessage(SimpleMailMessage message) {
		LOG.debug("Sending mail with {}",message);
		emailSender.send(message);
		LOG.info("email sent");
	}

}
