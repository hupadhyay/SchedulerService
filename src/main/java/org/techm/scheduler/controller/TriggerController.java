package org.techm.scheduler.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.service.TriggerService;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class is manage rest end-points for read/write operations of
 * <object>Trigger</object> objects.
 * 
 * @author Himanshu
 *
 */
@Path("trigger")
public class TriggerController {
	
	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(TriggerController.class);
	
	/** Instance of Trigger Service */
	private TriggerService triggerService;

	/**
	 * Constructor Injection of <class>TriggerServiceImpl</class> to service
	 * instance of <class>TriggerService</class>.
	 * 
	 * @param triggerService
	 */
	@Inject
	public TriggerController(TriggerService triggerService) {
		this.triggerService = triggerService;
	}

	/**
	 * POST Operation to create resource of type <class>Trigger</class>.
	 * 
	 * @param trigger
	 * @return newly created trigger object.
	 */
	@POST
	@Consumes(SchedulerConstants.TRIGGER_MIME)
	public Trigger createTrigger(Trigger trigger) {
		logger.info("Creating of trigger object. Object :" + trigger);
		return triggerService.createTrigger(trigger);
	}

	/**
	 * PUT Operation to update resource of type <class>Trigger</class>.
	 * 
	 * @param trigger
	 * @return newly updated trigger object.
	 */
	@PUT
	@Consumes(SchedulerConstants.TRIGGER_MIME)
	public Trigger updateTrigger(Trigger trigger) {
		logger.info("Updating of trigger object. Object :" + trigger);
		return triggerService.updateTrigger(trigger);
	}

	/**
	 * Get All operation to get all the list of <object>Trigger</class>
	 * 
	 * @return list of trigger objects
	 */
	@GET
	@Produces(SchedulerConstants.TRIGGER_MIME)
	public List<Trigger> getAllTriggers() {
		logger.info("Retrieving all trigger objects.");
		List<Trigger> listOfTriggers = triggerService.getAllTriggers();
		return listOfTriggers;
	}

	/**
	 * Get the Trigger object based on given id.
	 * 
	 * @param triggerId
	 * @return Trigger object.
	 */
	@GET
	@Path("{triggerId}")
	@Produces(SchedulerConstants.TRIGGER_MIME)
	public Trigger getTriggerById(@NotNull @PathParam("triggerId") String triggerId) {
		logger.info("Retrieving of trigger object based on id: " + triggerId);
		Trigger trigger = triggerService.getTriggerById(triggerId);
		return trigger;
	}

	/**
	 * Delete the trigger object based on its Id.
	 * 
	 * @param triggerId
	 * @return
	 */
	@DELETE
	@Path("{triggerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean deleteTrigger(@NotNull @PathParam("triggerId") String triggerId) {
		logger.info("Deleting of trigger object based on id: " + triggerId);
		return triggerService.deleteTrigger(triggerId);
	}

}
