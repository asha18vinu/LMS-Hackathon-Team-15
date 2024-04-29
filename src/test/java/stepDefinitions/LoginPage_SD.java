package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import commonUtilities.CommonUtils;
import commonUtilities.LoggerLoad;
import context.TestContext;
import dataFilesReader.ExcelFileData;
import dataFilesReader.ExcelFileSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage_SD {

	private TestContext testContext;
	private LoginPage loginPage;
	private HomePage homePage;
	private CommonUtils commonUtils;
	private String title;
	private static final Logger logger = LogManager.getLogger(HomePage_SD.class);

	public LoginPage_SD(TestContext testcontext) {
		this.testContext = testcontext;
		this.homePage = testContext.getPageObjectManager().getHomePage();
		this.loginPage = testContext.getPageObjectManager().getLoginPage();
		this.commonUtils = testContext.getCommonUtils();
	}

	@Given("Admin is on Home Page")
	public void admin_is_on_home_page() {

		title = commonUtils.getTitle();
		System.out.println("Home Page: " + title);

	}

	@When("Admin enter invalid credentials from sheet {string} and {string} and clicks login button")
	public void admin_enter_invalid_credentials_from_sheet_and_and_clicks_login_button(String option, String sheetName)
			throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		loginPage.verifyLogin(ExcelFileData.userName, ExcelFileData.password);
		loginPage.clickLoginButton();
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);

	}

	@When("Admin enter value only in password from sheet {string} and {string} and clicks login button")
	public void admin_enter_value_only_in_password_from_sheet_and_and_clicks_login_button(String option,
			String sheetName) throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		loginPage.verifyLogin(ExcelFileData.userName, ExcelFileData.password);
		loginPage.clickLoginButton();
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);

	}

	@When("Admin enter no value in username and password from sheet {string} and {string} and clicks login button")
	public void admin_enter_no_value_in_username_and_password_from_sheet_and_and_clicks_login_button(String option,
			String sheetName) throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		loginPage.verifyLogin(ExcelFileData.userName, ExcelFileData.password);
		loginPage.clickLoginButton();
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);

	}

	@When("Admin enter value only only numeric value in password from sheet {string} and {string} and clicks login button")
	public void admin_enter_value_only_only_numeric_value_in_password_from_sheet_and_and_clicks_login_button(
			String option, String sheetName) throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		loginPage.verifyLogin(ExcelFileData.userName, ExcelFileData.password);
		loginPage.clickLoginButton();
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);

	}

	@When("Admin enter value only in username from sheet {string} and {string} and clicks login button")
	public void admin_enter_value_only_in_username_from_sheet_and_and_clicks_login_button(String option,
			String sheetName) throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		loginPage.verifyLogin(ExcelFileData.userName, ExcelFileData.password);
		loginPage.clickLoginButton();
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);

	}

	@When("Admin enter valid credentials from sheet {string} and {string} and clicks login button")
	public void admin_enter_valid_credentials_from_sheet_and_and_clicks_login_button(String option, String sheetName)
			throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		loginPage.enterLoginDetails(ExcelFileData.userName, ExcelFileData.password);
		loginPage.clickLoginButton();
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);

	}

	@When("Admin enter valid credentials  from sheet {string} and {string} and clicks login button through keyboard")
	public void admin_enter_valid_credentials_from_sheet_and_and_clicks_login_button_through_keyboard(String option,
			String sheetName) throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		loginPage.loginThroughKeyBoard(ExcelFileData.userName, ExcelFileData.password);
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);

	}

	@When("Admin enter valid credentials from sheet {string} and {string} and clicks login button through mouse")
	public void admin_enter_valid_credentials_from_sheet_and_and_clicks_login_button_through_mouse(String option,
			String sheetName) throws Exception {

		ExcelFileData.loginPageExcelData(option, sheetName);
		//loginPage.enterLoginDetails(ExcelFileData.userName, ExcelFileData.password);
		loginPage.loginThroughMouse(ExcelFileData.userName, ExcelFileData.password);
		LoggerLoad.info("UserName : " + ExcelFileData.userName + "Password is: " + ExcelFileData.password);
	}

	@Then("Admin should land on dashboard page")
	public void admin_should_land_on_dashboard_page() {

		String title = commonUtils.getTitle();
		Assert.assertEquals(title, "LMS");
	}

	@Then("Error message please check username\\/password")
	public void error_message_please_check_username_password() {

		String errorMessage = loginPage.LoginErrorDisplayMsg();
		System.out.println("Message::" + errorMessage);
		Assert.assertEquals(errorMessage, "Invalid username and password Please try again");

	}
	@Then("Error message please enter username and password")
	public void error_message_please_enter_username_and_password() {
		
		String errorMessage = loginPage.UserPasswordErrorDisplayMsg();
		System.out.println("Message::" + errorMessage);
	//	Assert.assertEquals(errorMessage, "Invalid username and password Please try again");
		
	}
	@Then("Error message please check username")
	public void error_message_please_check_username() {
	   
		String actualMessage=loginPage.UserErrorDisplayMsg(); 
		Assert.assertEquals(actualMessage,"Please enter your user name");
	}

	@Then("Error message please check password")
	public void error_message_please_check_password() {
	    
		String actualMessage=loginPage.PasswordErrorDisplayMsg();
		Assert.assertEquals(actualMessage, "Please enter your password");
	}
	
	@Then("Error message please enter username")
	public void error_message_please_enter_username() {
	   
		String actualMessage=loginPage.UserErrorDisplayMsg();
		Assert.assertEquals(actualMessage,"Please enter your user name");
	}

	@Then("Error message please enter password")
	public void error_message_please_enter_password() {
		
		String actualMessage=loginPage.PasswordErrorDisplayMsg();
		Assert.assertEquals(actualMessage, "Please enter your password");
	}

	@When("Admin enter no value only in input and password field and click on Loginbutton")
	public void admin_enter_no_value_only_in_input_and_password_field_and_click_on_loginbutton() throws InterruptedException {
	    
		loginPage.clickLoginButton();
	}

	
}
