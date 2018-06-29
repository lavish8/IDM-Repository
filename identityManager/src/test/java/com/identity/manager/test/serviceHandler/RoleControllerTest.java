/**
 * 
 */
package com.identity.manager.test.serviceHandler;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.identity.manager.constant.WebConstants;
import com.identity.manager.service.RoleService;
import com.identity.manager.web.controller.RoleController;
import com.identity.manager.web.domain.RolePojo;

/**
 * @author maheshs1
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private RoleController roleController;
	
	@Mock
	private RoleService<RolePojo, Serializable> roleService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(roleController).build();
	}

	/**
	 * Test method for {@link com.identity.manager.service.impl.RoleServiceImpl#save(com.identity.manager.web.domain.RolePojo)}.
	 * @throws Exception 
	 */
	@Test
	public void testSaveS() throws Exception {
		ResultActions result = mockMvc.perform(post(WebConstants.V1_ROLE_URI))
		 	.andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	        //.andExpect(jsonPath("$", hasSize(1)));
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
