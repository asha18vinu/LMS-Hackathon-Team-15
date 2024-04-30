package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonUtilities.CommonUtils;

public class ManageProgramPage {
	public String progName;
    public String progDesc;
	public String status;
	
	//Feature: Manage Program Validation
    @FindBy(xpath="//*[@id='username']") public WebElement userName;
    @FindBy(xpath="//*[@id='password']") public WebElement password;
    @FindBy(xpath="//button[@id='login']") public WebElement login;
    @FindBy(xpath="//span[text()='Program']") public WebElement programLink;
    @FindBy(xpath="//div[contains(text(), ' Manage Program')]") public WebElement mpText;
    @FindBy(xpath="//mat-card-content[@class = 'mat-card-content']/../mat-card-title/div[2]/div[3]/button/span[2][contains(text(), 'A New Program')]") public WebElement mpAddBtn;   
    @FindBy(xpath="//input[@type='text' and @placeholder='Search...']") public WebElement SearchTextBox;
    @FindBy(xpath="//div[@class='box']/div[1]/button/span[@class='p-button-icon pi pi-trash']") public WebElement deleteBtn;
    @FindBy(xpath="//div[@class = 'p-datatable-wrapper ng-star-inserted']/table/thead/tr[1]") public WebElement mpDataTableHeader;
    @FindBy(xpath="//div[@class = 'p-datatable-wrapper ng-star-inserted']/table/tbody") public WebElement mpDataTable;
    @FindBy(xpath="//p-paginator/div/span[@class = 'p-paginator-current ng-star-inserted']") public WebElement mpShowingText;
    @FindBy(xpath="//button[@class='p-paginator-next p-paginator-element p-link p-ripple']") public WebElement nextBtn;
    
    //Feature: Add a Program
    @FindBy(xpath="//p-dialog/div/div/div/span[contains(text(),'Program Details')]") public WebElement dialogPageName;
    @FindBy(xpath="//div[@class='ng-tns-c132-3 p-dialog-content']/div/label[text()='Name']/../input[@type='text']") public WebElement NameTextBox;
    @FindBy(xpath="//div[@class='ng-tns-c132-3 p-dialog-content']/div/label[text()='Description']/../input[@type='text']") public WebElement DescTextBox;
    @FindBy(xpath="//div[@class='radio ng-star-inserted']/div/lable[text()='Status']") public WebElement Status;
    @FindBy(xpath = "//body/app-root[1]/app-program[1]/p-dialog[1]/div[1]/div[1]/div[2]/div[3]/div[2]/p-radiobutton[1]/div[1]/div[2]") public WebElement ActRadioBtn;		
    @FindBy(xpath="//div[@class='p-radiobutton p-component']/div/input[@type='radio' and @id='Inactive']") public WebElement InactiveRadioBtn; 
    @FindBy(xpath="//div[@class='p-dialog-footer ng-tns-c132-3 ng-star-inserted']/button[1]/span[text()='Cancel']") public WebElement CancelBtn;
    @FindBy(xpath="//div[@class='p-dialog-footer ng-tns-c132-3 ng-star-inserted']/button[2]/span[text()='Save']") public WebElement SaveBtn;
    @FindBy(xpath="//div[@class='p-dialog-header-icons ng-tns-c132-3']/button[contains(@class,'p-dialog-header-close')]") public WebElement closebtn;
    @FindBy(xpath="//div[contains(@class, 'p-dialog-content')]/div/label[@for='programName']/../small[contains(text(),'Program name is required.')]") public WebElement ProgNameError;
    @FindBy(xpath="//div[contains(@class, 'p-dialog-content')]/div/label[@for='programDescription']/../small[contains(text(),'Description is required.')]") public WebElement DescriptionError;
    @FindBy(xpath="//div[contains(@class, 'p-dialog-content')]/div/label[@for='programName']/../small[contains(text(),'This field should start with an alphabet, no special char and min 2 char.')]") public WebElement ProgNameSyntaxError;
    @FindBy(xpath="//div[@role='alert']") public WebElement alertPopUp;
    
    //Feature:Delete a Program
    
