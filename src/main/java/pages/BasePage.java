package pages;

import org.openqa.selenium.By;
import web.webelements.*;

public class BasePage {


    public final String url = "https://www.copart.com/";

    public static By getCustomLocator(String uname){

        return By.xpath("//select[@data-uname='"+uname+"']");
    }

    public Button getButton(By locator) {
        return new Button(locator);
    }

    public Input getInput(By locator) {
        return new Input(locator);
    }

    public Frame getFrame(By locator) {
        return new Frame(locator);
    }

    public SelectCustom getSelect(By locator) {
        return new SelectCustom(locator);
    }

    public Text getText(By locator) {
        return new Text(locator);
    }

    public CheckBox getCheckBox(By locator) {
        return new CheckBox(locator);
    }

    public Switcher getSwitcher(By locator) {
        return new Switcher(locator);
    }


    public List getList(By locator) {
        return new List(locator);
    }
}
