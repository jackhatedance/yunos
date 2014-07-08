package com.deviceyun.smarthome.service;

import java.util.List;

import com.deviceyun.smarthome.domain.User;

public interface UserService {
	String getUser(String id);

	List<User> list();
}
