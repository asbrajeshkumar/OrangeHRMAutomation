package framework.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import framework.BaseTest;
import framework.extentReport.ExtentConfig;
import framework.extentReport.ExtentManager;
import utility.ConfigManager;
import utility.Constants;
import utility.LogUtil;

public class TestListener extends BaseTest implements ITestListener {
	private static final Logger log = LogUtil.getLogger(TestListener.class);
	
	
	@Override
	public void onStart(ITestContext oContext) {
		log.info("Execution started");
		Constants.sInitDate =(new SimpleDateFormat("yyyy/MM/dd")).format(new Date()).replaceAll("/", "");
		Constants.sInitTime = (new SimpleDateFormat("HH:mm:ss")).format(new Date()).replaceAll(":", "");
		new ConfigManager();
		new ExtentManager();
	}
	
	 @Override
	  public void onTestStart(ITestResult iTestResult) {
	    ExtentManager.startExtentTest(iTestResult.getMethod().getDescription());
	    
	    
	  }
	 
	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.extentReport.flush();
	}
	
	@Override
	public void  onTestSuccess(ITestResult result) {
		ExtentManager.getExtentTest().log(Status.PASS, "Test case passed.");
	}
	
	@Override
	public void  onTestFailure(ITestResult result) {
		ExtentManager.getExtentTest().log(Status.FAIL, "Test case failed.");
	}
}
