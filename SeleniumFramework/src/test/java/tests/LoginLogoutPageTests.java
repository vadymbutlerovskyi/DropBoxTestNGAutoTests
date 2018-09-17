package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.LoginLogoutPage;
import pages.UploadFilePage;

public class LoginLogoutPageTests extends BasePage {
	
//	public LoginLogoutPageTests(WebDriver driver) {
//		super(driver);
//	}
	
	@Parameters({"login", "home", "email", "password"})
	@Test	
	public void logIn (String login, String home, String email, String password) {
//		BasePage test = new BasePage();
//		test.testSetup();
		baseTest.goToUrl(login);
		//LoginLogoutPage loginLogout = new LoginLogoutPage(_driver);
		loginLogout.enterIntoEmail(email);
//		loginLogout = page.GetInstance(LoginLogoutPage.class);
//		goToUrl(login);
//		waitForPageLoaded(5);
//		loginLogout.enterIntoEmail(email);
//		test.pass(email + " was entered into email field");
//		loginLogout.enterIntoPassword(password);
//		test.pass(password + " was entered into password field");		
//		loginLogout.clickOnSignIn();
//		test.pass("Clicked on the Sign In button");
//		loginLogout.waitForRecentFiles();
//		test.info("Recent files are loaded");
//		loginLogout.waitForAvatar();
//		test.info("Avatar is loaded");
//		amIOnUrl(home);
//		test.pass("Successfully signed in");
	}
	
	@Parameters ("password")
//	@Test (priority = 1)
	@Test
	public void testAction(String pass) {
		upload.enterIntoPassword(pass);
	}
//	public void logOut (String login) {
//		LoginLogoutPage loginLogout;
//		loginLogout = page.GetInstance(LoginLogoutPage.class);
//		loginLogout.clickOnAvatarImg();
//		test.pass("Clicked on the Avatar");
//		loginLogout.clickOnSignOut();
//		test.pass("Clicked on the Sign Out button");
//		waitForPageLoaded(5);
//		amIOnUrl(login);
//		test.pass("Successfully signed out");
//	}
}
