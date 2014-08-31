package com.driverstack.yunos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.Device;

@Component
public class DeviceDaoImpl extends AbstractDao implements DeviceDao {

	@Override
	public Device get(String id) {
		Session session = getCurrentSession();
		return (Device) session.get(Device.class, id);
	}

	@Override
	public List<Device> listByUser(String userId) {

		Query q = getCurrentSession().createQuery(
				"select d from Device as d where d.user.id=?");
		q.setString(0, userId);
		return q.list();

	}
}
