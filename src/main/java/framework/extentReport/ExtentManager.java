package framework.extentReport;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
	public static ExtentReports extentReport;
	public static ExtentReports getExtentReport() {
		return extentReport;
	}

	public static ExtentTest extentTest;
	public static Map<String, ExtentTest>extentTestMap = new HashMap<>();

	static{
		if (extentReport == null) {
			extentReport = ExtentConfig.createExtentReport();
		}
	}

	public static synchronized ExtentTest getExtentTest() {
		ITestResult iTestResult = Reporter.getCurrentTestResult();
		String sMethod = iTestResult.getMethod().getMethodName();
		String sThreadID = ""+Thread.currentThread().getId();
		return extentTestMap.get(sMethod+sThreadID);
	}
	
	public static synchronized ExtentTest startExtentTest(String sDescription) {
		ITestResult iTestResult = Reporter.getCurrentTestResult();
		String sMethod = iTestResult.getMethod().getMethodName();
		String sThreadID = ""+Thread.currentThread().getId();
		
		extentTest = extentReport.createTest(sMethod, sDescription);
		ITestContext iTestContext = Reporter.getCurrentTestResult().getTestContext();
		iTestContext.setAttribute("ExtentTest"+sThreadID, extentTest);
		extentTestMap.put(sMethod+sThreadID, extentTest);
		return extentTest;
	}
}
