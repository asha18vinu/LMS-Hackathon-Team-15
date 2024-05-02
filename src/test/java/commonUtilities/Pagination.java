package commonUtilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
<<<<<<< HEAD

public class Pagination {
	
		WebDriver driver;
		
		@FindBy(xpath="//p-paginator/div/span[1]") WebElement paginationString;
		@FindBy(xpath="//div[@class='p-datatable-footer ng-star-inserted']/div") WebElement pageFooter;
		@FindBy(xpath="//span[@class='p-paginator-pages ng-star-inserted']")List<WebElement> paginationNumbers;
		@FindBy(xpath="//span[@class='p-paginator-pages ng-star-inserted']/button[1]") WebElement firstPage;
		
		@FindBy(xpath ="//span[@class='p-paginator-icon pi pi-angle-left']") WebElement previousPageIcon;
		@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-right']") WebElement nextPageIcon;
		@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-double-right']") WebElement lastPageIcon;
		@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-double-left]") WebElement firstPageIcon;
		
		String totalRecords;
		//String currentPage;
		
		//Total Records
		public String extractTotalUserFromFooter()
		{
			totalRecords = (pageFooter.getText()).replaceAll("[^0-9]","");
			return totalRecords;
		}
		
		public boolean isPrevPageIconDisplayed()
		{
			if(previousPageIcon.isDisplayed()) 
				return true;
			else
				return false;
				
		}
		
		public boolean isNextPageIconDisplayed()
		{
			if(nextPageIcon.isDisplayed()) 
				return true;
			else 
				return false;
				
		}
		
		public boolean isFirstPageIconDisplayed()
		{
			if(firstPageIcon.isDisplayed()) 
				return true;
			else 
				return false;
				
		}
		
		public boolean isLastPageIconDisplayed()
		{
			if(lastPageIcon.isDisplayed()) 
				return true;
			else 
				return false;
				
		}
		
		public boolean isPreviousLinksDisabled()
		{
			//if(firstPage.getAttribute("class").contains("p-highlight"))
			firstPage.click();
			if((!previousPageIcon.isEnabled()) &&(!firstPageIcon.isEnabled()))
				return true;
			else 
				return false;
		}
		
		public boolean isNextLinksDisabled()
		{
			int lastRecord = paginationNumbers.size();
			WebElement lastPage = driver.findElement(By.xpath("//button[normalize-space()='"+lastRecord+"']"));
			lastPage.click();
			
			if((!lastPageIcon.isEnabled()) &&(!nextPageIcon.isEnabled()))
				return true;
			else 
				return false;
		}
		
		//Current Page Number
		public int getSelectedPageNumber() 
		{
			int currentPage;

		    for (int i = 0; i < paginationNumbers.size(); i++) {
		        WebElement page = paginationNumbers.get(i);
		        
		        // Check if the button has the "p-highlight" class
		        if (page.getAttribute("class").contains("p-highlight"))
		        {
		            String pageNumberString = page.getText().trim();
		            currentPage = Integer.parseInt(pageNumberString);
		            System.out.println("Current Page - "+currentPage);
		            return currentPage;
		        }
		    }
		    
		    return -1;

		}

		//Pagination Footer 
		public boolean verifyFooterText(String moduleName)
		{
			String actualFooterText = pageFooter.getText();
			String expectedFooterText = "In total there are "+totalRecords+" "+moduleName+ " ";
			
			if(actualFooterText.equals(expectedFooterText))
				return true;
			else
				return false;
		}
		
		//Pagination Text 
		public boolean verifyPaginationText()
		{
			String actualPaginationText = paginationString.getText();
			int selectedPageNumber = getSelectedPageNumber();
			int recordsPerPage = 5;
			int startRecord = (selectedPageNumber - 1) * recordsPerPage + 1;
			int endRecord = startRecord+recordsPerPage-1;
			String expectedPaginationText = "Showing "+startRecord+" to " +endRecord+ " of "+totalRecords+ " entries";
			
			if(actualPaginationText.equals(expectedPaginationText))
				return true;
			else
				return false;
			
		}
