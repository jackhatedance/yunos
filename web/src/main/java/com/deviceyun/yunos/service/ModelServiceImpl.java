package com.deviceyun.yunos.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.domain.Model;
import com.deviceyun.yunos.domain.Product;

@Component
public class ModelServiceImpl implements ModelService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Model> getModels(Product product, String locale) {

		Query q = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select m from Model m "
								+ "left join m.locales l with l.locale=:locale "
								+ "join m.product p "
								+ "where m.primary is null " + "and p=:product ");

		q.setString("locale", locale);
		q.setEntity("product", product);

		List<Model> dbResult = (List<Model>) q.list();
		List<Model> locales = new ArrayList<Model>();
		for (Model m : dbResult)
			locales.add(m.get(locale));

		return locales;
	}

}
