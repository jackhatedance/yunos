package net.abstractfactory.yunos.dao.mybatisMapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import net.abstractfactory.yunos.domain.Device;

public interface DeviceMapper {
	@Select("SELECT * FROM Device WHERE id = #{id}")
	Device getDevice(String id);

	@Select("SELECT * FROM Device")
	List<Device> list();
}
