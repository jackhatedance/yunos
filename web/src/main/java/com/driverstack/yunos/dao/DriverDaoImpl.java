package com.driverstack.yunos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.Driver;

@Component
public class DriverDaoImpl extends AbstractDao implements DriverDao {

	@Override
	public Driver get(String id) {
		Session session = getCurrentSession();
		return (Driver) session.get(Driver.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Driver> findDriver(com.driverstack.yunos.device.Model model) {
		Session session = getCurrentSession();
		Query query = session
				.createQuery("select d from Driver as d inner join d.supportedModels as m where m.vendor.name=:vendor and m.name=:model");

		query.setString("vendor", model.getVendor());
		query.setString("model", model.getModel());

		return query.list();

	}
}
