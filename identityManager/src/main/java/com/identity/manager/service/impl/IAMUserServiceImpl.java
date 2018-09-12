package com.identity.manager.service.impl;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.identity.manager.config.ModelMapperConfig;
import com.identity.manager.enums.DomainObjectEnum;
import com.identity.manager.exception.InvalidDataException;
import com.identity.manager.persistence.dao.IAMCompanyDao;
import com.identity.manager.persistence.dao.IAMUserDao;
import com.identity.manager.persistence.dao.StatusDao;
import com.identity.manager.persistence.domain.CompanyDoc;
import com.identity.manager.persistence.domain.IAMUser;
import com.identity.manager.service.UserService;
import com.identity.manager.web.domain.UserPojo;
import com.identity.platform.utils.error.exception.PlatformExceptionTranslatorUtil;

import reactor.core.publisher.Mono;

@Service("iamUserServiceImpl")
public class IAMUserServiceImpl implements UserService<UserPojo, Long>, ReactiveUserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(IAMUserServiceImpl.class);

	@Autowired
	private IAMUserDao iamUserDao;

	@Autowired
	private ModelMapperConfig modelMapper;

	@Autowired
	private IAMCompanyDao companyDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StatusDao statusDao;
	
	/*@Autowired
	private UserRepositoryDao userRepositoryDao;*/
	
	@Override
	public <S extends UserPojo> S save(S entity) {
		Mono<IAMUser> localUser = iamUserDao.findByLogin(entity.getLogin());
		// isContinue=false for create user isContinue=true for user update
		if (ObjectUtils.isEmpty(localUser) && !entity.isContinue()) {

			log.info("User with username {} and email {} already exist. Nothing will be done. ", entity.getLogin(),
					entity.getEmail());
			throw new InvalidDataException("User with username [" + entity.getLogin() + "] and email ["
					+ entity.getEmail() + "] already exist. ");
		} else {
			CompanyDoc userCompany = companyDao.findByCode(entity.getCompany());
			if (userCompany == null) {
				throw new InvalidDataException("Company not found for value of " + entity.getCompany());
			}
			String encryptedPassword = passwordEncoder.encode(entity.getPassword());
			entity.setPassword(encryptedPassword);

			IAMUser user = modelMapper.map(entity, IAMUser.class);

			user.setUniqueIdentifierValue(user.getLogin());
			user.setUniqueIdentifierKey(DomainObjectEnum.UNIQUE_IDENTIFIER_KEY);
			/*user.setUserRepository(
					userRepositoryDao.findByName(DomainObjectEnum.USER_EDIRECTORY_REPOSITORY.getValue()));*/
			user.setCompany(userCompany);
			user.setCreatedBy(iamUserDao.findByLogin("VIVEKC"));
			user.setLastModifiedBy(iamUserDao.findByLogin("VIVEKC"));
			user.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));

			/*String roleName = entity.isContinue() ? entity.getRoles().iterator().next()
					: ApplicationEnum.READ_ROLE.getValue();
			Role role = roleDao.findByName(roleName);
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);
			userRole.setCreatedBy(userDao.findByLogin("VIVEKC"));
			userRole.setLastModifiedBy(userDao.findByLogin("VIVEKC"));
			userRole.setObsoleteDate(LocalDate.now());
			userRole.setPriority(8L);
			userRole.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));
			userRole.setStatusDate(LocalDateTime.now());

			user.getUserRoles().add(userRole);*/
			localUser = iamUserDao.save(user);
		}
		return (S) modelMapper.map(localUser, UserPojo.class);

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
		Mono<IAMUser> user = iamUserDao.findById((Long) id);
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

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return Mono.justOrEmpty(User.withUserDetails((UserDetails) iamUserDao.findByLogin(username)).build());
	}
}
