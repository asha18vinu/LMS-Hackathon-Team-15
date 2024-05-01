package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.*;
import tech.grasshopper.pdf.section.dashboard.Dashboard;
import commonUtilities.Pagination;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ManageProgrampage;

public class PageObjectManager {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DashboardPage dashboardPage;
	private UserPage_VerifySort userVerifySort;
	private UserPage_Edit userPage_Edit;
	private ManageProgramPage mp;
	private ManageProgrampage  manageProgramPage;
	private UserSortingPage userSortingPage;
	private Pagination pagination;

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
	 public ManageProgramPage getMp()
        {
                return (mp == null) ? mp = new ManageProgramPage(driver) : mp;

        }
	public ManageProgrampage getManageProgramPage()
	{ 
		return (manageProgramPage == null) ? 
				manageProgramPage = new ManageProgrampage(driver) : 
					manageProgramPage; 
	}

	
	public UserSortingPage getUserSortingPage() {
		return (userSortingPage == null) ?
				userSortingPage = new UserSortingPage(driver):
						userSortingPage;
		
	}
	
	public Pagination getPagination() {
		return (pagination == null) ? pagination = new Pagination(driver) :
			pagination;
	}
}
