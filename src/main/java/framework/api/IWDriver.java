package framework.api;

import org.openqa.selenium.WebDriver;

public interface IWDriver {

	WebDriver getDriver();

	void waitForPageLoad();

	void closeDriver();

	void captureScreenshot(String fileWithPath);

	void pass(String message, boolean screenshot);

	void fail(String message, boolean screenshot);

	void skip(String message, boolean screenshot);

	void info(String message, boolean screenshot);

	void wait(int iSec) throws InterruptedException;
	

}
