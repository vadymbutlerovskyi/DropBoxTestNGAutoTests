package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;

public class UploadFileTests extends BasePage {

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
	}
}