package com.freecrm.qa.customListener;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.freecrm.qa.testBase.TestBase;

public class Listener extends TestBase implements ITestListener{

	public static final Logger logger = Logger.getLogger(Listener.class.getName());
	
	
	public void onTestStart(ITestResult result) {
		
		String methodName = result.getName();
		logger.info("Started "+methodName+" excecution");
		
	}
		
	
	public void onTestSuccess(ITestResult result) {
		
		
		String methodName = result.getName();
		
		if(result.isSuccess()){
			TakesScreenshot ts = (TakesScreenshot)driver;
			Calendar calendar = Calendar.getInstance();
			File image = ts.getScreenshotAs(OutputType.FILE);
//			String imageLocation = System.getProperty("user.dir")+"/src/main/java/com/freecrm/qa/screenshots/";
			String imageLocation = System.getProperty("user.dir")+"/src/main/java/com/freecrm/qa/config/";
			DateFormat dateTime = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String actualImageLocation = imageLocation+"success_screenshots/"+methodName+"_"+dateTime.format(calendar.getTime())+".jpg";
			File destFile = new File(actualImageLocation);
			try {
				FileUtils.copyFile(image, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Reporter.log("<a href= '" + destFile.getAbsolutePath() + "'> <img src='"+destFile.getAbsolutePath() + "' height = '100' width='100'/> </a>");

			
		}

		
	}

	public void onTestFailure(ITestResult result) {
		
		// This statement gives the method name which actually fails on the runtime
		String methodName = result.getName();
		
		if(!result.isSuccess()){
			TakesScreenshot ts = (TakesScreenshot)driver;
			Calendar calendar = Calendar.getInstance();
			File image = ts.getScreenshotAs(OutputType.FILE);
//			String imageLocation = System.getProperty("user.dir")+"/src/main/java/com/freecrm/qa/screenshots/";
			String imageLocation = System.getProperty("user.dir")+"/src/main/java/com/freecrm/qa/config/";
			DateFormat dateTime = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String actualImageLocation = imageLocation+"failure_screenshots/"+methodName+"_"+dateTime.format(calendar.getTime())+".png";
			File destFile = new File(actualImageLocation);
			try {
				FileUtils.copyFile(image, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Reporter.log("<a href= '" + destFile.getAbsolutePath() + "'> <img src='"+destFile.getAbsolutePath() + "' height = '100' width='100'/> </a>");

			
		}

		
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
				
	}

	public void onFinish(ITestContext context) {
		
		String methodName = context.getName();
		logger.info("Finished "+methodName+" execution");
		
	}

	
	
	
}
