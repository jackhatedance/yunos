package com.deviceyun.yunos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deviceyun.yunos.dao.ApplicationDao;
import com.deviceyun.yunos.domain.Application;

@Component
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	ApplicationDao dao;

	@Override
	public boolean isValid(String id) {
		Application app = dao.get(id);
		return app != null;
	}
}
