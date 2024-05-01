package stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignStudent_SD {
	
	WebDriver driver;
	TestContext testcontext;
	
	//private static final Logger logger = LogManager.getLogger(AssignStudent_SD.class); 
	
	public AssignStudent_SD(TestContext testcontext) {
		this.testcontext = testcontext;		
		driver = testcontext.getWebDriverManager().getDriver(); 		
	}
	
	@Given("User login in and navigate to User Module")
	public void user_login_in_and_navigate_to_user_module() {
		driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("sdetorganizers@gmail.com");
		driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("UIHackathon@02");
		driver.findElement(By.xpath("//button[@*='submit']")).click();
		
		driver.findElement(By.xpath("//button[@*='user']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

@Given("Admin is on Manage User Page after clicks User on the navigation bar")
public void admin_is_on_manage_user_page_after_clicks_user_on_the_navigation_bar() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

	@Given("Admin is in manage user page")
	public void admin_is_in_manage_user_page() {
	    
	}

	@When("Admin clicks Assign Student button")
	public void admin_clicks_assign_student_button() 
	{
		driver.findElement(By.xpath("//button[@*='Assign']")).click();  
	}

	@Then("Admin should see a pop up open for assign student details with empty form along with Save and Cancel button and close \\(X) icon on the top right corner of the window")
	public void admin_should_see_a_pop_up_open_for_assign_student_details_with_empty_form_along_with_save_and_cancel_button_and_close_x_icon_on_the_top_right_corner_of_the_window()
	{
		Assert.assertNotNull(driver.findElement(By.xpath("//label['Cancel']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//label['Save']")));

		Assert.assertNotNull(driver.findElement(By.className("p-dialog-header-close")));
	}

@Then("Admin should see User Role as R03,and other fields Student Email id,Program Name,Batch Name and Status with respective input boxes.")

public void admin_should_see_user_role_as_r03_and_other_fields_student_email_id_program_name_batch_name_and_status_with_respective_input_boxes() 
{
	Assert.assertNotNull(driver.findElement(By.name("roleId")));
	Assert.assertNotNull(driver.findElement(By.name("userId")));
	Assert.assertNotNull(driver.findElement(By.name("programName")));
	Assert.assertNotNull(driver.findElement(By.name("batchName")));
	WebElement radioElement=driver.findElement(By.className("radio"));
	List<WebElement> statusElements=radioElement.findElements(By.className("ng-star-inserted"));
	Assert.assertEquals(statusElements.get(0).getText(),"Active");
	Assert.assertEquals(statusElements.get(1).getText(),"Inactive");
}
	
	@Then("Admin should see drop down boxes with valid datas for Student Email id,Program Name and Batch Name")
	public void admin_should_see_drop_down_boxes_with_valid_datas_for_student_email_id_program_name_and_batch_name()
	{
		driver.findElement(By.name("userId")).click();
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions
		         .presenceOfNestedElementsLocatedBy(By.xpath("//li[@role='option']"), By.xpath("//li[@role='option']")));
		List<WebElement> allOptions = driver.findElements(By.xpath("//li[@role='option']"));
		allOptions.get(0).click();
		
		Assert.assertNotNull(driver.findElement(By.name("programName")).getAttribute("value"));
		Assert.assertNotNull(driver.findElement(By.name("batchName")).getAttribute("value"));
	}
	@Then("Admin should see two radio button for Status")
	public void admin_should_see_two_radio_button_for_status() 
	{
		WebElement radioElement=driver.findElement(By.className("radio"));
		List<WebElement> statusElements=radioElement.findElements(By.className("ng-star-inserted"));
		Assert.assertEquals(statusElements.get(0).getText(),"Active");
		Assert.assertEquals(statusElements.get(1).getText(),"Inactive");
	}
	
	@Given("Admin is in Assign Student details pop up page")
	public void admin_is_in_assign_student_details_pop_up_page() {
		driver.findElement(By.xpath("//button[@*='user']")).click();
		driver.findElement(By.xpath("//button[@*='Assign']")).click();
		
	}

	@When("Admin clicks Save button with entering any data")
	public void admin_clicks_save_button_with_entering_any_data() {
		driver.findElement(By.xpath("//button[@label='Save']")).click();
	}

	@Then("Admin gets a Error message alert")
	public void admin_gets_a_error_message_alert() {
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='User Email Id is required.']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='Program Name is required.']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='Batch Name is required.']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='Status is required.']")));
	}

@When("Admin clicks Cancel or Close Icon on Assign Student form")
public void admin_clicks_cancel_close_x_icon_on_assign_student_form() 
{

	driver.findElement(By.className("p-dialog-header-close")).click();
}

@Then("Assign Student popup window should be closed without saving")
public void assign_student_popup_window_should_be_closed_without_saving()
{
	try {
		Thread.sleep(3000l);
	} catch (InterruptedException e)
	{
		e.printStackTrace();
	}
	Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']")).size(), 0);
}
@When("Admin clicks Cancel on  button")
public void admin_clicks_cancel_on_button()
{
	driver.findElement(By.xpath("//label['Cancel']")).click();
	
}

@Then("Admin can see the Assign Student popup disappears without assigning")
public void admin_can_see_the_assign_student_popup_disappears_without_assigning() 
{
	Assert.assertNotNull(driver.findElement(By.xpath("//label['Cancel']")));
}

}
  



  


		
	    


