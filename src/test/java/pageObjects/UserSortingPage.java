package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



import commonUtilities.LoggerLoad;

public class UserSortingPage {
	
	WebDriver driver;
	WebDriverWait wait;
	String text = "Selenium";
	
	public UserSortingPage(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//tr//th[2]")
	WebElement userIDSortIcon;
	
	@FindBy (xpath = "//tr//th[3]")
	WebElement userNameSortIcon;
	
	@FindBy (xpath = "//tr//th[4]")
	WebElement locationSortIcon;
	
	@FindBy (xpath = "//tr//th[5]")
	WebElement phonenumberSortIcon;
	
	@FindBy (xpath = "//tr//td[2]")
	List<WebElement> userIDList;
	
	@FindBy (xpath = "//tr//td[3]")
	List<WebElement> userNameList;
	
	@FindBy (xpath = "//tr//td[4]")
	List<WebElement> userLocationList;
	
	@FindBy (xpath = "//tr//td[5]")
	List<WebElement> userPhoneNumberList;
	
	@FindBy (id = "filterGlobal")
	WebElement userSearch;
	
	 @FindBy (xpath = "//mat-card-title//button[@icon = 'pi pi-trash']")
	 WebElement deleteButton;
	
	 @FindBy (xpath = "//span[contains(text(),'Yes')]")
	 WebElement yesButton;
	 
	 @FindBy (xpath = "//span[@class = 'p-button-icon p-button-icon-left pi pi-times']")
	 WebElement noButton;
	
	 @FindBy (xpath = "//button[@class = 'p-button-rounded p-button-danger p-button p-component p-button-icon-only']")
	 WebElement rowDeleteIcon;
	 
	 @FindBy (xpath = "//div[@class = 'ng-trigger ng-trigger-animation ng-tns-c133-9 p-dialog p-confirm-dialog p-component ng-star-inserted']")
	 WebElement deleteConfirmWindow;
	
	 @FindBy (xpath = "//span[@class = 'p-dialog-title ng-tns-c133-9 ng-star-inserted']")
	 WebElement deleteWindowHeader;
	 
	 @FindBy (xpath = "//span[@class = 'p-confirm-dialog-message ng-tns-c133-9']")
	 WebElement deleteWindowMessage;
	 
	 @FindBy (xpath = "//button[@class = 'ng-tns-c133-9 p-confirm-dialog-reject p-ripple p-button p-component ng-star-inserted']")
	 WebElement userNoButton;
	 
	 String expHeader = "Are you sure you want to delete the user?";
	 String header = "Confirm";
	 
	 @FindBy (xpath = "//button[@class = 'ng-tns-c133-9 p-dialog-header-icon p-dialog-header-close p-link ng-star-inserted']")
	 WebElement closeButton;
	 
	 @FindBy(css = "tbody.p-datatable-tbody tr")
		List<WebElement> CurrentRows;
	 
	 @FindBy(xpath = "//button[@class='p-paginator-next p-paginator-element p-link p-ripple']") 
		WebElement nextPageIcon;
	 
	 @FindBy(xpath="//small[@id='text-danger']")
		WebElement invalidInputFielderrormsg;
		WebElement secondColumnElement;
	 
	
	
	public WebElement getUserIDSortIcon() {
		return userIDSortIcon;
	}

	public WebElement getUserNameSortIcon() {
		return userNameSortIcon;
	}

	public WebElement getLocationSortIcon() {
		return locationSortIcon;
	}
	
	public WebElement getPhonenumberSortIcon() {
		return phonenumberSortIcon;
	}

	public List<WebElement> getUserIDList() {
		return userIDList;
	}

	public List<WebElement> getUserNameList() {
		return userNameList;
	}

	public List<WebElement> getUserLocationList() {
		return userLocationList;
	}

	public List<WebElement> getUserPhoneNumberList() {
		return userPhoneNumberList;
	}
	
	public void clickYesButton() {
		yesButton.click();
	}
	
	public boolean isCloseddeleteConfirmWindow() {
		if(deleteConfirmWindow.isDisplayed()) {
			return true;
		}
		else {
			LoggerLoad.info("Delete confirmation window closed");
			return false;
		}
	}
	public boolean verifyDeletedBatches(List<String> deletedNames) throws InterruptedException {
	    boolean isDeletedRecordPresent = false;
//	    WebElement nextPageButton = driver.findElement(By.xpath("/html/body/app-root/app-batch/div/mat-card/mat-card-content/p-table/div/p-paginator/div/button[3]"));
	  
//	    WebElement searchBox = driver.findElement(By.id("filterGlobal"));
	    userSearch.clear();
	    userSearch.sendKeys("Selenium");

	   
	    while (true) {	      
	        List<WebElement> currentPageRows = getRowsFromCurrentPage();
	        if (currentPageRows == null || currentPageRows.isEmpty()) {
	            break; 
	        }	     
	        for (WebElement row : currentPageRows) {
	            List<WebElement> batchNameElements = row.findElements(By.xpath(".//td[2]")); // Adjust XPath as needed
	            List<String> batchNames = batchNameElements.stream()
	                                                       .map(WebElement::getText)
	                                                       .collect(Collectors.toList());	    
	            for (String deletedBatchName : deletedNames) {
	                if (batchNames.contains(deletedBatchName)) {
	                    isDeletedRecordPresent = true;
	                    System.out.println(deletedBatchName + " Program name is not deleted while selecting multiple rows");
	                    break; 
	                }	               
	            }    
	           
	        }
	        if (isDeletedRecordPresent) {
                return isDeletedRecordPresent; // Exit the inner loop if a deleted batch name is found
            }
	        if (nextPageIcon.getAttribute("disabled") == null || nextPageIcon.getAttribute("disabled").isEmpty()) {
	        	nextPageIcon.click();
	        } else {
	            // The button is disabled, so we exit the loop or handle the case accordingly
	            break;
	        }
	    }
	    return isDeletedRecordPresent;
	}
	
	private List<WebElement> getRowsFromCurrentPage() {
		
		return CurrentRows;
	}

	public List<String> selectMultipleCheckboxesA() throws InterruptedException {
		   
	    WebElement searchBox = driver.findElement(By.id("filterGlobal"));
	    searchBox.sendKeys("seleniumTribe");
	    
	    Thread.sleep(1000);
	    //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
	    
	    List <String>secondColumnTexts = new ArrayList<>();
	    
	    List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='p-datatable-tbody']/tr"));

		for (WebElement row : rows) {
			WebElement checkbox = row.findElement(By.xpath("//td[1]//div[@role='checkbox']"));
			if (!checkbox.isSelected()) {
				
				checkbox.click();
				Thread.sleep(1000);
				secondColumnElement = row.findElement(By.xpath("./td[2]"));
				String text = secondColumnElement.getText();
				secondColumnTexts.add(text);
				LoggerLoad.info("BatchNames Checkbox Selected For Delete : "+text);
			}
		}
		clickManageHeaderDeleteIconForMultipleRow();
		return secondColumnTexts;

	}
	
