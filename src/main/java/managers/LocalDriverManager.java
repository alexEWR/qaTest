package managers;


import config.ConfigService;
import config.EnvironmentConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.browserservice.WebDriverService;

public class LocalDriverManager {

    private static final Logger logger = LoggerFactory.getLogger(LocalDriverManager.class);

    private static ConfigService configService = ConfigService.loadDefault();
    private static EnvironmentConfig environmentConfig = configService.environmentConfig;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        if (webDriver.get() != null) {
            return webDriver.get();
        } else {
            WebDriverService webDriverFactory = new WebDriverService(environmentConfig.browserConfig);
            WebDriver driver = webDriverFactory.createDriverInstance();
            setWebDriver(driver);
            return webDriver.get();
        }
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }


    /** Closes WebDriver as well as Browser */
    public static void removeDriver() {
        logger.info("Thread id = " + Thread.currentThread().getId() +
                " Hashcode of webDriver instance = " + webDriver.get().hashCode() + " was closed");
        WebDriver driver = LocalDriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            webDriver.remove();
        }
    }
}
