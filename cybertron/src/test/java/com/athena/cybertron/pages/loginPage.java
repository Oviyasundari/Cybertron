package com.athena.cybertron.pages;

import java.util.Properties;

import com.athena.cybertron.wrappers.FacebookWrapper;

public class loginPage extends FacebookWrapper {
	public Properties loginPageProp = loadProp("./pageObjects/loginPage.properties");

	public void login(String uname, String pwd) {
		enterById(loginPageProp.getProperty("emailID"), uname);
		enterById(loginPageProp.getProperty("passwordID"), pwd);
		clickById(loginPageProp.getProperty("loginButtonID"));
	}
}
