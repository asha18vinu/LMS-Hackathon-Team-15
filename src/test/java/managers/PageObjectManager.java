package managers;

import org.openqa.selenium.WebDriver;

import commonUtilities.CommonUtils;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ManageProgramPage;
import pageObjects.MultipleDeleteProgram;
import pageObjects.NavigationFromManageProgramPage;
import pageObjects.UserSortingPage;
import pageObjects.ValidateSortingProgramTable;



public class PageObjectManager {
	WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private ManageProgramPage  manageProgramPage;
	private UserSortingPage userSortingPage;
	
	
	public PageObjectManager(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public HomePage getHomePage()
	{
		return (homePage == null) ? homePage = new HomePage(driver) : homePage; 
	}

	public LoginPage getLoginPage()
	{ 
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage; 
	}
	
	public ManageProgramPage getManageProgramPage()
	{ 
		return (manageProgramPage == null) ? 
				manageProgramPage = new ManageProgramPage(driver) : 
					manageProgramPage; 
	}

	
	public UserSortingPage getUserSortingPage() {
		return (userSortingPage == null) ?
				userSortingPage = new UserSortingPage(driver):
						userSortingPage;
		
	}
	
//	public CommonUtils getCommonUtils() {
//		return (commonUtils == null) ? commonUtils = new CommonUtils(driver) : commonUtils;
//	}
}

