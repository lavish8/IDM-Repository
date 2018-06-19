package com.identity.manager.test.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.identity.manager.enums.DomainObjectEnum;
import com.identity.manager.persistence.dao.CompanyDao;
import com.identity.manager.persistence.dao.EntitytypeDao;
import com.identity.manager.persistence.dao.RoleDao;
import com.identity.manager.persistence.dao.StatusDao;
import com.identity.manager.persistence.dao.UserDao;
import com.identity.manager.persistence.dao.UserRepositoryDao;
import com.identity.manager.persistence.domain.Company;
import com.identity.manager.persistence.domain.Role;
import com.identity.manager.persistence.domain.User;
import com.identity.manager.persistence.domain.UserRole;
import com.identity.manager.test.dao.util.CompanyUtil;
import com.identity.manager.test.dao.util.UserUtil;

public abstract class AbstractDaoIntegrationTest {

	@Autowired
	protected RoleDao roleDao;

	@Autowired
	protected UserDao userDao;

	@Autowired
	protected UserRepositoryDao userRepositoryDao;

	@Autowired
	protected CompanyDao companyDao;

	@Autowired
	protected StatusDao statusDao;

	@Autowired
	protected EntitytypeDao entityTypeDao;

	protected Role createRole(String roleName) {
		Role role = new Role();
		role.setCreatedBy(userDao.findByLogin("VIVEKC"));
		role.setDescription(roleName);
		role.setEntityType(entityTypeDao.findByName(DomainObjectEnum.ENTITY_TYPE_ROLE.getValue()));
		role.setLastModifiedBy(userDao.findByLogin("VIVEKC"));
		role.setName(roleName);
		role.setObsoleteDate(LocalDate.now());
		role.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));
		role.setStatusDate(LocalDateTime.now());
		return roleDao.save(role);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	protected Company createCompany(String name, String code) {
		Company company = CompanyUtil.createCompany(name, code);
		company.setCreatedBy(userDao.findByLogin("VIVEKC"));
		company.setEntityType(entityTypeDao.findByName(DomainObjectEnum.ENTITY_TYPE_COMPANY.getValue()));
		company.setLastModifiedBy(userDao.findByLogin("VIVEKC"));
		company.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));
		return companyDao.save(company);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	protected User createUser(String username, String email, String company) {
		User user = UserUtil.createUser(username, email);
		user.setUserRepository(userRepositoryDao.findByName(DomainObjectEnum.USER_LOCAL_REPOSITORY.getValue()));
		user.setCompany(companyDao.findByCode(company));
		user.setCreatedBy(userDao.findByLogin("VIVEKC"));
		user.setLastModifiedBy(userDao.findByLogin("VIVEKC"));
		user.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));
		return userDao.save(user);
	}

	protected void manageUserProfile(Role role, User user) {
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRole.setCreatedBy(userDao.findByLogin("VIVEKC"));
		userRole.setLastModifiedBy(userDao.findByLogin("VIVEKC"));
		userRole.setObsoleteDate(LocalDate.now());
		userRole.setPriority(8L);
		userRole.setStatus(statusDao.findByName(DomainObjectEnum.STATUS_ACTIVE.getValue()));
		userRole.setStatusDate(LocalDateTime.now());

		if (null == user.getUserRoles()) {
			Set<UserRole> userRoles = new HashSet<UserRole>();
			userRoles.add(userRole);
			user.setUserRoles(userRoles);
		} else {
			user.getUserRoles().add(userRole);
		}
		userDao.save(user);
	}
}
