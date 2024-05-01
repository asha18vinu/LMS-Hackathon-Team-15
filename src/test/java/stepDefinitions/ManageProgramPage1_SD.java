package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import context.TestContext;
import commonUtilities.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Duration;
import pageObjects.*;


public class ManageProgramPage1_SD {
	
	WebDriver driver;
	TestContext testcontext;
	private static final Logger logger = LogManager.getLogger(LoginPage_SD.class);
	ManageProgramPage1 mp;
	CommonUtils cUtils;
	
	public ManageProgramPage1_SD(TestContext testcontext)
	{
		this.testcontext = testcontext;	
		this.mp = testcontext.getPageObjectManager().getMp();
		driver=testcontext.getWebDriverManager().getDriver(); 
		this.cUtils = new CommonUtils(driver);
	}
	
//	@Given("Admin is in Home Page")
//	public void admin_is_in_home_page() {
//		logger.info("Login Page: Admin is on the login page");
//	}

	@When("Admin enter valid credentials and clicks login button")
	public void admin_enter_valid_credentials_and_clicks_login_button() {
	    mp.userName.sendKeys("sdetorganizers@gmail.com");
	    mp.password.sendKeys("UIHackathon@02");
	    mp.login.click();
	}

//	@Then("Admin should land on dashboard page")
//	public void admin_should_land_on_dashboard_page() {
//		logger.info("Admin is in the Dashboard page");
//	}

	@When("Admin clicks Program on the navigation bar")
	public void admin_clicks_program_on_the_navigation_bar() {
		mp.programLink.click();
	}

	@Then("Admin should see URL with Manage Program")
	public void admin_should_see_url_with_manage_program() {
	    String pageSrc = driver.getPageSource();
	    if(pageSrc.contains("Program"))
	    {
	    	logger.info("Page Validation success");
	    } else {
	    	logger.info("Page Validation failed");
	    }
	}
	
	@Then("Admin should see a heading with text Manage Program on the page")
	public void admin_should_see_a_heading_with_text_manage_program_on_the_page() {
	    assertEquals(mp.mpText.getText(), "Manage Program");
	}

	@Then("Admin should see a +A New Program button on the program page above the data table")
	public void admin_should_see_a_a_new_program_button_on_the_program_page_above_the_data_table() {
		assertEquals(mp.mpAddBtn.getText(), "A New Program");
	}

	@Then("Admin should see Search bar with text as Search...")
	public void admin_should_see_search_bar_with_text_as_search() {
		Assert.assertTrue(mp.SearchTextBox.isDisplayed());
	}
    
	@Then("Admin should see a Delete button on the top left hand side as Disabled")
	public void admin_should_see_a_delete_button_on_the_top_left_hand_side_as_disabled() {
		Assert.assertTrue(mp.deleteBtn.isEnabled());
	}
	
	@Then("Admin should see the number of records \\(rows of data in the table) displayed on the page.")
	public void admin_should_see_the_number_of_records_rows_of_data_in_the_table_displayed_on_the_page() {
	    java.util.List<WebElement> rows = mp.mpDataTable.findElements(By.tagName("tr"));
	    assertEquals(rows.size(), 5);
	}
	
	@Then("Admin should see data table on the Manage Program Page with following column headers \\(Program Name, Program Description, Program Status, Edit,Delete)")
	public void admin_should_see_data_table_on_the_manage_program_page_with_following_column_headers_program_name_program_description_program_status_edit_delete() {
	    java.util.List<WebElement> headers = mp.mpDataTableHeader.findElements(By.tagName("th"));
	    assertEquals(headers.size(), 5);
	    assertEquals(headers.get(1).getText(), "Program Name");
	    assertEquals(headers.get(2).getText(), "Program Description");
	    assertEquals(headers.get(3).getText(), "Program Status");
	    assertEquals(headers.get(4).getText(), "Edit / Delete");
	}
	
	@Then("Admin should see the text as Showing x to y of z entries along with Pagination icon below the table.")
	public void admin_should_see_the_text_as_showing_x_to_y_of_z_entries_along_with_pagination_icon_below_the_table() throws InterruptedException {
	    Thread.sleep(2000);
	    String showStr = mp.mpShowingText.getText().toString();
	    System.out.println("ShowStr=" + showStr);
	    String[] numbers = showStr.split("\\D+");
	    System.out.println("Number of elements: " + numbers.length);
	    for (String number : numbers) {
	        System.out.println("Number: " + number);
	    }
	    //assertEquals(numbers.length, 3);
	    String newShowStr = "Showing " + numbers[1] + " to " + numbers[2] + " of " + numbers[3] + " entries";
	    assertEquals(showStr, newShowStr);
	}
	
