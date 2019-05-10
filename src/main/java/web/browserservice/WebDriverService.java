package web.browserservice;

import config.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

public class WebDriverService {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverService.class);

    private String browser;

    private boolean headLessMode;
    private final String opSystem;


    public WebDriverService(BrowserConfig config){
        this.browser = config.browserType;
        this.headLessMode = config.headlessMode;
        this.opSystem = System.getProperty("os.name");
    }

    public WebDriver createDriverInstance() {
        logger.info("Creating browser driver instance");
        WebDriver driver = null;

        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized", "--ignore-certificate-errors");
                options.setCapability("acceptSslCerts", true);
                options.addArguments("window-size=1920,1080");

                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                options.merge(capability);

                if (headLessMode) {options.addArguments("headless");}
                if (opSystem.contains("Linux")) {
                    logger.info("Add chromedriver's properties for Linux ");

                    options.setBinary("/app/.apt/usr/bin/google-chrome");
                    options.addArguments("disable-gpu");
                    options.addArguments("no-sandbox");
                }

                selectDriverForSystem();
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                break;
        }
        return driver;
    }

    private void selectDriverForSystem() {
        logger.info("Your system is: " + opSystem);

        if (opSystem.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver");
        } else if (opSystem.contains("Linux")) {
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver_linux");
        } else if (opSystem.contains("Win")) {
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
        } else {
            logger.error("Your system is: " + opSystem + ", no chrome driver for it");
        }
    }

}
