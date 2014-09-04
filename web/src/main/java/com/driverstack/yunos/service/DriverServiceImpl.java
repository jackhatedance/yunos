package com.driverstack.yunos.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.core.DriverClassLoader;
import com.driverstack.yunos.core.DriverManager;
import com.driverstack.yunos.core.ResoucePath;
import com.driverstack.yunos.dao.DriverDao;
import com.driverstack.yunos.domain.Driver;
import com.driverstack.yunos.domain.DriverConfigurationDefinition;
import com.driverstack.yunos.domain.DriverConfigurationDefinitionItem;
import com.driverstack.yunos.domain.Model;
import com.driverstack.yunos.domain.DeviceClass;
import com.driverstack.yunos.driver.DriverProperties;
import com.driverstack.yunos.driver.config.ConfigurationDefinition;
import com.driverstack.yunos.driver.config.ConfigurationItem;
import com.driverstack.yunos.driver.config.ConfigureAnnotationParser;

@Component
public class DriverServiceImpl extends AbstractService implements DriverService {

	@Autowired
	private ResoucePath resourcePath;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private DriverClassLoader driverClassLoader;

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
		com.driverstack.yunos.driver.Driver driver = driverClassLoader
				.loadDriver(bais, driverProps.getClassName());

		ConfigureAnnotationParser parser = new ConfigureAnnotationParser();
		ConfigurationDefinition def = parser.parse(driver.getConfigureClass());

		// 3 save to file system
		try {
			String shortFileName = String.format("%s-%s.jar",
					driverProps.getName(), driverProps.getVersion());

			String path = String.format("%s%s", resourcePath.getDriverPath(),
					driverProps.getAuthorName());

			File dir = new File(path);
			dir.mkdir();

			FileOutputStream out = new FileOutputStream(dir + "/"
					+ shortFileName);
			IOUtils.copy(in, out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// save to DB.
		Driver driverDomain = new Driver(driverProps.getName(),
				driverProps.getVersion(), driverProps.getSdkVersion(),
				driverProps.getClassName(), driverProps.getAuthorName(),
				driverProps.getAuthorEmail());

		DriverConfigurationDefinition configDefDomain = convertToDomainObject(def);
		driverDomain.setConfigurationDefinition(configDefDomain);

		return getCurrentSession().save(driverDomain);

	}

	/**
	 * convert
	 * 
	 * @param configItem
	 * @return
	 */
	private DriverConfigurationDefinition convertToDomainObject(
			ConfigurationDefinition def) {

		DriverConfigurationDefinition configureDefinitionDomain = new DriverConfigurationDefinition(
				def.getDefaultLocaleTag(), def.getSupportedLocaleTags());

		DriverConfigurationDefinitionItem primaryDomainItem = null;
		for (ConfigurationItem dci : def.getItems()) {
			Locale defaultLocale = def.getDefaultLocale();
			primaryDomainItem = new DriverConfigurationDefinitionItem(
					configureDefinitionDomain, dci.getOrder(),
					dci.getFieldName(), dci.getName().get(
							defaultLocale.toString()), dci.getDescription()
							.get(defaultLocale.toString()), dci.getType()
							.toString(), null, def.getDefaultLocale()
							.toString());

			for (Locale locale : def.getSupportedLocales()) {
				
				if(locale==defaultLocale)
					continue;
				
				String localeTag = locale.toString();
				DriverConfigurationDefinitionItem subDomainItem = new DriverConfigurationDefinitionItem(
						null, dci.getOrder(),
						dci.getFieldName(), dci.getName().get(localeTag), dci
								.getDescription().get(localeTag), dci.getType()
								.toString(), null, locale.toString());

				primaryDomainItem.addLocales(localeTag, subDomainItem);
			}

			configureDefinitionDomain.addItem(primaryDomainItem);
		}
		return configureDefinitionDomain;
	}

	@Override
	public Driver get(Serializable id) {
		Session s = getCurrentSession();
		Driver d = (Driver) s.get(Driver.class, id);

		return d;
	}

	@Override
	public void delete(Serializable id) {
		Session s = getCurrentSession();
		Object obj = s.load(Driver.class, id);
		s.delete(obj);

	}

	 
}
