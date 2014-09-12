package com.driverstack.yunos.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.domain.Vendor;

@Component
public class DeviceClassServiceImpl extends AbstractService implements
		DeviceClassService {

	@Override
	public List<DeviceClass> find(Vendor vendor, String locale) {
		Session s = getCurrentSession();

		Query q = s
				.createQuery("select distinct dc from Model m join m.deviceClass as dc join m.vendor as v where v=:vendor ");
		q.setEntity("vendor", vendor);

		List<DeviceClass> primarys = q.list();

		List<DeviceClass> localeItems = new ArrayList<DeviceClass>();
		for (DeviceClass obj : primarys)
			localeItems.add(obj.get(locale));

		return localeItems;

	}

}
