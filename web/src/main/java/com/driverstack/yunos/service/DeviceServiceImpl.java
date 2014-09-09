package com.driverstack.yunos.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.domain.Vendor;

@Component
public class DeviceServiceImpl extends AbstractService implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

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
}
