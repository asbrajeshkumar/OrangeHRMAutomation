package framework;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import framework.api.IWDriver;
import framework.api.IWElement;
import framework.api.ToolApi;
import framework.or.OrLoader;
import framework.or.OrStructure;
import framework.or.StructureInjector;
import utility.Constants;
import utility.LogUtil;

public class BasePage {
	private static final Logger log = LogUtil.getLogger(BasePage.class);
	
	protected WebDriver driver = null;
	protected IWDriver iDriver = null;
	protected IWElement elem = null;
	protected HashMap<String, OrStructure> orMap = null;
	
	public BasePage(IWDriver IDriver) {
		iDriver = IDriver;
		driver = IDriver.getDriver();
		elem = ToolApi.getElement(iDriver);
		orMap = new HashMap<String, OrStructure>();
	}
	
	protected void initOR(BasePage page) {
		log.info("Initiated object repository for ["+page.getClass().getSimpleName()+"]");
		String aPath = Constants.BASEPATH+"\\src\\test\\resources\\objectRepository\\"+page.getClass().getSimpleName()+".json";
		OrLoader loader = new OrLoader(aPath);
		StructureInjector.inject(this, loader);
	}
	
}
