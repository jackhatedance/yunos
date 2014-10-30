package com.driverstack.yunos.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.TokenDao;
import com.driverstack.yunos.dao.UserDao;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.domain.auth.Token;
import com.driverstack.yunos.domain.auth.TokenDeviceAuthorization;
import com.driverstack.yunos.domain.auth.TokenUserAuthorization;
import com.driverstack.yunos.web.security.TokenUserDetails;

@Component("tokenService")
public class TokenServiceImpl extends AbstractService implements TokenService,
		UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenDao tokenDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private SecureRandom random = new SecureRandom();

	@Override
	public Token createUserToken(User user) {

		Token token = new Token();
		token.setCreateTime(new Date());
		token.setExpireTime(new Date(2050, 1, 1));

		TokenUserAuthorization tua = new TokenUserAuthorization(user, true,
				true, true, true);

		String secret = new BigInteger(128, random).toString(32);
		token.setPassword(secret);
		
		String secretHash = passwordEncoder.encode(secret);
		token.setPasswordHash(secretHash);
		
		token.setOwner(user);

		token.addAuthorization(tua);

		tokenDao.save(token);

		return token;

	}

	@Override
	public Token createDeviceToken(Device device) {
		Token token = new Token();

		TokenDeviceAuthorization tda = new TokenDeviceAuthorization(device,
				true, true, true, true);

		token.addAuthorization(tda);

		tokenDao.save(token);

		return token;

	}

	@Override
	public void deleteToken(Token token) {
		tokenDao.delete(token);

	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		String tokenId = username;

		Token token = tokenDao.get(tokenId);
		if (token == null)
			throw new UsernameNotFoundException("token ID:" + username);

		TokenUserDetails tokenUserDetails = new TokenUserDetails(token);
		return tokenUserDetails;
	}

}
