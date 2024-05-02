package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtilities.CommonUtils;
import commonUtilities.LoggerLoad;

public class UserPage_Edit {

	WebDriver driver;
	private CommonUtils commonUtils;
	private DashboardPage dashboardPage;
	WebDriverWait wait;

	// Locators Login Dashboard page
	@FindBy(id = "username")
	WebElement inputUserName;
	@FindBy(id = "password")
	WebElement enterPassword;
	@FindBy(id = "login")
	WebElement loginButton;
	@FindBy(id = "user")
	WebElement userTab;

	// Locators User page
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

	@FindBy(xpath = "//p-dropdown[@id='roleId']")
	WebElement userRoleId;
	@FindBy(xpath = "//p-dropdown[@id='userRoleStatus']")
	WebElement userRoleStatusEle;
	@FindBy(xpath = "//p-dropdown[@id='userVisaStatus']")
	WebElement userVisaStatusEle;
//	@FindBy(xpath = "//span[@Class='p-dropdown-trigger-icon ng-tns-c101-44 pi pi-chevron-down']")
//	WebElement selUserRoledropArrow;
//	@FindBy(xpath = "//span[@Class='p-dropdown-trigger-icon ng-tns-c101-45 pi pi-chevron-down']")
//	WebElement selUserRoleStatusdropArrow;
//	@FindBy(xpath = "//span[@Class='p-dropdown-trigger-icon ng-tns-c101-46 pi pi-chevron-down']")
//	WebElement selUserVisaStatusArrow;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement userSubmitButton;
	@FindBy(xpath = "//button[@color='warn']")
	WebElement userCancelButton;
	@FindBy(id = "pr_id_5-label")
	WebElement userPopUpText;
	@FindBy(xpath = "//div[text()=' Manage User']")
	WebElement manageUserHeadingText;

