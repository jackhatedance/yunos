package com.deviceyun.yunos;

import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deviceyun.yunos.device.Model;
import com.deviceyun.yunos.dao.DriverDao;
import com.deviceyun.yunos.dao.mybatisMapper.DeviceMapper;
import com.deviceyun.yunos.dao.mybatisMapper.UserMapper;
import com.deviceyun.yunos.domain.Device;
import com.deviceyun.yunos.domain.Driver;
import com.deviceyun.yunos.domain.User;

@ContextConfiguration(locations = "classpath:/com/deviceyun/yunos/DaoTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DriverDao driverDao;

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

		org.hibernate.Query query = sessionFactory.getCurrentSession()
				.createQuery("from User u where u.firstName = :firstName");
		query.setString("firstName", "jack");
		User user = (User) query.uniqueResult();
		Assert.assertNotNull(user);

		// test device api
		query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DeviceApi a where a.brand = :brand and category=:category and product=:product");
		query.setString("brand", "Generic");
		query.setString("category", "Light");
		query.setString("product", "Light");
		com.deviceyun.yunos.domain.DeviceApi da = (com.deviceyun.yunos.domain.DeviceApi) query
				.uniqueResult();
		Assert.assertNotNull(da);
		Assert.assertNotNull(da.getVersions().get("1.0"));

	}

	@Test
	// @Ignore
	public void testDriverDao() throws Exception {

		assertNotNull(driverDao);
		Model model = new Model("7eggs", "Multifunction Transmitter",
				"IR-RF433");
		List<Driver> drivers = driverDao.findDriver(model);
		Assert.assertNotNull(drivers);
		Assert.assertFalse(drivers.isEmpty());
		Assert.assertEquals("jack ding", drivers.get(0).getAuthor());

	}

}
