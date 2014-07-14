package com.deviceyun.yunos;

import static junit.framework.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deviceyun.yunos.core.DeviceManager;

@ContextConfiguration(locations = "classpath:/com/deviceyun/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ServiceTests {

	@Autowired
	private DeviceManager deviceManager;

	@Test
	public void testDeviceManager() throws Exception {
		assertNotNull(deviceManager);

		// deviceManager.
	}
}
