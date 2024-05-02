package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;
import pageObjects.LoginPage;

import pageObjects.ManageProgrampage;
import pageObjects.UserSortingPage;

public class UserPageSorting_SD {
	
	private WebDriver driver;
	TestContext testcontext;
	private static final Logger logger = LogManager.getLogger(ManageProgramPage2_SD.class);
	BatchPage batchPage;
	ManageProgrampage manageProgramPage;
	LoginPage login;
	
	UserSortingPage userSortingPage;

	public UserPageSorting_SD(TestContext testContext) {
		this.testcontext = testContext;
		System.out.println("* this is testcontext: **" +testContext);
		driver = testContext.getWebDriverManager().getDriver();
		login = testcontext.getPageObjectManager().getLoginPage();
		userSortingPage = testContext.getPageObjectManager().getUserSortingPage();
		manageProgramPage = testContext.getPageObjectManager().getManageProgramPage();
		batchPage = testContext.getPageObjectManager().getBatchPage();
	}

////	@Given("Admin is logged in with valid credentials")
//	public void admin_is_logged_in_with_valid_credentials() {
////		login.login();
//	}
////
//	@Given("Admin is on Manage User Page")
//	public void admin_is_on_manage_user_page() {
//		 
//	}

	@When("Admin clicks on ID sort iconn")
	public void admin_clicks_on_id_sort_iconn() {
		manageProgramPage.clickSortIcon(userSortingPage.getUserIDSortIcon());
		manageProgramPage.clickSortIcon(userSortingPage.getUserIDSortIcon());
	}

	@Then("Admin should see User details are sorted by idd")
	public void admin_should_see_user_details_are_sorted_by_idd() {
		manageProgramPage.sortAscendingOrder(userSortingPage.getUserIDList());
	 
	}

	@When("Admin clicks on name sort icon")
	public void admin_clicks_on_name_sort_icon() {
		manageProgramPage.clickSortIcon(userSortingPage.getUserNameSortIcon());
	}

	@Then("Admin should see User details are sorted by name")
	public void admin_should_see_user_details_are_sorted_by_name() {
		manageProgramPage.sortAscendingOrder(userSortingPage.getUserNameList());
		
	}

	@When("Admin clicks on Location sort icon")
	public void admin_clicks_on_location_sort_icon() {
		manageProgramPage.clickSortIcon(userSortingPage.getLocationSortIcon());
	}

	@Then("Admin should see User details are sorted by Location")
	public void admin_should_see_user_details_are_sorted_by_location() {
		manageProgramPage.sortAscendingOrder(userSortingPage.getUserLocationList());
	}

	@When("Admin clicks on Phone number sort icon")
	public void admin_clicks_on_phone_number_sort_icon() {
		manageProgramPage.clickSortIcon(userSortingPage.getPhonenumberSortIcon());
	}

	@Then("Admin should see User details are sorted by Phone number")
	public void admin_should_see_user_details_are_sorted_by_phone_number() {
		manageProgramPage.sortAscendingOrderInteger(userSortingPage.getUserPhoneNumberList());;
	}
	
	@Given("Admin is on Confirm Deletion alert")
	public void admin_is_on_confirm_deletion_alert() {
	    
	}

	@When("Admin clicks on the delete icon")
	public void admin_clicks_on_the_delete_icon() {
		userSortingPage.getdeleteRowIcon().click();
	}

//	@Then("Admin should see a alert open with heading Confirm along with  <YES> and <NO> button for deletion")
//	public void admin_should_see_a_alert_open_with_heading_confirm_along_with_yes_and_no_button_for_deletion() {
//	    userSortingPage.validateDeleteConfirmationWindow();
//	}

	@When("Admin clicks yes option")
	public void admin_clicks_yes_option() {
		userSortingPage.getdeleteRowIcon().click();
	    manageProgramPage.yesButton().click();
	}

	@Then("Admin gets a message <Successful User Deleted alert> and do not see that user in the data table")
	public void admin_gets_a_message_successful_user_deleted_alert_and_do_not_see_that_user_in_the_data_table() {
	    userSortingPage.verifyTheDeletedMessage();
	    
	}

	@When("Admin clicks  No option")
	public void admin_clicks_no_option() throws InterruptedException {
		userSortingPage.getdeleteRowIcon().click();
		Thread.sleep(1000);
	   userSortingPage.clickUserNoButton();
	}

//	@Then("Admin can see the deletion alert disappears without deleting")
//	public void admin_can_see_the_deletion_alert_disappears_without_deleting() {
//	    Assert.assertEquals(false, userSortingPage.isCloseddeleteConfirmWindow());
//	}

	@When("Admin clicks on close button")
	public void admin_clicks_on_close_button() throws InterruptedException {
		userSortingPage.getdeleteRowIcon().click();
		Thread.sleep(1000);
	   userSortingPage.clickCloseButton();
	}

	@Then("Admin can see the deletion alert will disappears without any changes")
	public void admin_can_see_the_deletion_alert_will_disappears_without_any_changes() {
		Assert.assertEquals(false, userSortingPage.isCloseddeleteConfirmWindow());
	}


}
