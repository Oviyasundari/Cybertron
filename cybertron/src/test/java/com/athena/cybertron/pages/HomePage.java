package com.athena.cybertron.pages;

import java.util.Properties;

import com.athena.cybertron.wrappers.FacebookWrapper;

public class HomePage extends FacebookWrapper {
	public Properties homePageProp = loadProp("./pageObjects/homePage.properties");

	public void login(String uname, String pwd) {
		enterById(homePageProp.getProperty("emailID"), uname);
		enterById(homePageProp.getProperty("passwordID"), pwd);
		clickById(homePageProp.getProperty("loginButtonID"));
	}
}
