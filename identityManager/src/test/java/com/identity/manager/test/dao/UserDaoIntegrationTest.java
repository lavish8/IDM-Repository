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
import com.identity.manager.enums.DomainObjectEnum;
import com.identity.manager.persistence.domain.Company;
import com.identity.manager.persistence.domain.Role;
import com.identity.manager.persistence.domain.User;
import com.identity.manager.persistence.domain.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IdentityManagerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Sorts by method name
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
	}

	@Test
	public void test4CreateNewRole() throws Exception {
		Role role = createRole(DomainObjectEnum.ADMIN_ROLE.getValue());
		Role retrievedRole = roleDao.findOne(role.getId());
		Assert.assertNotNull(retrievedRole);
	}
	
	@Test
	public void test5FindNewCompany() throws Exception {
		Company retrievedCompany = companyDao.findByName(company);
		Assert.assertNotNull(retrievedCompany);
	}

	@Test
	public void test1CreateNewUser() throws Exception {		
		User user = createUser(username, email);
		User retrievedUser = userDao.findOne(user.getId());
		Assert.assertNotNull(retrievedUser);
	}
	
	@Test
	public void test6CreateNewUserProfile() throws Exception {
		User newlyCreatedUser = userDao.findByLogin(username);		
		Assert.assertNotNull(newlyCreatedUser);
		Assert.assertTrue(newlyCreatedUser.getId() != 0);
		
		Role newlyCreatedRole = roleDao.findByName(DomainObjectEnum.ADMIN_ROLE.getValue());		
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
	public void test7DeleteUser() throws Exception {
		User user =  userDao.findByLogin(username);
		userDao.delete(user.getId());
		User old = userDao.findByEmail(email);
		Assert.assertNull(old);
	}

	@Test
	public void test3GetUserByEmail() throws Exception {
		User newlyFoundUser = userDao.findByEmail(email);
		Assert.assertNotNull(newlyFoundUser);
		Assert.assertNotNull(newlyFoundUser.getId());
	}

	@Test
	public void test2UpdateUserPassword() throws Exception {
		User user =  userDao.findByLogin(username);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());

		String newPassword = UUID.randomUUID().toString();
		userDao.updateUserPassword(user.getId(), newPassword);

		user = userDao.findOne(user.getId());
		Assert.assertEquals(newPassword, user.getPassword());
	}

}
