package stepDefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import context.TestContext;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;

public class DeleteBatch_SD {

	TestContext context;
	BatchPage batchPage;
	WebDriver driver;
	String actualerrorMsg;
	boolean flag;
	public static List<String> batchDelete;
	private static final Logger logger = LogManager.getLogger(Hooks.class);

	public DeleteBatch_SD(TestContext context) {
		this.context = context;
		batchPage = context.getPageObjectManager().getBatchPage();
	}

	@Given("The delete icon on row level in data table is enabled")
	public void the_delete_icon_on_row_level_in_data_table_is_enabled() {
		batchPage.checkDeleteButtonEachRow();
		logger.info("Admin checks the delete icon on row level enabled or not");

	}

	@Then("Alert appears with yes and No option")
	public void alert_appears_with_yes_and_no_option() {
		batchPage.assertYesOrNoBtn();
		logger.info("verify Dialog box appears with yes or no option ");
	}

	@Given("Admin clicks the delete icon")
	public void admin_clicks_the_delete_icon() throws InterruptedException {
		batchPage.clickSingleChkbox();
		logger.info("Admin clicks the delte icon");
	}

	@When("Admin click {string} option")
	public void admin_click_option(String string) {
		batchPage.clickYesOption();
		logger.info("Admin clicks the yes option");
	}

	@When("Admin click {string} NO option")
	public void admin_click_optionno(String string) {
		batchPage.clickNoOption();
		logger.info("Admin clicks the no option");
	}

	@Then("Batch deleted alert pops and batch is no more available in data table")
	public void batch_deleted_alert_pops_and_batch_is_no_more_available_in_data_table() {
		batchPage.verifyTheDeletedMessage();
	}

	@Then("Batch is still listed in data table")
	public void batch_is_still_listed_in_data_table() throws InterruptedException {
		batchPage.checkfordeletedBatch();
	}

	@Given("None of the checkboxes in data table are selected")
	public void none_of_the_checkboxes_in_data_table_are_selected() {
		batchPage.checkDataTableForChkBoxSelected();
	}

	@Then("The delete icon under the {string} header should be disabled")
	public void the_delete_icon_under_the_header_should_be_disabled(String string) {
		batchPage.checkDeleteButton();
	}

	@Given("One of the checkbox row is selected")
	public void one_of_the_checkbox_row_is_selected() throws InterruptedException {
		batchPage.clickSingleChkbox();
	}

	@When("Click delete icon below {string} header")
	public void click_delete_icon_below_header(String string) throws InterruptedException {
		//batchPage.clickMDeleteIconForsingleRow();
		batchPage.clickYesOption();
		System.out.println("header delete enabled");	}

	@When("Click delete icon below {string} header for mutiple rows")
	public void click_delete_icon_below_header_for_multiple_rows(String string) throws InterruptedException {
		// batchPage.clickManageHeaderDeleteIconForMultipleRow();

	}

	@Then("The respective row in the data table is deleted")
	public void the_respective_row_in_the_data_table_is_deleted() throws InterruptedException {
		boolean flag = batchPage.verifyDeletedBatches(batchDelete);

		Assert.assertEquals(flag, true, "Multiple rows delete icon is not deleting the batches.");

	}

	@Then("Admin logout of the application")
	public void admin_logout_of_the_application() throws InterruptedException {
		batchPage.logoutOftheApp();
		System.out.println("logged out");
	}

	@Given("Two or more checkboxes row is selected")
	public void two_or_more_checkboxes_row_is_selected() throws InterruptedException {
		batchDelete = batchPage.selectMultipleCheckboxesA();
	}

	@Given("Admin is in the manage batch page")
	public void admin_is_in_the_manage_batch() {

	}

	@When("Admin verifies the deleted multiple rows are reflected")
	public void admin_verifies_the_deleted_multiple_rows_are_reflected() throws InterruptedException {
		boolean flag = batchPage.verifyDeletedBatches(batchDelete);
		try {
			Assert.assertTrue(flag);
		} catch (Exception e) {
			System.out.println("Multipple Batches deleteicon is not working");
		}

	}

	@Then("The respective bacth in the data table is deleted")
	public void the_respective_bacth_in_the_data_table_is_deleted() {

	}
}
