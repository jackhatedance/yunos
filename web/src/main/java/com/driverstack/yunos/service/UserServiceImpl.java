package com.driverstack.yunos.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.UserDao;
import com.driverstack.yunos.domain.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User getUser(String id) {
		return (User) getCurrentSession().load(User.class, id);
	}

	@Override
	public User getUserByEmail(String email) {
		Criteria c = getCurrentSession().createCriteria(User.class).add(
				Restrictions.eq("email", email));
		return (User) c.uniqueResult();
	}

	@Override
	public List<User> list() {
		return getCurrentSession().createCriteria(User.class).list();

	}

	@Override
	public void save(User user) {
		String hash = passwordEncoder.encode(user.getPassword());
		user.setPasswordHash(hash);

		getCurrentSession().save(user);
	}
}
