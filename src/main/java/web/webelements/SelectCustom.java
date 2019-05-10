package web.webelements;

import managers.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectCustom extends AbstractElement {

    private static final Logger logger = LoggerFactory.getLogger(SelectCustom.class);

    public SelectCustom(By locator) {
        super(locator);
    }

    public void selectByVisibleText(String label) {

    /*    for (int i =0; i<3 && !getButton(By.xpath("//a/span[contains(.,'"+label+"')]")).isPresent(); i++) {
            getButton(locator).waitForVisibleAndClick(3);

            try {
                getButton(By.xpath("//a/span[contains(.,'"+label+"')]")).waitForVisibleAndClick(2);
                return;

            } catch (TimeoutException e) {
                getButton(locator).waitForVisibleAndClick(3);
                Logger.INFO(e.getMessage());
            }

        }*/
        getButton(locator).waitForElementVisible();
        new Select(getWebElement()).selectByVisibleText(label);

//        getButton(locator).waitForVisibleAndClick();
//        try {
//            getButton(By.xpath("//select/option[contains(.,'" + label + "')]")).waitForClickableAndClick();
//        }
//        catch (WebDriverException exception){
//            logger.info("Trying to scroll to element with name '"+label+"'");
////            WebElement element = LocalDriverManager.getDriver().findElement(By.xpath("//a/span[contains(.,'" + label + "')]"));
////            ((JavascriptExecutor) LocalDriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
////            getButton(By.xpath("//a/span[contains(.,'" + label + "')]")).waitForClickableAndClick();
//
//        }
    }
    public void selectGroupByText(String param) {
        getButton(locator).waitForVisibleAndClick();
        getButton(By.xpath("//div[@class='scrollable-content']")).waitForElementVisible();
        try {
            getButton(By.xpath("//div/span[contains(.,'" + param + "')]")).waitForClickableAndClick();
        }
        catch (WebDriverException exception){
            logger.info("Trying to scroll to element with name '"+param+"'");
            WebElement element = LocalDriverManager.getDriver().findElement(By.xpath("//div/span[contains(.,'" + param + "')]"));
            ((JavascriptExecutor) LocalDriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            getButton(By.xpath("//div/span[contains(.,'" + param + "')]")).waitForClickableAndClick();
        }
    }

}
