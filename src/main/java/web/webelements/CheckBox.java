package web.webelements;

import org.openqa.selenium.By;

public class CheckBox extends AbstractElement {
    public CheckBox(By locator) {
        super(locator);
    }

    public void check(){
        if (!getWebElement().isSelected()){
            getWebElement().click();
        }
    }

    public void uncheck(){
        if (getWebElement().isSelected()){
            getWebElement().click();
        }
    }

    public boolean isChecked(){
        return getWebElement().isSelected();
    }
}
