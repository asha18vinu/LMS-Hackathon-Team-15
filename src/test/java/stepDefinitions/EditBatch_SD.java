package stepDefinitions;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;

public class EditBatch_SD {
	
	
	TestContext context;
	BatchPage batchPage;
	WebDriver driver;
	String actualerrorMsg;
	boolean flag;

	public EditBatch_SD(TestContext context) {
		this.context = context;
		batchPage = context.getPageObjectManager().getBatchPage();
	}
	
	@Given("The edit icon on row level in data table is enabled")
	public void the_edit_icon_on_row_level_in_data_table_is_enabled() {
	    batchPage.checkEditIconisEnabled();
	}

	@When("Admin clicks the edit icon")
	public void admin_clicks_the_edit_icon() {
	   batchPage.clicksTheEditIcon();
	}

	@When("Update the fields with valid values and click save {string} and {int}")
	public void update_the_fields_with_valid_values_and_click_save(String sheetName,Integer rowno) throws InvalidFormatException, IOException {
	   batchPage.updateBatchDetails(sheetName,rowno);
	}

	@Then("The updated batch details should appear on the data table")
	public void the_updated_batch_details_should_appear_on_the_data_table() {
	   batchPage.verifyBatchUpdated();
	}

	@When("Update the fields with invalid values and click save")
	public void update_the_fields_with_invalid_values_and_click_save() {
	   
	}

	@Then("Error message should appear")
	public void error_message_should_appear() {
	  
	}

	@When("Erase data from mandatory field")
	public void erase_data_from_mandatory_field() {
	  
	}

	@When("Erase data from description field")
	public void erase_data_from_description_field() {
	   
	}

}
