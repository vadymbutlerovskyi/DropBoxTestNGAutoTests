package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.LoginLogoutPage;
import pages.UploadFilePage;

public class CreateNewFolderTests extends BasePage {
	
	@Parameters("folderName")
	@Test	
	public void createNewFolder (String folderName) {
		BasePage.folder.goToFiles();
		BasePage.folder.createNewFolder();
		BasePage.folder.inputNewFolderName(folderName);
		BasePage.folder.waitForNewFolder(folderName);
		//Since the new folder is always listed in the first row once created
		String nameFromFilesTableFirstRow = BasePage.folder.getNameFromFilesTableFirstRow();
		Assert.assertEquals(nameFromFilesTableFirstRow, folderName, 
				"The element on the first name did not match created folder. Given: " + folderName
				+ ". Found: " + nameFromFilesTableFirstRow);
	}
	
	
}