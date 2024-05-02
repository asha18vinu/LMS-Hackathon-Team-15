
Feature: Pagination 
	Verify the Pagination features
	
	Background: Admin is on Manage Program Page after logged in
	Given Admin is logged in with valid credentials and admin is on the manage program page
	 

  Scenario: Verify Next page link	
    When Admin clicks Next page link on the program table 
    Then Admin should see the Pagination has Next active link
  
  Scenario: Verify Last page link
    When Admin clicks Last page link
    Then Admin should see the last page record on the table with Next page link are disabled
     
  Scenario: Verify First page link
    When Admin clicks First page link
    Then Admin should see the previous page record on the table with pagination has previous page link
   
   Scenario: Verify Start page link	
    When Admin clicks Start page link
    Then Admin should see the very first page record on the table with Previous page link are disabled