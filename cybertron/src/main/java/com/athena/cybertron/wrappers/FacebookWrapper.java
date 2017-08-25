package com.athena.cybertron.wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class FacebookWrapper extends CommonMethods {
	public Properties faceBookProp;

	@BeforeMethod
	public void login() {
		invokeApp();
		loadConfig();
	}

	@AfterMethod
	public void logout() {
		
		quitBrowser();
	}

	public Properties loadProp(String propPath) {
		faceBookProp = new Properties();
		try {
			faceBookProp.load(new FileInputStream(new File(propPath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return faceBookProp;
	}

}
