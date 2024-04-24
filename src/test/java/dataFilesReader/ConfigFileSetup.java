package dataFilesReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import browserTypeEnum.BrowserType;

public class ConfigFileSetup {

	private static Properties properties;
	private final String propertyFilePath = "src/test/resources/configTestData/configTestData.properties";

	public ConfigFileSetup() 
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try 
			{
				properties.load(reader);
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public BrowserType getBrowserType() 
	{
		String browserName = properties.getProperty("browser");		
		if (browserName == null || browserName.equals("chrome"))
			return BrowserType.CHROME;
		
		else if (browserName.equalsIgnoreCase("firefox"))
			return BrowserType.FIREFOX;
		
		else if (browserName.equals("edge"))
			return BrowserType.EDGE;
		
		else
			throw new RuntimeException(
				"Browser Name Key value is not matched : " + browserName);
	}

	public long getImplicitWait() 
	{
		String implicitlyWait = properties.getProperty("implicitWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}

	public String getUrl() 
	{
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("Url not specified in the file.");
	}
	
	public String getScreenshotPath() {
		String screenshotPath=properties.getProperty("screenshotPath");
		if(screenshotPath!=null)
			return screenshotPath;
		else 
			throw new RuntimeException("ScreenshotPath not specified in the file.");		
	}
	
	public String getExcelTestData() 
	{
		String excelTestData = properties.getProperty("excelTestData");
		if (excelTestData != null)
			return excelTestData;
		else
			throw new RuntimeException("implicit Wait not specified in the file.");
	}
}
