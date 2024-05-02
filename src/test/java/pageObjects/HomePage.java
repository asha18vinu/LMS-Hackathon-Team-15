package pageObjects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import commonUtilities.CommonUtils;
import managers.FileReaderManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class HomePage {

	WebDriver driver;
	private CommonUtils commonUtils;

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
	@FindBy(tagName = "body")
	WebElement checkHomePageText;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.commonUtils = new CommonUtils(driver);
	}

	public void goToHomePage() {

		driver.get(FileReaderManager.getInstance().getResourcebundleInstance().getUrl());

		return;
	}

	public String getHomePageUrl() {

		String currUrl = commonUtils.getCurrentUrl();
		System.out.println("Current URL is: " + currUrl);
		return currUrl;
	}

	public String verifyHomePageUrl(String url) {

		driver.get(url);
		String currUrl = commonUtils.getCurrentUrl();
		System.out.println("Current URL is: " + currUrl);
		return currUrl;
	}

	public String getHomePageTitle() {

		String title = commonUtils.getTitle();
		System.out.println("Title is : " + title);
		return title;
	}

	public String verifyInputfieldAlignment() {

		String inputfieldAlignment = inputFieldAlignment.getCssValue("text-align");
		return inputfieldAlignment;
	}

	public String verifyUserInputColor() {

		String userInputColor = inputUserName.getCssValue("color");
		System.out.println("Input color::" + userInputColor);
		return userInputColor;
	}

	public String verifyPasswordInputColor() {

		String passwordInputColor = enterPassword.getCssValue("color");
		System.out.println("password color::" + passwordInputColor);
		return passwordInputColor;
	}

	public String verifyHomePageSpelling() {

		String text = checkHomePageText.getText().toString();

		return text;
	}

	public int verifyHomePageBrokenLinks() {

		String url = commonUtils.getCurrentUrl();

		int responseCode = commonUtils.verifyBrokenLink(url);

		return responseCode;

	}

	public File verifyLogoAlignment() throws IOException {

		String align = logo.getCssValue("text-align");
		String logofloat = logo.getCssValue("float");
		String loc = logo.getLocation().toString();
		String margin = logo.getCssValue("margin");
		System.out.println("Align: " + align + "Location: " + loc + "Margin: " + margin + "LogoFloat:" + logofloat);
		// add screenshot code
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotFileName = "Screenshot_VerifyLogoAlignment_" + timestamp + ".png";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Destfile = new File(FileReaderManager.getInstance().getResourcebundleInstance().getScreenshotPath()
				+ screenshotFileName);
		FileUtils.copyFile(screenshotFile, Destfile);

		return screenshotFile;
	}

	public boolean loginButtonIsPresent() {

		boolean loginButtonPresent = commonUtils.isDisplayed(loginButton);
		return loginButtonPresent;
	}

	public String verifyApplicationName() throws TesseractException {

		File screenShots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		ITesseract tesseract = new Tesseract();
		String text = tesseract.doOCR(screenShots);

		return text;

	}

	public String verifyCompanyName() throws TesseractException {

		File screenShots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		ITesseract tesseract = new Tesseract();
		String text = tesseract.doOCR(screenShots);

		return text;

	}

	public boolean verifySignInContent() {

		boolean signInContentDisplayed = commonUtils.isDisplayed(checkHomePageText);

		return signInContentDisplayed;
	}

	public String VerifyFirstTextField() {

		String text = userLabel.getText();
		System.out.println("User text::" + text);
		return text;

	}

	public String VerifySecondTextField() {

		String text = passwordLabel.getText();
		System.out.println("Password test" + text);
		return text;

	}

	public int verifyAllInputTextField() {

		List<WebElement> textFieldList = driver.findElements(By.tagName("input"));
		int totalTextField = textFieldList.size();
		System.out.println("Total Input Text Field: " + textFieldList.size());
		return totalTextField;
	}

	public String VerifySymbol_FirstTextField() {

		String firsttextinfo = userLabel.getText();

		return firsttextinfo;
	}

	public String Verify_LoginButton_Alignment() {

		String loginButtonAlignment = loginButton.getCssValue("text-align");
		return loginButtonAlignment;
	}

	public String VerifySymbol_SecondTextField() {

		String secondtextinfo = passwordLabel.getText();
		System.out.println(secondtextinfo);
		return secondtextinfo;

	}

}
