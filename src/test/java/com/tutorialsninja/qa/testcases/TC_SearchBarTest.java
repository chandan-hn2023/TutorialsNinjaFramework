package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.qa.base.base;
import com.tutorialsninja.qa.pageobjects.PO_HomePage;
import com.tutorialsninja.qa.pageobjects.PO_SearchResultsPage;

//Final update June8th 2023
public class TC_SearchBarTest extends base {

	public WebDriver driver;
	PO_SearchResultsPage searchResultsPage;

	@BeforeMethod
	public void startUp() throws IOException {
		loadPropertiesFile();
		driver = intializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}

	@AfterMethod
	public void closeAllbrowsers() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithExistingProductName() {
		PO_HomePage homePage = new PO_HomePage(driver);
		homePage.enterProductName(dataProp.getProperty("validProduct"));
		searchResultsPage = homePage.clickOnSearchButton();
		
		Assert.assertEquals(searchResultsPage.retrieveValidProductNameMessage(),
				dataProp.getProperty("validProductConfirmationMessage"));
	}

	@Test(priority = 2)
	public void verifySearchWithNonExistingProductName() {
		PO_HomePage homePage = new PO_HomePage(driver);
		homePage.enterProductName(dataProp.getProperty("inValidProduct"));
		searchResultsPage = homePage.clickOnSearchButton();
		
		Assert.assertEquals(searchResultsPage.retrieveInValidProductNameMessage(),
				dataProp.getProperty("noProductWarning"));
	}

	@Test(priority = 3)
	public void verifySearchWithoutProvidingProductName() {
		PO_HomePage homePage = new PO_HomePage(driver);
		searchResultsPage = homePage.clickOnSearchButton();
		
		Assert.assertEquals(searchResultsPage.retrieveInValidProductNameMessage(),
				dataProp.getProperty("noProductWarning"));
	}

}
