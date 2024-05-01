package context;

import org.testng.annotations.Parameters;

import commonUtilities.CommonUtils;
import managers.DriverManager;
import managers.PageObjectManager;

public class TestContext {
	private DriverManager webDriverManager;
	private PageObjectManager pageObjectManager;
	private CommonUtils commonUtils;
	private BatchPage batchPage;
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private DashboardPage dashboardPage;
	private UserPage_VerifySort userVerifySort;
	private UserPage_Edit userPage_Edit;
	private ManageProgramPage mp;
	

	public TestContext() {
		webDriverManager = new DriverManager();
		pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
		commonUtils = new CommonUtils(webDriverManager.getDriver());
	}

	public DriverManager getWebDriverManager() {
		return webDriverManager;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}

	public CommonUtils getCommonUtils() {

		return commonUtils;
	}

	public BatchPage getBatchPage()
	{ 
		return (batchPage == null) ? batchPage = new BatchPage(driver) : batchPage; 
	}
}