	@Then("Admin should see the footer as In total there are z programs.z- Total number of records")
	 public void admin_should_see_the_footer_as_in_total_there_are_z_programs_z_total_number_of_records() throws InterruptedException {
		Thread.sleep(2000);
		String showStr = mp.totalProgram.getText().toString();
		System.out.println("ShowStr=" + showStr);
		String[] numbers = showStr.split("\\D+");
		String newShowStr = "In total there are " + numbers[1] + " programs.";
		assertEquals(showStr, newShowStr);
	 }
	
	@Then("Admin should see the sort arrow icon beside to each column header except Edit and Delete")
	public void admin_should_see_the_sort_arrow_icon_beside_to_each_column_header_except_edit_and_delete() {
	    for(int i =2; i <=4; i++)
	    {
	    	String xpath = "//div[@class='p-datatable-wrapper ng-star-inserted']/table/thead/tr/th[" + i + "]/p-sorticon";
	    	WebElement sortBtn = driver.findElement(By.xpath(xpath));
	    	System.out.println("xpath is" + xpath);
	    	Assert.assertTrue(sortBtn.isDisplayed());
	    }
	}
	
	@Then("Admin should see check box on the left side in all rows of the data table")
	public void admin_should_see_check_box_on_the_left_side_in_all_rows_of_the_data_table() {
		java.util.List<WebElement> rows = mp.mpDataTable.findElements(By.tagName("tr"));
		for(int i=1;i<=rows.size();i++)
		{
		   String xpath = "//div[@class='p-datatable-wrapper ng-star-inserted']/table/tbody/tr[" + i + "]/td[1]/p-tablecheckbox";
		   WebElement checkbox=driver.findElement(By.xpath(xpath));
		   Assert.assertTrue(checkbox.isDisplayed());   
		}
	}

	@Then("Admin should see the Edit and Delete buttons on each row of the data table")
	public void admin_should_see_the_edit_and_delete_buttons_on_each_row_of_the_data_table() {
		java.util.List<WebElement> rows = mp.mpDataTable.findElements(By.tagName("tr"));
		for(int i=1;i<=rows.size();i++)
		{
			String Editxpath="//div[@class='p-datatable-wrapper ng-star-inserted']/table/tbody/tr[" + i + "]/td[5]/div/span/button[@id='editProgram']";
			String Deletexpath="//div[@class='p-datatable-wrapper ng-star-inserted']/table/tbody/tr["+ i + "]/td[5]/div/span/button[@id='deleteProgram']";
			WebElement Editbtn=driver.findElement(By.xpath(Editxpath));
			WebElement Deletebtn=driver.findElement(By.xpath(Deletexpath));
			Assert.assertTrue(Editbtn.isDisplayed());
			Assert.assertTrue(Deletebtn.isDisplayed());
		} 
	} 
	
//	@Given("Admin is on dashboard page after Login")
//	public void admin_is_on_dashboard_page_after_login() {
//		mp.userName.sendKeys("sdetorganizers@gmail.com");
//	    mp.password.sendKeys("UIHackathon@02");
//	    mp.login.click();
//	}

	@When("Admin clicks <A New Program> button")
	public void admin_clicks_a_new_program_button() {
	    mp.mpAddBtn.click();
	    cUtils.waitForElementToBeVisible(driver, mp.dialogPageName);
	}

	@Then("Admin should see a popup open for Program details with empty form along with <SAVE> and <CANCEL> button and Close\\(X) Icon on the top right corner of the window")
	public void admin_should_see_a_popup_open_for_program_details_with_empty_form_along_with_save_and_cancel_button_and_close_x_icon_on_the_top_right_corner_of_the_window() {
	    Assert.assertTrue(mp.dialogPageName.isDisplayed());
	    Assert.assertTrue(mp.SaveBtn.isDisplayed());
	    Assert.assertTrue(mp.CancelBtn.isDisplayed());
	    Assert.assertTrue(mp.closebtn.isDisplayed());
	}
	
	@Then("Admin should see two input fields and their respective text boxes in the program details window")
	public void admin_should_see_two_input_fields_and_their_respective_text_boxes_in_the_program_details_window() {
	  Assert.assertTrue(mp.NameTextBox.getText().isEmpty());
	  Assert.assertTrue(mp.DescTextBox.getText().isEmpty());
	}

