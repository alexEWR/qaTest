package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigService {

    private final Config config;
    public final EnvironmentConfig environmentConfig;

    public static ConfigService loadDefault() {
        Config defaultConfig = ConfigFactory.load();
        return new ConfigService(defaultConfig);
    }

    public ConfigService(Config config) {
        this.config = config;
        this.environmentConfig = new EnvironmentConfig(this.config.getConfig("environment"));
    }
}
