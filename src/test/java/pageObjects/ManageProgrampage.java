package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonUtilities.LoggerLoad;

public class ManageProgrampage {
	WebDriver driver;
	WebDriverWait wait;

	public ManageProgrampage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//tr[1]//td[1]/p-tablecheckbox//div[@class = 'p-checkbox p-component']")
	WebElement checkBox;

	@FindBy(xpath = "//mat-card-title//button[@icon = 'pi pi-trash']")
	WebElement deleteButton;

	@FindBy(xpath = "//span[@class = 'p-button-icon p-button-icon-left pi pi-check']")
	WebElement yesButton;

	@FindBy(xpath = "//tr//td[2]")
	WebElement programNameTab;

	@FindBy(id = "filterGlobal")
	WebElement searchButton;

	@FindBy(xpath = "//span[@class = 'p-paginator-current ng-star-inserted']")
	WebElement footerPage;

	@FindBy(xpath = "//div[@class = 'p-toast-detail ng-tns-c90-22']")
	WebElement successMessage;

	@FindBy(xpath = "//span[@class = 'p-button-icon p-button-icon-left pi pi-times']")
	WebElement noButton;

	@FindBy(xpath = "//tr//th[1]")
	WebElement selectMultipleCheckBox;

	@FindBy(xpath = "//button[@class = 'ng-tns-c133-17 p-confirm-dialog-accept p-ripple p-button p-component ng-star-inserted']")
	WebElement multipleDeleteYesButton;

	@FindBy(xpath = "//button[@id = 'batch']")
	WebElement Batch_Button;

	@FindBy(xpath = "//button[@id = 'program']/span[1]")
	WebElement Program_Button;

	@FindBy(xpath = "//div[@class = 'signin-content']//mat-card//mat-card-title/div[1]")
	WebElement Header;

	@FindBy(xpath = "//button[@id = 'user']")
	WebElement User_Button;

	@FindBy(xpath = "//button[@id = 'logout']")
	WebElement Logout_Button;

	@FindBy(xpath = "//div[@class = 'signin-content']")
	WebElement Login_Page;

	@FindBy(xpath = "//div[@class = 'p-datatable-wrapper ng-star-inserted']")
	WebElement programWebTable;

	@FindBy(xpath = "//tr/th[2]")
	WebElement programNameColumn;

	@FindBy(xpath = "//tr//th//p-sorticon[@field = 'programName']/i")
	WebElement programNameSortIcon;

	@FindBy(xpath = "//tr//th[3]")
	WebElement programDescSortIcon;

	@FindBy(xpath = "//tr//th[4]")
	WebElement programStatusSortIcon;

	@FindBy(xpath = "//tr//th[3]")
	WebElement programDescriptioncolumn;

	public void clickCheckbox() {
		checkBox.click();
	}

	public void verifyDeleteIcon() {
		if (deleteButton != null) {
			deleteButton.isEnabled();
			LoggerLoad.info("Delete button is enabled");
		} else {
			Assert.fail("Delete button is not enabled");
		}
	}

	public WebElement HeaderDeletButton() {
		return deleteButton;
	}

	public WebElement yesButton() {
		return yesButton;

	}

	public WebElement noButton() {
		return noButton;

	}

	public WebElement checkBox() {
		return checkBox;
	}

	public WebElement selectMultipleCheckBox() {
		return selectMultipleCheckBox;

	}

	public WebElement multipleDeleteYesButton() {
		return multipleDeleteYesButton;

	}

	public String getProgramName() {
		String programName = programNameTab.getText().toString();
		return programName;
	}

	public void deleteFunction(String name, WebElement element1, WebElement element2, WebElement element3)
			throws InterruptedException {

		element1.click();
		element2.click();
		element3.click();
//			String message = successMessage.getText();
//			Assert.assertEquals(message, "Programs Deleted");
		searchButton.click();
		searchButton.sendKeys(name);
		Thread.sleep(2000);
		String exp = footerPage.getText().toString();

		Assert.assertEquals(exp, "Showing 1 to 5 of 32 entries", "Program deleted");
		LoggerLoad.info("Program deleted successfully");
	}

