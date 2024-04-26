package pageObjects;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import commonUtilities.CommonUtils;
import managers.FileReaderManager;

public class BatchPage {

	WebDriver driver;
	CommonUtils cUtils;
	WebDriverWait wait;
	String actualFieldType;

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
	@FindBy(xpath = "//*[@id='programName']/div/input")
	WebElement programNameFieldDropDownBtn;
	@FindBy(xpath = "//input[@*='batchNoOfClasses']")
	WebElement noOfClassesSnipperField;
	@FindBy(linkText = "Save")
	WebElement saveBtn;
	@FindBy(linkText = "Cancel")
	WebElement cancelBtn;
	@FindBy(css = "span.p-dialog-header-close-icon.ng-tns-c132-50.pi.pi-times")
	WebElement clearCrossBtn;

	public BatchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		cUtils = new CommonUtils(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

}
