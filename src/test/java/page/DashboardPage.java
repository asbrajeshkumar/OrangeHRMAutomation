package page;

import org.apache.log4j.Logger;

import framework.BasePage;
import framework.api.IWDriver;
import framework.or.OrStructure;
import framework.or.Structure;
import utility.LogUtil;

public class DashboardPage extends BasePage{
	
private static final Logger log = LogUtil.getLogger(LoginPage.class);
	
	public DashboardPage(IWDriver iDriver) {
		super(iDriver);
		super.initOR(this);
	}
	
	
	@Structure(name = "lblDashboard")
	private OrStructure lblDashboard;
	
	
	public void validateUserLogin() throws Exception {
		if(elem.isAvailableOnPage(lblDashboard, 15)) {
			iDriver.pass("User logged in successfully", false);
		}else {
			iDriver.pass("User login failed", true);
		}
	}
	
}