	public void clickSingleChkbox() throws InterruptedException {
		WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.sendKeys("SeleniumTribe");
		LoggerLoad.info("Record deleting");

//			    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));

		WebElement checkbox = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]//div[@role='checkbox']"));
		if (!checkbox.isSelected() && checkbox.isDisplayed()) {
			checkbox.click();
		}

		LoggerLoad.info("checkbox selected for the rows");
	}

	public void deleteFunctionWithNoOption(String name, WebElement element1, WebElement element2, WebElement element3)
			throws InterruptedException {
		element1.click();
		element2.click();
		element3.click();
//		String message = successMessage.getText();
//		Assert.assertEquals(message, "Programs Deleted");
		searchButton.click();
		searchButton.sendKeys(name);
		Thread.sleep(2000);
		String exp = footerPage.getText().toString();

		Assert.assertEquals(exp, "Showing 1 to 5 of 32 entries", "Program not deleted");
		LoggerLoad.info("Program not deleted");
	}

	public void clickBatchButton() {
		// testContext.getCommonUtils().clickAndWait(Batch_Button, 10);
		if (Batch_Button != null) {

			Batch_Button.click();
		} else {
			LoggerLoad.error("Batch element not found");
		}

	}

	public void clickProgramButton() {

		if (Program_Button != null) {

			Program_Button.click();
		} else {
			LoggerLoad.error("Program element not found");
		}

	}

	public void verifyManageProgramPageHeader() {

		if (Header != null) {
			Header.isDisplayed();
			String headerText = Header.getText().toString();
			Assert.assertEquals(headerText, "Manage Program");
			System.out.println("Admin is in the '" + headerText + "' page");
			LoggerLoad.info("Admin is on the Manage Program Page");
		} else {
			LoggerLoad.error("Header not displayed");

		}
	}

	public void verifyManageBatchPageHeader() {

		if (Header != null) {
			Header.isDisplayed();
			String headerText = Header.getText().toString();
			Assert.assertEquals(headerText, "Manage Batch");
			System.out.println("Admin is in the '" + headerText + "' page");
			LoggerLoad.info("Admin is on the Manage Batch Page");
		} else {
			LoggerLoad.error("Header not displayed");

		}
	}

	public void verifyManageUserPageHeader() {

		if (Header != null) {
			Header.isDisplayed();
			String headerText = Header.getText().toString();
			Assert.assertEquals(headerText, "Manage User");
			System.out.println("Admin is in the '" + headerText + "' page");
			LoggerLoad.info("Admin is on the Manage User Page");
		} else {
			LoggerLoad.error("Header not displayed");

		}
	}

	public void clickUserButton() {

		if (User_Button != null) {

			User_Button.click();
		} else {
			LoggerLoad.error("User Button not found");
		}

	}

	public void clickLogoutButton() {

		if (Logout_Button != null) {

			Logout_Button.click();
		} else {
			LoggerLoad.error("User Button not found");
		}

	}

	public boolean assertPage() {
		String expected = "https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login";
		String actual = driver.getCurrentUrl().toString();
		if (expected.equalsIgnoreCase(actual)) {
			LoggerLoad.info("Redirected to the login page after clicking logout");
			return true;
		} else {
			LoggerLoad.info("Not redirected to the home page");
			return false;
		}
	}
