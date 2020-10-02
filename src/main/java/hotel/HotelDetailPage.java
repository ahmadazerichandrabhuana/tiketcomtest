package hotel;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class HotelDetailPage extends BasePageSelenium {
    public HotelDetailPage(WebDriver webDriver) {
        super(webDriver);
    }

    By buttonBookNow = By.xpath("//button[@class='btn-book tix-button-2']");

    public void bookNow(){
        switchToNewTab();
        scrollToSelectedElement(webDriver.findElement(buttonBookNow));
        //will only choose the top most detail
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(buttonBookNow))).click();
    }
}
