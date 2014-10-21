package com.driverstack.yunos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.domain.Driver;
import com.driverstack.yunos.domain.DriverConfigurationDefinition;
import com.driverstack.yunos.domain.DriverConfigurationDefinitionItem;
import com.driverstack.yunos.remote.vo.ConfigurationItem;

@Component
public class DeviceServiceImpl extends AbstractService implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private DriverService driverService;

	@Override
	public List<Device> listByUserId(String userId, DeviceClass deviceClass) {
		return deviceDao.listByUser(userId, deviceClass);
	}

	@Override
	public String save(Device device) {
		return (String) getCurrentSession().save(device);
	}

	@Override
	public void remove(String deviceId) {
		Session s = getCurrentSession();
		Device d = (Device) s.load(Device.class, deviceId);
		s.delete(d);
	}

	@Override
	public void update(Device device) {
		getCurrentSession().update(device);

	}

	@Override
	public List<com.driverstack.yunos.domain.ConfigurationItem> createConfiguration(
			Device device, Driver driver) {
		Session s = getCurrentSession();

		List<com.driverstack.yunos.domain.ConfigurationItem> userItems = new ArrayList<com.driverstack.yunos.domain.ConfigurationItem>();

		// get configuration definitions from driver
		DriverConfigurationDefinition def = driver.getConfigurationDefinition();

		for (DriverConfigurationDefinitionItem defItem : def.getItems()) {

			String name = defItem.getName();
			com.driverstack.yunos.domain.ConfigurationItem factoryItem = device
					.getCalculatedFactoryValue(name);

			com.driverstack.yunos.domain.ConfigurationItem userItem = null;
			if (factoryItem != null) {
				// clone
				userItem = new com.driverstack.yunos.domain.ConfigurationItem(
						factoryItem);
			} else
				userItem = new com.driverstack.yunos.domain.ConfigurationItem(
						defItem.getName(), defItem.getType(),
						defItem.getDefaultValue());

			userItems.add(userItem);

		}

		return userItems;
	}
}
