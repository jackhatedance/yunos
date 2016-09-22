package net.abstractfactory.yunos.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.domain.Driver;
import net.abstractfactory.yunos.domain.Model;

@Component
public class DriverDaoImpl extends AbstractDao implements DriverDao {

	@Override
	public Driver get(String id) {
		Session session = getCurrentSession();
		return (Driver) session.get(Driver.class, id);
	}

	@Override
	public List<Driver> findDriver(Model model) {
		// 1 find driver that support the model directly.
		// 2 find those support indirectly(compatible)
		Session s = getCurrentSession();
		// Query q = s
		// .createQuery("select d from Driver d join d.supportedModels sm left join sm.compatibleModels cm where sm=:m or cm=:m");

		Query q = s
				.createQuery("select d from Driver d join d.supportedModels m "
						+" left join m.compliedBy cm where m=:m or cm=:m");

		q.setEntity("m", model);

		return q.list();
	}

}
