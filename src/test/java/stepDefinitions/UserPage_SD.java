package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import commonUtilities.CommonUtils;
import commonUtilities.LoggerLoad;
import context.TestContext;
import dataFilesReader.ExcelFileData;
import dataFilesReader.ExcelFileSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserPage_SD {
	
	private TestContext testContext;
	private LoginPage loginPage;
	private HomePage homePage;
	private UserPage_VerifySort userVerifySort;
	private UserPage_Edit userPage_Edit;
	private CommonUtils commonUtils;
	private String title;
	private static final Logger logger = LogManager.getLogger(HomePage_SD.class);

	public UserPage_SD(TestContext testcontext) {
		this.testContext = testcontext;
		this.homePage = testContext.getPageObjectManager().getHomePage();
		this.loginPage = testContext.getPageObjectManager().getLoginPage();
		this.userVerifySort=testcontext.getPageObjectManager().getUserVerifySort();
		this.userPage_Edit=testcontext.getPageObjectManager().getUserPage_Edit();
		this.commonUtils = testContext.getCommonUtils();
	}
	
	
	//Verify User Sort
	@Given("Admin is on dashboard page after Login and clicks User on the navigation bar")
	public void admin_is_on_dashboard_page_after_login_and_clicks_User_on_the_navigation_bar() {
	 
	   userVerifySort.verifyDashboardPage();
	}
	

	@Given("Admin is on Manage User Page")
	public void Admin_is_on_Manage_User_Page() {
	 
	 String title=commonUtils.getCurrentUrl();
	 System.out.println("*******User Manage page*********"+ title);
	}

	@When("Admin clicks on ID sort icon")
	public void admin_clicks_on_id_sort_icon() {
	 
	   userVerifySort.SortByUserId();
	}

	@Then("Admin should see User details are sorted by id")
	public void admin_should_see_user_details_are_sorted_by_id() {
	 
	   
	}
	//User Edit step definitions

}
