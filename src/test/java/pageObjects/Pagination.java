package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	}

