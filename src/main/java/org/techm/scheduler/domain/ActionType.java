package org.techm.scheduler.domain;

/**
 * Enum for action type of job.
 *
 */
public enum ActionType {
	
    /**Command action type. **/
    COMMAND("Command"),
    
    /** Event action type. **/
    EVENT("Event"),
    
    /**Rule trigger action type. **/
    RULE_TRIGGER("RuleTrigger");
    
    /** action type. **/
    private String actionType;

    /**
     * Enum constructor.
     * 
     * @param newActionType action type.
     */
    ActionType(String newActionType) {
    	actionType = newActionType;
    }

    /**
     * Return string value for action type enum.
     * 
     * @return the action string
     */
    public String value() {
        return actionType;
    }
    
    /**
     * ActionType for job.
     * 
     * @param value - Enum value
     * @return ActionType if exists.
     */
    public static ActionType safeValueOf(String value) {
        
    	ActionType actionType = null;
        switch (value) {
            case "Command":
            	actionType = COMMAND;
                break;
            case "Event":
            	actionType = EVENT;
                break;
            case "RuleTrigger":
            	actionType = RULE_TRIGGER;
                break;
            default:
                break;
        }
        
        return actionType;
    }
    
    /** {@inheritDoc} */
    public String toString() {
        return actionType;
    }


}
