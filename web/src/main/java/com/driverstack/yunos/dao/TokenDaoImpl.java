package com.driverstack.yunos.dao;

import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.auth.Token;

@Component
public class TokenDaoImpl extends AbstractDao implements TokenDao {

	@Override
	public Token get(String token) {
		// assume token is id.
		Token t = (Token) getCurrentSession().get(Token.class, token);
		return t;
	}

	@Override
	public void save(Token token) {
		getCurrentSession().save(token);
		
	}

	@Override
	public void delete(Token token) {
		getCurrentSession().delete(token);

	}
}
