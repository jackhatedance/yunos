package com.driverstack.yunos.dao;

import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.Token;

@Component
public class TokenDaoImpl extends AbstractDao implements TokenDao {

	@Override
	public void save(Token token) {
		getCurrentSession().save(token);
	}
}
