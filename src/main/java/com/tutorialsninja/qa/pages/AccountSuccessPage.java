package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class AccountSuccessPage extends Utilities {

	@FindBy(xpath="//div [@id='content']//h1")
	WebElement accountSucessfullyCreatedMessage;
	
	
	public AccountSuccessPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	// Actions
	public String retriveAccountSucessfullyCreatedMessage() {
		return accountSucessfullyCreatedMessage.getText();
	}
}
