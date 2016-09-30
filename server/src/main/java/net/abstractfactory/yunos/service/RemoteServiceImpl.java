package net.abstractfactory.yunos.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import net.abstractfactory.yunos.api.ApiUtils;
import net.abstractfactory.yunos.api.Parameter;
import net.abstractfactory.yunos.api.ParameterType;
import net.abstractfactory.yunos.core.DeviceManager;
import net.abstractfactory.yunos.core.DriverObjectFactory;
import net.abstractfactory.yunos.dao.ApplicationDao;
import net.abstractfactory.yunos.dao.DeviceDao;
import net.abstractfactory.yunos.dao.GenericDao;
import net.abstractfactory.yunos.domain.DeviceClass;
import net.abstractfactory.yunos.domain.Model;
import net.abstractfactory.yunos.domain.User;
import net.abstractfactory.yunos.domain.Vendor;
import net.abstractfactory.yunos.domain.auth.Token;
import net.abstractfactory.yunos.driver.config.ConfigurationItemType;
import net.abstractfactory.yunos.driver.device.FunctionalDevice;
import net.abstractfactory.yunos.driver.device.PhysicalDevice;
import net.abstractfactory.yunos.remote.vo.AccessToken;
import net.abstractfactory.yunos.remote.vo.ConfigurationItem;
import net.abstractfactory.yunos.remote.vo.Device;
import net.abstractfactory.yunos.remote.vo.Driver;
import net.abstractfactory.yunos.remote.vo.DriverConfigurationDefinitionItem;

/**
 * remote service for client, such as mobile app
 * 
 * @author jackding
 * 
 */
@Component
public class RemoteServiceImpl implements RemoteService {
	@Autowired
	private GenericDao genericDao;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private DeviceClassService deviceClassService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private DeviceManager deviceManager;
	@Autowired
	private DriverObjectFactory driverObjectFactory;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private FunctionalDeviceService functionalDeviceService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private ApplicationDao applicationDao;

	public DeviceManager getDeviceManager() {
		return deviceManager;
	}

	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

	@Override
	public AccessToken requestAccessToken(String userId) {

		User user = (User) genericDao.get(User.class, userId);

		Token token = tokenService.createUserToken(user);

		AccessToken accessToken = new AccessToken(token.getId(),
				token.getPassword());

		return accessToken;
	}

	@Override
	public void revokeAccessToken(String key) {

		Token token = (Token) genericDao.load(Token.class, key);

		tokenService.deleteToken(token);

	}

	@Override
	public void changePassword(String userId, String newPassword) {
		User user = userService.getUser(userId);
		if (user == null)
			throw new RuntimeException("invalid userId: " + userId);

		userService.changePassword(user, newPassword);

	}

	@Override
	public List<Device> queryUserDevices(String userId, String deviceClassId) {
		DeviceClass deviceClass = null;
		if (deviceClassId != null)
			deviceClass = (DeviceClass) genericDao.load(DeviceClass.class,
					deviceClassId);

		List<net.abstractfactory.yunos.domain.Device> domainDeviceList = deviceService
				.listByUserId(userId, deviceClass);
		List<Device> remoteDeviceList = new ArrayList<Device>();

		for (net.abstractfactory.yunos.domain.Device d : domainDeviceList) {
			Device remoteDevice = createRemoteDevice(d, null);
			remoteDeviceList.add(remoteDevice);
		}
		return remoteDeviceList;
	}

