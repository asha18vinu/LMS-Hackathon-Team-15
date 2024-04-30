package commonUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
	public static Logger LoggerLoad = LogManager.getLogger(Listeners.class);

	@Override
	public void onTestStart(ITestResult result) {
		LoggerLoad.info("inTestStart");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LoggerLoad.info("Test Success :" +result.getTestName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LoggerLoad.info("Test Failed :"+result.getTestName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LoggerLoad.info("Test Skipped" + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		LoggerLoad.info("Test Finished" + context.getName());
	}

}
