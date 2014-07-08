package com.deviceyun.smarthome.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.deviceyun.smarthome.domain.User;

public interface UserDao {
	@Select("SELECT * FROM users WHERE id = #{id}")
	User getUser(@Param("userId") String id);

}
