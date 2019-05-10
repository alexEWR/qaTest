package web.webelements;

import managers.LocalDriverManager;
import org.openqa.selenium.By;

public class Frame extends AbstractElement {
    public Frame(By locator){
        super(locator);
    }

    public void switchTo(){
        LocalDriverManager.getDriver().switchTo().frame(getWebElement());
    }
}
