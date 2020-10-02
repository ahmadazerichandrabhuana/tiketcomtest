package hotel;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class SearchHotelResultPage extends BasePageSelenium {
    public SearchHotelResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    By hotelCard = By.xpath("//div[@class='hotel-card ']");

    public void chooseHotel(){
        waitForAjaxToFinish();
        //will only choose the top most hotel card
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(hotelCard))).click();
    }
}
