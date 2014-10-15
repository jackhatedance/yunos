package com.driverstack.yunos;

import com.driverstack.yunos.driver.device.TimerListener;

public interface TimerService {

	/**
	 * 
	 * @param listener
	 * @param interval
	 *            in milliseconds
	 */
	public abstract void subscribe(TimerListener listener, int interval, int code);

	public abstract void subscribe(TimerListener listener, String cronExpression, int code);

	public abstract void unsubscribe(TimerListener listener);

}