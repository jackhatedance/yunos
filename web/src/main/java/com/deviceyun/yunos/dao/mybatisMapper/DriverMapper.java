package com.deviceyun.yunos.dao.mybatisMapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.deviceyun.yunos.domain.Driver;

public interface DriverMapper {
	@Select("SELECT * FROM Driver WHERE id = #{id}")
	Driver get(String id);

	@Select("SELECT * FROM Driver")
	List<Driver> list();
}
