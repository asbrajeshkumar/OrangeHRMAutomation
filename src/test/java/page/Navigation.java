package page;

import org.apache.log4j.Logger;

import framework.BasePage;
import framework.api.IWDriver;
import framework.or.OrStructure;
import framework.or.Structure;
import utility.LogUtil;

public class Navigation extends BasePage{
	private static final Logger log = LogUtil.getLogger(LoginPage.class);

	public Navigation(IWDriver iDriver) {
		super(iDriver);
		super.initOR(this);
	}
	
	
	@Structure(name = "btnProfileIcon")
	private OrStructure btnProfileIcon;
	
	@Structure(name = "btnLogout")
	private OrStructure btnLogout;
	
	public void logout() {
		log.info("Logging out");
		LoginPage login = new LoginPage(iDriver);
		try {
			elem.click(btnProfileIcon);
			elem.click(btnLogout);
			
			if(elem.isAvailableOnPage(login.getTxtUsername(), 10)) {
				iDriver.pass("User logout successfull", false);
			}else {
				iDriver.fail("User logout failed", true);
			}
		} catch (Exception e) {
			iDriver.fail("Failed while logout", true);
			e.printStackTrace();
		}
	}
	
}
