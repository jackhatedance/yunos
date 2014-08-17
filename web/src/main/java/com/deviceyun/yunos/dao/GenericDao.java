package com.deviceyun.yunos.dao;

import java.io.Serializable;
import java.util.List;

import com.deviceyun.yunos.domain.Device;

public interface GenericDao {

	Object load(Class clazz, Serializable id);

	Object get(Class clazz, Serializable id);

}
