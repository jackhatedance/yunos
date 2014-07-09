package com.deviceyun.smarthome.dao.mybatisMapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.deviceyun.smarthome.dao.entity.DeviceEntity;

public interface DeviceMapper {
	@Select("SELECT * FROM devices WHERE id = #{id}")
	DeviceEntity getDevice(String id);

	@Select("SELECT * FROM devices")
	List<DeviceEntity> list();
}
