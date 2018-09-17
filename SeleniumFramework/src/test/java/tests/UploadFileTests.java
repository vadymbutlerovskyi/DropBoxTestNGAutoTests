package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.LoginLogoutPage;
import pages.UploadFilePage;

public class UploadFileTests extends BasePage {

	@Parameters("password")
	@Test
	public void testAction(String pass) {
		System.out.println("Inside testAction");
		
		BasePage.upload.enterIntoPassword(pass);
		
//		UploadFilePage loginLogout = new UploadFilePage(_driver);
//		loginLogout.uploadFile("sdfsdf");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	@Parameters({"filePath", "fileName"})
//	@Test (priority = 1)	
//	public void uploadFile (String filePath, String fileName) {
//		String fileFullPath = System.getProperty("user.dir") + filePath + fileName;
//		driver.findElement(By.xpath("//input[@type='file' and @style='display: none;']")).sendKeys(fileFullPath);
//		
//		
//		//UploadFilePage upload = page.GetInstance(UploadFilePage.class);
//		
//		//upload.uploadFile(fileFullPath);
//	}
}