package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorials.qa.base.base;
import com.tutorialsninja.qa.pageobjects.PO_AccountPage;
import com.tutorialsninja.qa.pageobjects.PO_HomePage;
import com.tutorialsninja.qa.pageobjects.PO_LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class TC_LoginPageTest extends base {

	public WebDriver driver;
	PO_LoginPage loginPage;

	@BeforeMethod
	public void startUp() throws IOException {
		loadPropertiesFile();
		driver = intializeBrowserAndOpenApplication(prop.getProperty("browser"));
		PO_HomePage homePage = new PO_HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
	} 

	@AfterMethod
	public void closeAllbrowsers() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "supplyTestDataToLogin")
	public void verifyLoginWithValidCreds(String email, String password) {
		loginPage.enterEmailID(email);
	    loginPage.enterPassword(password);
	    PO_AccountPage accountPage = loginPage.clickOnLoginButton();

		Assert.assertTrue(accountPage.retrieveLoginConfirmationMessage(),
				"Edit your account information is not displayed");
	}

	@DataProvider
	public Object[][] supplyTestDataToLogin() throws IOException {
		Object[][] data = Utilities.readExcelData("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCreds() {
		loginPage.enterEmailID(Utilities.generateEmailWithTimeStamp());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.retrieveInvalidCredentialsWarningMessage(),
				dataProp.getProperty("invalidCredentialsWarning"));
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		loginPage.enterEmailID(dataProp.getProperty("invalidEmailID"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.retrieveInvalidCredentialsWarningMessage(),
				dataProp.getProperty("invalidCredentialsWarning"));
	}

	@Test(priority = 4)
	public void verifyLoginWithvalidEmailAndInValidPassword() {
		loginPage.enterEmailID(prop.getProperty("validEmailID"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.retrieveInvalidCredentialsWarningMessage(),
				dataProp.getProperty("invalidCredentialsWarning"));
	}

	@Test(priority = 5)
	public void verifyLoginWithNoEmailPassword() {
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.retrieveInvalidCredentialsWarningMessage(),
				dataProp.getProperty("invalidCredentialsWarning"));
	}

}
