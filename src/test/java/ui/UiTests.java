package ui;

import managers.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.VehicleFinder;

public class UiTests {

    private static final Logger logger = LoggerFactory.getLogger(UiTests.class);
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
    }

    @BeforeMethod
    public void beforeTest() {
        driver = LocalDriverManager.getDriver();
    }

    @AfterMethod
    public void afterTest() {
        LocalDriverManager.removeDriver();
    }

    @Test
    public void openCopart() {
        logger.info("Starting test ");
        VehicleFinder finder = new VehicleFinder();
        finder.openPage()
                .selectMake("Acura")
                .selectYears("2015", "2019");

    }
}
