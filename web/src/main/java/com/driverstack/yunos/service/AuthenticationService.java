package com.driverstack.yunos.service;

import com.driverstack.yunos.domain.Token;

public interface AuthenticationService {

	Token login(String userId, String password);
}
