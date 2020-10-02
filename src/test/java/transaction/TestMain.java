package transaction;

import common.ExtractScreenshotSelenium;
import common.YamlUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Usage : Main Test containing basic preparation and closing methods
 * @author zorozeri@gmail.com
 */
public abstract class TestMain {
    protected static WebDriver webDriver;
    protected static Properties properties;
    protected static YamlUtil yamlUtil;
    protected static ExtractScreenshotSelenium extractScreenshotSelenium;
    protected String className, chromeDriverPath, testDataPath;

    @BeforeSuite
    public void startDataPreparation() {
        properties = new Properties();
        yamlUtil = new YamlUtil();
        try {
            properties.load(new FileInputStream(System.getProperty("config")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    protected void startTestCase() {
        className = getClass().getName();
        testDataPath = properties.getProperty("testDataPath") + "/" + className;
        yamlUtil.setYamlPathUsingClassName(testDataPath);
        chromeDriverPath = properties.getProperty("chromeDriverPath");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setAcceptInsecureCerts(Boolean.TRUE);
        options.setHeadless(Boolean.FALSE);
        options.setExperimentalOption("prefs", prefs);
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get(properties.getProperty("urlWeb"));
    }

    @AfterMethod
    public void closeTestCase(
            ITestResult testResult,
            ITestContext context
    ) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            extractScreenshotSelenium.takeScreenshot(webDriver);
        }
        webDriver.close();
    }

    @AfterSuite
    public void addAllureInformation() {
        Properties allure = new Properties();
        allure.setProperty("Platform", "Web");
        allure.setProperty("Code by", "Ahmad Azeri Chandra Bhuana");
        try {
            File file = new File("./allure-results/environment.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            allure.store(fileOut, "Allure Report Environment");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}