package com.driverstack.yunos.service;

import java.util.List;
import java.util.Locale;

import com.driverstack.yunos.domain.Brand;

public interface BrandService {

	List<Brand> getAllBrands(String locale);
	
	 
}
