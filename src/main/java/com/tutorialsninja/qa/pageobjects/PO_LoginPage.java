package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_LoginPage {
	
	WebDriver driver;
	public PO_LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-email")
    private WebElement emailElement;
	
	@FindBy(id = "input-password")
	private WebElement passwordElement;

	@FindBy(css = "input[value='Login']")
	private WebElement loginButtonElement;
	
	@FindBy(css = "div[class*='alert']")
	private WebElement invalidCredentialsWarningElement;
	
	public void enterEmailID(String emailText)
	{
		emailElement.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordElement.sendKeys(passwordText);
	}
	
	public PO_AccountPage clickOnLoginButton()
	{
		loginButtonElement.click();
		return new PO_AccountPage(driver); 
	}
	
	public String retrieveInvalidCredentialsWarningMessage()
	{
		String warningText = invalidCredentialsWarningElement.getText();
		return warningText;
	}
	
}























