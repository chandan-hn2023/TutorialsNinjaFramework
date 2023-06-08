package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_SearchResultsPage {
	
	WebDriver driver;
	public PO_SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "HP LP3065")
	WebElement validProductMessageElement;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	WebElement inValidProductMessageElement;
	
	public String retrieveValidProductNameMessage()
	{
		String validProductText = validProductMessageElement.getText();
		return validProductText;
	}
	
	public String retrieveInValidProductNameMessage()
	{
		String inValidProductText = inValidProductMessageElement.getText();
		return inValidProductText;
	}


}
