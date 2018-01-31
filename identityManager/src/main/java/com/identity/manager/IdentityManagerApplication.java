package com.identity.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableJpaRepositories(basePackages="com.identity.manager.persistence.dao")
public class IdentityManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityManagerApplication.class, args);
	}
}
