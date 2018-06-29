package com.identity.platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockEmailServiceImpl extends AbstractEmailService<Object> {
	
	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailServiceImpl.class);

	@Override
	public void sendFeedbackEmail(Object feedbackPojo) {
		LOG.debug("Simulating an email service");
		LOG.info("email msg {} ", feedbackPojo);
		LOG.info("email sent");		
	}
}
