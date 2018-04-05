package com.freecrm.qa.LoginTestCase;

import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.testBase.TestBase;


public class LoginTest extends TestBase {

	public static final Logger logger = Logger.getLogger(LoginTest.class.getName());
	static LoginPage login;  

	@DataProvider(name = "loginData")
	public String[][] getTestData(){

		String [][] testRecords = getData("TestData.xlsx", "FreeCRMLoginTestData");
		return testRecords;
	}

	@BeforeMethod
	public void setUp() throws URISyntaxException{

		init();

	}


	@Test(priority=1)
	public void verifyLoginPageLogo() throws Exception{



		logger.info("==============Starting verifyLoginPageLogo test=====================");

		login = new LoginPage(driver);
	
		boolean b  = login.verifyLogo();
		
		//String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
//		if(!b){
//			getScreenShot(methodName);
//		}
		

		Assert.assertTrue(b, "Logo is not present");

		logger.info("==============Finished verifyLoginPageLogo test=====================");

	}


	//	@Test(dataProvider = "loginData",priority=2)
	public void verifyLoginFunctionality(String username, String password, String runmode){

		if(runmode.equals("n")){
			logger.info("User marked this record as no run==> "+username+" "+password+" "+runmode );
			throw new SkipException("user marked this record as no run");
		}

		logger.info("==============Starting verifyLoginFunctionality test=====================");

		login = new LoginPage(driver);
		login.verifyLogin(username,password);

		boolean status = login.verifyAuthentication();
		Assert.assertEquals(status, true);

		login.clickOnLogout();
		logger.info("==============Finished verifyLoginFunctionality test=====================");


	}

	@AfterMethod
	public void tearDown(){

		driver.quit();
	}


}
