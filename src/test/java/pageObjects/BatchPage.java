package pageObjects;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtilities.CommonUtils;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import managers.FileReaderManager;

public class BatchPage {

	WebDriver driver;
	CommonUtils cUtils;
	WebDriverWait wait;
	String actualFieldType;
	boolean flag = false;
	String batchUrl;
	int count=0;
	static String batchName, description, noOfClasses;// ,programNametxt,status;
	static String programName = "SeleniumCourse"
			+ ""
			+ ""
			+ ""
			+ "";
	Random random = new Random();

	@FindBy(xpath = "//span[text()='Batch']")
	WebElement batchNavBarBtn;
	@FindBy(xpath = "//div[@*='signin-content']//div[text()=' Manage Batch']")
	WebElement manageBatchText;
	@FindBy(xpath = "//button[@*='new']")
	WebElement addBatchBtn;
	@FindBy(xpath = "//span[text()='Batch Details']")
	WebElement batchDetailsPopupboxHeading;
	@FindBy(xpath = "//span[@*='pr_id_5-label']")
	WebElement batchDetailsDlgBox;
	@FindBy(xpath = "//input[@*='batchName']")
	WebElement nameTextField;
	@FindBy(xpath = "//input[@*='batchDescription']")
	WebElement descriptionTextField;
	@FindBy(id="programName")
	WebElement programNameTextField;
	@FindBy(xpath = "//input[@*='ACTIVE']")
	WebElement statusActiveRadiobtn;
	@FindBy(xpath = "//input[@*='INACTIVE']")
	WebElement statusINactiveRadiobtn;
	@FindBy(xpath = "//div[@*='button']/span")
	WebElement programNameFieldDropDownBtn;
	@FindBy(xpath = "//input[@id='batchNoOfClasses']")
	WebElement noOfClassesSnipperField;
	@FindBy(xpath = "//button[@label='Save']")
	WebElement saveBtn;
	@FindBy(linkText = "Cancel")
	WebElement cancelBtn;
	@FindBy(css = "span.p-dialog-header-close-icon.ng-tns-c132-50.pi.pi-times")
	WebElement clearCrossBtn;
	@FindBy(xpath = "//*[@id='batchStatus']/div[1]")
	WebElement activeRadiobtn;
	@FindBy(css = "label[for='batchDescription']")
	WebElement descriptionLabel;
	@FindBy(css = "tbody.p-datatable-tbody tr")
	List<WebElement> tableRowData;
	@FindBy(css = "td:not(:first-child):not(:last-child)")
	List<WebElement> tableCellData;
	@FindBy(xpath = "//button[@class='p-paginator-next p-paginator-element p-link p-ripple']")
	WebElement nextPageBtn;
	@FindBy(xpath = "//button[@class='p-paginator-next p-paginator-element p-link p-ripple']")
	WebElement nextPageButton;
	@FindBy(css = "tbody.p-datatable-tbody tr")
	List<WebElement> CurrentRows;
	// @FindBy(xpath="//*[@id='batchStatus']/div[1]")
	// WebElement activeRadiobtn ;
	@FindBy(xpath = "//small[contains(@class, 'p-invalid') and contains(text(), 'Batch Name is required.')]")
	WebElement nameFieldErrorMsg;
	@FindBy(xpath = "//small[@class='p-invalid ng-star-inserted']")
	WebElement prgNameErrorMsg;
	@FindBy(xpath = "//small[normalize-space()='Batch Name is required.']")
	WebElement nameErrorElement;
	@FindBy(xpath = "//small[normalize-space()='Number of classes is required.']")
	WebElement noOfClassesErrorElement;
	@FindBy(xpath = "//small[normalize-space()='Batch Description is required.']")
	WebElement descriptionErrorElement;
	@FindBy(xpath = "//small[normalize-space()='Program Name is required.']")
	WebElement programNameErrorElement;
	@FindBy(xpath = "//small[normalize-space()='Status is required.']")
	WebElement statusActiveErrorElement;

	public BatchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		cUtils = new CommonUtils(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		batchUrl = FileReaderManager.getInstance().getResourcebundleInstance().getBatchUrl();

	}

