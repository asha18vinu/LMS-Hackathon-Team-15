package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonUtils;

public class LoginPage {

	WebDriver driver;
	private CommonUtils commonUtils;
	private DashboardPage dashboardPage;

	// Locators
	@FindBy(className = "images")
	WebElement logo;
	@FindBy(id = "mat-form-field-label-1")
	WebElement userLabel;
	@FindBy(xpath = "//label[@id='mat-form-field-label-1'] //span[text()=' *']")
	WebElement userSymbol;
	@FindBy(id = "username")
	WebElement inputUserName;
	@FindBy(id = "mat-form-field-label-3")
	WebElement passwordLabel;
	@FindBy(xpath = "//label[@id='mat-form-field-label-3'] //span[text()=' *'] ")
	WebElement passwordSymbol;
	@FindBy(id = "password")
	WebElement enterPassword;
	@FindBy(id = "login")
	WebElement loginButton;
	@FindBy(className = "mat-card-content")
	WebElement inputFieldAlignment;
	@FindBy(xpath = "//p[contains(text(),'Please')]")
	WebElement checkHomePageText;
	@FindBy(id = "errormessage")
	WebElement errorMessage;
	@FindBy(id = "mat-error-0")
	WebElement userErrorMessage;
	@FindBy(id = "mat-error-1")
	WebElement passwordErrorMessage;

	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.commonUtils = new CommonUtils(driver);
<<<<<<< HEAD
=======
	}

//Login Page Methods
	public String verifyHomePageUrl() {

		String currUrl = commonUtils.getCurrentUrl();
		System.out.println("Current URL is: " + currUrl);
		return currUrl;
	}

	public String loginPageDisplayMsg() {

		String userMessage = null;
		String passwordMessage = null;
		String loginMessage = null;
		String message;

		try {

			userMessage = userErrorMessage.getText();
			System.out.println("User error msg::" + userMessage);
			return userMessage;
		} catch (Exception e) {
			System.out.println("No User error msg is displayed ::" + e);
		}
		try {
			passwordMessage = passwordErrorMessage.getText();
			System.out.println("Password error msg::" + passwordMessage);
			return passwordMessage;
		} catch (Exception e) {
			System.out.println("No password error msg is displayed ::" + e);
		}
		try {
			loginMessage = errorMessage.getText();
			System.out.println("Login error Message::" + loginMessage);
			return loginMessage;
		} catch (Exception e) {
			System.out.println("No Login error msg is displayed ::" + e);

		}
		return (message = userMessage + passwordMessage + loginMessage);

	}

	public String LoginErrorDisplayMsg() {

		String displayMsg = errorMessage.getText();
		// System.out.println(displayMsg);
		return displayMsg;

	}

	public String UserErrorDisplayMsg() {

		String displayMsg = null;
		try {
			displayMsg = userErrorMessage.getText();
			return displayMsg;
		} catch (Exception e) {
			System.out.println(
					"No error message is displayed when User detail is empty because of element not found and exception::"
							+ e);
		}
		// System.out.println(displayMsg);
		return displayMsg;

	}

	public String UserPasswordErrorDisplayMsg() {

//		String displayMsg = null;
//		try {
		String userMsg = userErrorMessage.getText();
		System.out.println("*****Msg*****" + userMsg);
		String passwordMsg = passwordErrorMessage.getText();
		System.out.println("*****Msg*****" + passwordMsg);
		String displayMsg = userMsg + passwordMsg;
		System.out.println("****Error msg" + displayMsg);
		return displayMsg;
//		}
//		catch (Exception e) {
//			System.out.println("No error message is displayed when both User& passowrd is empty because of element not found and exception::"+e);
//		}
//		
		// return displayMsg;

	}

	public String PasswordErrorDisplayMsg() {

		String displayMsg = null;
		try {
			displayMsg = passwordErrorMessage.getText();
			System.out.println(displayMsg);

		} catch (Exception e) {
			System.out.println(
					"No error message is displayed when Password detail is empty because of element not found and exception::"
							+ e);

		}

		return displayMsg;

	}

	public void verifyLogin(String username, String password) {

		try {
			inputUserName.clear();
			inputUserName.sendKeys(username + Keys.ENTER);

		} catch (Exception e) {
			System.out.println("Input user field cannot be null: " + e);

		}
		try {
			enterPassword.clear();
			enterPassword.sendKeys(password + Keys.ENTER);

		} catch (Exception e) {
			System.out.println("Password field cannot be null: " + e);

		}
		// loginButton.click();

	}

	public void enterLoginDetails(String username, String password) {

		inputUserName.sendKeys(username);
		enterPassword.sendKeys(password);

		return;
	}

	public void loginThroughKeyBoard(String username, String password) {

		inputUserName.sendKeys(username + Keys.TAB);
		enterPassword.sendKeys(password + Keys.TAB);
		loginButton.sendKeys(Keys.ENTER);
		return;
	}

	public void loginThroughMouse(String username, String password) {

		commonUtils.click(driver, inputUserName);
		inputUserName.sendKeys(username);
		commonUtils.click(driver, enterPassword);
		enterPassword.sendKeys(password);
		commonUtils.click(driver, loginButton);

		return;
	}

	public void clickLoginButton() throws InterruptedException {

		commonUtils.click(driver, loginButton);
		Thread.sleep(2);
		return;
	}

	public void loginThroughPen() {

		return;
	}

	public void loginThroughWheel() {

		return;
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
	}

