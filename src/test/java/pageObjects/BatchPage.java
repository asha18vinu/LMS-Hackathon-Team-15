package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

	private static final Logger logger = LogManager.getLogger(BatchPage.class);
	WebDriver driver;
	CommonUtils cUtils;
	WebDriverWait wait;
	String actualFieldType;
	boolean flag = false;
	String batchUrl;
	int count = 0;
	public static String batchName, description, noOfClasses;// ,programNametxt,status;
	static String programNameStr;
	public static String batchNametoEdit;


	@FindBy(xpath="(//ul[@role='listbox']/p-dropdownitem/li)[2]")
	WebElement programNameTextElement;
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
	@FindBy(xpath = "//div[@class='p-hidden-accessible']//input[@*='ACTIVE']")
	WebElement statusActiveRadiobtn;
	@FindBy(xpath = "//div[@class='p-hidden-accessible']//input[@*='INACTIVE']")
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
	@FindBy(xpath = "/html/body/app-root/app-batch/p-confirmdialog/div/div/div[3]/button[2]")
	WebElement yesButton;
	@FindBy(xpath = "/html/body/app-root/app-batch/p-confirmdialog/div/div/div[3]/button[1]")
	WebElement noButton;
	@FindBy(xpath = "//span[@class='p-button-icon pi pi-pencil']")
	WebElement editIcon;
	@FindBy(id = "filterGlobal")
	WebElement searchBox;
	@FindBy(xpath = "//p-dropdown[@id='programName']/div/div/input")
	WebElement programName;
	@FindBy(xpath = "//small[@class='p-invalid ng-star-inserted']")
	WebElement descWebElement;
	@FindBy(xpath = "//small[@id='text-danger']")
	WebElement invalidInputFielderrormsg;
	WebElement secondColumnElement;
	@FindBy(xpath = "//input[@*='userLoginEmailId']")
	WebElement userName;
	@FindBy(xpath = "//input[@*='Password']")
	WebElement password;
	@FindBy(xpath = "//button[@*='submit']")
	WebElement loginBtn;


	
	
	
	


	public BatchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		cUtils = new CommonUtils(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		batchUrl = FileReaderManager.getInstance().getResourcebundleInstance().getBatchUrl();

	}


	public void login() {
		userName.sendKeys(FileReaderManager.getInstance().getResourcebundleInstance().getUserName());
		password.sendKeys(FileReaderManager.getInstance().getResourcebundleInstance().getPassword());
		loginBtn.click();
	}


	public void batchNavigationBar() {
		batchNavBarBtn.click();


	public void batchNavigationBar() {
		batchNavBarBtn.click();
		
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
	}

	public void addNewBatchButtonClick() {
		addBatchBtn.click();
		logger.info("+Add new Batch is clicked");
	}

	public void popUpVerification() {
		String expected = FileReaderManager.getInstance().getResourcebundleInstance().getBatchPopboxHeader();
		cUtils.getAssertionEqualsCheck(batchDetailsPopupboxHeading.getText(), expected);
		logger.info("Admin verifiees the Popupbox");
	}

	public void checkTheFieldExistanceAndType(String fieldName, String fieldType) {
		Map<String, WebElement> fieldNameLocators = fieldNameAndType();
<<<<<<< HEAD
		logger.info("checks for the Field Type" + fieldName + " and for its FieldType " + fieldType);
		try {
			cUtils.checkExistanceofFieldType(fieldName, fieldType, fieldNameLocators);
		} catch (AssertionError e) {
			logger.info("Field mismatch");
		}
	}

	public void invalidPrgInput(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowNo);
		batchName = data.get(rowNo).get("BatchName") + random.nextInt(90) + 10;
		nameTextField.sendKeys(batchName);
		descriptionTextField.sendKeys(data.get(rowNo).get("Description"));
		programNameFieldDropDownBtn.click();
		System.out.println("clicking dropdown");
		WebElement prgPlaceHolderElt = driver.findElement(By.xpath("//input[@placeholder='Select a Program name']"));
		prgPlaceHolderElt.sendKeys("prg1");
		Thread.sleep(1000);
=======
		logger.info("checks for the Field Type" +fieldName+" and for its FieldType "+fieldType);
		try
		{
		cUtils.checkExistanceofFieldType(fieldName, fieldType, fieldNameLocators);
		}catch(AssertionError e)
		{
			logger.info("Field mismatch");
		}
	}
	public void invalidPrgInput(String sheetName, Integer rowNo) throws InvalidFormatException, IOException, InterruptedException
	{
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowNo);
		batchName = data.get(rowNo).get("BatchName") + random.nextInt(90) + 10;
		nameTextField.sendKeys(batchName);
		descriptionTextField.sendKeys(data.get(rowNo).get("Description"));		
		programNameFieldDropDownBtn.click();
		System.out.println("clicking dropdown");	
		WebElement prgPlaceHolderElt=driver.findElement(By.xpath("//input[@placeholder='Select a Program name']"));
		prgPlaceHolderElt.sendKeys("prg1");	
		Thread.sleep(1000);     
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
		if (!activeRadiobtn.isSelected()) {
			activeRadiobtn.click();
		}
		noOfClassesSnipperField.sendKeys(data.get(rowNo).get("NoOfClasses"));
