package com.tutorialsninja.qa.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class PO_RegisterPage {

	WebDriver driver;

	public PO_RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	WebElement firstNameElement;

	@FindBy(id = "input-lastname")
	WebElement lastNameElement;

	@FindBy(id = "input-email")
	WebElement emailElement;

	@FindBy(id = "input-telephone")
	WebElement telePhoneElement;

	@FindBy(id = "input-password")
	WebElement passwordElement;

	@FindBy(id = "input-confirm")
	WebElement confirmPasswordElement;

	@FindBy(name = "agree")
	WebElement agreePrivacyElement;

	@FindBy(css = "input[value='Continue']")
	WebElement continueButtonWebElement;

	@FindBy(css = "input[name='newsletter'][value='1']")
	WebElement newsLetterElement;

	@FindBy(css = "div[class*='alert-dismissible']")
	WebElement accountAlreadyRegisteredMessageElement;

	@FindBy(css = "div[class*='alert-dismissible']")
	WebElement privacyPolicyWarningMessageElement;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	WebElement firstNameWarningMessageElement;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	WebElement lastNameWarningMessageElement;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	WebElement emailWarningMessageElement;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	WebElement telephoneWarningMessageElement;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	WebElement passwordWarningMessageElement;

	public void enterFirstName(String firstNameText) {
		firstNameElement.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameElement.sendKeys(lastNameText);
	}

	public void enterEmailID(String emailText) {
		emailElement.sendKeys(emailText);
	}

	public void enterTelePhone(String telePhoneText) {
		telePhoneElement.sendKeys(telePhoneText);
	}

	public void enterPassword(String passwordText) {
		passwordElement.sendKeys(passwordText);
	}

	public void confirmPassword(String confirmPasswordText) {
		confirmPasswordElement.sendKeys(confirmPasswordText);
	}

	public void clickOnAgreePrivacy() {
		agreePrivacyElement.click();
	}

	public PO_AccountSuccessPage clickOncontinueButton() {
		continueButtonWebElement.click();
		return new PO_AccountSuccessPage(driver);
	}

	public void clickOnNewsLetterRadioButton() {
		newsLetterElement.click();
	}

	public String retrieveAccountAlreadyRegisteredMessage() {
		String alreadyRegisteredAccountText = accountAlreadyRegisteredMessageElement.getText();
		return alreadyRegisteredAccountText;
	}

	public String retrievePrivacyPolicyWarningMessage() {
		String privacyPolicyWarningMessageText = privacyPolicyWarningMessageElement.getText();
		return privacyPolicyWarningMessageText;
	}

	public String retrieveFirstNameWarningMessage() {
		String firstNameWarningMessageText = firstNameWarningMessageElement.getText();
		return firstNameWarningMessageText;
	}

	public String retrieveLastNameWarningMessage() {
		String LastNameWarningMessageText = lastNameWarningMessageElement.getText();
		return LastNameWarningMessageText;
	}

	public String retrieveEmailWarningMessage() {
		String emailWarningMessageText = emailWarningMessageElement.getText();
		return emailWarningMessageText;
	}

	public String retrieveTelephoneWarningMessage() {
		String telephoneWarningMessageText = telephoneWarningMessageElement.getText();
		return telephoneWarningMessageText;
	}

	public String retrievePasswordWarningMessage() {
		String passwordWarningMessageText = passwordWarningMessageElement.getText();
		return passwordWarningMessageText;
	}

}
