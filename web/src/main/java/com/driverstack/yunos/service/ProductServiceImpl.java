package com.driverstack.yunos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.Brand;
import com.driverstack.yunos.domain.Product;

@Component
public class ProductServiceImpl implements ProductService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getProducts(Brand brand, String locale) {

		Query q = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select p from Product p "
								+ "left join p.locales l with l.locale=:locale "
								+ "join p.brand b "
								+ "where p.primary is null " + "and b=:brand ");

		q.setString("locale", locale);
		q.setEntity("brand", brand);

		List<Product> dbResult = (List<Product>) q.list();
		List<Product> localeProducts = new ArrayList<Product>();
		for (Product b : dbResult)
			localeProducts.add(b.get(locale));

		return localeProducts;
	}

}
