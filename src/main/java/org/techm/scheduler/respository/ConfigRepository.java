package org.techm.scheduler.respository;

import java.util.List;

import org.techm.scheduler.domain.Config;

/**
 * Declaring service methods for <class>Config</class> Repository implementations.
 * 
 * @author Himanshu
 *
 */
public interface ConfigRepository {

	Config createConfig(Config config);

	Config updateConfig(Config config);

	Config getConfigById(String configId);

	List<Config> getAllConfigs();

	List<Config> getConfigsForStatus(String dimOrOnOff);

	boolean deleteConfig(String configId);

}
