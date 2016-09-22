package net.abstractfactory.yunos.driver.device;

public class FunctionalDeviceReference {
	private String deviceId;
	/**
	 * functional device index of the physical device
	 */
	private int index;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
