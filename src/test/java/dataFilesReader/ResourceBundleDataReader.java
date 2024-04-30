package dataFilesReader;

import java.util.ResourceBundle;

import browserTypeEnum.BrowserType;
import runner.TestRunner;

public class ResourceBundleDataReader {

	ResourceBundle dataRead = ResourceBundle.getBundle("configTestData/configTestData");

	public BrowserType getBrowserType() {
		//	***************These are the code used to Run from different browser**************//
		String browserName = TestRunner.BROWSER.get();		//******Browser value from testng.xml to testrunner to here. uncomment this if you want to execute from testngcb.xml
		// BrowserType
		// browser1=FileReaderManager.getInstance().getResourcebundleInstance().getBrowserType();
		// BrowserType browserType = BrowserType.valueOf((browser.toUpperCase()));
		
		
	//****************************************************************************************//
		//String browserName = dataRead.getString("browser");
		//System.out.println(browserName);
		System.out.println(browserName);
		if (browserName == null || browserName.equalsIgnoreCase("chrome"))
			return BrowserType.CHROME;

		else if (browserName.equalsIgnoreCase("firefox"))
			return BrowserType.FIREFOX;

		else if (browserName.equalsIgnoreCase("edge"))
			return BrowserType.EDGE;

		else
			throw new RuntimeException("Browser Name Key value is not matched : " + browserName);
	}

	public long getImplicitWait() {
		String implicitlyWait = dataRead.getString("implicitWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}

	public String getUrl() {
		String url = dataRead.getString("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("Url not specified in the file.");
	}

	public String getScreenshotPath() {
		String screenshotPath = dataRead.getString("screenshotPath");
		if (screenshotPath != null)
			return screenshotPath;
		else
			throw new RuntimeException("ScreenshotPath not specified in the file.");
	}

	public String getExcelTestData() {
		String excelTestData = dataRead.getString("excelTestData");
		if (excelTestData != null)
			return excelTestData;
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}

	public String getBatchUrl() 
	{
		String batchpageUrl = dataRead.getString("batchpageUrl");
		if (batchpageUrl != null)
			return batchpageUrl;
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}
	
	public String getBatchPopboxHeader() 
	{
		String batchPopUpBoxHeader = dataRead.getString("batchPopUpBoxHeader");
		if (batchPopUpBoxHeader != null)
			return batchPopUpBoxHeader;
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}

	public String getManageBatchHeader() 
	{
		String BatchHeader = dataRead.getString("BatchHeader");
		if (BatchHeader != null)
			return BatchHeader;
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}

		
	public String getUserName() 
	{
		String userName = dataRead.getString("userName");
		if (userName != null)
			return userName;
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}
	public String getPassword() 
	{
		String password = dataRead.getString("password");
		if (password != null)
			return password;
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}

}
