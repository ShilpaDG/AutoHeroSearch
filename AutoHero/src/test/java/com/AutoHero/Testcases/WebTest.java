package com.AutoHero.Testcases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.AutoHero.Dataprovider.PropertiesRead;
import com.AutoHero.Factory.BrowserFactory;
import com.AutoHero.Factory.DataProviderFactory;

public class WebTest extends BaseClass{	
	
	public static String PROPERTIES_PATH = System.getProperty("user.dir") + File.separator + "Configuration/";
	static PropertiesRead prop = new PropertiesRead(PROPERTIES_PATH + File.separator + "application.properties");
	WebDriver driver;
	Search search;
	
	/**
	 * Launch Browser with application url
	 * */
	@BeforeClass
	public void launchApplication() {
		driver =BrowserFactory.getBrowswer(prop.getProperty("BrowserType"));
		driver.get(DataProviderFactory.getCofig().getApplicationUrl());
		
	}
	
	/**
	 * Method to test search functionality
	 * @throws IOException 
	 * @throws InterruptedException 
	 * */
	@Test(priority =1)
	public void autoSearchTest() throws IOException, InterruptedException {
		logger=extent.createTest("Auto Search Test");
		search = new Search(driver,logger);
		search.filterAuto();
	}
	
	/***
	 * Close browser After execution of class
	 */
	@AfterClass
	public void closeBroswer()
	{
		driver.quit();
	}
	

}
