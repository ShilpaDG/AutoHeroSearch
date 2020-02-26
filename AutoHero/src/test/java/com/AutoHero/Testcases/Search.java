package com.AutoHero.Testcases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.AutoHero.Dataprovider.PropertiesRead;
import com.AutoHero.Utils.AutoUtils;
import com.AutoHero.Utils.WebDriverUtils;

public class Search {

	WebDriver driver;
	public ExtentTest logger;
	WebDriverUtils webDriverUtils;
	public static String PROPERTIES_PATH = System.getProperty("user.dir") + File.separator + "Configuration/";
	static PropertiesRead prop = new PropertiesRead(PROPERTIES_PATH + File.separator + "application.properties");

	public Search(WebDriver driver, ExtentTest logger) {
		this.logger = logger;
		this.driver = driver;
	}

	public static By SEARCH = new By.ByXPath("//a[text()='Fahrzeugbestand']");
	public static By FIRSTRIGISTRATION = new By.ByXPath("//span[text()='Erstzulassung ab']");
	public static By ANYYEAR = new By.ByXPath("//select[@name='yearRange.min']");
	public static By SORTCAR = new By.ByXPath("//select[@name='sort']");
	public static By PAGESIZE = new By.ByXPath("//select[@name='pageSize']");
	public static By FILTERSELECTED = new By.ByXPath("//button[text()='Erstzulassung von']");
	public static String GETYEAR = "(//uL[contains(@class,'specList__')])[$YEAR]/li[1]";
	public static By RECORDLIST = new By.ByXPath("//uL[contains(@class,'specList__')]");
	public static By LASTPAGE = new By.ByXPath("//span[text()='»']");
	public static String TOTALPRICE = "(//div[contains(@class,'totalPrice___')])[$Price]";
	public static By BETWEENPAGENUMBER = new By.ByXPath("//uL[contains(@class,'pagination')]/li[7]/a[text()='5']");
	
	/**
	 * Method to test search functionality with first registration and price filters
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public void filterAuto() throws IOException, InterruptedException {
		int yearCheck = 2015;

		webDriverUtils = new WebDriverUtils(driver, logger);
		logger.info("click on Search");
		webDriverUtils.click(SEARCH);
		webDriverUtils.waitForElementToBeVisible(FIRSTRIGISTRATION);
		logger.info("click on First Registration");
		webDriverUtils.click(FIRSTRIGISTRATION);
		logger.info("select From 2015");
		webDriverUtils.selectDropDownValueByIndex(ANYYEAR, prop.getProperty("YearForFirstRegistrationDropDownValue"));
		logger.info("select Price Descending");
		webDriverUtils.selectDropDownValueByIndex(SORTCAR,prop.getProperty("PriceDescendingDropDownValue"));
		webDriverUtils.waitForElementToBeVisible(FILTERSELECTED);
		logger.info("select 24 items to list in one page");
		webDriverUtils.selectDropDownValueByIndex(PAGESIZE,prop.getProperty("PaginationDropDownValue"));
		
		logger.info("get number of items in one page");
		int listcountFirstPage = webDriverUtils.getListCount(RECORDLIST);
		AutoUtils autoUtils= new AutoUtils(driver, logger);
		logger.info("Verify Search functionality for first page");
		autoUtils.verifyYearRegistered(listcountFirstPage, yearCheck);
		
		logger.info("Verify Search functionality for 5th page");
		pageVerify(BETWEENPAGENUMBER,yearCheck);
		logger.info("Verify Search functionality for Last page");
		pageVerify(LASTPAGE,yearCheck);
		
	}
	
	/**Method to verify all cars are filtered by first registration year
	 * and all cars are sorted by price type selected
	 * @param locator
	 * @param yearCheck
	 * @throws IOException
	 */
	public void pageVerify(By locator,int yearCheck) throws IOException {
		AutoUtils autoUtils= new AutoUtils(driver, logger);
		logger.info("navigate to perticular page");
		webDriverUtils.click(locator);
		webDriverUtils.waitForPageLoad();
		
		logger.info("get number of items in particular page");
		int listcountLastPage = webDriverUtils.getListCount(RECORDLIST);
		autoUtils.verifyYearRegistered(listcountLastPage, yearCheck);
	}

}
