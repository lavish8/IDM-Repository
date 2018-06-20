package com.identity.manager.service.impl;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.identity.manager.config.ModelMapperConfig;
import com.identity.manager.persistence.dao.UserDao;
import com.identity.manager.persistence.domain.User;
import com.identity.manager.service.UserService;
import com.identity.manager.web.domain.UserPojo;
import com.identity.platform.utils.error.exception.PlatformExceptionTranslatorUtil;

@Service
public class IAMUserServiceImpl implements UserService<UserPojo, Long> {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapperConfig modelMapper;

	@Override
	public <S extends UserPojo> S save(S entity) {
		return null;
	}

	@Override
	public <S extends UserPojo> Iterable<S> save(Iterable<S> entities) {
		return null;
	}

	@Override
	public Iterable<UserPojo> findAll(Iterable<UserPojo> ids) {
		return null;
	}

	@Override
	public UserPojo findOne(Serializable id) {
		if (Objects.isNull(id)) {
			PlatformExceptionTranslatorUtil.raiseNotFoundException();
		}
		User user = userDao.findOne((Long) id);
		return modelMapper.map(user, UserPojo.class);
	}

	@Override
	public UserPojo find(UserPojo criteria) {
		return null;
	}

	@Override
	public void delete(Serializable id) {
		
	}

	@Override
	public void delete(UserPojo entity) {
		
	}

	@Override
	public void delete(Iterable<? extends UserPojo> entities) {
		
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public UserPojo findByLogin(String login) {
		return null;
	}

	@Override
	public UserPojo findByEmail(String email) {
		return null;
	}

	@Override
	public void updateUserPassword(String login, String password) {
		
	}

	@Override
	public String loggedInUser() {
		return null;
	}

	@Override
	public Set<String> grantedAuthorities() {
		return null;
	}


}
