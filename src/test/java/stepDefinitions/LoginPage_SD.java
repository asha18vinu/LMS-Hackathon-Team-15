package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Report;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginPage_SD  {
	WebDriver driver;
	TestContext testcontext;
	private static final Logger logger = LogManager.getLogger(LoginPage_SD.class); 
	
	
	public LoginPage_SD(TestContext testcontext)
	{
		this.testcontext = testcontext;		
		driver=testcontext.getWebDriverManager().getDriver(); 		
	}
	
	

	@Given("Admin is on the Login page")
	public void admin_is_on_the_Login_page() {
		
		logger.info("Login Page: Admin is on the login page");
	}

	@When("Admin enters the valid username and password")
	public void admin_enters_the_valid_username_and_password() {
		driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("sdetorganizers@gmail.com");
		driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("UIHackathon@02");
		driver.findElement(By.xpath("//button[@*='submit']")).click();
	}

	@Then("I validate the outcomes")
	public void i_validate_the_outcomes() {
		logger.info("Admin is in the Dashboard page");
	}

	@Given("Admin Logout of the application")
	public void admin_Logout_of_the_application() {
	
	}

	@Then("Message displayed successfull")
	public void message_displayed_successfull() {
	   
	}

	@Given("Launch the browser")
	public void launch_the_browser() {
		Reporter.log("First step");
	}

	@Then("open the application")
	public void open_the_application() {
	  // logger.info("WEbsite is opened");logger.error("An error occurred: {}");
	}

}
