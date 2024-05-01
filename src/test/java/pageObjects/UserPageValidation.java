package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonUtilities.CommonUtils;
import commonUtilities.Pagination;
import context.TestContext;

public class UserPageValidation 
{
	WebDriver driver;
	CommonUtils commonUtils;
	TestContext testcontext;
	Pagination pagination;
	
	String totalRecords;
	boolean flag = false;
	
	static String userFirstName;
	
	@FindBy(xpath="//input[@*='userLoginEmailId']") WebElement userName;
	@FindBy(xpath="//input[@*='Password']") WebElement pwd;
	@FindBy(xpath="//button[@*='submit']") WebElement btn;
	
	@FindBy(xpath="//button[@id='user']") WebElement userNavigationElt;
	@FindBy(xpath="//div[@class = 'signin-content']//mat-card//mat-card-title/div[1]") WebElement userPageHeader;
	
	@FindBy(xpath="//span[@class='p-paginator-current ng-star-inserted']") WebElement paginationString;
	@FindBy(xpath="//div[@class='p-datatable-footer ng-star-inserted']/div") WebElement pageFooter;
	@FindBy(xpath="//span[@class='p-paginator-pages ng-star-inserted']/button")List<WebElement> paginationNumbers;
	@FindBy(xpath="//span[@class='p-paginator-pages ng-star-inserted']/button[1]") WebElement firstPage;
		
	
	@FindBy(xpath="//span[@class='p-paginator-icon pi pi-angle-left']") WebElement previousPageIcon;
	@FindBy(xpath="//span[@class='p-paginator-icon pi pi-angle-right']") WebElement nextPageIcon;
	@FindBy(xpath="//span[@class='p-paginator-icon pi pi-angle-double-right']") WebElement lastPageIcon;
	@FindBy(xpath="//span[@class='p-paginator-icon pi pi-angle-double-left]") WebElement firstPageIcon;
	
	@FindBy(xpath="//table[@role='grid']//tr[@class='ng-star-inserted']/th") List<WebElement> tableHeader;
	@FindBy(xpath="//table[@role='grid']/tbody/tr[@class='ng-star-inserted']") List<WebElement> tableRow;
	@FindBy(css = "td:not(:first-child):not(:last-child)") List<WebElement> tableCellData;
	@FindBy(css = "tbody.p-datatable-tbody tr")	List<WebElement> tableCurrentRow;
	
	@FindBy(xpath="//div[@class='box'][2]/div[1]/button") WebElement deleteIcon;
	@FindBy(xpath="//input[@id='filterGlobal']") WebElement searchText;
	@FindBy(xpath="//div[@class='box'][2]/div[3]/button") WebElement addNewUser;
	@FindBy(xpath="//div[@class='box'][2]/div[4]/button") WebElement assignStudent;
	@FindBy(xpath="//div[@class='box'][2]/div[5]/button") WebElement assignStaff;

	
	public UserPageValidation(WebDriver driver) 
	{		
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		commonUtils = new CommonUtils(this.driver);
		pagination = new Pagination(this.driver);
		System.out.println("Inside User Page Constructor");
	}
	
	public void login()
	{
		userName.sendKeys("sdetorganizers@gmail.com");
		pwd.sendKeys("UIHackathon@02");
		btn.click();
		System.out.println("Login successfull");
	}
	
	//Navigation Bar Element Click
	public void clickNavigationBarElement(String element)
	{
		//if((userNavigationElt.getText()) == element)
		//{
			userNavigationElt.click();					
		//}
	}
	
	//User Page Header
	public String getPageHeader()
	{		
		return userPageHeader.getText();
	}
		
	
//	public boolean isPrevPageIconDisplayed()
//	{
//		if(previousPageIcon.isDisplayed()) 
//			return true;
//		else return false;
//			
//	}
//	
//	public boolean isNextPageIconDisplayed()
//	{
//		if(nextPageIcon.isDisplayed()) 
//			return true;
//		else return false;
//			
//	}
//	
//	public boolean isFirstPageIconDisplayed()
//	{
//		if(firstPageIcon.isDisplayed()) 
//			return true;
//		else return false;
//			
//	}
//	
//	public boolean isLastPageIconDisplayed()
//	{
//		if(lastPageIcon.isDisplayed()) 
//			return true;
//		else return false;
//			
//	}
//	
//	public boolean isPreviousLinksDisabled()
//	{
//		//if(firstPage.getAttribute("class").contains("p-highlight"))
//		firstPage.click();
//		if((!previousPageIcon.isEnabled()) &&(!firstPageIcon.isEnabled()))
//			return true;
//		else 
//			return false;
//	}
//	
//	public boolean isNextLinksDisabled()
//	{
//		int lastRecord = paginationNumbers.size();
//		WebElement lastPage = driver.findElement(By.xpath("//button[normalize-space()='"+lastRecord+"']"));
//		lastPage.click();
//		
//		if((!lastPageIcon.isEnabled()) &&(!nextPageIcon.isEnabled()))
//			return true;
//		else 
//			return false;
//	}
//	
//	
//	//Total Records 
//	public String extractTotalRecordsFromFooter()
//	{
//		totalRecords = (pageFooter.getText()).replaceAll("[^0-9]","");
//		return totalRecords;
//	}
//	
//	//Pagination Footer 
//	public boolean verifyFooterText(String moduleName)
//	{
//		String recordCount = extractTotalRecordsFromFooter();
//		String actualFooterText = pageFooter.getText();
//		String expectedFooterText = "In total there are "+recordCount+" "+moduleName+ ".";
//		
//		System.out.println("Actual Footer - "+actualFooterText);
//		System.out.println("ExpectedFooter - "+expectedFooterText);
//		if(actualFooterText.equals(expectedFooterText))
//			return true;
//		else
//			return false;
//	}
//	
//	//Current Page Number
//	public int getSelectedPageNumber() 
//	{
//		int currentPage;
//
//	    for (int i = 0; i < paginationNumbers.size(); i++)
//	    {    	
//	        WebElement page = paginationNumbers.get(i);
//	        
//	        // Check if the button has the "p-highlight" class
//	        if (page.getAttribute("class").contains("p-highlight"))
//	        {
//	            String pageNumberString = page.getText().trim();
//	            currentPage = Integer.parseInt(pageNumberString);
//	            System.out.println("Current Page - "+currentPage);
//	            return currentPage;
//	        }
//	    }
//	   
//	    return -1;
//	}
//	
//	//Pagination Text 
//	public boolean verifyPaginationText()
//	{		
//		int selectedPageNumber = getSelectedPageNumber();
//		int recordsPerPage = 5;
//		int startRecord = (selectedPageNumber - 1) * recordsPerPage + 1;
//		int endRecord = startRecord+recordsPerPage-1;
//		
//		String actualPaginationText = paginationString.getText();
//		String expectedPaginationText = "Showing "+startRecord+" to " +endRecord+ " of "+totalRecords+ " entries";
//		
//		System.out.println("Expected Pagination Text - "+expectedPaginationText);
//		if(actualPaginationText.equals(expectedPaginationText))
//			return true;
//		else
//			return false;
//		
//	}
	
