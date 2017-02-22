package org.techm.scheduler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.techm.scheduler.domain.Config;
import org.techm.scheduler.respository.ConfigRepository;
import org.techm.scheduler.service.ConfigService;

public class ConfigServiceImpl implements ConfigService {

	private ConfigRepository configRepository;
	
	@Inject
	public ConfigServiceImpl(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}
	
	@Override
	public Config createConfig(Config config) {
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
