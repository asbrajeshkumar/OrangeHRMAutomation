package script;

import org.testng.annotations.Test;

import framework.BaseTest;
import framework.api.IWDriver;
import framework.launcher.LaunchDriver;
import page.AdminPage;
import page.LoginPage;
import page.Navigation;

public class CreateUserTest extends BaseTest{

	@Test(description = "login to OrangeHRM application", priority = 1, groups = {"login"}, enabled = true)
	public void createAdminUser() throws Exception {
		IWDriver driver = LaunchDriver.preparePrerequisites();
		LoginPage login = new LoginPage(driver);
		login.login();
		
		AdminPage admin = new AdminPage(driver);
		admin.addAdminUser();
		
		Navigation nav = new Navigation(driver);
		nav.logout();
	}
}
