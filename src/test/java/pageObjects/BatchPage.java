package pageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonUtilities.CommonUtils;
import managers.FileReaderManager;

public class BatchPage {

	WebDriver driver;
	CommonUtils cUtils;
	WebDriverWait wait;
	String actualFieldType;
	boolean flag = false;
	String batchUrl;
	int count = 0;
	static String batchName, description, noOfClasses;// ,programNametxt,status;
	static String programName = "amazon";
	Random random = new Random();
	List<String> deletedBatchNames;
	WebElement batchNameElement;
	public static List<String> secondColumnTexts;
	@FindBy(xpath = "//mat-card-title[@class='mat-card-title']//div[normalize-space()='Manage Batch']")
	WebElement cardTitleElement;
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
	@FindBy(id = "programName")
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
	@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-right']")
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
	@FindBy(xpath = "//div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']")
	WebElement parentPaginators;
	@FindBy(xpath = "//span[@class='p-paginator-pages ng-star-inserted']/button")
	List<WebElement> noPaginators;
	@FindBy(xpath = "//table[@role='grid']//tr[@class='ng-star-inserted']/th") // thead[@class='p-datatable-thead']//tr[@class='ng-star-inserted']/th
	List<WebElement> tableHeader;
	@FindBy(xpath = "//table[@role='grid']/tbody/tr[@class='ng-star-inserted']")
	List<WebElement> tableRow;
	@FindBy(xpath = "//button[contains(@class, 'p-button-danger')]")
	WebElement deleteButton;
	@FindBy(xpath = "//span[normalize-space()='A New Batch']")
	WebElement newBatchElt;
	@FindBy(xpath = "//table[@role='grid']")
	WebElement tableBody;
	@FindBy(xpath = "//div[contains(@class, 'p-confirm-dialog')]")
	WebElement dialog;
	@FindBy(xpath = "//span[normalize-space()='Yes']")
	WebElement yesButton;
	@FindBy(xpath = "//button[contains(@class, 'p-confirm-dialog-reject')]")
	WebElement noButton;
	@FindBy(xpath="//span[@class='p-button-icon pi pi-pencil']")
	WebElement editIcon ;
	@FindBy(id="filterGlobal")
	 WebElement searchBox;
	
	WebElement secondColumnElement;

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
		List<WebElement> options = driver.findElements(By.xpath("//ul[@role='listbox']/p-dropdownitem/li/span"));
		System.out.println(options.size());

		for (WebElement option : options) {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();
			System.out.println(optionText);
			if (optionText.equals(programName)) {
				System.out.println("programName : " + programName);
				option.click();
				break;
			}
		}
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
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

	public boolean checkForTheAddedBatch() {

		driver.navigate().back();
		List<WebElement> tableRowData = new ArrayList<>();

//		WebElement nextPageButton = driver
//				.findElement(By.xpath("//button[@class='p-paginator-next p-paginator-element p-link p-ripple']"));

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
			System.out.println("Batch present the table");
		} else
			System.out.println("batch is not Present");

