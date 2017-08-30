package com.athena.cybertron.tests;

import org.testng.annotations.Test;

import com.athena.cybertron.pages.InvalidLoginPage;
import com.athena.cybertron.pages.LoginPage;
import com.athena.cybertron.wrappers.FacebookWrapper;

public class LoginTest extends FacebookWrapper {
	{
		ExcelFileName = "xlData";
		SheetName = "login";
	}
	LoginPage loginPage = new LoginPage();
	InvalidLoginPage errorPage = new InvalidLoginPage();

	@Test(dataProvider = "xlData", dataProviderClass = FacebookWrapper.class)
	public void login(String uname, String pwd, String type, String expected) {
		reportStep("Started Executing the class " + this.getClass().getName(), "INFO");
		loginPage.login(uname, pwd);
		if (type.equalsIgnoreCase("NEGATIVE")) {
			loginPage.verifyTextContainsByXpath(errorPage.invalidLoginPageProp.getProperty("loginError"), expected);
		} else {
			if (verifyTitle(expected))
				reportStep("Logged In Successfully", "PASS");
			else
				reportStep("Logged In Successfully", "FAIL");
		}
		reportStep("Completed Executing the class " + this.getClass().getName(), "INFO");
	}
}