package com.freecrm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.freecrm.qa.testBase.TestBase;

/*
 * 
 * Here we have extended TestBase because we are invoking waitForElement method defined in class TestBase
 * 
 * 
 * 
 */

public class LoginPage extends TestBase {
	
	public static final Logger logger = Logger.getLogger(LoginPage.class.getName());
	
	WebDriver driver;
	
	@FindBy(xpath = "//img[contains(@src,'freecrm.jpg')]")
	WebElement logo;
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;
	
	
	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'User: Naveen K')]")
	WebElement authenticateUser;
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logout;
		
	
	public LoginPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	public boolean verifyLogo(){
		
		logger.info("Checking the availibility of logo");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(logo));
		return this.logo.isDisplayed();
//		return false;
	}
	
	
	
	
	public void verifyLogin(String username, String password){
		
		this.username.sendKeys(username);
		logger.info("Entered username "+username);
		this.password.sendKeys(password);
		logger.info("Entered password "+password);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginBtn.click();
		logger.info("Clicked on button "+loginBtn.getAttribute("value"));
	}
	
	
	
	
	public boolean verifyAuthentication(){
		
		try{
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@src='https://www.freecrm.com/system/index.cfm']")));
			logger.info("Switched to main panel");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			try{
				authenticateUser.isDisplayed(); 
				logger.info("User is found on Home page");
			}catch(Exception e){
				logger.info("User is not found on Home page");
			}
			
			logger.info("Logged in successfully");
			return true;
		}catch(Exception e){
			logger.info("Unauthorized user");
			return false;
			
		}
		
	}
	
	
	
	
	
	public void clickOnLogout(){
		
		try{			
			Thread.sleep(4000);
			logout.click();
			Thread.sleep(1000);
			logger.info("User is logged out");
		}catch(Exception e){
			logger.info("Logout is not clicked");
			
		}
		
	}
	
	
}
