package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonUtils;

public class UserPage_Edit {

	WebDriver driver;
	private CommonUtils commonUtils;
	private DashboardPage dashboardPage;

	// Locators
	@FindBy(id = "filterGlobal")
	WebElement searchBox;

	@FindBy(css = "button[icon='pi pi-pencil']")
	WebElement editUserIcon;
	@FindBy(id = "mat-input-2")
	WebElement inputUserFirstName;
	@FindBy(id = "mat-input-3")
	WebElement inputUserMiddleName;
	@FindBy(id = "mat-input-4")
	WebElement inputUserLastName;
	@FindBy(id = "mat-input-5")
	WebElement inputUserLocation;
	@FindBy(id = "mat-input-6")
	WebElement inputUserPhoneNo;
	@FindBy(id = "mat-input-7")
	WebElement inputUserLinkedInUrl;
	@FindBy(id = "mat-input-8")
	WebElement inputUserEmail;
	@FindBy(id = "mat-input-9")
	WebElement inputUserUnderGraduate;
	@FindBy(id = "mat-input-10")
	WebElement inputUserPostGrad;
	@FindBy(id = "mat-input-11")
	WebElement inputUserTimeZone;
	@FindBy(id = "mat-input-12")
	WebElement inputUserComments;

	@FindBy(xpath = "//span[@Class='p-dropdown-trigger-icon ng-tns-c101-44 pi pi-chevron-down']")
	WebElement selUserRoledropArrow;
	@FindBy(xpath = "//span[@Class='p-dropdown-trigger-icon ng-tns-c101-45 pi pi-chevron-down']")
	WebElement selUserRoleStatusdropArrow;
	@FindBy(xpath = "//span[@Class='p-dropdown-trigger-icon ng-tns-c101-46 pi pi-chevron-down']")
	WebElement selUserVisaStatusArrow;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement userSubmitButton;
	@FindBy(xpath = "//button[@color='warn']")
	WebElement userCancelButton;
	@FindBy(id = "pr_id_5-label")
	WebElement userPopUpText;

	//
	// constructor
	public UserPage_Edit(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.commonUtils = new CommonUtils(driver);
	}

	public void searchUserByName() {

		String searchUser = "Riya";// call search by username program here
		commonUtils.sendKeysMethod(searchBox, searchUser, 1);

	}

	public void clickOnUserEditIcon() {

		commonUtils.clickAndWait(editUserIcon, 1);

	}
	public String verifyUserPopWindow() {

		String text=commonUtils.checkTextElement(userPopUpText, 1);
		return text;

	}

}
