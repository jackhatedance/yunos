package com.deviceyun.smarthome.dao.mybatisMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.deviceyun.smarthome.domain.User;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	User getUser(@Param("id") String id);

	User findUserByFirstName(String firstName);

	User findUserByEmail(String email);
}