		return flag;
	}

	private List<WebElement> getRowsFromCurrentPage() {
		return CurrentRows;
	}

	public void verifySuccessMessage() {
		String ExpectedMessageText = "Successful";
		try {
			WebElement successMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			String actualMessageText = successMessage.getText();
			System.out.println("actual Success Message: " + actualMessageText);
			System.out.println("Expected :" + ExpectedMessageText);
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

	public boolean checkMandatoryFieldsBlank(String sheetname, Integer rowno)
			throws InvalidFormatException, IOException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetname, rowno);
		boolean flag = false;
		WebElement errorMsg = null;
		for (int i = 0; i < 5; i++) {
			String fieldName = data.get(i).get("Fields");
			String expectedErrorMsg = data.get(i).get("errorMsg");
			saveBtn.click();
			switch (fieldName) {
			case "batchField":
				errorMsg = driver.findElement(By.xpath("//small[normalize-space()='Batch Name is required.']"));
				flag = cUtils.validateErrorMsg(errorMsg.getText(), expectedErrorMsg);
				break;

			case "descriptionField":
				errorMsg = driver.findElement(By.xpath("//small[normalize-space()='Batch Description is required.']"));
				flag = cUtils.validateErrorMsg(errorMsg.getText(), expectedErrorMsg);
				break;

			case "programField":
				errorMsg = driver.findElement(By.xpath("//small[normalize-space()='Program Name is required.']"));
				flag = cUtils.validateErrorMsg(errorMsg.getText(), expectedErrorMsg);
				break;

			case "radioBtn":
				errorMsg = driver.findElement(By.xpath("//small[normalize-space()='Status is required.']"));
				flag = cUtils.validateErrorMsg(errorMsg.getText(), expectedErrorMsg);
				break;

			case "NoOfClasses":
				errorMsg = driver.findElement(By.xpath("//small[normalize-space()='Number of classes is required.']"));
				flag = cUtils.validateErrorMsg(errorMsg.getText(), expectedErrorMsg);
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

	public String batchField(String fieldName, String invalidValues) {
		WebElement errorMsg = null;
		switch (fieldName) {
		case "batchName":
			nameTextField.sendKeys(invalidValues);
			errorMsg = driver.findElement(By.xpath("//small[@id='text-danger']"));
			return errorMsg.getText();

		case "Description":
			descriptionTextField.sendKeys(invalidValues);
			errorMsg = driver.findElement(By.xpath("//small[@id='text-danger']"));
			return errorMsg.getText();

		}

		return errorMsg.getText();
	}

	public void checkURL(String expectedString) {
		String currentUrl = driver.getCurrentUrl();
		try {
			Assert.assertTrue(currentUrl.contains("manage batch"), "URL does not contain 'manage batch'");
		} catch (Exception e) {
			Assert.fail("URL does not contain 'manage batch");
		}
	}

	public void checkHeader(String headerText) {
		try {
			String cardTitleText = cardTitleElement.getText();
			Assert.assertTrue(cardTitleText.contains("Manage Batch"), "Header 'Manage Batch' not found");
		} catch (Exception e) {
			Assert.fail("Header 'Manage Batch' not found");
		}
	}

	public void checkPaginatorElementsDisplayed() {
		List<WebElement> buttonElements = parentPaginators.findElements(By.tagName("button"));
		for (WebElement button : buttonElements) {
			Assert.assertTrue(button.isDisplayed(), "Button is not displayed: " + button.getText());
		}
		for (WebElement button : noPaginators) {
			Assert.assertTrue(button.isDisplayed(), "Button is not displayed: " + button.getText());
		}

	}

	public List<String> getTableHeader(String sheetName, Integer rowno) throws InvalidFormatException, IOException {
		String tableHeaders;
		List<String> failedAssertions = new ArrayList<>();
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowno);
		for (rowno = 0; rowno < 7; rowno++) {
			tableHeaders = data.get(rowno).get("TableHeader");
			List<String> tableHead = cUtils.getTableHeaderFromTable(tableHeader);
			System.out.println("Asserting :" + tableHead.contains(tableHeaders));
			if (!tableHead.contains(tableHeaders)) {
				failedAssertions.add("Table header '" + tableHeaders + "' not found in row " + (rowno + 1));
			}
		}

		Assert.assertTrue(failedAssertions.isEmpty(), "Failed assertions: " + failedAssertions);
		return null;
	}

	public void checkDeleteButton() {
		String disabledAttributeValue = deleteButton.getAttribute("disabled");
		System.out.println("disabledAttributeValueManage header delete :  "+disabledAttributeValue);
		Assert.assertEquals(disabledAttributeValue, "true", "Delete button is not disabled");
	}

	public void checkNewBatchStringPresent(String string) {

		Assert.assertTrue(newBatchElt.isDisplayed(), "New batch element is not displayed");

	}

	public void checkDataTableForChkBox() {
		WebElement tableBody = driver.findElement(By.xpath("//table[@role='grid']"));

		List<WebElement> rows = tableBody.findElements(By.xpath(".//tbody/tr"));
		for (WebElement row : rows) {
			WebElement firstCell = row.findElement(By.xpath(".//td[1]"));
			WebElement checkbox = firstCell.findElement(By.tagName("input"));
			if(!checkbox.isSelected())
			{
				
			}
			Assert.assertEquals(checkbox.getAttribute("type"), "checkbox", "Checkbox not found in row");
		}

	}

	public void checkDeleteButtonEachRow() {

		List<WebElement> rows = tableBody.findElements(By.xpath(".//tbody/tr"));
		for (WebElement row : rows) {
			WebElement firstCell = row.findElement(By.xpath(".//td[7]"));
			WebElement deleteBtn = firstCell
					.findElement(By.xpath("//tr[@class='ng-star-inserted'][1]//button[@icon='pi pi-trash']"));
			Assert.assertTrue(deleteBtn.isEnabled(), "Delete icon is not enabled");
		}

	}
	
	public void SelectTheManageHeaderDelete(WebElement checkbox)
	{
		if(!checkbox.isSelected())
		{
			checkbox.click(); 
            System.out.println("Checkbox selected");		           
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@icon='pi pi-trash' and not(@disabled)]")));
		}
	}


	
	public List<String> selectMultipleCheckboxesA() throws InterruptedException {
	   
		WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.sendKeys("BatchTribe");

		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));

		secondColumnTexts = new ArrayList<>();		
		System.out.println("cleared the list in the begining");
		List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='p-datatable-tbody']/tr"));

		for (WebElement row : rows) {
			WebElement checkbox = row.findElement(By.xpath("./td[1]//div[@role='checkbox']"));
			if (!checkbox.isSelected()) {
				checkbox.click();
				secondColumnElement = row.findElement(By.xpath("./td[2]"));
				String text = secondColumnElement.getText();
				secondColumnTexts.add(text);
				System.out.println(text);
			}

		}
		clickManageHeaderDeleteIconForMultipleRow();
		return secondColumnTexts;

	}

	   
	
	    
	public void clickManageHeaderDeleteIconForMultipleRow() {
		try {
			if (deleteButton.isEnabled()) {				
				Thread.sleep(1000);			
				deleteButton.click();
				Thread.sleep(1000);
				System.out.println("delete icon clicked");
				clickYesInConfirmationDialog();
			}
		} catch (Exception e) {
			System.out.println("No batch to delete");
		}
}
	
	public void clickYesInConfirmationDialog() {
	   try {
		   Thread.sleep(1000);
	     yesButton.click();
	     Thread.sleep(1000);
	     
	     System.out.println("yesopton is clicked");
	    } catch (Exception e) {
	        System.out.println("Confirmation dialog not found or Yes button not present.");
	    }
	}

