package com.driverstack.yunos;

import com.driverstack.yunos.driver.device.TimerListener;

public interface TimerService {

	/**
	 * 
	 * @param listener
	 * @param interval
	 *            in milliseconds
	 */
	public Object subscribe(TimerListener listener, int interval, int code);

	public Object subscribe(TimerListener listener, String cronExpression,
			int code);

	public void unsubscribe(Object jobId);

}