//Login Page Methods
	public String verifyHomePageUrl() {

		String currUrl = commonUtils.getCurrentUrl();
		System.out.println("Current URL is: " + currUrl);
		return currUrl;
	}

	public String loginPageDisplayMsg() {

		String userMessage = null;
		String passwordMessage = null;
		String loginMessage = null;
		String message;

		try {

			userMessage = userErrorMessage.getText();
			System.out.println("User error msg::" + userMessage);
			return userMessage;
		} catch (Exception e) {
			System.out.println("No User error msg is displayed ::" + e);
		}
		try {
			passwordMessage = passwordErrorMessage.getText();
			System.out.println("Password error msg::" + passwordMessage);
			return passwordMessage;
		} catch (Exception e) {
			System.out.println("No password error msg is displayed ::" + e);
		}
		try {
			loginMessage = errorMessage.getText();
			System.out.println("Login error Message::" + loginMessage);
			return loginMessage;
		} catch (Exception e) {
			System.out.println("No Login error msg is displayed ::" + e);

		}
		return (message = userMessage + passwordMessage + loginMessage);

	}

	public String LoginErrorDisplayMsg() {

		String displayMsg = errorMessage.getText();
		// System.out.println(displayMsg);
		return displayMsg;

	}

	public String UserErrorDisplayMsg() {

		String displayMsg = null;
		try {
			displayMsg = userErrorMessage.getText();
			return displayMsg;
		} catch (Exception e) {
			System.out.println(
					"No error message is displayed when User detail is empty because of element not found and exception::"
							+ e);
		}
		// System.out.println(displayMsg);
		return displayMsg;

	}

	public String UserPasswordErrorDisplayMsg() {

//		String displayMsg = null;
//		try {
		String userMsg = userErrorMessage.getText();
		System.out.println("*****Msg*****" + userMsg);
		String passwordMsg = passwordErrorMessage.getText();
		System.out.println("*****Msg*****" + passwordMsg);
		String displayMsg = userMsg + passwordMsg;
		System.out.println("****Error msg" + displayMsg);
		return displayMsg;
//		}
//		catch (Exception e) {
//			System.out.println("No error message is displayed when both User& passowrd is empty because of element not found and exception::"+e);
//		}
//		
		// return displayMsg;

	}

	public String PasswordErrorDisplayMsg() {

		String displayMsg = null;
		try {
			displayMsg = passwordErrorMessage.getText();
			System.out.println(displayMsg);

		} catch (Exception e) {
			System.out.println(
					"No error message is displayed when Password detail is empty because of element not found and exception::"
							+ e);

		}

		return displayMsg;

	}

	public void verifyLogin(String username, String password) {

		try {
			inputUserName.clear();
			inputUserName.sendKeys(username + Keys.ENTER);

		} catch (Exception e) {
			System.out.println("Input user field cannot be null: " + e);

		}
		try {
			enterPassword.clear();
			enterPassword.sendKeys(password + Keys.ENTER);

		} catch (Exception e) {
			System.out.println("Password field cannot be null: " + e);

		}
		// loginButton.click();

	}

	public void enterLoginDetails(String username, String password) {

		inputUserName.sendKeys(username);
		enterPassword.sendKeys(password);

		return;
	}

	public void loginThroughKeyBoard(String username, String password) {

		inputUserName.sendKeys(username + Keys.TAB);
		enterPassword.sendKeys(password + Keys.TAB);
		loginButton.sendKeys(Keys.ENTER);
		return;
	}

	public void loginThroughMouse(String username, String password) {

		commonUtils.click(driver, inputUserName);
		inputUserName.sendKeys(username);
		commonUtils.click(driver, enterPassword);
		enterPassword.sendKeys(password);
		commonUtils.click(driver, loginButton);

		return;
	}

	public void clickLoginButton() throws InterruptedException {

		commonUtils.click(driver, loginButton);
		Thread.sleep(2);
		return;
	}

	public void loginThroughPen() {

		return;
	}

	public void loginThroughWheel() {

		return;
	}

}