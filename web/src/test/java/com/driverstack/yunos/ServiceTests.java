package com.driverstack.yunos;

import static junit.framework.Assert.assertNotNull;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.driverstack.yunos.dao.GenericDao;
import com.driverstack.yunos.domain.ConfigurationItem;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.domain.Driver;
import com.driverstack.yunos.domain.DriverConfigurationDefinitionItem;
import com.driverstack.yunos.domain.Model;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.domain.Vendor;
import com.driverstack.yunos.service.DeviceClassService;
import com.driverstack.yunos.service.DeviceService;
import com.driverstack.yunos.service.DriverService;
import com.driverstack.yunos.service.UserService;

@ContextConfiguration(locations = "classpath:/com/driverstack/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ServiceTests {

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private UserService userService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceClassService deviceClassService;

	@Test
	public void testAll() throws Exception {
		assertNotNull(userService);
		assertNotNull(deviceService);

		User user = userService.getUserByEmail("jack@example.com");
		assertNotNull(user);

		List<Device> devices = deviceService.listByUserId(user.getId());
		assertNotNull(devices);
		Assert.assertFalse(devices.isEmpty());

		List<Vendor> allBrands = genericDao.getAll(Vendor.class);
		for (Vendor b : allBrands)
			System.out.println(":"
					+ b.get(Locale.SIMPLIFIED_CHINESE.toString())
							.getShortName());

		// Brand baihuon = chineseBrands.get(chineseBrands.size() - 1);
		// Assert.assertEquals("zh_CN", baihuon.getLocale());

	}

	@Test
	public void testDeviceClassService() throws Exception {
		// 7eggs
		String vendorId = "c20c8c53-2485-11e4-9fa1-08002785c3ec";
		Vendor vendor = (Vendor) genericDao.get(Vendor.class, vendorId);

		List<DeviceClass> deviceClasses = deviceClassService.find(vendor,
				"zh_CN");

		Assert.assertFalse(deviceClasses.isEmpty());

	}

	@Test
	public void testDeviceService() throws Exception {
		String deviceId = "cb170afb-087f-11e4-b721-08002785c3ec";
		Device dev = (Device) genericDao.get(Device.class, deviceId);

		Map<String, ConfigurationItem> map = dev.getUserConfigurationItems();
		ConfigurationItem item = map.get("port");
		Assert.assertEquals("588", item.getValue());

		// another device
		deviceId = "94a1c5a8-39c9-11e4-8a8f-08002785c3ec";
		dev = (Device) genericDao.get(Device.class, deviceId);

		item = dev.getCalculatedFactoryValue("bits");
		Assert.assertEquals("24", item.getValue());
	}

	@Test
	public void testDriverService() throws Exception {

		// 1. upload driver
		InputStream input = getClass().getResourceAsStream(
				"/sampleDriver/RF-IR-Transmitter-Driver-1.0.jar");
		Serializable driverId = driverService.upload(input);
		Assert.assertNotNull(driverId);

		// 2. retrieve uploaded driver
		Driver d = driverService.get(driverId);

		// check driver configuration definitions
		List<DriverConfigurationDefinitionItem> items = d
				.getConfigurationDefinition().getItems();

		DriverConfigurationDefinitionItem item = items.get(0).get("zh_CN");
		String actualName = item.getDisplayName();
		Assert.assertEquals("主机", actualName);

		// test delete driver
		driverService.delete(driverId);

		// test matching models
		// jack 7eggs multi-function transmitter
		String modelId = "3a2725d7-087e-11e4-b721-08002785c3ec";
		modelId="da50f304-3e26-11e4-8a8f-08002785c3ec";
		
		Model model = (Model) genericDao.load(Model.class, modelId);
		List<Driver> availableDrivers = driverService
				.findAvailableDrivers(model);
		Assert.assertFalse(availableDrivers.isEmpty());

	}
}
