package com.identity.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
=======
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> 6179f6b32b765fdc9618444fe4885894f0c24467
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
<<<<<<< HEAD
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
=======
>>>>>>> 6179f6b32b765fdc9618444fe4885894f0c24467

/**
 * The Class OAuth2Config is responsible for generating tokens specific to a client. 
 * Auth server will be generating tokens for client which will be requesting to authorization server 
 * for authorization code.
 * 
 * @author maheshs1
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;
<<<<<<< HEAD
	
	@Autowired
    private TokenStore tokenStore;
 
    @Autowired
    private UserApprovalHandler userApprovalHandler;
=======
>>>>>>> 6179f6b32b765fdc9618444fe4885894f0c24467

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	private  static final String CLIEN_ID = "devglan-client";
	private static final String CLIENT_SECRET = "devglan-secret";
	private static final String GRANT_TYPE = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String IMPLICIT = "implicit";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String TRUST = "trust";
<<<<<<< HEAD
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60; //Access token is only valid for 60 minutes.
    private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60; //Refresh token is only valid for 6 hours.
=======
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
>>>>>>> 6179f6b32b765fdc9618444fe4885894f0c24467

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.oauth2.config.annotation.web.configuration.
	 * AuthorizationServerConfigurerAdapter#configure(org.springframework.
	 * security.oauth2.config.annotation.web.configurers.
	 * AuthorizationServerEndpointsConfigurer)
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
<<<<<<< HEAD
		configurer.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler);
=======
>>>>>>> 6179f6b32b765fdc9618444fe4885894f0c24467
		configurer.authenticationManager(authenticationManager);
		configurer.userDetailsService(userDetailsService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.oauth2.config.annotation.web.configuration.
	 * AuthorizationServerConfigurerAdapter#configure(org.springframework.
	 * security.oauth2.config.annotation.configurers.
	 * ClientDetailsServiceConfigurer)
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient(CLIEN_ID)
				.secret(CLIENT_SECRET)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.authorizedGrantTypes(GRANT_TYPE, REFRESH_TOKEN, IMPLICIT, AUTHORIZATION_CODE)
				.resourceIds("resource");
	}
}
