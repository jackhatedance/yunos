package com.driverstack.yunos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.Brand;

@Component
public class BrandServiceImpl implements BrandService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Brand> getAllBrands(String locale) {
		Query q = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select b from Brand b "
								+ "left join b.locales l with l.locale=:locale where b.primary is null");
		q.setString("locale", locale);

		List<Brand> dbResult = (List<Brand>) q.list();
		List<Brand> localeBrands = new ArrayList<Brand>();
		for (Brand b : dbResult)
			localeBrands.add(b.get(locale));

		return localeBrands;
	}
}
