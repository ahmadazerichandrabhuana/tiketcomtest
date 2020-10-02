package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
/**
 * Usage : basic functionality across all pages
 * @author zorozeri@gmail.com
 */
public abstract class BasePageSelenium {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public BasePageSelenium(WebDriver webDriver) {
        this.webDriver = webDriver;
		wait = new WebDriverWait(webDriver, 30);
    }

    public void waitForAjaxToFinish() {
        WebDriverWait wait = new WebDriverWait(webDriver, 120);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wDriver) {
                return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString().equals("complete");
            }
        });
    }

    public void scrollToSelectedElement(WebElement myElement){
        JavascriptExecutor jse2 = (JavascriptExecutor)webDriver;
        jse2.executeScript("arguments[0].scrollIntoView()", myElement);
    }

    public void switchToNewTab(){
        ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(0));
        webDriver.close();
        webDriver.switchTo().window(tabs2.get(1));
        waitForAjaxToFinish();
    }
}