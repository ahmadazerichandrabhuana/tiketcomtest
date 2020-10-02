package common;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Usage : take screenshot
 * @author zorozeri@gmail.com
 */
public class ExtractScreenshotSelenium {
	protected WebDriver webDriver;

	public ExtractScreenshotSelenium(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@Step("Take Screenshot")
	public static void takeScreenshot(WebDriver webDriver) {
		try {
			//Since web apps run so fast, need sleep to catch screenshot properly
			Thread.sleep(1000);
			File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			Allure.addAttachment("Screenshot", new FileInputStream(srcFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
