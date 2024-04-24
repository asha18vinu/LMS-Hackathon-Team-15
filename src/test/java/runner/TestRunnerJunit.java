package runner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;




@RunWith(Cucumber.class)
@CucumberOptions(publish=true,features ={"src/test/resources/Features/"}, 
glue = {"stepDefinitions","hooks"}, 
monochrome = false, 
plugin = {"pretty",
		//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//		"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
//		"html:target/Cucumber.html",
		"json:target/cucumber.json",
		"junit:target/JUNITReports/reports.xml",
		"rerun:target/failedTestCaseReRun.txt"
		})



public class TestRunnerJunit {
	
	
	


}