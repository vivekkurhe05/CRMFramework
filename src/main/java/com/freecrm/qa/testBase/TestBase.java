package com.freecrm.qa.testBase;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.freecrm.qa.customListener.Listener;
import com.freecrm.qa.customListener.WebEventListener;
import com.freecrm.qa.excelreader.Excel_Reader;


public class TestBase {

	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	
//	public static WebDriver driver;
	public WebDriver dr;
	public EventFiringWebDriver driver;
	public WebEventListener eventListener;
	
	DesiredCapabilities capabilities;
	Excel_Reader excel;
	Listener lis;
	
	String url = "http://www.freecrm.com";
	String browser = "firefox";

	
	
	public void init() throws URISyntaxException{
		
		getBrowser(browser);
		logger.info(browser+" is opened");
		
		getURL(url);
		
		URI uri = new URI(driver.getCurrentUrl());
		logger.info("URL "+uri.getHost()+" is opened");
		String log4jConfPath = "Log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
	}
	
	public void getBrowser(String browser){
				
		
		if(System.getProperty("os.name").contains("Windows")){
			
			System.out.println("Test running on "+System.getProperty("os.name"));
			
			if(browser.equalsIgnoreCase("firefox")){
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				
				logger.info("Creating the object of "+browser);
//				driver = new FirefoxDriver();
				dr = new FirefoxDriver();
				
				//EventFiringWebDriver registers the webEvent listener hence it takes WebDriver object reference as an argument
				driver = new EventFiringWebDriver(dr);
				
				eventListener = new WebEventListener();
				
				//If you comment this line then eventListener will not work
//				driver.register(eventListener);
				
				
				
			}else if(browser.equalsIgnoreCase("chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				
				logger.info("Creating the object of "+browser);
//				driver = new ChromeDriver();
				dr = new ChromeDriver();
				//EventFiringWebDriver registers the webEvent listener hence it takes WebDriver object reference as an argument
				driver = new EventFiringWebDriver(dr);
				
				eventListener = new WebEventListener();
				driver.register(eventListener);

				
				
			}else if(browser.equalsIgnoreCase("headless")){
				
//				driver = new HtmlUnitDriver(true);
				dr = new HtmlUnitDriver(true);
				//EventFiringWebDriver registers the webEvent listener hence it takes WebDriver object reference as an argument
				driver = new EventFiringWebDriver(dr);
				
				eventListener = new WebEventListener();
				driver.register(eventListener);
				
				
			}
			
			
		}else if(System.getProperty("os.name").contains("Linux")){
			
			System.out.println("Test running on "+System.getProperty("os.name"));
			
			if(browser.equalsIgnoreCase("firefox")){
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				
				logger.info("Creating the object of "+browser);
//				driver = new FirefoxDriver();
				dr = new FirefoxDriver();
				
				
				
			}else if(browser.equalsIgnoreCase("chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				
				logger.info("Creating the object of "+browser);
//				driver = new ChromeDriver();
				dr = new ChromeDriver();
					
			}else if(browser.equalsIgnoreCase("headless")){
				
//				driver = new HtmlUnitDriver(true);
				dr = new HtmlUnitDriver(true);
			}
			
			
			
		}else if(System.getProperty("os.name").contains("Mac")){
			
			
			System.out.println("Test running on "+System.getProperty("os.name"));
			
			if(browser.equalsIgnoreCase("firefox")){
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				
				logger.info("Creating the object of "+browser);
//				driver = new FirefoxDriver();
				dr = new FirefoxDriver();
				
				
			}else if(browser.equalsIgnoreCase("chrome")){
				
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				
				logger.info("Creating the object of "+browser);
//				driver = new ChromeDriver();
				dr = new ChromeDriver();
					
			}else if(browser.equalsIgnoreCase("headless")){
				
//				driver = new HtmlUnitDriver(true);
				dr = new HtmlUnitDriver(true);
			}			
		}
		
	}
	
	
	public void getURL(String url){
		
		try{
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		logger.info("Navigating to the URL "+url);
		driver.get(url);
		}catch(Exception e){
			
			System.out.println("Check internet connection");
		}

	}
	
	
	public String[][] getData(String excelName, String sheetName){
		
		String path = System.getProperty("user.dir")+"/src/main/java/com/freecrm/qa/data/"+excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(excelName, sheetName);
		return data;
	}
	
	
	
	//We have implemented ITestListener interface now we dont need to define this method here
	public String getScreenShot(String imageName) throws Exception{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		Calendar calendar = Calendar.getInstance();
		File image = ts.getScreenshotAs(OutputType.FILE);
		String imageLocation = System.getProperty("user.dir")+"/src/main/java/com/freecrm/qa/screenshots/";
		DateFormat dateTime = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageLocation = imageLocation+imageName+"_"+dateTime.format(calendar.getTime())+".png";
		File destFile = new File(actualImageLocation);
		FileUtils.copyFile(image, destFile);
		Reporter.log("<a href= '" + destFile.getAbsolutePath() + "'> <img src='"+destFile.getAbsolutePath() + "' height = '100' width='100'/> </a>");
		return actualImageLocation;
		
		
	}
	
	public void waitForElement(WebElement element, long timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
			
	public static void main(String args[]) throws IOException{
		
//		TestBase tbase = new TestBase();
//		
//		Config con = new Config(prop);
//		System.out.println(con.getBrowserName());
//		System.out.println(con.getLoginUserName());
//		System.out.println(con.getLoginPassword());
//		System.out.println(con.getExplicitWait());
//		System.out.println(con.getImplicitWait());
		
		
		
		
		
		
		
	}

}
