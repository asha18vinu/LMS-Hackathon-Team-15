package managers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import browserManager.ChromeManager;
import browserManager.EdgeManager;
import browserTypeEnum.BrowserType;

public final class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public DriverManager() {

	}

	public WebDriver getDriver() {

		
		BrowserType browserType=FileReaderManager.getInstance().getResourcebundleInstance().getBrowserType();
	
		// factory design pattern implementation
		if (driver.get() == null) {
			switch (browserType) {

			case CHROME:
				driver.set(ChromeManager.getInstance().getDriver());
				break;

			case EDGE:
				driver.set(EdgeManager.getInstance().getDriver());
				break;

			default:
				throw new IllegalArgumentException("Unsupported webdriver:" + browserType);
			}

		}
		driver.get().manage().deleteAllCookies();
		driver.get().manage().timeouts().implicitlyWait(
				Duration.ofSeconds(FileReaderManager.getInstance().getResourcebundleInstance().getImplicitWait()));
		driver.get().manage().timeouts().pageLoadTimeout(
				Duration.ofSeconds(FileReaderManager.getInstance().getResourcebundleInstance().getImplicitWait()));
		driver.get().manage().window().maximize();

		return driver.get();
	}

	public void closeBrowser() {
		System.out.println("I.m closing the driver");
		driver.get().quit();
		driver.remove();
	}
}
