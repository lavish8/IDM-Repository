package com.identity.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.identity.manager.service.EmailService;
import com.identity.manager.service.impl.SmtpEmailServiceImpl;

@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/identityManager/application-prod.properties")
public class ProductionConfig {
	
	@Bean
	public EmailService emailService(){
		return new SmtpEmailServiceImpl();
	}

}
