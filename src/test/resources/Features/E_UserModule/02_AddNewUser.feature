Feature: Add new user
As an Admin User want to verify the existence of fields on the User details Page
So that User can add a new User with the required information

Background:
	Given Admin login with valid credentials
	Given Admin is logged on the Lms portal dashboardPage after login
	When Admin clicks "User" from navigation bar
	Then Admin clicks + A new User button
	Then Admin should see pop up open for User details
	
	Scenario Outline: Admin verifies the fields exits in User details page pop up 
	Then Admin verifies the "<Fields>" existance and its "<FieldType>"
	
	Examples:
	|Fields|FieldType|
	|FirstName  |textbox|
	|MiddleName |textbox|
	|LastName		|textbox|
	|Location   |textbox|
	|PhoneNumber|textbox|
	|LinkedIn   |textbox|
	|RoleId			|dropDown|
	|RoleStatus|dropDown|
	|VisaStatus|dropDown|
	|Email		 |textbox|
	|UG 			 |textbox|
	|PG				 |textbox|
	|TimeZone|textbox|
	|Comments|textbox|
		
Scenario: Validate User Details Popup window has Cancel,Submit and Close button
Then Admin should see pop up open for user details with Cancel ,Submit and close buttons

Scenario Outline: Check if user is created when only mandatory fields are entered with valid data
Given Admin is on User details pop up	
When Admin enters mandatory fields "<sheetname>" and <RowNo> in the form and clicks on submit button	
Then Admin gets message User added "<Message>" and "<Sceanrio>" and for successful creation check for the user in table

Examples:
|sheetname   | RowNo| Message  | Sceanrio  |
|AddUser		 | 0    | Success  | User created|
|AddUser		 | 1    | Failed   | Existing values|
|AddUser		 | 2    | Failed   | Invalid data |
|AddUser		 | 3    | Failed   | Missing mandatory values|
|AddUser		 | 4    | Failed   | Missing mandatory values|

Scenario: Empty form submission
Given Admin is on User details pop up	
When Admin clicks on submit button without entering data 	
Then User won't be created and Admin gets error message

Scenario: Validate Cancel/Close(X) icon on User Details form
Given Admin is on User details pop up	
When Admin clicks Cancel or Close(X) Icon on User Details form	
Then User Details popup window should be closed without saving

Scenario: Validate Cancel button on User Details form
Given Admin is on User details pop up	
When Admin clicks Cancel button 	
Then Admin can see the User details popup disappears without adding any user

