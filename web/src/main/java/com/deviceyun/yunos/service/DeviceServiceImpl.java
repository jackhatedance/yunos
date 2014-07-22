package com.deviceyun.yunos.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.dao.DeviceDao;
import com.deviceyun.yunos.domain.Device;

@Component
public class DeviceServiceImpl implements DeviceService {

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

}
