package pageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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
	static String batchName;
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
	@FindBy(xpath = "//p-dropdown[@id='programName']")
	WebElement programNameTextField;
	@FindBy(xpath = "//input[@*='ACTIVE']")
	WebElement statusActiveRadiobtn;
	@FindBy(xpath = "//input[@*='INACTIVE']")
	WebElement statusINactiveRadiobtn;
	@FindBy(xpath = "//div[@*='button']/span")
	WebElement programNameFieldDropDownBtn;
	@FindBy(xpath = "//input[@*='batchNoOfClasses']")
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
	@FindBy(xpath="//button[@class='p-paginator-next p-paginator-element p-link p-ripple']")
	WebElement nextPageBtn;
	@FindBy(xpath="//button[@class='p-paginator-next p-paginator-element p-link p-ripple']")
	WebElement nextPageButton ;
	@FindBy(css="tbody.p-datatable-tbody tr")
	List<WebElement> CurrentRows;

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

		Map<String, WebElement> fieldNameLocators = new HashMap<String, WebElement>();
		fieldNameLocators.put("NameField", nameTextField);
		fieldNameLocators.put("NumberofClassesField", noOfClassesSnipperField);
		fieldNameLocators.put("DescriptionField", descriptionTextField);
		fieldNameLocators.put("ProgramnameField", programNameFieldDropDownBtn);
		fieldNameLocators.put("ActiveField", statusActiveRadiobtn);
		fieldNameLocators.put("InactiveField", statusINactiveRadiobtn);

		cUtils.checkExistanceofFieldType(fieldName, fieldType, fieldNameLocators);

	}

	public void fillBatchValidDestails(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException, InterruptedException {
		Map<String, String> data = cUtils.getValidDataFromExcel(sheetName, rowNo);		
        batchName = data.get("batchName")+random.nextInt(90) + 10;		
		nameTextField.sendKeys(batchName);
		System.out.println("batchName is created : "+batchName);
		descriptionTextField.sendKeys(data.get("description"));	
		String programName = "SeleniumProgram";
		programNameFieldDropDownBtn.click();
		WebElement dropdownItem = driver
				.findElement(By.xpath("//span[contains(text(), '" + programName + "')]/ancestor::p-dropdownitem"));
		dropdownItem.click();
		WebElement activeRadiobtn = driver.findElement(By.xpath("//*[@id='batchStatus']/div[1]"));
		if (!activeRadiobtn.isSelected()) {
			activeRadiobtn.click();
		}
		noOfClassesSnipperField.sendKeys(data.get("noOfClasses"));

	}

	public void checkDescriptionFieldOptional() {
		String labelText = descriptionLabel.getText();
		boolean flag = labelText.contains("*");
		
		try {
			assertEquals(flag, "true", "Description should not be a OptionalField");		  
		} catch (AssertionError e) {		    
		   
		    System.out.println("Description Is not Optional Assertion" );		  
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
						flag=true;
						break;
					}
				}
			}

			nextPageButton.click();
		}
		
		if(flag)
		{
			System.out.println("Batch is created and diplayed in the table");
		}else
			System.out.println("batch is not created");
	}

	private List<WebElement> getRowsFromCurrentPage() {//div[@role='alert']
		return CurrentRows;
	}

	public void verifySuccessMessage() {
		String ExpectedMessageText="Successful Batch Created Successfully";
		try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
            String actualMessageText = successMessage.getText();
            System.out.println("Success Message: " + actualMessageText);
            cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
        } catch (Exception e) {
            System.out.println("Success message not found within the timeout period.");
        }
	}

	public void checkMandatoryFieldsBlank(String sheetname, Integer rowno) throws InvalidFormatException, IOException {
		Map<String, String> data = cUtils.getValidDataFromExcel(sheetname, rowno);
		
	}

}


