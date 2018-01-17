/*
 * 
 */
package com.identity.manager.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.identity.manager.service.EmailService;
import com.identity.manager.web.domain.ContactPojo;


/**
 * The Class ContactController.
 */
@RestController
public class ContactController {
	
	/**  The application logger. */
	private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);
	
	/** The email service. */
	@Autowired
	private EmailService emailService;

	/**
	 * Contact post.
	 *
	 * @param contact the contact
	 * @return the string
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public ResponseEntity<String> contactPost(@RequestBody ContactPojo contact) {
		LOG.debug("################# content of contact page {} #############", contact);
		emailService.sendFeedbackEmail(contact);
		return new ResponseEntity<>("Your responce has been registered successfully", HttpStatus.OK);
	}
}