//	public boolean verifyDeletedBatches(List<String> deletedBatchNames) {
//
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
//		List<WebElement> batchNameElements = driver.findElements(By.xpath("//table//tbody//tr//td[2]"));
//		List<String> batchNames = batchNameElements.stream().map(WebElement::getText).collect(Collectors.toList());
//
//		System.out.println("Inside verify delete batch code");
//		for (String deletedBatchName : deletedBatchNames) {
//			System.out.println(deletedBatchName+"checking the batch deleted");
//
//			if (batchNames.contains(deletedBatchName)) {
//				return false;
//			}
//		}
//		//deletedBatchNames.clear();
//		return true;
//	}
	
	
	public boolean verifyDeletedBatches(List<String> deletedBatchNames) {	
	boolean isDeletedBatchPresent = false;

	while (true) {
	    // Wait for the presence of table rows
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
	    
	    // Find all batch name elements on the current page
	    List<WebElement> batchNameElements = driver.findElements(By.xpath("//table//tbody//tr//td[2]"));
	    
	    // Extract text from batch name elements and collect them into a list
	    List<String> batchNames = batchNameElements.stream()
	                                               .map(WebElement::getText)
	                                               .collect(Collectors.toList());
	    
	    // Check if any of the deleted batch names are present on this page
	    for (String deletedBatchName : deletedBatchNames) {
	        if (batchNames.contains(deletedBatchName)) {
	            isDeletedBatchPresent = true;
	            break;
	        }
	    }
	    
	    if (isDeletedBatchPresent) {
	        break; // Exit the loop if a deleted batch name is found
	    }
	    
	    if (!nextPageButton.isEnabled()) {
	        break; // Exit the loop if there are no more pages
	    }
	    
	    try {
	        WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-paginator-icon pi pi-angle-right']")));
	        JavascriptExecutor executor = (JavascriptExecutor) driver;
	        executor.executeScript("arguments[0].click();", nextPageButton);
	        nextPageButton.click();
	    } catch (ElementClickInterceptedException e) {
	        // If the button is not clickable due to an intercepting element, try clicking using JavaScript
	        
	    
	    } catch (NoSuchElementException e) {
	        // Print an error message if the next page button is not found
	        System.out.println("Next page button not found: " );
	    }
	   // nextPageBtn.click(); // Navigate to the next page
	
	}
	return !isDeletedBatchPresent;  // Return true if no deleted batch name is found

	}
		

	
	public void verifyTheDeletedBatches()
	{
		boolean batchesDeleted = verifyDeletedBatches(deletedBatchNames);
		if (batchesDeleted) {
			System.out.println("Batches deleted successfully.");
		} else {
			System.out.println("Failed to delete batches.");
		}
	}

	public void clickSingleChkbox() throws InterruptedException {
		 WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		    searchBox.sendKeys(batchName);
		    System.out.println(batchName+ "  "+"batchname im deleteing");		
		   
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
		  
		    WebElement checkbox = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]//div[@role='checkbox']"));
		  
		    checkbox.click();
		 
		    System.out.println("checkbox selected for the rows");
	}

	
	public void clickMDeleteIconForsingleRow()
	{
		    WebElement deleteIcon = driver.findElement(By.xpath("//table//tbody//tr[1]//td[7]//button[@icon='pi pi-trash']"));
		    deleteIcon.click();
		    System.out.println("deleted for the rows");
	}

	public void assertYesOrNoBtn() {		
		Assert.assertTrue(yesButton.isDisplayed(), "'Yes' optionbutton is not displayed");
		Assert.assertTrue(noButton.isDisplayed(), "'No' optionbutton is not displayed");
		System.out.println("Assertion yes button done");
	}

	public void clickYesOption() {
		yesButton.click();
		System.out.println("yes button clicked");
	}

	public void verifyTheDeletedMessage() {
		try {
			String ExpectedMessageText = "Successful";
			WebElement batchDeletedMsg = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='p-toast-summary ng-tns-c90-39']")));
			String actualMessageText = batchDeletedMsg.getText();
			System.out.println("actual Success Message: " + actualMessageText);
			System.out.println("Expected :" + ExpectedMessageText);
			cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
			boolean flag = checkForTheAddedBatch();
			System.out.println("Checked for the message:" + flag);
			Assert.assertFalse(flag);
		} catch (Exception e) {
			// Assert.fail("Batch Deleted message not found within the timeout period.");
		}

	}

	public void clickNoOption() {
		noButton.click();
		System.out.println("No button clicked");

	}

	public void checkfordeletedBatch() {
		boolean flag = checkForTheAddedBatch();
		System.out.println("Checked for the batch message:" + flag);
		Assert.assertFalse(flag);
	}

	public void selectTheChkBox() {
		String deleteBatch = batchName;
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='filterGlobal']"));	
		searchBox.sendKeys(deleteBatch);	
		List<WebElement> rows = tableBody.findElements(By.xpath(".//tbody/tr"));

		for (WebElement row : rows) {
			WebElement secondCell = row.findElement(By.xpath(".//td[2]"));
			WebElement firstCell = row.findElement(By.xpath(".//td[1]"));
			System.out.println(secondCell.getText());
			if (secondCell.getText().equalsIgnoreCase(deleteBatch)) {
				WebElement checkbox = firstCell.findElement(By.tagName("input"));
				if(!checkbox.isSelected())				{
					checkbox.click(); // Click to select checkbox if not already selected
		            System.out.println("Checkbox selected");
				}
//				WebElement deleteBtn = secondCell
//						.findElement(By.xpath("//tr[@class='ng-star-inserted'][1]//button[@icon='pi pi-trash']"));
//				deleteBtn.click();
				break;				
			}
		}
		
	}

	public void checkDataTableForChkBoxSelected() {
		List<WebElement> checkboxes = driver.findElements(By.xpath("//table//input[@type='checkbox']")); 
        boolean noneSelected = true;
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                noneSelected = false;
                break;  
            }
        }
       
        if (noneSelected) {
            System.out.println("None of the checkboxes in the data table are selected.");          
        } else {
            System.out.println("At least one checkbox in the data table is selected.");
           
        }
		
	}

	public void checkEditIconisEnabled() {	
		WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.sendKeys(batchName);
		try {
			// populate then row
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));

			editIcon = driver.findElement(By.xpath("//span[@class='p-button-icon pi pi-pencil']"));

			Assert.assertTrue(editIcon.isEnabled());

		} catch (Exception e) {
			System.out.println("editicon not enabled");
		}

	}

	public void clicksTheEditIcon() {
		 editIcon.click();
		 System.out.println("clicked edit iccon");
	}

	public void updateBatchDetails(String sheetName, Integer rowno) throws InvalidFormatException, IOException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowno);
		description = data.get(rowno).get("UpdateBatchDescription");
		noOfClasses=data.get(rowno).get("UpdateNoofClasses");		
		descriptionTextField.sendKeys(description);
		if(!statusINactiveRadiobtn.isSelected())
		{
			statusINactiveRadiobtn.click();
		}
		noOfClassesSnipperField.sendKeys(noOfClasses);
		saveBtn.click();
		verifySuccessMessage();		
	}

	public void verifyBatchUpdated() {
		
		
	}

	public void logoutOftheApp() throws InterruptedException {
		WebElement logoutButton = driver.findElement(By.xpath("//span[text()='Logout']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", logoutButton);
	}

}
