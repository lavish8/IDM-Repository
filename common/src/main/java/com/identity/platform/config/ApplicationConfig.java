package com.identity.platform.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.identity.platform.persistence.domain.CouchbaseConfiguration;
import com.identity.platform.service.EmailService;
import com.identity.platform.service.MockEmailServiceImpl;

/**
 * Created by maheshs1 on 01/02/2018.
 */
@Configuration
@ComponentScan("com.identity")
@Import(value= {CouchbaseConfiguration.class})
@EnableJpaRepositories(basePackages = { "com.identity.platform.persistence", "com.identity.manager.persistence" })
@EnableCouchbaseRepositories(basePackages = { "com.identity.platform.persistence" })
@EntityScan(basePackages = { "com.identity.platform.persistence", "com.identity.manager.persistence" })
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public ValidatingCouchbaseEventListener validationEventListener() {
		return new ValidatingCouchbaseEventListener(validator());
	}

	@Bean
	public EmailService<?> emailService() {
		return new MockEmailServiceImpl();
	}

	@Bean
	public ServletRegistrationBean h2ConsoleServletRegistration() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
		bean.addUrlMappings("/console/*");
		return bean;
	}

}
