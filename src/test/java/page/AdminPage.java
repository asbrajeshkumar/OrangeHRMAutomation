package page;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

import framework.BasePage;
import framework.api.IWDriver;
import framework.or.OrStructure;
import framework.or.Structure;
import utility.LogUtil;

public class AdminPage extends BasePage{

private static final Logger log = LogUtil.getLogger(LoginPage.class);
	
	public AdminPage(IWDriver iDriver) {
		super(iDriver);
		super.initOR(this);
	}
	
	@Structure(name = "btnAdminTab")
	private OrStructure btnAdminTab;
	
	@Structure(name = "btnAddUser")
	private OrStructure btnAddUser;
	
	@Structure(name = "cboUserRole")
	private OrStructure cboUserRole;
	
	@Structure(name = "lstAdminRole")
	private OrStructure lstAdminRole;
	
	@Structure(name = "txtEmployeeName")
	private OrStructure txtEmployeeName;
	
	@Structure(name = "lblEmployeeNameSuggestion")
	private OrStructure lblEmployeeNameSuggestion;
	
	@Structure(name = "cboUserStatus")
	private OrStructure cboUserStatus;
	
	@Structure(name = "lstUserEnabled")
	private OrStructure lstUserEnabled;
	
	@Structure(name = "txtUsername")
	private OrStructure txtUsername;
	
	@Structure(name = "txtPassword")
	private OrStructure txtPassword;
	
	@Structure(name = "txtConfirmPassword")
	private OrStructure txtConfirmPassword;
	
	@Structure(name = "btnSave")
	private OrStructure btnSave;
	
	@Structure(name = "txtSearchUsername")
	private OrStructure txtSearchUsername;
	
	@Structure(name = "btnSearchUser")
	private OrStructure btnSearchUser;
	
	@Structure(name = "lblSearchResult")
	private OrStructure lblSearchResult;
	
	
	
	public void addAdminUser() throws Exception {
		iDriver.info("Creating admin user", false);
		Faker faker = new Faker();
		Random random = new Random();
		String username = faker.name().username();
		String basePassword = faker.internet().password(8, 12, true, true);
		int digit = random.nextInt(10);
		String password = basePassword += digit;
		String employeeName = "savi N g";
		iDriver.info("Creating admin user with username = ["+username+"] for employee name = ["+employeeName+"]", false);
		
		try {
			elem.click(btnAdminTab);
			elem.click(btnAddUser);
			elem.click(cboUserRole);
			elem.click(lstAdminRole);
			elem.sendKeys(txtEmployeeName, employeeName);
			iDriver.wait(3);
			elem.click(lblEmployeeNameSuggestion);
			elem.click(cboUserStatus);
			elem.click(lstUserEnabled);
			elem.sendKeys(txtUsername, username);
			elem.sendKeys(txtPassword, password);
			elem.sendKeys(txtConfirmPassword, password);
			elem.click(btnSave);
			iDriver.waitForPageLoad();
			if(searchUser(username)) {
				iDriver.pass("Admin user was created successfully", true);
			}else {
				iDriver.fail("Failed to create admin user", true);
			}
		} catch (Exception e) {
			iDriver.fail("Failed to create admin user", true);
			e.printStackTrace();
		}
	}
	
	public boolean searchUser(String username) {
		boolean userfound = false;
		try {
			elem.sendKeys(txtSearchUsername, username);
			elem.click(btnSearchUser);
			userfound = elem.isAvailableOnPage(lblSearchResult, 5);
		} catch (Exception e) {
			iDriver.fail("Failed while searching user", true);
			e.printStackTrace();
		}
		return userfound;
	}
	
	
	
}
