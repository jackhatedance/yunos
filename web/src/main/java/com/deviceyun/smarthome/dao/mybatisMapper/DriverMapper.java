package com.deviceyun.smarthome.dao.mybatisMapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.deviceyun.smarthome.dao.entity.DriverEntity;

public interface DriverMapper {
	@Select("SELECT * FROM drivers WHERE id = #{id}")
	DriverEntity get(String id);

	@Select("SELECT * FROM drivers")
	List<DriverEntity> list();
}
