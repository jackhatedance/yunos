package net.abstractfactory.yunos.dao;

import java.io.Serializable;
import java.util.List;

import net.abstractfactory.yunos.domain.Device;

public interface GenericDao {

	Object load(Class clazz, Serializable id);

	Object get(Class clazz, Serializable id);

	List getAll(Class entityClass);
	
	void saveOrUpdate(Object entity);

}
