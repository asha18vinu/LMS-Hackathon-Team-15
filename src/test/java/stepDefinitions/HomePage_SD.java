package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import commonUtilities.CommonUtils;
import commonUtilities.LoggerLoad;
import context.TestContext;
import dataFilesReader.ExcelFileData;
import io.cucumber.java.en.*;
import net.sourceforge.tess4j.TesseractException;
import pageObjects.*;

public class HomePage_SD {

	private TestContext testContext;
	private HomePage homePage;
	private CommonUtils commonUtils;
	private String title;
	private static final Logger logger = LogManager.getLogger(HomePage_SD.class);

	public HomePage_SD(TestContext testContext) {

		this.testContext = testContext;
		this.homePage = testContext.getPageObjectManager().getHomePage();
		this.commonUtils = testContext.getCommonUtils();
	}

	@Given("Admin launch the browser")
	public void admin_launch_the_browser() {

		logger.info("----------Admin launch the Browser----------");
	}

	@When("Admin gives the correct LMS portal URL")
	public void admin_gives_the_correct_lms_portal_url() {

		homePage.goToHomePage();
		
	}

	@Then("Admin should recieve {int} page not found error")
	public void admin_should_recieve_page_not_found_error(Integer expectedResponsecode) {

		int responseCode = homePage.verifyHomePageBrokenLinks();
		LoggerLoad.info("Actual responseCode::" + responseCode + " Expected ResponseCode::" + expectedResponsecode);
		Assert.assertEquals(responseCode, expectedResponsecode);
	}
	
	// Home Page Verification

