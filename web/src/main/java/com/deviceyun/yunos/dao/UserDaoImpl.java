package com.deviceyun.yunos.dao;

import org.springframework.stereotype.Component;

import com.deviceyun.yunos.domain.User;

@Component
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User getUser(String id) {

		return (User) getCurrentSession().load(User.class, id);
	}
}
