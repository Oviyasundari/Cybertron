package com.athena.cybertron.tests;

import org.testng.annotations.Test;

import com.athena.cybertron.pages.LoginPage;
import com.athena.cybertron.wrappers.FacebookWrapper;

public class LoginTest extends FacebookWrapper {
	{
		ExcelFileName = "xlData";
		SheetName = "login";
	}
	LoginPage loginPage = new LoginPage();
	@Test(dataProvider = "xlData", dataProviderClass = FacebookWrapper.class)
	public void login(String uname, String pwd) {
		reportStep("Started Executing the class " + this.getClass().getName(), "INFO");
		loginPage.login(uname, pwd);
		
		reportStep("Completed Executing the class " +this.getClass().getName(), "INFO");
	}
}
