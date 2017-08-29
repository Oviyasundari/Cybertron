package com.athena.cybertron.tests;

import org.testng.annotations.Test;

import com.athena.cybertron.pages.LoginPage;
import com.athena.cybertron.utils.ExcelReader;
import com.athena.cybertron.wrappers.FacebookWrapper;

public class LoginTest extends FacebookWrapper {
	LoginPage loginPage = new LoginPage();
	@Test(dataProvider = "xlData", dataProviderClass = ExcelReader.class)
	public void login(String uname, String pwd) {
		loginPage.login(uname, pwd);
	}
}
