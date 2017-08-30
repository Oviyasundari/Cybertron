package com.athena.cybertron.wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.athena.cybertron.utils.ExcelReader;

public class FacebookWrapper extends CommonMethods {
	public Properties faceBookProp;
	public static String ExcelFileName, SheetName;

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return faceBookProp;
	}

	@DataProvider(name = "xlData")
	public Object[][] getData() {
		return ExcelReader.getExcelSheetData(ExcelFileName, SheetName);
	}

}
