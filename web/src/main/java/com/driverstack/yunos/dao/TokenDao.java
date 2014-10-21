package com.driverstack.yunos.dao;

import com.driverstack.yunos.domain.auth.Token;

public interface TokenDao {

	void save(Token token);
	void delete(Token token);
}