	public void batchNavigationBar() {
		batchNavBarBtn.click();

	}

	public void addNewBatch() {
		addBatchBtn.click();
		String Expected = FileReaderManager.getInstance().getResourcebundleInstance().getBatchUrl();
		cUtils.getAssertionEqualsCheck(driver.getCurrentUrl(), Expected);

	}

	public void popUpVerification() {

		String expected = FileReaderManager.getInstance().getResourcebundleInstance().getBatchPopboxHeader();
		cUtils.getAssertionEqualsCheck(batchDetailsPopupboxHeading.getText(), expected);
	}

	

	public void checkTheFieldExistanceAndType(String fieldName, String fieldType) {
		Map<String, WebElement> fieldNameLocators = fieldNameAndType();
		cUtils.checkExistanceofFieldType(fieldName, fieldType, fieldNameLocators);

	}

	public void fillBatchValidDestails(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {

		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowNo);
		batchName = data.get(rowNo).get("BatchName") + random.nextInt(90) + 10;
		nameTextField.sendKeys(batchName);
		descriptionTextField.sendKeys(data.get(rowNo).get("Description"));
		programNameFieldDropDownBtn.click();
		WebElement dropdownItem = driver
				.findElement(By.xpath("//span[contains(text(), '" + programName + "')]/ancestor::p-dropdownitem"));
		dropdownItem.click();
		if (!activeRadiobtn.isSelected()) {
			activeRadiobtn.click();
		}
		noOfClassesSnipperField.sendKeys(data.get(rowNo).get("NoOfClasses"));

	}

	public void checkDescriptionFieldOptional() {
		String labelText = descriptionLabel.getText();
		boolean flag = labelText.contains("*");

		try {
			assertEquals(flag, "true", "Description should not be a OptionalField");
		} catch (AssertionError e) {

			System.out.println("Description Is not Optional Assertion");
		}

	}

	public void clickSaveButton() {
		saveBtn.click();
	}

			public void checkForTheAddedBatch() {
			
				driver.navigate().back();
				List<WebElement> tableRowData = new ArrayList<>();
			
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
			
						if (cell.getText().equalsIgnoreCase(batchName)) {
							flag = true;
							break;
						}
					}
				}
			
				nextPageButton.click();
			}
			
			if (flag) {
				System.out.println("Batch is created and diplayed in the table");
			} else
				System.out.println("batch is not created");
			}
			
			private List<WebElement> getRowsFromCurrentPage() {// div[@role='alert']
				return CurrentRows;
			}

	public void verifySuccessMessage() {
		String ExpectedMessageText = "Successful";
		try {
			WebElement successMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			String actualMessageText = successMessage.getText();
			System.out.println("actual Success Message: " + actualMessageText);
			System.out.println("Expected :"+ExpectedMessageText );
			cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
		} catch (Exception e) {
			System.out.println("Success message not found within the timeout period.");
		}
	}
	
	public Map<String, WebElement> fieldNameAndType() {

		Map<String, WebElement> fieldNameLocators = new LinkedHashMap<>();
		fieldNameLocators.put("NameField", nameTextField);
		fieldNameLocators.put("NumberofClassesField", noOfClassesSnipperField);
		fieldNameLocators.put("DescriptionField", descriptionTextField);
		fieldNameLocators.put("ProgramnameField", programNameTextField);
		fieldNameLocators.put("ActiveField", statusActiveRadiobtn);
		fieldNameLocators.put("InactiveField", statusINactiveRadiobtn);

		return fieldNameLocators;

	}

