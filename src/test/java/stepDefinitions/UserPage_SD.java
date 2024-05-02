package stepDefinitions;

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
import managers.FileReaderManager;
import pageObjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserPage_SD {

	private TestContext testContext;
	private LoginPage loginPage;
	private HomePage homePage;
	private UserPage_VerifySort userVerifySort;
	private UserPage_Edit userPage_Edit;
	private CommonUtils commonUtils;
	private String title;
	private static final Logger logger = LogManager.getLogger(HomePage_SD.class);

	public UserPage_SD(TestContext testcontext) {
		this.testContext = testcontext;
		this.homePage = testContext.getPageObjectManager().getHomePage();
		this.loginPage = testContext.getPageObjectManager().getLoginPage();
		this.userVerifySort = testcontext.getPageObjectManager().getUserVerifySort();
		this.userPage_Edit = testcontext.getPageObjectManager().getUserPage_Edit();
		this.commonUtils = testContext.getCommonUtils();
	}

	// Update User details code Here

	@Given("Admin is on dashboard page after Login and clicks User on the navigation bar")
	public void admin_is_on_dashboard_page_after_login_and_clicks_User_on_the_navigation_bar() {

		userPage_Edit.verifyDashboardPage();
	}

	@Given("Admin is on Manage User Page")
	public void Admin_is_on_Manage_User_Page() {

		String title = commonUtils.getCurrentUrl();
		System.out.println("*******User Manage page*********" + title);
	}

	@When("Admin clicks on ID sort icon")
	public void admin_clicks_on_id_sort_icon() {

		userVerifySort.SortByUserId();
	}

	@Then("Admin should see User details are sorted by id")
	public void admin_should_see_user_details_are_sorted_by_id() {

	}

	// User Edit step definitions

	@When("Admin clicks on the edit icon")
	public void admin_clicks_on_the_edit_icon() {

		userPage_Edit.searchUserByName();// call the actual searchUserByname program
		userPage_Edit.clickOnUserEditIcon();

	}

	@Then("A new pop up with User details appears from sheet {string} and {string}")
	public void a_new_pop_up_with_user_details_appears_from_sheet_and(String option, String sheetName)
			throws Exception {

		ExcelFileData.userPageEditExcelData(option, sheetName);
		String expectedTextTitle = ExcelFileData.userDetailsPopupText;
		System.out.println("Expected user pop text" + expectedTextTitle);
		String textTitle = userPage_Edit.verifyUserDetailsPopUpWindow();
		System.out.println("From webPage:" + textTitle);

		// Assert.assertEquals(textTitle, expectedTextTitle);
	}

	@When("Update the fields with valid data and click submit from sheet {string} and {string}")
	public void update_the_fields_with_valid_data_and_click_submit_from_sheet_and(String option, String sheetName)
			throws Exception {

		ExcelFileData.userPageEditExcelData(option, sheetName);
		userPage_Edit.addUserDetails(ExcelFileData.userFirstName, ExcelFileData.userLastName,
				ExcelFileData.userMiddleName, ExcelFileData.userLocation, ExcelFileData.userPhoneNo,
				ExcelFileData.userLinkedInUrl, ExcelFileData.userRole, ExcelFileData.userRoleStatus,
				ExcelFileData.userVisaStatus, ExcelFileData.userEmail, ExcelFileData.userUnderGraduate,
				ExcelFileData.userPostGraduate, ExcelFileData.userTimeZone, ExcelFileData.userComments);

		LoggerLoad.info("UserFirstName : " + ExcelFileData.userFirstName + "LastName : " + ExcelFileData.userLastName
				+ "Middle Name : " + ExcelFileData.userMiddleName + "Location : " + ExcelFileData.userLocation
				+ "Phone No : " + ExcelFileData.userPhoneNo + "LinkedInUrl : " + ExcelFileData.userLinkedInUrl
				+ "UserRole : " + ExcelFileData.userRole + "Visa Status : " + ExcelFileData.userVisaStatus
				+ "UserRoleStatus : " + ExcelFileData.userRoleStatus + "Timezone : " + ExcelFileData.userTimeZone);
	}

	@Then("Admin gets message {string} and see the updated values in data table")
	public void admin_gets_message_and_see_the_updated_values_in_data_table(String expectedText) {

		String message = userPage_Edit.userUpdateDisplayMsg();
		System.out.println(message);
		Assert.assertTrue(message.contains(expectedText));
	}

	@When("Update the fields with invalid values and click submit from sheet {string} and {string}")
	public void update_the_fields_with_invalid_values_and_click_submit_from_sheet_and(String option, String sheetName)
			throws Exception {

		ExcelFileData.userPageEditExcelData(option, sheetName);
		userPage_Edit.addUserDetails(ExcelFileData.userFirstName, ExcelFileData.userLastName,
				ExcelFileData.userMiddleName, ExcelFileData.userLocation, ExcelFileData.userPhoneNo,
				ExcelFileData.userLinkedInUrl, ExcelFileData.userRole, ExcelFileData.userRoleStatus,
				ExcelFileData.userVisaStatus, ExcelFileData.userEmail, ExcelFileData.userUnderGraduate,
				ExcelFileData.userPostGraduate, ExcelFileData.userTimeZone, ExcelFileData.userComments);

		LoggerLoad.info("UserFirstName : " + ExcelFileData.userFirstName + "LastName : " + ExcelFileData.userLastName
				+ "Middle Name : " + ExcelFileData.userMiddleName + "Location : " + ExcelFileData.userLocation
				+ "Phone No : " + ExcelFileData.userPhoneNo + "LinkedInUrl : " + ExcelFileData.userLinkedInUrl
				+ "UserRole : " + ExcelFileData.userRole + "Visa Status : " + ExcelFileData.userVisaStatus
				+ "UserRoleStatus : " + ExcelFileData.userRoleStatus + "Timezone : " + ExcelFileData.userTimeZone);
	}

	@Then("Admin should get Error message")
	public void admin_should_get_error_message() {
		String text = userPage_Edit.userUpdateDisplayMsg();
		System.out.println("Error msg should be displayed" + text);
	}

	@When("Update the mandatory fields with valid values and click submit from sheet {string} and {string}")
	public void update_the_mandatory_fields_with_valid_values_and_click_submit_from_sheet_and(String option,
			String sheetName) throws Exception {

		ExcelFileData.userPageEditExcelData(option, sheetName);
		userPage_Edit.updateMandatoryUserDetails(ExcelFileData.userFirstName, ExcelFileData.userLastName,
				ExcelFileData.userLocation, ExcelFileData.userPhoneNo, ExcelFileData.userRole,
				ExcelFileData.userRoleStatus, ExcelFileData.userVisaStatus);

		LoggerLoad.info("UserFirstName : " + ExcelFileData.userFirstName + "LastName : " + ExcelFileData.userLastName
				+ "Middle Name : " + ExcelFileData.userMiddleName + "Location : " + ExcelFileData.userLocation
				+ "Phone No : " + ExcelFileData.userPhoneNo + "LinkedInUrl : " + ExcelFileData.userLinkedInUrl
				+ "UserRole : " + ExcelFileData.userRole + "Visa Status : " + ExcelFileData.userVisaStatus
				+ "UserRoleStatus : " + ExcelFileData.userRoleStatus + "Timezone : " + ExcelFileData.userTimeZone);

	}

	@When("Update the optional fields with valid values and click submit from sheet {string} and {string}")
	public void update_the_optional_fields_with_valid_values_and_click_submit_from_sheet_and(String option,
			String sheetName) throws Exception {

		ExcelFileData.userPageEditExcelData(option, sheetName);
		userPage_Edit.updateOptionalUserDetails(ExcelFileData.userMiddleName, ExcelFileData.userEmail,
				ExcelFileData.userUnderGraduate, ExcelFileData.userPostGraduate, ExcelFileData.userTimeZone,
				ExcelFileData.userComments, ExcelFileData.userLinkedInUrl);

	}

	@When("Admin enters only numbers or special char in the text fields from sheet {string} and {string}")
	public void admin_enters_only_numbers_or_special_char_in_the_text_fields_from_sheet_and(String option,
			String sheetName) throws Exception {

		ExcelFileData.userPageEditExcelData(option, sheetName);
		userPage_Edit.updateInvalidNumericUserDetails(ExcelFileData.userMiddleName, ExcelFileData.userUnderGraduate,
				ExcelFileData.userPostGraduate, ExcelFileData.userTimeZone, ExcelFileData.userComments);

		LoggerLoad.info("UserFirstName : " + ExcelFileData.userFirstName + "LastName : " + ExcelFileData.userLastName
				+ "Middle Name : " + ExcelFileData.userMiddleName + "Location : " + ExcelFileData.userLocation
				+ "Phone No : " + ExcelFileData.userPhoneNo + "LinkedInUrl : " + ExcelFileData.userLinkedInUrl
				+ "UserRole : " + ExcelFileData.userRole + "Visa Status : " + ExcelFileData.userVisaStatus
				+ "UserRoleStatus : " + ExcelFileData.userRoleStatus + "Timezone : " + ExcelFileData.userTimeZone);
	}

	@When("Admin clicks Cancel button on edit popup")
	public void admin_clicks_cancel_button_on_edit_popup() {

		userPage_Edit.verifyCancelButton();
	}

	@Then("Admin can see the User details popup disappears and can see nothing changed for particular User")
	public void admin_can_see_the_user_details_popup_disappears_and_can_see_nothing_changed_for_particular_user() {

		String text = userPage_Edit.onManagePage();
		Assert.assertEquals(text, "Manage User");
	}

}
