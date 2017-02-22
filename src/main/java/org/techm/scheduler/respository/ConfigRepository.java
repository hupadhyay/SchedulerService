package org.techm.scheduler.respository;

import java.util.List;

import org.techm.scheduler.domain.Config;

public interface ConfigRepository {

	Config createConfig(Config config);

	Config updateConfig(Config config);

	Config getConfigById(String configId);

	List<Config> getAllConfigs();
	
	List<Config> getConfigsForStatus(String dimOrOnOff);

	boolean deleteConfig(String configId);

}
