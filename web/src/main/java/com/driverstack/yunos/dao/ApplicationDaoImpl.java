package com.driverstack.yunos.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.Application;

@Component
public class ApplicationDaoImpl extends AbstractDao implements ApplicationDao {

	@Override
	public Application get(String id) {
		Session session = getCurrentSession();
		return (Application) session.get(Application.class, id);
	}
}
