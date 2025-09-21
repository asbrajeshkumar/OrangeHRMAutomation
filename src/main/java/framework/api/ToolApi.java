package framework.api;

import org.openqa.selenium.WebDriver;

public class ToolApi {

	public static IWDriver getDriver(WebDriver Driver) {
		return new WDriver(Driver);
	}

	public static IWElement getElement(IWDriver Driver) {
		return new WElement(Driver);
	}
}
