package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_AccountPage {
	
	WebDriver driver;
	public PO_AccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement loginConfirmationElement;
	
	public boolean retrieveLoginConfirmationMessage()
	{
		boolean loginStatus = loginConfirmationElement.isDisplayed();
		return loginStatus;
	}
}
