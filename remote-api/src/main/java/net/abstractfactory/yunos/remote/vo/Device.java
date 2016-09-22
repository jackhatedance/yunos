package net.abstractfactory.yunos.remote.vo;

import java.io.Serializable;

public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146674463860579186L;

	private String id;

	private String modelId;
	private String modelName;
	
	private String deviceClassId;
	private String deviceClassName;
	
	private String vendorId;
	private String vendorName;

	private HardwareType hardwareType;

	private String revision;
	private String mfgSerialNumber;

	private String factoryConfigure;

	private String userConfigure;

	private String deviceState;

	private String name;

	private String location;

	private String description;

	private String driverId;
	private int defaultFunctionalDeviceIndex;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getDeviceClassId() {
		return deviceClassId;
	}

	public void setDeviceClassId(String deviceClassId) {
		this.deviceClassId = deviceClassId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDeviceClassName() {
		return deviceClassName;
	}

	public void setDeviceClassName(String deviceClassName) {
		this.deviceClassName = deviceClassName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public HardwareType getHardwareType() {
		return hardwareType;
	}

	public void setHardwareType(HardwareType hardwareType) {
		this.hardwareType = hardwareType;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getMfgSerialNumber() {
		return mfgSerialNumber;
	}

	public void setMfgSerialNumber(String mfgSerialNumber) {
		this.mfgSerialNumber = mfgSerialNumber;
	}

	public String getFactoryConfigure() {
		return factoryConfigure;
	}

	public void setFactoryConfigure(String factoryConfigure) {
		this.factoryConfigure = factoryConfigure;
	}

	public String getUserConfigure() {
		return userConfigure;
	}

	public void setUserConfigure(String userConfigure) {
		this.userConfigure = userConfigure;
	}

	public String getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public int getDefaultFunctionalDeviceIndex() {
		return defaultFunctionalDeviceIndex;
	}

	public void setDefaultFunctionalDeviceIndex(int defaultFunctionalDeviceIndex) {
		this.defaultFunctionalDeviceIndex = defaultFunctionalDeviceIndex;
	}

}
