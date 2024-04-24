package commonUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import context.TestContext;
import hooks.Hooks;

public class Listeners implements ITestListener {
	TestContext contexta;
	WebDriver driver;
	Hooks hook;

//	public Listeners(TestContext contexta) {
//		this.contexta = contexta;		
//	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("On test Sttart : " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
	//	contexta.getWebDriverManager().getDriver();
	}

	@Override
	public void onFinish(ITestContext context) {
		//contexta.getWebDriverManager().closeBrowser();;
	}

	public static void debug(String message) {

	}

}
