package com.identity.platform.persistence.util;

import org.springframework.stereotype.Repository;

import com.identity.platform.persistence.domain.CommonDataAccess;
import com.identity.platform.utils.error.exception.PlatformException;

@Repository
public class MysqlDataAccessUtil implements CommonDataAccess {

	@Override
	public boolean ping() throws PlatformException {
		return true;
	}

}
