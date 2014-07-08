package com.deviceyun.smarthome;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deviceyun.smarthome.service.UserService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationTests {

	@Autowired
	private UserService service;

	@Test
	public void testGetFirstUser() throws Exception {
		assertNotNull(service);
		// assertEquals("Customer1", service.getFirstUser());
	}

	@Test
	public void testFindUserByName() throws Exception {
		assertNotNull(service);
		// assertEquals("0", service.findUserByName("Customer1"));
		// assertEquals("1", service.findUserByName("Customer2"));
	}

}
