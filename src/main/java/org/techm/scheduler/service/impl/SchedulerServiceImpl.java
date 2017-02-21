package org.techm.scheduler.service.impl;

import java.util.Map;
import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.domain.SchedulerKey;
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.service.SchedulerService;
import org.techm.scheduler.utils.QuartzJob;

public class SchedulerServiceImpl implements SchedulerService {

	/**
	 * 
	 */
	@Override
	public boolean scheduleService(Job job, Trigger trigger, String configId, String strUrl) {
		org.quartz.Trigger qrtzTrigger = createQuartzTrigger(trigger);

		SchedulerKey jobSchedulerKey = job.getSchedulerKey();
		JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
				.withIdentity(jobSchedulerKey.getName(), jobSchedulerKey.getGroup()).build();
		jobDetail.getJobDataMap().put("job", job);
		jobDetail.getJobDataMap().put("configId", configId);
		jobDetail.getJobDataMap().put("url", strUrl);

		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.scheduleJob(jobDetail, qrtzTrigger);
			return true;
		} catch (SchedulerException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean removeService(Trigger trigger) {
		SchedulerKey schedulerKey = trigger.getSchedulerKey();
		TriggerKey triggerKey = new TriggerKey(schedulerKey.getName(), schedulerKey.getGroup());

		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			scheduler.unscheduleJob(triggerKey);
			return true;
		} catch (SchedulerException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	private org.quartz.Trigger createQuartzTrigger(Trigger trigger) {
		org.quartz.Trigger qTrigger = null;

		Map<String, String> map = trigger.getSchedularAttributes();

		qTrigger = createCronScheduler(trigger, map);

		return qTrigger;
	}

	/**
	 * 
	 * @param prmTrigger
	 * @param map
	 * @return
	 */
	private org.quartz.Trigger createCronScheduler(Trigger prmTrigger, Map<String, String> map) {
		org.quartz.Trigger qTrigger = null;
		SchedulerKey schedulerKey = prmTrigger.getSchedulerKey();
		int initialDelay = getValueInInt(map.get("initialDelay"));
		IntervalUnit unit = getTimeUnit(map.get("timeUnit"));
		String cronExpression = getCronExpression(map.get("cronExpression"));
		TimeZone timeZone = getTimeZone(map.get("timeZone"));

		TriggerBuilder<org.quartz.Trigger> builder = TriggerBuilder.newTrigger().withIdentity(schedulerKey.getName(),
				schedulerKey.getGroup());

		if (initialDelay == 0) {
			builder.startNow();
		} else {
			builder.startAt(DateBuilder.futureDate(initialDelay, unit));
		}

		CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		cronBuilder.inTimeZone(timeZone);

		qTrigger = builder.withSchedule(cronBuilder).build();

		return qTrigger;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	private int getValueInInt(String strInitDeley) throws NumberFormatException {
		int initDelay = -1;
		if (strInitDeley != null && strInitDeley.length() != 0) {
			initDelay = Integer.parseInt(strInitDeley);
		}
		return initDelay;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	private String getCronExpression(String cronExp) {
		if (cronExp != null && cronExp.length() != 0) {
			return cronExp;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	private TimeZone getTimeZone(String timeZone) {
		if (timeZone != null && timeZone.length() != 0) {
			return TimeZone.getTimeZone(timeZone);
		}
		return TimeZone.getDefault();
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	private IntervalUnit getTimeUnit(String strUnit) {
		if (strUnit.equalsIgnoreCase("ms") || strUnit.equalsIgnoreCase("millisecond")) {
			return IntervalUnit.MILLISECOND;
		} else if (strUnit.equalsIgnoreCase("sec") || strUnit.equalsIgnoreCase("second")) {
			return IntervalUnit.SECOND;
		} else if (strUnit.equalsIgnoreCase("min") || strUnit.equalsIgnoreCase("minutes")) {
			return IntervalUnit.MINUTE;
		} else if (strUnit.equalsIgnoreCase("hrs") || strUnit.equalsIgnoreCase("hours")) {
			return IntervalUnit.HOUR;
		} else if (strUnit.equalsIgnoreCase("day")) {
			return IntervalUnit.DAY;
		} else if (strUnit.equalsIgnoreCase("week")) {
			return IntervalUnit.WEEK;
		} else if (strUnit.equalsIgnoreCase("year")) {
			return IntervalUnit.YEAR;
		}
		return null;
	}

}
