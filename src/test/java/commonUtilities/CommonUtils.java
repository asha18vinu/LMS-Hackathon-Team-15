package commonUtilities;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import context.*;
import managers.DriverManager;

public class CommonUtils {

	private DriverManager driverManager = new DriverManager();
	private WebDriver driver;
	private Actions action = new Actions(driverManager.getDriver());

	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		// this.action=action;
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

}
