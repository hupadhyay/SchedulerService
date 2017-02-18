package org.techm.scheduler.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Himanshu
 *
 */
public class Trigger implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Id of trigger. */
	private String id;

	/** Name of this trigger . */
	private String name;

	/** Scheduler of this trigger. */
	private SchedulerType schedulerType;

	/** The simple scheduler attributes of this trigger. */
	private Map<String, String> simpleSchedularAttrs = new HashMap<>();

	/** The cron scheduler attributes of this trigger. */
	private Map<String, String> cronSchedularAttrs = new HashMap<>();

	/** SchedulerKey of this trigger. */
	private SchedulerKey schedulerKey;
	
	/**
	 * Getter method for id.
	 * 
	 * @return unique Id of trigger.
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
	 * Gets the name for this trigger.
	 * 
	 * @return Returns the name for this trigger.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this trigger.
	 * 
	 * @param newName
	 *            The name to set.
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Gets the schedulerType.
	 * 
	 * @return Returns the schedulerType.
	 */
	public SchedulerType getSchedulerType() {
		return schedulerType;
	}

	/**
	 * Sets the scheduler type to this trigger.
	 * 
	 * @param newSchedulerType
	 *            the scheduler type to set.
	 */
	public void setSchedulerType(SchedulerType newSchedulerType) {
		schedulerType = newSchedulerType;
	}

	/**
	 * Gets the jobDataAttributes.
	 * 
	 * @return Returns the jobDataAttributes.
	 */
	public Map<String, String> getSimpleSchedularAttrs() {
		return simpleSchedularAttrs;
	}

	/**
	 * Setter for simpleSchedularAttrs.
	 *
	 * @param newSimpleSchedularAttrs
	 *            The simpleSchedularAttrs to set.
	 */
	public void setSimpleSchedularAttrs(Map<String, String> newSimpleSchedularAttrs) {
		simpleSchedularAttrs = newSimpleSchedularAttrs;
	}

	/**
	 * Gets the cronSchedularAttrs.
	 * 
	 * @return Returns the cronSchedularAttrs.
	 */
	public Map<String, String> getCronSchedularAttrs() {
		return cronSchedularAttrs;
	}

	/**
	 * Setter for cronSchedularAttrs.
	 *
	 * @param newCronSchedularAttrs
	 *            The cronSchedularAttrs to set.
	 */
	public void setCronSchedularAttrs(Map<String, String> newCronSchedularAttrs) {
		cronSchedularAttrs = newCronSchedularAttrs;
	}

	/**
	 * Gets schedulerKey.
	 * 
	 * @return Returns scheduler key associated to this trigger.
	 */
	public SchedulerKey getSchedulerKey() {
		return schedulerKey;
	}

	/**
	 * Sets the scheduler key to this trigger.
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
		return "Trigger [id=" + id + ", name=" + name + ", schedulerType=" + schedulerType + ", simpleSchedularAttrs="
				+ simpleSchedularAttrs + ", cronSchedularAttrs=" + cronSchedularAttrs + ", schedulerKey=" + schedulerKey
				+ "]";
	}
}