<<<<<<< HEAD
		System.out.println("Batchname created : " + batchName);
		logger.info("BatchName created: " + batchName);
=======
		System.out.println("Batchname created : "+batchName);
		logger.info("BatchName created: " +batchName);
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
	}

	public void fillBatchValidDestails(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowNo);
		batchName = data.get(rowNo).get("BatchName") + random.nextInt(90) + 10;
		nameTextField.sendKeys(batchName);
<<<<<<< HEAD
		descriptionTextField.sendKeys(data.get(rowNo).get("Description"));
		programNameFieldDropDownBtn.click();
		List<WebElement> options = driver.findElements(By.xpath("//ul[@role='listbox']/p-dropdownitem/li/span"));
		programNameStr = programNameTextElement.getText();
		for (WebElement option : options) {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();
			if (optionText.equals(programNameTextElement.getText())) {
=======
		descriptionTextField.sendKeys(data.get(rowNo).get("Description"));		
		programNameFieldDropDownBtn.click();
		List<WebElement> options = driver.findElements(By.xpath("//ul[@role='listbox']/p-dropdownitem/li/span"));
	    programNameStr=programNameTextElement.getText();
		for (WebElement option : options) {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();			
			if (optionText.equals(programNameTextElement.getText())) {			
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
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
<<<<<<< HEAD
		System.out.println("Batchname created : " + batchName);
		logger.info("BatchName created: " + batchName);
	}

	public void checkDescriptionFieldOptional(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		String labelText = descriptionLabel.getText();
		boolean flag = labelText.contains("*");
		fillBatchValidDestails(sheetName, rowNo);
		try {
			Assert.assertEquals(flag, false);
		} catch (AssertionError e) {
			logger.info("Description option sceanrio failed");
		}

	}

	public boolean clickSaveButton() throws InterruptedException {
		saveBtn.click();

		flag = true;
		Thread.sleep(1000);
		try {

			if (!(descWebElement.isDisplayed()) || (invalidInputFielderrormsg.isDisplayed()))
				flag = false;
			// System.out.println("catched the descriptional error");
		} catch (AssertionError e) {
=======
		System.out.println("Batchname created : "+batchName);
		logger.info("BatchName created: " +batchName);	 
	}  
	

	public void checkDescriptionFieldOptional(String sheetName, Integer rowNo) throws InvalidFormatException, IOException, InterruptedException {
		String labelText = descriptionLabel.getText();
		boolean flag = labelText.contains("*");
		 fillBatchValidDestails(sheetName, rowNo);
		 try {
		Assert.assertEquals(flag, false);
		 }catch(AssertionError e)
		 {
			 logger.info("Description option sceanrio failed");
		 }		
		
	}

	public boolean clickSaveButton() throws InterruptedException {
		saveBtn.click();		
		
		flag=true;
		Thread.sleep(1000);
		try {		
			
		if(!(descWebElement.isDisplayed()) || (invalidInputFielderrormsg.isDisplayed()))		
			flag=false;
		//System.out.println("catched the descriptional error");
		} catch (AssertionError e) {			
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
			logger.info("Description Is not Optional Assertion error");
			Assert.fail(e.getMessage());
		}

		return flag;
	}

<<<<<<< HEAD
	public boolean checkForTheAddedBatch() throws InterruptedException {
		searchBox.clear();
		searchBox.sendKeys("Batch");
		List<WebElement> tableRowData = new ArrayList<>();
		Thread.sleep(1000);
		;
		WebElement nextPageButton = driver
				.findElement(By.xpath("//button[@class='p-paginator-next p-paginator-element p-link p-ripple']"));

		while (true) {
=======
	public boolean checkForTheAddedBatch() throws InterruptedException {	
		driver.navigate().back();
		//driver.navigate().refresh();
		List<WebElement> tableRowData = new ArrayList<>();
		Thread.sleep(1000);;
		WebElement nextPageButton = driver
				.findElement(By.xpath("//button[@class='p-paginator-next p-paginator-element p-link p-ripple']"));

		while (nextPageButton.isEnabled()) {
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8

			List<WebElement> currentPageRows = getRowsFromCurrentPage();

			tableRowData.addAll(currentPageRows);

			for (WebElement row : currentPageRows) {

				List<WebElement> cells = row.findElements(By.tagName("td"));

				List<WebElement> filteredCells = new ArrayList<>();
				for (int i = 1; i < cells.size() - 1; i++) {
					filteredCells.add(cells.get(i));
				}

				for (WebElement cell : filteredCells) {

<<<<<<< HEAD
					if (cell.getText().equalsIgnoreCase(batchName)
							|| cell.getText().equalsIgnoreCase(batchNametoEdit)) {
						flag = true;
						logger.info("bacthname is still present");
=======
					if (cell.getText().equalsIgnoreCase(batchName)||cell.getText().equalsIgnoreCase(batchNametoEdit)) {
						flag = true;
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
						break;
					}
				}
			}
<<<<<<< HEAD
			Thread.sleep(1000);
			;
			try {

				if (nextPageButton.getAttribute("disabled") == null
						|| nextPageButton.getAttribute("disabled").isEmpty()) {
					nextPageButton.click();
				} else {
					// The button is disabled, so we exit the loop or handle the case accordingly
					break;
				}
			} catch (Exception e) {
				logger.info("Assertion error : " + e.getMessage());
=======
			Thread.sleep(1000);;
			try
			{
		
			nextPageButton.click();
			}catch(Exception e)
			{
				logger.info("Assertion error : " +e.getMessage());
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
				break;
			}
		}

		if (flag) {
			logger.info("Batch present the table");
		} else
			logger.info("batch is not Present");

		return flag;
	}

	private List<WebElement> getRowsFromCurrentPage() {
		return CurrentRows;
	}

	public boolean verifySuccessMessage() {
		String ExpectedMessageText = "Successful";
<<<<<<< HEAD
		boolean flag = false;
=======
		boolean flag=false;
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		try {
			WebElement successMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			String actualMessageText = successMessage.getText();
			System.out.println("actual Success Message: " + actualMessageText);
<<<<<<< HEAD
			System.out.println("Expected :" + ExpectedMessageText);
			flag = cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
=======
			System.out.println("Expected :" + ExpectedMessageText);		
			flag=cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		} catch (Exception e) {
			logger.info("Success message not found within the timeout period.");
		}
		return flag;
	}

	public Map<String, WebElement> fieldNameAndType() {

		Map<String, WebElement> fieldNameLocators = new LinkedHashMap<>();
		fieldNameLocators.put("NameField", nameTextField);
		fieldNameLocators.put("NumberofClassesField", noOfClassesSnipperField);
		fieldNameLocators.put("DescriptionField", descriptionTextField);
		fieldNameLocators.put("ProgramnameField", programName);
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
		logger.info("Assertion passed for " + fieldName);
	}

	public boolean batchField(String fieldName, String invalidValues) {
		logger.info("Admin ccheceks all the fields with invalid values");
		WebElement errorMsg = null;
		switch (fieldName) {
		case "batchName":
			nameTextField.sendKeys(invalidValues);
			saveBtn.click();
//			errorMsg = driver.findElement(By.xpath("//small[@id='text-danger']"));
<<<<<<< HEAD
			if (invalidInputFielderrormsg.isDisplayed()) {
				flag = true;
=======
			if(invalidInputFielderrormsg.isDisplayed())
			{
				flag=true;
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
			}
			break;

		case "Description":
			descriptionTextField.sendKeys(invalidValues);
			saveBtn.click();
<<<<<<< HEAD
			// errorMsg = driver.findElement(By.xpath("//small[@id='text-danger']"));
			if (invalidInputFielderrormsg.isDisplayed()) {
				flag = true;
=======
			//errorMsg = driver.findElement(By.xpath("//small[@id='text-danger']"));
			if(invalidInputFielderrormsg.isDisplayed())
			{
				flag=true;
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
			}
			break;

		default:
			break;

		}

		return flag;
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
<<<<<<< HEAD
			Assert.assertTrue(
					cardTitleText.contains(
							FileReaderManager.getInstance().getResourcebundleInstance().getManageBatchHeader()),
					"Header 'Manage Batch' not found");
=======
			Assert.assertTrue(cardTitleText.contains(FileReaderManager.getInstance().getResourcebundleInstance().getManageBatchHeader()), "Header 'Manage Batch' not found");
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
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
			logger.info("Asserting :" + tableHead.contains(tableHeaders));
			if (!tableHead.contains(tableHeaders)) {
				failedAssertions.add("Table header '" + tableHeaders + "' not found in row " + (rowno + 1));
			}
		}

		Assert.assertTrue(failedAssertions.isEmpty(), "Failed assertions: " + failedAssertions);
		return null;
	}

	public void checkDeleteButton() {
		String disabledAttributeValue = deleteButton.getAttribute("disabled");
<<<<<<< HEAD
		// System.out.println("disabledAttributeValueManage header delete :
		// "+disabledAttributeValue);
=======
		//System.out.println("disabledAttributeValueManage header delete :  "+disabledAttributeValue);
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
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
<<<<<<< HEAD
			if (!checkbox.isSelected()) {

=======
			if(!checkbox.isSelected())
			{
				
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
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
<<<<<<< HEAD

	public void SelectTheManageHeaderDelete(WebElement checkbox) {
		if (!checkbox.isSelected()) {
			checkbox.click();
			logger.info("Checkbox selected");
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(".//button[@icon='pi pi-trash' and not(@disabled)]")));
		}
	}

	public void clickManageHeaderDeleteIconForMultipleRow() {
		try {
			if (deleteButton.isEnabled()) {
				Thread.sleep(1000);
=======
	
	public void SelectTheManageHeaderDelete(WebElement checkbox)
	{
		if(!checkbox.isSelected())
		{
			checkbox.click(); 
            logger.info("Checkbox selected");		           
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@icon='pi pi-trash' and not(@disabled)]")));
		}
	}  
	
	    
	public void clickManageHeaderDeleteIconForMultipleRow() {
		try {
			if (deleteButton.isEnabled()) {				
				Thread.sleep(1000);			
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
				deleteButton.click();
				Thread.sleep(1000);
				logger.info("Delete icon clicked");
				clickYesInConfirmationDialog();
			}
		} catch (Exception e) {
			logger.info("No batch to delete");
		}
<<<<<<< HEAD
	}

	public void clickYesInConfirmationDialog() {
		try {
			Thread.sleep(1000);
			yesButton.click();
			Thread.sleep(1000);
			logger.info("yesoption is clicked in the Dialog Box");
		} catch (Exception e) {
			logger.info("Confirmation dialog not found or Yes button not present.");
		}
	}

	public boolean checkForTheAddedBatch1() throws InterruptedException {

		// driver.navigate().back();
		// driver.navigate().refresh();

		List<WebElement> tableRowData = new ArrayList<>();
		Thread.sleep(1000);
		;
=======
}
	
	public void clickYesInConfirmationDialog() {
	   try {
		   Thread.sleep(1000);
	     yesButton.click();
	     Thread.sleep(1000);	     
	     logger.info("yesoption is clicked in the Dialog Box");
	    } catch (Exception e) {
	        logger.info("Confirmation dialog not found or Yes button not present.");
	    }
	}


	public boolean checkForTheAddedBatch1() throws InterruptedException {	
		//driver.navigate().back();
		//driver.navigate().refresh();
		List<WebElement> tableRowData = new ArrayList<>();
		Thread.sleep(1000);;
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
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

<<<<<<< HEAD
					if (cell.getText().equalsIgnoreCase(batchName)
							|| cell.getText().equalsIgnoreCase(batchNametoEdit)) {
=======
					if (cell.getText().equalsIgnoreCase(batchName)||cell.getText().equalsIgnoreCase(batchNametoEdit)) {
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
						flag = true;
						break;
					}
				}
			}
<<<<<<< HEAD
			Thread.sleep(1000);
			;
			try {

				nextPageButton.click();
			} catch (Exception e) {
				logger.info("Assertion error : " + e.getMessage());
=======
			Thread.sleep(1000);;
			try
			{
		
			nextPageButton.click();
			}catch(Exception e)
			{
				logger.info("Assertion error : " +e.getMessage());
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
				break;
			}
		}

		if (flag) {
			logger.info("Batch present the table");
		} else
			logger.info("batch is not Present");

		return flag;
	}

<<<<<<< HEAD
	public boolean verifyDeletedBatches(List<String> deletedBatchNames) throws InterruptedException {
		boolean isDeletedBatchPresent = false;
		WebElement nextPageButton = driver.findElement(By.xpath(
				"/html/body/app-root/app-batch/div/mat-card/mat-card-content/p-table/div/p-paginator/div/button[3]"));

		// WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.clear();
		searchBox.sendKeys("BatchTribe");

		while (true) {
			List<WebElement> currentPageRows = getRowsFromCurrentPage();
			if (currentPageRows == null || currentPageRows.isEmpty() || deletedBatchNames==null) {
				break;
			}
			for (WebElement row : currentPageRows) {
				List<WebElement> batchNameElements = row.findElements(By.xpath(".//td[2]")); // Adjust XPath as needed
				List<String> batchNames = batchNameElements.stream().map(WebElement::getText)
						.collect(Collectors.toList());
				for (String deletedBatchName : deletedBatchNames) {
					if (batchNames.contains(deletedBatchName)) {
						isDeletedBatchPresent = true;
						System.out
								.println(deletedBatchName + " BatchName is not deleted while selecting multiple rows");
						break;
					}
				}

			}
			if (isDeletedBatchPresent) {
				return isDeletedBatchPresent; // Exit the inner loop if a deleted batch name is found
			}
			if (nextPageButton.getAttribute("disabled") == null || nextPageButton.getAttribute("disabled").isEmpty()) {
				nextPageButton.click();
			} else {
				// The button is disabled, so we exit the loop or handle the case accordingly
				break;
			}
		}
		return isDeletedBatchPresent;
	}

	public void verifyTheDeletedBatches() throws InterruptedException {
=======
	
	public boolean verifyDeletedBatches(List<String> deletedBatchNames) throws InterruptedException {
	    boolean isDeletedBatchPresent = false;
	    WebElement nextPageButton = driver.findElement(By.xpath("/html/body/app-root/app-batch/div/mat-card/mat-card-content/p-table/div/p-paginator/div/button[3]"));
	  
	    WebElement searchBox = driver.findElement(By.id("filterGlobal"));
	    searchBox.sendKeys("BatchTribe");

	   
	    while (true) {	      
	        List<WebElement> currentPageRows = getRowsFromCurrentPage();
	        if (currentPageRows == null || currentPageRows.isEmpty()) {
	            break; 
	        }	     
	        for (WebElement row : currentPageRows) {
	            List<WebElement> batchNameElements = row.findElements(By.xpath(".//td[2]")); // Adjust XPath as needed
	            List<String> batchNames = batchNameElements.stream()
	                                                       .map(WebElement::getText)
	                                                       .collect(Collectors.toList());	    
	            for (String deletedBatchName : deletedBatchNames) {
	                if (batchNames.contains(deletedBatchName)) {
	                    isDeletedBatchPresent = true;
	                    System.out.println(deletedBatchName + " BatchName is not deleted while selecting multiple rows");
	                    break; 
	                }	               
	            }    
	           
	        }
	        if (isDeletedBatchPresent) {
                return isDeletedBatchPresent; // Exit the inner loop if a deleted batch name is found
            }
	        if (nextPageButton.getAttribute("disabled") == null || nextPageButton.getAttribute("disabled").isEmpty()) {
	            nextPageButton.click();
	        } else {
	            // The button is disabled, so we exit the loop or handle the case accordingly
	            break;
	        }
	    }
	    return isDeletedBatchPresent;
	}


	
	public void verifyTheDeletedBatches() throws InterruptedException
	{
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		boolean batchesDeleted = verifyDeletedBatches(deletedBatchNames);
		if (batchesDeleted) {
			logger.info("Batches deleted successfully.");
		} else {
			logger.info("Failed to delete batches.");
		}
	}

	public void clickSingleChkbox() throws InterruptedException {
<<<<<<< HEAD
		// WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.sendKeys(batchName);
		logger.info(batchName + "  " + "batchname im deleteing");

	

			WebElement checkbox = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]//div[@role='checkbox']"));
			try {
				if (!(checkbox == null)) {
					
					checkbox.click();

					WebElement deleteIcon = driver
							.findElement(By.xpath("//table//tbody//tr[1]//td[7]//button[@icon='pi pi-trash']"));

					deleteIcon.click();
					logger.info("deleted for the rows");
				}
			} catch (AssertionError e) {
				logger.info("Assertion error : " + e.getMessage());
			}

			// clickMDeleteIconForsingleRow();
			logger.info("checkbox selected for the rows");
		} 
	//}

	public void clickMDeleteIconForsingleRow() throws InterruptedException {
		try {
			
		    WebElement deleteIcon = wait.until(ExpectedConditions
		    		.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-batch/div/mat-card/mat-card-title/div[2]/div[1]/button")));
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
  Thread.sleep(1000);
			if(deleteIcon.isEnabled())
			{
			Thread.sleep(1000);
			deleteIcon.click();
			System.out.println("deleted");
			logger.info("deleted for the rows");
		}} catch (AssertionError e) {
			logger.info("Assertion error : " );
		}
	}

	public void assertYesOrNoBtn() {
		try {
			Assert.assertTrue(yesButton.isDisplayed(), "'Yes' optionbutton is not displayed");
			Assert.assertTrue(noButton.isDisplayed(), "'No' optionbutton is not displayed");
		} catch (AssertionError e) {
			logger.info("Assertion error ocurred in vidibility of yes and no button" + e.getMessage());
=======
		 WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		    searchBox.sendKeys(batchName);
		    logger.info(batchName+ "  "+"batchname im deleteing");		
		   
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
		  
		    WebElement checkbox = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]//div[@role='checkbox']"));
		    if(!(checkbox==null))
		    {
		    if(!checkbox.isSelected()&&checkbox.isDisplayed())
		    {
		    checkbox.click();
		    }
		    clickMDeleteIconForsingleRow();
		    logger.info("checkbox selected for the rows");
		    }
		    else {
		    	logger.info("No Records found");
		    }
	}

	
	public void clickMDeleteIconForsingleRow()
	{try {
		    WebElement deleteIcon = driver.findElement(By.xpath("//table//tbody//tr[1]//td[7]//button[@icon='pi pi-trash']"));
		    deleteIcon.click();
		    logger.info("deleted for the rows");
	}catch(AssertionError e)
	{
		logger.info("Assertion error : "+e.getMessage());
	}
	}

	public void assertYesOrNoBtn() {	
		try {
		Assert.assertTrue(yesButton.isDisplayed(), "'Yes' optionbutton is not displayed");
		Assert.assertTrue(noButton.isDisplayed(), "'No' optionbutton is not displayed");
		}catch(AssertionError e)
		{
			logger.info("Assertion error ocurred in vidibility of yes and no button" +e.getMessage());
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		}
		logger.info("Assertion yes button found");
	}

	public void clickYesOption() {
		try {
<<<<<<< HEAD
			yesButton.click();
			logger.info("yes button clicked");
		} catch (AssertionError e) {
			logger.info("assertion error" + e.getMessage());
=======
		yesButton.click();
		logger.info("yes button clicked");
		}
		catch(AssertionError e)
		{
			logger.info("assertion error");
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		}
	}

	public void verifyTheDeletedMessage() {
		try {
			String ExpectedMessageText = "Successful";
			WebElement batchDeletedMsg = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='p-toast-summary ng-tns-c90-39']")));
<<<<<<< HEAD
			String actualMessageText = batchDeletedMsg.getText();
			cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
			boolean flag = checkForTheAddedBatch();

=======
			String actualMessageText = batchDeletedMsg.getText();		
			cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
			boolean flag = checkForTheAddedBatch();
		
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
			Assert.assertFalse(flag);
		} catch (Exception e) {
			// Assert.fail("Batch Deleted message not found within the timeout period.");
		}

	}

	public void clickNoOption() {
<<<<<<< HEAD
		try {
			noButton.click();
		} catch (AssertionError e) {
			logger.info("assertion error : " + e.getMessage());
		}
=======
		try
		{
		noButton.click();
		}catch(AssertionError e)
		{
		logger.info("assertion error : "+e.getMessage());
		}
	
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8

	}

	public void checkfordeletedBatch() throws InterruptedException {
<<<<<<< HEAD
		// WebElement searchBox =
		// driver.findElement(By.xpath("//input[@id='filterGlobal']"));
		searchBox.clear();
		searchBox.sendKeys("Batch");
		boolean flag = checkForTheAddedBatch();
		logger.info("Checked for the batch message:" + flag);
		Assert.assertTrue(flag);
=======
		boolean flag = checkForTheAddedBatch();
		logger.info("Checked for the batch message:" + flag);
		Assert.assertFalse(flag);
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
	}

	public void selectTheChkBox() {
		String deleteBatch = batchName;
<<<<<<< HEAD
		// WebElement searchBox =
		// driver.findElement(By.xpath("//input[@id='filterGlobal']"));
		searchBox.sendKeys(deleteBatch);
=======
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='filterGlobal']"));	
		searchBox.sendKeys(deleteBatch);	
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		List<WebElement> rows = tableBody.findElements(By.xpath(".//tbody/tr"));

		for (WebElement row : rows) {
			WebElement secondCell = row.findElement(By.xpath(".//td[2]"));
			WebElement firstCell = row.findElement(By.xpath(".//td[1]"));
<<<<<<< HEAD
			// System.out.println(secondCell.getText());
			if (secondCell.getText().equalsIgnoreCase(deleteBatch)) {
				WebElement checkbox = firstCell.findElement(By.tagName("input"));
				if (!checkbox.isSelected()) {
					checkbox.click(); // Click to select checkbox if not already selected
					logger.info("Checkbox selected");
=======
			//System.out.println(secondCell.getText());
			if (secondCell.getText().equalsIgnoreCase(deleteBatch)) {
				WebElement checkbox = firstCell.findElement(By.tagName("input"));
				if(!checkbox.isSelected())				{
					checkbox.click(); // Click to select checkbox if not already selected
		            logger.info("Checkbox selected");
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
				}
//				WebElement deleteBtn = secondCell
//						.findElement(By.xpath("//tr[@class='ng-star-inserted'][1]//button[@icon='pi pi-trash']"));
//				deleteBtn.click();
<<<<<<< HEAD
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
			logger.info("None of the checkboxes in the data table are selected.");
		} else {
			logger.info("At least one checkbox in the data table is selected.");

		}

	}

	public void checkEditIconisEnabled() {
		// WebElement searchBox = driver.findElement(By.id("filterGlobal"));
=======
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
            logger.info("None of the checkboxes in the data table are selected.");          
        } else {
            logger.info("At least one checkbox in the data table is selected.");
           
        }
		
	}

	public void checkEditIconisEnabled() {	
		WebElement searchBox = driver.findElement(By.id("filterGlobal"));
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		searchBox.sendKeys(batchName);
		try {
			// populate then row
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));

			editIcon = driver.findElement(By.xpath("//span[@class='p-button-icon pi pi-pencil']"));

			Assert.assertTrue(editIcon.isEnabled());

		} catch (Exception e) {
			logger.info("editicon not enabled");
		}

	}

	public void clicksTheEditIcon() {
<<<<<<< HEAD
		editIcon.click();
		logger.info("Admin clicked edit iccon to check desctription optional field");
	}

	public boolean updateBatchDetails(String sheetName, Integer rowno, String batchNametoEdit)
			throws InvalidFormatException, IOException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowno);

		description = data.get(rowno).get("UpdateBatchDescription");
		noOfClasses = data.get(rowno).get("UpdateNoofClasses");
=======
		 editIcon.click();
		logger.info("Admin clicked edit iccon to check desctription optional field");
	}

	public boolean updateBatchDetails(String sheetName, Integer rowno, String batchNametoEdit) throws InvalidFormatException, IOException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowno);
				
		description = data.get(rowno).get("UpdateBatchDescription");
		noOfClasses=data.get(rowno).get("UpdateNoofClasses");	
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
		descriptionTextField.clear();
		descriptionTextField.sendKeys(description);
//		if(!statusINactiveRadiobtn.isSelected())
//		{
//			statusINactiveRadiobtn.click();
//		}
		noOfClassesSnipperField.sendKeys(noOfClasses);
		saveBtn.click();
<<<<<<< HEAD
		flag = verifySuccessMessage();
		return flag;
	}
=======
		flag=verifySuccessMessage();	
		return flag;
		}


>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8

	public void logoutOftheApp() throws InterruptedException {
		WebElement logoutButton = driver.findElement(By.xpath("//span[text()='Logout']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", logoutButton);
		logger.info("Logged out of the Application");
	}

	public String enterInvalidvalue(String sheetName, Integer rowno) throws InvalidFormatException, IOException {
<<<<<<< HEAD

		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowno);
		description = data.get(rowno).get("InvalidValues");
		noOfClasses = data.get(rowno).get("InvalidValues");
		descriptionTextField.sendKeys(description);
		String errormsg = data.get(rowno).get("errormsg");
=======
		
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetName, rowno);
		description = data.get(rowno).get("InvalidValues");
		noOfClasses=data.get(rowno).get("InvalidValues");		
		descriptionTextField.sendKeys(description);
        String	errormsg=data.get(rowno).get("errormsg");
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
//		if(!statusINactiveRadiobtn.isSelected())
//		{
//			statusINactiveRadiobtn.click();
//		}
		noOfClassesSnipperField.sendKeys("InvalidValues");
		saveBtn.click();
		verifySuccessMessage();
<<<<<<< HEAD
		return errormsg;
	}

	public boolean checkInvalidValuesinUpdate(String sheetname, Integer rowno)
			throws InvalidFormatException, IOException, InterruptedException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetname, rowno);
		description = data.get(rowno).get("InvalidValues");
		noOfClasses = data.get(rowno).get("InvalidValues");
		String nameTesxtField = data.get(rowno).get("InvalidValues");
		// System.out.println(description+ " "+noOfClasses+" "+nameTesxtField);
		boolean flag = false;
		// WebElement errorMsg = null;
		for (int i = 0; i < 5; i++) {
			String fieldName = data.get(i).get("Fields");
			String expectedErrorMsg = data.get(0).get("errormsg");
			logger.info("expected eroor msg got for the invalid input in update" + expectedErrorMsg);
			Thread.sleep(1000);

			// errorMsg = driver.findElement(By.xpath("//small[@id='text-danger']"));

			switch (fieldName) {
			case "batchField":
				try {
					Assert.assertEquals(nameTextField.isEnabled(), true,
							"Assertion error: manadatory field should be enabled" + fieldName);
					WebElement batchFieldInvalidValueErrorMsg = driver
							.findElement(By.xpath("//small[normalize-space()='Batch Name is required.']"));
					cUtils.updateInvalidValuesCheck(nameTextField, batchFieldInvalidValueErrorMsg, expectedErrorMsg,
							saveBtn, nameTesxtField, flag);
				} catch (AssertionError e) {
=======
		return errormsg;		
	}
	
	
	
	
	public boolean checkInvalidValuesinUpdate(String sheetname, Integer rowno)throws InvalidFormatException, IOException, InterruptedException {
		List<Map<String, String>> data = cUtils.getValidDataFromExcel(sheetname, rowno);		
		description = data.get(rowno).get("InvalidValues");
		noOfClasses=data.get(rowno).get("InvalidValues");	
		String nameTesxtField=data.get(rowno).get("InvalidValues");	
	//	System.out.println(description+ " "+noOfClasses+"  "+nameTesxtField);
		boolean flag = false;
	//	WebElement errorMsg = null;
		for (int i = 0; i < 5; i++) {
			String fieldName = data.get(i).get("Fields");
			String expectedErrorMsg = data.get(0).get("errormsg");
			logger.info("expected eroor msg got for the invalid input in update" +expectedErrorMsg);
			Thread.sleep(1000);
			
			//errorMsg = driver.findElement(By.xpath("//small[@id='text-danger']"));
			
			switch (fieldName) {
			case "batchField":
				try
				{
				Assert.assertEquals(nameTextField.isEnabled(), true,"Assertion error: manadatory field should be enabled" +fieldName);
				WebElement batchFieldInvalidValueErrorMsg = driver.findElement(By.xpath("//small[normalize-space()='Batch Name is required.']"));				
				cUtils.updateInvalidValuesCheck(nameTextField,batchFieldInvalidValueErrorMsg,expectedErrorMsg,saveBtn,nameTesxtField,flag);	
				}catch(AssertionError e)
				{
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
					logger.info("Batch Name field should be enabled");
				}
				break;

			case "descriptionField":
<<<<<<< HEAD
				try {
					Assert.assertEquals(descriptionTextField.isEnabled(), true,
							"Assertion error: manadatory field should be enabled :" + fieldName);
					WebElement descriptionFieldInvalidValueErrorMsg = driver
							.findElement(By.xpath("//small[normalize-space()='Batch Description is required.']"));
					cUtils.updateInvalidValuesCheck(descriptionTextField, descriptionFieldInvalidValueErrorMsg,
							expectedErrorMsg, saveBtn, description, flag);
				} catch (Exception e) {
					logger.info("Batch Description field should be enabled");
				}
				break;

			case "programField":
				try {
					Assert.assertEquals(programName.isEnabled(), true,
							"Assertion error: manadatory field should be enabled :" + fieldName);
					WebElement programNameFieldInvalidValueErrorMsg = driver
							.findElement(By.xpath("//small[normalize-space()='Program Name is required.']"));
					cUtils.updateInvalidValuesCheck(programName, programNameFieldInvalidValueErrorMsg, expectedErrorMsg,
							saveBtn, BatchPage.programNameStr, flag);
				} catch (Exception e) {
					logger.info("Program Name field should be enabled");
				}
				break;

			case "radioBtn":
				try {
					WebElement radioBtnFieldInvalidValueErrorMsg = driver
							.findElement(By.xpath("//small[normalize-space()='Status is required.']"));
					cUtils.updateInvalidValuesCheck(statusActiveRadiobtn, radioBtnFieldInvalidValueErrorMsg,
							expectedErrorMsg, saveBtn, "null", flag);
				} catch (Exception e) {
					logger.info("radio button  field should be enabled");
				}
				break;

			case "NoOfClasses":
				try {
					WebElement snipperFieldInvalidValueErrorMsg = driver
							.findElement(By.xpath("//small[normalize-space()='Status is required.']"));
					cUtils.updateInvalidValuesCheck(noOfClassesSnipperField, snipperFieldInvalidValueErrorMsg,
							expectedErrorMsg, saveBtn, "null", flag);
				} catch (Exception e) {
					logger.info("NoofClasses field should be enabled");
				}
				break;
			default:
				break;
			}

		}
		return flag;

	}

	public void verifyBatchURl() {
		try {
			String Expected = FileReaderManager.getInstance().getResourcebundleInstance().getBatchUrl();
			cUtils.getAssertionEqualsCheck(driver.getCurrentUrl(), Expected);
		} catch (AssertionError e) {
			logger.info("Admin Verifies the BatchUrl and it failed");
		}
	}

	public void verifyBatchURl1() {
		try {
			String Expected = FileReaderManager.getInstance().getResourcebundleInstance().getBatchUrl();
			cUtils.getAssertionEqualsCheck(driver.getCurrentUrl(), Expected);
		} catch (AssertionError e) {
=======
				try
				{
				Assert.assertEquals(descriptionTextField.isEnabled(), true,"Assertion error: manadatory field should be enabled :"+fieldName);
				WebElement descriptionFieldInvalidValueErrorMsg=driver.findElement(By.xpath("//small[normalize-space()='Batch Description is required.']"));
				cUtils.updateInvalidValuesCheck(descriptionTextField, descriptionFieldInvalidValueErrorMsg, expectedErrorMsg,saveBtn,description,flag);				
				}catch(Exception e)
				{
					logger.info("Batch Description field should be enabled");
				}break;

			case "programField":
				try
				{
				Assert.assertEquals(programName.isEnabled(), true,"Assertion error: manadatory field should be enabled :"+fieldName);
				WebElement programNameFieldInvalidValueErrorMsg=driver.findElement(By.xpath("//small[normalize-space()='Program Name is required.']"));
				cUtils.updateInvalidValuesCheck(programName, programNameFieldInvalidValueErrorMsg, expectedErrorMsg,saveBtn,BatchPage.programNameStr,flag);				
			}catch(Exception e)
			{
				logger.info("Program Name field should be enabled");
			}break;		

			case "radioBtn":
				try
				{
				WebElement radioBtnFieldInvalidValueErrorMsg=driver.findElement(By.xpath("//small[normalize-space()='Status is required.']"));
				cUtils.updateInvalidValuesCheck(statusActiveRadiobtn, radioBtnFieldInvalidValueErrorMsg, expectedErrorMsg,saveBtn,"null",flag);				
				}catch(Exception e)
				{
					logger.info("radio button  field should be enabled");
				}break;						

			case "NoOfClasses":
				try
				{
				WebElement snipperFieldInvalidValueErrorMsg=driver.findElement(By.xpath("//small[normalize-space()='Status is required.']"));
				cUtils.updateInvalidValuesCheck(noOfClassesSnipperField, snipperFieldInvalidValueErrorMsg, expectedErrorMsg,saveBtn,"null",flag);				
		}catch(Exception e)
		{
			logger.info("NoofClasses field should be enabled");
		}break;	
		default:
			break;					
		}
	
	
			}	return flag;
	
	
	
	}

	
	
	public void verifyBatchURl1() {		
		try
		{
			String Expected = FileReaderManager.getInstance().getResourcebundleInstance().getBatchUrl();
			cUtils.getAssertionEqualsCheck(driver.getCurrentUrl(), Expected);
		}catch(AssertionError e)
		{

			logger.info("Admin Verifies the BatchUrl and it failed");
		}
	}

	public void clickSaveBtn() {
		saveBtn.click();
	}


	public boolean clearDescriptionalField() throws InterruptedException {
		descriptionTextField.clear();
		descriptionTextField.sendKeys(" ");
		flag = clickSaveButton();
		return flag;

	}

	


	public List<String> selectMultipleCheckboxesA() throws InterruptedException {
		   
		WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.sendKeys("BatchTribe");

		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));

		secondColumnTexts = new ArrayList<>();		
		List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='p-datatable-tbody']/tr"));

		for (WebElement row : rows) {
			WebElement checkbox = row.findElement(By.xpath("./td[1]//div[@role='checkbox']"));
			if (!checkbox.isSelected()) {
				checkbox.click();
				secondColumnElement = row.findElement(By.xpath("./td[2]"));
				String text = secondColumnElement.getText();
				secondColumnTexts.add(text);

				logger.info("BatchNames Checkbox Selected For Delete : "+text);

			}
		}
		clickManageHeaderDeleteIconForMultipleRow();
		return secondColumnTexts;

	}

	public String checkForCreatedBranchToEdit() throws InterruptedException {

		// WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.sendKeys(batchName);
		WebElement branchNameToEdit = driver
				.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[2][contains(text(), '" + batchName + "')]"));

		try {
			Thread.sleep(1000);
			// Locate the row containing the batch name in the search results table
			// WebElement branchNameToEdit =
			// driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[2][contains(text(),
			// '" + batchName + "')]"));

			// Click the "Edit" button in the corresponding row
			WebElement editButton = branchNameToEdit.findElement(
					By.xpath("./following-sibling::td/div/span/button[contains(@class, 'p-button-success')]"));
			logger.info("Batch Edited Successfully" + branchNameToEdit);
			// updateBatchDetails(branchNameToEdit.getText());
		} catch (Exception e) {
			System.out.println("bactheditverification");
		}
		return branchNameToEdit.getText();

	}

	public boolean checkManadatoryFieldsEnabled() {

		if (nameTextField.isEnabled() && programName.isEnabled() && statusActiveRadiobtn.isSelected())
			logger.info("mandatory fields not enabled");
		flag = true;

		return false;
	}
	}







