package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import tests.BaseTest;

public class LoginLogoutPage extends BaseTest {

	public LoginLogoutPage(WebDriver driver, ExtentTest test){
		super(driver, test);
		PageFactory.initElements(driver,  this);
	}

	//*******Page Objects*******	
	@FindBy(how = How.XPATH, using = "//div[@class='clearfix']//button[@type='submit']")
	private WebElement signIn;

	@FindBy(how = How.XPATH, using = "//input[@type='email']")
	private WebElement emailField;

	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	private WebElement passField;

	@FindBy(how = How.XPATH, using = "//ul[@class='recents-list']/li")
	private WebElement recentFiles;

	@FindBy(how = How.XPATH, using = "//img[@class='mc-avatar-image']")
	private WebElement avatarImg;

	@FindBy(how = How.XPATH, using = "//a[contains(@href,'logout') or contains(text(),'Sign out')]")
	private WebElement signOutBtn;

	//*******Actions*******	
	public void clickOnSignIn() {
		signIn.click();
		report.pass("Clicked on the Sign In button");
	}

	public void clickOnSignOut() {
		signOutBtn.click();
		report.pass("Clicked on the Sign Out button");
	}

	public void enterIntoEmail(String email) {
		emailField.sendKeys(email);
		report.pass(email + " was entered into email field");
	}

	public void enterIntoPassword(String pass) {
		passField.sendKeys(pass);
		report.pass(pass + " was entered into password field");
	}

	public void clickOnAvatarImg() {
		avatarImg.click();
		report.pass("Clicked on the Avatar");
	}

	//*******Waiters*******
	public void waitForRecentFilesOnHomePage() {
		Integer interval = 10;
		try {
			WebDriverWait wait = new WebDriverWait(_driver, interval);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(recentFiles));
			report.info("Recent files are loaded");
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			report.fail("Recent files were not loaded within " + interval + " secs because of timeout exeption");
		}
	}

	public void waitForAvatar() {
		Integer interval = 5;
		try {
			WebDriverWait wait = new WebDriverWait(_driver, interval);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(avatarImg));
			report.info("Avatar is loaded");
		}
		catch (org.openqa.selenium.TimeoutException exp) {
			report.fail("Avatar was not loaded within " + interval + " secs because of timeout exeption");
		}
	}
}
