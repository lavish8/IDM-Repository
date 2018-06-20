package com.identity.platform.service;

import org.springframework.mail.SimpleMailMessage;

// TODO: Auto-generated Javadoc
/**
 * Contract for email service. Created by maheshs1 on 02/10/2017.
 *
 * @param <T> the generic type
 */
public interface EmailService<T> {
	
	/**
	 * Sends an email with the content in the Feedback Pojo.
	 *
	 * @param feedbackPojo the feedback pojo
	 */
	public void sendFeedbackEmail(T feedbackPojo);

	/**
	 * Sends an email with the content of the Simple Mail Message object.
	 *
	 * @param message The object containing the email content
	 * @param subject the subject
	 * @param body the body
	 */

	public void sendGenericEmailMessage(SimpleMailMessage message, String subject, String body);

}
