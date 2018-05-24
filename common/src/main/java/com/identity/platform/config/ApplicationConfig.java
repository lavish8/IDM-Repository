package com.identity.platform.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by maheshs1 on 01/02/2018.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.identity.manager.persistence.dao")
@EntityScan(basePackages = "com.identity.manager.persistence.domain")
@EnableTransactionManagement
public class ApplicationConfig {


}