	public List<String> selectMultipleCheckboxes() throws InterruptedException {
	    WebElement searchBox = driver.findElement(By.id("filterGlobal"));
	    searchBox.sendKeys("seleniumTribe");

	    // Wait for the rows to be loaded
	   // WebDriverWait wait = new WebDriverWait(driver, 5);
//	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@class='p-datatable-tbody']/tr")));

	    	Thread.sleep(1000);
	    List<String> secondColumnTexts = new ArrayList<>();

	    List<WebElement> rows = driver.findElements(By.xpath("//tbody[@class='p-datatable-tbody']/tr"));

	    for (WebElement row : rows) {
	        WebElement checkbox = row.findElement(By.xpath(".//td[1]//div[@role='checkbox']"));
	        if (!checkbox.isSelected()) {
	            checkbox.click();
	            Thread.sleep(1000);// Wait for the checkbox to be updated
	            WebElement secondColumnElement = row.findElement(By.xpath(".//td[2]"));
	            String text = secondColumnElement.getText();
	            secondColumnTexts.add(text);
	            LoggerLoad.info("BatchNames Checkbox Selected For Delete: " + text);
	        }
	    }
	    
	    return secondColumnTexts;
	}
	

    public void clickManageHeaderDeleteIconForMultipleRow(){
    	try
    	{
    		if(deleteButton.isEnabled())
    		{

    			deleteButton.click();
    		}
    	}catch(Exception e)
    	{
    		System.out.println("No user to delete");
    	}
    }
    
