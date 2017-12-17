package com.identity.manager.test.dao;

import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
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
public class UserDaoIntegrationTest extends AbstractDaoIntegrationTest {

	@Rule
	public TestName daoIntegrations = new TestName();

	@Before
	public void init() {
		Assert.assertNotNull(roleDao);
		Assert.assertNotNull(userDao);
	}

	public void testCreateNewRole() throws Exception {
		Role role = createRole(DomainObjectEnum.ADMIN_ROLE.getValue());
		Role retrievedRole = roleDao.findOne(role.getId());
		Assert.assertNotNull(retrievedRole);
	}
	
	public void testCreateNewCompany() throws Exception {
		Company compnay = createCompany("Nucleus Software", "NSE");
		Role retrievedRole = roleDao.findOne(compnay.getId());
		Assert.assertNotNull(retrievedRole);
	}

	@Test
	public void testCreateNewUser() throws Exception {
		User user = createUser("shas0312", "shas0312.muj@gmail.com");
		User retrievedUser = userDao.findOne(user.getId());
		Assert.assertNotNull(retrievedUser);
	}
	
	public void testCreateNewUserProfile() throws Exception {

		String username = daoIntegrations.getMethodName();
		String email = daoIntegrations.getMethodName() + "@devopsbuddy.com";

		User basicUser = createUser(username, email);

		User newlyCreatedUser = userDao.findOne(basicUser.getId());
		
		Assert.assertNotNull(newlyCreatedUser);
		Assert.assertTrue(newlyCreatedUser.getId() != 0);
		
		Role role = createRole(DomainObjectEnum.MANAGE_USER_ROLE.getValue());
		Role newlyCreatedRole = roleDao.findOne(role.getId());
		
		Assert.assertNotNull(newlyCreatedRole);
		Assert.assertNotNull(newlyCreatedRole.getId());
		
		manageUserProfile(newlyCreatedRole, newlyCreatedUser);
		Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
		for (UserRole ur : newlyCreatedUserUserRoles) {
			Assert.assertNotNull(ur.getRole());
			Assert.assertNotNull(ur.getRole().getId());
		}

	}
	
	public void testDeleteUser() throws Exception {

		String username = daoIntegrations.getMethodName();
		String email = daoIntegrations.getMethodName() + "@devopsbuddy.com";

		User basicUser = createUser(username, email);
		userDao.delete(basicUser.getId());
	}

	public void testGetUserByEmail() throws Exception {
		String email = daoIntegrations.getMethodName() + "@devopsbuddy.com";

		User newlyFoundUser = userDao.findByEmail(email);
		Assert.assertNotNull(newlyFoundUser);
		Assert.assertNotNull(newlyFoundUser.getId());
	}

	public void testUpdateUserPassword() throws Exception {
		String username = daoIntegrations.getMethodName();
		String email = daoIntegrations.getMethodName() + "@devopsbuddy.com";

		User user = createUser(username, email);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());

		String newPassword = UUID.randomUUID().toString();

		userDao.updateUserPassword(user.getId(), newPassword);

		user = userDao.findOne(user.getId());
		Assert.assertEquals(newPassword, user.getPassword());

	}

}
