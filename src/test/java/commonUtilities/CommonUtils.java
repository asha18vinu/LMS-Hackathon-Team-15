package commonUtilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import managers.DriverManager;

public class CommonUtils {
		private DriverManager driverManager = new DriverManager();
		private WebDriver driver;
		Actions actions = new Actions(driverManager.getDriver());
		
		public CommonUtils(WebDriver driver) {
			this.driver = driver;
		}
	//Explicit Wait method
	public void explicitlyWaitForElement(WebElement element,long waitTimeinSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeinSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	//Click and wait Method
	public void clickAndWait(WebElement element, long waitTimeinSeconds) {
		 try {
			wait(waitTimeinSeconds);
			explicitlyWaitForElement(element, waitTimeinSeconds);
			element.click();
			LoggerLoad.info("Element Clicked successfully: {} " +element);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LoggerLoad.error("Element not Clicked");
		}
		 
	}
	
	//Element is displayed
	public boolean isPresent(WebElement element) {
		boolean flag  = false;
		try {
			if(element.isDisplayed() || element.isEnabled()) {
				flag = true;
			}
			
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			flag = false;
			LoggerLoad.error("Element'"+element+"'is not present");
		}
		return flag;
		
	}
	
	// Select from dropdown
	public void selectByVisibleText(WebElement element, String visibleText) {
		WebElement dropdown = element;
		
		try {
			Select select = new Select(dropdown);
			select.selectByVisibleText(visibleText);
			LoggerLoad.info("text'"+visibleText+"'is selected from the dropdown'"+element+"'");
		} catch (Exception e) {
			LoggerLoad.error("'"+element+"'is not present or text'"+element+"'is not selected");
		}
	}
	
	//Send keys method
	public void sendKeysMethod(WebElement element, String value, long waitTimeinSeconds) {
		try {
			explicitlyWaitForElement(element, waitTimeinSeconds);
			element.click();
			element.clear();
			element.sendKeys(value);
			
		} catch (Exception e) {
			LoggerLoad.error("Element'"+element+"'is not displayed");
			Assert.fail("Element'"+element+"'is not displayed");
		}
		
	}
	
	//Verify Text Element
	public void verifyTextElement(String comparingText,WebElement actualElement, String elementDescription, long waitTimeinSeconds) {
	explicitlyWaitForElement(actualElement, waitTimeinSeconds);
	String actualText = actualElement.getText();
	if(actualText.equals(comparingText)) {
		LoggerLoad.info(""+elementDescription+""+comparingText+""+"is verified");
	}
	else {
		LoggerLoad.error(""+elementDescription+""+comparingText+""+"is not verified");
	}
	}
	
	
}
