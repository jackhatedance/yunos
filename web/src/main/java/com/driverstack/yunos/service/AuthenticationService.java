package com.driverstack.yunos.service;

import com.driverstack.yunos.domain.auth.Token;

public interface AuthenticationService {

	Token login(String userId, String password);

	void revoke(Token token);
}
