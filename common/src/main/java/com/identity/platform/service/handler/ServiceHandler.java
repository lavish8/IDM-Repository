package com.identity.platform.service.handler;

import java.util.Map;

import com.identity.platform.utils.client.AbstractResponse;
import com.identity.platform.utils.error.exception.PlatformException;

public interface ServiceHandler {
	AbstractResponse handle(Map<String, Object> input) throws PlatformException;
}
