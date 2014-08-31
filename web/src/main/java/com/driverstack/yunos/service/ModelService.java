package com.driverstack.yunos.service;

import java.util.List;

import com.driverstack.yunos.domain.Model;
import com.driverstack.yunos.domain.Product;

public interface ModelService {

	/**
	 * list models of given product
	 * @param brand
	 * @param locale
	 * @return
	 */
	List<Model> getModels(Product product, String locale);
}
