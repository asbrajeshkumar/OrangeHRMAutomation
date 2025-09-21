package framework.or;

import org.openqa.selenium.By;

public class OrStructure {

	private String locName, locType, locValue;
	int waitInSec = 0;
	public OrStructure(String locName, String locType, String locValue, int waitInSec) {
		super();
		this.locName = locName;
		this.locType = locType;
		this.locValue = locValue;
		this.waitInSec = waitInSec;
	}
	
	public By getBy() {
		String sBy = this.getLocType().toUpperCase();
		By toReturn = null;
		
		switch(sBy) {
		case "ID":
			toReturn = By.id(this.getLocValue());
			break;
		case "XPATH":
			toReturn = By.xpath(this.getLocValue());
			break;
		case "NAME":
			toReturn = By.name(this.getLocValue());
			break;
		case "CLASSNAME":
			toReturn = By.className(this.getLocValue());
			break;
		}
		return toReturn;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	public String getLocValue() {
		return locValue;
	}

	public void setLocValue(String locValue) {
		this.locValue = locValue;
	}

	public int getWaitInSec() {
		return waitInSec;
	}

	public void setWaitInSec(int waitInSec) {
		this.waitInSec = waitInSec;
	}
	
}
