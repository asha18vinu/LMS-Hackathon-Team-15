package hooks;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.qameta.allure.Allure;
import managers.DriverManager;
import managers.FileReaderManager;
import stepDefinitions.LoginPage_SD;

public class Hooks {
	private static final Logger logger = LogManager.getLogger(Hooks.class);
	TestContext testContext;
	WebDriver driver;

	public Hooks(TestContext context) {
		testContext = context;
			}

	@Before
	public void setUp() {	
		   driver = testContext.getWebDriverManager().getDriver();	
		   driver.get(FileReaderManager.getInstance().getResourcebundleInstance().getUrl());	
		   System.out.println("Driver object : "+driver);
	}

	@After
	public void getScreenShot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			String scenarioName = scenario.getName();
			Status scenarioStatus = scenario.getStatus();
			scenario.log("Scenario Name: " + scenarioName);
			scenario.log("Scenario Status: " + scenarioStatus);
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String screenshotFileName = "Screenshot_" + timestamp + ".png";
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(FileReaderManager.getInstance().getResourcebundleInstance().getScreenshotPath() +screenshotFileName));
		    Allure.attachment("FailedScreenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
			
		}	
		

	}
	
	
}
