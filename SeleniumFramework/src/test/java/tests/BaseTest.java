package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import config.PropertiesFile;

public class BaseTest {	

	public WebDriver _driver;
	public static String browser;
	static String driverPath;
	public ExtentTest report;

	public BaseTest(WebDriver driver, ExtentTest test)
	{
		report = test;
		_driver = driver;
	}

	//	public BaseTest() {
	//		test.log(Status.INFO, "Test has started");
	//		PropertiesFile.getProperties();
	//		if(browser.equalsIgnoreCase("chrome")) {
	//			driverPath = System.getProperty("user.dir") + "/drivers/chromedriver/chromedriver.exe";
	//			System.setProperty("webdriver.chrome.driver", driverPath);
	//			_driver = new ChromeDriver();
	//		}
	//		else if (browser.equalsIgnoreCase("firefox")) {
	//			driverPath = System.getProperty("user.dir") + "/drivers/geckodriver/geckodriver.exe";
	//			System.setProperty("webdriver.gecko.driver", driverPath);
	//			_driver = new FirefoxDriver();
	//		}
	//		test.info(browser + " instance was open");
	//		_driver.manage().window().maximize();
	//		test.info("Window maximized");
	//		page = new PageGenerator(_driver);		
	//	}

	public void generateReport() {
		
	}

	

	public BaseTest() {
		PropertiesFile.getProperties();
		if(browser.equalsIgnoreCase("chrome")) {
			driverPath = System.getProperty("user.dir") + "/drivers/chromedriver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			_driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			driverPath = System.getProperty("user.dir") + "/drivers/geckodriver/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			_driver = new FirefoxDriver();
		}
		_driver.manage().window().maximize();	
	}




	public void tearDown() {
		_driver.quit();        
	}

	//Actions
	public void goToUrl(String url) {
		_driver.get(url);
		report.info("The URL " + url + " was triggered to load");
	}

	//Waiters
	public void waitForPageLoaded(Integer timeOut) {
		try {
			new WebDriverWait(_driver, timeOut).until(
					webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
			report.pass("The URL was succesfully loaded");
		} catch (Throwable error) {
			report.fail("The URL failed to load. Current URL was: " + _driver.getCurrentUrl());
		}
	}

	//Bools
	public void amIOnUrl(String url) {
		String currentUrl = _driver.getCurrentUrl();
		try {
			Assert.assertEquals(currentUrl, url);
			report.pass("The URL is correct");
		}
		catch (AssertionError ext) {
			try {
				Assert.assertTrue(currentUrl.startsWith(url));
			}
			catch (AssertionError ext1) {
				report.fail("Expected url: " + url + ". Landed to " + currentUrl);
			}
		}
		finally {
			report.info("Landed to " + currentUrl);
		}
	}


}
