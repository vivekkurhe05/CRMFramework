package com.freecrm.qa.customListener;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;

public class WebEventListener implements WebDriverEventListener {
	
	public static final Logger log = Logger.getLogger(WebEventListener.class.getName());
	
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		
		log("Before navigating to "+url+"");
		
	}
	
	
	public void afterNavigateTo(String url, WebDriver arg1) {
		
		log("After navigating to "+url+"");
		
	}
	
	
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] arg2) {
		
		log("Value of the "+element.toString()+" before any changes made");
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver arg1, CharSequence[] arg2) {
		
		log("Element value changed to "+element.toString());
		
	}
	
	public void beforeClickOn(WebElement element, WebDriver arg1) {
		
		log("Try to click on "+element.toString());
		
	}


	public void afterClickOn(WebElement element, WebDriver arg1) {
		
		log("Clicked on "+element.toString());
		
	}
	
	
	public void beforeNavigateBack(WebDriver driver) {
		
		log("Navigating back to previous page");
		
	}

	public void afterNavigateBack(WebDriver arg0) {
		
		log("Navigated back to previous page");
		
	}

	
	public void beforeNavigateForward(WebDriver driver) {
		
		log("Navigating forward to the page");
		
	}

	
	public void afterNavigateForward(WebDriver driver) {
		
		log("Navigated forward to the page");
		
	}


	public void onException(Throwable error, WebDriver driver) {
		
		log("Exception occured "+error);
//		Reporter.log(s);
		
	}

	
	public void afterAlertAccept(WebDriver arg0) {
		
	}

	public void afterAlertDismiss(WebDriver arg0) {
		
	}



	public void afterFindBy(By by, WebElement arg1, WebDriver arg2) {
		
		log("Found element by :"+by.toString());
		
	}



	public void afterNavigateRefresh(WebDriver arg0) {
		
	}


	public void afterScript(String arg0, WebDriver arg1) {
		
	}

	public void beforeAlertAccept(WebDriver arg0) {
		
	}

	public void beforeAlertDismiss(WebDriver arg0) {
		
	}



	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		
		log("Trying to find element by :"+by.toString());
		
	}



	public void beforeNavigateRefresh(WebDriver arg0) {
		
	}


	public void beforeScript(String arg0, WebDriver arg1) {
		
	}

	
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}

}
