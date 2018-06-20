package com.identity.platform.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.identity.platform.auth.constant.PlatformConstants.Health;
import com.identity.platform.persistence.domain.CommonDataAccess;
import com.identity.platform.utils.error.exception.PlatformException;

@Component
public abstract class AbstractHealthService {

	/** The data access util. */
	@Autowired
	@Qualifier(value = "couchbaseDataAccessUtil")
	protected CommonDataAccess couchbaseDataAccessUtil;
	
	@Autowired
	@Qualifier(value  = "mysqlDataAccessUtil")
	protected CommonDataAccess mysqlDataAccessUtil;
	
	/**
	 * Return the map which contains api health status , db health status etc. If
	 * not health, then raise platform exception
	 *
	 * @return Map
	 * @throws PlatformException
	 *             the platform exception
	 */
	public final Map<String, Boolean> health() throws PlatformException {
		final Map<String, Boolean> healthStats = new HashMap<>();
		healthStats.put(Health.API_HEALTHY.getValue(), Boolean.TRUE);
		if (this.isDBSupported()) {
			healthStats.put(Health.DB_HEALTHY.getValue(), this.isDbHealthy());
		}
		healthStats.putAll(this.checkHealthApartFromDB());
		return healthStats;
	}

	/**
	 * Check health apart from DB.
	 *
	 * @return the map
	 */
	public abstract Map<String, Boolean> checkHealthApartFromDB();

	/**
	 * Return true if DB is healthy.
	 *
	 * @return true
	 * @throws PlatformException
	 *             if DB is not healthy
	 */
	public abstract boolean isDbHealthy() throws PlatformException;

	/**
	 * Checks if is DB supported.
	 *
	 * @return true, if is DB supported
	 * @throws PlatformException
	 *             the platform exception
	 */
	public abstract boolean isDBSupported() throws PlatformException;

}
