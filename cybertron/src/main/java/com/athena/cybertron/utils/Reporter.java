package com.athena.cybertron.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter extends TestListenerAdapter {
	public static ExtentReports report;
	public static ExtentTest logger;
	public static RemoteWebDriver driver;
	public static String resultDirectory = "./output";

	@BeforeSuite
	public void startReport() {
		report = new ExtentReports(resultDirectory + "/Report.html");
		logger = report.startTest("Report");
	}

	public void reportStep(String message, String status) {
		if (status.equalsIgnoreCase("INFO")) {
			logger.log(LogStatus.INFO, message);
		} else if (status.equalsIgnoreCase("PASS")) {
			logger.log(LogStatus.PASS, message);
		} else if (status.equalsIgnoreCase("FAIL")) {
			logger.log(LogStatus.FAIL, message);
			String file_path = captureScreenshot(message.replace(" ", "_"));
			reportStep("Refer " + file_path + " for Screenshot", "INFO");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String file_path = captureScreenshot(result.getName());
		reportStep("Refer" + file_path + "for failure Screenshot", "INFO");
	}

	public String captureScreenshot(String Screenshotname) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dest = resultDirectory + Screenshotname + ".png";
		File destination = new File(dest);
		try {
			if (destination.exists()) {
				destination.delete();
			}
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
