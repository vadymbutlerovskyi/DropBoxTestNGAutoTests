package pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import tests.BaseTest;

public class CreateNewFolderPage extends BaseTest {

	public CreateNewFolderPage(WebDriver driver, ExtentTest test){
		super(driver, test);
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
		report.pass("Clicked on Files link on left side bar");
	}

	public void createNewFolder() {
		createNewFolderBtn.click();
		report.pass("Clicked on the New Folder button");
	}

	public void inputNewFolderName(String folderName) {
		inputNewFolderName.sendKeys(folderName + Keys.ENTER);
		report.pass(folderName + " was entered as name for new folder");
	}

	//*******Gets*******
	public String getNameFromFilesTableFirstRow() {
		String firstRowName = firstRowFilesTable.getText();
		report.info(firstRowName + " name was found at the first row");
		return firstRowName;	
	}

	//*******Waiters*******
	public void waitForNewFolder(String folderName) {
		Integer interval = 5;
		try {	
			WebDriverWait wait = new WebDriverWait(_driver, interval);
			Boolean element = wait.until(ExpectedConditions.textToBePresentInElement(firstRowFilesTable, folderName));
			report.info("New folder was created");
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			report.fail("New folder failed to show up within " + interval + " seconds");
		}
	}
}
