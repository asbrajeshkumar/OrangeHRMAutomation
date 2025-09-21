package framework.api;

import org.openqa.selenium.WebElement;

import framework.or.OrStructure;

public interface IWElement{

	void click(OrStructure btnSubmit);

	String getText(OrStructure lblUsername);

	void sendKeys(OrStructure txtUsername, String username);

	WebElement getWebElement(OrStructure orElem) throws Exception;

	void waitTillElementIsPresent(OrStructure oElem) throws Exception;

	void waitTillElementIsPresent(OrStructure oElem, int WaitInSeconds) throws Exception;

	Boolean isAvailableOnPage(OrStructure oElem, int WaitInSeconds) throws Exception;

	Boolean isAvailableOnPage(OrStructure oElem) throws Exception;

	void selectDropdownWithValue(OrStructure oElem, String sValue) throws Exception;

	void waitTillElementIsDisplayed(OrStructure oElem) throws Exception;

	void waitTillElementIsDisplayed(OrStructure oElem, int WaitInSeconds) throws Exception;

	
	

}
