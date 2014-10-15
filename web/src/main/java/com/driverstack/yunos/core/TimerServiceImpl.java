package com.driverstack.yunos.core;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.HashMap;
import java.util.Map;

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

	Scheduler scheduler;

	Map<TimerListener, JobKey> jobs = new HashMap<TimerListener, JobKey>();

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
	public synchronized void subscribe(TimerListener listener, int interval,
			int code) {

		JobDetail job = JobBuilder.newJob(MyJob.class).build();

		job.getJobDataMap().put(MyJob.TIMER_LISTENER, listener);
		job.getJobDataMap().put(MyJob.TIMER_CODE, code);

		Trigger trigger = newTrigger().withSchedule(
				SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInMilliseconds(interval).repeatForever())

		.build();

		if (trigger != null) {
			try {
				scheduler.scheduleJob(job, trigger);

				jobs.put(listener, job.getKey());
			} catch (SchedulerException e) {

				throw new RuntimeException(e);
			}
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
	public synchronized void subscribe(TimerListener listener,
			String cronExpression, int code) {

		JobDetail job = JobBuilder.newJob(MyJob.class).build();

		job.getJobDataMap().put(MyJob.TIMER_LISTENER, listener);
		job.getJobDataMap().put(MyJob.TIMER_CODE, code);

		Trigger

		trigger = newTrigger().withSchedule(cronSchedule(cronExpression))
				.forJob(job).build();

		if (trigger != null) {
			try {
				scheduler.scheduleJob(job, trigger);

				jobs.put(listener, job.getKey());
			} catch (SchedulerException e) {

				throw new RuntimeException(e);
			}
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
	public synchronized void unsubscribe(TimerListener listener) {

		JobKey jobKey = jobs.get(listener);
		try {
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

}
