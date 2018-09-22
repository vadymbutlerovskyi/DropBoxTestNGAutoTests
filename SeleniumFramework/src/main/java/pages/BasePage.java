package pages;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import tests.BaseTest;

public class BasePage {
	
	ITestContext testContext = null;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	public static ExtentTest reportLogInOut;
	public static ExtentTest reportUpload;
	public static ExtentTest reportNewFolder;
	public static ExtentTest reportSearchDelete;
	public BaseTest baseTest;
	public static LoginLogoutPage loginLogout;
	public static UploadFilePage upload;
	public static CreateNewFolderPage folder;
	public static SearchAndDeleteFilePage deleteSought;

	@BeforeSuite
	public void extentReportSetup() {
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		reportLogInOut = extent.createTest("Log in and out", "These are login and logout tests");
		reportUpload = extent.createTest("Upload file", "These are tests for upload file functionality");
		reportNewFolder = extent.createTest("Create new folder", "These are tests for create new folder functionality");
		reportSearchDelete = extent.createTest("Search and delete", 
				"These are tests for search and delete a specific item. Undo delete test is also available. Exclude it in testng.xml if not needed.");
	}
	
	@BeforeTest
	public void testSetup(final ITestContext testContext){
		baseTest = new BaseTest();
		String currentTestName = testContext.getName();
		switch (currentTestName) {
			case "LogInLogOutTests":
				baseTest.report = reportLogInOut;
				break;
			case "UploadFileTest":
				baseTest.report = reportUpload;
				break;
			case "CreateFolderTest":
				baseTest.report = reportNewFolder;
				break;
			case "SearchAndDelete":
				baseTest.report = reportSearchDelete;
				break;
			default:
				System.out.println("HINT: Double check test names correspond to cases at @BeforeTest");
		}		
		loginLogout = new LoginLogoutPage(baseTest._driver, baseTest.report);
		upload = new UploadFilePage(baseTest._driver, baseTest.report);
		folder = new CreateNewFolderPage(baseTest._driver, baseTest.report);
		deleteSought = new SearchAndDeleteFilePage(baseTest._driver, baseTest.report);
	}

	@AfterTest
	public void cleanUp() {
		baseTest.tearDown();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
}
