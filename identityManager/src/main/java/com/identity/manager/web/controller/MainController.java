package com.identity.manager.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.identity.manager.enums.ModelViewEnum;
import com.identity.manager.web.domain.ContactPojo;

@Controller
public class MainController {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);	

	@RequestMapping("/")
	public String index() {
		LOG.debug("################# loading index page #############");
		return ModelViewEnum.INDEX_VIEW_NAME.getId();
	}
	
	@RequestMapping("/login")
	public String login() {
		LOG.debug("################# loading login page #############");
		return ModelViewEnum.LOGIN_VIEW_NAME.getId();
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		LOG.debug("################# loading logout page #############");
		return ModelViewEnum.LOGOUT_VIEW_NAME.getId();
	}
	
	@RequestMapping("/payload")
	public String payload() {
		LOG.debug("################# loading payload page #############");
		return ModelViewEnum.PAYLOAD_VIEW_NAME.getId();
	}

	@RequestMapping("/about")
	public String about() {
		return ModelViewEnum.ABOUT_US_VIEW_NAME.getId();
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(ModelMap model) {
		LOG.debug("################# loading contact page #############");
		ContactPojo contact = new ContactPojo();
		model.addAttribute(ModelViewEnum.FEEDBACK_MODEL, contact);
		return ModelViewEnum.CONTACT_US_VIEW_NAME.getId();
	}
}
