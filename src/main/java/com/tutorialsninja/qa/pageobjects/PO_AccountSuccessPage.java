package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_AccountSuccessPage {
	
	WebDriver driver;
	public PO_AccountSuccessPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement accountSuccessfullyCreatedMessageElement;
	
	public String retrieveAccountSuccessfullyCreatedMessage()
	{
		String accountCreatedText = accountSuccessfullyCreatedMessageElement.getText();
		return accountCreatedText;
	}
	
	

}
