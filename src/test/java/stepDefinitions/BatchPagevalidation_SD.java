package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import context.TestContext;
import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;

public class BatchPagevalidation_SD {

	TestContext context;
	BatchPage batchPage;
	WebDriver driver;
	String actualerrorMsg;
	boolean flag;
	private static final Logger logger = LogManager.getLogger(Hooks.class);

	public BatchPagevalidation_SD(TestContext context) {
		this.context = context;
		batchPage = context.getPageObjectManager().getBatchPage();
	}

	@Then("Admin should see the {string} in the url")
	public void admin_should_see_the_in_the_url(String expectedString) {
		batchPage.checkURL(expectedString);
	}

	@Then("Admin should see the {string} in the header")
	public void admin_should_see_the_in_the_header(String headerText) {
		batchPage.checkHeader(headerText);
	}

	@Then("Admin should see the pagination controls under the data table")
	public void admin_should_see_the_pagination_controls_under_the_data_table() {
		batchPage.checkPaginatorElementsDisplayed();
	}

	@Then("Admin Should see the data table with headers {string} and {int}")
	public void admin_should_see_the_data_table_with_headers(String sheetName, Integer rowno)
			throws InvalidFormatException, IOException {
		  batchPage.getTableHeader(sheetName, rowno);
	}

	@Then("Admin should be able to see the {string} icon button that is disabled")
	public void admin_should_be_able_to_see_the_icon_button_that_is_disabled(String string) {
		batchPage.checkDeleteButton();
	}
	
	


	@Then("Admin should be able to see the {string} button")
	public void admin_should_be_able_to_see_the_button(String string) {
		batchPage.checkNewBatchStringPresent(string);
	}


	@Then("Each row in the data table should have a checkboxx")
	public void each_row_in_the_data_table_should_have_a_checkboxx() {

		batchPage.checkDataTableForChkBox();
	}
	
}
