package page;

import org.apache.log4j.Logger;

import framework.BasePage;
import framework.api.IWDriver;
import framework.or.OrStructure;
import framework.or.Structure;
import utility.ConfigManager;
import utility.LogUtil;

public class LoginPage extends BasePage{
	
	private static final Logger log = LogUtil.getLogger(LoginPage.class);
	
	public LoginPage(IWDriver iDriver) {
		super(iDriver);
		super.initOR(this);
	}
	
	
	


	@Structure(name = "lblUsername")
	private OrStructure lblUsername;
	
	@Structure(name = "lblPassword")
	private OrStructure lblPassword;
	
	@Structure(name = "txtUsername")
	private OrStructure txtUsername;
	
	@Structure(name = "txtPassword")
	private OrStructure txtPassword;
	
	@Structure(name = "btnSubmit")
	private OrStructure btnSubmit;
	
	
	
	public void login() {
		iDriver.info("Login process initiated.", false);
//		String username = elem.getText(lblUsername);
		String username = ConfigManager.getProps().getProperty("ORANGEHRM.USER");
//		String password = elem.getText(lblPassword);
		String password = ConfigManager.getProps().getProperty("ORANGEHRM.PASSWORD");
		iDriver.info("login user: ["+username.substring(11)+"]", false);
		elem.sendKeys(txtUsername, username.substring(11));
		elem.sendKeys(txtPassword, password.substring(11));
		elem.click(btnSubmit);
		iDriver.waitForPageLoad();
	}

	
	
	public OrStructure getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(OrStructure txtUsername) {
		this.txtUsername = txtUsername;
	}
	
}
