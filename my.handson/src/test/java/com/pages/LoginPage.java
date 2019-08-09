package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.utilities.ExecutionHelper;


public class LoginPage {
	
	WebDriver driver;
	
	//Constructor
	public LoginPage(WebDriver ldriver) 
	{
		
		this.driver = ldriver;
		
	}
	
	//To locate elements on LoginPage - this is the new commit added
	@FindBy(xpath="//input[@id='txtUsername']") WebElement uname;
	
	@FindBy(xpath = "//input[@id='txtPassword']") WebElement pwd;
	
	@FindBy(xpath = "//input[@id='btnLogin']") WebElement submitbutton;

	public void loginfn(String unamefromuser, String pwdfromuser) {
		
		//highlight the element and then perform the action
		
/*		ExecutionHelper.highlightElement(driver, uname);
		uname.sendKeys(unamefromuser);
		
		ExecutionHelper.highlightElement(driver, pwd);
		pwd.sendKeys(pwdfromuser);
		
		submit.click();*/
		
		
		ExecutionHelper.waitforWebElement(driver, uname, 20).sendKeys(unamefromuser);
		ExecutionHelper.waitforWebElement(driver, pwd, 20).sendKeys(pwdfromuser);;
		ExecutionHelper.waitforWebElement(driver, submitbutton, 20).click();

	}
	
	public void verifyTitle()
	{
		String title=driver.getTitle();
		
		Assert.assertEquals(title,"OrangeHRM");
	}

}
