package commonUtilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataFilesReader.ExcelFileSetup;
import managers.FileReaderManager;

public class CommonUtils {

	WebDriver driver;
	WebDriverWait wait;
	ExcelFileSetup excelReader;
	String excelPath;
	
	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		excelReader=FileReaderManager.getInstance().getExcelInstance();	
		excelPath=FileReaderManager.getInstance().getResourcebundleInstance().getExcelTestData(); 
	}

	public void getAssertionEqualsCheck(String actual, String expected) {
		try {
			assertEquals(actual, expected);
		} catch (Exception e) {
			System.out.println("Assertion Error");
		}
	}

	public void checkExistanceofFieldType(String fieldName, String fieldType,Map<String, WebElement> fieldNameLocators) {

		String actualFieldType = " ";

		for (Entry<String, WebElement> entry : fieldNameLocators.entrySet()) {
			String fieldname = entry.getKey();
			WebElement element = entry.getValue();
			try 
			{
			if (fieldName.equalsIgnoreCase(fieldname) && (element.isEnabled() || element.isDisplayed())) {
				System.out.println(fieldName+ "  "+element.isDisplayed());
				if (element.getTagName().equals("input") && element.getAttribute("type").equals("text"))
					actualFieldType = "TextBox";

				else if (element.getTagName().equals("input")
						&& element.getAttribute("type").equals("radio"))
					actualFieldType = "radioButton";

				else if (element.getTagName().equals("input") && element.getAttribute("type").equals("number"))
					actualFieldType = "SpinnerTextBox";
				System.out.println(fieldname + "  " + fieldType + "  " + actualFieldType);
				
				assertEquals(fieldType.toLowerCase(), actualFieldType.toLowerCase(),"Field type mismatch for field: " + fieldname);
			    }
			}
			catch(Exception e)
			{
			System.out.println("Field name and type mismatch");
			}
			}
			
		   
	}

	public void enterDetails(String batchName, String description, String noOfClasses) {
		
	}

	public Map<String, String> getValidDataFromExcel(String sheetName, Integer rowNo)throws InvalidFormatException, IOException {
		Map<String, String> dataMap = new HashMap<>();

		excelReader = FileReaderManager.getInstance().getExcelInstance();
		List<Map<String, String>> list = excelReader.getData(excelPath, sheetName);
		Map<String, String> rowData = list.get(rowNo);

		String batchName = rowData.get("BatchName");
		String description = rowData.get("Description");
		String noOfClasses = rowData.get("NoOfClasses");

		dataMap.put("batchName", batchName);
		dataMap.put("description", description);
		dataMap.put("noOfClasses", noOfClasses);

		return dataMap;
	}
}


