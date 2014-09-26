package com.driverstack.yunos;

import static junit.framework.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.driverstack.yunos.remote.vo.ConfigurationItem;
import com.driverstack.yunos.remote.vo.Device;
import com.driverstack.yunos.remote.vo.DeviceClass;
import com.driverstack.yunos.remote.vo.FunctionalDevice;
import com.driverstack.yunos.remote.vo.Vendor;
import com.driverstack.yunos.service.RemoteService;

@ContextConfiguration(locations = "classpath:/com/driverstack/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RemoteServiceTests {

	@Autowired
	private RemoteService remoteService;

	@Test
	public void testAll() throws Exception {
		assertNotNull(remoteService);

		List<com.driverstack.yunos.remote.vo.Device> devices = remoteService
				.listDevice("jackding");

		assertNotNull(devices);

		Assert.assertFalse(devices.isEmpty());

	}

	@Test
	public void testVendor() throws Exception {

		// vendor 7eggs
		String vendorId = "c20c8c53-2485-11e4-9fa1-08002785c3ec";

		List<Vendor> vendors = remoteService.getAllVendors("zh_CN");

		Map<String, Vendor> map = new HashMap<String, Vendor>();
		for (Vendor v : vendors)
			map.put(v.getShortName(), v);

		Vendor vendor = map.get("柏煌");

		Assert.assertEquals("f525b8e7-2485-11e4-9fa1-08002785c3ec",
				vendor.getId());
	}

	@Test
	public void testDeviceClass() throws Exception {

		// vendor 7eggs
		String vendorId = "c20c8c53-2485-11e4-9fa1-08002785c3ec";
		String locale = "zh_CN";
		List<DeviceClass> deviceClasses = remoteService.getDeviceClasses(null,
				locale);

		Assert.assertFalse(deviceClasses.isEmpty());
	}

	@Test
	public void testDevice() throws Exception {

		String deviceId = "cb170afb-087f-11e4-b721-08002785c3ec";

		Device remoteDev = remoteService.getDevice(deviceId);
		Assert.assertEquals("c0bbb53f-2489-11e4-9fa1-08002785c3ec",
				remoteDev.getDeviceClassId());

		remoteDev.setLocation("Loc1");

		remoteService.updateDevice(remoteDev);

		Device remoteDev2 = remoteService.getDevice(deviceId);
		Assert.assertEquals("Loc1", remoteDev2.getLocation());

		
		List<FunctionalDevice> functionalDeviceList = remoteService.getFunctionalDevices(deviceId, "zh_CN");
		Assert.assertFalse(functionalDeviceList.isEmpty());
	}

	@Test
	public void testDeviceConfiguration() throws Exception {

		String deviceId = "cb170afb-087f-11e4-b721-08002785c3ec";
		List<ConfigurationItem> items = remoteService
				.getDeviceConfiguration(deviceId);

		Map<String, ConfigurationItem> map = new HashMap<String, ConfigurationItem>();
		for (ConfigurationItem item : items)
			map.put(item.getName(), item);

		ConfigurationItem portItem = map.get("port");

		Assert.assertEquals("port", portItem.getName());
		Assert.assertEquals("57", portItem.getValue());

		// test init config dc098964-dd7d-451a-ad2e-e04d7287df78

		String driverId = "fc6e1b6b-febb-4de6-a3c7-3f7841eb69d9";
		items = remoteService.getDeviceInitialConfiguration(deviceId, driverId);
		for (ConfigurationItem item : items)
			map.put(item.getName(), item);

		ConfigurationItem hostItem = map.get("host");

		Assert.assertEquals("host", hostItem.getName());
		Assert.assertEquals("0.0.0.0", hostItem.getValue());

		portItem = map.get("port");

		Assert.assertEquals("port", portItem.getName());
		Assert.assertEquals("60", portItem.getValue());

	}

}
