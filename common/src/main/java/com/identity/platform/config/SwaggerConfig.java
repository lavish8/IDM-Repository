package com.identity.platform.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.identity.platform.utils.SwaggerMessageBuilder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String WEB_CONTROLLER = "com.identity.manager.web.controller";

	@Value("${swagger.base.url}")
	private String swaggerBaseURL;

	/** The protocol. */
	@Value("${swagger.protocol}")
	private String protocol;

	@Value("${security.oauth2.client.id}")
	private String UI_CLIENT_ID;

	@Value("${security.oauth2.client.client-secret}")
	private String UI_CLIENT_SECRET;

	@Value("${host.full.dns.auth.link}")
	private String authLink;

	@Autowired
	private SwaggerMessageBuilder configBuilder;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(WEB_CONTROLLER))
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().pathMapping("/").directModelSubstitute(LocalDate.class, String.class)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, configBuilder.buildHttpErrorResponseMessages())
				.globalResponseMessage(RequestMethod.POST, configBuilder.buildHttpErrorResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, configBuilder.buildHttpErrorResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, configBuilder.buildHttpErrorResponseMessages())
				.globalResponseMessage(RequestMethod.OPTIONS, configBuilder.buildHttpErrorResponseMessages())
				.securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Collections.singletonList(securityScheme())).useDefaultResponseMessages(false)
				// .host(swaggerBaseURL).protocols(Sets.newHashSet(protocol))
				.apiInfo(apiInfo());
	}

	private OAuth securityScheme() {
		List<AuthorizationScope> authorizationScopeList = Arrays.asList(authorizationScope());

		List<GrantType> grantTypes = new ArrayList<>();
		GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(authLink + "/oauth/token");
		grantTypes.add(creGrant);

		return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(UI_CLIENT_ID, UI_CLIENT_SECRET, "", "", "", ApiKeyVehicle.HEADER,
				HttpHeaders.AUTHORIZATION, " ");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/")).build();
	}

	private List<SecurityReference> defaultAuth() {
		return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScope()));
	}

	private AuthorizationScope[] authorizationScope() {
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
		authorizationScopes[2] = new AuthorizationScope("write", "write all");
		return authorizationScopes;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("IAM API").description("").termsOfServiceUrl("https://www.example.com/api")
				.contact(new Contact("Shashikant", "http://www.example.com", "shashikant@example.com"))
				.license("Open Source").licenseUrl("https://www.example.com").version("1.0.0").build();
	}

}
