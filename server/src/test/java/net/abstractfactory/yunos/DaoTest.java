package net.abstractfactory.yunos;

import static junit.framework.Assert.assertNotNull;

import javax.transaction.Transactional;

import junit.framework.Assert;
import net.abstractfactory.yunos.dao.DeviceDao;
import net.abstractfactory.yunos.dao.DriverDao;
import net.abstractfactory.yunos.dao.mybatisMapper.DeviceMapper;
import net.abstractfactory.yunos.dao.mybatisMapper.UserMapper;
import net.abstractfactory.yunos.domain.Device;
import net.abstractfactory.yunos.domain.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:/net/abstractfactory/yunos/DaoTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private DeviceDao deviceDao;

	@Test
	public void testMybatisMappers() throws Exception {
		assertNotNull(userMapper);
		User user = userMapper.findUserByFirstName("jack");
		Assert.assertNotNull(user);

		assertNotNull(deviceMapper);
		Device device = deviceMapper
				.getDevice("cb170afb-087f-11e4-b721-08002785c3ec");
		Assert.assertNotNull(device);

	}

	@Test
	public void testHibernate() throws Exception {
		assertNotNull(sessionFactory);

		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Query query = sessionFactory.getCurrentSession()
				.createQuery("from User u where u.firstName = :firstName");
		query.setString("firstName", "jack");
		User user = (User) query.uniqueResult();
		Assert.assertNotNull(user);

		// test device api
		/*
		query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from FunctionalDevice a where a.brand = :brand and category=:category and product=:product");
		query.setString("brand", "Generic");
		query.setString("category", "Light");
		query.setString("product", "Light");
		net.abstractfactory.yunos.domain.FunctionalDevice da = (net.abstractfactory.yunos.domain.FunctionalDevice) query
				.uniqueResult();
		Assert.assertNotNull(da);
		
		Vendor baihuon = (Vendor) session.get(Vendor.class,
				"f525b8e7-2485-11e4-9fa1-08002785c3ec");

		Assert.assertNotNull(baihuon);

		Vendor zhCNBaihoun = baihuon.get("zh_CN");

		Assert.assertNotNull(zhCNBaihoun);
*/
	}

}
