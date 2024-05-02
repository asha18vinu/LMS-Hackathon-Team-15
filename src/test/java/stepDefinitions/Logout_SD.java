package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Logout_SD
{
	WebDriver driver;
	TestContext testcontext;
	
	private static final Logger logger = LogManager.getLogger(Logout_SD.class); 
	
	public Logout_SD(TestContext testcontext) {
		this.testcontext = testcontext;		
		driver=testcontext.getWebDriverManager().getDriver(); 		
	}
	
	
	@Given("Admin is in dashboard page")
	public void admin_is_in_dashboard_page() {
		driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("sdetorganizers@gmail.com");
		driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("UIHackathon@02");
		driver.findElement(By.xpath("//button[@*='submit']")).click();
	}

	@When("Admin click Logout button on navigation bar")
	public void admin_click_logout_button_on_navigation_bar() {
		driver.findElement(By.xpath("//button[@*='logout']")).click();
	}

	@Then("Admin should land on login in page")
	public void admin_should_land_on_login_in_page() {
		Assert.assertNotNull(driver.findElement(By.xpath("//button[@*='submit']")));
	}
}
