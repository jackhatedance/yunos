package com.driverstack.yunos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.TokenDao;
import com.driverstack.yunos.dao.UserDao;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.domain.auth.Token;
import com.driverstack.yunos.domain.auth.TokenDeviceAuthorization;
import com.driverstack.yunos.domain.auth.TokenUserAuthorization;
import com.driverstack.yunos.web.security.TokenUserDetail;

@Component("tokenService")
public class TokenServiceImpl extends AbstractService implements TokenService,
		UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenDao tokenDao;

	@Override
	public Token createUserToken(User user) {

		Token token = new Token();

		TokenUserAuthorization tua = new TokenUserAuthorization(user, true,
				true, true, true);

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
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		String tokenId = username;

		Token token = tokenDao.get(tokenId);

		return new TokenUserDetail(token);
	}

}