	protected Device createRemoteDevice(
			net.abstractfactory.yunos.domain.Device domainDevice, String locale) {
		Device remoteDevice = new Device();
		net.abstractfactory.yunos.remote.vo.HardwareType hardwareType = new net.abstractfactory.yunos.remote.vo.HardwareType();
		hardwareType.setVendor(domainDevice.getModel().getVendor()
				.getShortName());
		hardwareType.setModel(domainDevice.getModel().getName());

		BeanUtils.copyProperties(domainDevice, remoteDevice, "model");

		remoteDevice.setHardwareType(hardwareType);

		Vendor vendor = domainDevice.getModel().getVendor();
		vendor.setLocale(locale);

		remoteDevice.setVendorId(vendor.getId());
		remoteDevice.setVendorName(vendor.getShortName());

		DeviceClass deviceClass = domainDevice.getModel().getDeviceClass();
		deviceClass.setLocale(locale);
		remoteDevice.setDeviceClassId(deviceClass.getId());
		remoteDevice.setDeviceClassName(deviceClass.getName());

		Model model = domainDevice.getModel();
		model.setLocale(locale);
		remoteDevice.setModelId(model.getId());
		remoteDevice.setModelName(model.getName());

		if (domainDevice.getDriver() != null)
			remoteDevice.setDriverId(domainDevice.getDriver().getId());

		remoteDevice.setDefaultFunctionalDeviceIndex(domainDevice
				.getDefaultFunctionalDeviceIndex());

		return remoteDevice;
	}

	@Override
	public Device getDevice(String deviceId, String locale) {
		net.abstractfactory.yunos.domain.Device domainDevice = deviceDao
				.get(deviceId);

		Device remoteDevice = createRemoteDevice(domainDevice, locale);

		return remoteDevice;
	}

	@Override
	public String addDevice(String userId, Device device) {
		net.abstractfactory.yunos.domain.Device domainDevice = new net.abstractfactory.yunos.domain.Device();

		updateDomainDeviceWithVo(device, domainDevice);

		User user = (User) genericDao.load(User.class, userId);
		domainDevice.setUser(user);

		return deviceService.save(domainDevice);
	}

	@Override
	public void updateDevice(Device device) {

		net.abstractfactory.yunos.domain.Device domainDevice = (net.abstractfactory.yunos.domain.Device) genericDao
				.get(net.abstractfactory.yunos.domain.Device.class, device.getId());

		updateDomainDeviceWithVo(device, domainDevice);

		deviceService.update(domainDevice);
	}

	private void updateDomainDeviceWithVo(Device device,
			net.abstractfactory.yunos.domain.Device domainDevice) {
		// update fields:modelId, deviceCLassId,name.location,description
		Model domainModel = (Model) genericDao.get(Model.class,
				device.getModelId());
		domainDevice.setModel(domainModel);

		domainDevice.setName(device.getName());
		domainDevice.setLocation(device.getLocation());
		domainDevice.setDescription(device.getDescription());

		net.abstractfactory.yunos.domain.Driver driver = null;
		if (device.getDriverId() != null)
			driver = (net.abstractfactory.yunos.domain.Driver) genericDao.get(
					net.abstractfactory.yunos.domain.Driver.class,
					device.getDriverId());

		domainDevice.setDriver(driver);
		domainDevice.setDefaultFunctionalDeviceIndex(device
				.getDefaultFunctionalDeviceIndex());
	}

	@Override
	public void removeDevice(String deviceId) {
		deviceService.remove(deviceId);
	}

	@Override
	public List<ConfigurationItem> getDeviceInitialConfiguration(
			String deviceId, String driverId) {
		net.abstractfactory.yunos.domain.Device domainDevice = (net.abstractfactory.yunos.domain.Device) genericDao
				.load(net.abstractfactory.yunos.domain.Device.class, deviceId);
		net.abstractfactory.yunos.domain.Driver domainDriver = (net.abstractfactory.yunos.domain.Driver) genericDao
				.load(net.abstractfactory.yunos.domain.Driver.class, driverId);

		List<net.abstractfactory.yunos.domain.ConfigurationItem> domainItems = deviceService
				.createConfiguration(domainDevice, domainDriver);

		List<ConfigurationItem> remoteItems = new ArrayList<ConfigurationItem>();
		for (net.abstractfactory.yunos.domain.ConfigurationItem domainItem : domainItems) {
			remoteItems.add(domainItem.toRemoteVO());
		}

		return remoteItems;
	}

