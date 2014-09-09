package com.driverstack.yunos.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.domain.Device;

@Component
public class DeviceClassServiceImpl implements DeviceService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DeviceDao deviceDao;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Device> listByUserId(String userId) {
		return deviceDao.listByUser(userId);
	}

	@Override
	public String saveDevice(Device device) {
		return (String) getCurrentSession().save(device);
	}

	@Override
	public void remove(String deviceId) {
		Session s = getCurrentSession();
		Device d = (Device) s.load(Device.class, deviceId);
		s.delete(s);
	}
}
