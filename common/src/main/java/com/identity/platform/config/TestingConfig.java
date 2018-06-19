package com.identity.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("test")
@PropertySource("file:///${CONF_DIR}/application-test.properties")
public class TestingConfig {

	
}

