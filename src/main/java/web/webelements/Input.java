package web.webelements;

import managers.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Input extends AbstractElement {
    public Input(By locator) {
        super(locator);
    }

    /**
     * Sends text to Input.
     *
     * @param text - String that has to be sent
     */
    public void sendKeys(String text) {
        if (text != null) {
            getWebElement().click();
            getWebElement().clear();
            getWebElement().sendKeys(text);
        }
    }

    public void sendKeys(Keys keys) {
        getWebElement().sendKeys(keys);
    }

    public void sendKeysWithoutClick(String text) {
        getWebElement().clear();
        getWebElement().sendKeys(text);
    }

    public void sendKeysWithoutClear(String text) {
        getWebElement().sendKeys(text);
    }

    /**
     * Waits until "value" parameter of Input contains specified text.
     */
    public void waitForTextIsPresentInElementValue(String text, int seconds) {
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), seconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
        } finally {
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Returns text that is inside of Input element
     */
    public String getText() {
        return getWebElement().getAttribute("value");
    }

    public String getValue() {
        WebElement element = LocalDriverManager.getDriver().findElement(locator);
        String ngModel;
        ngModel = this.getAttribute("ng-model");
        if (ngModel != null) {
            JavascriptExecutor jse = ((JavascriptExecutor) LocalDriverManager.getDriver());
            return jse.executeScript("return angular.element(arguments[0]).scope()." + ngModel + ";", element).toString();
        } else {
            throw new AssertionError("There is no attribute 'ng-model' for WebElement with locator: " + locator + ";\n WebElement: " + element);
        }
    }
}
