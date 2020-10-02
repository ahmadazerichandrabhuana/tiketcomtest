package login;

import common.BasePageSelenium;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class LoginPage extends BasePageSelenium {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    By inputUsername = By.name("username");
    By buttonContinue = By.xpath("//button[contains(text(), 'Lanjutkan')]");
    By inputPassword = By.name("password");
    By buttonLogin = By.xpath("//button[contains(text(), 'Log in')]");
    By loginFacebookButton = By.xpath("//div[@class='btn-signup waves-effect waves-light btn-socmed facebook']");

    public void clickLoginWithFacebook() {
        waitForAjaxToFinish();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(loginFacebookButton))).click();
    }

    public void loginWithEmail(String username, String password){
        waitForAjaxToFinish();
        wait.until(ExpectedConditions.elementToBeClickable(inputUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(buttonContinue)).click();
        wait.until(ExpectedConditions.elementToBeClickable(inputPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin)).click();
    }
}
