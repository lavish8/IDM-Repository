package com.identity.manager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.identity.platform.utils.I18NUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentityManagerApplicationTests {
	
	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(IdentityManagerApplicationTests.class);
	
	@Autowired
	private I18NUtils i18NService;
	
	@Test
	public void contextLoads() {
		
	}

	@Test
	public void testMessageByLocaleService() throws Exception {
		String expectedResult = "Bootstrap starter template";
		String messageId = "index.main.header.h1";
		String actual = i18NService.getMessage(messageId);
		LOG.info("--------------"+actual+"------------");
		
		Assert.assertEquals("The actual and expected Strings don't match", expectedResult, actual);
	}

}
