Feature: Edit User Feature

  Background: 
    Given Admin is on dashboard page after Login and clicks User on the navigation bar
    And Admin is on Manage User Page

  @01UserPage_EditUser_verifyUserPopup
  Scenario Outline: Validate row level edit icon
    Given Admin is on Manage User Page
    When Admin clicks on the edit icon
    Then A new pop up with User details appears from sheet "<option>" and "<sheetName>"

    Examples: 
      | option                     | sheetName     |
      | VerifyUserDetailsPopupText | UserPage_Edit |

  @02UserPage_EditUserDetails_Valid
  Scenario Outline: Check if the fields are updated with valid data
    Given Admin clicks on the edit icon
    When Update the fields with valid data and click submit from sheet "<option>" and "<sheetName>"
    Then Admin gets message "User Updated Successfully" and see the updated values in data table

    Examples: 
      | option               | sheetName     |
      | UpdateUserMiddleName | UserPage_Edit |
      | UpdateUserLastName   | UserPage_Edit |
      | UpdateUserLocation   | UserPage_Edit |
      | UpdateUserPhoneNo    | UserPage_Edit |
      | UpdateUserLinkedUrl  | UserPage_Edit |
      | UpdateUserRole       | UserPage_Edit |
      | UpdateUserRoleStatus | UserPage_Edit |
      | UpdateUseUnderGrad   | UserPage_Edit |
      | UpdateUserEmail      | UserPage_Edit |
      | UpdateUserVisaStatus | UserPage_Edit |
      | UpdateUserPostGrad   | UserPage_Edit |
      | UpdateUserComments   | UserPage_Edit |
      | UpdateuserTimeZone   | UserPage_Edit |
      | UpdateUserAllDetails | UserPage_Edit |
      | UpdateMultipleFields | UserPage_Edit |

  @03UserPage_EditUserdetails_InValid
  Scenario Outline: Check if the fields are updated with invalid values
    Given Admin clicks on the edit icon
    When Update the fields with invalid values and click submit from sheet "<option>" and "<sheetName>"
    Then Admin should get Error message

    Examples: 
      | option                                | sheetName     |
      | InvalidUpdate_NumericValueInLocation  | UserPage_Edit |
      | InvalidUpdate_ExistingPhoneNum        | UserPage_Edit |
      | InvalidUpdate_invalidEmail            | UserPage_Edit |
      | InvalidUpdate_NumericValueInTextField | UserPage_Edit |
      | InvalidUpdate_InvalidTimezone         | UserPage_Edit |
<<<<<<< HEAD
=======
      | InvalidUpdate_PhoneNumber             | UserPage_Edit |
>>>>>>> 8f90cb4acc11f0e62d9e12bae67023f6b2fc77e8

  @04UserPage_EditUserdetails_MandatoryFields
  Scenario Outline: Check if the fields are updated with invalid values
    Given Admin clicks on the edit icon
    When Update the mandatory fields with valid values and click submit from sheet "<option>" and "<sheetName>"
    Then Admin gets message "User Updated Successfully" and see the updated values in data table

    Examples: 
      | option               | sheetName     |
      | UpdateMandatoryField | UserPage_Edit |

  @05UserPage_EditUserdetails_OptionalFields
  Scenario Outline: Check if the fields are updated with invalid values
    Given Admin clicks on the edit icon
    When Update the optional fields with valid values and click submit from sheet "<option>" and "<sheetName>"
    Then Admin gets message "User Updated Successfully" and see the updated values in data table

    Examples: 
      | option               | sheetName     |
      | UpdateOptionalFields | UserPage_Edit |
      | UpdateByMissingEmail | UserPage_Edit |

  @06UserPage_EditUserdetails_InvalidFieldsInTextFields
  Scenario Outline: Check if the fields are updated with invalid values
    Given Admin clicks on the edit icon
    When Admin enters only numbers or special char in the text fields from sheet "<option>" and "<sheetName>"
    Then Admin should get Error message

    Examples: 
      | option                                | sheetName     |
      | InvalidUpdate_NumericValueInTextField | UserPage_Edit |

  @07UserPage_EditUserdetails_CancelButton
  Scenario: Check if the fields are updated with invalid values
    Given Admin clicks on the edit icon
    When Admin clicks Cancel button on edit popup
    Then Admin can see the User details popup disappears and can see nothing changed for particular User
