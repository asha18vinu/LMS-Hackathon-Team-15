package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.BatchPage;

import pageObjects.LoginPage;



public class PageObjectManager {
	WebDriver driver;
	private LoginPage loginPage;
	//private HomePage homePage;
	private BatchPage batchPage;
	
	public PageObjectManager(WebDriver driver) 
	{
		this.driver = driver;
	}
		

	public LoginPage getLoginPage()
	{ 
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage; 
	}
	
	public BatchPage getBatchPage()
	{ 
		return (batchPage == null) ? batchPage = new BatchPage(driver) : batchPage; 
	}
}

