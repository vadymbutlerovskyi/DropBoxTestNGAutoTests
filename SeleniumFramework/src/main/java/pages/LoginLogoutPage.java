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

public class LoginLogoutPage extends BaseTest {

	public LoginLogoutPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,  this);
	}
//
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
	}
	
	public void clickOnSignOut() {
		signOutBtn.click();
	}
		
	public void enterIntoEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterIntoPassword(String pass) {
		passField.sendKeys(pass);
	}
		
	public void clickOnAvatarImg() {
		avatarImg.click();
	}

	//*******Waiters*******
	public void waitForRecentFilesOnHomePage() {
		WebDriverWait wait = new WebDriverWait(_driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(recentFiles));
	}
	
	public void waitForAvatar() {
		WebDriverWait wait = new WebDriverWait(_driver, 5);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(avatarImg));
	}
}
