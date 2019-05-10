package pages;

import managers.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleFinder extends BasePage {
    private String pageUrl = url + "vehicleFinder/";
    private static final By YEARS_FROM_SELECT_LOCATOR = getCustomLocator("vehiclefinderFromyeardownbox");
    private static final By YEARS_TO_SELECT_LOCATOR = getCustomLocator("vehiclefinderToyeardownbox");
    private static final By CAR_PRODUCER_SELECT_LOCATOR = getCustomLocator("vehiclefinderMakedownbox");

    private WebDriver driver;

    public VehicleFinder() {
        this.driver = LocalDriverManager.getDriver();
    }

    public VehicleFinder openPage() {
        driver.get(pageUrl);
        return this;
    }

    public VehicleFinder selectYears(String from, String to) {
        getSelect(YEARS_FROM_SELECT_LOCATOR).selectByVisibleText(from);
        getSelect(YEARS_TO_SELECT_LOCATOR).selectByVisibleText(to);
        return this;
    }

    public VehicleFinder selectMake(String producerName) {
        getSelect(CAR_PRODUCER_SELECT_LOCATOR).selectByVisibleText(producerName);
        return this;
    }
}
