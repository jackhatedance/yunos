package net.abstractfactory.yunos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.dao.ApplicationDao;
import net.abstractfactory.yunos.domain.Application;

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
