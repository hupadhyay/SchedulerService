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

import org.techm.scheduler.domain.Config;
import org.techm.scheduler.service.ConfigService;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class is manage rest end-points for read/write operations of
 * <object>Config</object> objects.
 * 
 * @author Himanshu
 *
 */
@Path("config")
public class ConfigController {

	/** Instance of Configuration Service */
	private ConfigService configService;

	/**
	 * Constructor Injection of <class>ConfigServiceImpl</class> to service
	 * instance of <class>ConfigService</class>.
	 * 
	 * @param configService
	 */
	@Inject
	public ConfigController(ConfigService configService) {
		this.configService = configService;
	}

	/**
	 * POST Operation to create resource of type <class>Config</class>.
	 * 
	 * @param config
	 * @return newly created config object.
	 */
	@POST
	@Consumes(SchedulerConstants.CONFIG_MIME)
	public Config createConfig(Config config) {
		return configService.createConfig(config);
	}

	/**
	 * PUT Operation to update resource of type <class>Config</class>.
	 * 
	 * @param config
	 * @return newly updated config object.
	 */
	@PUT
	@Consumes(SchedulerConstants.CONFIG_MIME)
	public Config updateConfig(Config config) {
		return configService.updateConfig(config);
	}

	/**
	 * Get All operation to get all the list of <object>Config</class>
	 * 
	 * @return list of config objects
	 */
	@GET
	@Produces(SchedulerConstants.CONFIG_MIME)
	public List<Config> getAllConfigurations() {
		List<Config> listOfConfigs = configService.getAllConfigs();
		return listOfConfigs;
	}

	/**
	 * Get the Config object based on given id.
	 * 
	 * @param configId
	 * @return Config object.
	 */
	@GET
	@Path("{configId}")
	@Produces(SchedulerConstants.CONFIG_MIME)
	public Config getConfigById(@NotNull @PathParam("configId") String configId) {
		Config config = configService.getConfigById(configId);
		return config;
	}

	/**
	 * Get all the config ojbects which is configured for dim/onOff.
	 * 
	 * @param dimOrOnOff
	 * @return list of config objects.
	 */
	@GET
	@Path("list/{dimOrOnOff}")
	@Produces(SchedulerConstants.CONFIG_MIME)
	public List<Config> getConfigsForStatus(@NotNull @PathParam("dimOrOnOff") String dimOrOnOff) {
		List<Config> listConfig = configService.getConfigsForStatus(dimOrOnOff);
		return listConfig;
	}

	/**
	 * Delete the config object based on its Id.
	 * 
	 * @param configId
	 * @return
	 */
	@DELETE
	@Path("{configId}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean deleteConfig(@NotNull @PathParam("configId") String configId) {
		return configService.deleteConfig(configId);
	}
}
