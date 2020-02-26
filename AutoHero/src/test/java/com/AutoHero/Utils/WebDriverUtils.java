package com.AutoHero.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class WebDriverUtils {

	WebDriver driver;
	static WebElement locator = null;
	private static int longTimeout = 30;
	public ExtentTest logger;

	public WebDriverUtils(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	
	/**
	 * Click webelements
	 * @param by
	 * @throws IOException
	 */

	public void click(By by) throws IOException {
		WebElement locator = null;
		try {
			locator = waitForElementToBeClickable(by);
			if (null != locator) {
				logger.info("Clicking the element: " + by);
				locator.click();
			}
		} catch (Exception e) {
			logger.fail("Exception in the click method: ",MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
	}

	/**
	 * wait for elements to be visible
	 * @param by
	 * @throws IOException
	 */

	public WebElement waitForElementToBeVisible(By locator) throws IOException {
		WebElement element = null;
		try {
			if (locator != null) {
				WebDriverWait wait = new WebDriverWait(driver, longTimeout);
				element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (element != null) {
					logger.info("Element is present: " + locator);
				} else {
					logger.info("Element does not present: " + locator);
				}
			}
		} catch (Exception e) {
			logger.fail("Exception in waitForElementToBeVisible method:",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return element;
	}

	/**
	 * Wait for page load
	 * @return
	 */
	public boolean waitForPageLoad() {
		int leadsZero = longTimeout;
		while (leadsZero != 0) {
			try {
				Thread.sleep(1000);
				leadsZero--;
			} catch (Exception e) {
				logger.info("Page could not load in the specified time: " + longTimeout);
			}
		}
		return false;
	}

	/**
	 * Wait for elements to be clickable
	 * @param locator
	 * @return
	 * @throws IOException
	 */
	public WebElement waitForElementToBeClickable(By locator) throws IOException {
		WebElement element = null;
		try {
			if (locator != null) {
				WebDriverWait wait = new WebDriverWait(driver, longTimeout);
				if (null != wait.until(ExpectedConditions.visibilityOfElementLocated(locator))) {
					logger.info("Element is clikable");
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				}
			}
		} catch (Exception e) {
			logger.fail("Exception in waitForElementToBeClickable method:",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return element;
	}

	/**
	 * Select Drop down value by text
	 * @param by
	 * @param number
	 * @throws IOException
	 */
	public void selectDropDownValueByIndex(By by, String number) throws IOException {
		try {
			Select select = new Select(driver.findElement(by));
			select.selectByValue(number);
		} catch (Exception e) {
			logger.fail("Exception in the dropdown method: ",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
	}

	/**
	 * Get text of any webelements
	 * @param by
	 * @return
	 * @throws IOException
	 */
	public String getText(By by) throws IOException {
		WebElement locator = null;
		String actualtitle = "";
		try {
			locator = waitForElementToBeVisible(by);
			if (null != locator) {
				actualtitle = locator.getText().trim();
			}
		} catch (Exception e) {
			logger.fail("Exception in the verifyTitle method for the expected string: " + "======",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return actualtitle;
	}
	
	/**
	 * Get Count of web elements
	 * @param by
	 * @return
	 * @throws IOException
	 */
	public int getListCount(By by) throws IOException {
		WebElement locator = null;
		List<WebElement> list = null;
		try {
			locator = waitForElementToBeVisible(by);
			if (null != locator) {
				  list = driver.findElements(by);
			}
		} catch (Exception e) {
			logger.fail("Exception to get List Count: " + "======",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return list.size();
	}

	/**
	 * Capture screen shot for failed scenarios
	 * @return
	 */
	public String captureScreenshot() {

		String filePath = System.getProperty("user.dir") + File.separator + "/Sreenshots/ScreenShot" + currentDataTime()
				+ ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(filePath));
		} catch (IOException e) {

		}
		return filePath;
	}

	public String currentDataTime() {
		String pattern = "HHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return date;
	}

}
