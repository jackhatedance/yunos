package com.deviceyun.yunos.dao;

import org.springframework.stereotype.Component;

import com.deviceyun.yunos.domain.Token;

@Component
public class TokenDaoImpl extends AbstractDao implements TokenDao {

	@Override
	public void save(Token token) {
		getCurrentSession().save(token);
	}
}
