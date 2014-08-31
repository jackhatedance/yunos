package com.driverstack.yunos.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.api.ApiUtils;
import com.driverstack.yunos.api.Parameter;
import com.driverstack.yunos.api.ParameterType;
import com.driverstack.yunos.core.DeviceManager;
import com.driverstack.yunos.dao.ApplicationDao;
import com.driverstack.yunos.dao.DeviceDao;
import com.driverstack.yunos.dao.GenericDao;
import com.driverstack.yunos.device.FunctionalDevice;
import com.driverstack.yunos.device.PhysicalDevice;
import com.driverstack.yunos.domain.Token;
import com.driverstack.yunos.remote.vo.Brand;
import com.driverstack.yunos.remote.vo.Device;
import com.driverstack.yunos.remote.vo.HardwareType;
import com.driverstack.yunos.remote.vo.Product;

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
	private DeviceManager deviceManager;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelService modelService;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private ApplicationDao applicationDao;

	public DeviceManager getDeviceManager() {
		return deviceManager;
	}

	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

	@Override
	public String login(String userId, String password) {
		Token t = authenticationService.login(userId, password);

		if (t != null)
			return t.getId();
		else
			return null;
	}

	@Override
	public List<Device> listDevice(String userId) {
		List<com.driverstack.yunos.domain.Device> domainDeviceList = deviceService
				.listByUserId(userId);
		List<Device> remoteDeviceList = new ArrayList<Device>();

		for (com.driverstack.yunos.domain.Device d : domainDeviceList) {
			Device remoteDevice = createRemoteDevice(d);
			remoteDeviceList.add(remoteDevice);
		}
		return remoteDeviceList;
	}

	protected Device createRemoteDevice(
			com.driverstack.yunos.domain.Device domainDevice) {
		Device remoteDevice = new Device();
		com.driverstack.yunos.remote.vo.HardwareType hardwareType = new com.driverstack.yunos.remote.vo.HardwareType();
		hardwareType.setBrand(domainDevice.getModel().getProduct().getBrand().getName());
		hardwareType.setProduct(domainDevice.getModel().getProduct().getName());
		hardwareType.setModel(domainDevice.getModel().getName());
		
		
		BeanUtils.copyProperties(domainDevice, remoteDevice, "model");

		remoteDevice.setHardwareType(hardwareType);
		return remoteDevice;
	}

	@Override
	public Device getDevice(String deviceId) {
		com.driverstack.yunos.domain.Device domainDevice = deviceDao
				.get(deviceId);

		Device remoteDevice = createRemoteDevice(domainDevice);

		return remoteDevice;
	}

	@Override
	public void addDevice(String userId, Device device) {
		com.driverstack.yunos.domain.Device domainDevice = new com.driverstack.yunos.domain.Device();
		BeanUtils.copyProperties(device, domainDevice);

		deviceService.saveDevice(domainDevice);
	}

	@Override
	public void removeDevice(String deviceId) {
		deviceService.remove(deviceId);
	}

	@Override
	public Object loadDeviceConfig(String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDeviceConfig(String deviceId, String config) {
		// TODO Auto-generated method stub

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
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Brand> getAllBrands(String locale) {
		Locale aLocale = Locale.forLanguageTag(locale.replaceAll("_", "-"));
		List<com.driverstack.yunos.domain.Brand> domainBrands = brandService
				.getAllBrands(locale);

		List<Brand> remoteBrands = new ArrayList<Brand>();
		for (com.driverstack.yunos.domain.Brand db : domainBrands) {
			Brand rb = new Brand();
			BeanUtils.copyProperties(db, rb);

			remoteBrands.add(rb);
		}

		return remoteBrands;
	}

	@Override
	public List<Product> getProducts(String brandId, String locale) {

		com.driverstack.yunos.domain.Brand brand = (com.driverstack.yunos.domain.Brand) genericDao
				.load(com.driverstack.yunos.domain.Brand.class, brandId);
		List<com.driverstack.yunos.domain.Product> domainProducts = productService
				.getProducts(brand, locale);

		List<Product> remoteProducts = new ArrayList<Product>();
		for (com.driverstack.yunos.domain.Product dp : domainProducts) {
			Product rp = new Product();
			BeanUtils.copyProperties(dp, rp);

			remoteProducts.add(rp);
		}

		return remoteProducts;
	}

	@Override
	public List<com.driverstack.yunos.remote.vo.Model> getModels(
			String productId, String locale) {

		com.driverstack.yunos.domain.Product product = (com.driverstack.yunos.domain.Product) genericDao
				.load(com.driverstack.yunos.domain.Product.class, productId);
		List<com.driverstack.yunos.domain.Model> domainModels = modelService
				.getModels(product, locale);

		List<com.driverstack.yunos.remote.vo.Model> remoteModels = new ArrayList<com.driverstack.yunos.remote.vo.Model>();
		for (com.driverstack.yunos.domain.Model dm : domainModels) {
			com.driverstack.yunos.remote.vo.Model rm = new com.driverstack.yunos.remote.vo.Model();
			BeanUtils.copyProperties(dm, rm);

			remoteModels.add(rm);
		}

		return remoteModels;
	}
}
