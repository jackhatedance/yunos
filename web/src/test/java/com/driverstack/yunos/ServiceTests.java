package com.driverstack.yunos;

import static junit.framework.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.driverstack.yunos.domain.Brand;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.service.BrandService;
import com.driverstack.yunos.service.DeviceService;
import com.driverstack.yunos.service.DriverService;
import com.driverstack.yunos.service.UserService;

@ContextConfiguration(locations = "classpath:/com/driverstack/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ServiceTests {

	@Autowired
	private UserService userService;

	@Autowired
	private BrandService brandService;
	@Autowired
	private DriverService driverService;

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

		List<Brand> chineseBrands = brandService
				.getAllBrands(Locale.SIMPLIFIED_CHINESE.toString());
		// List<Brand> chineseBrands=brandService.getAllBrands(new Locale("en",
		// "us"));
		// for(Brand b : chineseBrands)
		// System.out.println(":"+b.getName());

		Brand baihuon = chineseBrands.get(chineseBrands.size() - 1);
		Assert.assertEquals("zh_CN", baihuon.getLocale());

	}
	
	@Test
	public void testDriverService() throws Exception {
		InputStream input= getClass().getResourceAsStream("/sampleDriver/RF-IR-Transmitter-Driver-1.0.jar");
		driverService.upload(input);		
	}
}
