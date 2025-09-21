package framework.extentReport;

import java.io.File;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.Constants;
import utility.LogUtil;

public class ExtentConfig {
	private static final Logger log = LogUtil.getLogger(ExtentConfig.class);
	private static ExtentReports extentReport;
	
	public static synchronized ExtentReports createExtentReport() {
		if (extentReport == null) {
			log.info("Creating extent report.");
			String reportDir = System.getProperty("user.dir") + "/report/";
			new File(reportDir).mkdirs();
			ExtentSparkReporter reporter = new ExtentSparkReporter("report/"+Constants.sInitDate+"/ExecutionResult_"+Constants.sInitTime+".html");
			String spath = reporter.getFile().getAbsolutePath();
			log.info("Extent report path :"+ spath);
			reporter.config().setReportName("Test Automation Result");
			reporter.config().setDocumentTitle("Automation Result");
			reporter.config().setTheme(Theme.DARK);

			extentReport = new ExtentReports();
			extentReport.attachReporter(reporter);
			extentReport.setSystemInfo("Tester", System.getProperty("user.name"));
			extentReport.setSystemInfo("OS", System.getProperty("os.name"));
			extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		return extentReport;
	}

	public static ExtentReports getExtentReport() {
		return extentReport;
	}
}
