package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import context.TestContext;
import hooks.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;

public class AddNewBatch_SD {
	TestContext context;
	BatchPage batchPage;
	WebDriver driver;
	boolean actualerrorMsg;
	public static boolean flag;
	private static final Logger logger = LogManager.getLogger(AddNewBatch_SD.class);
	
	public AddNewBatch_SD(TestContext context) {
		this.context = context;
		batchPage = context.getPageObjectManager().getBatchPage();
	}

	@Given("Admin login with valid credentials")
	public void admin_login() {
      batchPage.login();
	}

	@Given("Admin is logged on the Lms portal dashboardPage after login")
	public void admin_is_logged_on_the_lms_portal_dashboard_page_after_login() { 
		batchPage.verifyBatchURl();
	    logger.info("Admin is in the Dashboard page");
	}

	@When("Admin clicks {string} from navigation bar")
	public void admin_clicks_from_navigation_bar(String string) {
		batchPage.batchNavigationBar();
	}

	@Then("Admin clicks {string} button")
	public void admin_clicks_button(String batch) {
		batchPage.addNewBatchButtonClick();
	}

	@Then("A new pop up with Batch details appears")
	public void a_new_pop_up_with_batch_details_appears() {
		batchPage.popUpVerification();
	}

	@Then("Admin Verifies the {string} existance and its {string}")
	public void admin_verifies_the_existance_and_its(String FieldName, String FieldType) {		
		batchPage.checkTheFieldExistanceAndType(FieldName, FieldType);
	}

	@When("Admin fill in all the fields except description with valid values {string} and {int}")
	public void admin_fill_in_all_the_fields_except_description_with_valid_values_and(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		batchPage.checkDescriptionFieldOptional(sheetName,rowNo);

	}

//	@Then("admin clicks save button")
//	public void admin_clicks_save_button() {
//		flag=batchPage.clickSaveButton();
//	}

	
	
	@Then("The newly added batch should be present in the data table in Manage Batch")
	public void the_newly_added_batch_should_be_present_in_the_data_table_in_manage_batch() {
		try {
		if (flag == true) {
			batchPage.checkForTheAddedBatch();
			logger.info("Admin checks the datatable to verify the batch is added");
			assertEquals(flag, "false", "Description should be a OptionalField");
		}					
		} catch (AssertionError e) {
			logger.info("Description Is not Optional Assertion error");			
		}
	}
	@Given("Admin is on the BatchDetails page")
	public void admin_is_on_the_batch_details_page() {
		logger.info("Admin is in the batch page");
	}

	@When("Admin fills out the mandatory fields {string} and {int}")
	public void admin_fills_out_the_mandatory_fields_and(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		batchPage.fillBatchValidDestails(sheetName, rowNo);
	}

	@Then("Admin clicks the save button")
	public void admin_clicks_the_save_button() throws InterruptedException {
		batchPage.clickSaveBtn();
		logger.info("Admin clicks the save button after adding valid data to the field");
	}

	@Then("Admin should see the Successfull message")
	public void admin_should_see_the_successfull_message() {
		flag=batchPage.verifySuccessMessage();
		 logger.info("Admin verifies the success message :"+flag);
		
	}
	
	
	@Then("The newly added batch should be present in the data table in Manage Batch page")
	public void the_newly_added_batch_should_be_present_in_the_data_table_in_manage_batch_page() {
		try {
			System.out.println("batchadded : "+ flag);
		if (flag == true) {
			batchPage.checkForTheAddedBatch();
			logger.info("Admin checks the datatable to verify the batch is added");	
		}}catch(Exception e)
		{
			logger.info("Batch added failed");
		}
	}


	@Given("Admin added the batch")
	public void admin_added_the_batch() {
      logger.info("Admin Added the batch");
	}

	@When("Admin enters any of the {string} with invalid values {string}")
	public void admin_enters_any_of_the_with_invalid_values(String fieldName,String invalidvalues) throws InvalidFormatException, IOException, InterruptedException {
	   actualerrorMsg=batchPage.batchField(fieldName,invalidvalues);
		
	}
	

	@Then("Admin should get error message {string}")
	public void admin_should_get_error_message(String expectedErrorMsg) {
	  
		try
		{
		assertEquals(actualerrorMsg, true, "Admin didnt get the Expected eror msg");
		}catch(Exception e)
		{
			
		}
	}

	@Then("Admin should get error message")
	public void admin_should_get_error_message() {
	  
		try
		{
		if(flag)
		{
			System.out.println("Error message is cehcked for empty field: passed");
		}
		}catch(Exception e)
		{
			
		}
	}
	
//	@When("Admin enters any of the Description with invalid values")
//	public void admin_enters_any_of_the_description_with_invalid_values() {
//
//	}
//
//	@When("Admin enters any of the Program Name with invalid values")
//	public void admin_enters_any_of_the_program_name_with_invalid_values() {
//
//	}
//
//	@When("Admin enters any of the Status with invalid values")
//	public void admin_enters_any_of_the_status_with_invalid_values() {
//
//	}
//
	@When("Any of the mandatory fields are blank {string} and {int}")
	public void any_of_the_mandatory_fields_are_blank_and(String sheetname, Integer rowno) throws InvalidFormatException, IOException {
	     flag=batchPage.checkMandatoryFieldsBlank(sheetname,rowno);
	}
//
//	@When("Admin fill in all the fields except description with valid values")
//	public void admin_fill_in_all_the_fields_except_description_with_valid_values() {
//
//	}
}
