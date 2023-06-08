package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_HomePage {
	
	WebDriver driver;
	public PO_HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".caret")
	private WebElement myAccountDropmenu;
	
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginOptionElement;
	
	@FindBy(linkText = "Register")
	private WebElement registerOptionElement;
	
	@FindBy(name = "search")
	private WebElement searchBarElement;
	
	@FindBy(css = "i[class*='fa-search']")
	private WebElement searchButtonElement;
	
	public void clickOnMyAccount()
	{
		myAccountDropmenu.click();
	}
	
	public PO_LoginPage selectLoginOption()
	{
		loginOptionElement.click();
		return new PO_LoginPage(driver);
	}

	public PO_RegisterPage selectRegisterOption()
	{
		registerOptionElement.click();
		return new PO_RegisterPage(driver);
	}
	
	public void enterProductName(String productName)
	{
		searchBarElement.sendKeys(productName);
	}
	
	public PO_SearchResultsPage clickOnSearchButton()
	{
		searchButtonElement.click();
		return new PO_SearchResultsPage(driver);
	}
	
	
	
}





















