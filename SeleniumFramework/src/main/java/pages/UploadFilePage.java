package pages;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import tests.BaseTest;

public class UploadFilePage extends BaseTest {

	public UploadFilePage(WebDriver driver) {
		//this._driver = driver;
		super(driver);
		PageFactory.initElements(driver,  this);
	}

	//*******Page Objects*******	
	By uploadAreaBy = By.xpath("//div[@id='flash-upload-container']/div/input");
	private WebElement uploadArea;
		
	@FindBy(how = How.XPATH, using = "//span[@class='tree-view__loading-label']")
	private WebElement loadingSpinnerFolders;
	
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
	}
	
	public void selectUploadFolder(String folderName) {
		//This is in case if folder was not yet created. Selected will be default Dropbox directory
		try {
			xpathSelectFolderUploadTo = String.format("//div[@role='tree']//li[@aria-label='%s']", folderName);
			uploadToFolder = _driver.findElement(By.xpath(xpathSelectFolderUploadTo));
			uploadToFolder.click();
		}
		catch (NoSuchElementException exp){
			System.out.println("The file will be uploaded to the default directory");
			dropBoxUploadDir = _driver.findElement(dropBoxUploadDirBy);
			dropBoxUploadDir.click();
		}		
	}
	
	public void hitUploadButton() {
		uploadBtn.click();
	}
	
	//*******Waiters*******
	Wait<WebDriver> wait = new FluentWait<WebDriver>(_driver).withTimeout(Duration.ofMinutes(1))
	        .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
	
	public void waitForFoldersOnUploadDestinationModal() {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, 5);
			Boolean element = wait.until(ExpectedConditions.invisibilityOf(loadingSpinnerFolders));
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='tree-view__group']//ul/li/span/span")));
		}
	}
	
	public void waitForFileUpload() {
		WebElement element = wait.until(ExpectedConditions.visibilityOf(progressBarSuccess));
	}
	
	public void waitForFileToBeProcessed() {
		WebElement element = wait.until(ExpectedConditions.visibilityOf(uploadedLogo));
	}
	
	public void waitForUploadZone() {
		WebDriverWait wait = new WebDriverWait(_driver, 5);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(uploadAreaBy));
	}
}
