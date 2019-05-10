package web.webelements;

import org.openqa.selenium.By;


public class Switcher extends AbstractElement {

    public Switcher(By locator) {
        super(locator);
    }

    public void switchOn() {
        if (getWebElement().getAttribute("aria-checked").contains("false")) {
            getWebElement().click();
            waitForTextIsPresentInAttribute("aria-checked", "true", 1);

        }

    }

    public void switchOff() {
        if (getWebElement().getAttribute("aria-checked").contains("true")) {
            getWebElement().click();
            waitForTextIsPresentInAttribute("aria-checked", "false", 1);
        }

    }
}
