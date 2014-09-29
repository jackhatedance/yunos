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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

		// remove file
		String fullFileName = getFullFunctionalDeviceJarFileName(fd
				.getOrganization().getCodeName(), fd.getArtifactId());
		File file = new File(fullFileName);
		file.delete();

		// delete from DB
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

			String artifactId = defaultBundle
					.get(FunctionalDeviceProperties.ARTIFACT_ID);

			String organizationId = defaultBundle
					.get(FunctionalDeviceProperties.ORGANIZATION_ID);

			String path = getFunctionalDeviceJarPath();

			File dir = new File(path);
			dir.mkdir();

			String fullFileName = getFullFunctionalDeviceJarFileName(
					organizationId, artifactId);
			FileOutputStream out = new FileOutputStream(fullFileName);

			bais.reset();
			IOUtils.copy(bais, out);
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// save to DB. create domain entity
		Session s = getCurrentSession();
		User user = null;

		String orgId = defaultBundle
				.get(FunctionalDeviceProperties.ORGANIZATION_ID);
		if (orgId == null)
			throw new RuntimeException("organizationId is not set.");

		Vendor orgnization = vendorService.loadByCodeName(orgId);
		if (orgnization == null)
			throw new RuntimeException("organizationId is invalid:" + orgId);

		FunctionalDevice domainFunctionalDevice = new FunctionalDevice(
				orgnization,
				defaultBundle.get(FunctionalDeviceProperties.ARTIFACT_ID),
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

		return save(domainFunctionalDevice);
	}

	private String getFunctionalDeviceJarShortFileName(String organizationId,
			String artifactId) {
		String shortFileName = String.format("%s-%s.jar", organizationId,
				artifactId);
		return shortFileName;
	}

	private String getFunctionalDeviceJarPath() {

		String dir = resourcePath.getFunctionalDevicePath();
		return dir;
	}

	private String getFullFunctionalDeviceJarFileName(String organizationId,
			String artifactId) {
		return String
				.format("%s%s",
						getFunctionalDeviceJarPath(),
						getFunctionalDeviceJarShortFileName(organizationId,
								artifactId));
	}

	@Override
	public FunctionalDevice getByClassName(String className) {
		Session s = getCurrentSession();
		Criteria c = s.createCriteria(FunctionalDevice.class);
		c.add(Restrictions.eq("className", className));

		return (FunctionalDevice) c.uniqueResult();
	}

	@Override
	public FunctionalDevice get(String organizationId, String artifactId) {

		Session s = getCurrentSession();
		Criteria c = s.createCriteria(FunctionalDevice.class);

		Vendor org = (Vendor) s.load(Vendor.class, organizationId);
		c.add(Restrictions.eq("organization", org));

		c.add(Restrictions.eq("artifactId", artifactId));

		return (FunctionalDevice) c.uniqueResult();
	}

	@Override
	public Serializable save(FunctionalDevice obj) {

		FunctionalDevice fd = getByClassName(obj.getClassName());
		if (fd != null)
			throw new RuntimeException("classname is already existing:"
					+ obj.getClassName());

		String organizationId = obj.getOrganization().getCodeName();

		fd = get(organizationId, obj.getArtifactId());
		if (fd != null)
			throw new RuntimeException(String.format(
					"organizationId-artifactId:%s-%s already existing:",
					organizationId, obj.getClassName()));

		return getCurrentSession().save(obj);

	}
}
