package com.deviceyun.yunos.service;

import java.util.List;

import com.deviceyun.yunos.domain.Model;
import com.deviceyun.yunos.domain.Product;

public interface ModelService {

	/**
	 * list models of given product
	 * @param brand
	 * @param locale
	 * @return
	 */
	List<Model> getModels(Product product, String locale);
}
