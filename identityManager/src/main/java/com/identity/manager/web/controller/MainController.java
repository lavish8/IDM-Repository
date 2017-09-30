package com.identity.manager.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/")
	public String login() {
		LOG.debug("################# index {} ############# ", "page");
		return "index";
	}

	@RequestMapping("/about")
	public String hello() {
		return "about";
	}

}
