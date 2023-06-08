package com.tutorialsninja.qa.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.qa.base.base;
import com.tutorialsninja.qa.pageobjects.PO_AccountSuccessPage;
import com.tutorialsninja.qa.pageobjects.PO_HomePage;
import com.tutorialsninja.qa.pageobjects.PO_RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class TC_RegisterTest extends base {

	public WebDriver driver;
	PO_RegisterPage registerPage;
	PO_AccountSuccessPage accountSuccessPage;

	@BeforeMethod
	public void startUp() throws IOException {
		loadPropertiesFile();
		driver = intializeBrowserAndOpenApplication(prop.getProperty("browser"));
		PO_HomePage homePage = new PO_HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}

	@AfterMethod
	public void closeAllbrowsers() {
		driver.quit();
	}

	@Test(priority = 1)
	public void registerWithMandatoryFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailID(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelePhone(dataProp.getProperty("telePhoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.confirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnAgreePrivacy();
		accountSuccessPage = registerPage.clickOncontinueButton();
		
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessfullyCreatedMessage(),
				dataProp.getProperty("accountSuccessfullyCreatedMessage"));
	}

	@Test(priority = 2)
	public void registerWithAllTheFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailID(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelePhone(dataProp.getProperty("telePhoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.confirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnNewsLetterRadioButton();
		registerPage.clickOnAgreePrivacy();
		accountSuccessPage = registerPage.clickOncontinueButton();
		
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessfullyCreatedMessage(),
				dataProp.getProperty("accountSuccessfullyCreatedMessage"));
	}

	@Test(priority = 3)
	public void resgisterwithExistingAccount() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailID(prop.getProperty("validEmailID"));
		registerPage.enterTelePhone(dataProp.getProperty("telePhoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.confirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnNewsLetterRadioButton();
		registerPage.clickOnAgreePrivacy();
		registerPage.clickOncontinueButton();
		
		Assert.assertEquals(registerPage.retrieveAccountAlreadyRegisteredMessage(),
				dataProp.getProperty("accountAlreadyRegisteredMessage"));
	}

	@Test(priority = 4)
	public void resgisterwithoutEnteringMandatoryFields() {
		registerPage.clickOncontinueButton();
		
		Assert.assertEquals(registerPage.retrievePrivacyPolicyWarningMessage(),
				dataProp.getProperty("privacyPolicyWarning"));
		Assert.assertEquals(registerPage.retrieveFirstNameWarningMessage(),
				dataProp.getProperty("firstNameWarning"));
		Assert.assertEquals(registerPage.retrieveLastNameWarningMessage(),
				dataProp.getProperty("lastNameWarning"));
		Assert.assertEquals(registerPage.retrieveEmailWarningMessage(),
				dataProp.getProperty("emailWarning"));
		Assert.assertEquals(registerPage.retrieveTelephoneWarningMessage(),
				dataProp.getProperty("telephoneWarning"));
		Assert.assertEquals(registerPage.retrievePasswordWarningMessage(),
				dataProp.getProperty("passwordWarning"));
	}
}
