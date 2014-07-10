package com.deviceyun.smarthome.service;

import java.util.List;

import com.deviceyun.smarthome.domain.User;

public interface UserService {
	User getUser(String id);

	List<User> list();
}
