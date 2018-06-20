package com.identity.manager.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.identity.platform.auth.constant.PlatformConstants.ErrorLabelKey;
import com.identity.platform.auth.constant.PlatformConstants.Info;
import com.identity.platform.service.AbstractHealthService;
import com.identity.platform.utils.error.PlatformErrorCodes;
import com.identity.platform.utils.error.exception.PlatformException;
import com.identity.platform.utils.error.exception.PlatformExceptionTranslatorUtil;
import com.identity.platform.utils.error.exception.PropertyPlaceHolderUtil;

@Component
public class IAMHealthService extends AbstractHealthService {

	@Override
	public Map<String, Boolean> checkHealthApartFromDB() {
		return new HashMap<>();
	}
	
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

}