//	public String actualUrl() {
//		
//		return null;
//		
//	}

	public void pogramWebTableDisplayed() {
		if (programWebTable.isDisplayed()) {
			LoggerLoad.info("Web Table is dislayed");
		} else {
			LoggerLoad.error("Webtable not found");
		}

	}

	public List<WebElement> getSortingProgramName() {
		List<WebElement> programNameList = driver.findElements(By.xpath("//tr//td[2]"));
		return programNameList;
	}

	public List<WebElement> getSortingProgramDesc() {
		List<WebElement> programDescList = driver.findElements(By.xpath("//tr//td[3]"));
		return programDescList;
	}

	public List<WebElement> getSortingProgramStatus() {
		List<WebElement> programDescList = driver.findElements(By.xpath("//tr//td[4]"));
		return programDescList;
	}

	public void sortAscendingOrder(List<WebElement> list) {
		try {
			// Capture all webelements into list

			// Capture text of all webelements into new(original) list
			List<String> originalList = list.stream().map(s -> s.getText()).collect(Collectors.toList());
			System.out.println("*****Application Sorting********" + originalList);

			// Sort the original list --> sorted list
			List<String> sortedList = originalList.stream().sorted(String.CASE_INSENSITIVE_ORDER)
					.collect(Collectors.toList());

			System.out.println("*****Java Stream Sorting********" + sortedList);

			Assert.assertTrue(originalList.equals(sortedList));

			List<String> programDesc = list.stream().filter(s -> s.getText().contains("Team22"))
					.map(s -> getPogramDescription(s)).collect(Collectors.toList());
			programDesc.forEach(a -> System.out.println(a));

			LoggerLoad.info("Column Sorted in ascending order");

			// list.stream().filter(s-> s.getText().contains("Automation
			// ")).map(s->getPogramDescription().collect(Collectors.toList());

		} catch (Exception e) {
			LoggerLoad.error("Column is not sorted");
		}

	}

	public void clickSortIcon(WebElement element) {
		element.click();
	}

	public WebElement getprogramNameSortIcon() {
		return programNameSortIcon;

	}

	public WebElement getprogramDescSortIcon() {

		return programDescSortIcon;

	}

	public WebElement getprogramStatusSortIcon() {

		return programStatusSortIcon;

	}

	public String getPogramDescription(WebElement s) {
		String progDesc = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return progDesc;
	}

	public String getPogramStatus(WebElement s) {
		String progStatus = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return progStatus;
	}

	public void sortDescendingOrder(List<WebElement> list) {
		try {

			// Capture all webelements into list

			// Capture text of all webelements into new(original) list
			List<String> originalList = list.stream().map(s -> s.getText()).collect(Collectors.toList());
			System.out.println("*****Application Sorting********" + originalList);

			// Sort the original list --> sorted list
			List<String> sortedList = originalList.stream().sorted(String.CASE_INSENSITIVE_ORDER.reversed())
					.collect(Collectors.toList());
			System.out.println("*****Java Stream Sorting********" + sortedList);

			Assert.assertTrue(originalList.equals(sortedList));

//			List<String> programStatus= programDescList.stream().filter(s->s.getText().contains("A")).map(s->getPogramStatus(s)).collect(Collectors.toList());
//			programStatus.forEach(a->System.out.println(a));

			LoggerLoad.info("Column Sorted in Descending order");

			// programNameList.stream().filter(s->
			// s.getText().contains("abcd")).map(s->getPogramDescription().collect(Collectors.toList()));

		} catch (Exception e) {
			LoggerLoad.error("Column is not sorted");
		}

	}

	public void sortAscendingOrderInteger(List<WebElement> list) {
		try {
			// Capture all webelements into list

			// Capture text of all webelements into new(original) list
			List<String> originalList = list.stream().map(s -> s.getText()).collect(Collectors.toList());
			System.out.println("*****Application Sorting********" + originalList);
			List<Integer> numList = new ArrayList<>();

			for (String str : originalList) {
				int num = Integer.parseInt(str);
				numList.add(num);
			}

			// Sort the original list --> sorted list
			List<Integer> sortedList = originalList.stream().map(Integer::valueOf).sorted()
					.collect(Collectors.toList());
			System.out.println("*****Java Stream Sorting********" + sortedList);

			Assert.assertTrue(numList.equals(sortedList));

			List<String> programDesc = list.stream().filter(s -> s.getText().contains("Team22"))
					.map(s -> getPogramDescription(s)).collect(Collectors.toList());
			programDesc.forEach(a -> System.out.println(a));

			LoggerLoad.info("Column Sorted in ascending order");

			// list.stream().filter(s-> s.getText().contains("Automation
			// ")).map(s->getPogramDescription().collect(Collectors.toList());

		} catch (Exception e) {
			LoggerLoad.error("Column is not sorted");
		}

	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8