	//Table Column Values 
	public List<String> getTableHeader()
	{
		List<String> colHeader = new ArrayList<String>();
		for(int i=1; i<tableHeader.size();i++)
		{
			String data = tableHeader.get(i).getText().trim();
			colHeader.add(data);
			System.out.println(data);			
		}
		
		return colHeader;
	}
	
	//Delete Icon Disabled
	public boolean isDeleteIconDisabled()
	{
		if(deleteIcon.getAttribute("disabled") != null)
			return true;
		else return false;
	}
	
	//Add New User Displayed
	public boolean isAddNewUserDisplayed()
	{
		if(commonUtils.isPresent(addNewUser))
			return true;
		else return false;
	}
	
	//Assign Student Displayed
	public boolean isAssignStudentDisplayed()
	{
		if(commonUtils.isPresent(assignStudent))
			return true;
		else return false;
	}
	
	//Assign Staff Displayed
	public boolean isAssignStaffDisplayed()
	{
		if(commonUtils.isPresent(assignStaff))
			return true;
		else return false;
	}
	
	//Search Text Box Displayed
	public boolean isSearchTextDisplayed()
	{
		if(commonUtils.isPresent(searchText))
			return true;
		else return false;
	}
	
	public void getTotalRowDisplayed()
	{	
		System.out.println("Total Rows in Data Table : "+tableRow.size());
	}
	
	public void isTableCheckBoxDisplayed()
	{
		int rowCount=0;
		for(WebElement row : tableRow)
		{
			rowCount++;
			WebElement chkBox = row.findElement(By.xpath("//p-tablecheckbox"));
			Assert.assertEquals(true,chkBox.isDisplayed(),"CheckBox not displayed for row "+rowCount);
		}
	}
	
	public void isTableEditIconEnabled()
	{
		int rowCount=0;
		for(WebElement row : tableRow)
		{
			rowCount++;
			WebElement editIcon = row.findElement(By.xpath("//div[@class='action']//button[1]"));
			Boolean enabled = editIcon.isEnabled();
			Assert.assertEquals(true,enabled,"Edit Icon is not Enabled for row "+rowCount);
		}
	}
	
	public void isTableDeleteIconEnabled()
	{
		int rowCount=0;
		for(WebElement row : tableRow)
		{
			rowCount++;
			WebElement delIcon = row.findElement(By.xpath("//div[@class='action']//button[2]"));
			Boolean enabled = delIcon.isEnabled();
			Assert.assertEquals(true,enabled,"Delete Icon is not Enabled for row "+rowCount);
		}
		
	}
	
	public void inputValueSearchBox(String data) throws InterruptedException 
	{
		//commonUtils.sendKeysMethod(searchText, data, 5);
		userFirstName = data;
		searchText.sendKeys(data);
		System.out.println("Value in search text - "+data);
		Thread.sleep(2000);
		String text = paginationString.getText();
		System.out.println("Pagination String in Input Search Box- "+text);
	}
	
	public void footerInvalidSearch()
	{	
		String value = "0 entries";
		System.out.println("Value to check: - "+value);
		
		String text = paginationString.getText();
		System.out.println("Actual Pagination String - "+text);
		
		Pattern pattern = Pattern.compile("\\b0\\b");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
            System.out.println("'0' entries is present in the table");
        } else {
            System.out.println("'0' entries is not present in the table");
        }
		Assert.assertTrue(text.contains(value));
	}
	
	public boolean checkUserSearch() 
	{
		
		List<WebElement> tableRowData = new ArrayList<>();
		List<WebElement> currentPageRows = getRowsFromCurrentPage();

		tableRowData.addAll(currentPageRows);

		for (WebElement row : currentPageRows) 
		{

			List<WebElement> cells = row.findElements(By.tagName("td"));

			List<WebElement> filteredCells = new ArrayList<>();
			for (int i = 1; i < cells.size() - 1; i++) 
			{
				filteredCells.add(cells.get(i));
			}

			for (WebElement cell : filteredCells) 
			{
				if (cell.getText().contains(userFirstName)) 
				{
						flag = true;
						break;
				}
			}
		}

		if (flag) {
			System.out.println("User is present in the table");
		} else
			System.out.println("User is not present in the table");

		return flag;
	}

	private List<WebElement> getRowsFromCurrentPage() {
		return tableCurrentRow;
	}
	
	
	
}
