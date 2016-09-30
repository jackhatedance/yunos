package net.abstractfactory.yunos.service;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.abstractfactory.yunos.domain.User;
import net.abstractfactory.yunos.service.UserService;

@ContextConfiguration(locations = "classpath:/net/abstractfactory/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceImplTest {
	@Autowired
	private UserService userService;

	@Test
	public void testGetUserByEmail() {
		User user = userService.getUserByEmail("jack@example.com");
		assertNotNull(user);
	}

	@Test
	public void testCreateUser() {
		User newUser1 = new User("jack12345", "pass", "jack12345", "ding",
				"jack@example.com");
		User newUser2 = new User("jack12345", "pass", "jack12345", "ding",
				"jack@example.com");

			userService.createUser(newUser1);

		try {
			userService.createUser(newUser2);
			fail();
		} catch (Exception e) {
			// expect a duplicate exception

		}

	}
}
