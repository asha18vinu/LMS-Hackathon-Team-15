package managers;

import dataFilesReader.ConfigFileSetup;
import dataFilesReader.ExcelFileSetup;
import dataFilesReader.ResourceBundleDataReader;
//using Singleton Eager initialization
public class FileReaderManager {
private static FileReaderManager fileReaderManager = new FileReaderManager();
private static ConfigFileSetup configFileSetup=new ConfigFileSetup();
private static ExcelFileSetup excelFileSetup=new ExcelFileSetup();
private static ResourceBundleDataReader dataRead=new ResourceBundleDataReader();

private FileReaderManager()
{
	
}

public static FileReaderManager getInstance()
{
	return fileReaderManager; 
}

public ConfigFileSetup getConfigInstance()
{
	return (configFileSetup==null)?new ConfigFileSetup() : configFileSetup;
}

public ExcelFileSetup getExcelInstance()
{
	return (excelFileSetup==null)?new ExcelFileSetup() : excelFileSetup;
}


public ResourceBundleDataReader getResourcebundleInstance()
{
	return (dataRead==null)?new ResourceBundleDataReader() : dataRead;
}
}


