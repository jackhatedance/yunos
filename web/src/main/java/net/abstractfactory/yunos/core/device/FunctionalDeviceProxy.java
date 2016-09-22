package net.abstractfactory.yunos.core.device;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.abstractfactory.yunos.core.DeviceManager;
import net.abstractfactory.yunos.driver.device.PhysicalDevice;

/**
 * this proxy tracking to the functional device object even if the driver is
 * re-loaded.
 * 
 * @author jack
 * 
 */
public class FunctionalDeviceProxy implements InvocationHandler,
		DeviceStatusChangeListener {
	private Object target;

	private DeviceManager deviceManager;
	private String deviceId;
	private int index;

	public FunctionalDeviceProxy(DeviceManager deviceManager, String deviceId,
			int index) {
		this.deviceManager = deviceManager;
		this.deviceId = deviceId;
		this.index = index;

	}

	private Object getTarget() {
		if (target == null) {
			PhysicalDevice pd = deviceManager.getPhysicalDeviceObject(deviceId);
			target = pd.getFunctionDevice(index);
		}

		return target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		if (method.getName().equals("onDriverLoad")) {
			// DriverChangeListener methods
			onDriverLoad();
			return null;
		} else if (method.getName().equals("onDriverUnload")) {
			onDriverUnload();
			return null;
		} else {

			Object t = getTarget();
			return method.invoke(t, args);
		}
	}

	@Override
	public void onDriverUnload() {
		target = null;
	}

	@Override
	public void onDriverLoad() {
		// TODO Auto-generated method stub

	}
}
