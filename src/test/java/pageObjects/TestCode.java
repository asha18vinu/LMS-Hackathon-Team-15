package pageObjects;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCode {

	public static void main(String[] args) throws TesseractException, InterruptedException {

		WebDriver driver;

		WebDriverManager.chromedriver().clearDriverCache().setup();
		driver = new ChromeDriver();
		driver.get("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("sdetorganizers@gmail.com");
		driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("UIHackathon@02");
		driver.findElement(By.xpath("//button[@*='submit']")).click();
		WebElement Userlink = driver.findElement(By.id("user"));

		Userlink.click();
		WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		searchBox.sendKeys("RiyaY");
		WebElement editUserIcon = driver.findElement(By.cssSelector("button[icon='pi pi-pencil']"));
		editUserIcon.click();
		// WebElement
		// selUserRoledropArrow=driver.findElement(By.xpath("//span[@Class='p-dropdown-trigger-icon
		// ng-tns-c101-44 pi pi-chevron-down']"));
		// selUserRoledropArrow.click();
		WebElement userRoleId = driver.findElement(By.xpath("//p-dropdown[@id='roleId']/div"));

		userRoleId.click();
		List<WebElement> Roleoptions = driver.findElements(By.xpath("//li[@role='option']"));
		System.out.println(Roleoptions.size());

		for (WebElement option : Roleoptions) {
			WebDriverWait waitId = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = waitId.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();
			System.out.println(optionText);
			if (optionText.equals("R02")) {
				System.out.println("role id : ");
				option.click();
				break;
			}

		}
		
		WebElement userRoleStatusEle = driver.findElement(By.xpath("//p-dropdown[@id='userRoleStatus']"));

		userRoleStatusEle.click();
		List<WebElement> RoleStatusOptions = driver.findElements(By.xpath("//li[@role='option']"));
		System.out.println(RoleStatusOptions.size());
		
		for (WebElement option : RoleStatusOptions) {
			WebDriverWait waitId = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = waitId.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();
			System.out.println(optionText);
			
			if (optionText.equalsIgnoreCase("Active")){
				System.out.println("userRole : " );
				option.click();
				break;
			}
		}
		
		WebElement userVisaStatusEle = driver.findElement(By.xpath("//p-dropdown[@id='userVisaStatus']"));
		userVisaStatusEle.click();
		List<WebElement> visaStatusOptions = driver.findElements(By.xpath("//li[@role='option']"));
		System.out.println(visaStatusOptions.size());
		
		for (WebElement option : visaStatusOptions) {
			WebDriverWait waitId = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = waitId.until(ExpectedConditions.elementToBeClickable(option));
			String optionText = element.getText();
			System.out.println(optionText);
			
			if (optionText.equalsIgnoreCase("H4")){
				System.out.println("userRole : " );
				option.click();
				break;
			}
		}
		
		WebElement inputUserEmail=driver.findElement(By.id("mat-input-8"));
		
	//	inputUserEmail.sendKeys("Riya@123.com");
		System.out.println("email added");
		
		
		WebElement userSubmitButton=driver.findElement(By.xpath("//button[@type='submit']"));
		userSubmitButton.click();
		WebDriverWait waitId = new WebDriverWait(driver, Duration.ofSeconds(10));
	//	WebElement EmailError=	driver.findElement(By.xpath("//div[@role='alert']"));
		WebElement element = waitId.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
	
	//EmailError.getText();
	System.out.println(element.getText());
		System.out.println("submitted");
		
		
	//	WebDriverWait waitId = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element1 = waitId.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		element.getText();
		System.out.println(element1.getText());
	//	WebElement userCancelButton=driver.findElement(By.xpath("//button[@color='warn']"));
		


		// span[text()='R02']

	}
}
