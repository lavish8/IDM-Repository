package com.identity.platform.persistence.domain;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.identity.platform.auth.constant.PlatformConstants.CouchBaseDatabaseConfig;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

	/** The Constant BUCKET_NAME. */
	protected static final String BUCKET_NAME = CouchBaseDatabaseConfig.COUCHBASESERVER_BUCKETNAME.getValue();
	protected static final String BUCKET_PASSWORD = CouchBaseDatabaseConfig.COUCHBASESERVER_BUCKET_PASSWORD.getValue();
	protected static final String HOSTNAME_NAME = CouchBaseDatabaseConfig.COUCHBASESERVER_HOSTNAME.getValue();
	private static final String COUCHBASE_PASSWORD = CouchBaseDatabaseConfig.COUCHBASESERVER_COUCHBASE_PASSWORD.getValue();
	private static final String COUCHBASE_USERNAME = CouchBaseDatabaseConfig.COUCHBASESERVER_COUCHBASE_USERNAME.getValue();

	@Autowired
	private Environment env;

	@Override
	protected List<String> getBootstrapHosts() {
		return Arrays.asList(env.getProperty(HOSTNAME_NAME));
	}

	@Override
	protected String getBucketName() {
		return env.getProperty(BUCKET_NAME);
	}

	@Override
	protected String getBucketPassword() {
		return env.getProperty(BUCKET_PASSWORD);
	}

	protected String getCouchbaseUserName() {
		return env.getProperty(COUCHBASE_USERNAME);
	}

	protected String getCouchbasePassword() {
		return env.getProperty(COUCHBASE_PASSWORD);
	}
}
