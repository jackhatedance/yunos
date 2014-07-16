package com.deviceyun.yunos.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.domain.Device;

@Component
public class DeviceDaoImpl implements DeviceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Device get(String id) {
		Session session = sessionFactory.getCurrentSession();
		return (Device) session.load(Device.class, id);
	}
}
