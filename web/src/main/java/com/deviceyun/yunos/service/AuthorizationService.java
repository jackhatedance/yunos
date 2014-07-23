package com.deviceyun.yunos.service;

import com.deviceyun.yunos.domain.Token;

public interface AuthorizationService {

	Token login(String userId, String password);
}
