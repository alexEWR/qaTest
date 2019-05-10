package config;

import com.typesafe.config.Config;
import java.util.StringJoiner;

public class BrowserConfig {

    public final String browserType;
    public final boolean headlessMode;

    public BrowserConfig(Config browserConfig) {
        this.browserType = browserConfig.getString("type");
        this.headlessMode = browserConfig.getBoolean("headlessMode");
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", BrowserConfig.class.getSimpleName() + "[", "]")
                .add("browserType='" + browserType + "'")
                .add("headlessMode='" + headlessMode + "'")
                .toString();
    }
}
