package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

public class SearchAndDeleteFilePage extends BaseTest {

	public SearchAndDeleteFilePage(WebDriver driver, ExtentTest test){
		super(driver, test);
		PageFactory.initElements(driver,  this);
	}

	//*******Page Objects*******	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='Search']")
	private WebElement searchInput;

	@FindBy(how = How.XPATH, using = "//tbody[contains(@class,'mc-table-body')]/tr[@aria-rowindex='1']//div[@class='brws-file-name-element']/span")
	private WebElement firstRowFilesTable;

	@FindBy(how = How.XPATH, using = "//tbody[contains(@class,'mc-table-body')]/tr[@class!='mc-table-row mc-media-row mc-media-row-border mc-media-row-clickable mc-media-row-culled brws-file-row brws-file-row-deleted']")
	private List<WebElement> rowsInResultTableExcDel;

	@FindBy(how = How.XPATH, using = "//tbody[contains(@class,'mc-table-body')]/tr")
	private List<WebElement> rowsInResultTableInclDel;

	@FindBy(how = How.XPATH, using = "//button[@class='action-delete mc-popover-content-item']")
	private WebElement deleteBtnActionPanel;

	@FindBy(how = How.XPATH, using = "//div[@class='mc-util-modal-actions']//button[contains(@class,'primary')]/span[@class='mc-button-content' or text()='Delete']")
	private WebElement deleteBtnModal;

	@FindBy(how = How.XPATH, using = "//span[@id='notify-msg']//span")
	private WebElement confirmMsgDel;
	
	@FindBy(how = How.XPATH, using = "//span[@id='notify-msg']")
	private WebElement confirmMsgUndo;

	@FindBy(how = How.XPATH, using = "//h3[@class='u-font-strong empty-folder-table-title']")
	private WebElement noResultsFoundMsg;

	@FindBy(how = How.XPATH, using = "//span[@id='notify-msg']//span/button")
	private WebElement undoBtn;

	//*******Actions*******	
	public void inputIntoSearchField(String fileName) {
		searchInput.sendKeys(fileName + Keys.ENTER);
		report.pass(fileName + " was typed into the search field");
	}

	public void hitDeleteButtonOnActionPanel() {
		deleteBtnActionPanel.click();
		report.pass("Delete button on the Action Panel was hit");
	}

	public void hitDeleteButtonOnModal() {
		deleteBtnModal.click();
		report.pass("Delete button on the Confirmation Modal was hit");
	}

	public void hitUndoButton() {
		undoBtn.click();
		report.pass("Undo button was clicked");
	}

	public void triggerActionMenuInResultTableForElement(String element) {
		String xpath;
		Integer pos;	
		for (pos = 1; pos<=rowsInResultTableExcDel.size(); pos++) {
			xpath = String.format("//tbody[contains(@class,'mc-table-body')]/tr[@aria-rowindex='%s']//div[@class='brws-file-name-element']/span/span", pos.toString());
			String currentTextRow = _driver.findElement(By.xpath(xpath)).getText();
			/*The following "if" is needed because of Dropbox algorithms. When creating a folder with duplicated name, it adds "([index])" in the end. 
			However, if you search for folder "TestFolder" and there are "TestFolder" and "TestFolder (1)",
			the "TestFolder (1)" may be removed because the name consists of 3 spans, where the first one is always TestFolder for both test folders
			and "TestFolder (1) will have additional two spans with texts "(" and "1)". So the script will skip this. and remove exactly "TestFolder"
			if such passed as argument.*/
			if (_driver.findElements(By.xpath(xpath)).size() > 1) {
				report.info("Detected duplicated folder with ordinal number in the end. Skipped");
			}
			else {
				if (currentTextRow.equals(element)) {
					xpath = String.format("//tbody[contains(@class,'mc-table-body')]/tr[%s]//div[@class='browse-overflow-menu']", pos.toString());
					_driver.findElement(By.xpath(xpath)).click();
					report.pass(element + " was found in row " + pos + " and Action Menu was invoked from there");
				}
				else {
					String err = element + " was not found in the table of search results";
					report.fail(err);
					throw new ElementNotInteractableException(err);
				}
			}
		}		
	}

