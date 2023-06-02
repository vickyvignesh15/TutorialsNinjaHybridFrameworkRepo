package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class LoginPage extends Utilities{
	
	@FindBy(id="input-email")
	WebElement emailField;
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	WebElement emailPasswordNoMatchWarning;
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Login Functionality
	
	public AccountPage login(String email, String password) {
		
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		
		return new AccountPage();
	}
	
	
	//Login warning retriever
	public String retriveEmailPasswordNoMatchWarningWarningMessage() {
		return emailPasswordNoMatchWarning.getText();
	}
	

}