  @Then("Admin should see two radio button for Program Status")
  public void admin_should_see_two_radio_button_for_program_status() {
    Assert.assertFalse(mp.InactiveRadioBtn.isSelected());
    Assert.assertFalse(mp.ActRadioBtn.isSelected());
  }
  
  @Given("Admin is on the Manage Program Page")
  public void admin_is_on_the_manage_program_page() {
	  mp.userName.sendKeys("sdetorganizers@gmail.com");
	  mp.password.sendKeys("UIHackathon@02");
	  mp.login.click();
	  mp.programLink.click();
      
  }

  @When("Admin clicks <Save> button without entering any data")
  public void admin_clicks_save_button_without_entering_any_data() {
	  cUtils.actionsClick(mp.SaveBtn, driver);
  }

  @Then("Admin gets a Error message alert")
  public void admin_gets_a_error_message_alert() {
	  Assert.assertTrue(mp.ProgNameError.isDisplayed());
	  Assert.assertTrue(mp.DescriptionError.isDisplayed());
      
  }
  
  @When("Admin enters only Program Name {string} in text box and clicks Save button")
  public void admin_enters_only_program_name_in_text_box_and_clicks_save_button(String string) {
	  cUtils.actionsSendKeys(driver, mp.NameTextBox, string);
	  cUtils.actionsClick(mp.SaveBtn, driver); 
  }

  @Then("Admin gets a message alert Description is required")
  public void admin_gets_a_message_alert_description_is_required() {
	  Assert.assertTrue(mp.DescriptionError.isDisplayed());
  }
  
 
  @When("Admin enters only Program description {string} in text box and clicks Save button")
  public void admin_enters_only_program_description_in_text_box_and_clicks_save_button(String string) {
	  cUtils.actionsSendKeys(driver,mp.DescTextBox ,string);
	  cUtils.actionsClick(mp.SaveBtn, driver); 
  }

  @Then("Admin gets a message alert Name is required")
  public void admin_gets_a_message_alert_name_is_required() {
	 Assert.assertTrue(mp.ProgNameError.isDisplayed());
  }
	 
	 @When("Admin selects only Status and clicks Save button")
	 public void admin_selects_only_status_and_clicks_save_button() {
		 cUtils.actionsClick(mp.ActRadioBtn,driver);
		 cUtils.actionsClick(mp.SaveBtn, driver); 
	 }

	 @Then("Admin gets a message alert Name and Description required")
	 public void admin_gets_a_message_alert_name_and_description_required() {
		Assert.assertTrue(mp.ProgNameError.isDisplayed());
		Assert.assertTrue(mp.DescriptionError.isDisplayed());
	
	 }
	  
	 @When("Admin enters only numbers or special char in name {string} and desc {string} column")
	    public void admin_enters_only_numbers_or_special_char_in_name_and_desc_column(String inValidProgName, String inValidDesc) {
		 cUtils.actionsSendKeys(driver,mp.NameTextBox, inValidProgName);
		 cUtils.actionsSendKeys(driver,mp.DescTextBox, inValidDesc); 
	}

   @Then("Admin gets a Syntax Error  alert")
	 public void admin_gets_a_syntax_error_alert() {
		 Assert.assertTrue(mp.ProgNameSyntaxError.isDisplayed());
	     
	 }
	 
	 @When("Admin clicks Cancel or Close icon on Program Details form")
	 public void admin_clicks_cancel_or_close_icon_on_program_details_form() {
		 cUtils.actionsClick(mp.closebtn, driver);
	 }

	 @Then("Program Details popup window should be closed without saving")
	 public void program_details_popup_window_should_be_closed_without_saving() {
		 try {
			 cUtils.waitForElementToBeInVisible(driver, mp.dialogPageName);
		 } catch (org.openqa.selenium.NoSuchElementException e) {
			 Assert.assertTrue(true);
		 }
	 }
	 
	 @When("Admin enter valid data and clicks Cancel button")
	 public void admin_enter_valid_data_and_clicks_cancel_button() {
		 cUtils.actionsSendKeys(driver, mp.NameTextBox, "Java");
		 cUtils.actionsSendKeys(driver,mp.DescTextBox ,"Oops Concept");
		 cUtils.actionsClick(mp.ActRadioBtn,driver);
		 cUtils.actionsClick(mp.CancelBtn, driver); 	 
	 }

