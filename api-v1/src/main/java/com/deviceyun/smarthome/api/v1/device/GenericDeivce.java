package com.deviceyun.smarthome.api.v1.device;

/**
 * extra entry for vendor specific API
 * 
 * @author jack
 * 
 */
public interface GenericDeivce {
	Object[] invoke(String method, Object[] parameters);
}
