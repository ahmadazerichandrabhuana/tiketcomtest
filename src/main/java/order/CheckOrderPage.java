package order;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class CheckOrderPage extends BasePageSelenium {
    public CheckOrderPage(WebDriver webDriver) {
        super(webDriver);
    }

    By cardOrder = By.xpath("//div[@class='card-body card-order-content']");
    By buttonOK = By.xpath("//button[contains(text(), 'OK')]");

    public void verifyOrderSuccess() {
        waitForAjaxToFinish();
        if(!webDriver.findElements(buttonOK).isEmpty()){
            webDriver.findElement(buttonOK).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(cardOrder));
        Assert.assertTrue(!webDriver.findElements(cardOrder).isEmpty());
    }
}
