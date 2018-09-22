package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;

public class LoginLogoutTests extends BasePage {
		
	@Parameters({"login", "home", "email", "password"})
	@Test	
	public void logIn (String login, String home, String email, String password) {

		baseTest.goToUrl(login);
		baseTest.waitForPageLoaded(5);
		loginLogout.enterIntoEmail(email);
		loginLogout.enterIntoPassword(password);	
		loginLogout.clickOnSignIn();		
		loginLogout.waitForRecentFilesOnHomePage();
		loginLogout.waitForAvatar();
		baseTest.amIOnUrl(home);
	}
	
	@Parameters ("login")
	@Test
	public void logOut(String login) {
		loginLogout.clickOnAvatarImg();
		loginLogout.clickOnSignOut();
		baseTest.waitForPageLoaded(5);
		baseTest.amIOnUrl(login);
	}
}
