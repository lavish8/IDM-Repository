/*
 * 
 */
package com.identity.manager.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.manager.service.EmailService;
import com.identity.manager.web.domain.ContactPojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * The Class ContactController.
 */
@RestController
@RequestMapping(value = "/contact")
@Api(value="contact us", description="You can share your feedback with us")
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
	@PostMapping
	@ApiOperation(value = "We value your feedback", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully mail sent"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public ResponseEntity<String> contactPost(@RequestBody ContactPojo contact) {
		LOG.debug("################# content of contact page {} #############", contact);
		emailService.sendFeedbackEmail(contact);
		return new ResponseEntity<>("Your responce has been registered successfully", HttpStatus.OK);
	}
}
