package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import tests.BaseTest;

public class CreateNewFolderPage extends BaseTest {

	public CreateNewFolderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,  this);
	}

	//*******Page Objects*******	
	@FindBy(how = How.ID, using = "files")
	private WebElement filesLeftSideLink;
		
	@FindBy(how = How.XPATH, using = "//div[@class='appactions-menu']//button[contains(@class,'action-new-folder')]")
	private WebElement createNewFolderBtn;

	@FindBy(how = How.XPATH, using = "//input[@class='c-input']")
	private WebElement inputNewFolderName;
	
	@FindBy(how = How.XPATH, using = "//tbody[contains(@class,'mc-table-body')]/tr[@aria-rowindex='1']//div[@class='brws-file-name-element']/span")
	private WebElement firstRowFilesTable;
	

	
	//*******Actions*******	
	public void goToFiles() {
		filesLeftSideLink.click();
	}
	
	public void createNewFolder() {
		createNewFolderBtn.click();
	}
	
	public void inputNewFolderName(String folderName) {
		inputNewFolderName.sendKeys(folderName + Keys.ENTER);
	}

	
	//*******Gets*******
		public String getNameFromFilesTableFirstRow() {
		return firstRowFilesTable.getText();
	}
	
	//*******Waiters*******
	public void waitForNewFolder(String folderName) {
		WebDriverWait wait = new WebDriverWait(_driver, 5);
		Boolean element = wait.until(ExpectedConditions.textToBePresentInElement(firstRowFilesTable, folderName));
	}
}
