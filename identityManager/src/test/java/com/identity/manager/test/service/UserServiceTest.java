package com.identity.manager.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.identity.manager.config.ModelMapperConfig;
import com.identity.manager.enums.DomainObjectEnum.COMPANY_TYPE;
import com.identity.manager.exception.InvalidDataException;
import com.identity.manager.persistence.dao.CompanyDao;
import com.identity.manager.persistence.dao.EntityTypeDao;
import com.identity.manager.persistence.dao.RoleDao;
import com.identity.manager.persistence.dao.StatusDao;
import com.identity.manager.persistence.dao.UserDao;
import com.identity.manager.persistence.dao.UserRepositoryDao;
import com.identity.manager.persistence.domain.Company;
import com.identity.manager.persistence.domain.User;
import com.identity.manager.service.UserService;
import com.identity.manager.test.service.util.UserUtil;
import com.identity.manager.web.domain.UserPojo;

@RunWith(PowerMockRunner.class)
public class UserServiceTest {	
 
    @InjectMocks
    private UserService<UserPojo,Long> userService;
    
    @Mock
	private RoleDao roleDao;

    @Mock
	private UserDao userDao;

    @Mock
	private UserRepositoryDao userRepositoryDao;

    @Mock
	private CompanyDao companyDao;

    @Mock
	private StatusDao statusDao;

    @Mock
	private EntityTypeDao entityTypeDao;

    @Mock
	private ModelMapperConfig modelMapper;

    @Mock
	private PasswordEncoder passwordEncoder;
    
    protected static final String username = "test";
	protected static final String email = username + "@devopsbuddy.com";
	protected static final String company = "Nucleus Software";
	protected static final String companyCode = "NSE";
	protected static final COMPANY_TYPE companyType = COMPANY_TYPE.IT;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expected=InvalidDataException.class)
	public void createUser() throws Exception {
    	findByLogin();
		UserPojo entity = UserUtil.createUser(username, email, companyCode);
		UserPojo createdEntity =null;// userDao.save(new User());
		Company userCompany = companyDao.findByCode(entity.getCompany());
		UserPojo retrievedUser = (UserPojo) userService.findByLogin(createdEntity.getLogin());
		Assert.assertNotNull(retrievedUser);
	}
    
    @Test
   	public void findByLogin() throws Exception {
    	User user =userDao.findByLogin(username);
   		Assert.assertNotNull(user);
   	}
}


