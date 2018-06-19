package com.identity.manager.service.impl;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.identity.manager.config.ModelMapperConfig;
import com.identity.manager.enums.ApplicationEnum;
import com.identity.manager.enums.DomainObjectEnum;
import com.identity.manager.exception.InvalidDataException;
import com.identity.manager.persistence.dao.CompanyDao;
import com.identity.manager.persistence.dao.EntityTypeDao;
import com.identity.manager.persistence.dao.RoleDao;
import com.identity.manager.persistence.dao.StatusDao;
import com.identity.manager.persistence.dao.UserDao;
import com.identity.manager.persistence.dao.UserRepositoryDao;
import com.identity.manager.persistence.domain.Company;
import com.identity.manager.persistence.domain.Role;
import com.identity.manager.persistence.domain.User;
import com.identity.manager.persistence.domain.UserRole;
import com.identity.manager.service.UserService;
import com.identity.manager.web.domain.UserPojo;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService<UserPojo, Long> {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepositoryDao userRepositoryDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private EntityTypeDao entityTypeDao;

	@Autowired
	private ModelMapperConfig modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public <S extends UserPojo> S save(S entity) {
		User localUser = userDao.findByLogin(entity.getLogin());

		if (localUser != null && !entity.isContinue()) {
			log.info("User with username {} and email {} already exist. Nothing will be done. ", entity.getLogin(),
					entity.getEmail());
			throw new InvalidDataException("User with username [" + entity.getLogin() + "] and email ["+entity.getEmail()+ "] already exist. ");
		} else {
			Company userCompany = companyDao.findByCode(entity.getCompany());
			if (userCompany == null) {
				throw new InvalidDataException("Company not found for value of " + entity.getCompany());
			}
			String encryptedPassword = passwordEncoder.encode(entity.getPassword());
			entity.setPassword(encryptedPassword);

			User user = modelMapper.map(entity, User.class);
			
			user.setUniqueIdentifierValue(user.getLogin());
			user.setUniqueIdentifierKey(DomainObjectEnum.UNIQUE_IDENTIFIER_KEY);
			user.setUserRepository(userRepositoryDao.findByName(DomainObjectEnum.USER_EDIRECTORY_REPOSITORY.getValue()));
			user.setCompany(userCompany);
			user.setCreatedBy(userDao.findByLogin("VIVEKC"));
			user.setLastModifiedBy(userDao.findByLogin("VIVEKC"));
			user.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));

			String roleName = entity.isContinue() ? entity.getRoles().iterator().next() : ApplicationEnum.READ_ROLE.getValue();
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

			user.getUserRoles().add(userRole);
			localUser = userDao.save(user);
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
	public UserPojo find(Serializable id) {
		User user = userDao.findOne((Long) id);
		return modelMapper.map(user, UserPojo.class);
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
		User user = userDao.findByLogin(login);
		if (user == null) {
			throw new InvalidDataException("User not found for value of login [{}]" + login);
		}
		return modelMapper.map(user, UserPojo.class);
	}

	@Override
	public UserPojo findByEmail(String email) {
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new InvalidDataException("User not found for value of email [{}]" + email);
		}
		return modelMapper.map(user, UserPojo.class);
	}

	@Override
	public void updateUserPassword(String login, String password) {
		User user = userDao.findByLogin(login);
		if (user == null) {
			throw new InvalidDataException("User not found for value of login [{}]" + login);
		}
		userDao.updateUserPassword(user.getId(), password);
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.UserService#loggedInUser()
	 */
	@Override
	public String loggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new UsernameNotFoundException("Anonymous user found"); 
		}
		return authentication.getName();
	}

	/* (non-Javadoc)
	 * @see com.identity.manager.service.UserService#grantedRoles()
	 */
	@Override
	public Set<String> grantedAuthorities() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
		return AuthorityUtils.authorityListToSet(grantedAuthorities);
	}
}
