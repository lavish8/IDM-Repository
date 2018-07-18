/**
 * 
 */
package com.identity.platform.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.identity.platform.auth.config.OauthExceptionTranslator;





@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	@Autowired
	private DefaultTokenServices tokenServices;

	// The TokenStore bean provided at the AuthorizationConfig
	@Autowired
	private TokenStore tokenStore;

	/**
	 * Exception translator.
	 *
	 * @return the web response exception translator
	 */
	@Bean
	public WebResponseExceptionTranslator exceptionTranslator() {
		return new OauthExceptionTranslator();
	}

	/**
	 * Access denied handler.
	 *
	 * @return the access denied handler
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		final OAuth2AccessDeniedHandler handler = new OAuth2AccessDeniedHandler();
		handler.setExceptionTranslator(exceptionTranslator());
		return handler;
	}
	
	@Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
		final OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
        entryPoint.setExceptionTranslator(exceptionTranslator());
        return entryPoint;
    }

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		Resource resource = new ClassPathResource("public.txt");
		String publicKey = "";
	    try {
	        publicKey = FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8);
	    } catch (final IOException e) {
	        throw new RuntimeException(e);
	    }
	    converter.setVerifierKey(publicKey);
		return converter;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId)
				.tokenServices(tokenServices)
				.tokenStore(tokenStore)
				.accessDeniedHandler(accessDeniedHandler())
				.authenticationEntryPoint(authenticationEntryPoint());

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
				.requestMatcher(new OAuthRequestedMatcher()).csrf().disable()
				.anonymous().disable()
				.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS).permitAll()
					//configure following URLs to require the role such as ROLE_USER 
					// Note:: it should not start with "ROLE_" as this is automatically inserted.
					.antMatchers("/api/hello").access("hasAnyRole('USER')")
					.antMatchers("/api/me").hasAnyRole("USER", "ADMIN")
					.antMatchers("/api/admin").hasRole("ADMIN")
					// use the full name when specifying authority access
					.antMatchers("/api/registerUser").hasAuthority("ROLE_REGISTER")
					// Specify that all access to /api/** to authenticated users
					.antMatchers("/api/**").authenticated()
				.and()
					.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler());

	}

	private static class OAuthRequestedMatcher implements RequestMatcher {
		public boolean matches(HttpServletRequest request) {
			// Determine if the resource called is "/api/**"
			String path = request.getServletPath();
			if (path.length() >= 5) {
				path = path.substring(0, 5);
				boolean isApi = path.equals("/api/");
				return isApi;
			} else
				return false;
		}
	}

}
