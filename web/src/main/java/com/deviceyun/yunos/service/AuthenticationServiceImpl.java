package com.deviceyun.yunos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.deviceyun.yunos.dao.TokenDao;
import com.deviceyun.yunos.dao.UserDao;
import com.deviceyun.yunos.domain.Token;
import com.deviceyun.yunos.domain.User;

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
