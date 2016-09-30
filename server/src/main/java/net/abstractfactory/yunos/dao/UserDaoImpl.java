package net.abstractfactory.yunos.dao;

import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.domain.User;

@Component
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User getUser(String id) {

		return (User) getCurrentSession().load(User.class, id);
	}
}
