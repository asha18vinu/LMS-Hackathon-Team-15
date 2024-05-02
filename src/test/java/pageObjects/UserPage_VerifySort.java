package pageObjects;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonUtils;

public class UserPage_VerifySort {

	WebDriver driver;
	private CommonUtils commonUtils;
	private DashboardPage dashboardPage;

	// Locators
	@FindBy(id = "username")
	WebElement inputUserName;
	@FindBy(id = "password")
	WebElement enterPassword;
	@FindBy(id = "login")
	WebElement loginButton;
	@FindBy(id = "user")
	WebElement userTab;
	@FindBy(xpath = "//p-sorticon[@field='userId']")
	WebElement userIdSortIcon;
	@FindBy(xpath = "//p-sorticon[@field='userFirstName']")
	WebElement userNameSortIcon;

	@FindBy(xpath = "//p-sorticon[@field='userLocation']")
	WebElement userLocationSortIcon;

	@FindBy(xpath = "//p-sorticon[@field='userPhoneNumber']]")
	WebElement userPhoneSortIcon;

	@FindBy(xpath = "//td[2]")
	WebElement userSortList_ById;

	//
	// constructor
	public UserPage_VerifySort(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.commonUtils = new CommonUtils(driver);
	}

//Login Page Methods

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

	public void SortByUserId() {

		// click on icon
		// get list of all userid
		// get text
		// apply sort in the sorted list
		// compare it with original list sorted list
		userIdSortIcon.click();

		List<WebElement> userIdList = driver.findElements(By.xpath("//tr/td[2]"));
		List<String> originalListByUserId = userIdList.stream().map(e -> e.getText()).collect(Collectors.toList());
		originalListByUserId.stream().forEach(p->System.out.println(p));
		
		List<String> sortedListByUserId = originalListByUserId.stream().sorted().collect(Collectors.toList());
		sortedListByUserId.stream().forEach(p->System.out.println(p));
		Assert.assertEquals(originalListByUserId, sortedListByUserId);
	}

}
