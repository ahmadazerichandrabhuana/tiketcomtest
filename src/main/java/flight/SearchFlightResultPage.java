package flight;

import common.BasePageSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class SearchFlightResultPage extends BasePageSelenium {
    public SearchFlightResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    By chooseFlight = By.xpath("//div[contains(text(), 'PILIH')]");
    By optionNoTransit = By.xpath("//label[contains(text(), 'Langsung')]");
    By optionOneTimeTransit = By.xpath("//label[contains(text(), '1 Transit')]");
    By optionTwoMoreTransit = By.xpath("//label[contains(text(), '2+ Transit')]");
    By buttonUnderstand = By.xpath("//div[contains(text(), 'Mengerti')]");
    By buttonUnderstandOK = By.xpath("//button[contains(text(), 'Ok, mengerti')]");

    public void skipUnderstandNotification(){
        waitForAjaxToFinish();
        if(!webDriver.findElements(buttonUnderstand).isEmpty()){
            webDriver.findElement(buttonUnderstand).click();
        }
        if(!webDriver.findElements(buttonUnderstandOK).isEmpty()){
            webDriver.findElement(buttonUnderstandOK).click();
        }
        waitForAjaxToFinish();
    }

    public void chooseFlight(int transit){
        waitForAjaxToFinish();
        skipUnderstandNotification();
        if(transit == 0){
            wait.until(ExpectedConditions.elementToBeClickable(optionNoTransit));
        } else if(transit == 1){
            wait.until(ExpectedConditions.elementToBeClickable(optionOneTimeTransit));
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(optionTwoMoreTransit));
        }
        //will only choose the top most flight option
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(chooseFlight))).click();
        waitForAjaxToFinish();
        skipUnderstandNotification();
        if(transit == 0){
            wait.until(ExpectedConditions.elementToBeClickable(optionNoTransit));
        } else if(transit == 1){
            wait.until(ExpectedConditions.elementToBeClickable(optionOneTimeTransit));
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(optionTwoMoreTransit));
        }
        //will only choose the top most flight option
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(chooseFlight))).click();
    }
}
