package com.driverstack.yunos.service;

import java.util.List;

import com.driverstack.yunos.domain.User;

public interface UserService {
	User getUser(String id);

	User getUserByEmail(String email);

	List<User> list();
	
	void save(User user);

}
