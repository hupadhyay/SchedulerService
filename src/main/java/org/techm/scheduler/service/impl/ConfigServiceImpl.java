package org.techm.scheduler.service.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.techm.scheduler.domain.Config;
import org.techm.scheduler.respository.ConfigRepository;
import org.techm.scheduler.service.ConfigService;

/**
 * Implementation of service interface <class>ConfigService</class>. Mainly
 * delegating call to repository layer.
 * 
 * @author Himanshu
 *
 */
public class ConfigServiceImpl implements ConfigService {

	/** Holds the instance of config repository service. */
	private ConfigRepository configRepository;
	
	@Inject
	public ConfigServiceImpl(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}
	
	@Override
	public Config createConfig(Config config) {
		if(config.isDim()){
			config.setId(UUID.randomUUID().toString());
		} else {
			config.setId(config.getControllerId()+"onoff");
		}
		return configRepository.createConfig(config);
	}

	@Override
	public Config updateConfig(Config config) {
		return configRepository.updateConfig(config);
	}

	@Override
	public Config getConfigById(String configId) {
		return configRepository.getConfigById(configId);
	}

	@Override
	public List<Config> getAllConfigs() {
		return configRepository.getAllConfigs();
	}

	@Override
	public boolean deleteConfig(String configId) {
		return configRepository.deleteConfig(configId);
	}

	@Override
	public List<Config> getConfigsForStatus(String dimOrOnOff) {
		return configRepository.getConfigsForStatus(dimOrOnOff);
	}

}