	@Then("Admin should land on the home page with title from sheet {string} and {string}")
	public void admin_should_land_on_the_home_page_with_title_from_sheet_and(String option, String sheetName)
			throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String title = commonUtils.getTitle();
		String expectedTitle = ExcelFileData.expectedTitle;
		LoggerLoad.info("Actual title::" + title + " Expected Title::" + expectedTitle);
		Assert.assertEquals(title, expectedTitle);

	}

	@When("Admin gives the invalid LMS portal URL from sheet {string} and {string}")
	public void admin_gives_the_invalid_lms_portal_url_from_sheet_and(String option, String sheetName)
			throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String actualUrl=homePage.verifyHomePageUrl(ExcelFileData.InvalidUrl);
		String expectedUrl=ExcelFileData.InvalidUrl;	
		LoggerLoad.info("Actual url::" + actualUrl + " Expected url::" + expectedUrl);
	}

	@When("Admin gives the invalid Url endpoint from sheet {string} and {string}")
	public void admin_gives_the_invalid_Url_endpoint_from_sheet_and(String option, String sheetName) throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String actualUrl=homePage.verifyHomePageUrl(ExcelFileData.InvalidUrlEndpoint);
		String expectedUrl=ExcelFileData.InvalidUrlEndpoint;		
		LoggerLoad.info("Actual url::" + actualUrl + " Expected url::" + expectedUrl);
	
	}

	@Then("HTTP response >= {int} then the link is broken from sheet {string} and {string}")
	public void http_response_then_the_link_is_broken_from_sheet_and(Integer int1, String option, String sheetName)
			throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		int responseCode = homePage.verifyHomePageBrokenLinks();
		String expectedResponsecode = ExcelFileData.expectedResponsecode;
		LoggerLoad.info("Actual responseCode::" + responseCode + " Expected ResponseCode::" + expectedResponsecode);
		LoggerLoad.info("Actual responseCode did not match with Expected ResponseCode, Not a Broken Link: ");
		Assert.assertNotEquals(responseCode, expectedResponsecode);

	}

	@Then("Admin should see correct spellings in all fields from sheet {string} and {string}")
	public void admin_should_see_correct_spellings_in_all_fields_from_sheet_and(String option, String sheetName)
			throws Exception {

		String homePageText = homePage.verifyHomePageSpelling();
		ExcelFileData.homePageExcelData(option, sheetName);
		String expectedText = ExcelFileData.expectedPageText;		
		LoggerLoad.info("Actual Home page Text::" + homePageText + " Expected Home page Text::" + expectedText);
		Assert.assertEquals(homePageText, expectedText);

	}

	@Then("Admin should see  application name from sheet {string} and {string}")
	public void admin_should_see_application_name_from_sheet_and(String option, String sheetName) throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String text = homePage.verifyApplicationName();
		String expectedText = ExcelFileData.applicationName;
		LoggerLoad.info("Actual application name" + text + " Expected application name" + expectedText);
		Assert.assertTrue(text.contains(expectedText));

	}

	@Then("Admin should see company name from sheet {string} and {string} below the app name")
	public void admin_should_see_company_name_from_sheet_and_below_the_app_name(String option, String sheetName)
			throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String text = homePage.verifyCompanyName();
		String expectedText = ExcelFileData.companyName;
		LoggerLoad.info("Actual company name::" + text + " Expected company name::" + expectedText);
		Assert.assertTrue(text.contains(expectedText));

	}

	@Then("Admin should in the first text field from sheet {string} and {string}")
	public void admin_should_in_the_first_text_field_from_sheet_and(String option, String sheetName) throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String firstText = homePage.VerifyFirstTextField();
		String expectedText = ExcelFileData.expectedFirstTextvalue;
		LoggerLoad.info("Actual first text field value::" + firstText + " Expected first text field value::" + expectedText);
		Assert.assertEquals(firstText, expectedText);

	}

	@Then("Admin should see asterik symbol next to user text from sheet {string} and {string}")
	public void admin_should_see_asterik_symbol_next_to_user_text_from_sheet_and(String option, String sheetName)
			throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String firstTextSymbol = homePage.VerifySymbol_FirstTextField();
		String expectedTextSymbol = ExcelFileData.expectedfirstTextSymbol;
		LoggerLoad.info(
				"Actual first symbol value::" + firstTextSymbol + " Expected first symbol value::" + expectedTextSymbol);

		Assert.assertEquals(firstTextSymbol, expectedTextSymbol);

	}

	@Then("Admin should in the second text field from sheet {string} and {string}")
	public void admin_should_in_the_second_text_field_from_sheet_and(String option, String sheetName) throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String secondText = homePage.VerifySecondTextField();
		String expectedText = ExcelFileData.expectedSecondTextvalue;
		LoggerLoad.info(
				"Actual second text field value::" + secondText + " Expected second text field value::" + expectedText);

		Assert.assertEquals(secondText, expectedText);
	}

	@Then("Admin should see symbol next to password text from sheet {string} and {string}")
	public void admin_should_see_symbol_next_to_password_text_from_sheet_and(String option, String sheetName)
			throws Exception {

		ExcelFileData.homePageExcelData(option, sheetName);
		String secondTextSymbol = homePage.VerifySymbol_SecondTextField();
		String expectedTextSymbol = ExcelFileData.expectedSecondTextSymbol;
		LoggerLoad.info("Actual symbol text::" + secondTextSymbol + " Expected symbol text::" + expectedTextSymbol);

		Assert.assertEquals(secondTextSymbol, expectedTextSymbol);

	}

	@Then("Admin should see input field on the centre of the page from sheet {string} and {string}")
	public void admin_should_see_input_field_on_the_centre_of_the_page_from_sheet_and(String option, String sheetName)
			throws Exception {
		ExcelFileData.homePageExcelData(option, sheetName);
		String inputFieldAlignment = homePage.verifyInputfieldAlignment();
		String expectedAlignment = ExcelFileData.inputFieldAlignment;
		LoggerLoad.info("Actual alignment Input field::" + inputFieldAlignment + " Expected alignment Input field::"
				+ expectedAlignment);

		Assert.assertEquals(inputFieldAlignment, expectedAlignment);
	}

	@Then("Admin should see login button on the centre of the page from sheet {string} and {string}")
	public void admin_should_see_login_button_on_the_centre_of_the_page_from_sheet_and(String option, String sheetName) throws Exception {
		
		ExcelFileData.homePageExcelData(option, sheetName);
		String loginButtonAlignment = homePage.Verify_LoginButton_Alignment();
		String expectedAlignment = ExcelFileData.loginButtonAlignment;
		LoggerLoad.info("Actual alignment login field::" + loginButtonAlignment + " Expected alignment login field::"
				+ expectedAlignment);

		Assert.assertEquals(loginButtonAlignment, expectedAlignment);

	}

	@Then("Admin should see user color from sheet {string} and {string}")
	public void admin_should_see_user_color_from_sheet_and(String option, String sheetName) throws Exception {
		
		ExcelFileData.homePageExcelData(option, sheetName);
		String userInputColor = homePage.verifyUserInputColor();
		
		String expectedColor = ExcelFileData.userInputColor;
		LoggerLoad.info("Actual color user::" + userInputColor + " Expected color user::"
				+ expectedColor);
		Assert.assertEquals(userInputColor, expectedColor);


	}

	@Then("Admin should see password color from sheet {string} and {string}")
	public void admin_should_see_password_color_from_sheet_and(String option, String sheetName) throws Exception {
		
		ExcelFileData.homePageExcelData(option, sheetName);
		String passwordInputColor = homePage.verifyPasswordInputColor();		
		String expectedColor = ExcelFileData.passwordInputColor;
		LoggerLoad.info("Actual color password field::" + passwordInputColor + " Expected color password::"
				+ expectedColor);
		Assert.assertEquals(passwordInputColor, expectedColor);
		
	}
	@Then("Admin should see two text field from sheet {string} and {string}")
	public void admin_should_see_two_text_field_from_sheet_and(String option, String sheetName) throws Exception {
	   
		ExcelFileData.homePageExcelData(option, sheetName);
		int totalTextfield = homePage.verifyAllInputTextField();
		String strtotalTextfield=String.valueOf(totalTextfield);
		String expected = ExcelFileData.expectedtotalTextfield;
		LoggerLoad.info("Actual number of Text field::" + strtotalTextfield + " Expected number of Text field::"
				+ expected);
		Assert.assertEquals(strtotalTextfield, expected);
		
	}
	@Then("Admin should see login button")
	public void admin_should_see_login_button() {

		boolean loginButtonPresent = homePage.loginButtonIsPresent();
		Assert.assertEquals(loginButtonPresent, true);
	}


	@Then("Admin should see logo on the left  side")
	public void admin_should_see_logo_on_the_left_side() throws IOException {

		homePage.verifyLogoAlignment();
	}

	@Then("Admin should see Please login to LMS application")
	public void admin_should_see() {

		boolean signInContent = homePage.verifySignInContent();
		Assert.assertEquals(signInContent, true);

	}


}
