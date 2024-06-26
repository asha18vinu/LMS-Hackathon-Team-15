package runner;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src\\test\\resources\\Features\\D_BatchModule\\",
	
		 glue = { "hooks","stepDefinitions" }, monochrome = false 
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
//		@AfterSuite
//		public void tearDown()
//		{
//			driver.quit();
//			System.out.println("Shutting down"); 
//			
//		}
}



