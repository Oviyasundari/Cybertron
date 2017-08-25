package com.athena.cybertron.tests;

import org.testng.annotations.Test;

import com.athena.cybertron.pages.HomePage;
import com.athena.cybertron.utils.ExcelReader;
import com.athena.cybertron.wrappers.FacebookWrapper;

public class FbLoginTest extends FacebookWrapper {

	@Test(dataProvider = "xlData", dataProviderClass = ExcelReader.class)
	public void testCase(String uname, String pwd) {
		HomePage homePage = new HomePage();
		homePage.login(uname, pwd);
		//call logout method to test negative test case
		
	}
}
