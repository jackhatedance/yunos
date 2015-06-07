package com.driverstack.yunos.dao;

import java.util.List;

import com.driverstack.yunos.domain.Driver;
import com.driverstack.yunos.driver.device.Model;

public interface DriverDao {
	Driver get(String id);
	/**
	 * find all available driver for a model
	 * 
	 * @param model
	 * @return
	 */
	List<Driver> findDriver(com.driverstack.yunos.domain.Model model);
}
