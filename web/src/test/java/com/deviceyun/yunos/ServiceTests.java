package com.deviceyun.yunos;

import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deviceyun.yunos.domain.Device;
import com.deviceyun.yunos.domain.User;
import com.deviceyun.yunos.service.DeviceService;
import com.deviceyun.yunos.service.UserService;

@ContextConfiguration(locations = "classpath:/com/deviceyun/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ServiceTests {

	@Autowired
	private UserService userService;
	@Autowired
	private DeviceService deviceService;

	@Test
	public void testAll() throws Exception {
		assertNotNull(userService);
		assertNotNull(deviceService);

		User user = userService.getUserByEmail("jack@example.com");
		assertNotNull(user);

		List<Device> devices = deviceService.listByUserId(user.getId());
		assertNotNull(devices);
		Assert.assertFalse(devices.isEmpty());

	}
}
