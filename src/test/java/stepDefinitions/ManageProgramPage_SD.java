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
import pageObjects.ValidateSortingProgramTable;

public class ManageProgramPage_SD {
	
	private WebDriver driver;
	TestContext testcontext;
	private static final Logger logger = LogManager.getLogger(ManageProgramPage_SD.class);
	String expectedUrl = "https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login";
	LoginPage login;
	ManageProgramPage manageProgramPage;
	
	public ManageProgramPage_SD(TestContext testContext) {
		this.testcontext = testContext;
		System.out.println("*** this is testcontext: ****" +testContext);
		driver = testContext.getWebDriverManager().getDriver();

		login = testcontext.getPageObjectManager().getLoginPage();
		manageProgramPage = testcontext.getPageObjectManager().getManageProgramPage();
	
		
	}
	
	
	@Given("Admin is logging in with valid credentials")
	public void admin_is_logging_in_with_valid_credentials() {
		login.login();
//	   testcontext.getPageObjectManager().getLoginPage().sendUsername();
//	   testcontext.getPageObjectManager().getLoginPage().sendPassword();
//	   testcontext.getPageObjectManager().getLoginPage().clickSubmit();
	}

	@When("Admin clicks on the Program button in the navigation bar")
	public void admin_clicks_on_the_program_button_in_the_navigation_bar() throws Exception {
		manageProgramPage.clickProgramButton();
	   
	}

	@Given("Admin is on Manage Program page")
	public void admin_is_on_manage_program_page() {
		manageProgramPage.verifyManageProgramPageHeader();
	}

	@When("Admin clicks on Batch link on Manage Program page")
	public void admin_clicks_on_batch_link_on_manage_program_page() {
		manageProgramPage.clickBatchButton();
	}

	@Then("Admin is re-directed to Batch page")
	public void admin_is_re_directed_to_batch_page() {
		manageProgramPage.verifyManageBatchPageHeader();
	}

	@When("Admin clicks on User link on Manage Program page")
	public void admin_clicks_on_user_link_on_manage_program_page() {
		manageProgramPage.clickUserButton();
	}

	@Then("Admin is re-directed to User page")
	public void admin_is_re_directed_to_user_page() {
		manageProgramPage.verifyManageUserPageHeader();
	}

	@When("Admin clicks on Logout link on Manage Program page")
	public void admin_clicks_on_logout_link_on_manage_program_page() {
		manageProgramPage.clickLogoutButton();
	}

	@Then("Admin is re-directed to Login page")
	public void admin_is_re_directed_to_login_page() {
		manageProgramPage.assertPage();
	}
	@Given("Admin is logged in with valid credentials and admin is on the manage program page")
	public void admin_is_logged_in_with_valid_credentials_and_admin_is_on_the_manage_program_page() {
		login.login();
		manageProgramPage.clickProgramButton();
		
	}

	@When("Admin clicks Next page link on the program table")
	public void admin_clicks_next_page_link_on_the_program_table() {
	    
	}

	@Then("Admin should see the Pagination has {string} active link")
	public void admin_should_see_the_pagination_has_active_link(String string) {
	    
	}

	@When("Admin clicks Last page link")
	public void admin_clicks_last_page_link() {
	    
	}

	@Then("Admin should see the last page record on the table with Next page link are disabled")
	public void admin_should_see_the_last_page_record_on_the_table_with_next_page_link_are_disabled() {
	
	}

	@When("Admin clicks First page link")
	public void admin_clicks_first_page_link() {
	    
	}

	@Then("Admin should see the previous page record on the table with pagination has previous page link")
	public void admin_should_see_the_previous_page_record_on_the_table_with_pagination_has_previous_page_link() {
	    
	}

	@When("Admin clicks Start page link")
	public void admin_clicks_start_page_link() {
	    
	 
	}

	@Then("Admin should see the very first page record on the table with Previous page link are disabled")
	public void admin_should_see_the_very_first_page_record_on_the_table_with_previous_page_link_are_disabled() {
	    
	}
	
	@When("Admin clicks the sort icon of program name columnn I complete action")
	public void admin_clicks_the_sort_icon_of_program_name_columnn_i_complete_action() {
		manageProgramPage.pogramWebTableDisplayed();
		manageProgramPage.clickSortIcon(manageProgramPage.getprogramNameSortIcon());;
	
	}

	@Then("The data get sorted on the table based on the program name column values in ascending order")
	public void the_data_get_sorted_on_the_table_based_on_the_program_name_column_values_in_ascending_order() {
		manageProgramPage.sortAscendingOrder(manageProgramPage.getSortingProgramName());
	}

	@Given("The data is in the ascending order on the table based on Program Name column")
	public void the_data_is_in_the_ascending_order_on_the_table_based_on_program_name_column() {
		manageProgramPage.clickSortIcon(manageProgramPage.getprogramNameSortIcon());
	}

	@When("Admin clicks the sort icon of program name column")
	public void admin_clicks_the_sort_icon_of_program_name_column() {
		manageProgramPage.clickSortIcon(manageProgramPage.getprogramNameSortIcon());
	}

	@Then("The data get sorted on the table based on the program name column values in descending order")
	public void the_data_get_sorted_on_the_table_based_on_the_program_name_column_values_in_descending_order() {
		manageProgramPage.sortDescendingOrder(manageProgramPage.getSortingProgramName());
	}

