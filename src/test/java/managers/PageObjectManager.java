package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ManageProgramPage;



public class PageObjectManager {
	WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private ManageProgramPage mp;
	
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
	
	public ManageProgramPage getMp()
	{
		return (mp == null) ? mp = new ManageProgramPage(driver) : mp;
		
	}
}