	@Override
	public List<ConfigurationItem> getDeviceConfiguration(String deviceId) {
		net.abstractfactory.yunos.domain.Device domainDevice = (net.abstractfactory.yunos.domain.Device) genericDao
				.get(net.abstractfactory.yunos.domain.Device.class, deviceId);

		List<ConfigurationItem> list = new ArrayList<ConfigurationItem>();
		Map<String, net.abstractfactory.yunos.domain.ConfigurationItem> map = domainDevice
				.getUserConfigurationItems();
		for (net.abstractfactory.yunos.domain.ConfigurationItem domainItem : map
				.values()) {
			list.add(domainItem.toRemoteVO());
		}

		return list;
	}

	@Override
	public void updateDeviceConfiguration(String deviceId,
			List<ConfigurationItem> configuration) {

		net.abstractfactory.yunos.domain.Device domainDevice = deviceDao
				.get(deviceId);
		Map<String, net.abstractfactory.yunos.domain.ConfigurationItem> map = domainDevice
				.getUserConfigurationItems();
		map.clear();

		for (ConfigurationItem remoteItem : configuration) {
			net.abstractfactory.yunos.domain.ConfigurationItem domainItem = new net.abstractfactory.yunos.domain.ConfigurationItem(
					remoteItem);
			domainDevice.addConfigurationItem(domainItem);
		}

		genericDao.saveOrUpdate(domainDevice);
	}

