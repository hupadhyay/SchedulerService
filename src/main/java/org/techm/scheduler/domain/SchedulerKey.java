package org.techm.scheduler.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/** Class encapsulating the SchedulerKey properties of a job and trigger. */
@Embeddable
public class SchedulerKey implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;
    
    /** The name associated to this job and trigger. */
    @Column(name="KEY_NAME")
    private String name;
    
    /** The group associated to this job and trigger. */
    @Column(name="KEY_GROUP")
    private String group;
    
    /**
     * Gets the name  of a job and trigger.
     * 
     * @return Returns the name.
     */
	public String getName() {
		return name;
	}

    /**
     * Sets the name of a job and trigger.
     * 
     * @param newName the name to set.
     * @return this instance.
     */
  	public SchedulerKey setName(String newName) {
    	name = newName;
		return this;
	}

    /**
     * Gets the group  of a job and trigger.
     * 
     * @return Returns the group.
     */
	public String getGroup() {
		return group;
	}

    /**
     * Sets the group of a job and trigger.
     * 
     * @param newGroup the group to set.
     * @return this instance.
     */
	public SchedulerKey setGroup(String newGroup) {
    	group = newGroup;
		return this;
	}

}
