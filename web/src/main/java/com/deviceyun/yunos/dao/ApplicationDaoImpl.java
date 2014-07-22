package com.deviceyun.yunos.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.domain.Application;

@Component
public class ApplicationDaoImpl extends AbstractDao implements ApplicationDao {

	@Override
	public Application get(String id) {
		Session session = sessionFactory.getCurrentSession();
		return (Application) session.get(Application.class, id);
	}
}