	@Override
	public Object operateDevice(String deviceId, int functionalDeviceIndex,
			String operation, Map<String, String> parameters) {

		PhysicalDevice physicalDevice = deviceManager
				.getPhysicalDeviceObject(deviceId);

		FunctionalDevice functionalDevice = physicalDevice
				.getFunctionDevice(functionalDeviceIndex);
		// perform operation on device
		// Object ret = device.invoke(operation, null);
		Method method = ApiUtils.getMethod(functionalDevice, operation);

		List<Parameter> paramList = ApiUtils.getParameterInfo(method);

		int paramSize = paramList.size();
		Object[] parameterObjects = new Object[paramSize];

		for (int i = 0; i < paramSize; i++) {
			Parameter p = paramList.get(i);
			String value = parameters.get(p.getName());

			ParameterType pt = ParameterType.getType(p.getType());
			Object objValue = pt.fromString(value);

			parameterObjects[i] = objValue;
		}

		try {
			return method.invoke(functionalDevice, parameterObjects);
		} catch (InvocationTargetException e) {
			// it is not useful to throw the InvocationTargetException, the
			// cause is more helpful.
			throw new RuntimeException(e.getCause());
		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public List<net.abstractfactory.yunos.remote.vo.DeviceClass> getDeviceClasses(
			String vendorId, String locale) {

		List<DeviceClass> domainDeviceClasses;
		if (vendorId != null) {
			Vendor vendor = (Vendor) genericDao.load(Vendor.class, vendorId);

			domainDeviceClasses = deviceClassService.find(vendor, locale);
		} else
			domainDeviceClasses = genericDao.getAll(DeviceClass.class);

		List<net.abstractfactory.yunos.remote.vo.DeviceClass> remoteObjects = new ArrayList<net.abstractfactory.yunos.remote.vo.DeviceClass>();
		for (DeviceClass d : domainDeviceClasses) {
			net.abstractfactory.yunos.remote.vo.DeviceClass r = new net.abstractfactory.yunos.remote.vo.DeviceClass();
			BeanUtils.copyProperties(d.get(locale), r);

			remoteObjects.add(r);
		}

		return remoteObjects;
	}

	@Override
	public List<net.abstractfactory.yunos.remote.vo.Vendor> getAllVendors(
			String locale) {
		Locale aLocale = Locale.forLanguageTag(locale.replaceAll("_", "-"));

		List<net.abstractfactory.yunos.domain.Vendor> domainVendors = genericDao
				.getAll(Vendor.class);

		List<net.abstractfactory.yunos.remote.vo.Vendor> remoteVendors = new ArrayList<net.abstractfactory.yunos.remote.vo.Vendor>();
		for (net.abstractfactory.yunos.domain.Vendor d : domainVendors) {
			net.abstractfactory.yunos.remote.vo.Vendor r = new net.abstractfactory.yunos.remote.vo.Vendor();
			BeanUtils.copyProperties(d.get(locale), r);

			remoteVendors.add(r);
		}

		return remoteVendors;
	}

	@Override
	public List<net.abstractfactory.yunos.remote.vo.Model> getModels(
			String vendorId, String deviceClassId, String locale) {

		net.abstractfactory.yunos.domain.Vendor vendor = (net.abstractfactory.yunos.domain.Vendor) genericDao
				.load(net.abstractfactory.yunos.domain.Vendor.class, vendorId);

		net.abstractfactory.yunos.domain.DeviceClass deviceClass = null;
		if (deviceClassId != null)
			deviceClass = (net.abstractfactory.yunos.domain.DeviceClass) genericDao
					.load(net.abstractfactory.yunos.domain.DeviceClass.class,
							deviceClassId);

		List<net.abstractfactory.yunos.domain.Model> domainModels = modelService
				.getModels(vendor, deviceClass, locale);

		List<net.abstractfactory.yunos.remote.vo.Model> remoteModels = new ArrayList<net.abstractfactory.yunos.remote.vo.Model>();
		for (net.abstractfactory.yunos.domain.Model dm : domainModels) {
			net.abstractfactory.yunos.remote.vo.Model rm = new net.abstractfactory.yunos.remote.vo.Model();
			BeanUtils.copyProperties(dm.get(locale), rm);

			remoteModels.add(rm);
		}

		return remoteModels;
	}

	@Override
	public List<Driver> getAvailableDrivers(String modelId) {

		net.abstractfactory.yunos.domain.Model model = (net.abstractfactory.yunos.domain.Model) genericDao
				.load(net.abstractfactory.yunos.domain.Model.class, modelId);
		List<net.abstractfactory.yunos.domain.Driver> domainDrivers = driverService
				.findAvailableDrivers(model);

		List<Driver> remoteObjects = new ArrayList<Driver>();
		for (net.abstractfactory.yunos.domain.Driver domainObject : domainDrivers) {
			Driver driver = new Driver(domainObject.getId(),
					domainObject.getName(), domainObject.getVersion(),
					domainObject.getDeveloperName());

			remoteObjects.add(driver);
		}

		return remoteObjects;
	}

	@Override
	public List<DriverConfigurationDefinitionItem> getDriverConfigurationDefinitionItems(
			String driverId, String locale) {

		net.abstractfactory.yunos.domain.Driver driver = (net.abstractfactory.yunos.domain.Driver) genericDao
				.load(net.abstractfactory.yunos.domain.Driver.class, driverId);

		List<net.abstractfactory.yunos.domain.DriverConfigurationDefinitionItem> domainItems = driver
				.getConfigurationDefinition().getItems();

		List<net.abstractfactory.yunos.remote.vo.DriverConfigurationDefinitionItem> remoteItems = new ArrayList<net.abstractfactory.yunos.remote.vo.DriverConfigurationDefinitionItem>();
		for (net.abstractfactory.yunos.domain.DriverConfigurationDefinitionItem di : domainItems) {
			net.abstractfactory.yunos.remote.vo.DriverConfigurationDefinitionItem ri = new net.abstractfactory.yunos.remote.vo.DriverConfigurationDefinitionItem();

			net.abstractfactory.yunos.domain.DriverConfigurationDefinitionItem localDomainDefItem = di
					.get(locale);
			BeanUtils.copyProperties(localDomainDefItem, ri);

			ri.setType(new ConfigurationItemType(localDomainDefItem.getType(),
					localDomainDefItem.getTypeParameter()));

			remoteItems.add(ri);
		}

		return remoteItems;

	}

	@Override
	public List<net.abstractfactory.yunos.remote.vo.FunctionalDevice> getFunctionalDevices(
			String deviceId, String locale) {
		List<net.abstractfactory.yunos.remote.vo.FunctionalDevice> voFunctionalDeviceList = new ArrayList<net.abstractfactory.yunos.remote.vo.FunctionalDevice>();
		/**
		 * the functional device list is runtime value.
		 */

		net.abstractfactory.yunos.domain.Device domainDevice = deviceDao
				.get(deviceId);

		if (domainDevice.getDriver() != null) {
			PhysicalDevice pd = deviceManager.getPhysicalDeviceObject(deviceId);

			// runtime FD to domain object
			List<FunctionalDevice> runtimeFDList = pd.getFunctionDevices();
			for (int i = 0; i < runtimeFDList.size(); i++) {
				FunctionalDevice rfd = runtimeFDList.get(i);

				String className = rfd.getClass().getInterfaces()[0]
						.getCanonicalName();
				net.abstractfactory.yunos.domain.FunctionalDevice domainFD = functionalDeviceService
						.getByClassName(className);

				domainFD.setLocale(locale);

				net.abstractfactory.yunos.remote.vo.FunctionalDevice voFD = new net.abstractfactory.yunos.remote.vo.FunctionalDevice(
						deviceId, domainDevice.getName(), i, domainFD
								.getOrganization().getCodeName(),
						domainFD.getArtifactId(), domainFD.getOrganization()
								.getShortName(), domainFD.getDisplayName());

				voFunctionalDeviceList.add(voFD);
			}

			// set index
			for (int i = 0; i < voFunctionalDeviceList.size(); i++)
				voFunctionalDeviceList.get(i).setIndex(i);
		}

		return voFunctionalDeviceList;
	}

	@Override
	public List<net.abstractfactory.yunos.remote.vo.FunctionalDevice> queryUserFunctionalDevices(
			String userId, String className, String locale) {

		// 1. query device(physical) from DB.
		List<net.abstractfactory.yunos.domain.Device> domainDeviceList = deviceService
				.listByUserId(userId, null);

		// 2. collect functional device in memory
		List<net.abstractfactory.yunos.remote.vo.FunctionalDevice> voFunctionalDeviceList = new ArrayList<net.abstractfactory.yunos.remote.vo.FunctionalDevice>();

		for (net.abstractfactory.yunos.domain.Device dd : domainDeviceList) {

			if (dd.getDriver() == null)
				continue;

			PhysicalDevice pd = deviceManager.getPhysicalDeviceObject(dd
					.getId());

			List<FunctionalDevice> runtimeFDList = pd.getFunctionDevices();
			for (int i = 0; i < runtimeFDList.size(); i++) {
				FunctionalDevice rfd = runtimeFDList.get(i);
				Class queryClass;
				try {
					queryClass = Class.forName(className, true,
							driverObjectFactory
									.getFunctionalDeviceClassLoader());

				} catch (ClassNotFoundException e) {

					throw new RuntimeException(e);
				}

				if (queryClass.isAssignableFrom(rfd.getClass())) {

					net.abstractfactory.yunos.domain.FunctionalDevice domainFD = functionalDeviceService
							.getByClassName(className);

					domainFD.setLocale(locale);

					String deviceId = dd.getId();

					net.abstractfactory.yunos.remote.vo.FunctionalDevice voFD = new net.abstractfactory.yunos.remote.vo.FunctionalDevice(
							deviceId, dd.getName(), i, domainFD
									.getOrganization().getCodeName(),
							domainFD.getArtifactId(), domainFD
									.getOrganization().getShortName(),
							domainFD.getDisplayName());

					voFunctionalDeviceList.add(voFD);
				}
			}
		}

		return voFunctionalDeviceList;
	}

	@Override
	public void reloadDriver(String deviceId) {
		net.abstractfactory.yunos.domain.Device domainDevice = deviceDao
				.get(deviceId);
		deviceManager.reloadDriver(domainDevice);

	}

	@Override
	public net.abstractfactory.yunos.remote.vo.User getUser(String userId) {
		User domainUser = userService.getUser(userId);

		net.abstractfactory.yunos.remote.vo.User remoteUser = new net.abstractfactory.yunos.remote.vo.User(
				domainUser.getId(), "", domainUser.getEmail(),
				domainUser.getFirstName(), domainUser.getLastName());

		return remoteUser;
	}

	@Override
	public void createUser(net.abstractfactory.yunos.remote.vo.User user) {
		User domainUser = new User();
		BeanUtils.copyProperties(user, domainUser);

		userService.createUser(domainUser);

	}
}
