package com.deviceyun.smarthome.dao.mybatisMapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.deviceyun.smarthome.domain.Driver;

public interface DriverMapper {
	@Select("SELECT * FROM drivers WHERE id = #{id}")
	Driver get(String id);

	@Select("SELECT * FROM drivers")
	List<Driver> list();
}
