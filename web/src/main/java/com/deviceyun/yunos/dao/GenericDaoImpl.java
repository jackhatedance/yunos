package com.deviceyun.yunos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.device.Model;
import com.deviceyun.yunos.domain.Driver;

@Component
public class GenericDaoImpl extends AbstractDao implements GenericDao {

	@Override
	public Object load(Class clazz, Serializable id) {
		return getCurrentSession().load(clazz, id);

	}

	@Override
	public Object get(Class clazz, Serializable id) {
		return getCurrentSession().get(clazz, id);
	}

}
