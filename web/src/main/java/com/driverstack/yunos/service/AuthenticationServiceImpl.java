package com.driverstack.yunos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.driverstack.yunos.dao.TokenDao;
import com.driverstack.yunos.dao.UserDao;
import com.driverstack.yunos.domain.Token;
import com.driverstack.yunos.domain.User;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenDao tokenDao;

	@Override
	public Token login(String userId, String password) {
		User user = userDao.getUser(userId);

		String hash = DigestUtils.md5DigestAsHex(password.getBytes());

		if (user.getPasswordHash().equalsIgnoreCase(hash)) {
			Token token = new Token();

			token.setUser(user);

			tokenDao.save(token);

			return token;
		}

		return null;
	}
}
