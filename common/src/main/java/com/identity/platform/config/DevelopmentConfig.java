package com.identity.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("file:///${CONF_DIR}/application-dev.properties")
public class DevelopmentConfig {

}
