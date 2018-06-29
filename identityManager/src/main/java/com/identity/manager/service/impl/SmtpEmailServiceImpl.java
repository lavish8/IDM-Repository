package com.identity.manager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.identity.manager.web.domain.ContactPojo;
import com.identity.platform.service.AbstractEmailService;

@Service
public class SmtpEmailServiceImpl extends AbstractEmailService<ContactPojo> {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailServiceImpl.class);

	@Autowired
	private MailSender emailSender;


	@Override
	public void sendFeedbackEmail(ContactPojo feedbackPojo) {
		SimpleMailMessage msg = prepareMailMessage(feedbackPojo);
		LOG.debug("Sending mail with {}",feedbackPojo);
		emailSender.send(msg);
		LOG.info("email sent");		
	}
	
	private SimpleMailMessage prepareMailMessage(ContactPojo feedback) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(defaultToAddress);
		message.setFrom(feedback.getEmail());
		message.setReplyTo(feedback.getEmail());
		message.setSubject("[DevOps Buddy]: Feedback received from " + feedback.getFirstName() + " "
				+ feedback.getLastName() + "!");
		message.setText("User with email: " + feedback.getEmail() + " left this feedback:\n" + feedback.getFeedback());
		return message;
	}

}
