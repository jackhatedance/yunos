package com.driverstack.yunos.core.timer;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.driverstack.yunos.driver.device.TimerListener;

public class MyJob implements Job {
	public static final String TIMER_LISTENER = "timer_listner";
	public static final String TIMER_CODE = "code";

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap data = context.getJobDetail().getJobDataMap();
		TimerListener tl = (TimerListener) data.get(TIMER_LISTENER);
		int code = (int) data.get(TIMER_CODE);
		

		tl.onTimerEvent(code);
	}

}
