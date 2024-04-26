package commonUtilities;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.FileReaderManager;

public class CommonUtils {

	WebDriver driver;
	WebDriverWait wait;

	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void getAssertionEqualsCheck(String actual, String expected) {
		assertEquals(actual, expected);
	}

	public void checkExistanceofFieldType(String fieldName, String fieldType,Map<String, WebElement> fieldNameLocators) {

		String actualFieldType = " ";

		for (Entry<String, WebElement> entry : fieldNameLocators.entrySet()) {
			String fieldname = entry.getKey();
			WebElement element = entry.getValue();

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
	}
}

//	public boolean validateHeader()
//	{
//		if(headers.get(0).getText().contains("Record") &&
//		headers.get(1).getText().contains("Doc") &&
//		headers.get(2).getText().contains("FILE") &&
//		headers.get(3).getText().contains("Upload Time") &&
//		headers.get(4).getText().contains("Report Name") &&
//		headers.get(5).getText().contains("Identified Health"))
//			return true;
//		else
//			return false;
//	}
