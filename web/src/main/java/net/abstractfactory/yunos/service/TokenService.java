package net.abstractfactory.yunos.service;

import net.abstractfactory.yunos.domain.Device;
import net.abstractfactory.yunos.domain.User;
import net.abstractfactory.yunos.domain.auth.Token;

public interface TokenService {
	Token createUserToken(User user);

	Token createDeviceToken(Device device);
	
	 
	void deleteToken(Token token);
	
}
