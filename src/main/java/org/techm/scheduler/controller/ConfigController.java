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

import org.techm.scheduler.domain.Config;
import org.techm.scheduler.service.ConfigService;
import org.techm.scheduler.utils.SchedulerConstants;

@Path("config")
public class ConfigController {

	private ConfigService configService;

	@Inject
	public ConfigController(ConfigService configService) {
		this.configService = configService;
	}

	@POST
	@Consumes(SchedulerConstants.CONFIG_MIME)
	public Config createConfig(Config config) {
		return configService.createConfig(config);
	}

	@PUT
	@Consumes(SchedulerConstants.CONFIG_MIME)
	public Config updateConfig(Config config) {
		return configService.updateConfig(config);
	}

	@GET
	@Produces(SchedulerConstants.CONFIG_MIME)
	public List<Config> getAllConfigurations() {
		List<Config> listOfConfigs = configService.getAllConfigs();
		return listOfConfigs;
	}

	@GET
	@Path("{configId}")
	@Produces(SchedulerConstants.CONFIG_MIME)
	public Config getConfigById(@NotNull @PathParam("configId") String configId) {
		Config config = configService.getConfigById(configId);
		return config;
	}
	
	@GET
	@Path("list/{dimOrOnOff}")
	@Produces(SchedulerConstants.CONFIG_MIME)
	public List<Config> getConfigsForStatus(@NotNull @PathParam("dimOrOnOff") String dimOrOnOff) {
		List<Config> listConfig = configService.getConfigsForStatus(dimOrOnOff);
		return listConfig;
	}

	@DELETE
	@Path("{configId}")
	public boolean deleteConfig(@NotNull @PathParam("configId") String configId) {
		return configService.deleteConfig(configId);
	}
}
