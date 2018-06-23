package com.identity.platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.identity.platform.auth.constant.PlatformConstants.HealthEnum;
import com.identity.platform.persistence.domain.CommonDataAccess;
import com.identity.platform.utils.error.exception.PlatformException;

@Component
public abstract class AbstractHealthService implements HealthIndicator {
	
	
	private static final Logger log = LoggerFactory.getLogger(AbstractHealthService.class);


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
	@Override
	public org.springframework.boot.actuate.health.Health health() {
		Health.Builder builder = new Health.Builder();
		try {
			if (this.isDBSupported()) {
				builder.withDetail(HealthEnum.DB_HEALTHY.getValue(), this.isDbHealthy());
			}
			this.checkHealthApartFromDB(builder);
			doHealthCheck(builder);
		}
		catch (Exception ex) {
			log.warn("Health check failed", ex);
			builder.down(ex);
		}
		return builder.build();
	
	}
	
	/**
	 * Actual health check logic.
	 * @param builder the {@link Builder} to report health status and details
	 * @throws Exception any {@link Exception} that should create a {@link Status#DOWN}
	 * system status.
	 */
	protected abstract void doHealthCheck(Health.Builder builder) throws Exception;

	/**
	 * Check health apart from DB.
	 *
	 * @return the map
	 */
	public abstract Builder checkHealthApartFromDB(Health.Builder builder);

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
