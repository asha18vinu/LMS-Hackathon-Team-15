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

public class testcode {

	public static void main(String[] args) throws TesseractException {

		WebDriver driver;

		WebDriverManager.chromedriver().clearDriverCache().setup();
		driver = new ChromeDriver();
		driver.get("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement userLabel = driver.findElement(By.id("mat-form-field-label-1"));
		WebElement inputUser = driver.findElement(By.id("username"));
		WebElement inputpassword = driver.findElement(By.id("password"));
		
		WebElement userSymbol = driver
				.findElement(By.xpath("//label[@id='mat-form-field-label-1'] //span[text()=' *']"));
		WebElement loginbt = driver.findElement(By.id("login"));
		WebElement logoImage = driver.findElement(By.className("images"));
		WebElement passwordLabel=driver.findElement(By.id("mat-form-field-label-3"));
		WebElement inputFieldAlign=driver.findElement(By.xpath("//input[@formcontrolname='userLoginEmailId']"));
		WebElement passwordSymbol=driver.findElement(By.xpath("//label[@id='mat-form-field-label-3'] //span[text()=' *']"));
		WebElement inputI=driver.findElement(By.className("mat-card-content"));
		//String pageContent=driver.findElement(By.tagName("body")).getText();
	//	System.out.println("All text::"+pageContent);
		
//		
		
		inputUser.sendKeys("");
		inputpassword.sendKeys("");
		loginbt.click();
		
//		WebElement loginMsg=driver.findElement(By.id("errormessage"));
		WebElement userMsg=driver.findElement(By.id("mat-error-0"));
		WebElement PswMsg=driver.findElement(By.id("mat-error-1"));
		
		String Umsg=userMsg.getText();
		System.out.println("***********user msg*****"+Umsg);
		String Pmsg	=PswMsg.getText();
		System.out.println("***********pass msg*****"+Pmsg);
		
//		String Lmsg=loginMsg.getText();
//		System.out.println("***********login msg*****"+Lmsg);
		
		
		
		
	
//		String in=	inputI.getCssValue("text-align");
//				System.out.println("input form position"+in);
//		String bin=	inputI.getCssValue("justify-content")	;
//		System.out.println(in+"********"+bin+ "location"+inputI.getLocation());
//	String	m=inputFieldAlign.getCssValue("align-items");
//	String	te=inputFieldAlign.getCssValue("display");
//	
//	System.out.println(m+"margin :"+ te+" text-align");
//	String textloc	=driver.findElement(with(By.tagName("label")).above(passwordSymbol)).getText().replace("*", "");;
//	String textloc1	=driver.findElement(with(By.tagName("label")).below(userSymbol)).getText().replace("*", "");;
//		System.out.println("*********Relative loc****"+textloc);
//		System.out.println("*********Relative loc****"+textloc1);
//		
//		userLabel.getCssValue("color");
//		
//		String userSymbolText=userSymbol.getText();
//		String passSymbolText=passwordSymbol.getText();
//		
//		System.out.println("user symbol text: " +userSymbolText);
//		System.out.println("password symbol text: " +passSymbolText);
//		String UserText=userLabel.getText();
//		System.out.println(UserText+"****UserText:  ");
//		
//		String PassText=passwordLabel.getText();
//		System.out.println(PassText+"****PassText:  ");
//		
//		int inputtag=driver.findElements(By.tagName("input")).size();
//		
//		System.out.println("No of input tag: "+inputtag);
//		System.out.println("userlabel color:" + userLabel.getCssValue("color"));
//		Color Grey = Color.fromString("white");
//		logoImage.getAriaRole();
//		logoImage.getLocation();
//		 String	logoText=logoImage.getText();
//			System.out.println("Logo Text"+logoText);
//		logoImage.getCssValue("background-image");
//		logoImage.getCssValue("background-Position");
//		System.out.println(logoImage.getCssValue("float"));
//		System.out.println(logoImage.getCssValue("background-Position"));
//		System.out.println("*******Logo align:" + logoImage.getCssValue("float"));
//		inputUser.getCssValue("text-align");
//		loginbt.getCssValue("text-align");
//		System.out.println("*******Useralign:" + inputUser.getCssValue("text-align"));
//		System.out.println("*******Password align:" + inputpassword.getCssValue("text-align"));
//		System.out.println("*******align:" + loginbt.getCssValue("text-align"));
//		String col = logoImage.getCssValue("color");
//		String colHex=Color.fromString(col).asHex();
//		System.out.println(colHex);
//		//String hexColor = rgbaToHex(col);
//		Color colname=Color.fromString(col);
//		Color colname1=Color.fromString("grey");
//		System.out.println(colname+"  which color");
//		System.out.println(colname+"  is it grey color");
//		
//		//assert col.equals(Grey);
//		// String pcol=Color.fromString(col);
//		// System.out.println(pcol);
//		int xco = logoImage.getLocation().x;
//		int yco = logoImage.getLocation().y;
//		System.out.println(xco + "x coordinate" + yco + " ycoordinate");
//		// logoImage.getCssValue(")
//		boolean disp = logoImage.isDisplayed();
//		System.out.println(disp + "is displayed");
//		System.out.println(disp + "is displayed");
//		System.out.println(logoImage.getAriaRole() + "arialRole");
//		System.out.println(logoImage.getLocation() + "location");
//		
//		String url="https://lms-frontend-api-hackathon-apr-3262373d.herokuapp.com/login";
//		driver.get(url);
//		driver.get("https://lms-frontend-api-hackathon-apr-326235f3973d.herokuapp.com/login");
//		WebElement logoImage1 = driver.findElement(By.className("images"));
//		
//		// System.out.println("Response code is: "+responsecode);
//		 File screenShots= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		 ITesseract tesseract = new Tesseract();
//		 String text = tesseract.doOCR(screenShots);
//		 System.out.println(text+"Logo text");
//		
//		try {
//			URL link = new URL(url);
//			HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
//			httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
//			httpURLConnection.connect();
//
//
//			if (httpURLConnection.getResponseCode() == 200) {
//				int recode = httpURLConnection.getResponseCode();
//				System.out.println(url + " - " + httpURLConnection.getResponseMessage()+"ResponseCode::"+httpURLConnection.getResponseCode());
//			} else {
//				System.out.println(url + " - " + httpURLConnection.getResponseMessage() + "ResponseCode::"+httpURLConnection.getResponseCode()+" - " + "is a broken link");
//			}
//		} catch (Exception e) {
//			System.out.println(url + " - " + "is a broken link");
//		}
	}

}
