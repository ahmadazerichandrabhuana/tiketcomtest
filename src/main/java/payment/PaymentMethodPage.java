package payment;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class PaymentMethodPage extends BasePageSelenium {
    public PaymentMethodPage(WebDriver webDriver) {
        super(webDriver);
    }

    By buttonBCAVirtualAccount = By.xpath("//span[contains(text(), 'BCA Virtual Account')]");
    By buttonByBCA = By.xpath("//span[contains(text(), 'Bank BCA')]");

    public void chooseBankBCA() {
        waitForAjaxToFinish();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(buttonByBCA))).click();
    }

    public void chooseBCAVirtualAccount() {
        waitForAjaxToFinish();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(buttonBCAVirtualAccount))).click();
    }
}
