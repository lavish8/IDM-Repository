package com.identity.manager.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.identity.manager.enums.ModelViewEnum;
import com.identity.manager.web.domain.ContactPojo;

@Controller
public class MainController {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/")
	public String login() {
		LOG.debug("################# loading index page #############");
		return "index";
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

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String contactPost(@ModelAttribute(ModelViewEnum.FEEDBACK_MODEL) ContactPojo contact) {
		LOG.debug("################# content of contact page {} #############", contact);
		return ModelViewEnum.CONTACT_US_VIEW_NAME.getId();
	}

}
