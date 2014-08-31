package com.driverstack.yunos.dao;

import java.util.List;

import com.driverstack.yunos.device.Model;
import com.driverstack.yunos.domain.Driver;

public interface DriverDao {
	Driver get(String id);
	/**
	 * find all available driver for a model
	 * 
	 * @param model
	 * @return
	 */
	List<Driver> findDriver(Model model);
}
