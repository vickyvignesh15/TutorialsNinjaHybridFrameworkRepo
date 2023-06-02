package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class RegisterPage extends Utilities {

	@FindBy(id = "input-firstname")
	WebElement firstNameField;

	@FindBy(id = "input-lastname")
	WebElement lastNameField;

	@FindBy(id = "input-email")
	WebElement emailField;

	@FindBy(id = "input-telephone")
	WebElement telephoneField;

	@FindBy(id = "input-password")
	WebElement passwordField;

	@FindBy(id = "input-confirm")
	WebElement confirmPasswordField;

	@FindBy(name = "agree")
	WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	WebElement yesNewsLetterButton;

	// Warnings
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	WebElement emailIsAlreadyRegisterdWarning;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	WebElement emailWarning;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	WebElement passwordWaring;

	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}


	public void clickOnSubmitButton() {
		submitButton.click();
	}

	public String retriveEmailIsAlreadyRegisterdWarning() {
		return emailIsAlreadyRegisterdWarning.getText();
	}
	
	
	//verifying all warnings
	public String retrivePrivacyPolicyWarning() {
		return privacyPolicyWarning.getText();	
	}
	
	public String retriveFirstNameWarning() {
		return firstNameWarning.getText();
	}
	
	public String retriveLastNameWarning() {
		return lastNameWarning.getText();
	}
	
	public String retriveEmailWaring() {
		return emailWarning.getText();
	}
	
	public String retriveTelephoneWarning() {
		return telephoneWarning.getText();
	}
	
	public String retrivePasswordWarning() {
		return passwordWaring.getText();
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstName,String lastName,String email,String telePhone,String password) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		telephoneField.sendKeys(telePhone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		privacyPolicyCheckbox.click();
		submitButton.click();
		
		return new AccountSuccessPage();
	}
	
	public AccountSuccessPage registerWithAllFields(String firstName,String lastName,String email,String telePhone,String password) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		telephoneField.sendKeys(telePhone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		yesNewsLetterButton.click();
		privacyPolicyCheckbox.click();
		submitButton.click();
		
		return new AccountSuccessPage();
	}
	
	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,String expectedLastNameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
			
		boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
		boolean firstNameWarningStatus = firstNameWarning.getText().contains(expectedFirstNameWarning);
		boolean lastNameWarningStatus = lastNameWarning.getText().contains(expectedLastNameWarning);
		boolean emailWarningStatus = emailWarning.getText().contains(expectedEmailWarning);
		boolean telePhoneWarningStatus = telephoneWarning.getText().contains(expectedTelephoneWarning);
		boolean passwordWarningStatus = passwordWaring.getText().contains(expectedPasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telePhoneWarningStatus && passwordWarningStatus;
				
	}
}
