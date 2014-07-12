package com.deviceyun.yunos.service;

import java.util.List;

import com.deviceyun.yunos.domain.User;

public interface UserService {
	User getUser(String id);

	List<User> list();
}
