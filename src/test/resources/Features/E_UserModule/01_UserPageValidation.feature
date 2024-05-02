Feature: User Page Validation
As a Admin User verifies the Manage User Page and its functionality

Background: 
 Given Admin login with valid credentials
 Given Admin is logged on the Lms portal dashboardPage after login to user
 When Admin clicks "User" from navigation bar
 
Scenario: Validate landing in User page
Then Admin should see the "user" in the URL

Scenario: Validate the heading
Then Admin should see a heading with text "Manage User" on the page

Scenario: Validate the text and pagination icon below the data table
Then Admin should see the text as "Showing x to y of z entries" along with Pagination icon below the table with x- starting record number on that page,y-ending record number on that page,z-Total number of records

Scenario: Validate data table headers in the User Pag
Then Admin Should see the data table with column names "ID", "Name", "Location", "Phone Number", "Edit / Delete"

Scenario: Validating the default state of Delete button	
Then Admin should see a Delete button on the top left hand side as Disabled

Scenario: Validate "+ A New user" button in User Page
Then Admin should be able to see the + A New User button above the data table

Scenario: Validate "+ Assign staff"  button in User page
Then Admin should be able to see the + Assign staff button above the data table

Scenario: Validate "+ Assign Student"  button in User page
Then Admin should be able to see the + Assign Student button above the data table

Scenario: Validate search box in User page
Then Admin should be able to see the search text box above the data table

Scenario: Validate number of data rows in the data table
Then Admin should see five records displayed on the data table

Scenario: Verify Check box on the data table
Then Each row in the data table should have a checkbox

Scenario: Verify edit icon on the data table	
Then Each row in the data table should have a edit icon that is enabled

Scenario: Verify  delete icon on the data table
Then Each row in the data table should have a delete icon that is enabled

Scenario: search user by name
Given Admin is on Manage User Pagee	
When Admin enters user name into search box.	
Then Admin should see user displayed with the entered name

Scenario: Validating the Search with unrelated keyword
Given Admin is on Manage User Page	
When Admin enters the keywords not present in the data table on the Search box 	
Then Admin should see zero entries on the data table
