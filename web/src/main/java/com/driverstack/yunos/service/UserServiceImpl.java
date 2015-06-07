package com.driverstack.yunos.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.UserDao;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.web.security.MyUserDetail;

@Component("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

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
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = getUser(username);

		if (user == null)
			throw new UsernameNotFoundException("username:" + username);

		MyUserDetail detail = new MyUserDetail(user);

		detail.getUsername();

		return detail;
	}

	
	public User loadUser(String id) {
		return (User) getCurrentSession().load(User.class, id);
	}
	
	@Override
	public User getUser(String id) {
		return (User) getCurrentSession().get(User.class, id);
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

	private void save(User user) {
		String hash = passwordEncoder.encode(user.getPassword());
		user.setPassword(hash);

		getCurrentSession().save(user);
	}

	@Override
	public void createUser(User user) {
		// check user ID
		User duplicateUser = getUser(user.getId());
		if (duplicateUser != null)
			throw new RuntimeException("cannot create user, ID already exists.");

		save(user);
	}

	@Override
	public void changePassword(User user, String newPassword) {
		String hash = passwordEncoder.encode(newPassword);
		user.setPassword(hash);

		getCurrentSession().update(user);

	}
}
