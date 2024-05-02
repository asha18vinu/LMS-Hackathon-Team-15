package managers;

import org.openqa.selenium.WebDriver;

import commonUtilities.Pagination;
import pageObjects.*;
import stepDefinitions.ManageProgramPage2_SD;
import tech.grasshopper.pdf.section.dashboard.Dashboard;

public class PageObjectManager {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DashboardPage dashboardPage;
	private UserPage_VerifySort userVerifySort;
	private UserPage_Edit userPage_Edit;
	private BatchPage batchPage;
	private ManageProgramPage1 mp;
	private ManageProgrampage manageProgramPage;
	private UserSortingPage userSortingPage;
	private UserPageAddUser addUserPage;
	private UserPageValidation userPage;
	private Pagination pagination;
	
	public BatchPage getBatchPage() {
		return (batchPage == null) ? batchPage = new BatchPage(driver) : batchPage;
	}

	

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
	 public ManageProgramPage1 getMp()
        {
                return (mp == null) ? mp = new ManageProgramPage1(driver) : mp;

        }
	 
	 public ManageProgrampage getManageProgramPage()
     {
             return (manageProgramPage == null) ? manageProgramPage = new ManageProgrampage(driver) : manageProgramPage;

     }
	 public UserSortingPage getUserSortingPage() {
			return (userSortingPage == null) ?
				userSortingPage = new UserSortingPage(driver):
				userSortingPage;	
			}
	 public UserPageValidation getUserpage()
		{
			return (userPage ==null) ? userPage = new UserPageValidation(driver) : userPage;
		}
		
		public UserPageAddUser getAddUserpage()
		{
			return (addUserPage ==null) ? addUserPage = new UserPageAddUser(driver) : addUserPage;
		}
		

		public Pagination getPagination() {
			return (pagination == null) ? pagination = new Pagination(driver) :
				pagination;
		}


}