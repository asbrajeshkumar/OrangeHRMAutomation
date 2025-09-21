package framework.launcher;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestContext;
import org.testng.Reporter;

import framework.api.IWDriver;
import framework.api.ToolApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConfigManager;
import utility.LogUtil;

public class LaunchDriver {
	private static final Logger log = LogUtil.getLogger(LaunchDriver.class);
	
	public static IWDriver preparePrerequisites() {
		log.info("Inside preparePrerequisites");
		String BROWSER = ConfigManager.getProps().getProperty("TARGET.BROWSER");
		String sURL = ConfigManager.getProps().getProperty("TARGET.URL");
		IWDriver iWDriver = getDriver(BROWSER);
		try {
			iWDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			iWDriver.getDriver().get(sURL);
		} catch (Exception e) {
			log.fatal("Caught exception in preparePrerequisites");
			e.printStackTrace();
		}

		return iWDriver;
	}
	
	private static IWDriver getDriver(String BROWSER) {
		WebDriver driver = null;
		IWDriver iDriver = null;
		ITestContext oIContext = Reporter.getCurrentTestResult().getTestContext();
		log.info("Inside getDriver");
		try {
			driver = getConfiguredBrowserInstance(BROWSER);
			iDriver = ToolApi.getDriver(driver);
			oIContext.setAttribute(getDriverContextCode(), iDriver);
			
		} catch (Exception e) {
			log.fatal("Caught exception in getDriver");
			e.printStackTrace();
		}
		return iDriver;
	}

	private static WebDriver getConfiguredBrowserInstance(String BROWSER) {
		log.info("Inside getConfiguredBrowserInstance");
		WebDriver driver = null;
		switch(BROWSER.toUpperCase()) {
		case "CHROME":
			driver = getChromeDriver();
			break;
		case "EDGE":
			driver = getEdgeDriver();
			break;
		default:
			driver = getChromeDriver();
			break;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver getChromeDriver() {
		WebDriver driver = null;
		log.info("Inside getChromeDriver");
		try {
			Thread.sleep(1000);
			ChromeOptions options = new ChromeOptions();
			options.setCapability("acceptInsecureCerts", true);
			options.addArguments("--disable-extensions");
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}catch(Exception e) {
			log.fatal("Caught exception in getChromeDriver");
		}
		return driver;
	}
	
	private static WebDriver getEdgeDriver() {
		WebDriver driver = null;
		log.info("Inside getEdgeDriver");
		try {
			Thread.sleep(1000);
			EdgeOptions options = new EdgeOptions();
			options.setCapability("acceptInsecureCerts", true);
			options.addArguments("--disable-extensions");
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}catch(Exception e) {
			log.fatal("Caught exception in getEdgeDriver");
		}
		return driver;
	}
	
	public static String getDriverContextCode() {
		String sThreadId = "" + Thread.currentThread().getId();
		return "Driver_" + sThreadId;
	}
	
	
	
}
