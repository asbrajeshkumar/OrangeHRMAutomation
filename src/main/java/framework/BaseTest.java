package framework;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import framework.api.IWDriver;
import framework.extentReport.ExtentManager;

public class BaseTest {

	@BeforeMethod(alwaysRun = true)
	protected synchronized void beforeMethod(ITestContext iContext) {;
		
	}
	
	@AfterMethod(alwaysRun = true)
	protected synchronized void afterMethod(ITestContext iContext) {
		 try {
		      if (null != getDriver()) {
		        getDriver().closeDriver();
		      }
		      ExtentManager.extentReport.flush();
		    } catch (Exception e) {
		    }
	}
	
	
	public static IWDriver getDriver(ITestContext oContext) {
	    return (IWDriver) oContext.getAttribute("Driver_" +"" + Thread.currentThread().getId());
	  }
	
	public IWDriver getDriver() {
	    ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
	    return getDriver(itestContext);
	  }
	
	
}
