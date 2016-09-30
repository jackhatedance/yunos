package net.abstractfactory.yunos.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.core.DriverManager;
import net.abstractfactory.yunos.core.DriverObjectFactory;
import net.abstractfactory.yunos.core.ResoucePath;
import net.abstractfactory.yunos.dao.DriverDao;
import net.abstractfactory.yunos.domain.Driver;
import net.abstractfactory.yunos.domain.DriverConfigurationDefinition;
import net.abstractfactory.yunos.domain.DriverConfigurationDefinitionItem;
import net.abstractfactory.yunos.domain.LocalDriverConfigurationDefinitionItem;
import net.abstractfactory.yunos.domain.Model;
import net.abstractfactory.yunos.driver.DriverProperties;
import net.abstractfactory.yunos.driver.config.ConfigurationDefinition;
import net.abstractfactory.yunos.driver.config.ConfigurationItem;
import net.abstractfactory.yunos.driver.config.ConfigureAnnotationParser;

@Component
public class DriverServiceImpl extends AbstractService implements DriverService {

	@Autowired
	private ResoucePath resourcePath;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private DriverObjectFactory driverObjectFactory;

	@Autowired
	private DriverManager driverManager;

	@Override
	public Serializable upload(InputStream in) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			org.apache.commons.io.IOUtils.copy(in, baos);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		byte[] bytes = baos.toByteArray();

		// 1 read the driver.properties file.
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		Properties props = driverManager.readDriverInfoFromJarFile(bais);
		DriverProperties driverProps = new DriverProperties(props);

		// 2 get the configure info
		bais.reset();
		net.abstractfactory.yunos.driver.Driver driver = driverObjectFactory
				.createDriverObject(bais, driverProps.getClassName());

		ConfigureAnnotationParser parser = new ConfigureAnnotationParser();
		ConfigurationDefinition def = parser.parse(driver.getConfigureClass());

		// 3 save to file system
		try {
			String shortFileName = getShortFileName(driverProps.getName(),
					driverProps.getVersion());

			String path = getFilePath(driverProps.getDeveloperName());

			File dir = new File(path);
			dir.mkdir();

			FileOutputStream out = new FileOutputStream(dir + "/"
					+ shortFileName);

			bais.reset();
			IOUtils.copy(bais, out);
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// save to DB.
		Driver driverDomain = new Driver(driverProps.getName(),
				driverProps.getVersion(), driverProps.getSdkVersion(),
				driverProps.getClassName(), driverProps.getDeveloperName(),
				driverProps.getDeveloperEmail(), driverProps.getDeveloperWeb());

		DriverConfigurationDefinition configDefDomain = convertToDomainObject(def);
		driverDomain.setConfigurationDefinition(configDefDomain);

		// check version duplicate
		List<Driver> drivers = find(driverDomain.getDeveloperName(),
				driverDomain.getName(), driverDomain.getVersion());
		if (driver != null && !drivers.isEmpty()) {
			throw new RuntimeException("Driver already exists.");
		}

		return getCurrentSession().save(driverDomain);

	}

	private String getFilePath(String developerName) {
		String path = String.format("%s%s", resourcePath.getDriverPath(),
				developerName);
		return path;
	}

	private String getShortFileName(String driverName, String version) {
		String shortFileName = String.format("%s-%s.jar", driverName, version);
		return shortFileName;
	}

	/**
	 * convert
	 * 
	 * @param configItem
	 * @return
	 */
	private DriverConfigurationDefinition convertToDomainObject(
			ConfigurationDefinition def) {

		DriverConfigurationDefinition domainConfigureDefinition = new DriverConfigurationDefinition(
				def.getDefaultLocaleTag(), def.getSupportedLocaleTags());

		DriverConfigurationDefinitionItem domainItem = null;
		for (ConfigurationItem dci : def.getItems()) {

			domainItem = new DriverConfigurationDefinitionItem(dci.getOrder(),
					dci.getFieldName(), dci.getType(), dci.getDefaultValue(),
					null, def.getDefaultLocale().toString());

			for (Locale locale : def.getSupportedLocales()) {

				String localeTag = locale.toString();
				LocalDriverConfigurationDefinitionItem domainLocalItem = new LocalDriverConfigurationDefinitionItem(
						dci.getName().get(localeTag), dci.getDescription().get(
								localeTag), locale.toString());

				domainItem.addLocalItem(localeTag, domainLocalItem);
			}

			domainConfigureDefinition.addItem(domainItem);
		}
		return domainConfigureDefinition;
	}

	@Override
	public Driver get(Serializable id) {
		Session s = getCurrentSession();
		Driver d = (Driver) s.get(Driver.class, id);

		return d;
	}

	@Override
	public List<Driver> find(String developerName, String driverName,
			String version) {
		Session s = getCurrentSession();
		// Query q = s
		// .createQuery("select d from Driver d join d.supportedModels sm left join sm.compatibleModels cm where sm=:m or cm=:m");
		Criteria c = s.createCriteria(Driver.class);

		if (developerName != null)
			c.add(Restrictions.eq("developerName", developerName));

		if (driverName != null)
			c.add(Restrictions.eq("name", driverName));

		if (version != null)
			c.add(Restrictions.eq("version", version));

		return c.list();
	}

	@Override
	public void delete(Serializable id) {
		Session s = getCurrentSession();
		Driver obj = (Driver) s.load(Driver.class, id);

		// remove file
		String shortFileName = getShortFileName(obj.getName(), obj.getVersion());
		String path = getFilePath(obj.getDeveloperName());

		String fullFileName = path + "/" + shortFileName;
		File file = new File(fullFileName);
		file.delete();

		// remove from DB
		s.delete(obj);

	}

	@Override
	public List<Driver> findAvailableDrivers(Model model) {
		return driverDao.findDriver(model);
	}
}
