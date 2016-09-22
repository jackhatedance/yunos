package net.abstractfactory.yunos.service;

import java.util.List;

import net.abstractfactory.yunos.domain.User;

public interface UserService {
	User getUser(String id);

	User getUserByEmail(String email);

	List<User> list();

	
	
	void createUser(User user);

	void changePassword(User user, String newPassword);

}
