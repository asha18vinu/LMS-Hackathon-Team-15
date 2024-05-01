package context;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import commonUtilities.CommonUtils;
import managers.DriverManager;
import managers.PageObjectManager;

public class TestContext {
	private DriverManager webDriverManager;
	private PageObjectManager pageObjectManager;
	private CommonUtils commonUtils;
	private WebDriver driver;
	
	

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

	
}
