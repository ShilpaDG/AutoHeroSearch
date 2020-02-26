package com.AutoHero.Factory;

import com.AutoHero.Dataprovider.ConfigDataProvider;

public class DataProviderFactory {
	
	public static ConfigDataProvider getCofig() {
		
		ConfigDataProvider config = new ConfigDataProvider();
		
		return config;
	}
	
}
