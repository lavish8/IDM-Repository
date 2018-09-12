package com.identity.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * The Class OAuth2Config is responsible for generating tokens specific to a
 * client. Auth server will be generating tokens for client which will be
 * requesting to authorization server for authorization code.
 * 
 * @author maheshs1
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	/** The user details service. */
	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("customTokenEnhancer")
	private TokenEnhancer tokenEnhancer;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;	
	
	@Value("${security.oauth2.client.id}")
	private String UI_CLIENT_ID;
	
	@Value("${security.oauth2.client.client-secret}")
	private String UI_CLIENT_SECRET;
	
	private static final String SAMPLE_CLIENT_ID = "sampleClientId";
	private static final String SAMPLE_CLIENT_SECRET = "sampleSecret";
	private static final String PASSWORD = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code"; //client will redirect the user to the authorization server like facebook google
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String CLIENT_CREDENTIALS = "client_credentials";
	
	private static final String IMPLICIT = "implicit"; // used for single page web apps that can’t keep a client secret
														// and authorization server returns an access token instead of refresh token
														// the authorization server returning an authorization code 
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String TRUST = "trust";
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

	/** The encryption SALT. */
	private static final String SALT = "fdalkjalk;3jlwf00sfaof";
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());

	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mykeys.jks"),
				"mypass".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeys"));
		return converter;
	}

	@Bean()
	@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="singleton")
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setTokenEnhancer(accessTokenConverter());
		return defaultTokenServices;

	}

	@Bean
	public WebResponseExceptionTranslator webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {

			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
				OAuth2Exception body = responseEntity.getBody();
				body.addAdditionalInformation("status", String.valueOf(body.getHttpErrorCode()));
				HttpHeaders headers = new HttpHeaders();
				headers.setAll(responseEntity.getHeaders().toSingleValueMap());
				// do something with header or response
				return new ResponseEntity<>(body, headers, responseEntity.getStatusCode());
			}
		};
	}

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
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager)
				 //.userDetailsService(userDetailsService)
				 //.exceptionTranslator(webResponseExceptionTranslator());
				.tokenServices(tokenServices())
				.tokenStore(tokenStore())//.userApprovalHandler(userApprovalHandler)
				//.tokenEnhancer(tokenEnhancer)
				.accessTokenConverter(accessTokenConverter());
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
			.withClient(UI_CLIENT_ID).authorizedGrantTypes(CLIENT_CREDENTIALS, PASSWORD, REFRESH_TOKEN, AUTHORIZATION_CODE)
										.authorities("ROLE_TRUSTED_CLIENT").scopes(SCOPE_READ, SCOPE_WRITE).resourceIds(resourceId)
										.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
										.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS).secret(UI_CLIENT_SECRET)
		.and()
			.withClient(SAMPLE_CLIENT_ID).authorizedGrantTypes(CLIENT_CREDENTIALS)
										.authorities("ROLE_REGISTER").scopes("registerUser").resourceIds(resourceId)
										.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
										.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS).secret(SAMPLE_CLIENT_SECRET);

	}

	// this method is used to avoid popup for client id and it's secret for
	// implicit flow
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		/*oauthServer
		// we're allowing access to the token only for clients with
		// 'ROLE_TRUSTED_CLIENT' authority
		.tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
		.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");*/
		
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
}
