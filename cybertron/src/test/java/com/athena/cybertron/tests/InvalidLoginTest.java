package com.athena.cybertron.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.athena.cybertron.pages.InvalidLoginPage;
import com.athena.cybertron.pages.LoginPage;
import com.athena.cybertron.wrappers.FacebookWrapper;

public class InvalidLoginTest extends FacebookWrapper {
	@BeforeClass
	public void setValues() {
		ExcelFileName = "xlData";
		SheetName = "error";
	}

	@Test(dataProvider = "xlData", dataProviderClass = FacebookWrapper.class)
	public void Invalidlogin(String uname, String pwd) {
		reportStep("Started Executing the class " + this.getClass().getName(), "INFO");
		LoginPage loginPage = new LoginPage();
		loginPage.login(uname, pwd);
		InvalidLoginPage error = new InvalidLoginPage();
		error.verifyTextContainsByXpath(error.loginErrorPageProp.getProperty("loginError"),
				"The password that you've entered is incorrect");
		reportStep("Completed Executing the class " + this.getClass().getName(), "INFO");

	}
}
