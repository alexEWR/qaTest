package config;

import com.typesafe.config.Config;

public class MySqlConfig {

    public final String mySqlUrl;
    public final String mySqlUser;
    public final String mySqlPassword;

    public MySqlConfig(Config mysqlConfig) {
        if (mysqlConfig.hasPath("url")) {
            this.mySqlUrl = mysqlConfig.getString("url");
        } else {
            this.mySqlUrl = "jdbc:mysql://" +
                    mysqlConfig.getString("host") + ":" +
                    mysqlConfig.getInt("port") + "/" +
                    mysqlConfig.getString("database");
        }

        this.mySqlUser = mysqlConfig.getString("username");
        this.mySqlPassword = mysqlConfig.getString("password");
    }

    @Override
    public String toString() {
        return "MySqlConfig{" +
                "mySqlUrl='" + mySqlUrl + '\'' +
                ", mySqlUser='" + mySqlUser + '\'' +
                ", mySqlPassword='" + mySqlPassword + '\'' +
                '}';
    }
}
