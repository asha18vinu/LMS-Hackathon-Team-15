@dashboardtag
Feature: This feature covers all AssignStudent scenarios

Background: 
Given User login in and navigate to User Module

	Scenario: Validate Assign Student Popup window
		Given Admin is in manage user page
		When Admin clicks Assign Student button
		Then Admin should see a pop up open for assign student details with empty form along with Save and Cancel button and close (X) icon on the top right corner of the window

		Scenario: Validate input fields and their text boxes in Assign Student form 
		Given Admin is in manage user page
		When Admin clicks Assign Student button
		Then Admin should see User Role as R03,and other fields Student Email id,Program Name,Batch Name and Status with respective input boxes.
		
		Scenario: Validate Dropdown in Assign Student Form
		Given Admin is in manage user page
		When Admin clicks Assign Student button
		Then Admin should see drop down boxes with valid datas for Student Email id,Program Name and Batch Name
		
		Scenario: Validate radio button in Assign Student Form
		Given Admin is in manage user page
		When Admin clicks Assign Student button
		Then Admin should see two radio button for Status
		
		Scenario: Empty Form Submission
		Given Admin is in Assign Student details pop up page
		When Admin clicks Save button with entering any data
		Then Admin gets a Error message alert
		
		Scenario: Validate Cancel or Close icon on Assign Student form
		Given Admin is in Assign Student details pop up page
		When Admin clicks Cancel or Close Icon on Assign Student form
		Then Assign Student popup window should be closed without saving
		
		Scenario: Validate Cancel button on Assign Student form
		Given Admin is in Assign Student details pop up page
		When Admin clicks Cancel on  button 
		Then Admin can see the Assign Student popup disappears without assigning 