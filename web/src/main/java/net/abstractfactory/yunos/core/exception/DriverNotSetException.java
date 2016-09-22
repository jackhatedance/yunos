package net.abstractfactory.yunos.core.exception;

public class DriverNotSetException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -104978461459000994L;

	public DriverNotSetException(String deviceId) {
		super(String.format("driver of device(#%s) is not set", deviceId));

	}

}
