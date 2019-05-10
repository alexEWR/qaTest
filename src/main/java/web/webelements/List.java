package web.webelements;

import org.openqa.selenium.By;

public class List extends AbstractElement {

    public List(By locator) {
        super(locator);
    }

    public java.util.List getElementsInList(){
        return getWebElements();
    }


}
