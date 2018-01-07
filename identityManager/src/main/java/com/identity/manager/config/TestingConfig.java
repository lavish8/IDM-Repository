package com.identity.manager.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.identity.manager.service.EmailService;
import com.identity.manager.service.impl.MockEmailServiceImpl;

@Configuration
@Profile("test")
@PropertySource("file:///${user.home}/identityManager/application-test.properties")
public class TestingConfig {

	@Bean
	public EmailService emailService(){
		return new MockEmailServiceImpl();		
	}
	
	@Bean
	public ServletRegistrationBean h2ConsoleServletRegistration() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
		bean.addUrlMappings("/console/*");
		return bean;
	}
}

