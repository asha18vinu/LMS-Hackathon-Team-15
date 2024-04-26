package hooks;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.qameta.allure.Allure;
import managers.DriverManager;
import managers.FileReaderManager;
import stepDefinitions.LoginPage_SD;

public class Hooks {
	private static final Logger logger = LogManager.getLogger(Hooks.class);
	static TestContext testContext;
	static WebDriver driver;

	public Hooks(TestContext context) {
		testContext = context;		
		driver = testContext.getWebDriverManager().getDriver();
		}

	@Before
	public  void setUp() {	
		   // driver = testContext.getWebDriverManager().getDriver();	
			driver.get(FileReaderManager.getInstance().getResourcebundleInstance().getUrl());			
			driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("sdetorganizers@gmail.com");
			driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("UIHackathon@02");
			driver.findElement(By.xpath("//button[@*='submit']")).click();
	}

	@After
	public  void getScreenShot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			String scenarioName = scenario.getName();
			Status scenarioStatus = scenario.getStatus();
			scenario.log("Scenario Name: " + scenarioName);
			scenario.log("Scenario Status: " + scenarioStatus);
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(FileReaderManager.getInstance().getResourcebundleInstance().getScreenshotPath() + new Date() + ".jpeg"));
		    Allure.attachment("FailedScreenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));			
		}	
	//	String scenarioName = scenario.getName();
		//driver = testContext.getWebDriverManager().getDriver();	
		//System.out.println("Im closing the driver from hooks : " +scenarioName);
		//testContext.getWebDriverManager().closeBrowser();;
		

	}
	
	
}
