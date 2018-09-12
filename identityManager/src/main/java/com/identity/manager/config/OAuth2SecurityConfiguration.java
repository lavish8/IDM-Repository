/**
 * 
 */
package com.identity.manager.config;

import java.util.Arrays;
import java.util.List;

/**
 * @author maheshs1
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebFluxSecurity()
// @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/** Public URLs. */
	private static final String[] PUBLIC_MATCHERS = { "/webjars/**", "/css/**", "/js/**", "/images/**", "/",
			"/contact/**", "/error/**/*", "/console/**", "/api/**", "/oauth/token", "/swagger-resources",
			"/swagger-resources/**", "/swagger-ui.html", "/swagger.json", "/v2/api-docs/**", "/api/v1/user/**",
			"/health", "/actuator/**",
			/*
			 * ForgotMyPasswordController.FORGOT_PASSWORD_URL_MAPPING,
			 * ForgotMyPasswordController.CHANGE_PASSWORD_PATH,
			 * SignupController.SIGNUP_URL_MAPPING
			 */
	};

	/*@Autowired
	private UserDetailsService userDetailsService;*/
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {

	    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	    final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
	    UserDetails user = userBuilder
	            .username("bill")
	            .password("abc123")
	            .roles("USER")
	            .build();

	    UserDetails admin = userBuilder
	            .username("admin")
	            .password("password")
	            .roles("USER","ADMIN")
	            .build();

	    return new InMemoryUserDetailsManager(user, admin);
	}

	@Autowired
	private Environment env;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() throws Exception {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth .userDetailsService(userDetailsService())
		 		 .passwordEncoder(passwordEncoder());
		 

		/*UserDetails user = User.withUsername("bill")
                .password(passwordEncoder().encode("abc123"))
                .roles("USER").build();
		
		auth.inMemoryAuthentication()
				.withUser(user);
				.and().withUser("bob").password("abc123").roles("USER")
				.and().withUser("admin").password("abc123").roles("SUPERUSER");*/
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// provides the default AuthenticationManager as a Bean
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if (!activeProfiles.contains("prod")) {
			http.csrf().disable().headers().frameOptions().disable();
		}
		http.anonymous().disable().authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/payload")
				.failureUrl("/login?error").permitAll().and()
				// sample anonymous customization by default ROLE_ANONYMOUS
				.anonymous().authorities("ROLE_ANON").and().logout().permitAll();
	}
}