package net.abstractfactory.yunos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.dao.DeviceDao;
import net.abstractfactory.yunos.domain.Device;
import net.abstractfactory.yunos.domain.DeviceClass;
import net.abstractfactory.yunos.domain.Driver;
import net.abstractfactory.yunos.domain.DriverConfigurationDefinition;
import net.abstractfactory.yunos.domain.DriverConfigurationDefinitionItem;
import net.abstractfactory.yunos.remote.vo.ConfigurationItem;

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
	public List<net.abstractfactory.yunos.domain.ConfigurationItem> createConfiguration(
			Device device, Driver driver) {
		Session s = getCurrentSession();

		List<net.abstractfactory.yunos.domain.ConfigurationItem> userItems = new ArrayList<net.abstractfactory.yunos.domain.ConfigurationItem>();

		// get configuration definitions from driver
		DriverConfigurationDefinition def = driver.getConfigurationDefinition();

		for (DriverConfigurationDefinitionItem defItem : def.getItems()) {

			String name = defItem.getName();
			net.abstractfactory.yunos.domain.ConfigurationItem factoryItem = device
					.getCalculatedFactoryValue(name);

			net.abstractfactory.yunos.domain.ConfigurationItem userItem = null;
			if (factoryItem != null) {
				// clone
				userItem = new net.abstractfactory.yunos.domain.ConfigurationItem(
						factoryItem);
			} else
				userItem = new net.abstractfactory.yunos.domain.ConfigurationItem(
						defItem.getName(), defItem.getType(),defItem.getTypeParameter(),
						defItem.getDefaultValue());

			userItems.add(userItem);

		}

		return userItems;
	}
}
