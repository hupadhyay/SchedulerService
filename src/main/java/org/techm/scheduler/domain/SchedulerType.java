package org.techm.scheduler.domain;

import javax.persistence.Embeddable;

/**
 * Enum for scheduler type of trigger.
 *
 */
public enum SchedulerType {
	
	  /** Simple scheduler of trigger. **/
    SIMPLESCHEDULER("simpleScheduler"),
    
	  /** Cron scheduler of trigger. **/
    CRONSCHEDULER("cronScheduler");

    /** scheduler type. **/
    private String schedulerType;

    /**
     * Enum constructor.
     * 
     * @param newSchedulerType scheduler type.
     */
    SchedulerType(String newSchedulerType) {
    	schedulerType = newSchedulerType;
    }

    /**
     * Return string value for scheduler type enum.
     * 
     * @return the schedulerType string
     */
    public String value() {
        return schedulerType;
    }

    /**
     * Returns enum value based on scheduler type string.
     * 
     * @param schedulerType the scheduler type string
     * @return SchedulerType enum
     */
    public static SchedulerType getSchedulerTypeEnum(String schedulerType) {
    	SchedulerType schedulerTypeEnum = null;
        switch (schedulerType) {
            case "simpleScheduler":
            	schedulerTypeEnum = SIMPLESCHEDULER;
                break;
            case "cronScheduler":
            	schedulerTypeEnum = CRONSCHEDULER;
                break;
            default:
                break;
        }

        return schedulerTypeEnum;

    }

    /** {@inheritDoc} */
    public String toString() {
        return schedulerType;
    }


}
