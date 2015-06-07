package com.driverstack.yunos.core;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

import com.driverstack.yunos.TimerService;
import com.driverstack.yunos.core.timer.MyJob;
import com.driverstack.yunos.driver.device.TimerListener;

@Component
public class TimerServiceImpl implements TimerService {
	static Logger logger = Logger.getLogger(TimerServiceImpl.class);

	private Scheduler scheduler;

	public TimerServiceImpl() {

		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

		try {
			scheduler = schedFact.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.error("create quartz scheduler failed.");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.driverstack.yunos.core.TimerService#subscribe(com.driverstack.yunos
	 * .driver.device.TimerListener, int)
	 */
	@Override
	public synchronized Object subscribe(TimerListener listener, int interval,
			int code) {

		JobDetail job = JobBuilder.newJob(MyJob.class).build();

		job.getJobDataMap().put(MyJob.TIMER_LISTENER, listener);
		job.getJobDataMap().put(MyJob.TIMER_CODE, code);

		Trigger trigger = newTrigger().withSchedule(
				SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInMilliseconds(interval).repeatForever())

		.build();

		try {
			scheduler.scheduleJob(job, trigger);
			return job.getKey();
		} catch (SchedulerException e) {

			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.driverstack.yunos.core.TimerService#subscribe(com.driverstack.yunos
	 * .driver.device.TimerListener, java.lang.String)
	 */
	@Override
	public synchronized Object subscribe(TimerListener listener,
			String cronExpression, int code) {

		JobDetail job = JobBuilder.newJob(MyJob.class).build();

		job.getJobDataMap().put(MyJob.TIMER_LISTENER, listener);
		job.getJobDataMap().put(MyJob.TIMER_CODE, code);

		Trigger trigger = newTrigger()
				.withSchedule(cronSchedule(cronExpression)).forJob(job).build();

		try {
			scheduler.scheduleJob(job, trigger);
			return job.getKey();
		} catch (SchedulerException e) {

			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.driverstack.yunos.core.TimerService#unsubscribe(com.driverstack.yunos
	 * .driver.device.TimerListener)
	 */
	@Override
	public synchronized void unsubscribe(Object jobId) {

		try {
			JobKey key = (JobKey) jobId;
			scheduler.deleteJob(key);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
