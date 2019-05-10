package config;

import com.typesafe.config.Config;

import java.util.StringJoiner;

public class EnvironmentConfig {

    public final MySqlConfig mySqlConfig;
    public final BrowserConfig browserConfig;
    public final WebDriverConfig webDriverConfig;


    public EnvironmentConfig(Config envConfig) {
        this.mySqlConfig = new MySqlConfig(envConfig.getConfig("mysql"));
        this.browserConfig = new BrowserConfig(envConfig.getConfig("browser"));
        this.webDriverConfig = new WebDriverConfig(envConfig.getConfig("webdriver"));
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", EnvironmentConfig.class.getSimpleName() + "[", "]")
                .add("mySqlConfig=" + mySqlConfig)
                .add("browserConfig=" + browserConfig)
                .add("webDriverConfig=" + webDriverConfig)
                .toString();
    }
}
