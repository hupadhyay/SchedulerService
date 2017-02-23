package org.techm.scheduler.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Class encapsulating the job action properties of a job. */
@Entity
@Table(name="JOB_ACTION")
public class JobAction implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    /** The action type associated to this job action. */
    @Enumerated(EnumType.STRING)
    @Column(name = "ACION_TYPE")
    private ActionType actionType;
    
    /** The device id associated to this job action. */
    @Column(name = "DEVICE_ID")
    private String deviceId;
    
    /** The command id associated to this job action. */
    @Column(name = "COMMAND_ID")
    private String commandId;
    
    /** The event id associated to this job action. */
    @Column(name = "EVENT_ID")
    private String eventId;
    
    /** The message associated to this job action. */
    @Column(name = "MESSAGE", length = 500)
    private String message;
    
    /** The group id associated to this job action. */
    @Column(name = "GROUP_ID")
    private String groupId;
    
    /** The stream id associated to this job action. */
    @Column(name = "STREAM_ID")
    private String streamId;
    
    /** The ruleTrigger id associated to this job action. */
    @Column(name = "RULE_TRIGGER_ID")
    private String ruleTriggerId;
    
    /**
     * Gets the Id of jobAction.
     * 
     * @return Returns the Id.
     */
    public Long getId() {
		return id;
	}
    
    /**
     * Sets the Id of job.
     * 
     * @param id of the jobAction to set.
     */
    public void setId(Long id) {
		this.id = id;
	}

    /**
     * Gets the action type  of job.
     * 
     * @return Returns the action type.
     */
	public ActionType getActionType() {
		return actionType;
	}

    /**
     * Sets the action type of job.
     * 
     * @param newActionType the action type to set.
     */
	public void setActionType(ActionType newActionType) {
		actionType = newActionType;
	}

    /**
     * Gets the device id  of job.
     * 
     * @return Returns the device id.
     */
	public String getDeviceId() {
		return deviceId;
	}

    /**
     * Sets the device id of job.
     * 
     * @param newDeviceId the device id to set.
     */
	public void setDeviceId(String newDeviceId) {
		deviceId = newDeviceId;
	}

    /**
     * Gets the command id  of job.
     * 
     * @return Returns the command id.
     */
	public String getCommandId() {
		return commandId;
	}

    /**
     * Sets the command id of job.
     * 
     * @param newCommandId the command id to set.
     */
	public void setCommandId(String newCommandId) {
		commandId = newCommandId;
	}

    /**
     * Gets the event id  of job.
     * 
     * @return Returns the event id.
     */
	public String getEventId() {
		return eventId;
	}

    /**
     * Sets the event id of job.
     * 
     * @param newEventId the event id to set.
     */
	public void setEventId(String newEventId) {
		eventId = newEventId;
	}

    /**
     * Gets the message  of job.
     * 
     * @return Returns the message.
     */
	public String getMessage() {
		return message;
	}

    /**
     * Sets the message of job.
     * 
     * @param newMessage the message to set.
     */
	public void setMessage(String newMessage) {
		message = newMessage;
	}

    /**
     * Gets the group id  of job.
     * 
     * @return Returns the group id.
     */
	public String getGroupId() {
		return groupId;
	}

    /**
     * Sets the group id of job.
     * 
     * @param newGroupId the group id to set.
     */
	public void setGroupId(String newGroupId) {
		groupId = newGroupId;
	}

    /**
     * Gets the stream id  of job.
     * 
     * @return Returns the stream id.
     */
	public String getStreamId() {
		return streamId;
	}

    /**
     * Sets the stream id of job.
     * 
     * @param newStreamId the stream id to set.
     */
	public void setStreamId(String newStreamId) {
		streamId = newStreamId;
	}
  
	 /**
     * Gets the ruleTrigger id  of job.
     * 
     * @return Returns the ruleTrigger id.
     */
    public String getRuleTriggerId() {
		return ruleTriggerId;
	}
    
    
    /**
     * Sets the ruleTrigger id of job.
     * 
     * @param ruleTriggerId the ruleTrigger id to set.
     */
    public void setRuleTriggerId(String ruleTriggerId) {
		this.ruleTriggerId = ruleTriggerId;
	}

}