=======
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pagination {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//p-paginator/div/span[1]") WebElement paginationString;
	@FindBy(xpath="//div[@class='p-datatable-footer ng-star-inserted']/div") WebElement pageFooter;
	@FindBy(xpath="//span[@class='p-paginator-pages ng-star-inserted']")List<WebElement> paginationNumbers;
	@FindBy(xpath="//span[@class='p-paginator-pages ng-star-inserted']/button[1]") WebElement firstPage;
	
	@FindBy(xpath ="//span[@class='p-paginator-icon pi pi-angle-left']") WebElement previousPageIcon;
	@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-right']") WebElement nextPageIcon;
	@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-double-right']") WebElement lastPageIcon;
	@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-double-left]") WebElement firstPageIcon;
	@FindBy (xpath = "//button[@class = 'p-paginator-next p-paginator-element p-link p-ripple']")
	WebElement nextIcon;
	
	@FindBy (xpath = "//button[@class = 'p-paginator-last p-paginator-element p-link p-ripple ng-star-inserted']")
	WebElement lastIcon;
	
	@FindBy (xpath = "//button[@class = 'p-paginator-next p-paginator-element p-link p-ripple p-disabled']")
	WebElement checkNextIcon;	
	
	@FindBy (xpath = "//button[@class = 'p-paginator-first p-paginator-element p-link p-ripple ng-star-inserted']")
	WebElement firstpage;
	
	@FindBy (xpath = "//button[text()='4']")
	WebElement fourthpage;
	
	@FindBy (xpath = "//button[text()='3']")
	WebElement thirdPage;
	
	@FindBy (xpath = "//button[@class = 'p-paginator-first p-paginator-element p-link p-ripple ng-star-inserted']")
	WebElement initialPage;
	String totalRecords;
	//String currentPage;
	
	public Pagination(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
	}

	//Total Records
	public String extractTotalUserFromFooter()
	{
		totalRecords = (pageFooter.getText()).replaceAll("[^0-9]","");
		return totalRecords;
	}

	public String extractTotalProgramFromFooter()
	{
		totalRecords = (pageFooter.getText()).replaceAll("[^0-9]","");
		return totalRecords;
	}
	public boolean isPrevPageIconDisplayed()
	{
		if(previousPageIcon.isDisplayed()) 
			return true;
		else
			return false;
			
	}
	
	public boolean isNextPageIconDisplayed()
	{
		if(nextPageIcon.isDisplayed()) 
			return true;
		else 
			return false;
			
	}
	
	public boolean isFirstPageIconDisplayed()
	{
		if(firstPageIcon.isDisplayed()) 
			return true;
		else 
			return false;
			
	}
	
	public boolean isLastPageIconDisplayed()
	{
		if(lastPageIcon.isDisplayed()) 
			return true;
		else 
			return false;
			
	}
	
	public boolean isPreviousLinksDisabled()
	{
		//if(firstPage.getAttribute("class").contains("p-highlight"))
		firstPage.click();
		if((!previousPageIcon.isEnabled()) &&(!firstPageIcon.isEnabled()))
			return true;
		else 
			return false;
	}
	
	public boolean isNextLinksDisabled()
	{
		int lastRecord = paginationNumbers.size();
		WebElement lastPage = driver.findElement(By.xpath("//button[normalize-space()='"+lastRecord+"']"));
		lastPage.click();
		
		if((!lastPageIcon.isEnabled()) &&(!nextPageIcon.isEnabled()))
			return true;
		else 
			return false;
	}
	
	//Current Page Number
	public int getSelectedPageNumber() 
	{
		int currentPage;

	    for (int i = 0; i < paginationNumbers.size(); i++) {
	        WebElement page = paginationNumbers.get(i);
	        
	        // Check if the button has the "p-highlight" class
	        if (page.getAttribute("class").contains("p-highlight"))
	        {
	            String pageNumberString = page.getText().trim();
	            currentPage = Integer.parseInt(pageNumberString);
	            System.out.println("Current Page - "+currentPage);
	            return currentPage;
	        }
	    }
	    
	    return -1;

	}

	//Pagination Footer 
	public boolean verifyFooterText(String moduleName)
	{
		String actualFooterText = pageFooter.getText();
		String expectedFooterText = "In total there are "+totalRecords+" "+moduleName+ " ";
		
		if(actualFooterText.equals(expectedFooterText))
			return true;
		else
			return false;
	}
	
	//Pagination Text 
	public boolean verifyPaginationText()
	{
		String actualPaginationText = paginationString.getText();
		int selectedPageNumber = getSelectedPageNumber();
		int recordsPerPage = 5;
		int startRecord = (selectedPageNumber - 1) * recordsPerPage + 1;
		int endRecord = startRecord+recordsPerPage-1;
		String expectedPaginationText = "Showing "+startRecord+" to " +endRecord+ " of "+totalRecords+ " entries";
		
		if(actualPaginationText.equals(expectedPaginationText))
			return true;
		else
			return false;
		
	}
	public void clickPreviousPageIcon() {
		previousPageIcon.click();
	}
	public boolean clickPage(WebElement element) {
		try {
	        
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	        System.out.println("The element clicked successfully");
	        
	        return true;
	    } catch(Exception e){
	        return false;
	    }
		}
	public boolean checkIsNextPageDisabled() {
		if(checkNextIcon.isEnabled()) {
			System.out.println("Next Page icon is enabled");
			return false;
		}
		else {
			System.out.println("Next Page icon is diabled");
			return true;
		}
	}
	
	public WebElement getNextIcon() {
		return nextIcon;
	}
	
	public WebElement getfirstpage() {
		return firstpage;
	}
	
	public WebElement getFourthPage() {
		return fourthpage;
	}
	
	public WebElement getLastIcon() {
		return lastIcon;
	}
	
	public boolean isThirdPageDisplayed() {
		if(thirdPage.isDisplayed()) {
			System.out.println("Previous page is visible");
			return true;
		}
		else {
			System.out.println("Previous page is not displayed");
			return false;
		}
	}
	
	public WebElement getInitialPage() {
		return initialPage;
	}
>>>>>>> 827f3cef0c4a9a6baa5e6cfbc2f265fbce3f2a45

	}

