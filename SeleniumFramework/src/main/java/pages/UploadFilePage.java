package pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import tests.BaseTest;

public class UploadFilePage extends BaseTest {

	public UploadFilePage(WebDriver driver, ExtentTest test){
		super(driver, test);
		PageFactory.initElements(driver,  this);
	}

	//*******Page Objects*******	
	By uploadAreaBy = By.xpath("//div[@id='flash-upload-container']/div/input");
	private WebElement uploadArea;

	static String xpathSelectFolderUploadTo;
	private WebElement uploadToFolder;

	@FindBy(how = How.XPATH, using = "//button[contains(@class,'primary')]/span[@class='mc-button-content' or text()='Upload']")
	private WebElement uploadBtn;

	@FindBy(how = How.XPATH, using = "//div[@class='mc-progress-bar-fg' and @style='width: 100%;']")
	private WebElement progressBarSuccess;

	@FindBy(how = How.XPATH, using = "//div[@class='mc-snackbar-icon']/*[name() = \"svg\"]/*[name() = \"g\"]/*[name() = \"path\" and @fill = \"#3DCC5E\"]")
	private WebElement uploadedLogo;

	By dropBoxUploadDirBy = By.xpath("//div[@role='tree']//li[contains(@aria-label,'Dropbox')]");
	private WebElement dropBoxUploadDir;

	//*******Actions*******	
	public void uploadFile(String filePath) {
		uploadArea = _driver.findElement(uploadAreaBy);
		uploadArea.sendKeys(filePath);
		report.pass("The file for upload was sent from: " + filePath);
	}

	public void selectUploadFolder(String folderName) {
		//In case if folder was not yet created, selected will be default Dropbox directory
		try {
			xpathSelectFolderUploadTo = String.format("//div[@role='tree']//li[@aria-label='%s']", folderName);
			uploadToFolder = _driver.findElement(By.xpath(xpathSelectFolderUploadTo));
			uploadToFolder.click();
			report.pass(folderName + " directory was selected for upload");
		}
		catch (NoSuchElementException exp){
			report.info("The file will be uploaded to the default Dropbox directory");
			dropBoxUploadDir = _driver.findElement(dropBoxUploadDirBy);
			dropBoxUploadDir.click();
			report.pass("Default Dropbox directory was selected");
		}		
	}

	public void hitUploadButton() {
		uploadBtn.click();
		report.pass("Upload button was clicked");
	}

	//*******Waiters*******
	Wait<WebDriver> wait = new FluentWait<WebDriver>(_driver).withTimeout(Duration.ofMinutes(1))
			.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

	public void waitForFoldersOnUploadDestinationModal() {
		wait = new FluentWait<WebDriver>(_driver).withTimeout(Duration.ofSeconds(7))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='tree-view__group']//ul/li/span/span")));
			report.pass("Tree-view with available folders for upload loaded");
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			report.fail("Tree-view with available folders for upload was not loaded");
		}
	}

	public void waitForFileUpload() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOf(progressBarSuccess));
			report.pass("File was successfully uploaded");
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			report.fail("File upload failed");
		}
	}

	public void waitForFileToBeProcessed() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOf(uploadedLogo));
			report.pass("File was processed. Upload has completely finished");
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			report.fail("File was not processed. Upload has been terminated");
		}
	}

	public void waitForUploadZone() {
		Integer interval = 5;
		try {	
			WebDriverWait wait = new WebDriverWait(_driver, interval);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(uploadAreaBy));
			report.info("Upload area is ready to receive a file");
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			report.fail("Upload area failed to load within " + interval + " seconds");
		}
	}
}
