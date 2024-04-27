package stepDefinitions;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;

public class AddNewBatch_SD {
	TestContext context;
	BatchPage batchPage;
	WebDriver driver;

	public AddNewBatch_SD(TestContext context) {
		this.context = context;
		batchPage = context.getPageObjectManager().getBatchPage();
	}

	@Given("Admin login with valid credentials")
	public void admin_login() {

	}

	@Given("Admin is logged on the Lms portal dashboardPage after login")
	public void admin_is_logged_on_the_lms_portal_dashboard_page_after_login() {

		System.out.println("User logged in to the system: ");

	}

	@When("Admin clicks {string} from navigation bar")
	public void admin_clicks_from_navigation_bar(String string) {
		batchPage.batchNavigationBar();
	}

	@Then("Admin clicks {string} button")
	public void admin_clicks_button(String batch) {
		batchPage.addNewBatch();
	}

	@Then("A new pop up with Batch details appears")
	public void a_new_pop_up_with_batch_details_appears() {
		batchPage.popUpVerification();
	}

	@Then("Admin Verifies the {string} existance and its {string}")
	public void admin_verifies_the_existance_and_its(String FieldName, String FieldType) {
		System.out.println(FieldName + "  " + FieldType);
		batchPage.checkTheFieldExistanceAndType(FieldName, FieldType);
	}

	@When("Admin fill in all the fields except description with valid values {string} and {int}")
	public void admin_fill_in_all_the_fields_except_description_with_valid_values_and(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		batchPage.checkDescriptionFieldOptional();

	}

	@Then("admin clicks save button")
	public void admin_clicks_save_button() {
		batchPage.clickSaveButton();
	}

	@Then("The newly added batch should be present in the data table in Manage Batch page")
	public void the_newly_added_batch_should_be_present_in_the_data_table_in_manage_batch_page() {
		batchPage.checkForTheAddedBatch();
	}

	@Given("Admin is on the BatchDetails page")
	public void admin_is_on_the_batch_details_page() {
		System.out.println("Admin is in the batch page");
	}

	@When("Admin fills out the mandatory fields {string} and {int}")
	public void admin_fills_out_the_mandatory_fields_and(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		batchPage.fillBatchValidDestails(sheetName, rowNo);
	}

	@Then("Admin clicks the save button")
	public void admin_clicks_the_save_button() {
		batchPage.clickSaveButton();
	}

	@Then("Admin should see the Successfull message")
	public void admin_should_see_the_successfull_message() {
		batchPage.verifySuccessMessage();
	}

	@Given("Admin added the batch")
	public void admin_added_the_batch() {
      System.out.println("Admin Added the batch");
	}

	@When("Admin enters any of the Name with invalid values")
	public void admin_enters_any_of_the_name_with_invalid_values() {

	}

	@Then("Admin should get error message")
	public void admin_should_get_error_message() {

	}

	@When("Admin enters any of the number of classes with invalid values")
	public void admin_enters_any_of_the_number_of_classes_with_invalid_values() {

	}

	@When("Admin enters any of the Description with invalid values")
	public void admin_enters_any_of_the_description_with_invalid_values() {

	}

	@When("Admin enters any of the Program Name with invalid values")
	public void admin_enters_any_of_the_program_name_with_invalid_values() {

	}

	@When("Admin enters any of the Status with invalid values")
	public void admin_enters_any_of_the_status_with_invalid_values() {

	}

	@When("Any of the mandatory fields are blank {string} and {int}")
	public void any_of_the_mandatory_fields_are_blank_and(String string, Integer int1) {
	    
	}

	@When("Admin fill in all the fields except description with valid values")
	public void admin_fill_in_all_the_fields_except_description_with_valid_values() {

	}
}
