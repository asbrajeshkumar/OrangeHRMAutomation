package framework.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import framework.extentReport.TestReporter;
import utility.Constants;
import utility.LogUtil;

public class WDriver implements IWDriver{
	private static final Logger log = LogUtil.getLogger(WDriver.class);
	
	private WebDriver driver = null;
	
	public WDriver(WebDriver pDriver) {
		driver = pDriver;
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}
	
	@Override
	public void waitForPageLoad() {
		log.info("Inside waitForPageLoad");
		long istartTime = System.currentTimeMillis();
		
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		try{
			wait.until(pageLoadCondition);
			long intEndTime = System.currentTimeMillis();
			long totalTime = intEndTime - istartTime;
			log.info("Waiting for page load " + (totalTime/1000) + " Seconds");	
		} catch(JavascriptException e) {
			log.info(e.getMessage());
		} catch(StaleElementReferenceException e) {
			log.info(e.getMessage());
		} catch (TimeoutException e) {
			log.info(e.getMessage());
		}
	}
	
	@Override
	public void closeDriver() {
		driver.quit();
	}
	
	
	@Override
	public void captureScreenshot(String fileWithPath) {
		log.info("Capturing screenshot.");
		try {
			TakesScreenshot scrShot = ((TakesScreenshot)driver);
			File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(fileWithPath);
			FileUtils.copyFile(ScrFile, DestFile);
		} catch (WebDriverException e) {
			log.fatal("Caught WebDriverException exception while capturing screenshot");
			e.printStackTrace();
		} catch (IOException e) {
			log.fatal("Caught WebDriverException IOException while capturing screenshot");
			e.printStackTrace();
		}
	}
	
	@Override
	public void pass(String message, boolean screenshot) {
		log.info(message);
		try {
			if(screenshot) {
				TakesScreenshot scrShot = ((TakesScreenshot) driver);
				 String base64Image = scrShot.getScreenshotAs(OutputType.BASE64);
				TestReporter.log(Status.PASS, createBase64ScreenshotURL(base64Image, message));
			}else {
				TestReporter.log(Status.PASS, message);
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
    public void fail(String message, boolean screenshot) {
		log.info(message);
		try {
			if(screenshot) {
				TakesScreenshot scrShot = ((TakesScreenshot) driver);
				 String base64Image = scrShot.getScreenshotAs(OutputType.BASE64);
				TestReporter.log(Status.FAIL, createBase64ScreenshotURL(base64Image, message));
			}else {
				TestReporter.log(Status.FAIL, message);
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
    public void info(String message, boolean screenshot) {
		log.info(message);
		try {
			if(screenshot) {
				TakesScreenshot scrShot = ((TakesScreenshot) driver);
				 String base64Image = scrShot.getScreenshotAs(OutputType.BASE64);
				TestReporter.log(Status.INFO, createBase64ScreenshotURL(base64Image, message));
			}else {
				TestReporter.log(Status.INFO, message);
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
    public void skip(String message, boolean screenshot) {
		log.info(message);
		try {
			if(screenshot) {
				TakesScreenshot scrShot = ((TakesScreenshot) driver);
				 String base64Image = scrShot.getScreenshotAs(OutputType.BASE64);
				TestReporter.log(Status.SKIP, createBase64ScreenshotURL(base64Image, message));
			}else {
				TestReporter.log(Status.SKIP, message);
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static synchronized String createBase64ScreenshotURL(String base64Image, String sErrorMessage) throws IOException {
	    String aTagStart = "<a href=\"data:image/png;base64," + base64Image + "\" data-featherlight=\"image\">";
	    String sStyleToCreateThumbNail = " style=\"width: 60px; height: 60px; object-fit: contain;\" ";
	    String imgTagStart = "<img src= \"data:image/png;base64," + ConvertFileToBase64(Constants.BASEPATH + "\\images\\screenshotIcon.png") + "\" "
	        + sStyleToCreateThumbNail + "alt=\"" + sErrorMessage + "\" width=\"50\" height=\"50\">" + sErrorMessage + "</a>";
	    return aTagStart + imgTagStart;
	  }
	
	public static String ConvertFileToBase64(String filePath) throws IOException {
	    byte[] byteData = Files.readAllBytes(Paths.get(filePath));
	    return Base64.getEncoder().encodeToString(byteData);
	  }
	
	@Override
	public void wait(int iSec) throws InterruptedException {
		Thread.sleep(iSec*1000);
	}
	
	
	
	
	
	
	
	
	
}
