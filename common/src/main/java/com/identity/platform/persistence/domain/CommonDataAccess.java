package com.identity.platform.persistence.domain;

import com.identity.platform.utils.error.exception.PlatformException;

public interface CommonDataAccess {

	boolean ping() throws PlatformException;

}
