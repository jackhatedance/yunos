package com.dingjianghao.yunos.server.security.service;

import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import net.abstractfactory.plum.repository.biz.interafce.Repository;
import net.abstractfactory.plum.security.SecurityService;
import net.abstractfactory.yunos.server.domain.ManagerUser;

/**
 * a simple implementation for demo
 * 
 * @author jack
 *
 */
@Service
public class MySecurityCheckService implements SecurityService {

	private static final String DEFAULT_USER_ID = "admin";
	private static final String DEFAULT_PASSWORD = "pass";

	@Autowired
	private Repository repository;

	public MySecurityCheckService() {

	}

	@Override
	public boolean checkPassword(String userId, String password) {
		ManagerUser user = (ManagerUser) repository.getCollection(ManagerUser.class).get(userId);

		String storedPasswordHash;
		if (user == null) {
			// default account: admin/pass
			if (DEFAULT_USER_ID.equals(userId))
				storedPasswordHash = DigestUtils.md5Hex(DEFAULT_PASSWORD);
			else
				return false;
		} else {
			storedPasswordHash = user.getPasswordHash();
		}

		String passHash = DigestUtils.md5Hex(password);

		if (storedPasswordHash != null && storedPasswordHash.equals(passHash))
			return true;
		else
			return false;
	}

	public boolean checkRole(String userId, String roleName) {

		ManagerUser user = (ManagerUser) repository.getCollection(ManagerUser.class).get(userId);

		// mandated
		if (user == null) {
			if ("admin".equals(userId) && "ADMIN".equalsIgnoreCase(roleName))
				return true;
			else
				return false;
		}

		Set<String> roles = user.getRoleAsNames();
		if (roles != null) {
			return roles.contains(roleName);
		}

		return false;
	}

	@Override
	public boolean isAllowed(String userId, String[] roleNames) {
		if (userId == null)
			return false;

		for (String roleName : roleNames) {
			if (checkRole(userId, roleName))
				return true;
		}
		return false;
	}

}
