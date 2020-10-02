package login;

import common.BasePageSelenium;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Usage : functionality on Login Page
 * @author zorozeri@gmail.com
 */
public class FacebookLoginPage extends BasePageSelenium {
    public FacebookLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    By fieldEmailPhone = By.id("email");
    By fieldPassword = By.id("pass");
    By buttonLoginFacebook = By.id("u_0_0");

    @Step("Do Login using Facebook Account")
    public void loginUsingFacebookAccount(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(fieldEmailPhone)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(fieldPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(buttonLoginFacebook)).click();
    }
}
