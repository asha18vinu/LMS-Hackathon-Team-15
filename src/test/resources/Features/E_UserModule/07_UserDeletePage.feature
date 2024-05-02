Feature: Delete User

Background: Admin is on Manage User Page after clicks user on the navigation bar
	Given Admin login with valid credentials
	
	Scenario: Validate row level delete icon
	Given Admin is on Manage User Page
	When Admin clicks the delete icon
	Then Admin should see a alert open with heading Confirm along with  <YES> and <NO> button for deletion
	
	Scenario: Click Yes on deletion window
	Given Admin is on Confirm Deletion alert
	When Admin clicks yes option
	Then Admin gets a message <Successful User Deleted alert> and do not see that user in the data table
	
	Scenario: Click No on deletion window
	Given Admin is on Confirm Deletion alert
	When Admin clicks  No option
	Then Admin can see the deletion alert disappears without deleting
	
	Scenario: Validate Close(X) icon on Confirm Deletion alert
	Given Admin is on Confirm Deletion alert
	When Admin clicks on close button
	Then Admin can see the deletion alert disappears without any changes
	
	@logout
  Scenario: Admin Logout button function
    #Given Admin is in dashboard page
    When Admin click Logout button on navigation bar
    Then Admin should land on login in page
