package org.techm.scheduler.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
 * 
 * @author Himanshu
 *
 */
@Entity
@Table(name="TRIGGER")
public class Trigger implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Id of trigger. */
	@Id
	@Column(name="ID")
	private String id;

	/** Name of this trigger . */
	@Column(name="NAME")
	private String name;

	/** Scheduler of this trigger. */
	@Enumerated(EnumType.STRING)
	@Column(name="SCHEDULER_TYPE")
	private SchedulerType schedulerType;

	/** The cron scheduler attributes of this trigger. */
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="TRIGGER_ATTRIB", joinColumns=@JoinColumn(name="TRIGGER_ID"))
	private Map<String, String> schedularAttributes = new HashMap<>();

	/** SchedulerKey of this trigger. */
	@Embedded
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
	 * Gets the cronSchedularAttrs.
	 * 
	 * @return Returns the cronSchedularAttrs.
	 */
	public Map<String, String> getSchedularAttributes() {
		return schedularAttributes;
	}

	/**
	 * Setter for schedularAttrs.
	 *
	 * @param newSchedularAttrs
	 *            The schedularAttrs to set.
	 */
	public void setSchedularAttributes(Map<String, String> newSchedularAttrs) {
		schedularAttributes = newSchedularAttrs;
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
		return "Trigger [id=" + id + ", name=" + name + ", schedulerType=" + schedulerType + ", schedularAttrs="
				+ schedularAttributes + ", schedulerKey=" + schedulerKey + "]";
	}
}
