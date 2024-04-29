package commonUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
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

	public Map<String, String> getValidProgramDataFromExcel(String sheetName, Integer rowNo)throws InvalidFormatException, IOException {
		Map<String, String> dataMap = new HashMap<>();

		excelReader = FileReaderManager.getInstance().getExcelInstance();
		System.out.println("excelPath=" + excelPath);
		List<Map<String, String>> list = excelReader.getData(excelPath, sheetName);
		Map<String, String> rowData = list.get(rowNo);

		String Name = rowData.get("ProgramName");
		String description = rowData.get("ProgramDescription");
		String Status = rowData.get("Status");
		String EditName = rowData.get("EditProgramName");
		String EditDescription = rowData.get("EditProgramDescription");

		dataMap.put("ProgramName", Name);
		dataMap.put("ProgramDescription", description);
		dataMap.put("Status", Status);
		dataMap.put("EditProgramName", EditName);
		dataMap.put("EditProgramDescription", EditDescription);

		return dataMap;
	}
	
	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement message = wait.until(ExpectedConditions.visibilityOf(element));
		return message;
	}
	
	public void waitForElementToBeInVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public String getActiveElementAttribute(WebDriver driver) {
		WebElement activeElement = driver.switchTo().activeElement();
		String message = activeElement.getAttribute("validationMessage");
		System.out.println("Actual message appeared on the screen is: " + message);
		return message;
	}

	public void actionsClick(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		actions.click().perform();
	}
	
	public void actionsSendKeys(WebDriver driver,WebElement element, String input ) {
		Actions actions = new Actions(driver);
		actions.sendKeys(element, input).perform();
	}
}
