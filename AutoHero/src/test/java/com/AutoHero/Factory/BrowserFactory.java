package com.AutoHero.Factory;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	
	static WebDriver driver;
	public static WebDriver getBrowswer(String browserName) {
		
		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", DataProviderFactory.getCofig().getFireFoxPath());
			driver= new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", DataProviderFactory.getCofig().getCromePath());
			driver= new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", DataProviderFactory.getCofig().getIEPath());
			driver= new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	public static void closeBrowser(WebDriver Idriver) {
		Idriver.quit();
	}

}
