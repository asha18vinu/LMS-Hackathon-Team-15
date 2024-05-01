package dataFilesReader;

import java.util.List;
import java.util.Map;
import dataFilesReader.ExcelFileSetup;
import dataFilesReader.ResourceBundleDataReader;

public class ExcelFileData extends ResourceBundleDataReader {

	// HomePage data
	public static String expectedTitle;
	public static String expectedResponsecode;
	public static String expectedPageText;
	public static String expectedtotalTextfield;
	public static String expectedFirstTextvalue;
	public static String expectedSecondTextvalue;
	public static String expectedfirstTextSymbol;
	public static String expectedSecondTextSymbol;
	public static String passwordInputColor;
	public static String userInputColor;
	public static String loginButtonAlignment;
	public static String inputFieldAlignment;
	public static String applicationName;
	public static String companyName;
	public static String signInContent;
	public static String LogoAlignment;
	public static String InvalidUrlEndpoint;
	public static String InvalidUrl;

	// LoginPage data
	public static String userName;
	public static String password;
	
	//UserPage data
	public static String userFirstName;
	public static String userMiddleName;
	public static String userLastName;
	public static String userLocation;
	public static String userPhoneNo;
	public static String userLinkedInUrl;
	public static String userRole;
	public static String userRoleStatus;
	public static String userUnderGraduate;
	public static String userEmail;
	public static String userVisaStatus;
	public static String userPostGraduate;
	public static String userTimeZone;
	public static String userComments;
	public static String userDetailsPopupText;
	public static String fullUserName;

	static Map<String, String> xLDataMap;

	// Home Page validation TestData
	public static Map<String, String> homePageExcelData(String key, String sheetName) throws Exception {

		xLDataMap = ExcelFileSetup.getData1(key, sheetName);

		expectedTitle = xLDataMap.get("expectedTitle");
		expectedResponsecode = xLDataMap.get("expectedResponsecode");
		expectedPageText = xLDataMap.get("expectedPageText");
		expectedtotalTextfield = xLDataMap.get("expectedtotalTextfield");
		expectedFirstTextvalue = xLDataMap.get("expectedFirstTextvalue");
		expectedSecondTextvalue = xLDataMap.get("expectedSecondTextvalue");
		passwordInputColor = xLDataMap.get("passwordInputColor");
		userInputColor = xLDataMap.get("userInputColor");
		loginButtonAlignment = xLDataMap.get("loginButtonAlignment");
		inputFieldAlignment = xLDataMap.get("inputFieldAlignment");
		applicationName = xLDataMap.get("applicationName");
		companyName = xLDataMap.get("companyName");
		signInContent = xLDataMap.get("signInContent");
		LogoAlignment = xLDataMap.get("LogoAlignment");
		InvalidUrlEndpoint = xLDataMap.get("InvalidUrlEndpoint");
		InvalidUrl = xLDataMap.get("InvalidUrl");
		expectedfirstTextSymbol=xLDataMap.get("expectedfirstTextSymbol");
		expectedSecondTextSymbol=xLDataMap.get("expectedSecondTextSymbol");
		
		return xLDataMap;

	}

	// Login Page validation TestData
	public static Map<String, String> loginPageExcelData(String key, String sheetName) throws Exception {

		xLDataMap = ExcelFileSetup.getData1(key, sheetName);

		userName = xLDataMap.get("UserNameData");
		password = xLDataMap.get("PasswordData");

		return xLDataMap;

	}
	
	// EDit/Update user details- User Page validation TestData
		public static Map<String, String> userPageEditExcelData(String key, String sheetName) throws Exception {

			xLDataMap = ExcelFileSetup.getData1(key, sheetName);

			userFirstName = xLDataMap.get("userFirstName");
			userMiddleName = xLDataMap.get("userMiddleName");
			userLastName = xLDataMap.get("userLastName");
			userLocation = xLDataMap.get("userLocation");
			userPhoneNo = xLDataMap.get("userPhoneNo");
			userLinkedInUrl = xLDataMap.get("userLinkedInUrl");
			userRole = xLDataMap.get("userRole");			
			userRoleStatus = xLDataMap.get("userRoleStatus");
			userUnderGraduate = xLDataMap.get("userUnderGraduate");
			userEmail = xLDataMap.get("userEmail");
			userVisaStatus = xLDataMap.get("userVisaStatus");
			userPostGraduate = xLDataMap.get("userPostGraduate");
			userTimeZone = xLDataMap.get("userTimeZone");
			userComments = xLDataMap.get("userComments");
			userDetailsPopupText=xLDataMap.get("userDetailsPopupText");
			fullUserName=xLDataMap.get("userFirstName")+" "+xLDataMap.get("userLastName");
			return xLDataMap;

		}

}