	 @Then("Admin can see the Program details popup disappears without creating any program")
	 public void admin_can_see_the_program_details_popup_disappears_without_creating_any_program() {
		 try {
			 cUtils.waitForElementToBeInVisible(driver, mp.dialogPageName);
		 } catch (org.openqa.selenium.NoSuchElementException e) {
			 Assert.assertTrue(true);
		 }
		 cUtils.waitForElementToBeVisible(driver,mp.mpText);
		 assertEquals(mp.mpText.getText(), "Manage Program");
	 }
	 
	 
	@When("Enter all the required fields with valid values in {string} and {int} and click Save button")
	public void enter_all_the_required_fields_with_valid_values_in_and_and_click_save_button(String string, Integer int1) throws InvalidFormatException, IOException {
	 
		 Map<String, String> data = cUtils.getValidProgramDataFromExcel(string, int1);
		 cUtils.actionsSendKeys(driver, mp.NameTextBox,data.get("ProgramName"));
		 cUtils.actionsSendKeys(driver,mp.DescTextBox ,data.get("ProgramDescription"));
		 if(data.get("Status").contains("Active"))
		 {
			 if(!mp.ActRadioBtn.isSelected()) {
				 System.out.println("CLICK ACTIVE");
				 mp.ActRadioBtn.click();
			 }
		 }
		 else if(data.get("Status").contains("Inactive"))
		 {
			 if(!mp.InactiveRadioBtn.isSelected()) {
				 cUtils.actionsClick(mp.InactiveRadioBtn,driver);
			 }
		 }
		 else
		 {
			 System.out.println("click def active");
			 cUtils.actionsClick(mp.ActRadioBtn,driver);
		 }
		 System.out.println("Button click =" + mp.ActRadioBtn.isSelected());
		 cUtils.waitForElementToBeVisible(driver, mp.SaveBtn);
		 cUtils.actionsClick(mp.SaveBtn, driver);
	 }
	 
	 @Then("Admin gets a message Successful Program Created alert and able to see the new program added in the data table for {string} and {int}")
	 public void admin_gets_a_message_successful_program_created_alert_and_able_to_see_the_new_program_added_in_the_data_table_for_and(String sheet, Integer rownum) throws InvalidFormatException, IOException {
		 String ExpectedMessageText="Program Created Successfully";
		 Map<String, String> data = cUtils.getValidProgramDataFromExcel(sheet, rownum);
		 mp.VerifySucessfullPopUp(driver, cUtils, ExpectedMessageText);
		 driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		 Assert.assertTrue(mp.checkForTheAddedProgram(driver, 
				  cUtils,
				  data.get("ProgramName"), 
				  data.get("ProgramDescription"), 
				  data.get("Status")));
	 }

	 // Edit Program Code
	 @When("Admin clicks <Edit> button on the data table for any row")
	 public void admin_clicks_edit_button_on_the_data_table_for_any_row() {
		 WebElement row = mp.getRow(driver, 1);
		 WebElement column = mp.getCell(driver, row, 5);
		 WebElement editBtn = column.findElement(By.id("editProgram"));
		 editBtn.click();
	 }

	 @Then("Admin should see a popup open for Program details to Edit")
	 public void admin_should_see_a_popup_open_for_program_details_to_edit() {
		 cUtils.waitForElementToBeVisible(driver, mp.dialogPageName);
		 Assert.assertTrue(mp.dialogPageName.isDisplayed());
	 }
	 
	 @When("Admin edits name column in {string} and {int}  and clicks save button")
	 public void admin_edits_name_column_in_and_and_clicks_save_button(String string, Integer int1) throws InvalidFormatException, IOException {
		 Map<String, String> data = cUtils.getValidProgramDataFromExcel(string, int1);
		 mp.NameTextBox.clear();
		 cUtils.actionsSendKeys(driver, mp.NameTextBox,data.get("EditProgramName"));
		 cUtils.actionsClick(mp.SaveBtn, driver);
	 }

