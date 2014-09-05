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
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.domain.Vendor;

@Component
public class VendorServiceImpl extends AbstractService implements VendorService {

	@Override
	public List<Vendor> getAll(String locale) {
		Session s = getCurrentSession();
		Criteria c = s.createCriteria(Vendor.class);
		c.add(Restrictions.isNull("primary"));

		List<Vendor> primarys = c.list();

		List<Vendor> localeVendors = new ArrayList<Vendor>();
		for (Vendor v : primarys)
			localeVendors.add(v.get(locale));

		return localeVendors;
	}
}
