package stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignStaff_SD 
{
	WebDriver driver;
	TestContext testcontext;
	
	public AssignStaff_SD(TestContext testcontext) 
	{
		this.testcontext = testcontext;		
		driver = testcontext.getWebDriverManager().getDriver(); 
		
	}
	
	@Given("Admin is in Assign Staff details pop up page")
	public void admin_is_in_assign_staff_details_pop_up_page() 
	{
		driver.findElement(By.xpath("//button[@label='Assign Staff']")).click();
	}
	
	@When("Admin clicks Assign Staff button")
	public void admin_clicks_assign_staff_button() 
	{
		driver.findElement(By.xpath("//button[@label='Assign Staff']")).click();
	}

	@Then("Admin should see a pop up open for assign staff details with empty form along with Save and Cancel button and close \\(X) icon on the top right corner of the window")
	public void admin_should_see_a_pop_up_open_for_assign_staff_details_with_empty_form_along_with_save_and_cancel_button_and_close_x_icon_on_the_top_right_corner_of_the_window()
	{
		Assert.assertNotNull(driver.findElement(By.xpath("//label['Cancel']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//label['Save']")));

		Assert.assertNotNull(driver.findElement(By.className("p-dialog-header-close"))); 
	}
	@Then("Admin should see User Role as R02,and other fields Student Email id, Skill, Program Name,Batch Name and Status with respective input boxes")
	public void admin_should_see_user_role_as_r02_and_other_fields_student_email_id_skill_program_name_batch_name_and_status_with_respective_input_boxes() 
	{
		Assert.assertNotNull(driver.findElement(By.name("roleId")));
		Assert.assertNotNull(driver.findElement(By.name("userId")));
		//Assert.assertNotNull(driver.findElement(By.xpath( "//p-dropdown[@id='userId']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//input[@id='skillName']")));
		//Assert.assertNotNull(driver.findElement(By.xpath( "//p-dropdown[@id='programName']")));
		Assert.assertNotNull(driver.findElement(By.id("programName")));
		//Assert.assertNotNull(driver.findElement(By.xpath(" //p-multiselect[@id='batchName']")));
		Assert.assertNotNull(driver.findElement(By.id("batchName")));
		WebElement radioElement=driver.findElement(By.className("radio"));
		List<WebElement> statusElements=radioElement.findElements(By.className("ng-star-inserted"));
		Assert.assertEquals(statusElements.get(0).getText(),"Active");
		Assert.assertEquals(statusElements.get(1).getText(),"Inactive");
	}
	
	@Then("Admin should see drop down boxes with valid datas for Student Email id, Skill, Program Name and Batch Name")
	public void admin_should_see_drop_down_boxes_with_valid_datas_for_student_email_id_program_name_and_batch_name()
	{
		driver.findElement(By.id("userId")).click();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
		w.until(ExpectedConditions
		         .presenceOfNestedElementsLocatedBy(By.xpath("//li[@role='option']"), By.xpath("//li[@role='option']")));
		List<WebElement> allOptions = driver.findElements(By.xpath("//li[@role='option']"));
		//allOptions.get(0).click();
		for(WebElement e : allOptions)
		{
			if("arunasel@gmail.com".equals(e.getText())) 
			{
				e.click();
				break;
			}
		}

		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		driver.findElement(By.id("programName")).click();
		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(3));
		w1.until(ExpectedConditions
		         .presenceOfNestedElementsLocatedBy(By.xpath("//li[@role='option']"), By.xpath("//li[@role='option']")));
		List<WebElement> allOptions1 = driver.findElements(By.xpath("//li[@role='option']"));
		allOptions1.get(0).click();
		
		
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		driver.findElement(By.id("batchName")).click();
//		WebDriverWait w2 = new WebDriverWait(driver, Duration.ofSeconds(3));
//		w2.until(ExpectedConditions
//		         .presenceOfNestedElementsLocatedBy(By.xpath("//li[@class='p-multiselect-item']"), By.xpath("//li[@class='p-multiselect-item']")));
//		
//		List<WebElement> allOptions2 = driver.findElements(By.xpath("//li[@class='p-multiselect-item']"));
//		
//		if(allOptions2.size() > 0) {
//			allOptions2.get(0).click();
//		}
		
	
		//Assert.assertNotNull(driver.findElement(By.id("programName")).getAttribute("value"));
		//Assert.assertNotNull(driver.findElement(By.id("batchName")).getAttribute("value"));
	}
	/*@Then("Admin should see two radio button for Status")
	public void admin_should_see_two_radio_button_for_status() 
	{
		WebElement radioElement=driver.findElement(By.className("radio"));
		List<WebElement> statusElements=radioElement.findElements(By.className("ng-star-inserted"));
		Assert.assertEquals(statusElements.get(0).getText(),"Active");
		Assert.assertEquals(statusElements.get(1).getText(),"Inactive");
	}*/
	
	@When("Admin clicks Save button without entering Student Email id")
	public void admin_clicks_save_button_without_entering_student_email_id() 
	{
		driver.findElement(By.xpath("//button[@label='Save']")).click();
	}
	
	@When("Admin clicks Save button without entering any data")
	public void admin_clicks_save_button_without_entering_any_data() {
		driver.findElement(By.xpath("//button[@label='Save']")).click();
	}

	@Then("Admin gets a Error message alert as Student Email id is required")
	public void admin_gets_a_error_message_alert_as_student_email_id_is_required() 
	{
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='Email Id is required.']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='Program Name is required.']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='Batch Name is required.']")));
		Assert.assertNotNull(driver.findElement(By.xpath("//div[normalize-space()='Status is required.']")));
	}
	
	/*@When("Enter all the required fields with valid values and click Save button")
	public void enter_all_the_required_fields_with_valid_values_and_click_save_button() 
	{
		driver.findElement(By.xpath("//button[@label='Save']")).click();
	}

	@Then("Admin gets a message Successfully Staff Assigned alert")
	public void admin_gets_a_message_successfully_staff_assigned_alert() 
	{
	    Assert.assertNotNull(driver.findElement(By.xpath(null)));
	}
*/
	
	@When("Admin clicks Cancel or Close Icon on Assign Staff  form")
	public void admin_clicks_cancel_or_close_icon_on_assign_staff_form()
	{

		driver.findElement(By.className("p-dialog-header-close")).click();
	}

	@Then("Assign Staff popup window should be closed without saving")
	public void assign_staff_popup_window_should_be_closed_without_saving() 
	{
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']")).size(), 0);
	
	}

	@When("Admin clicks Cancel button")
	public void admin_clicks_cancel_button() 
	{
		driver.findElement(By.xpath("//label['Cancel']")).click();
	}

	@Then("Admin can see the Assign Staff popup disappears without assigning")
	public void admin_can_see_the_assign_staff_popup_disappears_without_assigning()
	{
		Assert.assertNotNull(driver.findElement(By.xpath("//label['Cancel']")));
	}

}
	
	
	

	
	
	
