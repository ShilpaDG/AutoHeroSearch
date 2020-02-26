package com.AutoHero.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class AutoUtils {
	
	public ExtentTest logger;
	WebDriver driver;
	WebDriverUtils webDriverUtils;
	public static String GETYEAR = "(//uL[contains(@class,'specList__')])[$YEAR]/li[1]";
	public static String TOTALPRICE = "(//div[contains(@class,'totalPrice___')])[$Price]";
	
	public AutoUtils(WebDriver driver, ExtentTest logger) {
		this.logger = logger;
		this.driver = driver;
	}
	/** Method to verify Registered year is above the selected year of First registration
	 * @param count
	 * @param yearCheck
	 */
	public void verifyYearRegistered(int count, int yearCheck) {
		webDriverUtils = new WebDriverUtils(driver, logger);
		String yearText, priceText;
		List<Float> priceList = new ArrayList<Float>();

		try {
			for (int i = 1; i <= count; i++) {
				logger.info("__________"+i+"   item in the page");
				logger.info("Get registration data");
				yearText = webDriverUtils.getText(By.xpath(GETYEAR.replace("$YEAR", "" + i + "")));
				logger.info("Get Price");
				priceText = webDriverUtils.getText(By.xpath(TOTALPRICE.replace("$Price", "" + i + "")));

				logger.info("Get only year from registration date");
				String price = priceText.substring(0, priceText.length() - 2);
				logger.info("Get only number from Price ");
				String year = yearText.substring(yearText.length() - 4, yearText.length());
				
				logger.info("add price value in list");
				priceList.add(Float.valueOf(price));
				logger.info("Auto Registered year : " + year);
				int yearofAutoRegistration = Integer.parseInt(year);
				logger.info("Check if Year is greater than or equal to 2015");
				if (!(yearCheck <= yearofAutoRegistration)) {
					logger.fail("year is lesser than First Registration filter");
					break;
				}else {
					logger.pass("year is Greater than First Registration filter");
				}
			}
			verifyPricesortedDesc(priceList);
		} catch (IOException e) {
			logger.fail("year or price is not accourding to filter");
		}

	}
	
	/**Method to Verify Price Sorted in Descending
	 * 
	 * @param priceList
	 */
	public void verifyPricesortedDesc(List<Float> priceList) {
		logger.info("copy price values to another list");
		List copy = new ArrayList(priceList);
		logger.info("Sort price value in decending order");
		Collections.sort(copy, Collections.reverseOrder());
		logger.info("copy :" + copy);
		
		logger.info("Verify whether both the list are same");
		boolean sorted = copy.equals(priceList);
		if (!sorted) {
			logger.fail("Price is not sorted");
		}else {
			logger.pass("year is Greater than First Registration filter");
		}
	}

}