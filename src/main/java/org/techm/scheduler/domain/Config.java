package org.techm.scheduler.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIG")
public class Config implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Id of Job. */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "ID")
	private String id;

	@Column(name="CONFIG_TYPE")
	private String configType;

	@Column(name="CONFIG_STATE")
	private String configState;

	@Column(name="CITY_ID")
	private String cityId;

	@Column(name="LOCALITY_ID")
	private String localityId;

	@Column(name="CONTROLLER_ID")
	private String controllerId;

	@Column(name="INTENSITY")
	private String intensity;

	@Column(name="PHASE")
	private String phase;

	@Column(name="DATE")
	private String date;

	@Column(name="DAYS")
	private String days;

	@Column(name="ON_TIME")
	private String onTime;

	@Column(name="OFF_TIME")
	private String offTime;

	@Column(name="IS_DIM")
	private boolean isDim;

	@Column(name="DIM_TYPE")
	private String dimType;

	@Column(name="JOB_ID")
	private String jobId;

	@Column(name="TRIGGER_ID")
	private String triggerId;

	@Column(name="LAST_EXEC_TIME")
	private String lastExecutionTime;

	@Column(name="NEXT_EXEC_TIME")
	private String nextExecutionTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigState() {
		return configState;
	}

	public void setConfigState(String configState) {
		this.configState = configState;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getLocalityId() {
		return localityId;
	}

	public void setLocalityId(String localityId) {
		this.localityId = localityId;
	}

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getOnTime() {
		return onTime;
	}

	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}

	public String getOffTime() {
		return offTime;
	}

	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}

	public boolean isDim() {
		return isDim;
	}

	public void setDim(boolean isDim) {
		this.isDim = isDim;
	}

	public String getDimType() {
		return dimType;
	}

	public void setDimType(String dimType) {
		this.dimType = dimType;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	public String getLastExecutionTime() {
		return lastExecutionTime;
	}

	public void setLastExecutionTime(String lastExecutionTime) {
		this.lastExecutionTime = lastExecutionTime;
	}

	public String getNextExecutionTime() {
		return nextExecutionTime;
	}

	public void setNextExecutionTime(String nextExecutionTime) {
		this.nextExecutionTime = nextExecutionTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
