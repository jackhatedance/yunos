package com.driverstack.yunos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.UserDao;
import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.domain.Model;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.domain.Vendor;

@Component
public class ModelServiceImpl extends AbstractService implements ModelService {

	@Override
	public List<Model> getModels(Vendor vendor, DeviceClass deviceClass,
			String locale) {
		Session s = getCurrentSession();
		Criteria c = s.createCriteria(Model.class);
		c.add(Restrictions.eq("vendor", vendor));

		if (deviceClass != null)
			c.add(Restrictions.eq("deviceClass", deviceClass));

		List<Model> models = c.list();

		return models;
	}

}