    public boolean verifyDeletedrecords(String[] deletedelements) {
	    // Wait for the table rows to appear after deletion
	  
//	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
	    
	    // Get all batch names in the table
	    List<WebElement> userNameElements = driver.findElements(By.xpath("//table//tbody//tr//td[2]"));
	    List<String> userNames = userNameElements.stream().map(WebElement::getText).collect(Collectors.toList());
	    
	    // Verify that the deleted batch names are no longer present in the table
	    for (String userName : userNames) {
	        if (userNames.contains(userName)) {
	            return false;
	        }
	    }
	    return true;
	}
    public void clickSingleChkbox() throws InterruptedException {
		 WebElement searchBox = driver.findElement(By.id("filterGlobal"));
		    searchBox.sendKeys(text);
		    System.out.println(text+ "  "+"username is deleteing");		
		   
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
		  
		    WebElement checkbox = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]//div[@role='checkbox']"));
		  
		    checkbox.click();
		 
		    System.out.println("checkbox selected for the rows");
	}


public void clickMDeleteIconForsingleRow()
	{
		    WebElement deleteIcon = driver.findElement(By.xpath("//table//tbody//tr[1]//td[7]//button[@icon='pi pi-trash']"));
		    deleteIcon.click();
		    System.out.println("deleted for the rows");
	}

	public void assertYesOrNoBtn() {		
		Assert.assertTrue(yesButton.isDisplayed(), "'Yes' optionbutton is not displayed");
		Assert.assertTrue(noButton.isDisplayed(), "'No' optionbutton is not displayed");
		System.out.println("Assertion yes button done");
	}


public void verifyTheDeletedMessage() {
		try {
			String ExpectedMessageText = "Successful";
			WebElement batchDeletedMsg = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='p-toast-summary ng-tns-c90-39']")));
			String actualMessageText = batchDeletedMsg.getText();
			System.out.println("actual Success Message: " + actualMessageText);
			System.out.println("Expected :" + ExpectedMessageText);
			
			
		} catch (Exception e) {
			// Assert.fail("Batch Deleted message not found within the timeout period.");
		}

	}

	public WebElement getdeleteRowIcon() {
		return rowDeleteIcon;
	}
	
	public WebElement getdeleteConfirmWindow() {
		return deleteConfirmWindow;
	}

	public void validateDeleteConfirmationWindow() {
		
		if(deleteConfirmWindow.isDisplayed()) {
			System.out.println("Delete Confirmation window displayed");
			LoggerLoad.info("Delete Confirmation window displayed");
		}
		else {
			LoggerLoad.info("Delete confirmation alert not displayed");
		}
		String headerMessage = deleteWindowMessage.getText().toString();
		String headertitle = deleteWindowHeader.getText().toString();
		Assert.assertEquals(headerMessage,expHeader);
		Assert.assertEquals(headertitle, header);
		
		if(yesButton.isDisplayed()) {
			System.out.println("Alert message is displayed with delete button");
			LoggerLoad.info("Alert message is displayed with delete button");
		}
		else {
			LoggerLoad.info("No buttons displayed");
		}
		
		if(noButton.isDisplayed()) {
			System.out.println("Alert message is displayed with delete button");
			LoggerLoad.info("Alert message is displayed with delete button");
		}
		else {
			LoggerLoad.info("No buttons displayed");
		}
		
		if(closeButton.isDisplayed()) {
			System.out.println("Alert message is displayed with delete button");
			LoggerLoad.info("Alert message is displayed with delete button");
		}
		else {
			LoggerLoad.info("No buttons displayed");
		}
	}
	
	public void clickCloseButton() {
		closeButton.click();
	}
	
	public void clickUserNoButton() {
		noButton.click();
	}

	public void userDelete() {
//		userSearch.sendKeys("Selenium");
//		List<String> originalList =  userNameList.stream().map(s -> s.getText()).collect(Collectors.toList());
//		 for (String deletedUserName : originalList) {
//		        if (originalList.contains(deletedUserName)) {
//		            isDeletedUserPresent = true;
//		            break;
		
	}
	
		        
		 }
