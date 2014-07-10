package com.deviceyun.smarthome.dao.mybatisMapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.deviceyun.smarthome.domain.Device;

public interface DeviceMapper {
	@Select("SELECT * FROM devices WHERE id = #{id}")
	Device getDevice(String id);

	@Select("SELECT * FROM devices")
	List<Device> list();
}
