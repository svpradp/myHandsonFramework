package com.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.utilities.BrowserFactory;
import com.utilities.ConfigDataProvider;
import com.utilities.ExcelDataProvider;
import com.utilities.ExecutionHelper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider edpObj;
	public ConfigDataProvider cdpObj;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void getReadySuite() {
		
		Reporter.log("Setting up the Suite",true);  //From TestNG
		
		edpObj = new ExcelDataProvider();
		cdpObj = new ConfigDataProvider();
		
		//Initiate the html reporter
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/TestReport_"+ExecutionHelper.getCurrentDateTime()+".html"));
		
		report = new ExtentReports();
		
		report.attachReporter(extent);
		
		Reporter.log("Suite set up complete",true);  //From TestNG
	} 

	@BeforeClass
	public void startUp() {

		//driver = BrowserFactory.startApp(driver, "chrome", "https://opensource-demo.orangehrmlive.com/");
			
		Reporter.log("Starting the browser",true); 
		
		driver = BrowserFactory.startApp(driver, cdpObj.getBrowser(), cdpObj.getURL());
		
		Reporter.log("Browser up and running",true); 
	}

	@AfterClass
	public void tearDown() {
		
		BrowserFactory.quitBrowser(driver);
		
		Reporter.log("Quitting the browser",true); 
	}
	
	@AfterMethod   //this runs after each method, and below method is written to capture the failure
	public void tearDownMethod(ITestResult result) {
		
		Reporter.log("Test About to End, getting execution status ready",true); 

		//This ensures a screenshot is taken only on test failure, if it passes, it doesnt take it
		if(result.getStatus()==ITestResult.FAILURE)
		{
			//ExecutionHelper.captureScreenshot(driver);
			
			try {
				logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(ExecutionHelper.captureScreenshot(driver)).build());
			} catch (IOException e) {
				System.out.println("Failed to attach screenshot to failed Test");
			}
			
		}
		
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			//ExecutionHelper.captureScreenshot(driver);
			
			try {
				logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(ExecutionHelper.captureScreenshot(driver)).build());
			} catch (IOException e) {
				System.out.println("Failed to attach screenshot to Passed Test");
			}
			
		}
		
		
		report.flush(); // will keep adding the update to the report after each method -- its initiated in BeforeSuite
		
		Reporter.log("Report updated for this test",true); 
	}

}
