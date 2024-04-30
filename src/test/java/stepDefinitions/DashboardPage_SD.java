package stepDefinitions;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardPage_SD {
	
	WebDriver driver;
	TestContext testcontext;
	long pageLoadStartTime;
	
	private static final Logger logger = LogManager.getLogger(DashboardPage_SD.class); 
	
	
	public DashboardPage_SD(TestContext testcontext) {
		this.testcontext = testcontext;		
		driver=testcontext.getWebDriverManager().getDriver(); 		
	}
	
	
	@Given("Admin is in Home Page")
	public void admin_is_in_home_page() {
		logger.info("Login Page: Admin is on the login page");
	}
	
	@When("Admin enter valid credentials and clicks login button")
	public void admin_enter_valid_credentials_and_clicks_login_button() {
		driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("sdetorganizers@gmail.com");
		driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("UIHackathon@02");
		driver.findElement(By.xpath("//button[@*='submit']")).click();
		pageLoadStartTime = System.currentTimeMillis();
	}
	
	@When("Admin enter invalid credentials  and clicks login button")
	public void admin_enter_invalid_credentials_and_clicks_login_button() {
		driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("invaliduser@test.com");
		driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("invalidPassword");
		driver.findElement(By.xpath("//button[@*='submit']")).click();
		pageLoadStartTime = System.currentTimeMillis();
	}
	
	
	@Then("Admin should see manage program as header")
	public void admin_should_see_manage_program_as_header() {
		WebElement titleElement = driver.findElement(By.className("mat-card-title"));
		Assert.assertTrue(StringUtils.contains(titleElement.getText(), "Manage Program"), "Title do not match");
	}
	
	@Then("Maximum navigation time in milliseconds, defaults to {int} seconds")
	public void maximum_navigation_time_in_milliseconds_defaults_to_seconds(Integer defaultSecs) {
	    long pageLoadEndTime = System.currentTimeMillis();
	    Assert.assertTrue((pageLoadEndTime - pageLoadStartTime) < (defaultSecs * 1000), "Dashboard page load time took more than expected");
	}
	
	@Then("Admin should see LMS -Learning management system  as title")
	public void admin_should_see_lms_learning_management_system_as_title() {
		WebElement toolbar = driver.findElement(By.className("mat-toolbar"));
		WebElement span = toolbar.findElement(By.xpath("//span"));
		Assert.assertEquals(span.getText(), "LMS - Learning Management System", "Title do not match");
	}
	
	@Then("LMS title should be on the top left corner of page")
	public void lms_title_should_be_on_the_top_left_corner_of_page() {
	    String title = driver.getTitle();
	    Assert.assertEquals(title, "LMS", "Title do not match");
	}
	
	@Then("Admin should see correct spelling in navigation bar text")
	public void admin_should_see_correct_spelling_in_navigation_bar_text() {
		WebElement toolbar = driver.findElement(By.className("mat-toolbar"));
		WebElement span = toolbar.findElement(By.xpath("//span"));
		Assert.assertEquals(span.getText(), "LMS - Learning Management System", "LMS Title do not match");
		
		WebElement programButton = toolbar.findElement(By.ById.id("program"));
		Assert.assertEquals(programButton.getText(), "Program", "Program Title do not match");
		
		WebElement batchButton = toolbar.findElement(By.ById.id("batch"));
		Assert.assertEquals(batchButton.getText(), "Batch", "Batch Title do not match");
		
		WebElement userButton = toolbar.findElement(By.ById.id("user"));
		Assert.assertEquals(userButton.getText(), "User", "User Title do not match");
		
		WebElement logoutButton = toolbar.findElement(By.ById.id("logout"));
		Assert.assertEquals(logoutButton.getText(), "Logout", "Logout Title do not match");
	}
	
	@Then("Admin should see error message {string}")
	public void admin_should_see(String errorMessage) {
		WebElement error = driver.findElement(By.ById.id("errormessage"));
		Assert.assertEquals(error.getText(), errorMessage, "Error message do not match");
	}
	
	@Then("Admin should see {string} in the 1st place")
	public void admin_should_see_in_the_1st_place(String buttonText) {
		WebElement toolbar = driver.findElement(By.className("mat-toolbar"));
		List<WebElement> buttons = toolbar.findElements(By.className("mat-button"));
		Assert.assertEquals(buttons.get(0).getText(), buttonText, buttonText + " is not first element");
	}
	
	@Then("Admin should see {string} in the 2nd place")
	public void admin_should_see_in_the_2nd_place(String buttonText) {
		WebElement toolbar = driver.findElement(By.className("mat-toolbar"));
		List<WebElement> buttons = toolbar.findElements(By.className("mat-button"));
		Assert.assertEquals(buttons.get(1).getText(), buttonText, buttonText + " is not second element");
	}
	
	@Then("Admin should see {string} in the 3rd place")
	public void admin_should_see_in_the_3rd_place(String buttonText) {
		WebElement toolbar = driver.findElement(By.className("mat-toolbar"));
		List<WebElement> buttons = toolbar.findElements(By.className("mat-button"));
		Assert.assertEquals(buttons.get(2).getText(), buttonText, buttonText + " is not third element");
	}
	
	@Then("Admin should see {string} in the 4th place")
	public void admin_should_see_in_the_4th_place(String buttonText) {
		WebElement toolbar = driver.findElement(By.className("mat-toolbar"));
		List<WebElement> buttons = toolbar.findElements(By.className("mat-button"));
		Assert.assertEquals(buttons.get(3).getText(), buttonText, buttonText + " is not fourth element");
	}


}
