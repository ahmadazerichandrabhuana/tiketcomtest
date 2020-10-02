package home;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
/**
 * Usage : functionality on Home Page
 * @author zorozeri@gmail.com
 */
public class HomePage extends BasePageSelenium {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    By imgHomeLogo = By.xpath("//img[@alt='logo tiket.com']");
    By buttonLogin = By.xpath("//a[contains(text(), 'Login')]");
    By buttonNextTime = By.xpath("//i[@class='tix tix-cancel']");
    By buttonCancelChangePassword = By.xpath("//i[@class='tix tix-cancel right-content']");

    // Flight section
    By buttonFlight = By.xpath("//img[@alt='Pesawat']");
    By selectedFrom = By.id("fromDropDownList-airport1");
    By selectedTo = By.id("toDropDownList-airport1");
    By inputFlightFrom = By.id("productSearchFrom");
    By inputFlightTo = By.id("productSearchTo");
    By buttonSearchDeparture = By.id("productSearchDeparture");
    By buttonFinishFlight = By.xpath("//span[contains(text(), 'SELESAI')]");
    By buttonSearchFlight = By.xpath("//button[contains(text(), 'CARI PENERBANGAN')]");

    // Hotel section
    By buttonHotel = By.xpath("//img[@alt='Hotel']");
    By inputSearchDestinationHotel = By.id("productSearchDestination");
    By buttonCheckIn = By.id("productSearchCheckIn");
    By buttonInputGuestRoom = By.id("productSearchGuestRoom");
    By buttonAddGuest = By.xpath("//div[@class='widget-passenger-cabin-type' and contains(text(), 'Tamu')]/parent::div/following-sibling::div/div[@class='widget-passenger-logo-container widget-passenger-counter-item']/i[@class='tix tix-plus']");
    By buttonAddRoom = By.xpath("//div[@class='widget-passenger-cabin-type' and contains(text(), 'Kamar')]/parent::div/following-sibling::div/div[@class='widget-passenger-logo-container widget-passenger-counter-item']/i[@class='tix tix-plus']");
    By buttonFinishGuestRoom = By.xpath("//div[@class='passenger-cabin-drop-down-text']/span");
    By buttonSearchHotel = By.xpath("//button[@class='product-form-search-btn']");

    // Logout section
    By imgAccount = By.cssSelector("span.account-label");
    By buttonLogout = By.xpath("//span[contains(text(), 'Keluar')]");
    By buttonLogoutYes = By.xpath("//div[contains(text(), 'YA')]");

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin)).click();
    }

    public void successLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(imgAccount));
        Assert.assertTrue(!webDriver.findElements(imgAccount).isEmpty());
    }

    public void skipNextTimeNotification(){
        waitForAjaxToFinish();
        if(!webDriver.findElements(buttonCancelChangePassword).isEmpty()){
            webDriver.findElement(buttonCancelChangePassword).click();
        }
        if(!webDriver.findElements(buttonNextTime).isEmpty()){
            webDriver.findElement(buttonNextTime).click();
            wait.until(ExpectedConditions.elementToBeClickable(imgHomeLogo)).click();
        }
        waitForAjaxToFinish();
    }

    public void searchFlight(String flightFrom, String flightTo) throws InterruptedException {
        skipNextTimeNotification();
        wait.until(ExpectedConditions.elementToBeClickable(buttonFlight)).click();
        waitForAjaxToFinish();
        wait.until(ExpectedConditions.elementToBeClickable(buttonSearchFlight));
        scrollToSelectedElement(webDriver.findElement(buttonSearchFlight));
        wait.until(ExpectedConditions.elementToBeClickable(inputFlightFrom)).click();
        wait.until(ExpectedConditions.elementToBeClickable(inputFlightFrom)).sendKeys(flightFrom);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectedFrom));
        Actions act = new Actions(webDriver);
        act.moveToElement(webDriver.findElement(selectedFrom)).click().perform();
        wait.until(ExpectedConditions.elementToBeClickable(inputFlightTo)).click();
        wait.until(ExpectedConditions.elementToBeClickable(inputFlightTo)).sendKeys(flightTo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectedTo));
        act.moveToElement(webDriver.findElement(selectedTo)).click().perform();
        scrollToSelectedElement(webDriver.findElement(buttonSearchDeparture));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSearchDeparture)).click();
        // I can't find a proper method to choose date dynamically, so here I use static date. I'm so sorry.
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@aria-label='Choose Rabu, 4 November 2020 as your check-in date. It’s available.']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@aria-label='Choose Sabtu, 7 November 2020 as your check-out date. It’s available.']"))).click();
        scrollToSelectedElement(webDriver.findElement(buttonFinishFlight));
        wait.until(ExpectedConditions.elementToBeClickable(buttonFinishFlight)).click();
        scrollToSelectedElement(webDriver.findElement(buttonSearchFlight));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSearchFlight)).click();
    }

    public void searchHotel(String destination, int guest, int room){
        skipNextTimeNotification();
        wait.until(ExpectedConditions.elementToBeClickable(buttonHotel)).click();
        scrollToSelectedElement(webDriver.findElement(inputSearchDestinationHotel));
        wait.until(ExpectedConditions.elementToBeClickable(inputSearchDestinationHotel)).sendKeys(destination + Keys.ENTER);
        scrollToSelectedElement(webDriver.findElement(buttonInputGuestRoom));
        wait.until(ExpectedConditions.elementToBeClickable(buttonCheckIn)).click();
        waitForAjaxToFinish();
        // I can't find a proper method to choose date dynamically, so here I use static date. I'm so sorry.
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@aria-label='Choose Rabu, 4 November 2020 as your check-in date. It’s available.']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonInputGuestRoom)).click();
        scrollToSelectedElement(webDriver.findElement(buttonAddGuest));
        for(int i = 1; i < guest; i++){
            wait.until(ExpectedConditions.elementToBeClickable(buttonAddGuest)).click();
        }
        for(int i = 1; i < room; i++){
            wait.until(ExpectedConditions.elementToBeClickable(buttonAddRoom)).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(buttonFinishGuestRoom)).click();
        scrollToSelectedElement(webDriver.findElement(buttonSearchHotel));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSearchHotel)).click();
    }

    public void doLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(imgAccount)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogout)).click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogoutYes)).click();
    }

    public void verifyLogout(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
        Assert.assertTrue(!webDriver.findElements(buttonLogin).isEmpty());
    }
}
