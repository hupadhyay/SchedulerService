package org.techm.scheduler.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** Class which represents job for scheduler. */
public class Job implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Id of Job. */
	private String id;

	/** Name of this job . */
	private String name;

	/** The data attributes of this job. */
	private Map<String, String> jobDataAttributes = new HashMap<>();

	/** JobAction of this job. */
	private JobAction jobAction;

	/** SchedulerKey of this job. */
	private SchedulerKey schedulerKey;
	
	/**
	 * Getter method for id.
	 * 
	 * @return unique Id of job.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter method for id.
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the name for this job.
	 * 
	 * @return Returns the name for this job.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this job.
	 * 
	 * @param newName
	 *            The name to set.
	 */
	public void setName(String newName) {
		name = newName;

	}

	/**
	 * Gets the jobDataAttributes.
	 * 
	 * @return Returns the jobDataAttributes.
	 */
    public Map<String, String> getJobDataAttributes() {
		return jobDataAttributes;
	}

	/**
	 * Setter for jobDataAttributes.
	 *
	 * @param newJobDataAttributes
	 *            The jobDataAttributes to set.
	 * 
	 */
    public void setJobDataAttributes(Map<String, String> newJobDataAttributes) {
		jobDataAttributes = newJobDataAttributes;
	}

	/**
	 * Gets jobAction.
	 * 
	 * @return Returns job action associated to this job.
	 */
	public JobAction getJobAction() {
		return jobAction;
	}

	/**
	 * Sets the job action to this job.
	 * 
	 * @param newJobAction
	 *            the job action to set.
	 */
	public void setJobAction(JobAction newJobAction) {
		jobAction = newJobAction;
	}
	
	/**
	 * Gets schedulerKey.
	 * 
	 * @return Returns scheduler key associated to this job.
	 */

	public SchedulerKey getSchedulerKey() {
		return schedulerKey;
	}

	/**
	 * Sets the scheduler key to this job.
	 * 
	 * @param newSchedulerKey
	 *            the scheduler key to set.
	 */
	public void setSchedulerKey(SchedulerKey newSchedulerKey) {
		schedulerKey = newSchedulerKey;
	}

	/**
	 * Representing object into string form.
	 */
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", jobDataAttributes=" + jobDataAttributes + ", jobAction="
				+ jobAction + ", schedulerKey=" + schedulerKey + "]";
	}

}
