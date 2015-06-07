package com.driverstack.yunos.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.domain.User;

@Component
public class DeviceDaoImpl extends AbstractDao implements DeviceDao {

	@Override
	public Device get(String id) {
		Session session = getCurrentSession();
		return (Device) session.get(Device.class, id);
	}

	@Override
	public List<Device> listByUser(String userId, DeviceClass deviceClass) {
		Session session = getCurrentSession();
		User user = (User) session.load(User.class, userId);

		Criteria c = getCurrentSession().createCriteria(Device.class);
		c.add(Restrictions.eq("user", user));
		if (deviceClass != null) {
			c.add(Restrictions.eq("deviceClass", deviceClass));
		}

		return c.list();

	}
}
