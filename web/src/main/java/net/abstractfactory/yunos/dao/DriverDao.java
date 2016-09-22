package net.abstractfactory.yunos.dao;

import java.util.List;

import net.abstractfactory.yunos.domain.Driver;
import net.abstractfactory.yunos.driver.device.Model;

public interface DriverDao {
	Driver get(String id);
	/**
	 * find all available driver for a model
	 * 
	 * @param model
	 * @return
	 */
	List<Driver> findDriver(net.abstractfactory.yunos.domain.Model model);
}
