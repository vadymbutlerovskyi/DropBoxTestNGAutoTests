package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import tests.BaseTest;

public class UploadFilePage extends BaseTest {

	public UploadFilePage(WebDriver driver) {
		//this._driver = driver;
		super(driver);
		PageFactory.initElements(driver,  this);
	}

	//*******Page Objects*******	
	@FindBy(how = How.XPATH, using = "//input[@type='file' and @style='display: none;']")
	public WebElement uploadArea;
	
	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	public WebElement passField;

	//*******Actions*******	
	public void uploadFile(String filePath) {
		uploadArea.sendKeys(filePath);
	}

	public void enterIntoPassword(String pass) {
		passField.sendKeys(pass);
	}
	
	//*******Waiters*******
	public void waitForRecentFiles() {
		WebDriverWait wait = new WebDriverWait(_driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='recents-list']/li")));
	}
	
	
}
