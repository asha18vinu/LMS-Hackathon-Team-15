package testDataBody;

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
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class testcode2 {

	public static void main(String[] args) throws TesseractException {

		WebDriver driver;

		WebDriverManager.chromedriver().clearDriverCache().setup();
		driver = new ChromeDriver();
		driver.get("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@*='userLoginEmailId']")).sendKeys("sdetorganizers@gmail.com");
		driver.findElement(By.xpath("//input[@*='Password']")).sendKeys("UIHackathon@02");
		driver.findElement(By.xpath("//button[@*='submit']")).click();

		WebElement batchlink = driver.findElement(By.xpath("//span[text()='Batch']"));
		WebElement addbatch = driver.findElement(By.xpath("/span[text()='A New Batch']"));
		WebElement batchNameLabel=driver.findElement(By.xpath("//label[@for='batchName']"));
		WebElement batchDescriptionLabel=driver.findElement(By.xpath("//label[@for='batchDescription']"));
		WebElement inputBatch=	driver.findElement(with(By.tagName("label")).below(batchNameLabel));
		WebElement inputbatchDesc=driver.findElement(By.id("batchDescription"));
		WebElement selProgram=driver.findElement(By.cssSelector(".p-dropdown-trigger-icon"));
		
		inputBatch.sendKeys("Team15Batch");
		inputbatchDesc.sendKeys("new java batch");
		
		
		
		batchlink.click();
		
	
	
	}
	

}
