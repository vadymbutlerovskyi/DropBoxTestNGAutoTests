package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import config.PropertiesFile;
import pages.LoginLogoutPage;
import pages.PageGenerator;
import pages.UploadFilePage;

public class BaseTest {	
	
    public WebDriver _driver;
    //public PageGenerator page;
    ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	public ExtentTest test;
	public static String browser;
	static String driverPath;


    
//	@BeforeSuite
//	public void generateReport() {
//		htmlReporter = new ExtentHtmlReporter("extent.html");
//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
//		test = extent.createTest("MyFirstTest", "Sample description");
//	}

	public BaseTest(WebDriver driver)
    {
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
		
		
		//page = new PageGenerator(_driver);	
	}
	
	
	
	
    public void tearDown() {
        _driver.quit();        
    }
	
//	@AfterSuite
//	public void afterSuite() {
//		extent.flush();
//	}
	
	public void enterIntoPassword(String pass) {
		_driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pass);
	}
	
	
	//Actions
	public void goToUrl(String url) {
		_driver.get(url);
		//test.info(url + " was triggered to open");
	}
	
	//Waiters
	public void waitForPageLoaded(Integer timeOut) {
        try {
        	new WebDriverWait(_driver, timeOut).until(
        	          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        	test.pass("The URL was succesfully loaded");
    	} catch (Throwable error) {
    		test.fail("The URL failed to load. Current URL was: " + _driver.getCurrentUrl());
        }
    }
	
	//Bools
	public void amIOnUrl(String url) {
		String currentUrl = _driver.getCurrentUrl();
		try {
			Assert.assertEquals(currentUrl, url);
		}
		catch (AssertionError ext) {
			Assert.assertTrue(currentUrl.startsWith(url));
		}
	}
}
