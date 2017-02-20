package org.techm.scheduler.service;

import java.util.List;

import org.techm.scheduler.domain.Config;

public interface ConfigService {

	Config createConfig(Config config);
	
	Config updateConfig(Config config);
	
	Config getConfigById(String configId);
	
	List<Config> getAllConfigs();
	
	boolean deleteConfig(String configId);
}
