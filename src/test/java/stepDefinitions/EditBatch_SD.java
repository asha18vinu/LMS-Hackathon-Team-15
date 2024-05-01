package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import context.TestContext;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;

public class EditBatch_SD {
	
	
	TestContext context;
	BatchPage batchPage;
	WebDriver driver;
	String actualerrorMsg;
	static boolean flag;
	static String batchNametoEdit;
	private static final Logger logger = LogManager.getLogger(Hooks.class);
	

	public EditBatch_SD(TestContext context) {
		this.context = context;
		batchPage = context.getPageObjectManager().getBatchPage();
	}
	
	@Given("The edit icon on row level in data table is enabled")
	public void the_edit_icon_on_row_level_in_data_table_is_enabled() {
	    batchPage.checkEditIconisEnabled();
	}

	@When("Admin clicks the edit icon")
	public void admin_clicks_the_edit_icon() throws InterruptedException {		
	   batchPage.clicksTheEditIcon();
	}
	
	@When("Admin clicks the edit icon to edit")
	public void admin_clicks_the_edit_icon_to_edit() throws InterruptedException {
		batchNametoEdit=batchPage.checkForCreatedBranchToEdit();
	   batchPage.clicksTheEditIcon();
	}

	@When("Update the fields with valid values and click save {string} and {int}")
	public void update_the_fields_with_valid_values_and_click_save(String sheetName,Integer rowno) throws InvalidFormatException, IOException  {
	   flag=batchPage.updateBatchDetails(sheetName,rowno,batchNametoEdit);
	}

	@Then("The updated batch details should appear on the data table")
	public void the_updated_batch_details_should_appear_on_the_data_table() throws InterruptedException {
//	   if(flag==true)
//	   {
//		      //batchPage.checkForTheAddedBatch();
//	   }
//	   else 
//		   logger.info("Batch Is not added after erasing decriptional field");
	   
	   Assert.assertTrue(flag);
	}

	@When("Update the fields with invalid values and click save {string} and {int}")
	public void update_the_fields_with_invalid_values_and_click_save(String sheetName,Integer rowno) throws InvalidFormatException, IOException, InterruptedException {
		 flag= batchPage.checkInvalidValuesinUpdate(sheetName,rowno);
	}

	@Then("Error message should appear")
	public void error_message_should_appear() {
		try
		{
		assertEquals(flag,true, "Admin didnt get the Expected eror msg");
		}catch(Exception e)
		{
			
		}
	}

	@When("Erase data from mandatory field {string} and {int}")
	public void erase_data_from_mandatory_field(String sheetName,Integer rowno) throws InvalidFormatException, IOException, InterruptedException {
		flag= batchPage.checkInvalidValuesinUpdate(sheetName,1);
	}

	@When("Erase data from description field")
	public void erase_data_from_description_field() throws InterruptedException {
	   flag=batchPage.clearDescriptionalField();
	}

}
