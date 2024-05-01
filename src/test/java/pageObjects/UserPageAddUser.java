package pageObjects;

import static org.testng.Assert.assertEquals;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtilities.CommonUtils;
import commonUtilities.LoggerLoad;
import context.TestContext;
import managers.FileReaderManager;

public class UserPageAddUser 
{
	
	WebDriver driver;
	WebDriverWait wait;
	CommonUtils commonUtils;
	TestContext testcontext;
	
	String userStatusMessage;
	String userNameSearch,userFName,userLName,userLocation,userPhone,userEmail;
	boolean flag = false;
	
	@FindBy(xpath="//input[@*='userLoginEmailId']") WebElement userName;
	@FindBy(xpath="//input[@*='Password']") WebElement pwd;
	@FindBy(xpath="//button[@*='submit']") WebElement btn;
	
	@FindBy(xpath="//div[@class='box'][2]/div[3]/button") WebElement addNewUser;
	@FindBy(xpath="//p-dialog[1]/div/div[@role='dialog']")WebElement userDetailsDialog;
	@FindBy(xpath="//span[@id='pr_id_5-label']") WebElement userPopUpHeader;
	@FindBy(xpath="//input[@formcontrolname='userFirstName']")WebElement userFirstNameField;
	@FindBy(xpath="//input[@formcontrolname='userMiddleName']")WebElement userMiddleNameField;
	@FindBy(xpath="//input[@formcontrolname='userLastName']")WebElement userLastNameField;
	@FindBy(xpath="//input[@formcontrolname='userLocation']")WebElement userLocationField;
	@FindBy(xpath="//input[@formcontrolname='userPhoneNumber']")WebElement userPhoneField;
	@FindBy(xpath="//input[@formcontrolname='userLinkedinUrl']")WebElement userLinkedInField;
	@FindBy(xpath="//p-dropdown[@id='roleId']")WebElement userRoleId;
	@FindBy(xpath="//p-dropdown[@id='userRoleStatus']")WebElement userRoleStatus;
	@FindBy(xpath="//p-dropdown[@id='userVisaStatus']")WebElement userVisaStatus;
	@FindBy(xpath="//input[@formcontrolname='userLoginEmail']")WebElement userEmailField;
	@FindBy(xpath="//input[@formcontrolname='userEduUg']")WebElement userUGField;
	@FindBy(xpath="//input[@formcontrolname='userEduPg']")WebElement userPGField;
	@FindBy(xpath="//input[@formcontrolname='userTimeZone']")WebElement userTimeZoneField;
	@FindBy(xpath="//input[@formcontrolname='userComments']")WebElement userCommentsField;
	@FindBy(xpath="//span[normalize-space()='Cancel']")WebElement userCancelButton;
	@FindBy(xpath="//span[normalize-space()='Submit']")WebElement userSubmitButton;
	@FindBy(xpath="//span[@class='p-dialog-header-close-icon ng-tns-c132-6 pi pi-times']")WebElement userCloseDialog;
	
	@FindBy(xpath="//mat-error[contains(text(),'First name is')]")WebElement userFnameError;
	@FindBy(xpath="//mat-error[contains(text(),'Middle name is')]")WebElement userMnameError;
	@FindBy(xpath="//mat-error[contains(text(),'Last name is')]")WebElement userLnameError;
	@FindBy(xpath="//mat-error[contains(text(),'Location is')]")WebElement userLocationError;
	@FindBy(xpath="//mat-error[contains(text(),'Phone number is')]")WebElement userPhoneError;
	@FindBy(xpath="//mat-error[contains(text(),'LinkedIn Url is')]")WebElement userLinkedinError;
	@FindBy(xpath="//mat-error[contains(text(),'Email address is')]")WebElement userEmailError;
	@FindBy(xpath="//mat-error[contains(text(),'Under Graduate is')]")WebElement userUGError;
	@FindBy(xpath="//mat-error[contains(text(),'Post Graduate is')]")WebElement userPGError;
	@FindBy(xpath="//mat-error[contains(text(),'Time Zone is')]")WebElement userTimeZoneError;
	@FindBy(xpath="//mat-error[contains(text(),'User Comments is')]")WebElement userCommentsError;
	
	@FindBy(css = "tbody.p-datatable-tbody tr")	List<WebElement> CurrentRows;
	
	public UserPageAddUser(WebDriver driver) 	
	{		
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		commonUtils = new CommonUtils(this.driver);		
	}

	public void login()
	{
		userName.sendKeys("sdetorganizers@gmail.com");
		pwd.sendKeys("UIHackathon@02");
		btn.click();
		System.out.println("Login successfull");
	}
	
	public void addNewUserClick()
	{
		addNewUser.click();
	}
	
	public void getUserPopUpHeader()
	{
		String Expected = FileReaderManager.getInstance().getResourcebundleInstance().getUserPopboxHeader();
		System.out.println(userPopUpHeader.getText());
		commonUtils.getAssertionEqualsCheck(userPopUpHeader.getText(), Expected);
		LoggerLoad.info("Admin Opens User Dialog");
	}
	
