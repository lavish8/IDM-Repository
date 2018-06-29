package com.identity.manager.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.identity.platform.auth.constant.PlatformConstants.ErrorLabelKey;
import com.identity.platform.auth.constant.PlatformConstants.HealthEnum;
import com.identity.platform.auth.constant.PlatformConstants.Info;
import com.identity.platform.service.AbstractHealthService;
import com.identity.platform.utils.error.PlatformErrorCodes;
import com.identity.platform.utils.error.exception.PlatformException;
import com.identity.platform.utils.error.exception.PlatformExceptionTranslatorUtil;
import com.identity.platform.utils.error.exception.PropertyPlaceHolderUtil;

@Component
public class IAMHealthService extends AbstractHealthService {

	
	@Value("${api.health.isDbSupported}")
	private boolean isDbSupported=false;

	@Override
	public boolean isDbHealthy() throws PlatformException {
		boolean healthy = false;
		try {
			healthy = couchbaseDataAccessUtil.ping() && mysqlDataAccessUtil.ping();
		} catch (final PlatformException e) {
			healthy = false;
		}

		if (healthy) {
			return healthy;
		}

		/*
		 * If DB is unhealthy the throw PlatformException with appropriate message that
		 * the service is not ready.
		 * 
		 */
		throw PlatformExceptionTranslatorUtil.wrapException(PlatformErrorCodes.INTERNAL_SERVER_ERROR,
				new Object[] { ObjectUtils
						.getDisplayString(PropertyPlaceHolderUtil.getStringProperty(Info.SERVICE_NAME.getValue())), },
				ErrorLabelKey.SERVICE_NOT_READY.getValue());
	}

	@Override
	public boolean isDBSupported() throws PlatformException {
		return isDbSupported;
	}

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		builder.up();
	}

	@Override
	public Builder checkHealthApartFromDB(Builder builder) {
		builder.withDetail(HealthEnum.API_HEALTHY.getValue(), Boolean.TRUE);
		return builder;
	}

}
