package net.abstractfactory.yunos.functionalDevice;

import net.abstractfactory.yunos.driver.i18n.I18nString;
/**
 * represent the FD info load from jar file.
 * 
 * @author jack
 *
 */
public class FunctionalDevice {
	private String name;
	private I18nString displayName;
	private I18nString description;
	private String vendor;
	private String className;
	private String sdkVersion;
	private String defaultLocale;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public I18nString getDisplayName() {
		return displayName;
	}

	public void setDisplayName(I18nString displayName) {
		this.displayName = displayName;
	}

	public I18nString getDescription() {
		return description;
	}

	public void setDescription(I18nString description) {
		this.description = description;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

}
