package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
		 plugin={"pretty","rerun:target1/failedTestCaseReRun.txt"}, 
				monochrome = true, 
				glue = {"stepDefinitions"}, 
				features= {"@target1/failedTestCaseReRun.txt"						
				} 
		        )

public class TestRunnerReRun extends AbstractTestNGCucumberTests{
	
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
				
		return super.scenarios();
    } 


}
