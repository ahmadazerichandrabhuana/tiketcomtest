package flight;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class FlightCheckoutPage extends BasePageSelenium {
    public FlightCheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    By buttonTitle = By.xpath("//div[@class='flight-dropdown contact-person-dropdown']//input[@class='input-flight-dropdown']");
    By selectTitle = By.xpath("//div[@class='flight-dropdown contact-person-dropdown']//div[contains(text(), 'Tuan')]");
    By inputName = By.xpath("//div[contains(text(), 'Seperti di KTP/Paspor/SIM (tanpa tanda baca dan gelar)')]/preceding-sibling::div//input");
    By inputPhone = By.xpath("//div[contains(text(), 'Nomor Telepon')]/preceding-sibling::div//input");
    By inputEmail = By.xpath("//div[contains(text(), 'E-ticket akan dikirim ke alamat Email ini.')]/preceding-sibling::div//input");
    By buttonLever = By.xpath("//div[@class='lever']");
    By buttonContinuePurchase = By.xpath("//button[contains(text(), 'LANJUTKAN KE PEMBAYARAN')]");
    By buttonContinuePurchaseConfirm = By.xpath("//button[contains(text(), 'YA, LANJUTKAN')]");

    public void fillDetail(){
        waitForAjaxToFinish();
        //just dummy data
        wait.until(ExpectedConditions.elementToBeClickable(buttonTitle)).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectTitle)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(inputName)).clear();
//        wait.until(ExpectedConditions.elementToBeClickable(inputName)).sendKeys(" ABC");
//        wait.until(ExpectedConditions.elementToBeClickable(inputName)).clear();
//        wait.until(ExpectedConditions.elementToBeClickable(inputPhone)).sendKeys("85649815569");
//        wait.until(ExpectedConditions.elementToBeClickable(inputEmail)).sendKeys("zorozeri@gmail.com");
//        scrollToSelectedElement(webDriver.findElement(buttonLever));
//        wait.until(ExpectedConditions.elementToBeClickable(buttonLever)).click();
        scrollToSelectedElement(webDriver.findElement(buttonContinuePurchase));
        wait.until(ExpectedConditions.elementToBeClickable(buttonContinuePurchase)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonContinuePurchaseConfirm)).click();
    }
}
