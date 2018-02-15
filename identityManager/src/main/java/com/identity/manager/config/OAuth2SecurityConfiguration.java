/**
 * 
 */
package com.identity.manager.config;

import java.security.SecureRandom;
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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 /** The encryption SALT. */
    private static final String SALT = "fdalkjalk;3jlwf00sfaof";
    
    /** Public URLs. */
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/contact/**",
            "/error/**/*",
            "/console/**",
            "/api/**",
            "/oauth/token",
            /*ForgotMyPasswordController.FORGOT_PASSWORD_URL_MAPPING,
            ForgotMyPasswordController.CHANGE_PASSWORD_PATH,
            SignupController.SIGNUP_URL_MAPPING*/
    };
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Environment env;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.inMemoryAuthentication()
		.withUser("bill").password("abc123").roles("ADMIN")
		.and().withUser("bob").password("abc123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
	        if (!activeProfiles.contains("prod")) {
	            http.csrf().disable()
	            	.headers().frameOptions().disable();
	        }
		http.anonymous().disable()
		.authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login")
        			.defaultSuccessUrl("/payload")
        			.failureUrl("/login?error").permitAll()
        .and()
     	// sample anonymous customization
     	.anonymous().authorities("ROLE_ANON")
        .and()
        .logout().permitAll();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
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

}