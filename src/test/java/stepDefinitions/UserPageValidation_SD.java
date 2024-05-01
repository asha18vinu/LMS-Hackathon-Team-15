package stepDefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import managers.FileReaderManager;
import commonUtilities.CommonUtils;
import commonUtilities.LoggerLoad;
import commonUtilities.Pagination;
import pageObjects.UserPageValidation;
import context.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserPageValidation_SD 
{
	WebDriver driver;
	TestContext testcontext;
	UserPageValidation userPage;
	CommonUtils commonUtils;
	Pagination pagination;
	private static final Logger logger = LogManager.getLogger(UserPageValidation_SD.class); 
	
	
	public UserPageValidation_SD(TestContext testcontext)
	{
		this.testcontext = testcontext;		
		//driver=testcontext.getWebDriverManager().getDriver(); 
		userPage = testcontext.getPageObjectManager().getUserpage();
		commonUtils = testcontext.getPageObjectManager().getCommonUtils();
		pagination = testcontext.getPageObjectManager().getPagination();
	}
	
	@Given("Admin login with valid credentials")
	public void admin_login_with_valid_credentials()
	{
	    userPage.login();
	    logger.info("Login Successful");
	}

	@Given("Admin is logged on the Lms portal dashboardPage after login")
	public void admin_is_logged_on_the_lms_portal_dashboard_page_after_login()
	{
		logger.info("Admin is in Dashboard Page");
	}

	
	@When("Admin clicks {string} from navigation bar")
	public void admin_clicks_from_navigation_bar(String moduleName) 
	{
	    userPage.clickNavigationBarElement(moduleName);
	    logger.info("Admins clicks User from Navigation Bar");
	}

	
	@Then("Admin should see the {string} in the URL")
	public void admin_should_see_the_in_the_url(String userPageUrl) 
	{
		String currentURL = commonUtils.getCurrentUrl();
		Assert.assertTrue(currentURL.contains(userPageUrl));
		logger.info("Expected - "+userPageUrl+ "in the URL - "+currentURL);
	}
	

	@Then("Admin should see a heading with text {string} on the page")
	public void admin_should_see_a_heading_with_text_on_the_page(String userPageHeader) 
	{
		//String header = commonUtils.getTitle();
		String header = userPage.getPageHeader();
		Assert.assertEquals(header, userPageHeader);
		logger.info("Expected Page Header - "+userPageHeader+" Actual Header - "+header);
	}

	@Then("Admin should see the text as {string} along with Pagination icon below the table with x- starting record number on that page,y-ending record number on that page,z-Total number of records")
	public void admin_should_see_the_text_as_along_with_pagination_icon_below_the_table_with_x_starting_record_number_on_that_page_y_ending_record_number_on_that_page_z_total_number_of_records(String txt) throws InterruptedException 
	{   
	   Boolean footerFlag= pagination.verifyFooterText("users");
	   Boolean testFlag = pagination.verifyPaginationText();
	   Assert.assertEquals(testFlag,true,"Pagination Text is not Displayed correctly");
	   Assert.assertEquals(footerFlag,true,"Page Footer Text with total record count is not Displayed");
	   
	   if (footerFlag && testFlag)
		   logger.info("Pagination values Displayed");
	   else
		   logger.info("Pagination values Not Displayed");
	   
	}

	@Then("Admin Should see the data table with column names {string}, {string}, {string}, {string}, {string}")
	public void admin_should_see_the_data_table_with_column_names(String Id, String Name, String Location, String Phone, String EditDel)
	{
	   List<String> ColumnNames = userPage.getTableHeader();
	   
	   Assert.assertTrue(ColumnNames.contains(Id));
	   Assert.assertTrue(ColumnNames.contains(Name));
	   Assert.assertTrue(ColumnNames.contains(Location));
	   Assert.assertTrue(ColumnNames.contains(Phone));
	   Assert.assertTrue(ColumnNames.contains(EditDel));
	   
	}

	@Then("Admin should see a Delete button on the top left hand side as Disabled")
	public void admin_should_see_a_delete_button_on_the_top_left_hand_side_as_disabled() 
	{
	    Boolean deleteIconFlag = userPage.isDeleteIconDisabled();
	    Assert.assertEquals(deleteIconFlag,true);
	    //commonUtils.getAssertionEqualsCheck(deleteIconFlag, true);
	    if(deleteIconFlag)
	    	logger.info("Delete Icon is Disabled");
	    else
	    	logger.info("Delete Icon is Not Disabled");
	}

	@Then("Admin should be able to see the + A New User button above the data table")
	public void admin_should_be_able_to_see_the_a_new_user_button_above_the_data_table() 
	{
		Boolean newUserFlag = userPage.isAddNewUserDisplayed();
		Assert.assertEquals(newUserFlag,true);
	    if(newUserFlag)
	    	logger.info("New User Button is Displayed");
	    else
	    	logger.info("New User Button is Not Displayed");
	}

	@Then("Admin should be able to see the + Assign staff button above the data table")
	public void admin_should_be_able_to_see_the_assign_staff_button_above_the_data_table() 
	{
		Boolean assignStaffFlag = userPage.isAssignStaffDisplayed();
		Assert.assertEquals(assignStaffFlag,true);
	    if(assignStaffFlag)
	    	logger.info("Assign Staff Button is Displayed");
	    else
	    	logger.info("Assign Staff Button is Not Displayed");
	}

	@Then("Admin should be able to see the + Assign Student button above the data table")
	public void admin_should_be_able_to_see_the_assign_student_button_above_the_data_table() 
	{
		Boolean assignStudentFlag = userPage.isAssignStudentDisplayed();
		Assert.assertEquals(assignStudentFlag,true);
	    if(assignStudentFlag)
	    	logger.info("Assign Student Button is Displayed");
	    else
	    	logger.info("Assign Student Button is Not Displayed");
	}


	@Then("Admin should be able to see the search text box above the data table")
	public void admin_should_be_able_to_see_the_search_text_box_above_the_data_table()
	{
		Boolean searchFlag = userPage.isSearchTextDisplayed();
		Assert.assertEquals(searchFlag,true,"Search Text Box is Not Displayed");
	    if(searchFlag)
	    	logger.info("Search Text Box is Displayed");
	    else
	    	logger.info("Search Text Box is Not Displayed");
	}

	@Then("Admin should see five records displayed on the data table")
	public void admin_should_see_five_records_displayed_on_the_data_table() 
	{
		userPage.getTotalRowDisplayed();
	}

	@Then("Each row in the data table should have a checkbox")
	public void each_row_in_the_data_table_should_have_a_checkbox()
	{
	   userPage.isTableCheckBoxDisplayed();
	}

	@Then("Each row in the data table should have a edit icon that is enabled")
	public void each_row_in_the_data_table_should_have_a_edit_icon_that_is_enabled() 
	{
	    userPage.isTableEditIconEnabled();
	}

	@Then("Each row in the data table should have a delete icon that is enabled")
	public void each_row_in_the_data_table_should_have_a_delete_icon_that_is_enabled()
	{
	    userPage.isTableDeleteIconEnabled();
	}

	@Given("Admin is on Manage User Page")
	public void admin_is_on_manage_user_page() 
	{
		//userPage.clickNavigationBarElement("User");
		System.out.println("Page Header - "+userPage.getPageHeader());
	    logger.info("Admin is in Manage User Page");
	}

	@When("Admin enters user name into search box.")
	public void admin_enters_user_name_into_search_box() throws InterruptedException 
	{
	   userPage.inputValueSearchBox("John");
	   logger.info("Search Valid UserName");
	}

	@Then("Admin should see user displayed with the entered name")
	public void admin_should_see_user_displayed_with_the_entered_name() 
	{
		userPage.checkUserSearch();
	}

	@When("Admin enters the keywords not present in the data table on the Search box")
	public void admin_enters_the_keywords_not_present_in_the_data_table_on_the_search_box() throws InterruptedException 
	{
		userPage.inputValueSearchBox("asdf");
		logger.info("Search Invalid UserName");
	}

	@Then("Admin should see zero entries on the data table")
	public void admin_should_see_zero_entries_on_the_data_table() 
	{
	    userPage.footerInvalidSearch();
	}

}
