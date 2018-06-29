package com.identity.platform.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class I18NConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("classpath:i18n/messages");
		Locale.setDefault(Locale.US);
		// Checks for new messages every 30 minutes
		resourceBundleMessageSource.setCacheSeconds(1800);
		return resourceBundleMessageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

}
