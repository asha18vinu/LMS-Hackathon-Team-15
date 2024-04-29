package commonUtilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import org.testng.Assert;

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
		excelReader = FileReaderManager.getInstance().getExcelInstance();
		excelPath = FileReaderManager.getInstance().getResourcebundleInstance().getExcelTestData();
	}

	public void getAssertionEqualsCheck(String actual, String expected) {
		try {
			Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()),
					"Actual message does not contain expected message");
		} catch (Exception e) {
			System.out.println("Assertion Error");
		}
	}

	public boolean validateErrorMsg(String errorMsg, String expectedErrorMsg) {
		boolean flag = false;
		try {
			// assertEquals(true, errorMsg.contains(expectedErrorMsg));
			assertEquals(errorMsg, expectedErrorMsg);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public void checkExistanceofFieldType(String fieldName, String fieldType,
			Map<String, WebElement> fieldNameLocators) {

		String actualFieldType = " ";

		for (Entry<String, WebElement> entry : fieldNameLocators.entrySet()) {
			String fieldname = entry.getKey();
			WebElement element = entry.getValue();
			try {
				if (fieldName.equalsIgnoreCase(fieldname) && (element.isEnabled() || element.isDisplayed())) {
					System.out.println(fieldName + "  " + fieldType);

					if (element.getTagName().equals("input") && element.getAttribute("type").equals("text")) {
						actualFieldType = "textBox";
						System.out.println(fieldname + "  " + fieldType + "  " + actualFieldType);
					}

					else if (element.getTagName().equals("input") && element.getAttribute("type").equals("radio")) {
						actualFieldType = "radioButton";

						System.out.println(fieldname + "  " + fieldType + "  " + actualFieldType);
					} else if (element.getAttribute("class").contains("p-dropdown-label")
							|| element.getAttribute("type").contains("text")
							|| element.getAttribute("aria-haspopup").contains("listbox")) {
						actualFieldType = "dropdown";
						System.out.println(fieldname + "  " + fieldType + "  " + actualFieldType);
					}

					else if (element.getTagName().equals("input")
							&& element.getAttribute("type").equalsIgnoreCase("number")) {
						actualFieldType = "textBox";

						System.out.println(fieldname + "  " + fieldType + "  " + actualFieldType);
					}

					assertEquals(fieldType.toLowerCase(), actualFieldType.toLowerCase(),
							"Field type mismatch for field: " + fieldname);
				}
			} catch (Exception e) {
				System.out.println("Field type mismatch");
			}
		}

	}

	public void enterDetails(String batchName, String description, String noOfClasses) {

	}

	public List<Map<String, String>> getValidDataFromExcel(String sheetName, Integer rowNo)
			throws InvalidFormatException, IOException {

		excelReader = FileReaderManager.getInstance().getExcelInstance();
		List<Map<String, String>> list = excelReader.getData(excelPath, sheetName);

		return list;
	}

	public List<String> getTableHeaderFromTable(List<WebElement> tableHeader) {
		List<String> colHeader = new ArrayList<String>();
		for (int i = 0; i < tableHeader.size(); i++) {
			String data = tableHeader.get(i).getText().trim();
			colHeader.add(data);
		}

		return colHeader;
	}

}
