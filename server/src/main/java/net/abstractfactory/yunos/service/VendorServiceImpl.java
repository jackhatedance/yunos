package net.abstractfactory.yunos.service;

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

import net.abstractfactory.yunos.dao.UserDao;
import net.abstractfactory.yunos.domain.User;
import net.abstractfactory.yunos.domain.Vendor;

@Component
public class VendorServiceImpl extends AbstractService implements VendorService {

	@Override
	public Vendor loadByCodeName(String codeName) {
		Session s = getCurrentSession();

		Criteria c = s.createCriteria(Vendor.class).add(
				Restrictions.eq("codeName", codeName));

		return (Vendor) c.uniqueResult();
	}

}
