package com.freecrm.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	public static final Logger logger = Logger.getLogger(RegistrationPage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUp;
	
	@FindBy(name = "first_name")
	WebElement firstname;
	
	@FindBy(name = "surname")
	WebElement lastname;
	
	@FindBy(name = "email")
	WebElement email;
	
	@FindBy(name = "email_confirm")
	WebElement emailConfirm;
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(name = "passwordconfirm")
	WebElement passwordConfirm;
	
	@FindBy(name = "agreeTerms")
	WebElement agreeTerms;
	
	public RegistrationPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	public void registerUser(String firtname,String lastname, String email, String emailconfirm, String username, String password, String passwordconfirm){
		
		logger.info("Waiting for sign up button to be visible");
		
		try {
			Thread.sleep(2000);
			this.signUp.click();
			logger.info("Clicked on sign up button");
			System.out.println("Element is visible");
		} catch (Exception e) {
			System.out.println("Element is not visible");
		}
		this.firstname.sendKeys(firtname);
		logger.info("Entered firstname "+firstname);
		this.lastname.sendKeys(lastname);
		logger.info("Entered lastname "+lastname);
		this.email.sendKeys(email);
		logger.info("Entered email "+email);
		this.emailConfirm.sendKeys(emailconfirm);
		logger.info("Entered email again "+emailconfirm);
		this.username.sendKeys(username);
		logger.info("Entered username "+username);
		this.password.sendKeys(password);
		logger.info("Entered password "+password);
		this.passwordConfirm.sendKeys(passwordconfirm);
		logger.info("Entered password again "+passwordconfirm);
		this.agreeTerms.click();
		logger.info("Click on agree terms");
	}
	

	
}
