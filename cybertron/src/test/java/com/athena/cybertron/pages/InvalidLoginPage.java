package com.athena.cybertron.pages;

import java.util.Properties;

import com.athena.cybertron.wrappers.FacebookWrapper;

public class InvalidLoginPage extends FacebookWrapper {
	public Properties loginErrorPageProp = loadProp("./pageObjects/invalidLoginPage.properties");
	
    
}
