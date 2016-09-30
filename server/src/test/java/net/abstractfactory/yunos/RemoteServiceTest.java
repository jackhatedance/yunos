package net.abstractfactory.yunos;

import static junit.framework.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import junit.framework.Assert;
import net.abstractfactory.yunos.remote.vo.AccessToken;
import net.abstractfactory.yunos.remote.vo.ConfigurationItem;
import net.abstractfactory.yunos.remote.vo.Device;
import net.abstractfactory.yunos.remote.vo.DeviceClass;
import net.abstractfactory.yunos.remote.vo.FunctionalDevice;
import net.abstractfactory.yunos.remote.vo.Vendor;
import net.abstractfactory.yunos.service.RemoteService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:/net/abstractfactory/yunos/ServiceTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RemoteServiceTest {

	@Autowired
	private RemoteService remoteService;

	@Test
	public void testAll() throws Exception {
		assertNotNull(remoteService);

		List<net.abstractfactory.yunos.remote.vo.Device> devices = remoteService
				.queryUserDevices("jackding", null);

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

		Device remoteDev = remoteService.getDevice(deviceId, "zh_CN");
		Assert.assertEquals("c0bbb53f-2489-11e4-9fa1-08002785c3ec",
				remoteDev.getDeviceClassId());

		remoteDev.setLocation("Loc1");

		remoteService.updateDevice(remoteDev);

		Device remoteDev2 = remoteService.getDevice(deviceId, "zh_CN");
		Assert.assertEquals("Loc1", remoteDev2.getLocation());

		List<FunctionalDevice> functionalDeviceList = remoteService
				.getFunctionalDevices(deviceId, "zh_CN");
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

		ConfigurationItem urlItem = map.get("url");

		Assert.assertEquals("url", urlItem.getName());
		Assert.assertEquals("http://tianhu.dingjianghao.com:54180/rcweb/",
				urlItem.getValue());

		// test init config dc098964-dd7d-451a-ad2e-e04d7287df78

		String driverId = "12b0bc3b-834f-48b8-bd2d-bfe508c8fc42";
		items = remoteService.getDeviceInitialConfiguration(deviceId, driverId);
		for (ConfigurationItem item : items)
			map.put(item.getName(), item);

		ConfigurationItem hostItem = map.get("host");

		Assert.assertEquals("host", hostItem.getName());
		Assert.assertEquals("0.0.0.0", hostItem.getValue());

		ConfigurationItem portItem = map.get("port");

		Assert.assertEquals("port", portItem.getName());
		Assert.assertEquals("60", portItem.getValue());

	}

	@Test
	public void testAuth() throws Exception {
		AccessToken at = remoteService.requestAccessToken("jackding");
		Assert.assertTrue(at.getKey() != null);

	}
}
