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
	public static CreateNewFolderPage folder;
	public static SearchAndDeleteFilePage deleteSought;
	
	@BeforeTest
	public void testSetup(){
		baseTest = new BaseTest();
		loginLogout = new LoginLogoutPage(baseTest._driver);
		upload = new UploadFilePage(baseTest._driver);
		folder = new CreateNewFolderPage(baseTest._driver);
		deleteSought = new SearchAndDeleteFilePage(baseTest._driver);
	}
	
	@AfterTest
	public void cleanUp() {
		baseTest.tearDown();
	}
	
	
}