	//*******Booleans*******
	public String isConfirmationMessageShown(String message, String type) {
		String msg = "";
		switch (type) {
		case "Delete":
			if (confirmMsgDel.isDisplayed() == true) {
				msg = confirmMsgDel.getText();
				report.info("Delete confirmation message is displayed with text " + msg);
			}
			else {
				report.fail("Delete message was not shown");
			}
		case "Undo":
			if (confirmMsgUndo.isDisplayed() == true) {
				msg = confirmMsgUndo.getText();
				report.info("Undo confirmation message is displayed with text " + msg);
			}
			else {
				report.fail("Undo message was not shown");
			}
		}
		return msg;
	}

	public String isNoResultMessageShown(String message) {
		try {
			if (rowsInResultTableInclDel.size() >= 1) {
				report.info("OK. Message for no result wasn't shown as there were additional files fetched by search and they are still there");
				return message;
			}
			else {
				String msg = noResultsFoundMsg.getText();
				report.info(msg + " was found after removal of the only item in the result list");
				return msg;
			}
		}
		catch (NoSuchElementException exp) {
			report.fail("The message for No Results wasn't shown and there were no other results listed");
			return "";
		}
	}

	public Boolean isSoughtElementShown(String element) {
		Boolean result = false;
		String xpath;
		Integer pos;	
		for (pos = 1; pos<=rowsInResultTableInclDel.size(); pos++) {
			xpath = String.format("//tbody[contains(@class,'mc-table-body')]/tr[@aria-rowindex='%s']//div[@class='brws-file-name-element']/span/span", pos.toString());
			String currentTextRow = _driver.findElement(By.xpath(xpath)).getText();
			/*The following "if" is needed because of Dropbox algorithms. When creating a folder with duplicated name, it adds "([index])" in the end. 
			However, if you search for folder "TestFolder" and there are "TestFolder" and "TestFolder (1)",
			the "TestFolder (1)" may be removed because the name consists of 3 spans, where the first one is always TestFolder for both test folders
			and "TestFolder (1) will have additional two spans with texts "(" and "1)". So the script will skip this. and remove exactly "TestFolder"
			if such passed as argument.*/
			if (_driver.findElements(By.xpath(xpath)).size() > 1) {
				report.info("Detected duplicated folder with ordinal number in the end. Skipped");
			}
			else {
				if (currentTextRow.equals(element)) {
					report.pass(element + " was found in the search");
					result = true;
				}
				else {
					report.info("The sought element was not found displayed");
					result = false;
				}
			}
		}
		return result;
	}

	//*******Waiters*******
	Wait<WebDriver> wait = new FluentWait<WebDriver>(_driver).withTimeout(Duration.ofMinutes(1))
			.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

	public void waitForSearchResult() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOf(firstRowFilesTable));
			report.pass("Search results were displayed");
		}
		catch (TimeoutException exp) {
			report.fail("The sought element was not found displayed");
		}
	}

	public void waitForConfirmationMessage(String type) {
		WebElement element;
		try {
			switch(type) {
			case "Delete":
				element = wait.until(ExpectedConditions.visibilityOf(confirmMsgDel));
				break;
			case "Undo":
				element = wait.until(ExpectedConditions.visibilityOf(confirmMsgUndo));
				break;
			}
			report.pass("The confirmation message was shown");
		}
		catch (TimeoutException exp) {
			
			report.fail("The confirmation message was not shown");
		}
	}

	public void waitForDeleteButtonOnModal() {
		Integer interval = 5;
		try {
			WebDriverWait wait = new WebDriverWait(_driver, interval);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(deleteBtnModal));
			report.pass("The delete button on modal was shown within " + interval + " seconds");
		}
		catch (TimeoutException exp) {
			report.fail("The delete button on modal was not shown " + interval + " seconds");
		}
	}
}
