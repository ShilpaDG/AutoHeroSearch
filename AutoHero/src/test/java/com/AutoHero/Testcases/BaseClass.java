package com.AutoHero.Testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	public ExtentReports extent;
	public ExtentTest logger;
	private WebDriver driver = null;

	@BeforeSuite
	public void setup()
	{
	    ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/Report.html");
	    extent = new ExtentReports();
	    extent.attachReporter(reporter);
	 }
	

	@AfterClass
	public void tearDown()
	{
		extent.flush();
	}

}
