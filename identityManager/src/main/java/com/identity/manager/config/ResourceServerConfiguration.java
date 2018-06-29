/**
 * 
 */
package com.identity.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.access.AccessDeniedHandler;
 
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
 
    private static final String RESOURCE_ID = "my_rest_api";
    
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
     
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
        anonymous().disable()
        .requestMatchers().antMatchers("/user/**")
        .and().authorizeRequests()
        .antMatchers("/user/**").access("hasRole('ADMIN')")
        .and().exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler());
    }
 
}
