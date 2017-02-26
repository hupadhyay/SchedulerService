package org.techm.scheduler.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This domain object contains all the configuration details of Scheduled job.
 * 
 * @author Himanshu
 */
@Entity
@Table(name = "CONFIG")
public class Config implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Id of Config object. */
	@Id
	@Column(name = "ID")
	private String id;

	/** Holds the type of configuration weather it is daily, weekly or once. */
	@Column(name = "CONFIG_TYPE")
	private String configType;

	/** Holds the state of configuration. */
	@Column(name = "CONFIG_STATE")
	private String configState;

	/** Holds the id of city. */
	@Column(name = "CITY_ID")
	private String cityId;

	/** Holds the id of locality of city. */
	@Column(name = "LOCALITY_ID")
	private String localityId;

	/** Holds the id of controller. */
	@Column(name = "CONTROLLER_ID")
	private String controllerId;

	/** Holds the dim percentage. */
	@Column(name = "INTENSITY")
	private String intensity;

	/** Holds the phase. */
	@Column(name = "PHASE")
	private String phase;

	/** Holds the date of scheduling. */
	@Column(name = "DATE")
	private String date;

	/** Holds the weekdays of scheduling. */
	@Column(name = "DAYS")
	private String days;

	/** Holds the onTime of Scheduling. */
	@Column(name = "ON_TIME")
	private String onTime;

	/** Holds the offTime of scheduling. */
	@Column(name = "OFF_TIME")
	private String offTime;

	/** Holds the flag to check weather it is for onoff or dim. */
	@Column(name = "IS_DIM")
	private boolean isDim;

	/** Holds the type of intensity ie broadcast, dim, individual. */
	@Column(name = "DIM_TYPE")
	private String dimType;

	/** Holds the id of job. */
	@Column(name = "JOB_ID")
	private String jobId;

	/** Holds the id of trigger. */
	@Column(name = "TRIGGER_ID")
	private String triggerId;

	/** Holds the last execution time. */
	@Column(name = "LAST_EXEC_TIME")
	private String lastExecutionTime;

	/** Holds the next execution time. */
	@Column(name = "NEXT_EXEC_TIME")
	private String nextExecutionTime;

	/**
	 * Getter for configId.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for configId
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter for config type.
	 * 
	 * @return configType
	 */
	public String getConfigType() {
		return configType;
	}

	/**
	 * Setter for config type.
	 * 
	 * @param configType
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}

	/**
	 * Getter for config state.
	 * 
	 * @return ConfigState
	 */
	public String getConfigState() {
		return configState;
	}

	/**
	 * Setter for configState.
	 * 
	 * @param configState
	 */
	public void setConfigState(String configState) {
		this.configState = configState;
	}

	/**
	 * Getter for city id.
	 * 
	 * @return
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * Setter for city id.
	 * 
	 * @param cityId
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * Getter for locality id.
	 * 
	 * @return localityId.
	 */
	public String getLocalityId() {
		return localityId;
	}

	/**
	 * Setter for locality Id.
	 * 
	 * @param localityId
	 */
	public void setLocalityId(String localityId) {
		this.localityId = localityId;
	}

	/**
	 * Getter for controller id.
	 * 
	 * @return controllerId
	 */
	public String getControllerId() {
		return controllerId;
	}

	/**
	 * Setter for controllerId.
	 * 
	 * @param controllerId
	 */
	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	/**
	 * Getter for intensity.
	 * 
	 * @return intensity.
	 */
	public String getIntensity() {
		return intensity;
	}

	/**
	 * Setter for intensity.
	 * 
	 * @param intensity
	 */
	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	/**
	 * Getter for phase.
	 * 
	 * @return phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * Setter for phase.
	 * 
	 * @param phase
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * Getter for scheduled date.
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Setter for date.
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Getter for days.
	 * 
	 * @return
	 */
	public String getDays() {
		return days;
	}

	/**
	 * Setter for days
	 * 
	 * @param days
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * Getter for onTime.
	 */
	public String getOnTime() {
		return onTime;
	}

	/**
	 * Setter for onTime.
	 * 
	 * @param onTime
	 */
	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}

	/**
	 * Getter for offTime.
	 * 
	 * @return offTime.
	 */
	public String getOffTime() {
		return offTime;
	}

	/**
	 * Setter for offTime
	 * 
	 * @param offTime
	 */
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}

	/**
	 * Check weather, it is scheduled for dim or not.
	 * 
	 * @return
	 */
	public boolean isDim() {
		return isDim;
	}

	/**
	 * Setter for dim.
	 * 
	 * @param isDim
	 */
	public void setDim(boolean isDim) {
		this.isDim = isDim;
	}

	/**
	 * Getter for dimType.
	 * 
	 * @return dimType.
	 */
	public String getDimType() {
		return dimType;
	}

	/**
	 * Setter for dimType.
	 * 
	 * @param dimType
	 */
	public void setDimType(String dimType) {
		this.dimType = dimType;
	}

	/**
	 * Getter for JobId
	 * 
	 * @return
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * Setter for jobId.
	 * 
	 * @param jobId
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * Getter for triggerId.
	 * 
	 * @return
	 */
	public String getTriggerId() {
		return triggerId;
	}

	/**
	 * Setter for triggerId.
	 * 
	 * @param triggerId
	 */
	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	/**
	 * Getter for last execution time.
	 * 
	 * @return lastExecutionTime.
	 */
	public String getLastExecutionTime() {
		return lastExecutionTime;
	}

	/**
	 * Setter for lastExecutionTime.
	 * 
	 * @param lastExecutionTime
	 */
	public void setLastExecutionTime(String lastExecutionTime) {
		this.lastExecutionTime = lastExecutionTime;
	}

	/**
	 * Getter for nextExecution time.
	 * 
	 * @return
	 */
	public String getNextExecutionTime() {
		return nextExecutionTime;
	}

	/**
	 * Setter for nextExecution time.
	 * 
	 * @param nextExecutionTime
	 */
	public void setNextExecutionTime(String nextExecutionTime) {
		this.nextExecutionTime = nextExecutionTime;
	}

	/**
	 * String representation of Config object.
	 */
	@Override
	public String toString() {
		return "Config [id=" + id + ", configType=" + configType + ", configState=" + configState + ", cityId=" + cityId
				+ ", localityId=" + localityId + ", controllerId=" + controllerId + ", intensity=" + intensity
				+ ", phase=" + phase + ", date=" + date + ", days=" + days + ", onTime=" + onTime + ", offTime="
				+ offTime + ", isDim=" + isDim + ", dimType=" + dimType + ", jobId=" + jobId + ", triggerId="
				+ triggerId + ", lastExecutionTime=" + lastExecutionTime + ", nextExecutionTime=" + nextExecutionTime
				+ "]";
	}
}
