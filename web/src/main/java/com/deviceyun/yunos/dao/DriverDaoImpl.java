package com.deviceyun.yunos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.domain.Driver;
import com.deviceyun.yunos.domain.Model;

@Component
public class DriverDaoImpl implements DriverDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public Driver get(String id) {
		Session session = sessionFactory.getCurrentSession();
		return (Driver) session.load(Driver.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Driver> findDriver(com.deviceyun.yunos.device.Model model) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select d from Driver as d inner join d.supportedModels as m where m.brand=:brand and m.product=:product and m.model=:model");

		query.setString("brand", model.getBrand());
		query.setString("product", model.getProduct());
		query.setString("model", model.getModel());

		return query.list();

	}
}
