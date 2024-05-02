package runner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import context.TestContext;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import managers.DriverManager;

<<<<<<< HEAD
@CucumberOptions(features = "src\\test\\resources\\Features\\D_BatchModule\\",
		//"src\\test\\resources\\Features\\D_BatchModule\\02_AddNewBatch.feature",
		//"src\\test\\resources\\Features\\D_BatchModule\\03_EditBatch.feature"},
		 glue = { "hooks","stepDefinitions" }, monochrome = false ,tags="@delete"
//			plugin = {"pretty",
//				
//					"timeline:test-output-thread/"
)

 
public  class TestRunner  extends AbstractTestNGCucumberTests{
		
		@Override
	    @DataProvider(parallel = false)
	    public Object[][] scenarios() {
					
			return super.scenarios();
	    }
	
		
		public  static ThreadLocal<String> BROWSER = new ThreadLocal<>();
		@BeforeTest
		@Parameters({"browser"})
		public void browserType(@Optional("chrome") String browser) throws Throwable
		{  
			System.out.println("BROWSER RUNNING : "+browser);
			TestRunner.BROWSER.set(browser);			
			
	    }
=======
@CucumberOptions(features = "src/test/resources/Features/E_UserModule/", glue = { "hooks",
		"stepDefinitions" }, monochrome = true, plugin = { "pretty",
			//	"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"html:target/Cucumber.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
			//	"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
			//	"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
			//	"timeline:test-output-thread/" 
				})

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {

		return super.scenarios();
	}

	public static ThreadLocal<String> BROWSER = new ThreadLocal<>();

	@BeforeTest
	@Parameters({ "browser" })
	public void browserType(@Optional("chrome") String browser) throws Throwable {
		System.out.println("BROWSER RUNNING : " + browser);
		TestRunner.BROWSER.set(browser);

	}
>>>>>>> 827f3cef0c4a9a6baa5e6cfbc2f265fbce3f2a45
//		@AfterSuite
//		public void tearDown()
//		{
//			driver.quit();
//			System.out.println("Shutting down"); 
//			
//		}

}
