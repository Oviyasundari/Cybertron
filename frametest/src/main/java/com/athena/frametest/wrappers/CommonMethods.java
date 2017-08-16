package com.athena.frametest.wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.athena.frametest.utils.Reporter;

public class CommonMethods extends Reporter implements Wrappers {

	public RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl, primaryWindowHandle, sHubUrl, sHubPort;

	public CommonMethods() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadObjects() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("object.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void unloadObjects() {
		prop = null;
	}

	/**
	 * This method will launch the browser in local machine and maximise the
	 * browser and set the wait for 30 seconds and load the url
	 *
	 * @param url
	 *            - The url with http or https
	 * 
	 */
	public void invokeApp(String browser) {
		invokeApp(browser, false);
	}

	/**
	 * This method will launch the browser in grid node (if remote) and maximise
	 * the browser and set the wait for 30 seconds and load the url
	 *
	 * @param url
	 *            - The url with http or https
	 * 
	 */

	public void invokeApp(String browser, boolean bRemote) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);

			prop.load(new FileInputStream(new File("frametest/src/main/java/config.properties")));

			// this is for grid run
			if (bRemote)
				driver = new RemoteWebDriver(new URL("http://" + sHubUrl + ":" + sHubPort + "/wd/hub"), dc);
			else { // this is for local run
				if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
					driver = new ChromeDriver();
				} else {
					System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
				}
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(sUrl);

			primaryWindowHandle = driver.getWindowHandle();
			reportStep("The browser:" + browser + " launched successfully", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}

	}

	/**
	 * This method will enter the value to the text field using id attribute to
	 * locate
	 * 
	 * @param idValue
	 *            - id of the webelement
	 * @param data
	 *            - The data to be sent to the webelement
	 */
	public void enterById(String idValue, String data) {
		try {
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);
			reportStep("The data: " + data + " entered successfully in field :" + idValue, "PASS");
		} catch (NoSuchElementException e) {
			reportStep("The data: " + data + " could not be entered in the field :" + idValue, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering " + data + " in the field :" + idValue, "FAIL");
		}
	}

	public void enterByName(String nameValue, String data) {
		try {
			driver.findElement(By.name(nameValue)).clear();
			driver.findElement(By.name(nameValue)).sendKeys(data);
			reportStep("The data: " + data + " entered successfully in field :" + nameValue, "PASS");
		} catch (NoSuchElementException e) {
			reportStep("The data: " + data + " could not be entered in the field :" + nameValue, "FAIL");
		} catch (WebDriverException e) {
			reportStep("The data: " + data + " could not be entered in the field :" + nameValue, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering " + data + " in the field :" + nameValue, "FAIL");
		}
	}

	public void enterByXpath(String xpathValue, String data) {
		try {
			driver.findElement(By.xpath(xpathValue)).clear();
			driver.findElement(By.xpath(xpathValue)).sendKeys(data);
			reportStep("The data: " + data + " entered successfully in field :" + xpathValue, "PASS");
		} catch (NoSuchElementException e) {
			reportStep("The data: " + data + " could not be entered in the field :" + xpathValue, "FAIL");
		} catch (WebDriverException e) {
			reportStep("The data: " + data + " could not be entered in the field :" + xpathValue, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering " + data + " in the field :" + xpathValue, "FAIL");
		}

	}

	public boolean verifyTitle(String title) {
		if (driver.getTitle().contains(title)) {
			return true;
		}
		return false;
	}

	public void verifyTextById(String id, String text) {
		try {
			String sText = driver.findElementById(id).getText();
			if (sText.equalsIgnoreCase(text)) {
				reportStep("The text: " + sText + " matches with the value :" + text, "PASS");
			} else {
				reportStep("The text: " + sText + " did not match with the value :" + text, "FAIL");
			}
		} catch (Exception e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
	}

	public void verifyTextByXpath(String xpath, String text) {
		try {
			String sText = driver.findElementByXPath(xpath).getText();
			if (sText.equalsIgnoreCase(text)) {
				reportStep("The text: " + sText + " matches with the value :" + text, "PASS");
			} else {
				reportStep("The text: " + sText + " did not match with the value :" + text, "FAIL");
			}
		} catch (Exception e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
	}

	public void verifyTextContainsByXpath(String xpath, String text) {
		try {
			String sText = driver.findElementByXPath(xpath).getText();
			if (sText.contains(text)) {
				reportStep("The text: " + sText + " matches with the value :" + text, "PASS");
			} else {
				reportStep("The text: " + sText + " did not match with the value :" + text, "FAIL");
			}
		} catch (Exception e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
	}

	public void verifyTextContainsById(String id, String text) {
		try {
			String sText = driver.findElementById(id).getText();
			if (sText.contains(text)) {
				reportStep("The text: " + sText + " matches with the value :" + text, "PASS");
			} else {
				reportStep("The text: " + sText + " did not match with the value :" + text, "FAIL");
			}
		} catch (Exception e) {
			reportStep("Unknown exception occured while verifying the text :" + text, "FAIL");
		}
	}

	public void clickById(String id) {
		try {
			driver.findElementById(id).click();
		} catch (Exception e) {
			reportStep("Unknown exception occured while clicking the Id :" + id, "FAIL");
		}

	}

	public void clickByClassName(String classVal) {
		try {
			driver.findElementByClassName(classVal).click();
		} catch (Exception e) {
			reportStep("Unknown exception occured while clicking by class name :" + classVal, "FAIL");
		}

	}

	public void clickByName(String name) {
		try {
			driver.findElementByName(name).click();
		} catch (Exception e) {
			reportStep("Unknown exception occured while clicking by name : " + name, "FAIL");
		}

	}

	public void clickByLink(String name) {
		try {
			driver.findElementByLinkText(name).click();
		} catch (Exception e) {
			reportStep("Unknown exception occured while clicking by link : " + name, "FAIL");
		}

	}

	public void clickByXpath(String xpathVal) {
		try {
			driver.findElementByXPath(xpathVal).click();
		} catch (Exception e) {
			reportStep("Unknown exception occured while clicking by xpath : " + xpathVal, "FAIL");
		}

	}

	public String getTextById(String idVal) {
		String sText = null;
		try {
			sText = driver.findElementById(idVal).getText();
			return sText;
		} catch (Exception e) {
			reportStep("Unknown exception occured while fetching text by id : " + idVal, "FAIL");
		}

		return sText;

	}

	public String getTextByXpath(String xpathVal) {
		String sText = null;
		try {
			sText = driver.findElementByXPath(xpathVal).getText();
			return sText;
		} catch (Exception e) {
			reportStep("Unknown exception occured while fetching text by xpath : " + xpathVal, "FAIL");
		}

		return sText;
	}

	public void selectVisibileTextById(String id, String value) {
		try {
			Select dropdown = new Select(driver.findElement(By.id(id)));
			dropdown.selectByVisibleText(value);
		} catch (Exception e) {
			reportStep("Unknown exception occured while selecting the text + " + value, "FAIL");
		}
	}

	public void selectIndexById(String id, String value) {
		try {
			Select listbox = new Select(driver.findElement(By.id(id)));
			listbox.selectByIndex(Integer.parseInt(value));
		} catch (Exception e) {
			reportStep("Unknown exception occured while selecting the text + " + value, "FAIL");
		}
	}

	public void switchToParentWindow() {
		try {
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
				break;
			}
		} catch (Exception e) {
			reportStep("The window could not be switched to the first window.", "FAIL");
		}
	}

	public void switchToLastWindow() {
		try {
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
			}
		} catch (Exception e) {
			reportStep("The window could not be switched to the last window.", "FAIL");
		}
	}

	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			reportStep("Unknown exception occured while accepting the alert : ", "FAIL");
		}

	}

	public void quitBrowser() {
		// TODO Auto-generated method stub

	}

}
