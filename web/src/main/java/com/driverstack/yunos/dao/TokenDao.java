package com.driverstack.yunos.dao;

import com.driverstack.yunos.domain.auth.Token;

public interface TokenDao {

	Token get(String token);
	void save(Token token);
	void delete(Token token);
}
