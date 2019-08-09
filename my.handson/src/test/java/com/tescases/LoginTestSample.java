package com.tescases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.pages.BaseClass;
import com.pages.LoginPage;

public class LoginTestSample extends BaseClass {

	@Test
	public void login() {
		
		logger = report.createTest("Login to CRM");	
		
		String uname = edpObj.getStringData("Login", 0, 0);
		
		String pwd = edpObj.getStringData("Login", 0, 1);

		LoginPage LpageObj = PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Starting Application");

		LpageObj.loginfn(uname, pwd);
		
		logger.pass("Login Success");
		
		LpageObj.verifyTitle();
		
		logger.pass("Login Page title verified");
		
	}

}
