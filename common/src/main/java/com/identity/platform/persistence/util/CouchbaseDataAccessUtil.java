package com.identity.platform.persistence.util;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.identity.platform.auth.constant.PlatformConstants.CouchBaseDatabaseConfig;
import com.identity.platform.persistence.domain.CommonDataAccess;
import com.identity.platform.utils.error.exception.PlatformException;
import com.identity.platform.utils.error.exception.PropertyPlaceHolderUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonDataAccessUtil.
 */
@Component
public class CouchbaseDataAccessUtil implements CommonDataAccess {
	
	/** The application logger. */
	private static final Logger LOG = LoggerFactory.getLogger(CouchbaseDataAccessUtil.class);	

	/** The Constant ROW_COUNT. */
	private static final String ROW_COUNT = "count";

	/** The Constant SPACE. */
	private static final char[] SPACE = " ".toCharArray();

	private static final String BUCKET_NAME = CouchBaseDatabaseConfig.COUCHBASESERVER_BUCKETNAME.getValue();;

	/** The health check attempts. */
	private final int healthCheckAttempts = 3;

	/** The couchbase server bucket. */
	@Autowired
	private Bucket couchbaseBucket;

	/** The my cluster. */
	@Autowired
	private Cluster myCluster;
	
	@Autowired
	private Environment env;

	/**
	 * (non-Javadoc).
	 *
	 * @return true, if successful
	 * @throws PlatformException
	 *             the platform exception
	 */
	@Override
	public final boolean ping() throws PlatformException {
		boolean isDBRunning = false;
		int counter = 1;
		final StringBuilder query = new StringBuilder("SELECT count(*) as count FROM");
		query.append(SPACE).append(PropertyPlaceHolderUtil.getStringProperty(BUCKET_NAME));

		while (!isDBRunning && counter <= healthCheckAttempts) {
			try {
				isDBRunning = executeQuery(query);
				counter++;
			} catch (final Exception e) {
				LOG.error("Exception in : {} attempt: ", counter, e);
				counter = manageCounter(counter);
			}
		}
		return isDBRunning;
	}

	/**
	 * Manage counter.
	 *
	 * @param counter
	 *            the counter
	 * @return the int
	 */
	private int manageCounter(int counter) {
		if (counter == healthCheckAttempts) {
			resetBucket();
		}
		counter++;
		return counter;
	}

	/**
	 * Reset bucket.
	 */
	private void resetBucket() {
		LOG.info("----- RESETTING BUCKET----:{} ", env.getProperty(BUCKET_NAME));
		this.couchbaseBucket.close();
		this.couchbaseBucket = this.myCluster.openBucket(env.getProperty(BUCKET_NAME));
	}

	/**
	 * Execute query.
	 *
	 * @param query
	 *            the query
	 * @return true, if successful
	 */
	private boolean executeQuery(StringBuilder query) {
		boolean result = true;
		final N1qlQueryResult queryResult = this.couchbaseBucket.query(N1qlQuery.simple(query.toString()), 2000,
				TimeUnit.MILLISECONDS);
		final Object count = queryResult.allRows().get(0).value().get(ROW_COUNT);
		if (count == null) {
			result = false;
		}
		return result;
	}
}
