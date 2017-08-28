package com.athena.cybertron.tests;

import org.testng.annotations.Test;

import com.athena.cybertron.pages.loginPage;
import com.athena.cybertron.utils.ExcelReader;
import com.athena.cybertron.wrappers.FacebookWrapper;

public class FbLoginTest extends FacebookWrapper {

	@Test(dataProvider = "xlData", dataProviderClass = ExcelReader.class)
	public void loginTest(String uname, String pwd) {
		loginPage loginPage = new loginPage();
		loginPage.login(uname, pwd);
	}
}
