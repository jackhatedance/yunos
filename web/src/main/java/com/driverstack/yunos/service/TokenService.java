package com.driverstack.yunos.service;

import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.domain.auth.Token;

public interface TokenService {
	Token createUserToken(User user);

	Token createDeviceToken(Device device);
	
	 
	void deleteToken(Token token);
	
}
