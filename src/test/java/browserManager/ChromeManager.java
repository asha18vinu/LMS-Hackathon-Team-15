package browserManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ChromeManager implements BrowserManager {

	private static volatile ChromeManager instance;

	private ChromeManager() {

	}

	public static  ChromeManager getInstance() {
		if (instance == null) {
            synchronized (ChromeManager.class) {
                if (instance == null) {
                    instance = new ChromeManager();
                }
            }
        }
        return instance;
	}

	@Override
	public WebDriver getDriver() {

		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		// options.addArguments("--incognito");
		return WebDriverManager.chromedriver().create();

	}
}
