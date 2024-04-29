Feature: Sort user 

Background: Admin is on Manage User Page after clicks user on the navigation bar
	Given Admin is logged in with valid credentials

Scenario: Sort user by id
Given Admin is on Manage User Page	
When Admin clicks on ID sort icon	
Then Admin should see User details are sorted by id

Scenario: Sort user by name
Given Admin is on Manage User Page	
When Admin clicks on name sort icon	
Then Admin should see User details are sorted by name

Scenario: Sort user by Location
Given Admin is on Manage User Page	
When Admin clicks on Location sort icon	
Then Admin should see User details are sorted by Location

Scenario: Sort user by Phone number
Given Admin is on Manage User Page	
When Admin clicks on Phone number sort icon	
Then Admin should see User details are sorted by Phone number