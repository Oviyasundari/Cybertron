package com.athena.cybertron.pages;

import java.util.Properties;

import com.athena.cybertron.wrappers.FacebookWrapper;

public class HomePage extends FacebookWrapper {
	public Properties loginProp = loadProp("./pageObjects/homePage.properties");

	public void login(String uname, String pwd) {
		enterById("email", uname);
		enterById("pass", pwd);
		clickById(loginProp.getProperty("loginButtonID"));
	}
}
