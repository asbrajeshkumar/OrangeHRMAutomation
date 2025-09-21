package script;

import org.testng.annotations.Test;

import framework.BaseTest;
import framework.api.IWDriver;
import framework.launcher.LaunchDriver;
import page.DashboardPage;
import page.LoginPage;

public class LoginTest extends BaseTest{

	
	
	@Test(description = "login to OrangeHRM application with a valid user", priority = 1, groups = {"login"}, enabled = true)
	public void validUserLoginTest() throws Exception {
		IWDriver driver = LaunchDriver.preparePrerequisites();
		LoginPage login = new LoginPage(driver);
		login.login();
		
		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.validateUserLogin();
		
	}
	
	
}
