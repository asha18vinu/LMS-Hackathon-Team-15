package commonUtilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Pagination {
	
		WebDriver driver;
		public Pagination(WebDriver driver){
			this.driver = driver;
			PageFactory.initElements(driver, this);
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
		
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
		int recordCount;
		//String currentPage;
		
		//Total Records
	public int extractTotalUserFromFooter()
	{
		totalRecords = pageFooter.getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(totalRecords);
        
        if (matcher.find()) 
        {            
            String numberString = matcher.group();            
            recordCount = Integer.parseInt(numberString);
        }   
        
		System.out.println(pageFooter.getText());
		System.out.println("Total Records in Table - "+ recordCount);
		return recordCount;
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
			WebElement paginationContainer = driver.findElement(By.xpath("//span[@class='p-paginator-pages ng-star-inserted']"));

        List<WebElement> paginationButtons = paginationContainer.findElements(By.tagName("button"));

       for (WebElement button : paginationButtons) 
       {
            // Check if the button has the "p-highlight" class
            if (button.getAttribute("class").contains("p-highlight")) 
            {              
                int highlightedButtonText = Integer.parseInt(button.getText());
                System.out.println("Highlighted Button Text: " + highlightedButtonText);
                return highlightedButtonText;
                
            }
        }
		return -1;

		}

		//Pagination Footer 
		public boolean verifyFooterText(String moduleName)
		{
			int records = extractTotalUserFromFooter();
		String actualFooterText = pageFooter.getText();
		String expectedFooterText = "In total there are "+records+" "+moduleName+ ".";
		System.out.println("Actual Text:  "+actualFooterText+ " Expected Text:  "+expectedFooterText);
		
		if(actualFooterText.equals(expectedFooterText))
			return true;
		else
			return false;
		}
		
		//Pagination Text 
		public boolean verifyPaginationText()
		{
			String actualPaginationText = paginationString.getText();
		Thread.sleep(1000);
		int selectedPageNumber = getSelectedPageNumber();
		int recordsPerPage = 5;
		int startRecord = (selectedPageNumber - 1) * recordsPerPage + 1;
		int endRecord = startRecord+recordsPerPage-1;
		String expectedPaginationText = "Showing "+startRecord+" to " +endRecord+ " of "+recordCount+ " entries";
		
		System.out.println("Actual Pagination Text- "+actualPaginationText+" Expected Pagination Text - "+expectedPaginationText);
		if(actualPaginationText.equals(expectedPaginationText))
			return true;
		else
			return false;
			
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

	}

