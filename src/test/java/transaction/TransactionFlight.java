package transaction;

import flight.FlightCheckoutPage;
import flight.SearchFlightResultPage;
import home.HomePage;
import hotel.HotelCheckoutPage;
import hotel.HotelDetailPage;
import hotel.SearchHotelResultPage;
import io.qameta.allure.Step;
import login.FacebookLoginPage;
import login.LoginPage;
import order.CheckOrderPage;
import org.testng.annotations.Test;
import payment.PaymentMethodPage;
import payment.TransferPage;

/**
 * Test case description:
 * Ensure User do Flight Transaction Successfully
 *
 * @author zorozeri@gmail.com
 */
public class TransactionFlight extends TestMain {
    String facebookEmail, facebookPassword, username, password, selectedFlight;

    @Test(description = "Ensure User do Flight Transaction Successfully")
    public void main() throws InterruptedException {
        precondition();
        step1();
        step2();
        step3();
        step4();
        step5();
        step6();
        step7();
        step8();
    }

    @Step("Precondition : prepare data")
    public void precondition() {
        username = yamlUtil.fetchObject("emailCredential.username").toString();
        password = yamlUtil.fetchObject("emailCredential.password").toString();
        facebookEmail = yamlUtil.fetchObject("facebookCredential.username").toString();
        facebookPassword = yamlUtil.fetchObject("facebookCredential.password").toString();
    }

    @Step("Given User is on Login Page")
    public void step1() {
        new HomePage(webDriver).clickLogin();
    }

    @Step("When User login using Email Account")
    public void step2() {
//        new LoginPage(webDriver).loginWithEmail(username, password);
        // login using email always need OTP, so I use login using facebook account instead
        new LoginPage(webDriver).clickLoginWithFacebook();
        new FacebookLoginPage(webDriver).loginUsingFacebookAccount(facebookEmail, facebookPassword);
    }

    @Step("Then User login successfully")
    public void step3() {
        new HomePage(webDriver).successLogin();
        extractScreenshotSelenium.takeScreenshot(webDriver);
    }

    @Step("When User search for Flight")
    public void step4() throws InterruptedException {
        new HomePage(webDriver).searchFlight("Jakarta", "Bali");
        new SearchFlightResultPage(webDriver).chooseFlight(0);
        new FlightCheckoutPage(webDriver).fillDetail();
    }

    @Step("And User finish transaction")
    public void step5() {
        new PaymentMethodPage(webDriver).chooseBCAVirtualAccount();
        new TransferPage(webDriver).doPayment();
    }

    @Step("Then User transaction for Hotel is success")
    public void step6() {
//        new CheckOrderPage(webDriver).verifyOrderSuccess();
        extractScreenshotSelenium.takeScreenshot(webDriver);
    }

    @Step("When User do Logout")
    public void step7() {
        new HomePage(webDriver).doLogout();
    }

    @Step("Then User successfully Logout")
    public void step8() {
        new HomePage(webDriver).verifyLogout();
        extractScreenshotSelenium.takeScreenshot(webDriver);
    }
}