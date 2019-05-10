package config;

import com.typesafe.config.Config;

public class WebDriverConfig {

    public final int visibilityWait;
    public final int implicitlyWait;

    public WebDriverConfig (Config webDriverConfig) {
        this.visibilityWait = webDriverConfig.getInt("visibilityWait");
        this.implicitlyWait = webDriverConfig.getInt("implicitlyWait");
    }
}
