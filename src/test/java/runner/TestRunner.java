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


	

	


	@CucumberOptions(features="src/test/resources/Features", glue={"hooks","stepDefinitions"},monochrome=true
//			plugin = {"pretty",
//				
//					"timeline:test-output-thread/"
					)

	public  class TestRunner extends AbstractTestNGCucumberTests{
//		TestContext context;
//		WebDriver driver;
//		public TestRunner(TestContext context) {
//			this.context=context;
//			driver=context.getWebDriverManager().getDriver();
//		}
//		@Override
//		@DataProvider(parallel=false)
//		public Object[][] scenarios(){
// 			
//			return super.scenarios();
//		}
//		
//		public  static ThreadLocal<String> BROWSER = new ThreadLocal<>();
//		@BeforeTest
//		@Parameters({"browser"})
//		public void browserType(@Optional("chrome") String browser) throws Throwable
//		{  
//			System.out.println("BROWSER RUNNING : "+browser);
//			TestRunner.BROWSER.set(browser);			
//			
//	    }
//		@AfterSuite
//		public void tearDown()
//		{
//			driver.quit();
//			System.out.println("Shutting down"); 
//			
//		}
}


