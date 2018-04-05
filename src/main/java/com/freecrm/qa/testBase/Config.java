package com.freecrm.qa.testBase;

import java.util.Properties;

/*
 * Here we have extended TestBase because we have to inherit the Properties object prop
 * we need not create new Properties object
 * 
 */

public class Config extends TestBase {
	
	private Properties prop;
	
	public Config(Properties prop){
		
		this.prop=prop;
		
	}
	
	
	public String getBrowserName(){
		
		return prop.getProperty("browser");
	}
	
	public String getWebsite(){
		
		return prop.getProperty("url", "http://www.freecrm.com");
	}
	
	public int getPageLoadTimeOut(){
		
		return Integer.parseInt(prop.getProperty("pageLoadTimeOut"));
	}
	
	
	public int getImplicitWait(){
		
		return Integer.parseInt(prop.getProperty("implicitWait"));
		
	}

	
	public int getExplicitWait(){
		
		return Integer.parseInt(prop.getProperty("explicitWait"));
		
	}
	
	public String getLoginUserName(){
		
		return prop.getProperty("username");
	}
	
	public String getLoginPassword(){
		
		return prop.getProperty("password");
	}

	
	
}
