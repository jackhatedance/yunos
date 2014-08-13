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

import com.deviceyun.yunos.service.RemoteService;

@ContextConfiguration(locations = "classpath:/com/deviceyun/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RemoteServiceTests {

	@Autowired
	private RemoteService remoteService;
	
	@Test
	public void testAll() throws Exception {
		assertNotNull(remoteService);
		 

		 
		 

		List<com.deviceyun.yunos.remote.vo.Device> devices = remoteService.listDevice("jackding");
		
		assertNotNull(devices);
		
		Assert.assertFalse(devices.isEmpty());

	}
}
