package web.webelements;

import managers.LocalDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Button extends AbstractElement {

    private static final Logger logger = LoggerFactory.getLogger(Button.class);

    public Button(By locator) {
        super(locator);
    }


    /**
     * Returns text of this element.
     */
    public String getText() {
        return getWebElement().getText();
    }

    /**
     * Imitate cursor hovering over this element, then clicks on it.
     */
    public void click() {
        getWebElement().click();
 /*       *//**try to focus with sendKeys*//*
        try {
            Actions actions = new Actions(LocalDriverManager.getDriver());
            actions.moveToElement(getWebElement()).sendKeys("").click().build().perform();
        }catch (IllegalArgumentException | StaleElementReferenceException e){
//            try {
                Actions actions = new Actions(WebDriverManager.getDriver());
                actions.moveToElement(getWebElement()).sendKeys("").click().build().perform();
//            }catch (Exception l){
//                Logger.EXCEPTION(l.getMessage());
//                throw l;
//            }
        }*/
    }

    /**
     * Waits for element visible, then clicks it.
     */
    public void waitForVisibleAndClick(int seconds) {
        waitForElementVisible(seconds);
        click();
    }

    /**
     * Waits for element visible during 5 sec, then clicks it.
     */
    public void waitForVisibleAndClick() {
        waitForElementVisible(DEFAULT_WAIT);
        click();
    }



    /**
     * Waits for element clickable, then clicks it.
     */
    public void waitForClickableAndClick(int seconds) {
        waitForElementClickable(seconds);
        click();
    }

    public void waitForClickableAndClick() {
        waitForElementClickable(DEFAULT_WAIT);
        click();
    }

    /**
     * Imitate cursor hovering over this element, then performs double clicking on it.
     */
    public void doubleClick() {

        Actions actions = new Actions(LocalDriverManager.getDriver());
        actions
                .moveToElement(getWebElement())
                .doubleClick()
                .build()
                .perform();
    }

    /**
     * Waits until element becomes clickable on the page.
     * NOTICE: works only with clickable elements.
     *
     * @param seconds - int
     */
    public void waitForElementClickable(int seconds) throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (seconds));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            try {
                WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (seconds));
                wait.until(ExpectedConditions.elementToBeClickable(locator));
            } catch (WebDriverException e2) {
                logger.error(e2.getMessage());
                throw e2;
            } finally {
                //restoring default value
                restoreDefaultImplicitlyWait();
            }
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    public boolean isClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), 2);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Button{" +
                "locator=" + locator +
                '}';
    }
}

