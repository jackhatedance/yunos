package com.driverstack.yunos.service;

import java.util.List;
import java.util.Locale;

import com.driverstack.yunos.domain.Brand;
import com.driverstack.yunos.domain.Product;

public interface ProductService {

	/**
	 * list products of given brand
	 * @param brand
	 * @param locale
	 * @return
	 */
	List<Product> getProducts(Brand brand, String locale);
}
