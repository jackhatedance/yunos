package com.driverstack.yunos.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.domain.Device;
import com.driverstack.yunos.domain.DeviceClass;

@Component
public class DeviceClassServiceImpl extends AbstractService implements DeviceClassService {
	@Override
	public List<DeviceClass> getAll(String locale) {
	
		Session s = getCurrentSession();
		Criteria c = s.createCriteria(DeviceClass.class);
		c.add(Restrictions.isNull("primary"));

		List<DeviceClass> primarys = c.list();

		List<DeviceClass> localeItems = new ArrayList<DeviceClass>();
		for (DeviceClass obj : primarys)
			localeItems.add(obj.get(locale));

		return localeItems;
	}

}