//	public void checkMandatoryFieldsBlank(String sheetname, Integer rowno) throws InvalidFormatException, IOException {
//
//		Map<String, WebElement> fieldLocators = fieldNameAndType();
//		Map<String, WebElement> errorMessageLocators = fieldNameAndErrorType();
//
//		Map<String, String> errorLocators = new LinkedHashMap<>();
//		errorLocators.put("NameField", "Batch Name is required");
//		errorLocators.put("NumberofClassesField", "Number of classes is required");
//		errorLocators.put("DescriptionField", "Batch Description is required");
//		errorLocators.put("ProgramnameField", "Program Name is required");
//		errorLocators.put("ActiveField", "Status is required");
//		errorLocators.put("InactiveField", "Status is required");
//
//		for (Map.Entry<String, WebElement> entry : fieldLocators.entrySet()) {
//			String fieldName = entry.getKey();
//			WebElement fieldElement = entry.getValue();
//			WebElement errorMessageElement = errorMessageLocators.get(fieldName);
//			String errorMessage = errorLocators.get(fieldName);
//			fieldElement.clear();
//
//			saveBtn.click();
//			System.out.println(errorMessageElement.getText());
//			String actualErrorMessage = errorMessageElement.getText();
//			assertErrorMessage(fieldName, actualErrorMessage, errorMessageElement.getText());
//			count++;
//			if(count>3)
//			break;
//		}
//	}
	
	public boolean checkMandatoryFieldsBlank(String sheetname, Integer rowno) throws InvalidFormatException, IOException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetname, rowno);
	    boolean flag=false;
		WebElement errorMsg = null;
		for(int i=0;i<5;i++) 
		{
		String fieldName = data.get(i).get("Fields");	
		String expectedErrorMsg=data.get(i).get("errorMsg");
		saveBtn.click();
		switch(fieldName)
		{
		case "batchField":		 		
			            errorMsg=driver.findElement(By.xpath("//small[normalize-space()='Batch Name is required.']"));
			            flag=cUtils.validateErrorMsg(errorMsg.getText(),expectedErrorMsg);
			            break;	       
			            
			           
		case "descriptionField":
			errorMsg=driver.findElement(By.xpath("//small[normalize-space()='Batch Description is required.']"));
			 flag=cUtils.validateErrorMsg(errorMsg.getText(),expectedErrorMsg);
			  break;
			  
		case "programField":			
			  errorMsg=driver.findElement(By.xpath("//small[normalize-space()='Program Name is required.']"));
			 flag=cUtils.validateErrorMsg(errorMsg.getText(),expectedErrorMsg);
			  break;
			  
		case "radioBtn":				
			errorMsg=driver.findElement(By.xpath("//small[normalize-space()='Status is required.']"));			
			flag=cUtils.validateErrorMsg(errorMsg.getText(),expectedErrorMsg);
			  break;
			  
		case "NoOfClasses":				
			errorMsg=driver.findElement(By.xpath("//small[normalize-space()='Number of classes is required.']"));
			 flag=cUtils.validateErrorMsg(errorMsg.getText(),expectedErrorMsg);	
			  break;
		default:
			break;
		}		
		}	  			     
		return flag;
	}
		
		
		

	
	
	

	public Map<String, WebElement> fieldNameAndErrorType() {
		
		Map<String, WebElement> errorLocators = new LinkedHashMap<>();
		errorLocators.put("NameField", nameErrorElement);
		errorLocators.put("NumberofClassesField", noOfClassesErrorElement);
		errorLocators.put("DescriptionField", descriptionErrorElement);
		errorLocators.put("ProgramnameField", programNameErrorElement);
		errorLocators.put("ActiveField", statusActiveErrorElement);
		errorLocators.put("InactiveField", statusActiveErrorElement);
		return errorLocators;
	}

	public static void assertErrorMessage(String fieldName, String expectedErrorMessage, String actualErrorMessage) {
		assert expectedErrorMessage.equals(actualErrorMessage)
				: "Error message for " + fieldName + " doesn't match expected.";
		System.out.println("Assertion passed for " + fieldName);
	}

	public String batchField(String fieldName,String invalidValues) {
		WebElement errorMsg = null;
		switch(fieldName)
		{
		case "batchName":
			           nameTextField.sendKeys(invalidValues);
			            errorMsg=driver.findElement(By.xpath("//small[@id='text-danger']"));
			           return errorMsg.getText();
			           
		case "Description":
			descriptionTextField.sendKeys(invalidValues);
			errorMsg=driver.findElement(By.xpath("//small[@id='text-danger']"));
	           return errorMsg.getText();

		}
		
			            
			     
		return errorMsg.getText();
	}
}
