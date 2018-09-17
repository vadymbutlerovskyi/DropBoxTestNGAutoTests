package pages;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import tests.BaseTest;

public class BasePage {
	
	public BaseTest baseTest;
	public LoginLogoutPage loginLogout;
	public static UploadFilePage upload;
	
	@BeforeTest
	public void testSetup(){
		baseTest = new BaseTest();
		loginLogout = new LoginLogoutPage(baseTest._driver);
		upload = new UploadFilePage(baseTest._driver);
		System.out.println("Inside Before Class");
	}
	
	@AfterTest
	public void cleanUp() {
		System.out.println("Inside After Class");
		baseTest.tearDown();
	}
	
	
}
