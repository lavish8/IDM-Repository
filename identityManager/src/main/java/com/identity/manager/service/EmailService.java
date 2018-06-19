package com.identity.manager.service;

import org.springframework.mail.SimpleMailMessage;

import com.identity.manager.web.domain.ContactPojo;

/**
 * Contract for email service. Created by maheshs1 on 02/10/2017.
 */
public interface EmailService {
	/**
	 * Sends an email with the content in the Feedback Pojo.
	 * 
	 * @param ContactPojo The feedback Pojo
	 */
	public void sendFeedbackEmail(ContactPojo feedbackPojo);

	/**
	 * Sends an email with the content of the Simple Mail Message object.
	 * 
	 * @param message The object containing the email content
	 */
	public void sendGenericEmailMessage(SimpleMailMessage message);

}