	@When("Admin clicks the sort icon of program Desc column")
	public void admin_clicks_the_sort_icon_of_program_desc_column() throws InterruptedException {
		manageProgramPage.clickSortIcon(manageProgramPage.getprogramDescSortIcon());
	}

	@Then("The data get sorted on the table based on the program description column values in ascending order")
	public void the_data_get_sorted_on_the_table_based_on_the_program_description_column_values_in_ascending_order() {
		manageProgramPage.sortAscendingOrder(manageProgramPage.getSortingProgramDesc());
	}

	@Given("The data is in the ascending order on the table based on Program Description column")
	public void the_data_is_in_the_ascending_order_on_the_table_based_on_program_description_column() {
		manageProgramPage.clickSortIcon(manageProgramPage.getprogramDescSortIcon());
	}

	@Then("The data get sorted on the table based on the program description column values in descending order")
	public void the_data_get_sorted_on_the_table_based_on_the_program_description_column_values_in_descending_order() {
		manageProgramPage.sortDescendingOrder(manageProgramPage.getSortingProgramDesc());
	}

	@When("Admin clicks the sort icon of program Status column")
	public void admin_clicks_the_sort_icon_of_program_status_column() {
		manageProgramPage.clickSortIcon(manageProgramPage.getprogramStatusSortIcon());
	}

	@Then("The data get sorted on the table based on the program status column values in ascending order")
	public void the_data_get_sorted_on_the_table_based_on_the_program_status_column_values_in_ascending_order() {
		manageProgramPage.sortAscendingOrder(manageProgramPage.getSortingProgramStatus());
	}

	@Given("The data is in the ascending order on the table based on Program Status column")
	public void the_data_is_in_the_ascending_order_on_the_table_based_on_program_status_column() {
		manageProgramPage.clickSortIcon(manageProgramPage.getprogramStatusSortIcon());
	}

	@Then("The data get sorted on the table based on the program status column values in descending order")
	public void the_data_get_sorted_on_the_table_based_on_the_program_status_column_values_in_descending_order() {
		manageProgramPage.sortDescendingOrder(manageProgramPage.getSortingProgramStatus());
	}
	
	@When("Admin clicks any checkbox in the data table")
	public void admin_clicks_any_checkbox_in_the_data_table() {
		manageProgramPage.clickCheckbox();
	}

	@Then("Admin should see common delete option enabled under header Manage Program")
	public void admin_should_see_common_delete_option_enabled_under_header_manage_program() {
		manageProgramPage.verifyDeleteIcon();
	}

	@Given("Admin clicks delete button under header after selecting the check box in the data table")
	public void admin_clicks_delete_button_under_header_after_selecting_the_check_box_in_the_data_table() {
	  
	}

	@Given("Admin is on Confirm Deletion alert")
	public void admin_is_on_confirm_deletion_alert() {
	
	}

	@When("Admin clicks <YES> button on the alert")
	public void admin_clicks_yes_button_on_the_alert() {
	
	}

	@Then("Admin should land on Manage Program page and can see the selected program is deleted from the data table")
	public void admin_should_land_on_manage_program_page_and_can_see_the_selected_program_is_deleted_from_the_data_table() {
		String program = manageProgramPage.getProgramName();
		   try {
			   manageProgramPage.deleteFunction(program,manageProgramPage.checkBox(),
					   manageProgramPage.HeaderDeletButton(), manageProgramPage.yesButton());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("Admin clicks <NO> button on the alert")
	public void admin_clicks_no_button_on_the_alert() {
		
	}

	@Then("Admin should land on Manage Program page and can see the selected program is not deleted from the data table")
	public void admin_should_land_on_manage_program_page_and_can_see_the_selected_program_is_not_deleted_from_the_data_table() {
		String program = manageProgramPage.getProgramName();
		   try {
			   manageProgramPage.deleteFunctionWithNoOption(program,manageProgramPage.checkBox(),
					   manageProgramPage.HeaderDeletButton(), manageProgramPage.noButton());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Given("Admin clicks delete button under header after selecting multiple checkboxes in the data table")
	public void admin_clicks_delete_button_under_header_after_selecting_multiple_checkboxes_in_the_data_table() {
		String program = manageProgramPage.getProgramName();
		   try {
			   manageProgramPage.deleteFunction(program,manageProgramPage.selectMultipleCheckBox(),
					   manageProgramPage.HeaderDeletButton(), manageProgramPage.yesButton());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Then("Admin should land on Manage Program page and can see the selected programs are deleted from the data table")
	public void admin_should_land_on_manage_program_page_and_can_see_the_selected_programs_are_deleted_from_the_data_table() {
		String program = manageProgramPage.getProgramName();
		   try {
			   manageProgramPage.deleteFunction(program,manageProgramPage.selectMultipleCheckBox(),
					   manageProgramPage.HeaderDeletButton(), manageProgramPage.yesButton());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("Admin should land on Manage Program page and can see the selected programs are not deleted from the data table")
	public void admin_should_land_on_manage_program_page_and_can_see_the_selected_programs_are_not_deleted_from_the_data_table() {
		String program = manageProgramPage.getProgramName();
		   try {
			   manageProgramPage.deleteFunctionWithNoOption(program,manageProgramPage.selectMultipleCheckBox(),
					   manageProgramPage.HeaderDeletButton(), manageProgramPage.noButton());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}