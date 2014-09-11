package com.driverstack.yunos.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.domain.Device;
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
	public List<Device> listByUserId(String userId) {
		return deviceDao.listByUser(userId);
	}

	@Override
	public String save(Device device) {
		return (String) getCurrentSession().save(device);
	}

	@Override
	public void remove(String deviceId) {
		Session s = getCurrentSession();
		Device d = (Device) s.load(Device.class, deviceId);
		s.delete(s);
	}

	@Override
	public void update(Device device) {
		getCurrentSession().update(device);

	}

	@Override
	public void initConfiguration(Device device) {
		Session s = getCurrentSession();
		
		//get configuration definitions from driver
		Driver driver = device.getDriver();
		DriverConfigurationDefinition def = driver.getConfigurationDefinition();
		
		Map<String, com.driverstack.yunos.domain.ConfigurationItem> userItemsMap= device.getUserConfigurationItems();		
		userItemsMap.clear();
		
		for(DriverConfigurationDefinitionItem defItem : def.getItems())
		{
			
			 String name = defItem.getName(); 
			 com.driverstack.yunos.domain.ConfigurationItem factoryItem =  device.getCalculatedFactoryValue(name);
			 
			 //clone
			 com.driverstack.yunos.domain.ConfigurationItem userItem = new com.driverstack.yunos.domain.ConfigurationItem(factoryItem);
			
			 userItemsMap.put(name, userItem);			
		}
		
	}
}
