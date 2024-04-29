package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.ManageProgramPage;
import pageObjects.MultipleDeleteProgram;
import pageObjects.NavigationFromManageProgramPage;
import pageObjects.UserSortingPage;
import pageObjects.ValidateSortingProgramTable;

public class SortUser_SD {
	
	private WebDriver driver;
	TestContext testcontext;
	private static final Logger logger = LogManager.getLogger(ManageProgramPage_SD.class);
	
	ManageProgramPage manageProgramPage;
	LoginPage login;
	
	UserSortingPage userSortingPage;

	public SortUser_SD(TestContext testContext) {
		this.testcontext = testContext;
		System.out.println("*** this is testcontext: ****" +testContext);
		driver = testContext.getWebDriverManager().getDriver();
		login = testcontext.getPageObjectManager().getLoginPage();
		userSortingPage = testContext.getPageObjectManager().getUserSortingPage();
		manageProgramPage = testContext.getPageObjectManager().getManageProgramPage();
		
	}

	@Given("Admin is logged in with valid credentials")
	public void admin_is_logged_in_with_valid_credentials() {
		login.login();
	}

	@Given("Admin is on Manage User Page")
	public void admin_is_on_manage_user_page() {
		manageProgramPage.clickUserButton();
	}

	@When("Admin clicks on ID sort icon")
	public void admin_clicks_on_id_sort_icon() {
		manageProgramPage.clickSortIcon(userSortingPage.getUserIDSortIcon());
	}

	@Then("Admin should see User details are sorted by id")
	public void admin_should_see_user_details_are_sorted_by_id() {
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

}
