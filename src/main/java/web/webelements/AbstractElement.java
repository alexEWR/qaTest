package web.webelements;


import config.ConfigService;
import config.EnvironmentConfig;
import managers.LocalDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class for abstract element of the page.
 * Each wrapper for WebElement should extends this class.
 */
public class AbstractElement {
    private static final Logger logger = LoggerFactory.getLogger(AbstractElement.class);
    private static ConfigService configService = ConfigService.loadDefault();
    private static EnvironmentConfig environmentConfig = configService.environmentConfig;

    public final int DEFAULT_WAIT = environmentConfig.webDriverConfig.visibilityWait;
    public final int IMPLICITLY_WAIT = environmentConfig.webDriverConfig.implicitlyWait;

    protected By locator;
    protected By targetLocator;
    protected By draggableLocator;

    public AbstractElement(By locator) {
        this.locator = locator;
    }

    public AbstractElement(By draggableLocator, By targetLocator) {
        this.targetLocator = targetLocator;
        this.draggableLocator = draggableLocator;
    }



    /**
     * This function performs searching for WebElement on the page by locator of this Element
     */

    public WebElement getWebElement() {
        try {
            //for element highlighting
            WebElement element = LocalDriverManager.getDriver().findElement(locator);
            for (int i = 0; i < 2; i++) {
                JavascriptExecutor js = (JavascriptExecutor) LocalDriverManager.getDriver();
                js.executeScript("arguments[0].style.border='3px solid red'", element);
                js.executeScript("arguments[0].style.border=''", element);
            }
            //return element;
            //return LocalDriverManager.getDriver().findElement(locator);
        } catch (Exception e) {
            logger.warn("Locator " + locator + " was not found.");
        }
        return LocalDriverManager.getDriver().findElement(locator);
    }

    public List<WebElement> getWebElements() {

        return LocalDriverManager.getDriver().findElements(locator);
    }

    public int getCount() {
        return LocalDriverManager.getDriver().findElements(locator).size();
    }


    /**
     * Imitates cursor hovering over this element. Depends on WebDriver's capabilities: ENABLE_PERSISTENT_HOVERING
     */
    public void mouseOver() {
        Actions actions = new Actions(LocalDriverManager.getDriver());
        actions.moveToElement(getWebElement()).build().perform();
    }

    /**
     * Returns value of specified attribute.
     *
     * @param attribute - exact name of the attribute you want to get.
     */
    public String getAttribute(String attribute) {
        try {
            return getWebElement().getAttribute(attribute);
        } catch (StaleElementReferenceException e) {
            return getWebElement().getAttribute(attribute);
        }
    }

    public String getCssValue(String attribute) {
        try {
            return getWebElement().getCssValue(attribute);
        } catch (StaleElementReferenceException e) {
            return getWebElement().getAttribute(attribute);
        }
    }

    /**
     * This function check immediately whether element is present on the page or not.
     * ImplicitlyWait has no impact on this function.
     */
    public boolean isPresent() {
        boolean isPresent = false;

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            isPresent = LocalDriverManager.getDriver().findElements(locator).size() != 0;
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }

        return isPresent;
    }

    /**
     * This function check immediately whether element is present on the page and is visible.
     * ImplicitlyWait has no impact on this function.
     */
    public boolean isVisible() {
        boolean isDisplayed;

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            isDisplayed = getWebElement().isDisplayed();
        } catch (NullPointerException | StaleElementReferenceException e) {
            isDisplayed = getWebElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            restoreDefaultImplicitlyWait();
        }

        return isDisplayed;
    }

    /**
     * Waits until element appears on the page.
     *
     * @param seconds - int
     */
    public void waitForElementPresent(int seconds) throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (seconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (seconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Wait until element appears on the page during default wait period
     *
     * @throws TimeoutException
     */
    public void waitForElementPresent() throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait

        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (DEFAULT_WAIT));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (DEFAULT_WAIT));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes visible on the page.
     *
     * @param seconds how long to wait int seconds
     */
    public void waitForElementVisible(int seconds) throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes visible on the page during default wait period.
     */
    public AbstractElement waitForElementVisible() throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), DEFAULT_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), DEFAULT_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
        return this;
    }

    /**
     * Waits until element becomes invisible on the page.
     *
     * @param seconds time in seconds
     */
    public void waitForElementIsNotVisible(int seconds) throws TimeoutException {

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), seconds);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes invisible on the page during default wait period
     *
     * @throws TimeoutException
     */
    public void waitForElementIsNotVisible() throws TimeoutException {

        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), DEFAULT_WAIT);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            //restoring default value
            restoreDefaultImplicitlyWait();
        }
    }

    /**
     * Waits until element becomes clickable on the page.
     * NOTICE: works only with clickable elements.
     */
    public void waitForElementClickable() throws TimeoutException {
        //to avoid redundant waiting because of implicitlyWait
        changeImplicitlyWait(0);

        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (DEFAULT_WAIT));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            try {
                WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), (DEFAULT_WAIT));
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


    /**
     * Waits until specified attribute contains specified value.
     *
     * @param attribute attribute name
     * @param value     text in attribute you are waiting for
     * @param seconds   time in seconds
     */
    public void waitForTextIsPresentInAttribute(String attribute, String value, int seconds) {
        for (int i = 0; i < (seconds * 2); i++) {
            sleep(501);
            if (getAttribute(attribute).contains(value)) {
                return;
            }
        }

        logger.error("Timeout after " + seconds + " seconds waiting for text '" + value + "' is present in attribute '" + attribute + "'");

        throw new TimeoutException();
    }

    /**
     * This function designed to change default implicitlyWait of WebDriver.
     * It could be useful when realizing of custom flow is needed.
     * IMPORTANT: Do not forget to set default value using restoreDefaultImplicitlyWait function!!!
     *
     * @param seconds time in seconds
     */
    protected void changeImplicitlyWait(int seconds) {
        LocalDriverManager.getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * Restores default value of implicitlyWait for WebDriver using Config.
     */
    protected void restoreDefaultImplicitlyWait() {
        LocalDriverManager.getDriver().manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
    }

    public Button getButton(By locator) {
        return new Button(locator);
    }

    /**
     * Try to avoid using this function!
     * Wrapper for Thread.sleep
     *
     * @param millis - time in milliseconds
     */
    public static void sleep(int millis) {

        logger.debug("Sleeping for " + millis + " milliseconds.");

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("Problem was encountered during using Thread.sleep() method.");
        }
    }

    public void dragAndDrop() {
        WebDriver driver = LocalDriverManager.getDriver();
        WebElement draggable = driver.findElement(draggableLocator);
        WebElement target = driver.findElement(targetLocator);
        new Actions(driver).dragAndDrop(draggable, target).perform();
    }

    public void waitForAnimationTime(String cssAttribute) {
        this.waitForElementPresent();
        Double res = Double.parseDouble(getButton(locator).getCssValue(cssAttribute).replace("s", ""));
        res = res * 1000;
        sleep(res.intValue());
    }
    public static void executeJavaScript(String jsCode) {
        WebDriver driver = LocalDriverManager.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsCode);
    }

    public static void executeJavaScriptClickByXpath(String xpath) {
        executeJavaScript("function getElementByXpath (path) {\n" +
                "  return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\n" +
                "}getElementByXpath(\""+ xpath + "\").click();");
    }


}
