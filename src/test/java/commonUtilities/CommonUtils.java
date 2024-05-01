package commonUtilities;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import context.*;
import dataFilesReader.ExcelFileSetup;
import managers.DriverManager;
import managers.FileReaderManager;

public class CommonUtils {

	private DriverManager driverManager = new DriverManager();
	private WebDriver driver;
	WebDriverWait wait;
	private Actions action = new Actions(driverManager.getDriver());
	ExcelFileSetup excelReader;
	String excelPath;
	
	
	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		excelReader=FileReaderManager.getInstance().getExcelInstance();	
		excelPath=FileReaderManager.getInstance().getResourcebundleInstance().getExcelTestData();
	}

	// Explicit Wait method
	public void explicitlyWaitForElement(WebElement element, long waitTimeinSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeinSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void explicitlyWaitForElement_Visible(WebElement element, long waitTimeinSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeinSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Click and wait Method
	public void clickAndWait(WebElement element, long waitTimeinSeconds) {
		try {
			wait(waitTimeinSeconds);
			explicitlyWaitForElement(element, waitTimeinSeconds);
			element.click();
			LoggerLoad.info("Element Clicked successfully: {} " + element);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LoggerLoad.error("Element not Clicked");
		}

	}

	// Send keys method
	public void sendKeysMethod(WebElement element, String value, long waitTimeinSeconds) {
		try {
			explicitlyWaitForElement(element, waitTimeinSeconds);
			element.click();
			element.clear();
			element.sendKeys(value);

		} catch (Exception e) {
			LoggerLoad.error("Element'" + element + "'is not displayed");

		}

	}

	// Verify Text Element
	public String checkTextElement(WebElement element, long waitTimeinSeconds) {
		explicitlyWaitForElement(element, waitTimeinSeconds);

		String text = null;
		try {
			text = element.getText();
		} catch (Exception e) {
			System.out.println("Text not found for the WebElement" + element + "because of exception" + e);
		}
		return text;
	}

	public String getCurrentUrl() {
		driver.getCurrentUrl();
		// System.out.println("Current Url is: " + driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

	public String getTitle() {

		driver.getTitle();
		// System.out.println("Current Tittle is: " + driver.getTitle());
		return driver.getTitle();
	}

	public void click(WebDriver driver, WebElement element) {
		
		try {
			action.moveToElement(element).click().build().perform();
		} catch (Exception e) {
			System.out.println(e + "Element is not clickable");

		}

	}

	public boolean isDisplayed(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()) {
				flag = true;
				System.out.println("The element is Displayed" + element);

			} else {
				flag = false;
				System.out.println("The element is not Displayed" + element);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	
	public int verifyBrokenLink(String url) {

		int recode = 0;
		try {
			URL link = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
			httpURLConnection.setConnectTimeout(4000); // Set connection timeout to 4 seconds
			httpURLConnection.connect();

			if (httpURLConnection.getResponseCode() == 200) {
				recode = httpURLConnection.getResponseCode();
				System.out.println(url + " - " + httpURLConnection.getResponseMessage() + "ResponseCode::"
						+ httpURLConnection.getResponseCode());
			} else {
				System.out.println(url + " - " + httpURLConnection.getResponseMessage() + "ResponseCode::"
						+ httpURLConnection.getResponseCode() + " - " + "is a broken link");
				recode = httpURLConnection.getResponseCode();
			}
		} catch (Exception e) {
			System.out.println(url + " - " + "is a broken link");
		}

		return recode;

	}

	 public Map<String, String> getValidProgramDataFromExcel(String sheetName, Integer rowNo)throws InvalidFormatException, IOException {
                Map<String, String> dataMap = new HashMap();

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
		public boolean getAssertionEqualsCheck(String actual, String expected) {
		boolean flag=false;
		try {
			Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()),
					"Actual message does not contain expected message");
			 flag=true;
		} catch (Exception e) {
			System.out.println("Assertion Error");
			 flag=false;
		}
		
		System.out.println(flag+ "   returning value in success msg");
		return flag;
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
			try { //Checks each fieldType attribut to ocnfirm its fieldtype
				
				if (fieldName.equalsIgnoreCase(fieldname) && (element.isEnabled() || element.isDisplayed())) {
					
					if (element.getTagName().equals("input") && element.getAttribute("type").equals("text")) {
						actualFieldType = "textBox";					
					}

					else if (element.getTagName().equals("input") && element.getAttribute("type").equals("radio") && (element.isEnabled() || element.isDisplayed())) {
						actualFieldType = "radioButton";
						
					} else if (element.getAttribute("class").contains("p-dropdown-label")
							|| element.getAttribute("type").contains("text")
							|| element.getAttribute("aria-haspopup").contains("listbox")&& (element.isEnabled() || element.isDisplayed())) {
						actualFieldType = "dropdown";					
					}

					else if (element.getTagName().equals("input")
							&& element.getAttribute("type").equalsIgnoreCase("number")&& (element.isEnabled() || element.isDisplayed())&&element.getAttribute("type").equals("text")) {
						actualFieldType = "textBox";
					}
					assertEquals(actualFieldType.toLowerCase(),fieldType.toLowerCase(),
							"Field type mismatch for field: " + fieldname);
				}
			} catch (Exception e) {
				System.out.println("Field type mismatch");
			}
		}

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

	public void updateInvalidValuesCheck(WebElement field, WebElement errorMsg, String expectedErrorMsg,
			WebElement saveBtn, String nameTesxtField, boolean flag) {
		System.out.println("field :" +field);
	if(field.isDisplayed() && field.isEnabled())
		{
		field.clear();
		field.sendKeys(nameTesxtField);
		
		saveBtn.click();
		flag = validateErrorMsg(errorMsg.getText(), expectedErrorMsg);
		}
	}

}
