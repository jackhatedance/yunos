package net.abstractfactory.yunos.dao;

import net.abstractfactory.yunos.domain.auth.Token;

public interface TokenDao {

	Token get(String token);
	void save(Token token);
	void delete(Token token);
}
