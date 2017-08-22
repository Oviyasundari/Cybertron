package com.athena.cybertron.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {
	// reporter code comes here
	public static ExtentReports report;
	public static ExtentTest logger;
	public static RemoteWebDriver driver;

	@BeforeSuite
	public void startReport() {
		report = new ExtentReports("./Results/Report.html");
		logger = report.startTest("Report");
	}

	public void reportStep(String message, String status) {
		System.out.println("logger=" + logger);
		if (status.equalsIgnoreCase("INFO")) {
			logger.log(LogStatus.INFO, message);
		} else if (status.equalsIgnoreCase("PASS")) {
			logger.log(LogStatus.PASS, message);
		} else if (status.equalsIgnoreCase("FAIL")) {
			logger.log(LogStatus.FAIL, message);
		}
	}

	public void takeScreenShot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String error_msg = captureScreenshot(result.getName());
			reportStep(error_msg, "Fail");
		}
	}

	public String captureScreenshot(String Screenshotname) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dest = "./screenshot/" + Screenshotname + ".png";
		File destination = new File(dest);
		try {
			FileUtils.copyFile(src, destination);
			return dest;
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

	@AfterSuite
	public void endReport() {
		report.endTest(logger);
		report.flush();
	}

}
