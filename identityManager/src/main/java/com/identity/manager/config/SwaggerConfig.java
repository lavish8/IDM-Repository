package com.identity.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${swagger.base.url}")
	private String swaggerBaseURL;

	/** The protocol. */
	@Value("${swagger.protocol}")
	private String protocol;
	
	@Bean
	public Docket productApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.host(swaggerBaseURL)
				.protocols(Sets.newHashSet(protocol))
				.select().apis(RequestHandlerSelectors.basePackage("com.identity.manager.web.controller"))
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}
	
}
