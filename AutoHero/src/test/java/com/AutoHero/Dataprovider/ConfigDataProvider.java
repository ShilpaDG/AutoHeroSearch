package com.AutoHero.Dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigDataProvider {
	Properties prop;
	
	public ConfigDataProvider() {
		File src= new File("./Configuration/config.properties");
		try {
		FileInputStream fis = new FileInputStream(src);
	    prop = new Properties();
		prop.load(fis);
		}catch(Exception e) {
			System.out.println("Exception is:"+e.getMessage());
		}
	}
	
	public String getApplicationUrl() {
		String url=prop.getProperty("url");
		return url;
	}
	public String getCromePath() {
		String url=prop.getProperty("cromePath");
		return url;
	}
	public String getIEPath() {
		String url=prop.getProperty("IEPath");
		return url;
	}
	public String getFireFoxPath() {
		String url=prop.getProperty("FireFoxPath");
		return url;
	}

}