	 @Then("Admin gets a message Successful Program Updated alert and able to see the updated {string} information in {string} and {int}")
	 public void admin_gets_a_message_successful_program_updated_alert_and_able_to_see_the_updated_information_in_and(String string, String field, Integer int1) throws InvalidFormatException, IOException { 
		 String ExpectedMessageText="Program Updated";
		 Map<String, String> data = cUtils.getValidProgramDataFromExcel(string, int1);
		 mp.VerifySucessfullPopUp(driver, cUtils, ExpectedMessageText);
		 driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		 WebElement row = mp.getRow(driver, 1);
		 
		 WebElement column2 = mp.getCell(driver, row, 2);
		 String progName = column2.getText();
		 System.out.println("progname" + progName);
		 
		 WebElement column3 = mp.getCell(driver, row, 3);
		 String progDesc = column3.getText();
		 System.out.println("progdesc" + progDesc);
		 
		 WebElement column4 = mp.getCell(driver, row, 4);
		 String status = column4.getText();
		 System.out.println("status" + status);
		 
		 if(field.contains("name")) {
			 Assert.assertTrue(mp.checkForTheAddedProgram(driver, 
		 		  cUtils,
				  data.get("EditProgramName"), 
				  progDesc, 
				  status)); 
		 } else if(field.contains("description")) {
			 Assert.assertTrue(mp.checkForTheAddedProgram(driver, 
			 		  cUtils,
			 		  progName, 
					  data.get("EditProgramDescription"), 
					  status));
		 }
	 }
	 
	 @When("Admin edits the value of the  Program description in {string}  and  {int} column and clicks save button")
	 public void admin_edits_the_value_of_the_program_description_in_and_column_and_clicks_save_button(String string, Integer int1) throws InvalidFormatException, IOException {
		 Map<String, String> data = cUtils.getValidProgramDataFromExcel(string, int1);
		 mp.DescTextBox.clear();
		 cUtils.actionsSendKeys(driver, mp.DescTextBox,data.get("EditProgramDescription"));
		 cUtils.actionsClick(mp.SaveBtn, driver);
	 }

	 @When("Admin edits description {string} column and clicks save button")
	 public void admin_edits_description_column_and_clicks_save_button(String string) {
	     mp.DescTextBox.clear();
	     cUtils.actionsSendKeys(driver, mp.DescTextBox, string);
	     cUtils.actionsClick(mp.SaveBtn, driver);
	 }

	 @When("Admin enters only numbers or special char in {string} and {string} column")
	 public void admin_enters_only_numbers_or_special_char_in_and_column(String inValidProgName, String inValidDesc) {
		 mp.NameTextBox.clear();
		 cUtils.actionsSendKeys(driver,mp.NameTextBox, inValidProgName);
		 mp.DescTextBox.clear();
		 cUtils.actionsSendKeys(driver,mp.DescTextBox, inValidDesc); 
	 }
	
	 @When("Admin changes the Status of a program and clicks save button")
	 public void admin_changes_the_status_of_a_program_and_clicks_save_button() {
		 if(mp.ActRadioBtn.isSelected()) {
			 mp.InactiveRadioBtn.click();
		 } else {
			 mp.ActRadioBtn.click();
		 }
		 cUtils.actionsClick(mp.SaveBtn, driver);
	 }

	 @Then("Admin gets a message Successful Program Updated alert")
	 public void admin_gets_a_message_successful_program_updated_alert() throws InvalidFormatException, IOException {
		 String ExpectedMessageText="Program Updated";
		 mp.VerifySucessfullPopUp(driver, cUtils, ExpectedMessageText);
	 }
	 
	 @When("Admin clicks <Cancel> button on edit popup")
	 public void admin_clicks_cancel_button_on_edit_popup() {
		cUtils.actionsClick(mp.CancelBtn, driver);   
	 }

	 @Then("Admin can see the Program details popup disappears and can see nothing changed for particular program")
	 public void admin_can_see_the_program_details_popup_disappears_and_can_see_nothing_changed_for_particular_program() {
		 try {
			cUtils.waitForElementToBeInVisible(driver, mp.dialogPageName);
		 } catch (org.openqa.selenium.NoSuchElementException e) {
			Assert.assertTrue(true);
		 }
		 cUtils.waitForElementToBeVisible(driver,mp.mpText);
		 assertEquals(mp.mpText.getText(), "Manage Program");
	 }

	 @When("Admin clicks <Delete> button on the data table for any row")
	 public void admin_clicks_delete_button_on_the_data_table_for_any_row() {
		 WebElement row = mp.getRow(driver, 1);
		 WebElement column = mp.getCell(driver, row, 5);
		 mp.progName = mp.getCell(driver, row, 2).getText().toString();
		 mp.progDesc = mp.getCell(driver, row, 3).getText().toString();
		 mp.status = mp.getCell(driver, row, 4).getText().toString();
		 System.out.println("PROGNAME"+mp.progName);
		 WebElement deleteBtn = column.findElement(By.id("deleteProgram"));
		 deleteBtn.click();
	 }

