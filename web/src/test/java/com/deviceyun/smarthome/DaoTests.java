package com.deviceyun.smarthome;

import static junit.framework.Assert.assertNotNull;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deviceyun.smarthome.dao.mybatisMapper.UserMapper;
import com.deviceyun.smarthome.domain.User;

@ContextConfiguration(locations = "classpath:/com/deviceyun/smarthome/DaoTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFindUserByName() throws Exception {
		assertNotNull(userMapper);
		User user = userMapper.findUserByFirstName("jack");
		Assert.assertNotNull(user);

	}
}
