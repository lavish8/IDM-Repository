package com.identity.manager.test.dao;

import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.identity.manager.IdentityManagerApplication;
import com.identity.manager.enums.ApplicationEnum;
import com.identity.manager.persistence.domain.Company;
import com.identity.manager.persistence.domain.Role;
import com.identity.manager.persistence.domain.User;
import com.identity.manager.persistence.domain.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IdentityManagerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Sorts by method name
public class UserDaoIntegrationTest extends AbstractDaoIntegrationTest {

	@Rule
	public TestName daoIntegrations = new TestName();

	protected static final String username = "test";
	protected static final String email = username + "@devopsbuddy.com";
	protected static final String company = "Nucleus Software";
	protected static final String companyCode = "NSE";

	@Before
	public void init() {
		Assert.assertNotNull(roleDao);
		Assert.assertNotNull(userDao);
		Assert.assertNotNull(userRepositoryDao);
		Assert.assertNotNull(companyDao);
		Assert.assertNotNull(statusDao);
		Assert.assertNotNull(entityTypeDao);
	}

	@Test
	private void test4CreateNewRole() throws Exception {
		Role adminRole = createRole(ApplicationEnum.ADMIN_ROLE.getValue());
		Role retrievedRole = roleDao.findOne(adminRole.getId());
		Assert.assertNotNull(retrievedRole);
		
		Role userRole = createRole(ApplicationEnum.MANAGE_USER_ROLE.getValue());
		Role retrieveduserRole= roleDao.findOne(userRole.getId());
		Assert.assertNotNull(retrieveduserRole);
		
		Role companyRole = createRole(ApplicationEnum.MANAGE_COMPANY_ROLE.getValue());
		Role retrievedCompanyRole = roleDao.findOne(companyRole.getId());
		Assert.assertNotNull(retrievedCompanyRole);
	}

	@Test
	private void test5CreateNewCompany() throws Exception {
		Company newCompany = createCompany(company, companyCode);
		Company retrievedCompany = companyDao.findByCode(newCompany.getCode());
		Assert.assertNotNull(retrievedCompany);
		
		test4CreateNewRole();
	}

	@Test
	private void test1CreateNewUser() throws Exception {
		User user = createUser(username, email, companyCode);
		User retrievedUser = userDao.findOne(user.getId());
		Assert.assertNotNull(retrievedUser);
	}

	@Test
	private void test6CreateNewUserProfile() throws Exception {
		User newlyCreatedUser = userDao.findByLogin(username);
		Assert.assertNotNull(newlyCreatedUser);
		Assert.assertTrue(newlyCreatedUser.getId() != 0);

		Role newlyCreatedRole = roleDao.findByName(ApplicationEnum.ADMIN_ROLE.getValue());
		Assert.assertNotNull(newlyCreatedRole);
		Assert.assertNotNull(newlyCreatedRole.getId());

		manageUserProfile(newlyCreatedRole, newlyCreatedUser);
		Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
		for (UserRole ur : newlyCreatedUserUserRoles) {
			Assert.assertNotNull(ur.getRole());
			Assert.assertNotNull(ur.getRole().getId());
		}

	}

	@Test
	private void test7DeleteUser() throws Exception {
		User user = userDao.findByLogin(username);
		userDao.delete(user.getId());
		User old = userDao.findByEmail(email);
		Assert.assertNull(old);
	}

	@Test
	private void test3GetUserByEmail() throws Exception {
		User newlyFoundUser = userDao.findByEmail(email);
		Assert.assertNotNull(newlyFoundUser);
		Assert.assertNotNull(newlyFoundUser.getId());
	}

	@Test
	private void test2UpdateUserPassword() throws Exception {
		User user = userDao.findByLogin(username);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());

		String newPassword = UUID.randomUUID().toString();
		userDao.updateUserPassword(user.getId(), newPassword);

		user = userDao.findOne(user.getId());
		Assert.assertEquals(newPassword, user.getPassword());
	}

}
