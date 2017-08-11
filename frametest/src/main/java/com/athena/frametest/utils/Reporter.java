package com.athena.frametest.utils;

import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Reporter
{
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeTest
	public void startReport(){
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/ExtentReport.html", true);
	}
	public void reportStep(String desc, String status){
		//logger.log(status, desc);
	}
}
