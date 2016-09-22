package net.abstractfactory.yunos.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.quartz.JobKey;

import net.abstractfactory.yunos.driver.device.TimerListener;

public class JobMap {
	Map<TimerListener, Set<JobKey>> jobs = new HashMap<TimerListener, Set<JobKey>>();

	public void addJob(TimerListener listener, JobKey key) {
		Set<JobKey> jobKeys = jobs.get(listener);
		if (listener == null) {
			jobKeys = new HashSet<JobKey>();
			jobs.put(listener, jobKeys);
		}
		jobKeys.add(key);
	}

	public Set<JobKey> getJobs(TimerListener listener) {
		return jobs.get(listener);
	}

	public void removeJobs(TimerListener listener) {
		jobs.remove(listener);
	}
}
