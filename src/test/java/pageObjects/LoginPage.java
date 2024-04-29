package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.CommonUtils;
import context.TestContext;

public class LoginPage {

	private WebDriver driver;
	TestContext testContext;
	
	public LoginPage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver,this);
		System.out.println("****This is the loginpage: "+driver);
	}
	@FindBy (id = "username") 
	WebElement userName;
	
	@FindBy (id = "password")
	WebElement passWord;
	
	@FindBy (xpath = "//button[@*='submit']")
	WebElement submit;
	
	public void login() {
		userName.sendKeys("sdetorganizers@gmail.com");
		passWord.sendKeys("UIHackathon@02");
		submit.click();
	}
//	
//	public void sendUsername() {
//		System.out.println("This is page driver"+driver);
//		//testContext.getPageObjectManager().getCommonUtils().sendKeysMethod(userName, "sdetorganizers@gmail.com", 10);
//	//commonUtils.sendKeysMethod(userName, "sdetorganizers@gmail.com", 10);
//		userName.sendKeys("sdetorganizers@gmail.com");
//		//testContext.getCommonUtils().sendKeysMethod(userName, "sdetorganizers@gmail.com", 10);
//	}
//	public void sendPassword() {
//		//testContext.getPageObjectManager().getCommonUtils().sendKeysMethod(passWord, "UIHackathon@02 ", 10);
//		passWord.sendKeys("UIHackathon@02");
//		//commonUtils.sendKeysMethod(passWord, "UIHackathon@02 ", 10);
//		//testContext.getCommonUtils().sendKeysMethod(passWord, "UIHackathon@02", 10);
//	}
//	
//	public void clickSubmit() {
//		//testContext.getPageObjectManager().getCommonUtils().clickAndWait(submit, 10);
//		submit.click();
//		//commonUtils.sendKeysMethod(passWord, "UIHackathon@02 ", 10);
//		//testContext.getCommonUtils().clickAndWait(submit, 10);
//	}
}
