package com.deviceyun.yunos.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.deviceyun.yunos.dao.UserDao;
import com.deviceyun.yunos.domain.User;

public class UserServiceImpl implements UserService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User getUser(String id) {
		return (User) getCurrentSession().load(User.class, id);

	}

	@Override
	public List<User> list() {
		return getCurrentSession().createCriteria(User.class).list();

	}
}
