@dashboardtag
Feature: This feature covers all AssignStaff scenarios

Background: 
Given User login in and navigate to User Module

 Scenario: Validate Assign Staff Popup window
 Given Admin is in manage user page
 When Admin clicks Assign Staff button
 Then Admin should see a pop up open for assign staff details with empty form along with Save and Cancel button and close (X) icon on the top right corner of the window

Scenario: Validate input fields and their text boxes in Assign Staff form 
Given Admin is in manage user page
When Admin clicks Assign Staff button
Then Admin should see User Role as R02,and other fields Student Email id, Skill, Program Name,Batch Name and Status with respective input boxes

Scenario: Validate Dropdown in Assign Staff Form
Given Admin is in manage user page
When Admin clicks Assign Staff button
Then Admin should see drop down boxes with valid datas for Student Email id, Skill, Program Name and Batch Name

Scenario: Validate radio button in Assign Staff Form
Given Admin is in manage user page
When Admin clicks Assign Staff button
Then Admin should see two radio button for Status

Scenario: Empty Form Submission
Given Admin is in Assign Staff details pop up page
When Admin clicks Save button without entering any data
Then Admin gets a Error message alert as Student Email id is required 

Scenario: Validate the Assign Staff form page without giving Student Email id
Given Admin is in Assign Staff details pop up page
When Admin clicks Save button without entering Student Email id
Then Admin gets a Error message alert as Student Email id is required

Scenario: Validate Cancel or Close icon on Assign Staff form
Given Admin is in Assign Staff details pop up page
When Admin clicks Cancel or Close Icon on Assign Staff  form
Then Assign Staff popup window should be closed without saving

Scenario: Validate Cancel button on Assign Staff form
Given Admin is in Assign Staff details pop up page
When Admin clicks Cancel button 
Then Admin can see the Assign Staff popup disappears without assigning 