	//
	// constructor
	public UserPage_Edit(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.commonUtils = new CommonUtils(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Login Page Methods

	public String verifyDashboardPage() {

		driver.get("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/");
		inputUserName.sendKeys("sdetorganizers@gmail.com");
		enterPassword.sendKeys("UIHackathon@02");
		loginButton.click();
		userTab.click();
		String currUrl = commonUtils.getCurrentUrl();
		System.out.println("Current URL is: " + currUrl);

		return currUrl;
	}

	public String searchUserByName() {

		String searchUser = "Riya";// call search by username program here
		commonUtils.sendKeysMethod(searchBox, searchUser, 1);

		WebElement userNameToEdit = driver.findElement(By.xpath("//td[normalize-space()='RiyaY Sen']"));
		// td[normalize-space()='RiyaY
		// Paul']/following-sibling::td/div/span/button[contains(@class,
		// 'p-button-success')]
		try {
			WebElement editButton = userNameToEdit.findElement(
					By.xpath("./following-sibling::td/div/span/button[contains(@class, 'p-button-success')]"));
			LoggerLoad.info("User Edit button clicked successfully" + userNameToEdit);
		} catch (Exception e) {
			System.out.println("User edit button verification" + e);
		}
		return userNameToEdit.getText();
		// commonUtils.sendKeysMethod(searchBox, searchUser, 1);

	}

	public void clickOnUserEditIcon() {

		// commonUtils.clickAndWait(editUserIcon, 1);
		editUserIcon.click();

	}

	public void addUserDetails(String userFirstName, String userLastName, String userMiddleName, String userLocation,
			String userPhoneNo, String userLinkedInUrl, String userRole, String userRoleStatus, String userVisaStatus,
			String userEmail, String userUnderGraduate, String userPostGraduate, String userTimeZone,
			String userComments) throws InterruptedException {

		commonUtils.sendKeysMethod(inputUserFirstName, userFirstName, 1);
		commonUtils.sendKeysMethod(inputUserLastName, userLastName, 1);
		commonUtils.sendKeysMethod(inputUserMiddleName, userMiddleName, 1);
		commonUtils.sendKeysMethod(inputUserLocation, userLocation, 1);
		commonUtils.sendKeysMethod(inputUserPhoneNo, userPhoneNo, 1);
		commonUtils.sendKeysMethod(inputUserLinkedInUrl, userLinkedInUrl, 1);

		userRoleId.click();
		List<WebElement> Roleoptions = driver.findElements(By.xpath("//li[@role='option']"));
		System.out.println(Roleoptions.size());

		for (WebElement option : Roleoptions) {

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();
			System.out.println(optionText);

			if (optionText.equalsIgnoreCase(userRole)) {
				System.out.println("userRole : " + userRole);
				System.out.println("userRole from page" + optionText);
				option.click();
				break;
			}
		}

		userRoleStatusEle.click();
		WebElement optionRoleStatus = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[normalize-space()='" + userRoleStatus + "']")));
		System.out.println(optionRoleStatus.getText());
		optionRoleStatus.click();

		userVisaStatusEle.click();
		WebElement optionVisaStatus = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[normalize-space()='" + userVisaStatus + "']")));
		System.out.println(optionVisaStatus.getText());

		optionVisaStatus.click();

		Thread.sleep(1);

		commonUtils.sendKeysMethod(inputUserEmail, userEmail, 1);
		commonUtils.sendKeysMethod(inputUserUnderGraduate, userUnderGraduate, 1);
		commonUtils.sendKeysMethod(inputUserTimeZone, userTimeZone, 1);
		commonUtils.sendKeysMethod(inputUserPostGrad, userPostGraduate, 1);
		commonUtils.sendKeysMethod(inputUserComments, userComments, 1);
		// Thread.sleep(1);
		userSubmitButton.click();
		System.out.println("User data updated");
		return;

	}

	public void updateMandatoryUserDetails(String userFirstName, String userLastName, String userLocation,
			String userPhoneNo, String userRole, String userRoleStatus, String userVisaStatus)
			throws InterruptedException {

		commonUtils.sendKeysMethod(inputUserFirstName, userFirstName, 1);
		commonUtils.sendKeysMethod(inputUserLastName, userLastName, 1);
		commonUtils.sendKeysMethod(inputUserLocation, userLocation, 1);
		commonUtils.sendKeysMethod(inputUserPhoneNo, userPhoneNo, 1);
		userRoleId.click();
		List<WebElement> Roleoptions = driver.findElements(By.xpath("//li[@role='option']"));
		System.out.println(Roleoptions.size());

		for (WebElement option : Roleoptions) {

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();
			System.out.println(optionText);

			if (optionText.equalsIgnoreCase(userRole)) {
				System.out.println("userRole : " + userRole);
				System.out.println("userRole from page" + optionText);
				option.click();
				break;
			}
		}
		userRoleStatusEle.click();
		WebElement optionRoleStatus = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[normalize-space()='" + userRoleStatus + "']")));
		System.out.println(optionRoleStatus.getText());
		optionRoleStatus.click();

		userVisaStatusEle.click();
		WebElement optionVisaStatus = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[normalize-space()='" + userVisaStatus + "']")));
		System.out.println(optionVisaStatus.getText());

		optionVisaStatus.click();

		// submit the user details
		userSubmitButton.click();
		System.out.println("User Mandatory data is updated");

		return;

	}

	public String userUpdateDisplayMsg() {

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		String text = element.getText();
		System.out.println(element.getText());
		return text;

	}

	public String verifyUserDetailsPopUpWindow() {

		String text = commonUtils.checkTextElement(userPopUpText, 1);
		return text;

	}

	public void updateOptionalUserDetails(String userMiddleName, String userEmail, String userUnderGraduate,
			String userPostGraduate, String userTimeZone, String userComments, String userLinkedInUrl) {

		commonUtils.sendKeysMethod(inputUserMiddleName, userMiddleName, 1);
		commonUtils.sendKeysMethod(inputUserLinkedInUrl, userLinkedInUrl, 1);
		commonUtils.sendKeysMethod(inputUserEmail, userEmail, 1);
		commonUtils.sendKeysMethod(inputUserUnderGraduate, userUnderGraduate, 1);
		commonUtils.sendKeysMethod(inputUserTimeZone, userTimeZone, 1);
		commonUtils.sendKeysMethod(inputUserPostGrad, userPostGraduate, 1);
		commonUtils.sendKeysMethod(inputUserComments, userComments, 1);

		userSubmitButton.click();
		System.out.println("User optionaldata updated");

	}

	public void updateInvalidNumericUserDetails(String userMiddleName, String userUnderGraduate,
			String userPostGraduate, String userTimeZone, String userComments) {

		commonUtils.sendKeysMethod(inputUserMiddleName, userMiddleName, 1);
		commonUtils.sendKeysMethod(inputUserUnderGraduate, userUnderGraduate, 1);
		commonUtils.sendKeysMethod(inputUserTimeZone, userTimeZone, 1);
		commonUtils.sendKeysMethod(inputUserPostGrad, userPostGraduate, 1);
		commonUtils.sendKeysMethod(inputUserComments, userComments, 1);

		userSubmitButton.click();
		System.out.println("User entered invalid Numeric value in text field");

	}

	public void verifyCancelButton() {

		commonUtils.click(driver, userCancelButton);

		return;

	}

	public String onManagePage() {

		commonUtils.click(driver, userCancelButton);
		String text = manageUserHeadingText.getText();

		return text;

	}

}