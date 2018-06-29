/**
 * 
 */
package com.identity.manager.test.service;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.identity.manager.service.RoleService;
import com.identity.manager.web.domain.RolePojo;

/**
 * @author maheshs1
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceIntegrationTest {
	
	@InjectMocks
	private RoleService<RolePojo, Long> roleService;
	
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Test method for {@link com.identity.manager.service.impl.RoleServiceImpl#save(com.identity.manager.web.domain.RolePojo)}.
	 */
	@Test
	public void testSaveS() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.identity.manager.service.impl.RoleServiceImpl#find(java.io.Serializable)}.
	 */
	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.identity.manager.service.impl.RoleServiceImpl#delete(java.io.Serializable)}.
	 */
	@Test
	public void testDeleteSerializable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.identity.manager.service.impl.RoleServiceImpl#findByName(java.lang.String)}.
	 */
	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

}
