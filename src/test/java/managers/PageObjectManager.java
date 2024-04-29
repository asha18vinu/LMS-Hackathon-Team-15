package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.*;
import tech.grasshopper.pdf.section.dashboard.Dashboard;

public class PageObjectManager {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DashboardPage dashboardPage;
	private UserPage_VerifySort userVerifySort;
	private UserPage_Edit userPage_Edit;

	public WebDriver getDriver() {
		return driver;
	}

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}

	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}

	public DashboardPage getDashboardPage() {
		return (dashboardPage == null ? dashboardPage = new DashboardPage() : dashboardPage);
	}
	public UserPage_VerifySort getUserVerifySort() {
		return (userVerifySort == null ? userVerifySort = new UserPage_VerifySort(driver) : userVerifySort);
	}
	
	public UserPage_Edit getUserPage_Edit() {
		return (userPage_Edit == null ? userPage_Edit = new UserPage_Edit(driver) : userPage_Edit);
	}


}
