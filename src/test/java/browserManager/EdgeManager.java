package browserManager;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class EdgeManager implements BrowserManager {
	private static volatile EdgeManager instance;

	private EdgeManager() {

	}

	public static  EdgeManager getInstance() {
		if (instance == null) {
            synchronized (EdgeManager.class) {
                if (instance == null) {
                    instance = new EdgeManager();
                }
            }
        }
        return instance;
	}

	@Override
	public WebDriver getDriver() {
		return WebDriverManager.edgedriver().create();

	}
}
