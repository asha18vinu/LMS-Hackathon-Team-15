Feature: LoginPage feature

  Background: 
    Given Admin gives the correct LMS portal URL
    And Admin is on Home Page
    
    @04LoginPage_InValidEmptyUserAndPassword
  Scenario Outline: Validate login credentials with null password and User
    When Admin enter no value in username and password from sheet "<option>" and "<sheetName>" and clicks login button
    Then Error message please enter username and password

    Examples: 
      | option                      | sheetName |
      | InValidEmptyUserAndPassword | loginPage |
    

  @01LoginPage_InValidCredential
  Scenario Outline: Validate login with invalid credentials
    #Given Admin gives the correct LMS portal URL
    When Admin enter invalid credentials from sheet "<option>" and "<sheetName>" and clicks login button
    Then Error message please check username/password

    Examples: 
      | option            | sheetName |
      | InvalidCredential | loginPage |

  @02LoginPage_InValidCredential_NullUsername
  Scenario Outline: Validate login credentials with null username
    When Admin enter value only in password from sheet "<option>" and "<sheetName>" and clicks login button
    Then Error message please enter username

    Examples: 
      | option              | sheetName |
      | InValidNullUsername | loginPage |

  @03LoginPage_InValidCredential_NullPassword
  Scenario Outline: Validate login credentials with null password
    When Admin enter value only in username from sheet "<option>" and "<sheetName>" and clicks login button
    Then Error message please enter password

    Examples: 
      | option              | sheetName |
      | InValidNullPassword | loginPage |

  
  @05LoginPage_InValidPasswordAllNumeric
  Scenario Outline: Validate login credentials with password as numeric
    When Admin enter value only only numeric value in password from sheet "<option>" and "<sheetName>" and clicks login button
    Then Error message please check username/password

    Examples: 
      | option                    | sheetName |
      | InValidPasswordAllNumeric | loginPage |

  @06LoginPage_InValidCredential_ClickLoginButton_withoutInput
  Scenario: Validate login credentials when user click Login button directly without entering any details
    When Admin enter no value only in input and password field and click on Loginbutton
    Then Error message please enter username and password

  @06LoginPage_ValidCredential
  Scenario Outline: Validate login with valid credentials
    When Admin enter valid credentials from sheet "<option>" and "<sheetName>" and clicks login button
    Then Admin should land on dashboard page

    Examples: 
      | option          | sheetName |
      | ValidCredential | loginPage |

  @07LoginPage_ValidCredential_ThroughKeyboard
  Scenario Outline: verify login button action through keyboard
    When Admin enter valid credentials  from sheet "<option>" and "<sheetName>" and clicks login button through keyboard
    Then Admin should land on dashboard page

    Examples: 
      | option          | sheetName |
      | ValidCredential | loginPage |

  @08LoginPage_ValidCredential_ThroughMouse
  Scenario Outline: verify login button action through mouse
    When Admin enter valid credentials from sheet "<option>" and "<sheetName>" and clicks login button through mouse
    Then Admin should land on dashboard page

    Examples: 
      | option          | sheetName |
      | ValidCredential | loginPage |
