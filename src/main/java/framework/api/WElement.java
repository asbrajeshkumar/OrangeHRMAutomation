package framework.api;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.or.OrStructure;
import utility.LogUtil;

public class WElement implements IWElement{
	private static final Logger log = LogUtil.getLogger(WElement.class);
	
	private WebDriver driver = null;
	IWDriver iDriver = null;
	
	public WElement(IWDriver idriver) {
			iDriver = idriver;
			driver = iDriver.getDriver();
	}

	@Override
	public void click(OrStructure orElem) {
		WebElement wElem = null;
		try {
			wElem = getWebElement(orElem);
			wElem.click();
			log.info(orElem.getLocName() +" was clicked.");
		}catch(Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", wElem);
			log.info(orElem.getLocName() +" was clicked with js executor.");
		}
		
	}

	@Override
	public WebElement getWebElement(OrStructure orElem) throws Exception{
		WebElement element = null;
		try {
			By byOfOrObj = orElem.getBy();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(orElem.getWaitInSec()));
			wait.until(ExpectedConditions.presenceOfElementLocated(byOfOrObj));
			element = driver.findElement(byOfOrObj);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while getting webelement."+e);
		}
		return element;
	}

	@Override
	public String getText(OrStructure orElem) {
		WebElement element = null;
		String sText = null;
		try {
			waitTillElementIsPresent(orElem, orElem.getWaitInSec());
			element = getWebElement(orElem);
			sText = element.getText();
		}catch(Exception e) {
			log.fatal("Exception thrown for while getting text value of "+orElem.getLocName());
			return null;
		}
		return sText;
	}

	@Override
	public void sendKeys(OrStructure oElem, String sValue) {
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			Thread.sleep(1000);
			element = getWebElement(oElem);
			click(oElem);
			element.clear();
			element.sendKeys(sValue);
		} catch (Exception e) {
			log.fatal("Caught exception while sending keys for "+oElem.getLocName());
		}
		
	}
	
	@Override
	public void waitTillElementIsPresent(OrStructure oElem) throws Exception{
		waitTillElementIsPresent(oElem, oElem.getWaitInSec());
	}

	@Override
	public void waitTillElementIsPresent(OrStructure oElem, int WaitInSeconds) throws Exception{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitInSeconds));
			wait.until(ExpectedConditions.presenceOfElementLocated(oElem.getBy()));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", getWebElement(oElem));
		} catch (Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while waiting to be present.");
		}
	}
	
	@Override
	public void waitTillElementIsDisplayed(OrStructure oElem) throws Exception{
		waitTillElementIsPresent(oElem, oElem.getWaitInSec());
	}

	@Override
	public void waitTillElementIsDisplayed(OrStructure oElem, int WaitInSeconds) throws Exception{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitInSeconds));
			wait.until(ExpectedConditions.visibilityOfElementLocated(oElem.getBy()));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", getWebElement(oElem));
		} catch (Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while waiting to be present.");
		}
	}
	
	@Override
	public Boolean isAvailableOnPage(OrStructure oElem)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			element = getWebElement(oElem);
			if(element !=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking presence on page.");
			return false;
		}
	}

	@Override
	public Boolean isAvailableOnPage(OrStructure oElem, int WaitInSeconds)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, WaitInSeconds);
			element = getWebElement(oElem);
			if(element !=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking presence on page.");
			return false;
		}
	}
	
	@Override
	public void selectDropdownWithValue(OrStructure oElem, String sValue) throws Exception{
		try{
			Thread.sleep(200);
			WebElement element = getWebElement(oElem);
			Select dropdown = new Select(element);
			dropdown.selectByValue(sValue);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while selecting value from dropdown for "+oElem.getLocName());
		}
	}

}
