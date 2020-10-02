package payment;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class TransferPage extends BasePageSelenium {
    public TransferPage(WebDriver webDriver) {
        super(webDriver);
    }

    By labelCode = By.xpath("//p[contains(text(), 'Kode Unik')]");
    By labelHotelName = By.xpath("//p[@class='product-name']");
    By buttonPay = By.xpath("//button[contains(text(), 'Bayar')]");
    By buttonOK = By.xpath("//button[contains(text(), 'OK')]");
    By buttonAlreadyPaid = By.xpath("//button[contains(text(), 'Saya Sudah Membayar')]");
    By buttonToMyOrder = By.xpath("//button[contains(text(), 'Ke My Order')]");

    public String getHotelName(){
        waitForAjaxToFinish();
        wait.until(ExpectedConditions.elementToBeClickable(labelCode)).click();
        return webDriver.findElement(labelHotelName).getText();
    }

    public void doPayment() {
        scrollToSelectedElement(webDriver.findElement(buttonPay));
        wait.until(ExpectedConditions.elementToBeClickable(buttonPay)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonOK)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonAlreadyPaid)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonToMyOrder)).click();
    }
}
