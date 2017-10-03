package com.identity.manager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.identity.manager.service.AbstractEmailService;

public class MockEmailServiceImpl extends AbstractEmailService {
	
	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailServiceImpl.class);

	@Override
	public void sendGenericEmailMessage(SimpleMailMessage message) {
		LOG.debug("Simulating an email service");
		LOG.info("email msg {} ", message);
		LOG.info("email sent");
	}


}
