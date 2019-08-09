package com.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExecutionHelper {

	// screenshot, alerts, frames, windows, Sync issue, javascript Executor

	// If you put the method as static -- you need not create an object of the class
	// to use it,
	// you can use it on demand

	public static String captureScreenshot(WebDriver driver) {
		String screenshotpath = System.getProperty("user.dir") + "./Screenshots/SS_" + getCurrentDateTime() + ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(src, new File(screenshotpath));

		} catch (Exception e) {

			System.out.println("Issue taking screenshot: " + e.getMessage());
		}

		return screenshotpath;
	}

	public static String getCurrentDateTime() {

		DateFormat customformat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");

		Date currentdate = new Date(); // Date should be imported from java.util and not from java.sql

		String FormattedcurrentDate = customformat.format(currentdate);

		return FormattedcurrentDate;
	}

	public static void highlightElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {

			Thread.sleep(1000);
		}

		catch (InterruptedException e) {

			System.out.println(e.getMessage());

		}

		// reverting the change
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

	}
	
	//this helps the driver to wait for 30 seconds, highlight the element provided in the argument and then lead to element not found exception
	public static WebElement waitforWebElement(WebDriver driver, WebElement element, int time)
	{
		
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(element));
	
		//wait for the element as above searched and highlight it before next step
		highlightElement(driver, ele);
		
		return ele;
	
	}
	
	//this helps the driver to wait for 30 seconds, highlight the element given through xpath and then lead to element not found exception
	public static WebElement waitforWebElement(WebDriver driver, String xpath, int time)
	
	{
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		
		//wait for the element searched through xpath and highlight it
		highlightElement(driver, ele);
		
		return ele;

	}
	

}