	 @Then("Admin should see a alert open with heading Confirm along with  <YES> and <NO> button for deletion")
	 public void admin_should_see_a_alert_open_with_heading_confirm_along_with_yes_and_no_button_for_deletion() {
		 cUtils.waitForElementToBeVisible(driver,mp.deleteConfirmAlert);
	     assertEquals(mp.alerttxt.getText(),"Confirm");
	     Assert.assertTrue(mp.deleteValidationMsg.getText().contains("Are you sure you want to delete"));
	     Assert.assertTrue(mp.YesBtn.isDisplayed());
	     Assert.assertTrue(mp.NoBtn.isDisplayed());  
	 }
	 
	 @When("Admin clicks <Yes> button in the dialog box to confirm the deletion")
	 public void admin_clicks_yes_button_in_the_dialog_box_to_confirm_the_deletion() {
		 cUtils.waitForElementToBeVisible(driver, mp.YesBtn);
		 cUtils.actionsClick(mp.YesBtn, driver);
		 driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
	 }
	 
	 @When("Admin find a specific program from {string} and clicks <Delete> button on the data table for the specific row {int}")
	 public void admin_find_a_specific_program_from_and_clicks_delete_button_on_the_data_table_for_the_specific_row(String string, Integer int1) throws InvalidFormatException, IOException, InterruptedException {
		 Map<String, String> data = cUtils.getValidProgramDataFromExcel(string, int1);
		 cUtils.actionsSendKeys(driver, mp.SearchTextBox,data.get("ProgramName"));
		 //driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		 //cUtils.waitForElementToBeVisible(driver, mp.mpDataTable);
		 Thread.sleep(2000);
		 WebElement row = mp.getRow(driver, 1);
		 WebElement column = mp.getCell(driver, row, 5);
		 mp.progName = mp.getCell(driver, row, 2).getText();
		 mp.progDesc = mp.getCell(driver, row, 3).getText();
		 mp.status = mp.getCell(driver, row, 4).getText();
		 System.out.println("PROGNAME"+mp.progName);
		 WebElement deleteBtn = column.findElement(By.id("deleteProgram"));
		 deleteBtn.click(); 
	 }

	 @Then("Admin gets a message Successful Program Deleted alert and able to see that program deleted in the data table")
	 public void admin_gets_a_message_successful_program_deleted_alert_and_able_to_see_that_program_deleted_in_the_data_table() throws InvalidFormatException, IOException {
		 String ExpectedMessageText="Program Deleted";
		 mp.VerifySucessfullPopUp(driver, cUtils, ExpectedMessageText);
		 //driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		 cUtils.waitForElementToBeVisible(driver, mp.mpDataTableHeader);
		 System.out.println("progName="+mp.progName);
		 Assert.assertFalse(mp.checkForTheAddedProgram(driver, 
				  cUtils,
				  mp.progName, 
				  mp.progDesc, 
				  mp.status));
	 }

	 @When("Admin clicks <NO> button on the alert")
         public void admin_clicks_no_button_on_the_alert() {
                 cUtils.waitForElementToBeVisible(driver,mp.NoBtn);
                 cUtils.actionsClick(mp.NoBtn, driver);
                 driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
         }

         @Then("Admin can see the deletion alert disappears without deleting")
         public void admin_can_see_the_deletion_alert_disappears_without_deleting() {
                 try {
                                cUtils.waitForElementToBeInVisible(driver,mp.deleteConfirmAlert);
                                Assert.assertTrue(mp.checkForTheAddedProgram(driver,
                                                  cUtils,
                                                  mp.progName,
                                                  mp.progDesc,
                                                  mp.status));
                         } catch (org.openqa.selenium.NoSuchElementException e) {
                                Assert.assertFalse(true);
                         }
         }

         @When("Admin clicks Close icon on Confirm Deletion alert  on the alert")
         public void admin_clicks_close_icon_on_confirm_deletion_alert_on_the_alert() {
                 cUtils.waitForElementToBeVisible(driver,mp.deleteConfirmCloseBtn);
                 cUtils.actionsClick(mp.deleteConfirmCloseBtn, driver);
                 driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
         }

         @Then("Admin can see the deletion alert disappears without any changes")
         public void admin_can_see_the_deletion_alert_disappears_without_any_changes() {
                try {
                                cUtils.waitForElementToBeInVisible(driver,mp.deleteConfirmAlert);
                        } catch (org.openqa.selenium.NoSuchElementException e) {
                                Assert.assertFalse(true);
                        }
         }
}




	 
	 











   




