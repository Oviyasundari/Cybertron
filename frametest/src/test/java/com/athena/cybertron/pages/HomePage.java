package com.athena.cybertron.pages;

import java.util.Properties;

import com.athena.cybertron.wrappers.FacebookWrapper;

public class HomePage extends FacebookWrapper {
	public Properties loginProp = loadProp("./pageObjects/homePage.properties");

	public void login() {
		System.out.println(loginProp.getProperty("emailID"));
		System.out.println(prop.getProperty("username"));
		enterById(loginProp.getProperty("emailID"), prop.getProperty("username"));
		enterById(loginProp.getProperty("passwordID"), prop.getProperty("password"));
		// System.out.println("uname="+uname);
		// System.out.println("pwd="+pwd);
		// enterById("email", uname);
		// enterById("pass", pwd);
		clickById(loginProp.getProperty("loginButtonID"));
	}
}
