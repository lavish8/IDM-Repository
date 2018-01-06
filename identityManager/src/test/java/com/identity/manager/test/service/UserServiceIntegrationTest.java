package com.identity.manager.test.service;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.identity.manager.IdentityManagerApplication;
import com.identity.manager.enums.ApplicationEnum;
import com.identity.manager.enums.DomainObjectEnum.COMPANY_TYPE;
import com.identity.manager.service.UserService;
import com.identity.manager.test.service.util.UserUtil;
import com.identity.manager.web.domain.UserPojo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IdentityManagerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Sorts by method name
public class UserServiceIntegrationTest {

	@Autowired
	private UserService<UserPojo, Long> userService;

	/*
	 * @Autowired private CompanyService<CompanyPojo, Long> companyService;
	 */

	protected static final String username = "test";
	protected static final String email = username + "@devopsbuddy.com";
	protected static final String company = "Nucleus Software";
	protected static final String companyCode = "NSE";
	protected static final COMPANY_TYPE companyType = COMPANY_TYPE.IT;

	@Before
	public void init() {
		Assert.assertNotNull(userService);
	}

	/*
	 * @Test private void test4CreateNewCompany() throws Exception { CompanyPojo
	 * newCompany = CompanyUtil.createCompany(company, companyCode,
	 * companyType); Company retrievedCompany =
	 * companyDao.findByCode(newCompany.getCode());
	 * Assert.assertNotNull(retrievedCompany);
	 * 
	 * test4CreateNewRole(); }
	 */

	@Test
	private void test1CreateNewUser() throws Exception {
		UserPojo entity = UserUtil.createUser(username, email, companyCode);
		UserPojo createdEntity = userService.save(entity);
		UserPojo retrievedUser = userService.findByLogin(createdEntity.getLogin());
		Assert.assertNotNull(retrievedUser);
	}

	@Test
	private void test5CreateNewUserProfile() throws Exception {
		UserPojo newlyCreatedUser = userService.findByLogin(username);
		Assert.assertNotNull(newlyCreatedUser);

		newlyCreatedUser.getRoles().add(ApplicationEnum.ADMIN_ROLE.getValue());
		UserPojo updatedEntity = userService.save(newlyCreatedUser);

		Assert.assertTrue(updatedEntity.getRoles().contains(ApplicationEnum.ADMIN_ROLE.getValue()));
	}

	@Test
	private void test6DeleteUser() throws Exception {
		UserPojo user = userService.findByLogin(username);
		userService.delete(user);
		UserPojo old = userService.findByEmail(email);
		Assert.assertNull(old);
	}

	@Test
	private void test2UpdateUserPassword() throws Exception {
		UserPojo user = userService.findByLogin(username);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getLogin());

		String newPassword = UUID.randomUUID().toString();
		user.setPassword(newPassword);
		userService.save(user);

		Assert.assertEquals(newPassword, user.getPassword());
	}

	@Test
	private void test3GetUserByEmail() throws Exception {
		UserPojo newlyFoundUser = userService.findByEmail(email);
		Assert.assertNotNull(newlyFoundUser);
		Assert.assertNotNull(newlyFoundUser.getLogin());
	}

}