    @FindBy(xpath= "//div[@class='p-dialog-header ng-tns-c133-4 ng-star-inserted']/span") public WebElement alerttxt;
    @FindBy(xpath= "//div[@class='p-dialog-footer ng-tns-c133-4 ng-star-inserted']/button[2]/span[2]") public WebElement YesBtn;
    @FindBy(xpath= "//div[@class='p-dialog-footer ng-tns-c133-4 ng-star-inserted']/button[1]/span[2]") public WebElement NoBtn;
    @FindBy(xpath= "//div[@class='p-dialog-content ng-tns-c133-4']/span") public WebElement deleteValidationMsg;
    @FindBy(xpath= "//div[@class='p-datatable-footer ng-star-inserted']/div") public WebElement totalProgram;
    @FindBy(xpath= "//p-confirmdialog/div") public WebElement deleteConfirmAlert;
    
    //Feature: Edit a Program
  
    
  //fetch the matching program row
    public boolean checkForTheAddedProgram(WebDriver driver, CommonUtils cUtils, String ProgName, String Desc, String Status) {
    	boolean found = false;
    	System.out.println("Inside checkForTheAddedProgram");
    	WebElement tbody = driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']"));
    	boolean isEmpty = tbody.findElements(By.xpath("./*")).isEmpty();
    	if (isEmpty) {
    		System.out.println("return false");
    		return found;
    	}

        while (true) {
            List<WebElement> currentPageRows = mpDataTable.findElements(By.tagName("tr"));
            System.out.println("ROW COUNT =" + currentPageRows.size());
            for (int i = 1; i <= currentPageRows.size(); i++) {
                System.out.println("ROW COUNT=" + i);
                String ProgNamePath = "//div[@class='p-datatable-wrapper ng-star-inserted']/table/tbody/tr[" + i + "]/td[2]";
                String ProgDescPath = "//div[@class='p-datatable-wrapper ng-star-inserted']/table/tbody/tr[" + i + "]/td[3]";
                String StatusPath = "//div[@class='p-datatable-wrapper ng-star-inserted']/table/tbody/tr[" + i + "]/td[4]";
                System.out.println("BEFORE IF CHECK");
                try {
                	if (driver.findElement(By.xpath(ProgNamePath)).getText().contains(ProgName) &&
                        driver.findElement(By.xpath(ProgDescPath)).getText().contains(Desc) &&
                        driver.findElement(By.xpath(StatusPath)).getText().contains(Status)) {
                		System.out.println("found is true");
                		found = true;
                		return true; // Program found, exit method
                	}
                } catch (StaleElementReferenceException e) {
                    // If element reference is stale, continue to the next iteration
                    System.out.println("StaleElementReferenceException occurred, retrying...");
                    break;
                }
            }
            System.out.println("NEXT BUTTON CLICK");
            try {
                cUtils.waitForElementToBeClickable(driver, nextBtn);
                nextBtn.click();
            } catch (Exception e) {
                // If "Next" button is not found or clickable, exit the loop
                System.out.println("Next button not found or clickable.");
                break;
            }
        }
        return found;
    }
    
    public WebElement getRow(WebDriver driver, int rowno) {
    	List<WebElement> rows = mpDataTable.findElements(By.tagName("tr"));
    	int i = 1;
    	for(WebElement row: rows) {
    		if(i == rowno) {
    			return row;
    		}
    		i++;
    	}
    	return null;
    }
    
    public WebElement getCell(WebDriver driver, WebElement row, int colnum) {
    	List<WebElement> cols = row.findElements(By.tagName("td"));
    	int i = 1;
    	for(WebElement col: cols) {
    		if(i == colnum) {
    			return col;
    		}
    		i++;
    	}
    	return null;
    }
    
    public void VerifySucessfullPopUp(WebDriver driver, CommonUtils cUtils, String ExpectedMessageText) throws InvalidFormatException, IOException {
		 try {
			 WebElement successMessage = cUtils.waitForElementToBeVisible(driver, alertPopUp);
			 String actualMessageText = successMessage.getText();
			 System.out.println("Success Message: " + actualMessageText);
			 //cUtils.getAssertionEqualsCheck(actualMessageText, ExpectedMessageText);
			 Assert.assertTrue(actualMessageText.contains(ExpectedMessageText));
		 } catch (Exception e) {
			 System.out.println("Success message not found within the timeout period.");
		 }
    }
    
    
    public ManageProgramPage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
    }
}
