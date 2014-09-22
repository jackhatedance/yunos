package com.driverstack.yunos.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.core.FunctionalDeviceManager;
import com.driverstack.yunos.core.ResoucePath;
import com.driverstack.yunos.domain.FunctionalDevice;
import com.driverstack.yunos.domain.LocalFunctionalDevice;
import com.driverstack.yunos.domain.User;
import com.driverstack.yunos.domain.Vendor;
import com.driverstack.yunos.driver.i18n.bundle.ResourceBundle;
import com.driverstack.yunos.driver.i18n.bundle.ResourceBundles;
import com.driverstack.yunos.functionalDevice.FunctionalDeviceProperties;

@Component
public class FunctionalDeviceServiceImpl extends AbstractService implements
		FunctionalDeviceService {

	@Autowired
	private ResoucePath resourcePath;

	@Autowired
	private FunctionalDeviceManager functionalDeviceManager;

	@Autowired
	private VendorService vendorService;

	@Override
	public void delete(Serializable id) {
		Session s = getCurrentSession();

		FunctionalDevice fd = (FunctionalDevice) s.load(FunctionalDevice.class,
				id);
		getCurrentSession().delete(fd);
	}

	@Override
	public Serializable upload(InputStream in) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			org.apache.commons.io.IOUtils.copy(in, baos);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		byte[] bytes = baos.toByteArray();

		// 1 read the functionalDevice.properties file.
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ResourceBundles bundles = functionalDeviceManager
				.readFunctionalDeviceInfoFromJarFile(bais);

		ResourceBundle defaultBundle = bundles.getBundle(null);

		// 3 save to file system
		try {

			String shortFileName = String.format("%s.jar",
					defaultBundle.get(FunctionalDeviceProperties.NAME));

			String path = String.format("%s%s", resourcePath
					.getFunctionalDevicePath(), defaultBundle
					.get(FunctionalDeviceProperties.DEVELOPER_NAME));

			File dir = new File(path);
			dir.mkdir();

			FileOutputStream out = new FileOutputStream(dir + "/"
					+ shortFileName);
			IOUtils.copy(in, out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// save to DB. create domain entity
		Session s = getCurrentSession();
		User user = null;

		String orgId = defaultBundle
				.get(FunctionalDeviceProperties.ORGNIZATION_ID);
		Vendor orgnization = null;
		if (orgId != null)
			orgnization = vendorService.loadByCodeName(orgId);

		FunctionalDevice domainFunctionalDevice = new FunctionalDevice(
				orgnization,
				defaultBundle.get(FunctionalDeviceProperties.CLASS_NAME),
				defaultBundle.get(FunctionalDeviceProperties.SDK_VERSION),
				user, new Date(),
				defaultBundle.get(FunctionalDeviceProperties.DEFAULT_LOCALE));

		String[] locales = defaultBundle.get(
				FunctionalDeviceProperties.SUPPORTED_LOCALES).split(",");
		for (String locale : locales) {

			ResourceBundle localBundle = bundles.getBundle(locale);

			LocalFunctionalDevice domainLocalFunctionalDevice = new LocalFunctionalDevice(
					localBundle.get(FunctionalDeviceProperties.DISPLAY_NAME),
					localBundle.get(FunctionalDeviceProperties.DESCRIPTION),
					locale);
			domainFunctionalDevice
					.addLocalFunctionalDevice(domainLocalFunctionalDevice);
		}

		return getCurrentSession().save(domainFunctionalDevice);
	}
}
