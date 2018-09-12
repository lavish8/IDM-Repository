package com.identity.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@DependsOn("beanUtil")
@ComponentScan(basePackages= {"com.identity.platform", "com.identity.manager"})
@EnableReactiveCouchbaseRepositories(considerNestedRepositories=true, basePackages = "com.identity.manager.persistence")
@EnableJpaRepositories(basePackages="com.identity.manager.persistence")
@EntityScan(basePackages = {"com.identity.manager.persistence.domain", "com.identity.platform.persistence"})
public class IdentityManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityManagerApplication.class, args);
	}
}
