package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.LoginLogoutPage;
import pages.UploadFilePage;

public class UploadFileTests extends BasePage {

//	@Parameters("password")
//	@Test
//	public void testAction(String pass) {
//		
//		
//		
//		
////		UploadFilePage loginLogout = new UploadFilePage(_driver);
////		loginLogout.uploadFile("sdfsdf");
//		
//	}
	
	
	@Parameters({"filePath", "fileName", "folderName"})
	@Test	
	public void uploadFile (String filePath, String fileName, String folderName) {
		BasePage.upload.waitForUploadZone();
		String fileFullPath = System.getProperty("user.dir") + filePath + fileName;
		BasePage.upload.uploadFile(fileFullPath);
		BasePage.upload.waitForFoldersOnUploadDestinationModal();
		BasePage.upload.selectUploadFolder(folderName);
		BasePage.upload.hitUploadButton();
		BasePage.upload.waitForFileUpload();
		BasePage.upload.waitForFileToBeProcessed();
		
		
		//UploadFilePage upload = page.GetInstance(UploadFilePage.class);
		
		//upload.uploadFile(fileFullPath);
	}
}