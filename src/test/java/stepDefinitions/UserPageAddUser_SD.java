package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import commonUtilities.CommonUtils;
import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.UserPageAddUser;

public class UserPageAddUser_SD {

	WebDriver driver;
	TestContext testcontext;
	UserPageAddUser addUserPage;
	CommonUtils commonUtils;
	private static final Logger logger = LogManager.getLogger(UserPageValidation_SD.class);

	public UserPageAddUser_SD(TestContext testcontext) {
		this.testcontext = testcontext;
		addUserPage = testcontext.getPageObjectManager().getAddUserpage();
		commonUtils = testcontext.getCommonUtils();
	}

	@Then("Admin clicks + A new User button")
	public void admin_clicks_a_new_user_button() {
		addUserPage.addNewUserClick();
	}

	@Then("Admin should see pop up open for User details")
	public void admin_should_see_pop_up_open_for_user_details() {
		addUserPage.getUserPopUpHeader();
	}

	@Then("Admin verifies the {string} existance and its {string}")
	public void admin_verifies_the_existance_and_its(String fieldName, String fieldType) {
		System.out.println("Field Name - " + fieldName);
		System.out.println("Field Type - " + fieldType);
		addUserPage.checkTheFieldExistanceAndType(fieldName, fieldType);
	}

	@Then("Admin should see pop up open for user details with Cancel ,Submit and close buttons")
	public void admin_should_see_pop_up_open_for_user_details_with_cancel_submit_and_close_buttons() {
		addUserPage.userPopUpButtonFields();
	}

	@Given("Admin is on User details pop up")
	public void admin_is_on_user_details_pop_up() {
		logger.info("Admin is on User Details Pop Up");
	}

	@When("Admin enters mandatory fields {string} and {int} in the form and clicks on submit button")
	public void admin_enters_mandatory_fields_and_in_the_form_and_clicks_on_submit_button(String sheetname,
			Integer rowNo) throws InvalidFormatException, IOException, InterruptedException {
		addUserPage.fillValidUserDetails(sheetname, rowNo);
		addUserPage.submitClick();
	}

	@Then("Admin gets message User added {string} and {string} and for successful creation check for the user in table")
	public void admin_gets_message_user_added_and_and_for_successful_creation_check_for_the_user_in_table(
			String message, String Scenario) {
		// addUserPage.checkScenario(Scenario);
		addUserPage.verifySuccessMessage(message, Scenario);
	}

	@When("Admin clicks on submit button without entering data")
	public void admin_clicks_on_submit_button_without_entering_data()
			throws InvalidFormatException, IOException, InterruptedException {
		addUserPage.submitClick();
		logger.info("Admin submits Empty Form");
	}

	@Then("User won't be created and Admin gets error message")
	public void user_won_t_be_created_and_admin_gets_error_message() throws InterruptedException {
		addUserPage.emptyFormErrorMsg();
	}

	@When("Admin clicks Cancel or Close\\(X) Icon on User Details form")
	public void admin_clicks_or_cancel_close_x_icon_on_user_details_form() {
		addUserPage.closeDialogClick();
		logger.info("Admin close the User Details Dialog without adding user");
		System.out.println("Admin close the User Details Dialog without adding user");
	}

	@Then("User Details popup window should be closed without saving")
	public void user_details_popup_window_should_be_closed_without_saving() {
		System.out.println("Then");
		addUserPage.chkUserDialogClosed();
	}

	@When("Admin clicks Cancel button")
	public void admin_clicks_cancel_button() {
		addUserPage.cancelButtonClick();
		logger.info("Admin Clicks the Cancel Button before creating user");
	}

	@Then("Admin can see the User details popup disappears without adding any user")
	public void admin_can_see_the_user_details_popup_disappears_without_adding_any_user() throws InterruptedException {
		addUserPage.chkUserDialogClosed();
	}
}
