package transaction;

import home.HomePage;
import hotel.HotelCheckoutPage;
import hotel.HotelDetailPage;
import hotel.SearchHotelResultPage;
import io.qameta.allure.Step;
import login.FacebookLoginPage;
import login.LoginPage;
import order.CheckOrderPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import payment.PaymentMethodPage;
import payment.TransferPage;
/**
 * Test case description:
 * Ensure User do Hotel Transaction Successfully
 *
 * @author zorozeri@gmail.com
 */
public class TransactionHotel extends TestMain {
    String facebookEmail, facebookPassword, selectedHotel;

    @Test(description = "Ensure User do Hotel Transaction Successfully")
    public void main() {
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
        facebookEmail = yamlUtil.fetchObject("facebookCredential.username").toString();
        facebookPassword = yamlUtil.fetchObject("facebookCredential.password").toString();
    }

    @Step("Given User is on Login Page")
    public void step1() {
        new HomePage(webDriver).clickLogin();
    }

    @Step("When User login using Facebook Account")
    public void step2() {
        new LoginPage(webDriver).clickLoginWithFacebook();
        new FacebookLoginPage(webDriver).loginUsingFacebookAccount(facebookEmail, facebookPassword);
    }

    @Step("Then User login successfully")
    public void step3() {
        new HomePage(webDriver).successLogin();
        extractScreenshotSelenium.takeScreenshot(webDriver);
    }

    @Step("When User search for Hotel")
    public void step4() {
        new HomePage(webDriver).searchHotel("Jakarta", 2, 2);
        new SearchHotelResultPage(webDriver).chooseHotel();
        new HotelDetailPage(webDriver).bookNow();
        new HotelCheckoutPage(webDriver).fillDetail();
    }

    @Step("And User finish transaction")
    public void step5() {
        new PaymentMethodPage(webDriver).chooseBankBCA();
        selectedHotel = new TransferPage(webDriver).getHotelName();
        new TransferPage(webDriver).doPayment();
    }

    @Step("Then User transaction for Hotel is success")
    public void step6() {
        new CheckOrderPage(webDriver).verifyOrderSuccess();
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