	public void closeUserDialog()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		boolean userDialogClosed = wait.until(ExpectedConditions.invisibilityOf(userCloseDialog));
		
		if(userDialogClosed)
		{
			System.out.println("User Dialog is closed");
		}
		else
		{
			System.out.println("User Dialog is not closed");
		}
	}
	
	public void checkTheFieldExistanceAndType(String fieldName, String fieldType) 
	{
		Map<String, WebElement> fieldNameLocators = fieldNameAndType();
		commonUtils.checkExistanceofFieldType(fieldName, fieldType, fieldNameLocators);
	}
	
	public Map<String, WebElement> fieldNameAndType() 
	{
		Map<String, WebElement> fieldNameLocators = new LinkedHashMap<>();
		fieldNameLocators.put("FirstName", userFirstNameField);
		fieldNameLocators.put("MiddleName", userMiddleNameField);
		fieldNameLocators.put("LastName", userLastNameField);
		fieldNameLocators.put("Location", userLocationField);
		fieldNameLocators.put("PhoneNumber", userPhoneField);
		fieldNameLocators.put("LinkedIn", userLinkedInField);
		fieldNameLocators.put("RoleId", userRoleId);
		fieldNameLocators.put("RoleStatus", userRoleStatus);
		fieldNameLocators.put("VisaStatus", userVisaStatus);
		fieldNameLocators.put("Email", userEmailField);
		fieldNameLocators.put("UG", userUGField);
		fieldNameLocators.put("PG", userPGField);
		fieldNameLocators.put("TimeZone", userTimeZoneField);
		fieldNameLocators.put("Comments", userCommentsField);

		return fieldNameLocators;

	}

	public void userPopUpButtonFields()
	{
		assertEquals(commonUtils.isPresent(userCancelButton),true);
		assertEquals(commonUtils.isPresent(userCloseDialog),true);
		assertEquals(commonUtils.isPresent(userSubmitButton),true);
	}
	
	
	public void fillValidUserDetails(String sheetName, Integer rowNo) throws InvalidFormatException, IOException, InterruptedException 
	{
		List<Map<String, String>> data = commonUtils.getValidDataFromExcel(sheetName, rowNo);
		
		userFName = data.get(rowNo).get("FirstName");
		userFirstNameField.sendKeys(userFName);		
		
		userMiddleNameField.sendKeys(data.get(rowNo).get("MiddleName"));
		
		userLName = data.get(rowNo).get("LastName");
		userLastNameField.sendKeys(userLName);
		
		userLocation = data.get(rowNo).get("Location");
		userLocationField.sendKeys(userLocation);
		
		userPhone = data.get(rowNo).get("PhoneNo");
		userPhoneField.sendKeys(userPhone);
		
		userLinkedInField.sendKeys(data.get(rowNo).get("LinkedInUrl"));
		
		userRoleId.click();
		WebDriverWait waitId = new WebDriverWait(driver, Duration.ofSeconds(10));
		String roleValue = data.get(rowNo).get("RoleId");
		WebElement optionRoleId = waitId.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='"+roleValue+"']")));
		optionRoleId.click();
		
		userRoleStatus.click();
		WebDriverWait waitstatus = new WebDriverWait(driver, Duration.ofSeconds(10));
		String roleStatusValue = data.get(rowNo).get("RoleStatus");
		WebElement optionRoleStatus = waitstatus.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='"+roleStatusValue+"']")));
		optionRoleStatus.click();
		
		userVisaStatus.click();
		WebDriverWait waitvisa = new WebDriverWait(driver, Duration.ofSeconds(10));
		String visaStatusValue = data.get(rowNo).get("VisaStatus");
		WebElement optionVisaStatus = waitvisa.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='"+visaStatusValue+"']")));
		optionVisaStatus.click();
		
		userEmail = data.get(rowNo).get("Email");
		userEmailField.sendKeys(userEmail);
		userUGField.sendKeys(data.get(rowNo).get("UG"));
		userPGField.sendKeys(data.get(rowNo).get("PG"));
		userTimeZoneField.sendKeys(data.get(rowNo).get("TimeZone"));
		userCommentsField.sendKeys(data.get(rowNo).get("Comments"));
		
	}
	
	public void checkScenario(String Scenario)
	{
		if(Scenario == "Missing mandatory values")
		{
			System.out.println("Missing Mandatory Fields");
			missingMandatoryFields();	
			closeDialogClick();
		}
		else if(Scenario == "Invalid data")
		{
			System.out.println("Invalid data");
			closeDialogClick();
		}
			
	}
	
	public void missingMandatoryFields()
	{
			if(userFName == null)
			{
				if(userFnameError.isDisplayed())
				{
					System.out.println("First name is Null");
					System.out.println("Error Message - "+userFnameError.getText());
				}
			}
			else if(userLName == null)
			{
				if(userLnameError.isDisplayed())
				{
					System.out.println("Last name is Null");
					System.out.println("Error Message - "+userLnameError.getText());
				}
			}
			else if(userLocation == null)
			{
				if(userLnameError.isDisplayed())
				{
					System.out.println("Location is Null");
					System.out.println("Error Message - "+userLocationError.getText());
				}
			}
			else if(userPhone == null)
			{
				if(userLnameError.isDisplayed())
				{
					System.out.println("Phone number is Null");
					System.out.println("Error Message - "+userPhoneError.getText());
				}
			}
			else if(userEmail == null)
			{
				if(userEmailError.isDisplayed())
				{
					System.out.println("User Email is Null");
					System.out.println("Error Message - "+userEmailError.getText());
				}
			}		

	}
	
	public void submitClick()
	{
		userSubmitButton.click();
	}
	
	public void cancelButtonClick() 
	{
		userCancelButton.click();
	}
	
	public void closeDialogClick()
	{
		userCloseDialog.click();
	}
	
	public boolean verifySuccessMessage(String message) 
	{
		String SuccessMessage = "Success";
		String FailureMessage = "Failed";
		boolean flag=false;
		try 
		{
			WebDriverWait waitmsg = new WebDriverWait(driver,Duration.ofSeconds(10));
			WebElement messageText = waitmsg.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));			
			String actualMessageText = messageText.getText();
						
			System.out.println("Actual Message: " + actualMessageText);
			System.out.println("Expected :" + message);
			
			if(message.equalsIgnoreCase(SuccessMessage))
			{
				if(actualMessageText.contains(SuccessMessage))
				{
					System.out.println("User Created Successfully");
					LoggerLoad.info(actualMessageText);
					checkForAddedUser();
				}
				else  
				{
					System.out.println("User Creation Failed");
					LoggerLoad.info(actualMessageText);
				}
			}
			
			if(message.equalsIgnoreCase(FailureMessage))
			{
				if(actualMessageText.contains(FailureMessage))
				{
					System.out.println("User Creation Failed");
					LoggerLoad.info(actualMessageText);
				}
				else  
				{
					System.out.println("User Creation not Failed");
					LoggerLoad.info(actualMessageText);
				}
			}
			
			//flag=commonUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
		} catch (Exception e) {
			LoggerLoad.info("Success message not found within the timeout period.");
		}
		return flag;
	}
	
	
	public void emptyFormErrorMsg() throws InterruptedException
	{
		String errorField;
		List <WebElement> fieldErrorMsg = new ArrayList<>();
		
		fieldErrorMsg.add(userFnameError);
		fieldErrorMsg.add(userMnameError);
		fieldErrorMsg.add(userLnameError);
		fieldErrorMsg.add(userLocationError);
		fieldErrorMsg.add(userPhoneError);
		fieldErrorMsg.add(userLinkedinError);
		fieldErrorMsg.add(userEmailError);
		fieldErrorMsg.add(userUGError);
		fieldErrorMsg.add(userPGError);
		fieldErrorMsg.add(userTimeZoneError);
		fieldErrorMsg.add(userCommentsError);
	
		for (WebElement element : fieldErrorMsg)
		{
			Thread.sleep(1000);
			if(element.isDisplayed())
			{			
				errorField = element.getText();
				System.out.println("Error Message - "+ errorField);							
			}
			else
			{
				System.out.println("Error message not displayed");
				break;
			}
		}
		
	}
	

	public boolean checkForAddedUser() throws InterruptedException
	{	
		userNameSearch = userFName+" "+userLName;
		System.out.println("User Name to be Searched - "+userNameSearch);
		//driver.navigate().back();
		//driver.navigate().refresh();
//		userNameSearch = userFName+" "+userLName;
//		System.out.println("User Name to be Searched - "+userNameSearch);
		List<WebElement> tableRowData = new ArrayList<>();
		Thread.sleep(1000);;
		WebElement nextPageButton = driver
				.findElement(By.xpath("//button[@class='p-paginator-next p-paginator-element p-link p-ripple']"));

		while (nextPageButton.isEnabled()) {

			List<WebElement> currentPageRows = getRowsFromCurrentPage();

			tableRowData.addAll(currentPageRows);

			for (WebElement row : currentPageRows) {

				List<WebElement> cells = row.findElements(By.tagName("td"));

				List<WebElement> filteredCells = new ArrayList<>();
				for (int i = 1; i < cells.size() - 1; i++) {
					filteredCells.add(cells.get(i));
				}

				for (WebElement cell : filteredCells) {

					if (cell.getText().equalsIgnoreCase(userNameSearch)) {
						flag = true;
						break;
					}
				}
			}
			Thread.sleep(1000);;
			nextPageButton.click();
		}

		if (flag) {
			LoggerLoad.info("User is present in the table");
		} else
			LoggerLoad.info("User is not present in the tablet");

		return flag;
	}

	private List<WebElement> getRowsFromCurrentPage() {
		return CurrentRows;
	}
}
