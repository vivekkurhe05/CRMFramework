package com.freecrm.qa.LoginTestCase;

import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.freecrm.qa.pages.RegistrationPage;
import com.freecrm.qa.testBase.TestBase;

public class RegistrationTest extends TestBase {
	
	public static final Logger logger = Logger.getLogger(RegistrationTest.class.getName());
	static RegistrationPage regpage;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() throws URISyntaxException{
		
		init();
		
	}

	
	@Test
	public void registration(){
				
		logger.info("====================Registration started=============================");
		regpage = new RegistrationPage(driver);
		regpage.registerUser("Rahul", "Gondal", "rahul@gmail.com", "rahul@gmail.com", "Rahul", "rahul123", "rahul123");
		logger.info("====================Registration ended===============================");
		
	}
	
	
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
	}
}
